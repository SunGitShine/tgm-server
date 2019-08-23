package com.juma.tgm.task.domain;

import java.io.Serializable;
import java.util.Date;

public class TaskCalendarReason implements Serializable {
    private Integer reasonId;

    private Integer calendarId;

    private Integer reasonType;

    private Integer reasonSort;

    private String reason;

    private Integer createUserId;

    private Date createTime;

    private Integer lastUpdateUserId;

    private Date lastUpdateTime;

    private static final long serialVersionUID = 1L;

    public Integer getReasonId() {
        return reasonId;
    }

    public void setReasonId(Integer reasonId) {
        this.reasonId = reasonId;
    }

    public Integer getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(Integer calendarId) {
        this.calendarId = calendarId;
    }

    public Integer getReasonType() {
        return reasonType;
    }

    public void setReasonType(Integer reasonType) {
        this.reasonType = reasonType;
    }

    public Integer getReasonSort() {
        return reasonSort;
    }

    public void setReasonSort(Integer reasonSort) {
        this.reasonSort = reasonSort;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
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
        TaskCalendarReason other = (TaskCalendarReason) that;
        return (this.getReasonId() == null ? other.getReasonId() == null : this.getReasonId().equals(other.getReasonId()))
            && (this.getCalendarId() == null ? other.getCalendarId() == null : this.getCalendarId().equals(other.getCalendarId()))
            && (this.getReasonType() == null ? other.getReasonType() == null : this.getReasonType().equals(other.getReasonType()))
            && (this.getReasonSort() == null ? other.getReasonSort() == null : this.getReasonSort().equals(other.getReasonSort()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUserId() == null ? other.getLastUpdateUserId() == null : this.getLastUpdateUserId().equals(other.getLastUpdateUserId()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getReasonId() == null) ? 0 : getReasonId().hashCode());
        result = prime * result + ((getCalendarId() == null) ? 0 : getCalendarId().hashCode());
        result = prime * result + ((getReasonType() == null) ? 0 : getReasonType().hashCode());
        result = prime * result + ((getReasonSort() == null) ? 0 : getReasonSort().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
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
        sb.append(", reasonId=").append(reasonId);
        sb.append(", calendarId=").append(calendarId);
        sb.append(", reasonType=").append(reasonType);
        sb.append(", reasonSort=").append(reasonSort);
        sb.append(", reason=").append(reason);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastUpdateUserId=").append(lastUpdateUserId);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        reasonId,
        calendarId,
        reasonType,
        reasonSort,
        reason,
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