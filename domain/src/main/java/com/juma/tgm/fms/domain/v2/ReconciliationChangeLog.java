package com.juma.tgm.fms.domain.v2;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReconciliationChangeLog implements Serializable {
    private Integer reconciliationChangeLogId;

    private Integer reconciliationId;

    private Integer type;

    private String driverName;

    private String plateNumber;

    private BigDecimal beforeTaxFreight;

    private BigDecimal taxRate;

    private BigDecimal afterTaxFreight;

    private String userName;

    private Integer customerId;

    private String customerName;

    private String note;

    private Date vehicleUseTime;

    private Integer createUserId;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getReconciliationChangeLogId() {
        return reconciliationChangeLogId;
    }

    public void setReconciliationChangeLogId(Integer reconciliationChangeLogId) {
        this.reconciliationChangeLogId = reconciliationChangeLogId;
    }

    public Integer getReconciliationId() {
        return reconciliationId;
    }

    public void setReconciliationId(Integer reconciliationId) {
        this.reconciliationId = reconciliationId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber == null ? null : plateNumber.trim();
    }

    public BigDecimal getBeforeTaxFreight() {
        return beforeTaxFreight;
    }

    public void setBeforeTaxFreight(BigDecimal beforeTaxFreight) {
        this.beforeTaxFreight = beforeTaxFreight;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getAfterTaxFreight() {
        return afterTaxFreight;
    }

    public void setAfterTaxFreight(BigDecimal afterTaxFreight) {
        this.afterTaxFreight = afterTaxFreight;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Date getVehicleUseTime() {
        return vehicleUseTime;
    }

    public void setVehicleUseTime(Date vehicleUseTime) {
        this.vehicleUseTime = vehicleUseTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        ReconciliationChangeLog other = (ReconciliationChangeLog) that;
        return (this.getReconciliationChangeLogId() == null ? other.getReconciliationChangeLogId() == null : this.getReconciliationChangeLogId().equals(other.getReconciliationChangeLogId()))
            && (this.getReconciliationId() == null ? other.getReconciliationId() == null : this.getReconciliationId().equals(other.getReconciliationId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getDriverName() == null ? other.getDriverName() == null : this.getDriverName().equals(other.getDriverName()))
            && (this.getPlateNumber() == null ? other.getPlateNumber() == null : this.getPlateNumber().equals(other.getPlateNumber()))
            && (this.getBeforeTaxFreight() == null ? other.getBeforeTaxFreight() == null : this.getBeforeTaxFreight().equals(other.getBeforeTaxFreight()))
            && (this.getTaxRate() == null ? other.getTaxRate() == null : this.getTaxRate().equals(other.getTaxRate()))
            && (this.getAfterTaxFreight() == null ? other.getAfterTaxFreight() == null : this.getAfterTaxFreight().equals(other.getAfterTaxFreight()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getVehicleUseTime() == null ? other.getVehicleUseTime() == null : this.getVehicleUseTime().equals(other.getVehicleUseTime()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getReconciliationChangeLogId() == null) ? 0 : getReconciliationChangeLogId().hashCode());
        result = prime * result + ((getReconciliationId() == null) ? 0 : getReconciliationId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getDriverName() == null) ? 0 : getDriverName().hashCode());
        result = prime * result + ((getPlateNumber() == null) ? 0 : getPlateNumber().hashCode());
        result = prime * result + ((getBeforeTaxFreight() == null) ? 0 : getBeforeTaxFreight().hashCode());
        result = prime * result + ((getTaxRate() == null) ? 0 : getTaxRate().hashCode());
        result = prime * result + ((getAfterTaxFreight() == null) ? 0 : getAfterTaxFreight().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getVehicleUseTime() == null) ? 0 : getVehicleUseTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}