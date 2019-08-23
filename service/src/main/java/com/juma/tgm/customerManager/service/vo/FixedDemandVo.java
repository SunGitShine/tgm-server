package com.juma.tgm.customerManager.service.vo;

import com.alibaba.fastjson.JSONObject;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.customer.domain.TruckCustomer;
import com.juma.tgm.customerManager.domain.FixedDemand;
import com.juma.tgm.customerManager.domain.FixedDemandDeliveryPoint;
import com.juma.tgm.customerManager.domain.FixedDemandTruck;
import com.juma.tgm.project.domain.Project;
import com.juma.tgm.truck.domain.bo.DriverTruckInfoBo;
import com.juma.tgm.waybill.domain.TruckRequire;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: FixedDemandVo
 * @Description:
 * @author: liang
 * @date: 2017-07-24 19:28
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class FixedDemandVo implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(FixedDemandVo.class);
    /****基础数据****/
    private FixedDemand fixedDemand;

    /**
     * 取货地
     */
    private List<FixedDemandDeliveryPoint> receiveAddresses;

    /**
     * 配送地
     */
    private List<FixedDemandDeliveryPoint> deliveryAddresses;

    /****基础数据****/

    private TruckCustomer truckCustomer;

    /**
     * 企业信息
     */
    private CustomerInfo customerInfo;

    private String requireStr;

    private String truckTypeStr;

    private String goodsInfoStr;

    private String receiptTypeStr;

    private TruckRequire truckRequire;

    /**
     * 自动下单策略
     */
    private FixedDemand.BillStrategy billStrategy;

    /**
     * 固定车辆
     */
    private List<FixedDemandTruck> fixedDemandTruck;

    // 固定车辆详情
    private List<DriverTruckInfoBo> fixedDemandTruckDetails = new ArrayList<DriverTruckInfoBo>();

    /**
     * 项目名称
     */
    private String projectName;

    private Project project;



    public FixedDemand getFixedDemand() {
        return fixedDemand;
    }

    public void setFixedDemand(FixedDemand fixedDemand) {
        this.fixedDemand = fixedDemand;
    }

    public TruckCustomer getTruckCustomer() {
        return truckCustomer;
    }

    public void setTruckCustomer(TruckCustomer truckCustomer) {
        this.truckCustomer = truckCustomer;
    }

    public String getRequireStr() {
        return requireStr;
    }

    public void setRequireStr(String requireStr) {
        this.requireStr = requireStr;
    }

    public String getReceiptTypeStr() {
        return receiptTypeStr;
    }

    public void setReceiptTypeStr(String receiptTypeStr) {
        this.receiptTypeStr = receiptTypeStr;
    }

    public List<FixedDemandDeliveryPoint> getReceiveAddresses() {
        return receiveAddresses;
    }

    public void setReceiveAddresses(List<FixedDemandDeliveryPoint> receiveAddresses) {
        this.receiveAddresses = receiveAddresses;
    }

    public List<FixedDemandDeliveryPoint> getDeliveryAddresses() {
        return deliveryAddresses;
    }

    public void setDeliveryAddresses(List<FixedDemandDeliveryPoint> deliveryAddresses) {
        this.deliveryAddresses = deliveryAddresses;
    }

    public String getTruckTypeStr() {
        return truckTypeStr;
    }

    public void setTruckTypeStr(String truckTypeStr) {
        this.truckTypeStr = truckTypeStr;
    }

    public String getGoodsInfoStr() {
        return goodsInfoStr;
    }

    public void setGoodsInfoStr(String goodsInfoStr) {
        this.goodsInfoStr = goodsInfoStr;
    }

    public TruckRequire getTruckRequire() {
        return truckRequire;
    }

    public void setTruckRequire(TruckRequire truckRequire) {
        this.truckRequire = truckRequire;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public FixedDemand.BillStrategy getBillStrategy() {
        return billStrategy;
    }

    public void setBillStrategy(FixedDemand.BillStrategy billStrategy) {
        this.billStrategy = billStrategy;
    }

    public List<FixedDemandTruck> getFixedDemandTruck() {
        return fixedDemandTruck;
    }

    public void setFixedDemandTruck(List<FixedDemandTruck> fixedDemandTruck) {
        this.fixedDemandTruck = fixedDemandTruck;
    }

    public List<DriverTruckInfoBo> getFixedDemandTruckDetails() {
        return fixedDemandTruckDetails;
    }

    public void setFixedDemandTruckDetails(List<DriverTruckInfoBo> fixedDemandTruckDetails) {
        this.fixedDemandTruckDetails = fixedDemandTruckDetails;
    }

    public FixedDemand.BillStrategy billStrategyToObject() {
        if (this.fixedDemand == null) return null;
        if (StringUtils.isBlank(this.fixedDemand.getAutoCreateBillStrategy())) return null;

        FixedDemand.BillStrategy strategy = null;
        try {
            strategy = JSONObject.parseObject(fixedDemand.getAutoCreateBillStrategy(), FixedDemand.BillStrategy.class);
        } catch (Exception e) {
            logger.info("固定需求反序列化错误", e);
        }

        return strategy;
    }

    public String billStrategyToJson(FixedDemand.BillStrategy billStrategy) {
        if (billStrategy == null) return null;
        String json = null;
        try {
            json = JSONObject.toJSONString(billStrategy);
        } catch (Exception e) {
            logger.info("固定需求序列化错误", e);
        }

        return json;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}
