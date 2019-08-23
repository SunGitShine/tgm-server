package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @Description: 报表数据列表
 * @author weilibin
 * @date 2016年8月1日 下午5:08:56
 * @version V1.0
 */

public class ReportFormDetail implements Serializable {

    private static final long serialVersionUID = -5945291936136870803L;
    /** 日期 */
    private String createDate;
    /** 司机名 */
    private String driverName;
    /** 发货人 */
    private String brokerName;
    /** 税前费用 */
    private BigDecimal preTaxFreight;
    /** 税后费用 */
    private BigDecimal afterTaxFreight;
    /** 司机收入 */
    private BigDecimal show4DriverFreight;
    /** 运单ID */
    private Integer waybillId;
    /** 运单号 */
    private String waybillNo;
    /** 所属客户 */
    private String customerName;
    /** 付款方式 */
    private Integer receiptType;
    /** 用车时间 */
    private String planDeliveryDate;
    /** 结算时间 */
    private String checkoutDate;
    /** 搬运费 */
    private BigDecimal handlingCost;
    /** 是否结算 */
    private String checkOut;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getPreTaxFreight() {
        return preTaxFreight;
    }

    public void setPreTaxFreight(BigDecimal preTaxFreight) {
        this.preTaxFreight = preTaxFreight;
    }

    public BigDecimal getAfterTaxFreight() {
        return afterTaxFreight;
    }

    public void setAfterTaxFreight(BigDecimal afterTaxFreight) {
        this.afterTaxFreight = afterTaxFreight;
    }

    public BigDecimal getShow4DriverFreight() {
        return show4DriverFreight;
    }

    public void setShow4DriverFreight(BigDecimal show4DriverFreight) {
        this.show4DriverFreight = show4DriverFreight;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(Integer receiptType) {
        this.receiptType = receiptType;
    }

    public String getPlanDeliveryDate() {
        return planDeliveryDate;
    }

    public void setPlanDeliveryDate(String planDeliveryDate) {
        this.planDeliveryDate = planDeliveryDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public BigDecimal getHandlingCost() {
        return handlingCost;
    }

    public void setHandlingCost(BigDecimal handlingCost) {
        this.handlingCost = handlingCost;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

}
