package com.juma.tgm.fms.domain.v2.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReconciliationChangeLogByCarVo implements Serializable{
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

    private Integer createUserId;

    private Date createTime;

    private Date vehicleUseTime;


    /**
     * 承运商 id
     *
     *
     * */
    private Integer vendorId;

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
        this.driverName = driverName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
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
        this.userName = userName;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public Date getVehicleUseTime() {
        return vehicleUseTime;
    }

    public void setVehicleUseTime(Date vehicleUseTime) {
        this.vehicleUseTime = vehicleUseTime;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }
}
