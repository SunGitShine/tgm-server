package com.juma.tgm.configure.domain;

import java.io.Serializable;
import java.util.Date;

public class PrivateFreightFactor implements Serializable {
    private Integer privateFreightFactorId;

    private Integer tenantId;

    private String tenantCode;

    private String regionCode;

    private String regionName;

    private String truckTypeName;

    private Integer truckTypeId;

    private Integer freightWay;

    private String factorJson;

    private Byte isEnable;

    private Date createTime;

    private Integer createUserId;

    private Integer lastUpdateUserId;

    private Date lastUpdateTime;

    private static final long serialVersionUID = 1L;

    public Integer getPrivateFreightFactorId() {
        return privateFreightFactorId;
    }

    public void setPrivateFreightFactorId(Integer privateFreightFactorId) {
        this.privateFreightFactorId = privateFreightFactorId;
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

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName == null ? null : truckTypeName.trim();
    }

    public Integer getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(Integer truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public Integer getFreightWay() {
        return freightWay;
    }

    public void setFreightWay(Integer freightWay) {
        this.freightWay = freightWay;
    }

    public String getFactorJson() {
        return factorJson;
    }

    public void setFactorJson(String factorJson) {
        this.factorJson = factorJson == null ? null : factorJson.trim();
    }

    public Byte getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Byte isEnable) {
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

    public Integer getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(Integer lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
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
        PrivateFreightFactor other = (PrivateFreightFactor) that;
        return (this.getPrivateFreightFactorId() == null ? other.getPrivateFreightFactorId() == null : this.getPrivateFreightFactorId().equals(other.getPrivateFreightFactorId()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getTenantCode() == null ? other.getTenantCode() == null : this.getTenantCode().equals(other.getTenantCode()))
            && (this.getRegionCode() == null ? other.getRegionCode() == null : this.getRegionCode().equals(other.getRegionCode()))
            && (this.getRegionName() == null ? other.getRegionName() == null : this.getRegionName().equals(other.getRegionName()))
            && (this.getTruckTypeName() == null ? other.getTruckTypeName() == null : this.getTruckTypeName().equals(other.getTruckTypeName()))
            && (this.getTruckTypeId() == null ? other.getTruckTypeId() == null : this.getTruckTypeId().equals(other.getTruckTypeId()))
            && (this.getFreightWay() == null ? other.getFreightWay() == null : this.getFreightWay().equals(other.getFreightWay()))
            && (this.getFactorJson() == null ? other.getFactorJson() == null : this.getFactorJson().equals(other.getFactorJson()))
            && (this.getIsEnable() == null ? other.getIsEnable() == null : this.getIsEnable().equals(other.getIsEnable()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getLastUpdateUserId() == null ? other.getLastUpdateUserId() == null : this.getLastUpdateUserId().equals(other.getLastUpdateUserId()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPrivateFreightFactorId() == null) ? 0 : getPrivateFreightFactorId().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getTenantCode() == null) ? 0 : getTenantCode().hashCode());
        result = prime * result + ((getRegionCode() == null) ? 0 : getRegionCode().hashCode());
        result = prime * result + ((getRegionName() == null) ? 0 : getRegionName().hashCode());
        result = prime * result + ((getTruckTypeName() == null) ? 0 : getTruckTypeName().hashCode());
        result = prime * result + ((getTruckTypeId() == null) ? 0 : getTruckTypeId().hashCode());
        result = prime * result + ((getFreightWay() == null) ? 0 : getFreightWay().hashCode());
        result = prime * result + ((getFactorJson() == null) ? 0 : getFactorJson().hashCode());
        result = prime * result + ((getIsEnable() == null) ? 0 : getIsEnable().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getLastUpdateUserId() == null) ? 0 : getLastUpdateUserId().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", privateFreightFactorId=").append(privateFreightFactorId);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", tenantCode=").append(tenantCode);
        sb.append(", regionCode=").append(regionCode);
        sb.append(", regionName=").append(regionName);
        sb.append(", truckTypeName=").append(truckTypeName);
        sb.append(", truckTypeId=").append(truckTypeId);
        sb.append(", freightWay=").append(freightWay);
        sb.append(", factorJson=").append(factorJson);
        sb.append(", isEnable=").append(isEnable);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", lastUpdateUserId=").append(lastUpdateUserId);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}