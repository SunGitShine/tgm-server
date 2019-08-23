package com.juma.tgm.configure.domain;

import java.io.Serializable;
import java.util.Date;

public class ServiceConf implements Serializable {
    private Integer serviceConfId;

    private Integer tenantId;

    private String tenantCode;

    private String parentRegionCode;

    private String parentRegionName;

    private String regionCode;

    private String regionName;

    private Boolean isOpenServiceRegion;

    private Boolean isOpenEntryRegion;

    private Boolean isProhibitionZone;

    private String customerServiceTel;

    private String operationSpecialist;

    private String operationSpecialistTel;

    private Date createTime;

    private Integer createUserId;

    private Date lastUpdateTime;

    private Integer lastUpdateUserId;

    private static final long serialVersionUID = 1L;

    public Integer getServiceConfId() {
        return serviceConfId;
    }

    public void setServiceConfId(Integer serviceConfId) {
        this.serviceConfId = serviceConfId;
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

    public String getParentRegionCode() {
        return parentRegionCode;
    }

    public void setParentRegionCode(String parentRegionCode) {
        this.parentRegionCode = parentRegionCode == null ? null : parentRegionCode.trim();
    }

    public String getParentRegionName() {
        return parentRegionName;
    }

    public void setParentRegionName(String parentRegionName) {
        this.parentRegionName = parentRegionName == null ? null : parentRegionName.trim();
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

    public Boolean getIsOpenServiceRegion() {
        return isOpenServiceRegion;
    }

    public void setIsOpenServiceRegion(Boolean isOpenServiceRegion) {
        this.isOpenServiceRegion = isOpenServiceRegion;
    }

    public Boolean getIsOpenEntryRegion() {
        return isOpenEntryRegion;
    }

    public void setIsOpenEntryRegion(Boolean isOpenEntryRegion) {
        this.isOpenEntryRegion = isOpenEntryRegion;
    }

    public Boolean getIsProhibitionZone() {
        return isProhibitionZone;
    }

    public void setIsProhibitionZone(Boolean isProhibitionZone) {
        this.isProhibitionZone = isProhibitionZone;
    }

    public String getCustomerServiceTel() {
        return customerServiceTel;
    }

    public void setCustomerServiceTel(String customerServiceTel) {
        this.customerServiceTel = customerServiceTel == null ? null : customerServiceTel.trim();
    }

    public String getOperationSpecialist() {
        return operationSpecialist;
    }

    public void setOperationSpecialist(String operationSpecialist) {
        this.operationSpecialist = operationSpecialist == null ? null : operationSpecialist.trim();
    }

    public String getOperationSpecialistTel() {
        return operationSpecialistTel;
    }

    public void setOperationSpecialistTel(String operationSpecialistTel) {
        this.operationSpecialistTel = operationSpecialistTel == null ? null : operationSpecialistTel.trim();
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
        ServiceConf other = (ServiceConf) that;
        return (this.getServiceConfId() == null ? other.getServiceConfId() == null : this.getServiceConfId().equals(other.getServiceConfId()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getTenantCode() == null ? other.getTenantCode() == null : this.getTenantCode().equals(other.getTenantCode()))
            && (this.getParentRegionCode() == null ? other.getParentRegionCode() == null : this.getParentRegionCode().equals(other.getParentRegionCode()))
            && (this.getParentRegionName() == null ? other.getParentRegionName() == null : this.getParentRegionName().equals(other.getParentRegionName()))
            && (this.getRegionCode() == null ? other.getRegionCode() == null : this.getRegionCode().equals(other.getRegionCode()))
            && (this.getRegionName() == null ? other.getRegionName() == null : this.getRegionName().equals(other.getRegionName()))
            && (this.getIsOpenServiceRegion() == null ? other.getIsOpenServiceRegion() == null : this.getIsOpenServiceRegion().equals(other.getIsOpenServiceRegion()))
            && (this.getIsOpenEntryRegion() == null ? other.getIsOpenEntryRegion() == null : this.getIsOpenEntryRegion().equals(other.getIsOpenEntryRegion()))
            && (this.getIsProhibitionZone() == null ? other.getIsProhibitionZone() == null : this.getIsProhibitionZone().equals(other.getIsProhibitionZone()))
            && (this.getCustomerServiceTel() == null ? other.getCustomerServiceTel() == null : this.getCustomerServiceTel().equals(other.getCustomerServiceTel()))
            && (this.getOperationSpecialist() == null ? other.getOperationSpecialist() == null : this.getOperationSpecialist().equals(other.getOperationSpecialist()))
            && (this.getOperationSpecialistTel() == null ? other.getOperationSpecialistTel() == null : this.getOperationSpecialistTel().equals(other.getOperationSpecialistTel()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getLastUpdateUserId() == null ? other.getLastUpdateUserId() == null : this.getLastUpdateUserId().equals(other.getLastUpdateUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getServiceConfId() == null) ? 0 : getServiceConfId().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getTenantCode() == null) ? 0 : getTenantCode().hashCode());
        result = prime * result + ((getParentRegionCode() == null) ? 0 : getParentRegionCode().hashCode());
        result = prime * result + ((getParentRegionName() == null) ? 0 : getParentRegionName().hashCode());
        result = prime * result + ((getRegionCode() == null) ? 0 : getRegionCode().hashCode());
        result = prime * result + ((getRegionName() == null) ? 0 : getRegionName().hashCode());
        result = prime * result + ((getIsOpenServiceRegion() == null) ? 0 : getIsOpenServiceRegion().hashCode());
        result = prime * result + ((getIsOpenEntryRegion() == null) ? 0 : getIsOpenEntryRegion().hashCode());
        result = prime * result + ((getIsProhibitionZone() == null) ? 0 : getIsProhibitionZone().hashCode());
        result = prime * result + ((getCustomerServiceTel() == null) ? 0 : getCustomerServiceTel().hashCode());
        result = prime * result + ((getOperationSpecialist() == null) ? 0 : getOperationSpecialist().hashCode());
        result = prime * result + ((getOperationSpecialistTel() == null) ? 0 : getOperationSpecialistTel().hashCode());
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
        sb.append(", serviceConfId=").append(serviceConfId);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", tenantCode=").append(tenantCode);
        sb.append(", parentRegionCode=").append(parentRegionCode);
        sb.append(", parentRegionName=").append(parentRegionName);
        sb.append(", regionCode=").append(regionCode);
        sb.append(", regionName=").append(regionName);
        sb.append(", isOpenServiceRegion=").append(isOpenServiceRegion);
        sb.append(", isOpenEntryRegion=").append(isOpenEntryRegion);
        sb.append(", isProhibitionZone=").append(isProhibitionZone);
        sb.append(", customerServiceTel=").append(customerServiceTel);
        sb.append(", operationSpecialist=").append(operationSpecialist);
        sb.append(", operationSpecialistTel=").append(operationSpecialistTel);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", lastUpdateUserId=").append(lastUpdateUserId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}