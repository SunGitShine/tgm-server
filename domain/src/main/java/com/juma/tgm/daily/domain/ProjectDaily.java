package com.juma.tgm.daily.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;



@ApiModel("项目日报")
public class ProjectDaily implements Serializable {
    @ApiModelProperty("项目日报ID")
    private Integer projectDailyId;

    @ApiModelProperty("租户ID")
    private Integer tenantId;

    @ApiModelProperty("业务区域")
    private String areaCode;

    @ApiModelProperty("日报编号")
    private String projectDailyNo;

    @ApiModelProperty("日报日期")
    private Date projectDailyDate;

    @ApiModelProperty("项目ID")
    private Integer projectId;

    @ApiModelProperty("客户ID")
    private Integer customerId;

    @ApiModelProperty("日报状态 1、未到期；2、已到期")
    private Integer dailyStatus;

    @ApiModelProperty("台账状态 1、未上传；2、已上传")
    private Integer standingBookStatus;

    @ApiModelProperty("运费状态 1、未确认；2、部分确认；3、已确认")
    private Integer freightStatus;

    @ApiModelProperty("是否已删除 0、否；1、是")
    private Boolean isDelete;

    @ApiModelProperty("创建人")
    private Integer createUserId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新人")
    private Integer lastUpdateUserId;

    @ApiModelProperty("更新时间")
    private Date lastUpdateTime;

    private static final long serialVersionUID = 1L;

    public Integer getProjectDailyId() {
        return projectDailyId;
    }

    public void setProjectDailyId(Integer projectDailyId) {
        this.projectDailyId = projectDailyId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getProjectDailyNo() {
        return projectDailyNo;
    }

    public void setProjectDailyNo(String projectDailyNo) {
        this.projectDailyNo = projectDailyNo == null ? null : projectDailyNo.trim();
    }

    public Date getProjectDailyDate() {
        return projectDailyDate;
    }

    public void setProjectDailyDate(Date projectDailyDate) {
        this.projectDailyDate = projectDailyDate;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getDailyStatus() {
        return dailyStatus;
    }

    public void setDailyStatus(Integer dailyStatus) {
        this.dailyStatus = dailyStatus;
    }

    public Integer getStandingBookStatus() {
        return standingBookStatus;
    }

    public void setStandingBookStatus(Integer standingBookStatus) {
        this.standingBookStatus = standingBookStatus;
    }

    public Integer getFreightStatus() {
        return freightStatus;
    }

    public void setFreightStatus(Integer freightStatus) {
        this.freightStatus = freightStatus;
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
        ProjectDaily other = (ProjectDaily) that;
        return (this.getProjectDailyId() == null ? other.getProjectDailyId() == null : this.getProjectDailyId().equals(other.getProjectDailyId()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getAreaCode() == null ? other.getAreaCode() == null : this.getAreaCode().equals(other.getAreaCode()))
            && (this.getProjectDailyNo() == null ? other.getProjectDailyNo() == null : this.getProjectDailyNo().equals(other.getProjectDailyNo()))
            && (this.getProjectDailyDate() == null ? other.getProjectDailyDate() == null : this.getProjectDailyDate().equals(other.getProjectDailyDate()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getDailyStatus() == null ? other.getDailyStatus() == null : this.getDailyStatus().equals(other.getDailyStatus()))
            && (this.getStandingBookStatus() == null ? other.getStandingBookStatus() == null : this.getStandingBookStatus().equals(other.getStandingBookStatus()))
            && (this.getFreightStatus() == null ? other.getFreightStatus() == null : this.getFreightStatus().equals(other.getFreightStatus()))
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
        result = prime * result + ((getProjectDailyId() == null) ? 0 : getProjectDailyId().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getAreaCode() == null) ? 0 : getAreaCode().hashCode());
        result = prime * result + ((getProjectDailyNo() == null) ? 0 : getProjectDailyNo().hashCode());
        result = prime * result + ((getProjectDailyDate() == null) ? 0 : getProjectDailyDate().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getDailyStatus() == null) ? 0 : getDailyStatus().hashCode());
        result = prime * result + ((getStandingBookStatus() == null) ? 0 : getStandingBookStatus().hashCode());
        result = prime * result + ((getFreightStatus() == null) ? 0 : getFreightStatus().hashCode());
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
        sb.append(", projectDailyId=").append(projectDailyId);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", areaCode=").append(areaCode);
        sb.append(", projectDailyNo=").append(projectDailyNo);
        sb.append(", projectDailyDate=").append(projectDailyDate);
        sb.append(", projectId=").append(projectId);
        sb.append(", customerId=").append(customerId);
        sb.append(", dailyStatus=").append(dailyStatus);
        sb.append(", standingBookStatus=").append(standingBookStatus);
        sb.append(", freightStatus=").append(freightStatus);
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
        projectDailyId,
        tenantId,
        areaCode,
        projectDailyNo,
        projectDailyDate,
        projectId,
        customerId,
        dailyStatus,
        standingBookStatus,
        freightStatus,
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