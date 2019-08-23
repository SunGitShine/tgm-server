package com.juma.tgm.configure.domain;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class FreightFactor implements Serializable,Comparable<FreightFactor> {
    private Integer freightFactorId;

    private String labelName;

    private String labelInputName;

    private Integer labelInputType;

    private String labelValueType;

    private Boolean required;

    private Integer showOrder;
    
    private Integer requiredMinValue;

    private Integer requiredMaxValue;

    private Byte isEnable;

    private transient  Date createTime;

    private transient  Integer createUserId;

    private transient  Integer lastUpdateUserId;

    private transient  Date lastUpdateTime;
    
    //冗余字段
    @JSONField(name="_checked")
    private Boolean _checked;

    private static final long serialVersionUID = 1L;

    public Integer getFreightFactorId() {
        return freightFactorId;
    }

    public void setFreightFactorId(Integer freightFactorId) {
        this.freightFactorId = freightFactorId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName == null ? null : labelName.trim();
    }

    public String getLabelInputName() {
        return labelInputName;
    }

    public void setLabelInputName(String labelInputName) {
        this.labelInputName = labelInputName == null ? null : labelInputName.trim();
    }

    public Integer getLabelInputType() {
        return labelInputType;
    }

    public void setLabelInputType(Integer labelInputType) {
        this.labelInputType = labelInputType;
    }

    public String getLabelValueType() {
        return labelValueType;
    }

    public void setLabelValueType(String labelValueType) {
        this.labelValueType = labelValueType == null ? null : labelValueType.trim();
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    public Byte getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Byte isEnable) {
        this.isEnable = isEnable;
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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        FreightFactor other = (FreightFactor) obj;
        if (_checked == null) {
            if (other._checked != null) return false;
        } else if (!_checked.equals(other._checked)) return false;
        if (freightFactorId == null) {
            if (other.freightFactorId != null) return false;
        } else if (!freightFactorId.equals(other.freightFactorId)) return false;
        if (isEnable == null) {
            if (other.isEnable != null) return false;
        } else if (!isEnable.equals(other.isEnable)) return false;
        if (labelInputName == null) {
            if (other.labelInputName != null) return false;
        } else if (!labelInputName.equals(other.labelInputName)) return false;
        if (labelInputType == null) {
            if (other.labelInputType != null) return false;
        } else if (!labelInputType.equals(other.labelInputType)) return false;
        if (labelName == null) {
            if (other.labelName != null) return false;
        } else if (!labelName.equals(other.labelName)) return false;
        if (labelValueType == null) {
            if (other.labelValueType != null) return false;
        } else if (!labelValueType.equals(other.labelValueType)) return false;
        if (required == null) {
            if (other.required != null) return false;
        } else if (!required.equals(other.required)) return false;
        if (requiredMaxValue == null) {
            if (other.requiredMaxValue != null) return false;
        } else if (!requiredMaxValue.equals(other.requiredMaxValue)) return false;
        if (requiredMinValue == null) {
            if (other.requiredMinValue != null) return false;
        } else if (!requiredMinValue.equals(other.requiredMinValue)) return false;
        if (showOrder == null) {
            if (other.showOrder != null) return false;
        } else if (!showOrder.equals(other.showOrder)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_checked == null) ? 0 : _checked.hashCode());
        result = prime * result + ((freightFactorId == null) ? 0 : freightFactorId.hashCode());
        result = prime * result + ((isEnable == null) ? 0 : isEnable.hashCode());
        result = prime * result + ((labelInputName == null) ? 0 : labelInputName.hashCode());
        result = prime * result + ((labelInputType == null) ? 0 : labelInputType.hashCode());
        result = prime * result + ((labelName == null) ? 0 : labelName.hashCode());
        result = prime * result + ((labelValueType == null) ? 0 : labelValueType.hashCode());
        result = prime * result + ((required == null) ? 0 : required.hashCode());
        result = prime * result + ((requiredMaxValue == null) ? 0 : requiredMaxValue.hashCode());
        result = prime * result + ((requiredMinValue == null) ? 0 : requiredMinValue.hashCode());
        result = prime * result + ((showOrder == null) ? 0 : showOrder.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", freightFactorId=").append(freightFactorId);
        sb.append(", labelName=").append(labelName);
        sb.append(", labelInputName=").append(labelInputName);
        sb.append(", labelInputType=").append(labelInputType);
        sb.append(", labelValueType=").append(labelValueType);
        sb.append(", required=").append(required);
        sb.append(", showOrder=").append(showOrder);
        sb.append(", isEnable=").append(isEnable);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", lastUpdateUserId=").append(lastUpdateUserId);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Boolean get_checked() {
        return _checked;
    }

    public void set_checked(Boolean _checked) {
        this._checked = _checked;
    }

    @Override
    public int compareTo(FreightFactor o) {
        if(o.getShowOrder() == null || this.getShowOrder() == null) return 1;
        return o.getShowOrder().compareTo(this.getShowOrder());
    }

    public Integer getRequiredMinValue() {
        return requiredMinValue;
    }

    public void setRequiredMinValue(Integer requiredMinValue) {
        this.requiredMinValue = requiredMinValue;
    }

    public Integer getRequiredMaxValue() {
        return requiredMaxValue;
    }

    public void setRequiredMaxValue(Integer requiredMaxValue) {
        this.requiredMaxValue = requiredMaxValue;
    }
}