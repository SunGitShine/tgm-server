package com.juma.tgm.fms.domain.v3.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel("客户侧对账单运单明细")
public class ReconcilicationForReceivableItemVo implements Serializable {
    private Integer reconcilicationItemId;

    private Integer reconcilicationId;

    private Integer waybillId;

    private String waybillNo;

    private Date planDeliveryTime;

    private BigDecimal receivableWithTax;

    private BigDecimal receivableWithoutTax;

    private BigDecimal taxRate;

    private BigDecimal rebateFee;

    private BigDecimal payableWithTax;

    private Integer settleStatus;

    private Integer payableReconcilicationStatus;

    private String adjustRemark;

    private String driverName;

    private Integer driverType;

    private String plateNumber;

    private String vendorName;
    @ApiModelProperty(value = "客户侧调整金额")
    private BigDecimal adjustForFreight;
    @ApiModelProperty(value = "客户侧运单最终的调整后金额")
    private BigDecimal finalFreightForCustomer;


    private static final long serialVersionUID = 1L;

    public BigDecimal getPayableWithTax() {
        return payableWithTax;
    }

    public void setPayableWithTax(BigDecimal payableWithTax) {
        this.payableWithTax = payableWithTax;
    }

    public Integer getReconcilicationItemId() {
        return reconcilicationItemId;
    }

    public void setReconcilicationItemId(Integer reconcilicationItemId) {
        this.reconcilicationItemId = reconcilicationItemId;
    }

    public Integer getReconcilicationId() {
        return reconcilicationId;
    }

    public void setReconcilicationId(Integer reconcilicationId) {
        this.reconcilicationId = reconcilicationId;
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
        this.waybillNo = waybillNo == null ? null : waybillNo.trim();
    }

    public Date getPlanDeliveryTime() {
        return planDeliveryTime;
    }

    public void setPlanDeliveryTime(Date planDeliveryTime) {
        this.planDeliveryTime = planDeliveryTime;
    }

    public BigDecimal getReceivableWithTax() {
        return receivableWithTax;
    }

    public void setReceivableWithTax(BigDecimal receivableWithTax) {
        this.receivableWithTax = receivableWithTax;
    }

    public BigDecimal getReceivableWithoutTax() {
        return receivableWithoutTax;
    }

    public void setReceivableWithoutTax(BigDecimal receivableWithoutTax) {
        this.receivableWithoutTax = receivableWithoutTax;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getRebateFee() {
        return rebateFee;
    }

    public void setRebateFee(BigDecimal rebateFee) {
        this.rebateFee = rebateFee;
    }

    public Integer getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(Integer settleStatus) {
        this.settleStatus = settleStatus;
    }

    public Integer getPayableReconcilicationStatus() {
        return payableReconcilicationStatus;
    }

    public void setPayableReconcilicationStatus(Integer payableReconcilicationStatus) {
        this.payableReconcilicationStatus = payableReconcilicationStatus;
    }

    public String getAdjustRemark() {
        return adjustRemark;
    }

    public void setAdjustRemark(String adjustRemark) {
        this.adjustRemark = adjustRemark;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Integer getDriverType() {
        return driverType;
    }

    public void setDriverType(Integer driverType) {
        this.driverType = driverType;
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

    public BigDecimal getAdjustForFreight() {
        return adjustForFreight;
    }

    public void setAdjustForFreight(BigDecimal adjustForFreight) {
        this.adjustForFreight = adjustForFreight;
    }

    public BigDecimal getFinalFreightForCustomer() {
        return finalFreightForCustomer;
    }

    public void setFinalFreightForCustomer(BigDecimal finalFreightForCustomer) {
        this.finalFreightForCustomer = finalFreightForCustomer;
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
        ReconcilicationForReceivableItemVo other = (ReconcilicationForReceivableItemVo) that;
        return (this.getReconcilicationItemId() == null ? other.getReconcilicationItemId() == null : this.getReconcilicationItemId().equals(other.getReconcilicationItemId()))
            && (this.getReconcilicationId() == null ? other.getReconcilicationId() == null : this.getReconcilicationId().equals(other.getReconcilicationId()))
            && (this.getWaybillId() == null ? other.getWaybillId() == null : this.getWaybillId().equals(other.getWaybillId()))
            && (this.getWaybillNo() == null ? other.getWaybillNo() == null : this.getWaybillNo().equals(other.getWaybillNo()))
            && (this.getPlanDeliveryTime() == null ? other.getPlanDeliveryTime() == null : this.getPlanDeliveryTime().equals(other.getPlanDeliveryTime()))
            && (this.getReceivableWithTax() == null ? other.getReceivableWithTax() == null : this.getReceivableWithTax().equals(other.getReceivableWithTax()))
            && (this.getReceivableWithoutTax() == null ? other.getReceivableWithoutTax() == null : this.getReceivableWithoutTax().equals(other.getReceivableWithoutTax()))
            && (this.getTaxRate() == null ? other.getTaxRate() == null : this.getTaxRate().equals(other.getTaxRate()))
            && (this.getRebateFee() == null ? other.getRebateFee() == null : this.getRebateFee().equals(other.getRebateFee()))
            && (this.getSettleStatus() == null ? other.getSettleStatus() == null : this.getSettleStatus().equals(other.getSettleStatus()))
            && (this.getPayableReconcilicationStatus() == null ? other.getPayableReconcilicationStatus() == null : this.getPayableReconcilicationStatus().equals(other.getPayableReconcilicationStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getReconcilicationItemId() == null) ? 0 : getReconcilicationItemId().hashCode());
        result = prime * result + ((getReconcilicationId() == null) ? 0 : getReconcilicationId().hashCode());
        result = prime * result + ((getWaybillId() == null) ? 0 : getWaybillId().hashCode());
        result = prime * result + ((getWaybillNo() == null) ? 0 : getWaybillNo().hashCode());
        result = prime * result + ((getPlanDeliveryTime() == null) ? 0 : getPlanDeliveryTime().hashCode());
        result = prime * result + ((getReceivableWithTax() == null) ? 0 : getReceivableWithTax().hashCode());
        result = prime * result + ((getReceivableWithoutTax() == null) ? 0 : getReceivableWithoutTax().hashCode());
        result = prime * result + ((getTaxRate() == null) ? 0 : getTaxRate().hashCode());
        result = prime * result + ((getRebateFee() == null) ? 0 : getRebateFee().hashCode());
        result = prime * result + ((getSettleStatus() == null) ? 0 : getSettleStatus().hashCode());
        result = prime * result + ((getPayableReconcilicationStatus() == null) ? 0 : getPayableReconcilicationStatus().hashCode());
        return result;
    }
}