package com.juma.tgm.fms.domain.v3;

import java.io.Serializable;
import java.util.Date;

public class ReconcilicationForSubPayable implements Serializable {
    private Integer subReconcilicationId;

    private String subReconcilicationNo;

    private Integer reconcilicationId;

    private Integer vendorId;

    private Byte settleStatus;

    private Integer createUserId;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getSubReconcilicationId() {
        return subReconcilicationId;
    }

    public void setSubReconcilicationId(Integer subReconcilicationId) {
        this.subReconcilicationId = subReconcilicationId;
    }

    public String getSubReconcilicationNo() {
        return subReconcilicationNo;
    }

    public void setSubReconcilicationNo(String subReconcilicationNo) {
        this.subReconcilicationNo = subReconcilicationNo == null ? null : subReconcilicationNo.trim();
    }

    public Integer getReconcilicationId() {
        return reconcilicationId;
    }

    public void setReconcilicationId(Integer reconcilicationId) {
        this.reconcilicationId = reconcilicationId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Byte getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(Byte settleStatus) {
        this.settleStatus = settleStatus;
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
        ReconcilicationForSubPayable other = (ReconcilicationForSubPayable) that;
        return (this.getSubReconcilicationId() == null ? other.getSubReconcilicationId() == null : this.getSubReconcilicationId().equals(other.getSubReconcilicationId()))
            && (this.getSubReconcilicationNo() == null ? other.getSubReconcilicationNo() == null : this.getSubReconcilicationNo().equals(other.getSubReconcilicationNo()))
            && (this.getReconcilicationId() == null ? other.getReconcilicationId() == null : this.getReconcilicationId().equals(other.getReconcilicationId()))
            && (this.getVendorId() == null ? other.getVendorId() == null : this.getVendorId().equals(other.getVendorId()))
            && (this.getSettleStatus() == null ? other.getSettleStatus() == null : this.getSettleStatus().equals(other.getSettleStatus()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSubReconcilicationId() == null) ? 0 : getSubReconcilicationId().hashCode());
        result = prime * result + ((getSubReconcilicationNo() == null) ? 0 : getSubReconcilicationNo().hashCode());
        result = prime * result + ((getReconcilicationId() == null) ? 0 : getReconcilicationId().hashCode());
        result = prime * result + ((getVendorId() == null) ? 0 : getVendorId().hashCode());
        result = prime * result + ((getSettleStatus() == null) ? 0 : getSettleStatus().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}