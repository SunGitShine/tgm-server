package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import me.about.poi.ExcelColumn;

/**
 * @ClassName WaybillExport.java
 * @Description 运单导出
 * @author Libin.Wei
 * @Date 2017年1月17日 下午8:46:46
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class WaybillExport implements Serializable {

    private static final long serialVersionUID = -694083037858864528L;
    @ExcelColumn(name = "运单号")
    private String waybillNo;
    @ExcelColumn(name = "业务区域")
    private String areaName;
    @ExcelColumn(name = "状态")
    private String statusViewText;
    @ExcelColumn(name = "所属客户")
    private String customerName;
    @ExcelColumn(name = "用车人")
    private String truckCustomerName;
    @ExcelColumn(name = "客户经理")
    private String customerManagerName;
    @ExcelColumn(name = "下单时间")
    private String createDate;
    @ExcelColumn(name = "用车时间")
    private String planDeliveryDate;
    @ExcelColumn(name = "司机")
    private String driverName;
    @ExcelColumn(name = "司机类型")
    private String driverType;
    @ExcelColumn(name = "车牌号")
    private String plateNumber;
    @ExcelColumn(name = "税前费用")
    private BigDecimal estimateFreight;
    @ExcelColumn(name = "税后费用")
    private BigDecimal afterTaxFreight;
    @ExcelColumn(name = "司机结算价")
    private BigDecimal show4DriverFreight;
    @ExcelColumn(name = "系统报价")
    private BigDecimal calculatedFreight;
    @ExcelColumn(name = "司机搬运费")
    private BigDecimal driverHandlingCost;
    @ExcelColumn(name = "小工搬运费")
    private BigDecimal laborerHandlingCost;
    @ExcelColumn(name = "小工姓名")
    private String laborerName;
    @ExcelColumn(name = "填写里程")
    private Integer estimateDistance;
    @ExcelColumn(name = "实际里程")
    private Integer actualMileage;
    @ExcelColumn(name = "货物类型")
    private String goodsType;
    @ExcelColumn(name = "是否回单")
    private String needReceiptStr;
    @ExcelColumn(name = "回单状态")
    private String needReceiptStatusStr;
    @ExcelColumn(name = "代收货款")
    private String collectionPayment;
    @ExcelColumn(name = "代收货款金额")
    private BigDecimal collectionPaymentSum;
    @ExcelColumn(name = "运单备注")
    private String remark;
    @ExcelColumn(name = "发单渠道")
    private String waybillSourceStr;
    @ExcelColumn(name = "改价备注")
    private String updateFreightRemark;
    @ExcelColumn(name = "取消原因")
    private String waybillCancelRemark;
    @ExcelColumn(name = "取消渠道")
    private String cancelChannelCnName;
    @ExcelColumn(name = "客户创建人")
    private String customerCreater;

    // 冗余：代收货款付款状态
    private String paymentStatusText;

    // @ExcelColumn(name = "运单配送点")
    // private Integer pointNo;
    // 手填配送点数
    // @ExcelColumn(name = "实际配送点")
    // private Integer distributionPointNo;
    // @ExcelColumn(name = "在仓耗时")
    // private BigDecimal driverLoadingCostDate;
    // @ExcelColumn(name = "装货耗时")
    // private BigDecimal loadingCostDate;
    // @ExcelColumn(name = "配送耗时")
    // private BigDecimal deliveryCostDate;
    // @ExcelColumn(name = "付款方式")
    // private String receiptTypeStr;
    // @ExcelColumn(name = "体积")
    // private String goodsVolume;
    // @ExcelColumn(name = "重量")
    // private String goodsWeight;
    // @ExcelColumn(name = "是否搬运")
    // private String needCarryStr;
    // @ExcelColumn(name = "派车方式")
    // private String receiveWayName;

    public String getCustomerCreater() {
        return customerCreater;
    }

    public void setCustomerCreater(String customerCreater) {
        this.customerCreater = customerCreater;
    }

    public String getPaymentStatusText() {
        return paymentStatusText;
    }

    public void setPaymentStatusText(String paymentStatusText) {
        this.paymentStatusText = paymentStatusText;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getStatusViewText() {
        return statusViewText;
    }

    public void setStatusViewText(String statusViewText) {
        this.statusViewText = statusViewText;
    }

    public String getTruckCustomerName() {
        return truckCustomerName;
    }

    public void setTruckCustomerName(String truckCustomerName) {
        this.truckCustomerName = truckCustomerName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverType() {
        return driverType;
    }

    public void setDriverType(String driverType) {
        this.driverType = driverType;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public BigDecimal getEstimateFreight() {
        return estimateFreight;
    }

    public void setEstimateFreight(BigDecimal estimateFreight) {
        this.estimateFreight = estimateFreight;
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

    public BigDecimal getDriverHandlingCost() {
        return driverHandlingCost;
    }

    public void setDriverHandlingCost(BigDecimal driverHandlingCost) {
        this.driverHandlingCost = driverHandlingCost;
    }

    public BigDecimal getLaborerHandlingCost() {
        return laborerHandlingCost;
    }

    public void setLaborerHandlingCost(BigDecimal laborerHandlingCost) {
        this.laborerHandlingCost = laborerHandlingCost;
    }

    public String getLaborerName() {
        return laborerName;
    }

    public void setLaborerName(String laborerName) {
        this.laborerName = laborerName;
    }

    public BigDecimal getCalculatedFreight() {
        return calculatedFreight;
    }

    public void setCalculatedFreight(BigDecimal calculatedFreight) {
        this.calculatedFreight = calculatedFreight;
    }

    public Integer getEstimateDistance() {
        return estimateDistance;
    }

    public void setEstimateDistance(Integer estimateDistance) {
        this.estimateDistance = estimateDistance;
    }

    public Integer getActualMileage() {
        return actualMileage;
    }

    public void setActualMileage(Integer actualMileage) {
        this.actualMileage = actualMileage;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerManagerName() {
        return customerManagerName;
    }

    public void setCustomerManagerName(String customerManagerName) {
        this.customerManagerName = customerManagerName;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getNeedReceiptStr() {
        return needReceiptStr;
    }

    public void setNeedReceiptStr(String needReceiptStr) {
        this.needReceiptStr = needReceiptStr;
    }

    public String getNeedReceiptStatusStr() {
        return needReceiptStatusStr;
    }

    public void setNeedReceiptStatusStr(String needReceiptStatusStr) {
        this.needReceiptStatusStr = needReceiptStatusStr;
    }

    public String getCollectionPayment() {
        return collectionPayment;
    }

    public void setCollectionPayment(String collectionPayment) {
        this.collectionPayment = collectionPayment;
    }

    public BigDecimal getCollectionPaymentSum() {
        return collectionPaymentSum;
    }

    public void setCollectionPaymentSum(BigDecimal collectionPaymentSum) {
        this.collectionPaymentSum = collectionPaymentSum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWaybillSourceStr() {
        return waybillSourceStr;
    }

    public void setWaybillSourceStr(String waybillSourceStr) {
        this.waybillSourceStr = waybillSourceStr;
    }

    public String getUpdateFreightRemark() {
        return updateFreightRemark;
    }

    public void setUpdateFreightRemark(String updateFreightRemark) {
        this.updateFreightRemark = updateFreightRemark;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPlanDeliveryDate() {
        return planDeliveryDate;
    }

    public void setPlanDeliveryDate(String planDeliveryDate) {
        this.planDeliveryDate = planDeliveryDate;
    }

    public String getWaybillCancelRemark() {
        return waybillCancelRemark;
    }

    public void setWaybillCancelRemark(String waybillCancelRemark) {
        this.waybillCancelRemark = waybillCancelRemark;
    }

    public String getCancelChannelCnName() {
        return cancelChannelCnName;
    }

    public void setCancelChannelCnName(String cancelChannelCnName) {
        this.cancelChannelCnName = cancelChannelCnName;
    }
}
