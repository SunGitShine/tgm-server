package com.juma.tgm.configure.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PackFreightRule implements Serializable {
    private Integer packFreightRuleId;

    private Integer tenantId;

    private String tenantCode;

    private String regionCode;

    private String regionName;

    private Integer truckTypeId;

    private String truckTypeName;

    private Integer baseMiles;

    private BigDecimal baseFreight;

    private BigDecimal overMilesUnit;

    private Boolean isEnable;

    private Date createTime;

    private Integer createUserId;

    private Date lastUpdateTime;

    private Integer lastUpdateUserId;

    private static final long serialVersionUID = 1L;

    public Integer getPackFreightRuleId() {
        return packFreightRuleId;
    }

    public void setPackFreightRuleId(Integer packFreightRuleId) {
        this.packFreightRuleId = packFreightRuleId;
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

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode == null ? null : regionCode.trim();
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public Integer getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(Integer truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName == null ? null : truckTypeName.trim();
    }

    public Integer getBaseMiles() {
        return baseMiles;
    }

    public void setBaseMiles(Integer baseMiles) {
        this.baseMiles = baseMiles;
    }

    public BigDecimal getBaseFreight() {
        return baseFreight;
    }

    public void setBaseFreight(BigDecimal baseFreight) {
        this.baseFreight = baseFreight;
    }

    public BigDecimal getOverMilesUnit() {
        return overMilesUnit;
    }

    public void setOverMilesUnit(BigDecimal overMilesUnit) {
        this.overMilesUnit = overMilesUnit;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
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
        PackFreightRule other = (PackFreightRule) that;
        return (this.getPackFreightRuleId() == null ? other.getPackFreightRuleId() == null : this.getPackFreightRuleId().equals(other.getPackFreightRuleId()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getTenantCode() == null ? other.getTenantCode() == null : this.getTenantCode().equals(other.getTenantCode()))
            && (this.getRegionCode() == null ? other.getRegionCode() == null : this.getRegionCode().equals(other.getRegionCode()))
            && (this.getRegionName() == null ? other.getRegionName() == null : this.getRegionName().equals(other.getRegionName()))
            && (this.getTruckTypeId() == null ? other.getTruckTypeId() == null : this.getTruckTypeId().equals(other.getTruckTypeId()))
            && (this.getTruckTypeName() == null ? other.getTruckTypeName() == null : this.getTruckTypeName().equals(other.getTruckTypeName()))
            && (this.getBaseMiles() == null ? other.getBaseMiles() == null : this.getBaseMiles().equals(other.getBaseMiles()))
            && (this.getBaseFreight() == null ? other.getBaseFreight() == null : this.getBaseFreight().equals(other.getBaseFreight()))
            && (this.getOverMilesUnit() == null ? other.getOverMilesUnit() == null : this.getOverMilesUnit().equals(other.getOverMilesUnit()))
            && (this.getIsEnable() == null ? other.getIsEnable() == null : this.getIsEnable().equals(other.getIsEnable()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getLastUpdateUserId() == null ? other.getLastUpdateUserId() == null : this.getLastUpdateUserId().equals(other.getLastUpdateUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPackFreightRuleId() == null) ? 0 : getPackFreightRuleId().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getTenantCode() == null) ? 0 : getTenantCode().hashCode());
        result = prime * result + ((getRegionCode() == null) ? 0 : getRegionCode().hashCode());
        result = prime * result + ((getRegionName() == null) ? 0 : getRegionName().hashCode());
        result = prime * result + ((getTruckTypeId() == null) ? 0 : getTruckTypeId().hashCode());
        result = prime * result + ((getTruckTypeName() == null) ? 0 : getTruckTypeName().hashCode());
        result = prime * result + ((getBaseMiles() == null) ? 0 : getBaseMiles().hashCode());
        result = prime * result + ((getBaseFreight() == null) ? 0 : getBaseFreight().hashCode());
        result = prime * result + ((getOverMilesUnit() == null) ? 0 : getOverMilesUnit().hashCode());
        result = prime * result + ((getIsEnable() == null) ? 0 : getIsEnable().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getLastUpdateUserId() == null) ? 0 : getLastUpdateUserId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", packFreightRuleId=").append(packFreightRuleId);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", tenantCode=").append(tenantCode);
        sb.append(", regionCode=").append(regionCode);
        sb.append(", regionName=").append(regionName);
        sb.append(", truckTypeId=").append(truckTypeId);
        sb.append(", truckTypeName=").append(truckTypeName);
        sb.append(", baseMiles=").append(baseMiles);
        sb.append(", baseFreight=").append(baseFreight);
        sb.append(", overMilesUnit=").append(overMilesUnit);
        sb.append(", isEnable=").append(isEnable);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", lastUpdateUserId=").append(lastUpdateUserId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}