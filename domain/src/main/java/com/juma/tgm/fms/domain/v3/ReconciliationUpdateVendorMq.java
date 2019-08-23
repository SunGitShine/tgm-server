package com.juma.tgm.fms.domain.v3;

import java.io.Serializable;
import java.util.Date;

public class ReconciliationUpdateVendorMq implements Serializable {
    private Integer mqId;

    private Integer oldVendorId;

    private String oldVendorName;

    private Integer vendorId;

    private String vendorName;

    private String excuteResult;

    private Date createTime;

    private String mqData;

    private static final long serialVersionUID = 1L;

    public Integer getMqId() {
        return mqId;
    }

    public void setMqId(Integer mqId) {
        this.mqId = mqId;
    }

    public Integer getOldVendorId() {
        return oldVendorId;
    }

    public void setOldVendorId(Integer oldVendorId) {
        this.oldVendorId = oldVendorId;
    }

    public String getOldVendorName() {
        return oldVendorName;
    }

    public void setOldVendorName(String oldVendorName) {
        this.oldVendorName = oldVendorName == null ? null : oldVendorName.trim();
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName == null ? null : vendorName.trim();
    }

    public String getExcuteResult() {
        return excuteResult;
    }

    public void setExcuteResult(String excuteResult) {
        this.excuteResult = excuteResult == null ? null : excuteResult.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMqData() {
        return mqData;
    }

    public void setMqData(String mqData) {
        this.mqData = mqData == null ? null : mqData.trim();
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
        ReconciliationUpdateVendorMq other = (ReconciliationUpdateVendorMq) that;
        return (this.getMqId() == null ? other.getMqId() == null : this.getMqId().equals(other.getMqId()))
            && (this.getOldVendorId() == null ? other.getOldVendorId() == null : this.getOldVendorId().equals(other.getOldVendorId()))
            && (this.getOldVendorName() == null ? other.getOldVendorName() == null : this.getOldVendorName().equals(other.getOldVendorName()))
            && (this.getVendorId() == null ? other.getVendorId() == null : this.getVendorId().equals(other.getVendorId()))
            && (this.getVendorName() == null ? other.getVendorName() == null : this.getVendorName().equals(other.getVendorName()))
            && (this.getExcuteResult() == null ? other.getExcuteResult() == null : this.getExcuteResult().equals(other.getExcuteResult()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getMqData() == null ? other.getMqData() == null : this.getMqData().equals(other.getMqData()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMqId() == null) ? 0 : getMqId().hashCode());
        result = prime * result + ((getOldVendorId() == null) ? 0 : getOldVendorId().hashCode());
        result = prime * result + ((getOldVendorName() == null) ? 0 : getOldVendorName().hashCode());
        result = prime * result + ((getVendorId() == null) ? 0 : getVendorId().hashCode());
        result = prime * result + ((getVendorName() == null) ? 0 : getVendorName().hashCode());
        result = prime * result + ((getExcuteResult() == null) ? 0 : getExcuteResult().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getMqData() == null) ? 0 : getMqData().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mqId=").append(mqId);
        sb.append(", oldVendorId=").append(oldVendorId);
        sb.append(", oldVendorName=").append(oldVendorName);
        sb.append(", vendorId=").append(vendorId);
        sb.append(", vendorName=").append(vendorName);
        sb.append(", excuteResult=").append(excuteResult);
        sb.append(", createTime=").append(createTime);
        sb.append(", mqData=").append(mqData);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        mqId,
        oldVendorId,
        oldVendorName,
        vendorId,
        vendorName,
        excuteResult,
        createTime,
        mqData;

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