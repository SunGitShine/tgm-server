package com.juma.tgm.task.domain;

import java.io.Serializable;

public class TaskFixedDelivery implements Serializable {
    private Integer fixedId;

    private Integer taskId;

    private String linkMan;

    private String linkManTel;

    private String addressName;

    private String addressDetail;

    private String coordinates;

    private static final long serialVersionUID = 1L;

    public Integer getFixedId() {
        return fixedId;
    }

    public void setFixedId(Integer fixedId) {
        this.fixedId = fixedId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan == null ? null : linkMan.trim();
    }

    public String getLinkManTel() {
        return linkManTel;
    }

    public void setLinkManTel(String linkManTel) {
        this.linkManTel = linkManTel == null ? null : linkManTel.trim();
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName == null ? null : addressName.trim();
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail == null ? null : addressDetail.trim();
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates == null ? null : coordinates.trim();
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
        TaskFixedDelivery other = (TaskFixedDelivery) that;
        return (this.getFixedId() == null ? other.getFixedId() == null : this.getFixedId().equals(other.getFixedId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getLinkMan() == null ? other.getLinkMan() == null : this.getLinkMan().equals(other.getLinkMan()))
            && (this.getLinkManTel() == null ? other.getLinkManTel() == null : this.getLinkManTel().equals(other.getLinkManTel()))
            && (this.getAddressName() == null ? other.getAddressName() == null : this.getAddressName().equals(other.getAddressName()))
            && (this.getAddressDetail() == null ? other.getAddressDetail() == null : this.getAddressDetail().equals(other.getAddressDetail()))
            && (this.getCoordinates() == null ? other.getCoordinates() == null : this.getCoordinates().equals(other.getCoordinates()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFixedId() == null) ? 0 : getFixedId().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getLinkMan() == null) ? 0 : getLinkMan().hashCode());
        result = prime * result + ((getLinkManTel() == null) ? 0 : getLinkManTel().hashCode());
        result = prime * result + ((getAddressName() == null) ? 0 : getAddressName().hashCode());
        result = prime * result + ((getAddressDetail() == null) ? 0 : getAddressDetail().hashCode());
        result = prime * result + ((getCoordinates() == null) ? 0 : getCoordinates().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fixedId=").append(fixedId);
        sb.append(", taskId=").append(taskId);
        sb.append(", linkMan=").append(linkMan);
        sb.append(", linkManTel=").append(linkManTel);
        sb.append(", addressName=").append(addressName);
        sb.append(", addressDetail=").append(addressDetail);
        sb.append(", coordinates=").append(coordinates);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        fixedId,
        taskId,
        linkMan,
        linkManTel,
        addressName,
        addressDetail,
        coordinates;

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