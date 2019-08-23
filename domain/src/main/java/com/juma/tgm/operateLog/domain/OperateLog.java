package com.juma.tgm.operateLog.domain;

import java.io.Serializable;
import java.util.Date;

public class OperateLog implements Serializable {
    private Integer operateLogId;

    private Byte logSign;

    private Integer relationTableId;

    private Byte operateType;

    private Byte operateApplicatoin;

    private Integer createUserId;

    private Date createTime;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getOperateLogId() {
        return operateLogId;
    }

    public void setOperateLogId(Integer operateLogId) {
        this.operateLogId = operateLogId;
    }

    public Byte getLogSign() {
        return logSign;
    }

    public void setLogSign(Byte logSign) {
        this.logSign = logSign;
    }

    public Integer getRelationTableId() {
        return relationTableId;
    }

    public void setRelationTableId(Integer relationTableId) {
        this.relationTableId = relationTableId;
    }

    public Byte getOperateType() {
        return operateType;
    }

    public void setOperateType(Byte operateType) {
        this.operateType = operateType;
    }

    public Byte getOperateApplicatoin() {
        return operateApplicatoin;
    }

    public void setOperateApplicatoin(Byte operateApplicatoin) {
        this.operateApplicatoin = operateApplicatoin;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        OperateLog other = (OperateLog) that;
        return (this.getOperateLogId() == null ? other.getOperateLogId() == null : this.getOperateLogId().equals(other.getOperateLogId()))
            && (this.getLogSign() == null ? other.getLogSign() == null : this.getLogSign().equals(other.getLogSign()))
            && (this.getRelationTableId() == null ? other.getRelationTableId() == null : this.getRelationTableId().equals(other.getRelationTableId()))
            && (this.getOperateType() == null ? other.getOperateType() == null : this.getOperateType().equals(other.getOperateType()))
            && (this.getOperateApplicatoin() == null ? other.getOperateApplicatoin() == null : this.getOperateApplicatoin().equals(other.getOperateApplicatoin()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOperateLogId() == null) ? 0 : getOperateLogId().hashCode());
        result = prime * result + ((getLogSign() == null) ? 0 : getLogSign().hashCode());
        result = prime * result + ((getRelationTableId() == null) ? 0 : getRelationTableId().hashCode());
        result = prime * result + ((getOperateType() == null) ? 0 : getOperateType().hashCode());
        result = prime * result + ((getOperateApplicatoin() == null) ? 0 : getOperateApplicatoin().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }
}