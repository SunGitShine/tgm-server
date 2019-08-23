package com.juma.tgm.customerManager.domain;

import java.io.Serializable;
import java.util.Date;

public class Task4Waybill implements Serializable {
    private Integer taskId;

    private Integer taskWaybillTemplateId;

    private Integer userId;

    private Integer employeeId;

    private Date taskStartDate;

    private Date taskEndDate;

    private String taskWeekDays;

    private Integer createUserId;

    private Date createTime;

    private Date lastUpdateTime;

    private Byte isClose;

    private Integer lastUpdateUserId;

    private static final long serialVersionUID = 1L;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskWaybillTemplateId() {
        return taskWaybillTemplateId;
    }

    public void setTaskWaybillTemplateId(Integer taskWaybillTemplateId) {
        this.taskWaybillTemplateId = taskWaybillTemplateId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Date getTaskStartDate() {
        return taskStartDate;
    }

    public void setTaskStartDate(Date taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    public Date getTaskEndDate() {
        return taskEndDate;
    }

    public void setTaskEndDate(Date taskEndDate) {
        this.taskEndDate = taskEndDate;
    }

    public String getTaskWeekDays() {
        return taskWeekDays;
    }

    public void setTaskWeekDays(String taskWeekDays) {
        this.taskWeekDays = taskWeekDays == null ? null : taskWeekDays.trim();
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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Byte getIsClose() {
        return isClose;
    }

    public void setIsClose(Byte isClose) {
        this.isClose = isClose;
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
        Task4Waybill other = (Task4Waybill) that;
        return (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getTaskWaybillTemplateId() == null ? other.getTaskWaybillTemplateId() == null : this.getTaskWaybillTemplateId().equals(other.getTaskWaybillTemplateId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getEmployeeId() == null ? other.getEmployeeId() == null : this.getEmployeeId().equals(other.getEmployeeId()))
            && (this.getTaskStartDate() == null ? other.getTaskStartDate() == null : this.getTaskStartDate().equals(other.getTaskStartDate()))
            && (this.getTaskEndDate() == null ? other.getTaskEndDate() == null : this.getTaskEndDate().equals(other.getTaskEndDate()))
            && (this.getTaskWeekDays() == null ? other.getTaskWeekDays() == null : this.getTaskWeekDays().equals(other.getTaskWeekDays()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getIsClose() == null ? other.getIsClose() == null : this.getIsClose().equals(other.getIsClose()))
            && (this.getLastUpdateUserId() == null ? other.getLastUpdateUserId() == null : this.getLastUpdateUserId().equals(other.getLastUpdateUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getTaskWaybillTemplateId() == null) ? 0 : getTaskWaybillTemplateId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getEmployeeId() == null) ? 0 : getEmployeeId().hashCode());
        result = prime * result + ((getTaskStartDate() == null) ? 0 : getTaskStartDate().hashCode());
        result = prime * result + ((getTaskEndDate() == null) ? 0 : getTaskEndDate().hashCode());
        result = prime * result + ((getTaskWeekDays() == null) ? 0 : getTaskWeekDays().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getIsClose() == null) ? 0 : getIsClose().hashCode());
        result = prime * result + ((getLastUpdateUserId() == null) ? 0 : getLastUpdateUserId().hashCode());
        return result;
    }
}