package com.juma.tgm.mq.rabbit.sync;

import com.alibaba.fastjson.JSON;
import com.juma.auth.user.domain.LoginUser;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.service.ConfParamService;
import com.juma.tgm.common.Constants;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.fms.domain.ReconciliationItem;
import com.juma.tgm.fms.service.ReconciliationService;
import com.juma.tgm.vendor.service.VendorMappingService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.WaybillService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName WaybillSync.java
 * @Description 更新运单表信息
 * @author Libin.Wei
 * @Date 2017年10月13日 下午3:19:25
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Component
public class CustomerInfoSync {

    private final Logger log = LoggerFactory.getLogger(CustomerInfoSync.class);
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private com.juma.crm.customer.service.CustomerInfoService crmCustomerInfoService;
    @Resource
    private WaybillService waybillService;
    @Resource
    private ReconciliationService reconciliationService;
    @Resource
    private ConfParamService confParamService;
    @Resource
    private VendorMappingService vendorMappingService;

    /**
     * 
     * 同步客户表
     * 
     * @author Libin.Wei
     * @Date 2017年10月20日 下午12:01:39
     * @param crmCustomerId
     */
    public void insertOrUpdate(Integer crmCustomerId) {
        if (null == crmCustomerId) {
            return;
        }

        com.juma.crm.customer.domain.CustomerInfo crmCustomerInfo = crmCustomerInfoService
                .findIgnoreDelete(crmCustomerId);
        if (null == crmCustomerInfo) {
            return;
        }
        log.info("CRM系统回传的数据：{}", JSON.toJSONString(crmCustomerInfo));
        log.info("CRM系统回传的数据isDelete：{}", crmCustomerInfo.isDelete());

        if (null == crmCustomerInfo.getTenantId()) {
            log.info("CRM系统回传的租户ID为空，不能同步，creCustomerId：{}", crmCustomerInfo.getCustomerId());
        }

        LoginUser LoginUser = Constants.SYS_LOGIN_USER;
        LoginUser.setTenantId(crmCustomerInfo.getTenantId());

        // 只同步客户信息
        if (null == crmCustomerInfo.getCustomerType() || crmCustomerInfo.getCustomerType()
                .equals(com.juma.crm.customer.domain.CustomerInfo.CustomerType.DRIVER.getValue())) {
            return;
        }

        CustomerInfo customerInfo = customerInfoService.findAllByCrmId(crmCustomerId);
        if (null == customerInfo) {
            // 添加
            if (checkSwitch()) {
                customerInfo = new CustomerInfo();
                buildCustomerInfo(crmCustomerInfo, customerInfo);
                customerInfo.setIsReceiveDailySms(true);
                customerInfo.setCreateTime(crmCustomerInfo.getCreateTime());
                customerInfoService.insert(customerInfo, LoginUser);
            }
            return;
        }

        // 原客户名称
        String oldCustomerName = customerInfo.getCustomerName();

        // 修改
        buildCustomerInfo(crmCustomerInfo, customerInfo);
        if (checkSwitch()) {
            customerInfo.setLastUpdateTime(crmCustomerInfo.getLastUpdateTime());
            customerInfoService.update(customerInfo, LoginUser);
        }

        // 更新运单表中的客户名称
        this.renewCustomerIfoInWaybill(customerInfo, oldCustomerName);
        // 更新对帐名目的客户信息
        this.renewReconciliationItemInItem(customerInfo, oldCustomerName);
        // 更改承运商客户名称
        vendorMappingService.updateVendorCustomerName(customerInfo.getCustomerId(), customerInfo.getCustomerName());
    }

    // 构造同步内容
    private void buildCustomerInfo(com.juma.crm.customer.domain.CustomerInfo crmCustomerInfo,
            CustomerInfo customerInfo) {

        log.info("CRM系统回传的租户ID是：{}", crmCustomerInfo.getTenantId());

        //老的CRM ID  雄鹰计划
        customerInfo.setOldId(crmCustomerInfo.getOldId());
        // crm客户ID
        customerInfo.setCrmCustomerId(crmCustomerInfo.getCustomerId());
        // 客户名称
        customerInfo.setCustomerName(crmCustomerInfo.getCustomerName());
        // 客户经理
        customerInfo.setCustomerManagerUserId(crmCustomerInfo.getUserId());
        // 状态
        customerInfo.setStatus(crmCustomerInfo.getStatus() == null ? null : crmCustomerInfo.getStatus().intValue());
        // 客户类型
        customerInfo.setCustomerType(crmCustomerInfo.getCustomerType().intValue());
        // 创建人
        customerInfo.setCreateUserId(crmCustomerInfo.getCreateUserId());
        // 最后更新人
        customerInfo.setLastUpdateUserId(crmCustomerInfo.getLastUpdateUserId());
        // 删除状态
        customerInfo.setIsDelete(crmCustomerInfo.isDelete());
        // 租户编码
        customerInfo.setTenantId(crmCustomerInfo.getTenantId());
        customerInfo.setTenantCode(crmCustomerInfo.getTenantCode());
        // 行政区域编码
        customerInfo.setRegionCode(crmCustomerInfo.getRegionCode());
        // 业务区域
        customerInfo.setAreaCode(crmCustomerInfo.getAreaCode());
        // 来源渠道编号
        customerInfo.setSourceChanelCode(crmCustomerInfo.getSourceChannelCode());
        // 联系人
        customerInfo.setLinkMan(crmCustomerInfo.getContactsName());
        // 联系人电话
        customerInfo.setLinkTel(crmCustomerInfo.getContactsPhone());
    }

    // 客户表mq同步开关
    private boolean checkSwitch() {
        List<ConfParamOption> list = confParamService.findParamOptions("TGM_SWITCH");
        for (ConfParamOption option : list) {
            if ("CUSTOMER_INFO_MQ".equals(option.getOptionName()) && "ON".equals(option.getOptionValue())) {
                return true;
            }
        }
        return false;
    }

    // 更新运单表中的客户信息
    private void renewCustomerIfoInWaybill(CustomerInfo customerInfo, String oldCustomerName) {
        // 若CRM没有更改客户名称，则不去同步waybill表与ReconciliationItem表中的客户信息
        if (StringUtils.isBlank(customerInfo.getCustomerName())
                || customerInfo.getCustomerName().equals(oldCustomerName)) {
            return;
        }

        Waybill example = new Waybill();
        example.setCustomerId(customerInfo.getCustomerId());
        Waybill newValue = new Waybill();
        newValue.setCustomerName(customerInfo.getCustomerName());

        waybillService.updateWaybillBatch(example, newValue, null);
    }

    // 更新对帐名目的客户信息
    private void renewReconciliationItemInItem(CustomerInfo customerInfo, String oldCustomerName) {
        // 若CRM没有更改客户名称，则不去同步waybill表与ReconciliationItem表中的客户信息
        if (StringUtils.isBlank(customerInfo.getCustomerName())
                || customerInfo.getCustomerName().equals(oldCustomerName)) {
            return;
        }

        ReconciliationItem item = new ReconciliationItem();
        item.setCustomerId(customerInfo.getCustomerId());
        item.setCustomerName(customerInfo.getCustomerName());
        reconciliationService.updateReconciliationItemByCustomerId(item);
    }

}
