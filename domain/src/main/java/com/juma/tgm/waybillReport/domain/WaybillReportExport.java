package com.juma.tgm.waybillReport.domain;

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

public class WaybillReportExport implements Serializable {

    private static final long serialVersionUID = 1875721966331556264L;
    @ExcelColumn(name = "运单号")
    private String waybillNo;
    @ExcelColumn(name = "业务范围")
    private String areaCodeName;
    // @ExcelColumn(name = "项目名称")
    private String projectName;
    @ExcelColumn(name = "客户名称")
    private String customerName;
    // @ExcelColumn(name = "用车人")
    // private String truckCustomerName;
    @ExcelColumn(name = "客户经理")
    private String customerManagerName;
    private String customerManagerPhone;
    @ExcelColumn(name = "用车时间")
    private String planDeliveryDate;
    @ExcelColumn(name = "司机姓名")
    private String driverName;
    @ExcelColumn(name = "司机类型")
    private String driverType;
    /** 司机电话 */
    private String driverMoble;
    // @ExcelColumn(name = "车型")
    // private String truckTypeName;
    @ExcelColumn(name = "车牌号")
    private String plateNumber;
    @ExcelColumn(name = "系统报价")
    private BigDecimal calculatedFreight;
    // @ExcelColumn(name = "支付方式")
    // private String receiptTypeStr;
    @ExcelColumn(name = "税前费用")
    private BigDecimal estimateFreight;
    @ExcelColumn(name = "税后费用")
    private BigDecimal afterTaxFreight;
    @ExcelColumn(name = "返点费")
    private BigDecimal rebateFee;
    @ExcelColumn(name = "司机搬运费")
    private BigDecimal driverHandlingCost;
    @ExcelColumn(name = "小工搬运费")
    private BigDecimal laborerHandlingCost;
    @ExcelColumn(name = "司机结算价")
    private BigDecimal show4DriverFreight;
    @ExcelColumn(name = "结算金额")
    private BigDecimal checkOutFreight;
    @ExcelColumn(name = "收款账户")
    private String receivableAccount;
    @ExcelColumn(name = "收款时间")
    private String receivableTime;
    @ExcelColumn(name = "备注")
    private String receivableRemark;
    @ExcelColumn(name = "结算状态")
    private String checkoutStr;

    /** 运单ID */
    private Integer waybillId;
    /** 付款方式 */
    private Integer receiptType;
    /** 业务范围 */
    private String areaCode;
    /** 运单结算对账状态 */
    private Integer reconciliationStatus;
    /** 用车人ID */
    private Integer truckCustomerId;
    /** 大客户ID（tms） */
    private Integer customerId;
    private Integer driverId;
    private Integer projectId;
    private Integer customerManagerId;
    /** 车辆ID */
    private Integer truckId;
    private Integer estimateDistance;
    private String driverPhone;
    private String truckCustomerPhone;

    /** 导入ERP结果 */
    private String erpResult;
    // 能否修改路线 true：可以
    private boolean canOperate = true;

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getCustomerManagerId() {
        return customerManagerId;
    }

    public void setCustomerManagerId(Integer customerManagerId) {
        this.customerManagerId = customerManagerId;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getAreaCodeName() {
        return areaCodeName;
    }

    public void setAreaCodeName(String areaCodeName) {
        this.areaCodeName = areaCodeName;
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

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public BigDecimal getCalculatedFreight() {
        return calculatedFreight;
    }

    public void setCalculatedFreight(BigDecimal calculatedFreight) {
        this.calculatedFreight = calculatedFreight;
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

    public BigDecimal getCheckOutFreight() {
        return checkOutFreight;
    }

    public void setCheckOutFreight(BigDecimal checkOutFreight) {
        this.checkOutFreight = checkOutFreight;
    }

    public String getReceivableAccount() {
        return receivableAccount;
    }

    public void setReceivableAccount(String receivableAccount) {
        this.receivableAccount = receivableAccount;
    }

    public String getReceivableTime() {
        return receivableTime;
    }

    public void setReceivableTime(String receivableTime) {
        this.receivableTime = receivableTime;
    }

    public String getReceivableRemark() {
        return receivableRemark;
    }

    public void setReceivableRemark(String receivableRemark) {
        this.receivableRemark = receivableRemark;
    }

    public String getCheckoutStr() {
        return checkoutStr;
    }

    public void setCheckoutStr(String checkoutStr) {
        this.checkoutStr = checkoutStr;
    }

    public Integer getEstimateDistance() {
        return estimateDistance;
    }

    public void setEstimateDistance(Integer estimateDistance) {
        this.estimateDistance = estimateDistance;
    }

    public String getDriverType() {
        return driverType;
    }

    public void setDriverType(String driverType) {
        this.driverType = driverType;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getTruckCustomerPhone() {
        return truckCustomerPhone;
    }

    public void setTruckCustomerPhone(String truckCustomerPhone) {
        this.truckCustomerPhone = truckCustomerPhone;
    }

    public BigDecimal getRebateFee() {
        return rebateFee;
    }

    public void setRebateFee(BigDecimal rebateFee) {
        this.rebateFee = rebateFee;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCustomerManagerPhone() {
        return customerManagerPhone;
    }

    public void setCustomerManagerPhone(String customerManagerPhone) {
        this.customerManagerPhone = customerManagerPhone;
    }

    public String getPlanDeliveryDate() {
        return planDeliveryDate;
    }

    public void setPlanDeliveryDate(String planDeliveryDate) {
        this.planDeliveryDate = planDeliveryDate;
    }

    public String getDriverMoble() {
        return driverMoble;
    }

    public void setDriverMoble(String driverMoble) {
        this.driverMoble = driverMoble;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public Integer getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(Integer receiptType) {
        this.receiptType = receiptType;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getReconciliationStatus() {
        return reconciliationStatus;
    }

    public void setReconciliationStatus(Integer reconciliationStatus) {
        this.reconciliationStatus = reconciliationStatus;
    }

    public Integer getTruckCustomerId() {
        return truckCustomerId;
    }

    public void setTruckCustomerId(Integer truckCustomerId) {
        this.truckCustomerId = truckCustomerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public String getErpResult() {
        return erpResult;
    }

    public void setErpResult(String erpResult) {
        this.erpResult = erpResult;
    }

    public boolean isCanOperate() {
        return canOperate;
    }

    public void setCanOperate(boolean canOperate) {
        this.canOperate = canOperate;
    }

}
