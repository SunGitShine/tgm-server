package com.juma.tgm.configure.domain;

import java.io.Serializable;
import java.util.Date;

public class ServiceConfItem implements Serializable {
    private static final long serialVersionUID = 7726556356199208130L;

    private Integer serviceConfItemId;

    private Integer serviceConfId;

    private Integer fenceId;

    private String fenceName;

    private Integer fenceType;

    private String fenceAddress;

    private Date createTime;

    private Integer createUserId;

    private Date lastUpdateTime;

    private Integer lastUpdateUserId;

    public Integer getServiceConfItemId() {
        return serviceConfItemId;
    }

    public void setServiceConfItemId(Integer serviceConfItemId) {
        this.serviceConfItemId = serviceConfItemId;
    }

    public Integer getServiceConfId() {
        return serviceConfId;
    }

    public void setServiceConfId(Integer serviceConfId) {
        this.serviceConfId = serviceConfId;
    }

    public Integer getFenceId() {
        return fenceId;
    }

    public void setFenceId(Integer fenceId) {
        this.fenceId = fenceId;
    }

    public String getFenceName() {
        return fenceName;
    }

    public void setFenceName(String fenceName) {
        this.fenceName = fenceName == null ? null : fenceName.trim();
    }

    public Integer getFenceType() {
        return fenceType;
    }

    public void setFenceType(Integer fenceType) {
        this.fenceType = fenceType;
    }

    public String getFenceAddress() {
        return fenceAddress;
    }

    public void setFenceAddress(String fenceAddress) {
        this.fenceAddress = fenceAddress;
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
        ServiceConfItem other = (ServiceConfItem) that;
        return (this.getServiceConfItemId() == null ? other.getServiceConfItemId() == null : this.getServiceConfItemId().equals(other.getServiceConfItemId()))
            && (this.getServiceConfId() == null ? other.getServiceConfId() == null : this.getServiceConfId().equals(other.getServiceConfId()))
            && (this.getFenceId() == null ? other.getFenceId() == null : this.getFenceId().equals(other.getFenceId()))
            && (this.getFenceName() == null ? other.getFenceName() == null : this.getFenceName().equals(other.getFenceName()))
            && (this.getFenceType() == null ? other.getFenceType() == null : this.getFenceType().equals(other.getFenceType()))
            && (this.getFenceAddress() == null ? other.getFenceAddress() == null : this.getFenceAddress().equals(other.getFenceAddress()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getLastUpdateUserId() == null ? other.getLastUpdateUserId() == null : this.getLastUpdateUserId().equals(other.getLastUpdateUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getServiceConfItemId() == null) ? 0 : getServiceConfItemId().hashCode());
        result = prime * result + ((getServiceConfId() == null) ? 0 : getServiceConfId().hashCode());
        result = prime * result + ((getFenceId() == null) ? 0 : getFenceId().hashCode());
        result = prime * result + ((getFenceName() == null) ? 0 : getFenceName().hashCode());
        result = prime * result + ((getFenceType() == null) ? 0 : getFenceType().hashCode());
        result = prime * result + ((getFenceAddress() == null) ? 0 : getFenceAddress().hashCode());
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
        sb.append(", serviceConfItemId=").append(serviceConfItemId);
        sb.append(", serviceConfId=").append(serviceConfId);
        sb.append(", fenceId=").append(fenceId);
        sb.append(", fenceName=").append(fenceName);
        sb.append(", fenceType=").append(fenceType);
        sb.append(", fenceAddress=").append(fenceAddress);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", lastUpdateUserId=").append(lastUpdateUserId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}