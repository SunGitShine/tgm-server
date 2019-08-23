package com.juma.tgm.customerInfo.service.impl;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.juma.auth.user.domain.LoginUser;
import com.juma.crm.support.service.Crm4TmsService;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.crm.service.RemedySyncService;
import testng.BaseTestNGTest;

/**
 * @author Libin.Wei
 * @version 1.0.0
 * @ClassName CustomerInfoServiceImplTest.java
 * @Description 请填写注释...
 * @Date 2017年8月23日 下午4:51:52
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class CustomerInfoServiceImplTest extends BaseTestNGTest {

    @Resource
    private CustomerInfoService customerInfoService;
//    @Resource
//    private ConfParamService confParamService;
//    @Resource
//    private com.juma.crm.customer.service.CustomerInfoService crmCustomerInfoService;
//    @Resource
//    private ChanelService chanelService;
    @Resource
    private Crm4TmsService crm4TmsService;
    @Resource
    private RemedySyncService remedySyncService;

//    @Test
//    public void findByEmployeeId() {
//
//        List<CustomerInfo> result = new ArrayList<CustomerInfo>();
//        CustomerInfoResp customerInfoResp = customerInfoService.findByEmployeeId(null, 546878454);
//        if (null == customerInfoResp) {
//            return;
//        }
//
//        for (CustomerInfo customerInfo : customerInfoResp.getCustomerInfoList()) {
//            if (customerInfo.getStatus() == 2) {
//                continue;
//            }
//            result.add(customerInfo);
//        }
//        System.out.println(JSON.toJSONString(customerInfoResp));
//    }
//
//    @Test
//    public void testTms() {
//        Integer crmCustomerId = 84966;
//        CustomerInfo customerInfo = customerInfoService.findAllByCrmId(crmCustomerId);
//        System.out.println(customerInfo);
//    }
//
//    @Test
//    public void testCrm() {
//        Integer crmCustomerId = 84966;
//        if (null == crmCustomerId) {
//            return;
//        }
//
//        com.juma.crm.customer.domain.CustomerInfo crmCustomerInfo = crmCustomerInfoService.findIgnoreDelete(crmCustomerId);
//        if (null == crmCustomerInfo) {
//            return;
//        }
//
//        if (null == crmCustomerInfo.getTenantId()) {
//        }
//
//        LoginUser LoginUser = Constants.SYS_LOGIN_USER;
//        LoginUser.setTenantId(crmCustomerInfo.getTenantId());
//
//        // 只同步客户信息
//        if (null == crmCustomerInfo.getCustomerType() || crmCustomerInfo.getCustomerType()
//            .equals(com.juma.crm.customer.domain.CustomerInfo.CustomerType.DRIVER.getValue())) {
//            return;
//        }
//
//        CustomerInfo customerInfo = customerInfoService.findAllByCrmId(crmCustomerId);
//        if (null == customerInfo) {
//            // 添加
//            if (checkSwitch()) {
//                customerInfo = new CustomerInfo();
//                buildCustomerInfo(crmCustomerInfo, customerInfo);
//                customerInfo.setCreateTime(crmCustomerInfo.getCreateTime());
//                customerInfoService.insert(customerInfo, LoginUser);
//            }
//            return;
//        }
//
//        // 原客户名称
//        String oldCustomerName = customerInfo.getCustomerName();
//
//        // 修改
//        buildCustomerInfo(crmCustomerInfo, customerInfo);
//        if (checkSwitch()) {
//            customerInfo.setLastUpdateTime(crmCustomerInfo.getLastUpdateTime());
//            customerInfoService.update(customerInfo, LoginUser);
//        }
//    }
//
//    // 构造同步内容
//    private void buildCustomerInfo(com.juma.crm.customer.domain.CustomerInfo crmCustomerInfo,
//                                   CustomerInfo customerInfo) {
//
//        // crm客户ID
//        customerInfo.setCrmCustomerId(crmCustomerInfo.getCustomerId());
//        // 客户名称
//        customerInfo.setCustomerName(crmCustomerInfo.getCustomerName());
//        // 客户经理
//        customerInfo.setCustomerManagerUserId(crmCustomerInfo.getUserId());
//        // 状态
//        customerInfo.setStatus(crmCustomerInfo.getStatus() == null ? null : crmCustomerInfo.getStatus().intValue());
//        // 客户类型
//        customerInfo.setCustomerType(crmCustomerInfo.getCustomerType().intValue());
//        // 创建人
//        customerInfo.setCreateUserId(crmCustomerInfo.getCreateUserId());
//        // 最后更新人
//        customerInfo.setLastUpdateUserId(crmCustomerInfo.getLastUpdateUserId());
//        // 删除状态
//        customerInfo.setIsDelete(crmCustomerInfo.isDelete());
//        // 租户编码
//        customerInfo.setTenantId(crmCustomerInfo.getTenantId());
//        customerInfo.setTenantCode(crmCustomerInfo.getTenantCode());
//        // 行政区域编码
//        customerInfo.setRegionCode(crmCustomerInfo.getRegionCode());
//        // 业务区域
//        customerInfo.setAreaCode(crmCustomerInfo.getAreaCode());
//        // 来源渠道编号
//        customerInfo.setSourceChanelCode(crmCustomerInfo.getSourceChannelCode());
//        // 联系人
//        customerInfo.setLinkMan(crmCustomerInfo.getContactsName());
//        // 联系人电话
//        customerInfo.setLinkTel(crmCustomerInfo.getContactsPhone());
//
//    }
//
//    // 客户表mq同步开关
//    private boolean checkSwitch() {
//        List<ConfParamOption> list = confParamService.findParamOptions("TGM_SWITCH");
//        for (ConfParamOption option : list) {
//            if ("CUSTOMER_INFO_MQ".equals(option.getOptionName()) && "ON".equals(option.getOptionValue())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Test
//    public void testCh() {
//        List<Chanel> list = chanelService.findChildrenChanel(0);
//        System.out.println(JSON.toJSONString(list));
//    }


    @Test
    public void customerRebateTest() {
//        CustomerInfo cusInfoByName = customerInfoService.findCusInfoByName("2342342",
//                new LoginUser(2, 1));
        remedySyncService.doSync(89353, 19);
//        System.out.println(cusInfoByName);
    }

    @Test
    public void findDepartmentByCustomerId(){
        LoginUser loginUser = new LoginUser();
        loginUser.setTenantId(9);
        loginUser.setUserId(55087);
        customerInfoService.findDepartmentByCustomerId(15883, loginUser);
    }
}
