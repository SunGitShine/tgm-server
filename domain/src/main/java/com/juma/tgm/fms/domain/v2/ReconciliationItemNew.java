package com.juma.tgm.fms.domain.v2;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReconciliationItemNew implements Serializable {
    private Integer reconciliationItemId;

    private Integer reconciliationId;

    private String areaCode;

    private Integer customerId;

    private String customerName;

    private Integer projectId;

    private String projectName;

    private Integer truckId;

    private String plateNumber;

    private BigDecimal taxRateValue;

    private BigDecimal rebateFee;

    private BigDecimal driverHandlingFee;

    private BigDecimal laborerHandlingFee;

    private BigDecimal hasPayFreight;

    private Byte payStatus;

    private String lesseeName;

    private Integer lesseeId;

    private BigDecimal driverInitialBeforeTax;

    private BigDecimal driverInitialAfterTax;

    private BigDecimal driverFinalBeforeTax;

    private BigDecimal driverFinalAfterTax;

    private Date createTime;

    private Integer createUserId;

    private Date lastUpdateTime;

    private Integer lastUpdateUserId;

    private Integer vendorId;

    private String vendorName;

    private static final long serialVersionUID = 1L;

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

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
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
        this.customerName = customerName == null ? null : customerName.trim();
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
        this.projectName = projectName == null ? null : projectName.trim();
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
        this.plateNumber = plateNumber == null ? null : plateNumber.trim();
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

    public BigDecimal getHasPayFreight() {
        return hasPayFreight;
    }

    public void setHasPayFreight(BigDecimal hasPayFreight) {
        this.hasPayFreight = hasPayFreight;
    }

    public Byte getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    public String getLesseeName() {
        return lesseeName;
    }

    public void setLesseeName(String lesseeName) {
        this.lesseeName = lesseeName == null ? null : lesseeName.trim();
    }

    public Integer getLesseeId() {
        return lesseeId;
    }

    public void setLesseeId(Integer lesseeId) {
        this.lesseeId = lesseeId;
    }

    public BigDecimal getDriverInitialBeforeTax() {
        return driverInitialBeforeTax;
    }

    public void setDriverInitialBeforeTax(BigDecimal driverInitialBeforeTax) {
        this.driverInitialBeforeTax = driverInitialBeforeTax;
    }

    public BigDecimal getDriverInitialAfterTax() {
        return driverInitialAfterTax;
    }

    public void setDriverInitialAfterTax(BigDecimal driverInitialAfterTax) {
        this.driverInitialAfterTax = driverInitialAfterTax;
    }

    public BigDecimal getDriverFinalBeforeTax() {
        return driverFinalBeforeTax;
    }

    public void setDriverFinalBeforeTax(BigDecimal driverFinalBeforeTax) {
        this.driverFinalBeforeTax = driverFinalBeforeTax;
    }

    public BigDecimal getDriverFinalAfterTax() {
        return driverFinalAfterTax;
    }

    public void setDriverFinalAfterTax(BigDecimal driverFinalAfterTax) {
        this.driverFinalAfterTax = driverFinalAfterTax;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(Integer lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName == null ? null : vendorName.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ReconciliationItemNew other = (ReconciliationItemNew) that;
        return (this.getReconciliationItemId() == null ? other.getReconciliationItemId() == null : this.getReconciliationItemId().equals(other.getReconciliationItemId()))
            && (this.getReconciliationId() == null ? other.getReconciliationId() == null : this.getReconciliationId().equals(other.getReconciliationId()))
            && (this.getAreaCode() == null ? other.getAreaCode() == null : this.getAreaCode().equals(other.getAreaCode()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getProjectName() == null ? other.getProjectName() == null : this.getProjectName().equals(other.getProjectName()))
            && (this.getTruckId() == null ? other.getTruckId() == null : this.getTruckId().equals(other.getTruckId()))
            && (this.getPlateNumber() == null ? other.getPlateNumber() == null : this.getPlateNumber().equals(other.getPlateNumber()))
            && (this.getTaxRateValue() == null ? other.getTaxRateValue() == null : this.getTaxRateValue().equals(other.getTaxRateValue()))
            && (this.getRebateFee() == null ? other.getRebateFee() == null : this.getRebateFee().equals(other.getRebateFee()))
            && (this.getDriverHandlingFee() == null ? other.getDriverHandlingFee() == null : this.getDriverHandlingFee().equals(other.getDriverHandlingFee()))
            && (this.getLaborerHandlingFee() == null ? other.getLaborerHandlingFee() == null : this.getLaborerHandlingFee().equals(other.getLaborerHandlingFee()))
            && (this.getHasPayFreight() == null ? other.getHasPayFreight() == null : this.getHasPayFreight().equals(other.getHasPayFreight()))
            && (this.getPayStatus() == null ? other.getPayStatus() == null : this.getPayStatus().equals(other.getPayStatus()))
            && (this.getLesseeName() == null ? other.getLesseeName() == null : this.getLesseeName().equals(other.getLesseeName()))
            && (this.getLesseeId() == null ? other.getLesseeId() == null : this.getLesseeId().equals(other.getLesseeId()))
            && (this.getDriverInitialBeforeTax() == null ? other.getDriverInitialBeforeTax() == null : this.getDriverInitialBeforeTax().equals(other.getDriverInitialBeforeTax()))
            && (this.getDriverInitialAfterTax() == null ? other.getDriverInitialAfterTax() == null : this.getDriverInitialAfterTax().equals(other.getDriverInitialAfterTax()))
            && (this.getDriverFinalBeforeTax() == null ? other.getDriverFinalBeforeTax() == null : this.getDriverFinalBeforeTax().equals(other.getDriverFinalBeforeTax()))
            && (this.getDriverFinalAfterTax() == null ? other.getDriverFinalAfterTax() == null : this.getDriverFinalAfterTax().equals(other.getDriverFinalAfterTax()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getLastUpdateUserId() == null ? other.getLastUpdateUserId() == null : this.getLastUpdateUserId().equals(other.getLastUpdateUserId()))
            && (this.getVendorId() == null ? other.getVendorId() == null : this.getVendorId().equals(other.getVendorId()))
            && (this.getVendorName() == null ? other.getVendorName() == null : this.getVendorName().equals(other.getVendorName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getReconciliationItemId() == null) ? 0 : getReconciliationItemId().hashCode());
        result = prime * result + ((getReconciliationId() == null) ? 0 : getReconciliationId().hashCode());
        result = prime * result + ((getAreaCode() == null) ? 0 : getAreaCode().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getProjectName() == null) ? 0 : getProjectName().hashCode());
        result = prime * result + ((getTruckId() == null) ? 0 : getTruckId().hashCode());
        result = prime * result + ((getPlateNumber() == null) ? 0 : getPlateNumber().hashCode());
        result = prime * result + ((getTaxRateValue() == null) ? 0 : getTaxRateValue().hashCode());
        result = prime * result + ((getRebateFee() == null) ? 0 : getRebateFee().hashCode());
        result = prime * result + ((getDriverHandlingFee() == null) ? 0 : getDriverHandlingFee().hashCode());
        result = prime * result + ((getLaborerHandlingFee() == null) ? 0 : getLaborerHandlingFee().hashCode());
        result = prime * result + ((getHasPayFreight() == null) ? 0 : getHasPayFreight().hashCode());
        result = prime * result + ((getPayStatus() == null) ? 0 : getPayStatus().hashCode());
        result = prime * result + ((getLesseeName() == null) ? 0 : getLesseeName().hashCode());
        result = prime * result + ((getLesseeId() == null) ? 0 : getLesseeId().hashCode());
        result = prime * result + ((getDriverInitialBeforeTax() == null) ? 0 : getDriverInitialBeforeTax().hashCode());
        result = prime * result + ((getDriverInitialAfterTax() == null) ? 0 : getDriverInitialAfterTax().hashCode());
        result = prime * result + ((getDriverFinalBeforeTax() == null) ? 0 : getDriverFinalBeforeTax().hashCode());
        result = prime * result + ((getDriverFinalAfterTax() == null) ? 0 : getDriverFinalAfterTax().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getLastUpdateUserId() == null) ? 0 : getLastUpdateUserId().hashCode());
        result = prime * result + ((getVendorId() == null) ? 0 : getVendorId().hashCode());
        result = prime * result + ((getVendorName() == null) ? 0 : getVendorName().hashCode());
        return result;
    }
}