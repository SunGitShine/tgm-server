package com.juma.tgm.task.domain;

import java.io.Serializable;

public class TaskNotfixedDelivery implements Serializable {
    private Integer notfixedId;

    private Integer taskId;

    private Integer minStops;

    private Integer maxStops;

    private String addressDetail;

    private static final long serialVersionUID = 1L;

    public Integer getNotfixedId() {
        return notfixedId;
    }

    public void setNotfixedId(Integer notfixedId) {
        this.notfixedId = notfixedId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getMinStops() {
        return minStops;
    }

    public void setMinStops(Integer minStops) {
        this.minStops = minStops;
    }

    public Integer getMaxStops() {
        return maxStops;
    }

    public void setMaxStops(Integer maxStops) {
        this.maxStops = maxStops;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail == null ? null : addressDetail.trim();
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
        TaskNotfixedDelivery other = (TaskNotfixedDelivery) that;
        return (this.getNotfixedId() == null ? other.getNotfixedId() == null : this.getNotfixedId().equals(other.getNotfixedId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getMinStops() == null ? other.getMinStops() == null : this.getMinStops().equals(other.getMinStops()))
            && (this.getMaxStops() == null ? other.getMaxStops() == null : this.getMaxStops().equals(other.getMaxStops()))
            && (this.getAddressDetail() == null ? other.getAddressDetail() == null : this.getAddressDetail().equals(other.getAddressDetail()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getNotfixedId() == null) ? 0 : getNotfixedId().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getMinStops() == null) ? 0 : getMinStops().hashCode());
        result = prime * result + ((getMaxStops() == null) ? 0 : getMaxStops().hashCode());
        result = prime * result + ((getAddressDetail() == null) ? 0 : getAddressDetail().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", notfixedId=").append(notfixedId);
        sb.append(", taskId=").append(taskId);
        sb.append(", minStops=").append(minStops);
        sb.append(", maxStops=").append(maxStops);
        sb.append(", addressDetail=").append(addressDetail);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        notfixedId,
        taskId,
        minStops,
        maxStops,
        addressDetail;

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