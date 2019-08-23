package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TaxRate implements Serializable {
    private Integer taxRateId;

    private String taxRateName;

    private BigDecimal taxRateValue;

    private String taxRateValueText;

    private Integer tenantId;

    private String tenantCode;

    private Date createTime;

    private Integer createUserId;

    private Boolean isDelete;

    private Date lastUpdateTime;

    private Integer lastUpdateUserId;

    private static final long serialVersionUID = 1L;

    public Integer getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Integer taxRateId) {
        this.taxRateId = taxRateId;
    }

    public String getTaxRateName() {
        return taxRateName;
    }

    public void setTaxRateName(String taxRateName) {
        this.taxRateName = taxRateName == null ? null : taxRateName.trim();
    }

    public BigDecimal getTaxRateValue() {
        return taxRateValue;
    }

    public void setTaxRateValue(BigDecimal taxRateValue) {
        this.taxRateValue = taxRateValue;
    }

    public String getTaxRateValueText() {
        return taxRateValueText;
    }

    public void setTaxRateValueText(String taxRateValueText) {
        this.taxRateValueText = taxRateValueText == null ? null : taxRateValueText.trim();
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode == null ? null : tenantCode.trim();
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

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
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
        TaxRate other = (TaxRate) that;
        return (this.getTaxRateId() == null ? other.getTaxRateId() == null : this.getTaxRateId().equals(other.getTaxRateId()))
            && (this.getTaxRateName() == null ? other.getTaxRateName() == null : this.getTaxRateName().equals(other.getTaxRateName()))
            && (this.getTaxRateValue() == null ? other.getTaxRateValue() == null : this.getTaxRateValue().equals(other.getTaxRateValue()))
            && (this.getTaxRateValueText() == null ? other.getTaxRateValueText() == null : this.getTaxRateValueText().equals(other.getTaxRateValueText()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getTenantCode() == null ? other.getTenantCode() == null : this.getTenantCode().equals(other.getTenantCode()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getLastUpdateUserId() == null ? other.getLastUpdateUserId() == null : this.getLastUpdateUserId().equals(other.getLastUpdateUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTaxRateId() == null) ? 0 : getTaxRateId().hashCode());
        result = prime * result + ((getTaxRateName() == null) ? 0 : getTaxRateName().hashCode());
        result = prime * result + ((getTaxRateValue() == null) ? 0 : getTaxRateValue().hashCode());
        result = prime * result + ((getTaxRateValueText() == null) ? 0 : getTaxRateValueText().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getTenantCode() == null) ? 0 : getTenantCode().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getLastUpdateUserId() == null) ? 0 : getLastUpdateUserId().hashCode());
        return result;
    }
}