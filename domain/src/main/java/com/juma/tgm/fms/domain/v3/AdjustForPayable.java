package com.juma.tgm.fms.domain.v3;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AdjustForPayable implements Serializable {
    private Integer adjustId;

    private Integer waybillId;

    private String waybillNo;

    private BigDecimal payableWithTax;

    private BigDecimal payableWithTaxAdjust;

    private BigDecimal driverTransportFee;

    private BigDecimal driverTransportFeeAdjust;

    private BigDecimal temporaryTransportFee;

    private BigDecimal temporaryTransportFeeAdjust;

    private String adjustRemark;

    private Integer adjustUserId;

    private Date adjustTime;

    private static final long serialVersionUID = 1L;

    public Integer getAdjustId() {
        return adjustId;
    }

    public void setAdjustId(Integer adjustId) {
        this.adjustId = adjustId;
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

    public BigDecimal getPayableWithTax() {
        return payableWithTax;
    }

    public void setPayableWithTax(BigDecimal payableWithTax) {
        this.payableWithTax = payableWithTax;
    }

    public BigDecimal getPayableWithTaxAdjust() {
        return payableWithTaxAdjust;
    }

    public void setPayableWithTaxAdjust(BigDecimal payableWithTaxAdjust) {
        this.payableWithTaxAdjust = payableWithTaxAdjust;
    }

    public BigDecimal getDriverTransportFee() {
        return driverTransportFee;
    }

    public void setDriverTransportFee(BigDecimal driverTransportFee) {
        this.driverTransportFee = driverTransportFee;
    }

    public BigDecimal getDriverTransportFeeAdjust() {
        return driverTransportFeeAdjust;
    }

    public void setDriverTransportFeeAdjust(BigDecimal driverTransportFeeAdjust) {
        this.driverTransportFeeAdjust = driverTransportFeeAdjust;
    }

    public BigDecimal getTemporaryTransportFee() {
        return temporaryTransportFee;
    }

    public void setTemporaryTransportFee(BigDecimal temporaryTransportFee) {
        this.temporaryTransportFee = temporaryTransportFee;
    }

    public BigDecimal getTemporaryTransportFeeAdjust() {
        return temporaryTransportFeeAdjust;
    }

    public void setTemporaryTransportFeeAdjust(BigDecimal temporaryTransportFeeAdjust) {
        this.temporaryTransportFeeAdjust = temporaryTransportFeeAdjust;
    }

    public String getAdjustRemark() {
        return adjustRemark;
    }

    public void setAdjustRemark(String adjustRemark) {
        this.adjustRemark = adjustRemark == null ? null : adjustRemark.trim();
    }

    public Integer getAdjustUserId() {
        return adjustUserId;
    }

    public void setAdjustUserId(Integer adjustUserId) {
        this.adjustUserId = adjustUserId;
    }

    public Date getAdjustTime() {
        return adjustTime;
    }

    public void setAdjustTime(Date adjustTime) {
        this.adjustTime = adjustTime;
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
        AdjustForPayable other = (AdjustForPayable) that;
        return (this.getAdjustId() == null ? other.getAdjustId() == null : this.getAdjustId().equals(other.getAdjustId()))
            && (this.getWaybillId() == null ? other.getWaybillId() == null : this.getWaybillId().equals(other.getWaybillId()))
            && (this.getWaybillNo() == null ? other.getWaybillNo() == null : this.getWaybillNo().equals(other.getWaybillNo()))
            && (this.getPayableWithTax() == null ? other.getPayableWithTax() == null : this.getPayableWithTax().equals(other.getPayableWithTax()))
            && (this.getPayableWithTaxAdjust() == null ? other.getPayableWithTaxAdjust() == null : this.getPayableWithTaxAdjust().equals(other.getPayableWithTaxAdjust()))
            && (this.getDriverTransportFee() == null ? other.getDriverTransportFee() == null : this.getDriverTransportFee().equals(other.getDriverTransportFee()))
            && (this.getDriverTransportFeeAdjust() == null ? other.getDriverTransportFeeAdjust() == null : this.getDriverTransportFeeAdjust().equals(other.getDriverTransportFeeAdjust()))
            && (this.getTemporaryTransportFee() == null ? other.getTemporaryTransportFee() == null : this.getTemporaryTransportFee().equals(other.getTemporaryTransportFee()))
            && (this.getTemporaryTransportFeeAdjust() == null ? other.getTemporaryTransportFeeAdjust() == null : this.getTemporaryTransportFeeAdjust().equals(other.getTemporaryTransportFeeAdjust()))
            && (this.getAdjustRemark() == null ? other.getAdjustRemark() == null : this.getAdjustRemark().equals(other.getAdjustRemark()))
            && (this.getAdjustUserId() == null ? other.getAdjustUserId() == null : this.getAdjustUserId().equals(other.getAdjustUserId()))
            && (this.getAdjustTime() == null ? other.getAdjustTime() == null : this.getAdjustTime().equals(other.getAdjustTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAdjustId() == null) ? 0 : getAdjustId().hashCode());
        result = prime * result + ((getWaybillId() == null) ? 0 : getWaybillId().hashCode());
        result = prime * result + ((getWaybillNo() == null) ? 0 : getWaybillNo().hashCode());
        result = prime * result + ((getPayableWithTax() == null) ? 0 : getPayableWithTax().hashCode());
        result = prime * result + ((getPayableWithTaxAdjust() == null) ? 0 : getPayableWithTaxAdjust().hashCode());
        result = prime * result + ((getDriverTransportFee() == null) ? 0 : getDriverTransportFee().hashCode());
        result = prime * result + ((getDriverTransportFeeAdjust() == null) ? 0 : getDriverTransportFeeAdjust().hashCode());
        result = prime * result + ((getTemporaryTransportFee() == null) ? 0 : getTemporaryTransportFee().hashCode());
        result = prime * result + ((getTemporaryTransportFeeAdjust() == null) ? 0 : getTemporaryTransportFeeAdjust().hashCode());
        result = prime * result + ((getAdjustRemark() == null) ? 0 : getAdjustRemark().hashCode());
        result = prime * result + ((getAdjustUserId() == null) ? 0 : getAdjustUserId().hashCode());
        result = prime * result + ((getAdjustTime() == null) ? 0 : getAdjustTime().hashCode());
        return result;
    }
}