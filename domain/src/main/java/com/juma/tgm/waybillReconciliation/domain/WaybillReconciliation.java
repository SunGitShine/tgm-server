package com.juma.tgm.waybillReconciliation.domain;

import me.about.poi.ExcelColumn;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName WaybillReconciliationVO.java
 * @Description 运单对账
 * @author Libin.Wei
 * @Date 2017年7月26日 上午9:58:50
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class WaybillReconciliation implements Serializable {

    private static final long serialVersionUID = -3324603378267845485L;
    @ExcelColumn(name = "运单号")
    private String waybillNo;
    // 用车时间
    @ExcelColumn(name = "用车时间" ,width = 30)
    private Date planDeliveryTime;

    // 司机
    @ExcelColumn(name = "司机/承运商")
    private String driverName;

    @ExcelColumn(name = "车牌号")
    private String plateNumber;

    /**
     * 承运商名称
     */
    private String vendorName;

    private Integer waybillId;

    private String reconciliationNo;

    @ExcelColumn(name = "含税金额")
    private BigDecimal estimateFreight = BigDecimal.ZERO;

    // 税后费用
    private BigDecimal afterTaxFreight = BigDecimal.ZERO;

    @ExcelColumn(name = "结算价")
    private BigDecimal show4DriverFreight = BigDecimal.ZERO;

    @ExcelColumn(name = "司机搬运费")
    private BigDecimal driverHandlingCost = BigDecimal.ZERO;

    @ExcelColumn(name = "小工搬运费")
    private BigDecimal laborerHandlingCost = BigDecimal.ZERO;

    @ExcelColumn(name = "备注")
    private String updateFreightRemark;

    @ExcelColumn(name = "税率")
    private BigDecimal taxRateValue;

    // 客户经理姓名
    private String customerManagerName;
    // 所属项目
    private String projectName;
    // 所属客户
    private String customerName;

    // 返点费
    private BigDecimal rebateFee = BigDecimal.ZERO;
    // 修改运单价格审核状态
    private Integer updateFreightAuditStatus;
    // 能否修改路线  true：可以
    private boolean canOperate = true;
    // 是否通知司机  true：通知
    @ExcelColumn(name = "是否通知司机")
    private String noticeToDriver = "否";
    // 是否通知发单人  true：通知
    @ExcelColumn(name = "是否通知发单人")
    private String noticeToWaybillOwner = "否";


    @ExcelColumn(name = "结算对象")
    private String settlementObject;

    private Integer projectId;
    private Integer customerId;
    private Integer driverId;
    private Integer customerManagerId;

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getCustomerManagerId() {
        return customerManagerId;
    }

    public void setCustomerManagerId(Integer customerManagerId) {
        this.customerManagerId = customerManagerId;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
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

    public String getUpdateFreightRemark() {
        return updateFreightRemark;
    }

    public void setUpdateFreightRemark(String updateFreightRemark) {
        this.updateFreightRemark = updateFreightRemark;
    }

    public Date getPlanDeliveryTime() {
        return planDeliveryTime;
    }

    public void setPlanDeliveryTime(Date planDeliveryTime) {
        this.planDeliveryTime = planDeliveryTime;
    }

    public String getCustomerManagerName() {
        return customerManagerName;
    }

    public void setCustomerManagerName(String customerManagerName) {
        this.customerManagerName = customerManagerName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDriverName() {
        return this.driverName ;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public BigDecimal getTaxRateValue() {
        return taxRateValue;
    }

    public void setTaxRateValue(BigDecimal taxRateValue) {
        this.taxRateValue = taxRateValue;
    }

    public BigDecimal getRebateFee() {
        return rebateFee;
    }

    public void setRebateFee(BigDecimal rebateFee) {
        this.rebateFee = rebateFee;
    }

    public Integer getUpdateFreightAuditStatus() {
        return updateFreightAuditStatus;
    }

    public void setUpdateFreightAuditStatus(Integer updateFreightAuditStatus) {
        this.updateFreightAuditStatus = updateFreightAuditStatus;
    }

    public boolean isCanOperate() {
        return canOperate;
    }

    public void setCanOperate(boolean canOperate) {
        this.canOperate = canOperate;
    }

    public String getNoticeToDriver() {
        return noticeToDriver;
    }

    public void setNoticeToDriver(String noticeToDriver) {
        this.noticeToDriver = noticeToDriver;
    }

    public String getNoticeToWaybillOwner() {
        return noticeToWaybillOwner;
    }

    public void setNoticeToWaybillOwner(String noticeToWaybillOwner) {
        this.noticeToWaybillOwner = noticeToWaybillOwner;
    }

    public String getReconciliationNo() {
        return reconciliationNo;
    }

    public void setReconciliationNo(String reconciliationNo) {
        this.reconciliationNo = reconciliationNo;
    }


    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }


    public String getSettlementObject() {
        return settlementObject;
    }

    public void setSettlementObject(String settlementObject) {
        this.settlementObject = settlementObject;
    }
}
