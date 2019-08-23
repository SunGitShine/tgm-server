package com.juma.tgm.customerManager.domain;

import java.io.Serializable;
import java.util.Date;

public class Task4WaybillReport implements Serializable {
    private Integer taskReportId;

    private Integer taskId;

    private Date taskExcuteDate;

    private Byte taskStatus;

    private String taskResult;

    private Byte hasRead;

    private Date createTime;

    private Integer createUserId;

    private Integer employeeId;

    private static final long serialVersionUID = 1L;

    public Integer getTaskReportId() {
        return taskReportId;
    }

    public void setTaskReportId(Integer taskReportId) {
        this.taskReportId = taskReportId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Date getTaskExcuteDate() {
        return taskExcuteDate;
    }

    public void setTaskExcuteDate(Date taskExcuteDate) {
        this.taskExcuteDate = taskExcuteDate;
    }

    public Byte getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Byte taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskResult() {
        return taskResult;
    }

    public void setTaskResult(String taskResult) {
        this.taskResult = taskResult == null ? null : taskResult.trim();
    }

    public Byte getHasRead() {
        return hasRead;
    }

    public void setHasRead(Byte hasRead) {
        this.hasRead = hasRead;
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

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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
        Task4WaybillReport other = (Task4WaybillReport) that;
        return (this.getTaskReportId() == null ? other.getTaskReportId() == null : this.getTaskReportId().equals(other.getTaskReportId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getTaskExcuteDate() == null ? other.getTaskExcuteDate() == null : this.getTaskExcuteDate().equals(other.getTaskExcuteDate()))
            && (this.getTaskStatus() == null ? other.getTaskStatus() == null : this.getTaskStatus().equals(other.getTaskStatus()))
            && (this.getTaskResult() == null ? other.getTaskResult() == null : this.getTaskResult().equals(other.getTaskResult()))
            && (this.getHasRead() == null ? other.getHasRead() == null : this.getHasRead().equals(other.getHasRead()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getEmployeeId() == null ? other.getEmployeeId() == null : this.getEmployeeId().equals(other.getEmployeeId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTaskReportId() == null) ? 0 : getTaskReportId().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getTaskExcuteDate() == null) ? 0 : getTaskExcuteDate().hashCode());
        result = prime * result + ((getTaskStatus() == null) ? 0 : getTaskStatus().hashCode());
        result = prime * result + ((getTaskResult() == null) ? 0 : getTaskResult().hashCode());
        result = prime * result + ((getHasRead() == null) ? 0 : getHasRead().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getEmployeeId() == null) ? 0 : getEmployeeId().hashCode());
        return result;
    }
}