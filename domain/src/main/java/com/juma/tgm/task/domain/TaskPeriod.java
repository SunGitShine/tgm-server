package com.juma.tgm.task.domain;

import java.io.Serializable;

public class TaskPeriod implements Serializable {
    private Integer periodId;

    private Integer taskId;

    private Boolean isStandardTime;

    private String deliveryPeriod;

    private String deliveryPeriodTime;

    private static final long serialVersionUID = 1L;

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Boolean getIsStandardTime() {
        return isStandardTime;
    }

    public void setIsStandardTime(Boolean isStandardTime) {
        this.isStandardTime = isStandardTime;
    }

    public String getDeliveryPeriod() {
        return deliveryPeriod;
    }

    public void setDeliveryPeriod(String deliveryPeriod) {
        this.deliveryPeriod = deliveryPeriod == null ? null : deliveryPeriod.trim();
    }

    public String getDeliveryPeriodTime() {
        return deliveryPeriodTime;
    }

    public void setDeliveryPeriodTime(String deliveryPeriodTime) {
        this.deliveryPeriodTime = deliveryPeriodTime == null ? null : deliveryPeriodTime.trim();
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
        TaskPeriod other = (TaskPeriod) that;
        return (this.getPeriodId() == null ? other.getPeriodId() == null : this.getPeriodId().equals(other.getPeriodId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getIsStandardTime() == null ? other.getIsStandardTime() == null : this.getIsStandardTime().equals(other.getIsStandardTime()))
            && (this.getDeliveryPeriod() == null ? other.getDeliveryPeriod() == null : this.getDeliveryPeriod().equals(other.getDeliveryPeriod()))
            && (this.getDeliveryPeriodTime() == null ? other.getDeliveryPeriodTime() == null : this.getDeliveryPeriodTime().equals(other.getDeliveryPeriodTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPeriodId() == null) ? 0 : getPeriodId().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getIsStandardTime() == null) ? 0 : getIsStandardTime().hashCode());
        result = prime * result + ((getDeliveryPeriod() == null) ? 0 : getDeliveryPeriod().hashCode());
        result = prime * result + ((getDeliveryPeriodTime() == null) ? 0 : getDeliveryPeriodTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", periodId=").append(periodId);
        sb.append(", taskId=").append(taskId);
        sb.append(", isStandardTime=").append(isStandardTime);
        sb.append(", deliveryPeriod=").append(deliveryPeriod);
        sb.append(", deliveryPeriodTime=").append(deliveryPeriodTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        periodId,
        taskId,
        isStandardTime,
        deliveryPeriod,
        deliveryPeriodTime;

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