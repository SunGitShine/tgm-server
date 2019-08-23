package com.juma.tgm.fms.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * reconciliation_item - 对帐名目
 * 
 * @author  2017-10-16
 * @version 1.0 
 */
public class ReconciliationItem implements Serializable {
    
    private static final long serialVersionUID = 4683627391552669635L;
    private Integer reconciliationItemId;
	private Integer reconciliationId;
	private Integer waybillId;
	private String waybillNo;
	private Integer customerId;
	private String customerName;
	private Integer projectId;
	private String projectName;
	private Integer truckId;
	private String plateNumber;
	private String areaCode;
	private BigDecimal taxRateValue;
	private BigDecimal estimateFreight;
	private BigDecimal afterTaxFreight;
	private BigDecimal rebateFee;
	private BigDecimal driverHandlingFee;
	private BigDecimal laborerHandlingFee;
	private BigDecimal show4DriverFreight;
	private String updateFreightRemark;
	
    public Integer getReconciliationItemId() {
        return reconciliationItemId;
    }
    public void setReconciliationItemId(Integer reconciliationItemId) {
        this.reconciliationItemId = reconciliationItemId;
    }
    public Integer getReconciliationId() {
        return reconciliationId;
    }
    public void setReconciliationId(Integer reconciliationId) {
        this.reconciliationId = reconciliationId;
    }
    public Integer getWaybillId() {
        return waybillId;
    }
    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public Integer getProjectId() {
        return projectId;
    }
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
    public BigDecimal getRebateFee() {
        return rebateFee;
    }
    public void setRebateFee(BigDecimal rebateFee) {
        this.rebateFee = rebateFee;
    }
    public BigDecimal getDriverHandlingFee() {
        return driverHandlingFee;
    }
    public void setDriverHandlingFee(BigDecimal driverHandlingFee) {
        this.driverHandlingFee = driverHandlingFee;
    }
    public BigDecimal getLaborerHandlingFee() {
        return laborerHandlingFee;
    }
    public void setLaborerHandlingFee(BigDecimal laborerHandlingFee) {
        this.laborerHandlingFee = laborerHandlingFee;
    }
    public BigDecimal getShow4DriverFreight() {
        return show4DriverFreight;
    }
    public void setShow4DriverFreight(BigDecimal show4DriverFreight) {
        this.show4DriverFreight = show4DriverFreight;
    }
    public String getWaybillNo() {
        return waybillNo;
    }
    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }
    public BigDecimal getTaxRateValue() {
        return taxRateValue;
    }
    public void setTaxRateValue(BigDecimal taxRateValue) {
        this.taxRateValue = taxRateValue;
    }
    public String getUpdateFreightRemark() {
        return updateFreightRemark;
    }
    public void setUpdateFreightRemark(String updateFreightRemark) {
        this.updateFreightRemark = updateFreightRemark;
    }
    public String getAreaCode() {
        return areaCode;
    }
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    public Integer getTruckId() {
        return truckId;
    }
    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }
    public String getPlateNumber() {
        return plateNumber;
    }
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}