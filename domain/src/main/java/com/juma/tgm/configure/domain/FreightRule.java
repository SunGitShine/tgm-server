package com.juma.tgm.configure.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FreightRule implements Serializable {
    private Integer freightRuleId;

    private Integer tenantId;

    private String tenantCode;

    private String regionCode;

    private String regionName;

    private Integer baseMiles;

    private Integer baseVolume;

    private Integer baseWeight;

    private BigDecimal baseFreight;

    private BigDecimal overMilesUnit;

    private BigDecimal overVolumeUnit;

    private BigDecimal overWeightUnit;

    private Integer maxMiles;

    private Integer maxWeight;

    private Integer maxVolume;

    private Boolean isEnable;

    private Date createTime;

    private Integer createUserId;

    private Date lastUpdateTime;

    private Integer lastUpdateUserId;

    private static final long serialVersionUID = 1L;

    public Integer getFreightRuleId() {
        return freightRuleId;
    }

    public void setFreightRuleId(Integer freightRuleId) {
        this.freightRuleId = freightRuleId;
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

    public Integer getBaseMiles() {
        return baseMiles;
    }

    public void setBaseMiles(Integer baseMiles) {
        this.baseMiles = baseMiles;
    }

    public Integer getBaseVolume() {
        return baseVolume;
    }

    public void setBaseVolume(Integer baseVolume) {
        this.baseVolume = baseVolume;
    }

    public Integer getBaseWeight() {
        return baseWeight;
    }

    public void setBaseWeight(Integer baseWeight) {
        this.baseWeight = baseWeight;
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

    public BigDecimal getOverVolumeUnit() {
        return overVolumeUnit;
    }

    public void setOverVolumeUnit(BigDecimal overVolumeUnit) {
        this.overVolumeUnit = overVolumeUnit;
    }

    public BigDecimal getOverWeightUnit() {
        return overWeightUnit;
    }

    public void setOverWeightUnit(BigDecimal overWeightUnit) {
        this.overWeightUnit = overWeightUnit;
    }

    public Integer getMaxMiles() {
        return maxMiles;
    }

    public void setMaxMiles(Integer maxMiles) {
        this.maxMiles = maxMiles;
    }

    public Integer getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Integer maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Integer getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(Integer maxVolume) {
        this.maxVolume = maxVolume;
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
        FreightRule other = (FreightRule) that;
        return (this.getFreightRuleId() == null ? other.getFreightRuleId() == null : this.getFreightRuleId().equals(other.getFreightRuleId()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getTenantCode() == null ? other.getTenantCode() == null : this.getTenantCode().equals(other.getTenantCode()))
            && (this.getRegionCode() == null ? other.getRegionCode() == null : this.getRegionCode().equals(other.getRegionCode()))
            && (this.getRegionName() == null ? other.getRegionName() == null : this.getRegionName().equals(other.getRegionName()))
            && (this.getBaseMiles() == null ? other.getBaseMiles() == null : this.getBaseMiles().equals(other.getBaseMiles()))
            && (this.getBaseVolume() == null ? other.getBaseVolume() == null : this.getBaseVolume().equals(other.getBaseVolume()))
            && (this.getBaseWeight() == null ? other.getBaseWeight() == null : this.getBaseWeight().equals(other.getBaseWeight()))
            && (this.getBaseFreight() == null ? other.getBaseFreight() == null : this.getBaseFreight().equals(other.getBaseFreight()))
            && (this.getOverMilesUnit() == null ? other.getOverMilesUnit() == null : this.getOverMilesUnit().equals(other.getOverMilesUnit()))
            && (this.getOverVolumeUnit() == null ? other.getOverVolumeUnit() == null : this.getOverVolumeUnit().equals(other.getOverVolumeUnit()))
            && (this.getOverWeightUnit() == null ? other.getOverWeightUnit() == null : this.getOverWeightUnit().equals(other.getOverWeightUnit()))
            && (this.getMaxMiles() == null ? other.getMaxMiles() == null : this.getMaxMiles().equals(other.getMaxMiles()))
            && (this.getMaxWeight() == null ? other.getMaxWeight() == null : this.getMaxWeight().equals(other.getMaxWeight()))
            && (this.getMaxVolume() == null ? other.getMaxVolume() == null : this.getMaxVolume().equals(other.getMaxVolume()))
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
        result = prime * result + ((getFreightRuleId() == null) ? 0 : getFreightRuleId().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getTenantCode() == null) ? 0 : getTenantCode().hashCode());
        result = prime * result + ((getRegionCode() == null) ? 0 : getRegionCode().hashCode());
        result = prime * result + ((getRegionName() == null) ? 0 : getRegionName().hashCode());
        result = prime * result + ((getBaseMiles() == null) ? 0 : getBaseMiles().hashCode());
        result = prime * result + ((getBaseVolume() == null) ? 0 : getBaseVolume().hashCode());
        result = prime * result + ((getBaseWeight() == null) ? 0 : getBaseWeight().hashCode());
        result = prime * result + ((getBaseFreight() == null) ? 0 : getBaseFreight().hashCode());
        result = prime * result + ((getOverMilesUnit() == null) ? 0 : getOverMilesUnit().hashCode());
        result = prime * result + ((getOverVolumeUnit() == null) ? 0 : getOverVolumeUnit().hashCode());
        result = prime * result + ((getOverWeightUnit() == null) ? 0 : getOverWeightUnit().hashCode());
        result = prime * result + ((getMaxMiles() == null) ? 0 : getMaxMiles().hashCode());
        result = prime * result + ((getMaxWeight() == null) ? 0 : getMaxWeight().hashCode());
        result = prime * result + ((getMaxVolume() == null) ? 0 : getMaxVolume().hashCode());
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
        sb.append(", freightRuleId=").append(freightRuleId);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", tenantCode=").append(tenantCode);
        sb.append(", regionCode=").append(regionCode);
        sb.append(", regionName=").append(regionName);
        sb.append(", baseMiles=").append(baseMiles);
        sb.append(", baseVolume=").append(baseVolume);
        sb.append(", baseWeight=").append(baseWeight);
        sb.append(", baseFreight=").append(baseFreight);
        sb.append(", overMilesUnit=").append(overMilesUnit);
        sb.append(", overVolumeUnit=").append(overVolumeUnit);
        sb.append(", overWeightUnit=").append(overWeightUnit);
        sb.append(", maxMiles=").append(maxMiles);
        sb.append(", maxWeight=").append(maxWeight);
        sb.append(", maxVolume=").append(maxVolume);
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