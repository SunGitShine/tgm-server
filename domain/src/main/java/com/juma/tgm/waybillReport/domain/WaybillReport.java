package com.juma.tgm.waybillReport.domain;

import com.juma.auth.user.domain.User;
import com.juma.tgm.base.domain.BaseIncomeInfo;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.customer.domain.TruckCustomer;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.waybill.domain.Waybill;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName WaybillReport.java
 * @Description 运单报表
 * @author Libin.Wei
 * @Date 2017年2月4日 下午4:53:31
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class WaybillReport implements Serializable {

    private static final long serialVersionUID = 4013883756427865913L;
    /** 运单 */
    private Waybill waybill;
    /** 大客户 */
    private CustomerInfo customerInfo;
    /** 客户经理(企业客户) */
    private TruckCustomer truckCustomer;
    /** 司机 */
    private Driver driver;
    /** 车辆信息 */
    private Truck truck;
    /** 用户 */
    private User user;
    /** 是否允许结算 */
    private boolean allowBalance;
    /** 批量结算ID集合 */
    private List<Integer> listSelectIds;
    /** 是否结算 */
    private String checkoutStr;
    /** 支付方式 */
    private String receiptTypeStr;
    /** 车型名称 */
    private String truckTypeName;
    /** 建单时间 */
    private String createDate;
    /** 用车时间 */
    private String planDeliveryDate;
    /** 本月收入信息 */
    private BaseIncomeInfo theMonthIncomeInfo;
    /** 上月收入信息 */
    private BaseIncomeInfo preMonthIncomeInfo;
    /** 收款时间 */
    private Date receivableTime;

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public TruckCustomer getTruckCustomer() {
        return truckCustomer;
    }

    public void setTruckCustomer(TruckCustomer truckCustomer) {
        this.truckCustomer = truckCustomer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Waybill getWaybill() {
        return waybill;
    }

    public void setWaybill(Waybill waybill) {
        this.waybill = waybill;
    }

    public boolean isAllowBalance() {
        return allowBalance;
    }

    public void setAllowBalance(boolean allowBalance) {
        this.allowBalance = allowBalance;
    }

    public List<Integer> getListSelectIds() {
        return listSelectIds;
    }

    public void setListSelectIds(List<Integer> listSelectIds) {
        this.listSelectIds = listSelectIds;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPlanDeliveryDate() {
        return planDeliveryDate;
    }

    public void setPlanDeliveryDate(String planDeliveryDate) {
        this.planDeliveryDate = planDeliveryDate;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public String getReceiptTypeStr() {
        return receiptTypeStr;
    }

    public void setReceiptTypeStr(String receiptTypeStr) {
        this.receiptTypeStr = receiptTypeStr;
    }

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
    }

    public String getCheckoutStr() {
        return StringUtils.isNotBlank(checkoutStr) ? checkoutStr
                : (this.waybill == null ? "未收款"
                        : (this.waybill.getReceiptStatus() == Waybill.ReceiptStatus.HAS_COLLECTION.getCode()
                                ? "已收款" : "未收款"));
    }

    public void setCheckoutStr(String checkoutStr) {
        this.checkoutStr = checkoutStr;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public BaseIncomeInfo getTheMonthIncomeInfo() {
        return theMonthIncomeInfo;
    }

    public void setTheMonthIncomeInfo(BaseIncomeInfo theMonthIncomeInfo) {
        this.theMonthIncomeInfo = theMonthIncomeInfo;
    }

    public BaseIncomeInfo getPreMonthIncomeInfo() {
        return preMonthIncomeInfo;
    }

    public void setPreMonthIncomeInfo(BaseIncomeInfo preMonthIncomeInfo) {
        this.preMonthIncomeInfo = preMonthIncomeInfo;
    }

    public Date getReceivableTime() {
        return receivableTime;
    }

    public void setReceivableTime(Date receivableTime) {
        this.receivableTime = receivableTime;
    }

    @Override
    public String toString() {
        return "WaybillReport [theMonthIncomeInfo=" + theMonthIncomeInfo + ", preMonthIncomeInfo=" + preMonthIncomeInfo
                + "]";
    }

}
