package com.juma.tgm.task.domain;

import java.io.Serializable;
import java.util.Date;

public class TaskCalendar implements Serializable {
    private Integer calendarId;

    private Integer taskId;

    private Integer timelineId;

    private Integer waybillId;

    private Integer vendorId;

    private Date workStartTime;

    private Date workEndTime;

    private Integer truckId;

    private Integer driverId;

    private Integer workStatus;

    private Integer deliveryStatus;

    private String failureReason;

    private static final long serialVersionUID = 1L;

    public Integer getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(Integer calendarId) {
        this.calendarId = calendarId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getTimelineId() {
        return timelineId;
    }

    public void setTimelineId(Integer timelineId) {
        this.timelineId = timelineId;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Date getWorkStartTime() {
        return workStartTime;
    }

    public void setWorkStartTime(Date workStartTime) {
        this.workStartTime = workStartTime;
    }

    public Date getWorkEndTime() {
        return workEndTime;
    }

    public void setWorkEndTime(Date workEndTime) {
        this.workEndTime = workEndTime;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

    public Integer getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Integer deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason == null ? null : failureReason.trim();
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
        TaskCalendar other = (TaskCalendar) that;
        return (this.getCalendarId() == null ? other.getCalendarId() == null : this.getCalendarId().equals(other.getCalendarId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getTimelineId() == null ? other.getTimelineId() == null : this.getTimelineId().equals(other.getTimelineId()))
            && (this.getWaybillId() == null ? other.getWaybillId() == null : this.getWaybillId().equals(other.getWaybillId()))
            && (this.getVendorId() == null ? other.getVendorId() == null : this.getVendorId().equals(other.getVendorId()))
            && (this.getWorkStartTime() == null ? other.getWorkStartTime() == null : this.getWorkStartTime().equals(other.getWorkStartTime()))
            && (this.getWorkEndTime() == null ? other.getWorkEndTime() == null : this.getWorkEndTime().equals(other.getWorkEndTime()))
            && (this.getTruckId() == null ? other.getTruckId() == null : this.getTruckId().equals(other.getTruckId()))
            && (this.getDriverId() == null ? other.getDriverId() == null : this.getDriverId().equals(other.getDriverId()))
            && (this.getWorkStatus() == null ? other.getWorkStatus() == null : this.getWorkStatus().equals(other.getWorkStatus()))
            && (this.getDeliveryStatus() == null ? other.getDeliveryStatus() == null : this.getDeliveryStatus().equals(other.getDeliveryStatus()))
            && (this.getFailureReason() == null ? other.getFailureReason() == null : this.getFailureReason().equals(other.getFailureReason()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCalendarId() == null) ? 0 : getCalendarId().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getTimelineId() == null) ? 0 : getTimelineId().hashCode());
        result = prime * result + ((getWaybillId() == null) ? 0 : getWaybillId().hashCode());
        result = prime * result + ((getVendorId() == null) ? 0 : getVendorId().hashCode());
        result = prime * result + ((getWorkStartTime() == null) ? 0 : getWorkStartTime().hashCode());
        result = prime * result + ((getWorkEndTime() == null) ? 0 : getWorkEndTime().hashCode());
        result = prime * result + ((getTruckId() == null) ? 0 : getTruckId().hashCode());
        result = prime * result + ((getDriverId() == null) ? 0 : getDriverId().hashCode());
        result = prime * result + ((getWorkStatus() == null) ? 0 : getWorkStatus().hashCode());
        result = prime * result + ((getDeliveryStatus() == null) ? 0 : getDeliveryStatus().hashCode());
        result = prime * result + ((getFailureReason() == null) ? 0 : getFailureReason().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", calendarId=").append(calendarId);
        sb.append(", taskId=").append(taskId);
        sb.append(", timelineId=").append(timelineId);
        sb.append(", waybillId=").append(waybillId);
        sb.append(", vendorId=").append(vendorId);
        sb.append(", workStartTime=").append(workStartTime);
        sb.append(", workEndTime=").append(workEndTime);
        sb.append(", truckId=").append(truckId);
        sb.append(", driverId=").append(driverId);
        sb.append(", workStatus=").append(workStatus);
        sb.append(", deliveryStatus=").append(deliveryStatus);
        sb.append(", failureReason=").append(failureReason);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        calendarId,
        taskId,
        timelineId,
        waybillId,
        vendorId,
        workStartTime,
        workEndTime,
        truckId,
        driverId,
        workStatus,
        deliveryStatus,
        failureReason;

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