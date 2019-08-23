package com.juma.tgm.project.domain.v2;

import java.io.Serializable;
import java.util.Date;

public class ProjectDepot implements Serializable {
    private Integer depotId;

    private Integer projectId;

    private String depotName;

    private String depotAddress;

    private String depotCoordinates;

    private String linkMan;

    private String linkManPhone;

    private Boolean isDelete;

    private Integer createUserId;

    private Date createTime;

    private Integer lastUpdateUserId;

    private Date lastUpdateTime;

    private static final long serialVersionUID = 1L;

    public Integer getDepotId() {
        return depotId;
    }

    public void setDepotId(Integer depotId) {
        this.depotId = depotId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getDepotName() {
        return depotName;
    }

    public void setDepotName(String depotName) {
        this.depotName = depotName == null ? null : depotName.trim();
    }

    public String getDepotAddress() {
        return depotAddress;
    }

    public void setDepotAddress(String depotAddress) {
        this.depotAddress = depotAddress == null ? null : depotAddress.trim();
    }

    public String getDepotCoordinates() {
        return depotCoordinates;
    }

    public void setDepotCoordinates(String depotCoordinates) {
        this.depotCoordinates = depotCoordinates == null ? null : depotCoordinates.trim();
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan == null ? null : linkMan.trim();
    }

    public String getLinkManPhone() {
        return linkManPhone;
    }

    public void setLinkManPhone(String linkManPhone) {
        this.linkManPhone = linkManPhone == null ? null : linkManPhone.trim();
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
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
        ProjectDepot other = (ProjectDepot) that;
        return (this.getDepotId() == null ? other.getDepotId() == null : this.getDepotId().equals(other.getDepotId()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getDepotName() == null ? other.getDepotName() == null : this.getDepotName().equals(other.getDepotName()))
            && (this.getDepotAddress() == null ? other.getDepotAddress() == null : this.getDepotAddress().equals(other.getDepotAddress()))
            && (this.getDepotCoordinates() == null ? other.getDepotCoordinates() == null : this.getDepotCoordinates().equals(other.getDepotCoordinates()))
            && (this.getLinkMan() == null ? other.getLinkMan() == null : this.getLinkMan().equals(other.getLinkMan()))
            && (this.getLinkManPhone() == null ? other.getLinkManPhone() == null : this.getLinkManPhone().equals(other.getLinkManPhone()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUserId() == null ? other.getLastUpdateUserId() == null : this.getLastUpdateUserId().equals(other.getLastUpdateUserId()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDepotId() == null) ? 0 : getDepotId().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getDepotName() == null) ? 0 : getDepotName().hashCode());
        result = prime * result + ((getDepotAddress() == null) ? 0 : getDepotAddress().hashCode());
        result = prime * result + ((getDepotCoordinates() == null) ? 0 : getDepotCoordinates().hashCode());
        result = prime * result + ((getLinkMan() == null) ? 0 : getLinkMan().hashCode());
        result = prime * result + ((getLinkManPhone() == null) ? 0 : getLinkManPhone().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
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
        sb.append(", depotId=").append(depotId);
        sb.append(", projectId=").append(projectId);
        sb.append(", depotName=").append(depotName);
        sb.append(", depotAddress=").append(depotAddress);
        sb.append(", depotCoordinates=").append(depotCoordinates);
        sb.append(", linkMan=").append(linkMan);
        sb.append(", linkManPhone=").append(linkManPhone);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastUpdateUserId=").append(lastUpdateUserId);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        depotId,
        projectId,
        depotName,
        depotAddress,
        depotCoordinates,
        linkMan,
        linkManPhone,
        isDelete,
        createUserId,
        createTime,
        lastUpdateUserId,
        lastUpdateTime;

        public String asc() {
            return column() + " ASC";
        }

        public String desc() {
            return column() + " DESC";
        }

        private String column() {
            StringBuilder buffer = new StringBuilder();
            char[] charArray = this.name().toCharArray();
            for(char ch : charArray) {
                if(Character.isUpperCase(ch)){
                    buffer.append("_");
                    buffer.append(Character.toLowerCase(ch));
                } else {
                    buffer.append(ch);
                }
            }
            return buffer.toString();
        }
    }
}