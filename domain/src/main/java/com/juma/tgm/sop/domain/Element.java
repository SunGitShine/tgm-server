package com.juma.tgm.sop.domain;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Element implements Serializable {
    private Integer elementId;

    private Integer stepId;

    private String elementKey;

    private String elementName;

    private String elementType;

    @JSONField(serialize = false)
    private Boolean isDelete;

    @JSONField(serialize = false)
    private Date createTime;

    @JSONField(serialize = false)
    private Integer createUserId;

    @JSONField(serialize = false)
    private Integer lastUpdateUserId;

    @JSONField(serialize = false)
    private Date lastUpdateTime;

    private static final long serialVersionUID = 1L;

    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public String getElementKey() {
        return elementKey;
    }

    public void setElementKey(String elementKey) {
        this.elementKey = elementKey == null ? null : elementKey.trim();
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName == null ? null : elementName.trim();
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType == null ? null : elementType.trim();
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
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
        Element other = (Element) that;
        return (this.getElementId() == null ? other.getElementId() == null
                : this.getElementId().equals(other.getElementId()))
                && (this.getStepId() == null ? other.getStepId() == null : this.getStepId().equals(other.getStepId()))
                && (this.getElementKey() == null ? other.getElementKey() == null
                        : this.getElementKey().equals(other.getElementKey()))
                && (this.getElementName() == null ? other.getElementName() == null
                        : this.getElementName().equals(other.getElementName()))
                && (this.getElementType() == null ? other.getElementType() == null
                        : this.getElementType().equals(other.getElementType()))
                && (this.getIsDelete() == null ? other.getIsDelete() == null
                        : this.getIsDelete().equals(other.getIsDelete()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null
                        : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getCreateUserId() == null ? other.getCreateUserId() == null
                        : this.getCreateUserId().equals(other.getCreateUserId()))
                && (this.getLastUpdateUserId() == null ? other.getLastUpdateUserId() == null
                        : this.getLastUpdateUserId().equals(other.getLastUpdateUserId()))
                && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null
                        : this.getLastUpdateTime().equals(other.getLastUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getElementId() == null) ? 0 : getElementId().hashCode());
        result = prime * result + ((getStepId() == null) ? 0 : getStepId().hashCode());
        result = prime * result + ((getElementKey() == null) ? 0 : getElementKey().hashCode());
        result = prime * result + ((getElementName() == null) ? 0 : getElementName().hashCode());
        result = prime * result + ((getElementType() == null) ? 0 : getElementType().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
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
        sb.append(", elementId=").append(elementId);
        sb.append(", stepId=").append(stepId);
        sb.append(", elementKey=").append(elementKey);
        sb.append(", elementName=").append(elementName);
        sb.append(", elementType=").append(elementType);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", lastUpdateUserId=").append(lastUpdateUserId);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}