package com.juma.tgm.fms.domain.v3;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReconcilicationForPayableItem implements Serializable {
    private Integer reconcilicationItemId;

    private Integer reconcilicationId;

    private Integer settleType;

    private Integer settleAccountId;

    private String settleAccountName;

    private Integer waybillId;

    private String waybillNo;

    private String plateNumber;

    private String vehicleFrameNo;

    private BigDecimal settleFreight;

    private BigDecimal taxRate;

    private BigDecimal rebateFee;

    private BigDecimal driverTransportFee;

    private BigDecimal temporaryTransportFee;

    private Integer settleStatus;

    private BigDecimal receivableWithTax;

    private static final long serialVersionUID = 1L;

    public BigDecimal getReceivableWithTax() {
        return receivableWithTax;
    }

    public void setReceivableWithTax(BigDecimal receivableWithTax) {
        this.receivableWithTax = receivableWithTax;
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

    public Integer getSettleType() {
        return settleType;
    }

    public void setSettleType(Integer settleType) {
        this.settleType = settleType;
    }

    public Integer getSettleAccountId() {
        return settleAccountId;
    }

    public void setSettleAccountId(Integer settleAccountId) {
        this.settleAccountId = settleAccountId;
    }

    public String getSettleAccountName() {
        return settleAccountName;
    }

    public void setSettleAccountName(String settleAccountName) {
        this.settleAccountName = settleAccountName == null ? null : settleAccountName.trim();
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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber == null ? null : plateNumber.trim();
    }

    public String getVehicleFrameNo() {
        return vehicleFrameNo;
    }

    public void setVehicleFrameNo(String vehicleFrameNo) {
        this.vehicleFrameNo = vehicleFrameNo == null ? null : vehicleFrameNo.trim();
    }

    public BigDecimal getSettleFreight() {
        return settleFreight;
    }

    public void setSettleFreight(BigDecimal settleFreight) {
        this.settleFreight = settleFreight;
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

    public BigDecimal getDriverTransportFee() {
        return driverTransportFee;
    }

    public void setDriverTransportFee(BigDecimal driverTransportFee) {
        this.driverTransportFee = driverTransportFee;
    }

    public BigDecimal getTemporaryTransportFee() {
        return temporaryTransportFee;
    }

    public void setTemporaryTransportFee(BigDecimal temporaryTransportFee) {
        this.temporaryTransportFee = temporaryTransportFee;
    }

    public Integer getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(Integer settleStatus) {
        this.settleStatus = settleStatus;
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
        ReconcilicationForPayableItem other = (ReconcilicationForPayableItem) that;
        return (this.getReconcilicationItemId() == null ? other.getReconcilicationItemId() == null : this.getReconcilicationItemId().equals(other.getReconcilicationItemId()))
            && (this.getReconcilicationId() == null ? other.getReconcilicationId() == null : this.getReconcilicationId().equals(other.getReconcilicationId()))
            && (this.getSettleType() == null ? other.getSettleType() == null : this.getSettleType().equals(other.getSettleType()))
            && (this.getSettleAccountId() == null ? other.getSettleAccountId() == null : this.getSettleAccountId().equals(other.getSettleAccountId()))
            && (this.getSettleAccountName() == null ? other.getSettleAccountName() == null : this.getSettleAccountName().equals(other.getSettleAccountName()))
            && (this.getWaybillId() == null ? other.getWaybillId() == null : this.getWaybillId().equals(other.getWaybillId()))
            && (this.getWaybillNo() == null ? other.getWaybillNo() == null : this.getWaybillNo().equals(other.getWaybillNo()))
            && (this.getPlateNumber() == null ? other.getPlateNumber() == null : this.getPlateNumber().equals(other.getPlateNumber()))
            && (this.getVehicleFrameNo() == null ? other.getVehicleFrameNo() == null : this.getVehicleFrameNo().equals(other.getVehicleFrameNo()))
            && (this.getSettleFreight() == null ? other.getSettleFreight() == null : this.getSettleFreight().equals(other.getSettleFreight()))
            && (this.getTaxRate() == null ? other.getTaxRate() == null : this.getTaxRate().equals(other.getTaxRate()))
            && (this.getRebateFee() == null ? other.getRebateFee() == null : this.getRebateFee().equals(other.getRebateFee()))
            && (this.getDriverTransportFee() == null ? other.getDriverTransportFee() == null : this.getDriverTransportFee().equals(other.getDriverTransportFee()))
            && (this.getTemporaryTransportFee() == null ? other.getTemporaryTransportFee() == null : this.getTemporaryTransportFee().equals(other.getTemporaryTransportFee()))
            && (this.getSettleStatus() == null ? other.getSettleStatus() == null : this.getSettleStatus().equals(other.getSettleStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getReconcilicationItemId() == null) ? 0 : getReconcilicationItemId().hashCode());
        result = prime * result + ((getReconcilicationId() == null) ? 0 : getReconcilicationId().hashCode());
        result = prime * result + ((getSettleType() == null) ? 0 : getSettleType().hashCode());
        result = prime * result + ((getSettleAccountId() == null) ? 0 : getSettleAccountId().hashCode());
        result = prime * result + ((getSettleAccountName() == null) ? 0 : getSettleAccountName().hashCode());
        result = prime * result + ((getWaybillId() == null) ? 0 : getWaybillId().hashCode());
        result = prime * result + ((getWaybillNo() == null) ? 0 : getWaybillNo().hashCode());
        result = prime * result + ((getPlateNumber() == null) ? 0 : getPlateNumber().hashCode());
        result = prime * result + ((getVehicleFrameNo() == null) ? 0 : getVehicleFrameNo().hashCode());
        result = prime * result + ((getSettleFreight() == null) ? 0 : getSettleFreight().hashCode());
        result = prime * result + ((getTaxRate() == null) ? 0 : getTaxRate().hashCode());
        result = prime * result + ((getRebateFee() == null) ? 0 : getRebateFee().hashCode());
        result = prime * result + ((getDriverTransportFee() == null) ? 0 : getDriverTransportFee().hashCode());
        result = prime * result + ((getTemporaryTransportFee() == null) ? 0 : getTemporaryTransportFee().hashCode());
        result = prime * result + ((getSettleStatus() == null) ? 0 : getSettleStatus().hashCode());
        return result;
    }
}