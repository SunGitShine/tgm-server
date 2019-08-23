package com.juma.tgm.fms.domain.v3;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel("调整主表")
public class AdjustForMaster implements Serializable {
    private Integer adjustId;

    @ApiModelProperty("调整编号")
    private String adjustNo;

    private Integer tenantId;

    private String tenantCode;

    @ApiModelProperty("运单业务范围")
    private String areaCode;

    @ApiModelProperty("对账单id")
    private String reconcilicationNo;

    @ApiModelProperty("调整前含税金额")
    private BigDecimal beforeAdjustAmount;

    @ApiModelProperty("调整前含税金额-包含自营承运商运单")
    private BigDecimal beforeAdjustWithselfAmount;

    @ApiModelProperty("调整单总调整金额")
    private BigDecimal adjustAmount;

    @ApiModelProperty("调整单绝对调整总金额")
    private BigDecimal adjustAbsAmount;

    @ApiModelProperty("另外一侧的调整前金额")
    private BigDecimal otherSideAmount;

    @ApiModelProperty("另外一侧的调整前金额-包含自营承运商运单")
    private BigDecimal otherSideWithselfAmount;

    @ApiModelProperty("审核通过时间")
    private Date approvalTime;

    @ApiModelProperty("审核状态 0,未提1,审核中2,被驳回3,已通过")
    private Integer approvalStatus;

    @ApiModelProperty("审核工作流id")
    private String processInstanceId;

    @ApiModelProperty("调整阶段 1：对账前  2：对账后")
    private Integer adjustType;

    @ApiModelProperty("调整主体  1：客户  2：承运商")
    private Integer adjustForWho;

    private String adjustForReason;

    private String createUserName;

    private Integer createUserId;

    private Date createTime;

    @ApiModelProperty("标记删除字段")
    private Integer isDelete;

    private static final long serialVersionUID = 1L;

    public Integer getAdjustId() {
        return adjustId;
    }

    public void setAdjustId(Integer adjustId) {
        this.adjustId = adjustId;
    }

    public String getAdjustNo() {
        return adjustNo;
    }

    public void setAdjustNo(String adjustNo) {
        this.adjustNo = adjustNo == null ? null : adjustNo.trim();
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode == null ? null : tenantCode.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getReconcilicationNo() {
        return reconcilicationNo;
    }

    public void setReconcilicationNo(String reconcilicationNo) {
        this.reconcilicationNo = reconcilicationNo == null ? null : reconcilicationNo.trim();
    }

    public BigDecimal getBeforeAdjustAmount() {
        return beforeAdjustAmount;
    }

    public void setBeforeAdjustAmount(BigDecimal beforeAdjustAmount) {
        this.beforeAdjustAmount = beforeAdjustAmount;
    }

    public BigDecimal getBeforeAdjustWithselfAmount() {
        return beforeAdjustWithselfAmount;
    }

    public void setBeforeAdjustWithselfAmount(BigDecimal beforeAdjustWithselfAmount) {
        this.beforeAdjustWithselfAmount = beforeAdjustWithselfAmount;
    }

    public BigDecimal getAdjustAmount() {
        return adjustAmount;
    }

    public void setAdjustAmount(BigDecimal adjustAmount) {
        this.adjustAmount = adjustAmount;
    }

    public BigDecimal getAdjustAbsAmount() {
        return adjustAbsAmount;
    }

    public void setAdjustAbsAmount(BigDecimal adjustAbsAmount) {
        this.adjustAbsAmount = adjustAbsAmount;
    }

    public BigDecimal getOtherSideAmount() {
        return otherSideAmount;
    }

    public void setOtherSideAmount(BigDecimal otherSideAmount) {
        this.otherSideAmount = otherSideAmount;
    }

    public BigDecimal getOtherSideWithselfAmount() {
        return otherSideWithselfAmount;
    }

    public void setOtherSideWithselfAmount(BigDecimal otherSideWithselfAmount) {
        this.otherSideWithselfAmount = otherSideWithselfAmount;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId == null ? null : processInstanceId.trim();
    }

    public Integer getAdjustType() {
        return adjustType;
    }

    public void setAdjustType(Integer adjustType) {
        this.adjustType = adjustType;
    }

    public Integer getAdjustForWho() {
        return adjustForWho;
    }

    public void setAdjustForWho(Integer adjustForWho) {
        this.adjustForWho = adjustForWho;
    }

    public String getAdjustForReason() {
        return adjustForReason;
    }

    public void setAdjustForReason(String adjustForReason) {
        this.adjustForReason = adjustForReason == null ? null : adjustForReason.trim();
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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
        AdjustForMaster other = (AdjustForMaster) that;
        return (this.getAdjustId() == null ? other.getAdjustId() == null : this.getAdjustId().equals(other.getAdjustId()))
            && (this.getAdjustNo() == null ? other.getAdjustNo() == null : this.getAdjustNo().equals(other.getAdjustNo()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getTenantCode() == null ? other.getTenantCode() == null : this.getTenantCode().equals(other.getTenantCode()))
            && (this.getAreaCode() == null ? other.getAreaCode() == null : this.getAreaCode().equals(other.getAreaCode()))
            && (this.getReconcilicationNo() == null ? other.getReconcilicationNo() == null : this.getReconcilicationNo().equals(other.getReconcilicationNo()))
            && (this.getBeforeAdjustAmount() == null ? other.getBeforeAdjustAmount() == null : this.getBeforeAdjustAmount().equals(other.getBeforeAdjustAmount()))
            && (this.getBeforeAdjustWithselfAmount() == null ? other.getBeforeAdjustWithselfAmount() == null : this.getBeforeAdjustWithselfAmount().equals(other.getBeforeAdjustWithselfAmount()))
            && (this.getAdjustAmount() == null ? other.getAdjustAmount() == null : this.getAdjustAmount().equals(other.getAdjustAmount()))
            && (this.getAdjustAbsAmount() == null ? other.getAdjustAbsAmount() == null : this.getAdjustAbsAmount().equals(other.getAdjustAbsAmount()))
            && (this.getOtherSideAmount() == null ? other.getOtherSideAmount() == null : this.getOtherSideAmount().equals(other.getOtherSideAmount()))
            && (this.getOtherSideWithselfAmount() == null ? other.getOtherSideWithselfAmount() == null : this.getOtherSideWithselfAmount().equals(other.getOtherSideWithselfAmount()))
            && (this.getApprovalTime() == null ? other.getApprovalTime() == null : this.getApprovalTime().equals(other.getApprovalTime()))
            && (this.getApprovalStatus() == null ? other.getApprovalStatus() == null : this.getApprovalStatus().equals(other.getApprovalStatus()))
            && (this.getProcessInstanceId() == null ? other.getProcessInstanceId() == null : this.getProcessInstanceId().equals(other.getProcessInstanceId()))
            && (this.getAdjustType() == null ? other.getAdjustType() == null : this.getAdjustType().equals(other.getAdjustType()))
            && (this.getAdjustForWho() == null ? other.getAdjustForWho() == null : this.getAdjustForWho().equals(other.getAdjustForWho()))
            && (this.getAdjustForReason() == null ? other.getAdjustForReason() == null : this.getAdjustForReason().equals(other.getAdjustForReason()))
            && (this.getCreateUserName() == null ? other.getCreateUserName() == null : this.getCreateUserName().equals(other.getCreateUserName()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAdjustId() == null) ? 0 : getAdjustId().hashCode());
        result = prime * result + ((getAdjustNo() == null) ? 0 : getAdjustNo().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getTenantCode() == null) ? 0 : getTenantCode().hashCode());
        result = prime * result + ((getAreaCode() == null) ? 0 : getAreaCode().hashCode());
        result = prime * result + ((getReconcilicationNo() == null) ? 0 : getReconcilicationNo().hashCode());
        result = prime * result + ((getBeforeAdjustAmount() == null) ? 0 : getBeforeAdjustAmount().hashCode());
        result = prime * result + ((getBeforeAdjustWithselfAmount() == null) ? 0 : getBeforeAdjustWithselfAmount().hashCode());
        result = prime * result + ((getAdjustAmount() == null) ? 0 : getAdjustAmount().hashCode());
        result = prime * result + ((getAdjustAbsAmount() == null) ? 0 : getAdjustAbsAmount().hashCode());
        result = prime * result + ((getOtherSideAmount() == null) ? 0 : getOtherSideAmount().hashCode());
        result = prime * result + ((getOtherSideWithselfAmount() == null) ? 0 : getOtherSideWithselfAmount().hashCode());
        result = prime * result + ((getApprovalTime() == null) ? 0 : getApprovalTime().hashCode());
        result = prime * result + ((getApprovalStatus() == null) ? 0 : getApprovalStatus().hashCode());
        result = prime * result + ((getProcessInstanceId() == null) ? 0 : getProcessInstanceId().hashCode());
        result = prime * result + ((getAdjustType() == null) ? 0 : getAdjustType().hashCode());
        result = prime * result + ((getAdjustForWho() == null) ? 0 : getAdjustForWho().hashCode());
        result = prime * result + ((getAdjustForReason() == null) ? 0 : getAdjustForReason().hashCode());
        result = prime * result + ((getCreateUserName() == null) ? 0 : getCreateUserName().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", adjustId=").append(adjustId);
        sb.append(", adjustNo=").append(adjustNo);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", tenantCode=").append(tenantCode);
        sb.append(", areaCode=").append(areaCode);
        sb.append(", reconcilicationNo=").append(reconcilicationNo);
        sb.append(", beforeAdjustAmount=").append(beforeAdjustAmount);
        sb.append(", beforeAdjustWithselfAmount=").append(beforeAdjustWithselfAmount);
        sb.append(", adjustAmount=").append(adjustAmount);
        sb.append(", adjustAbsAmount=").append(adjustAbsAmount);
        sb.append(", otherSideAmount=").append(otherSideAmount);
        sb.append(", otherSideWithselfAmount=").append(otherSideWithselfAmount);
        sb.append(", approvalTime=").append(approvalTime);
        sb.append(", approvalStatus=").append(approvalStatus);
        sb.append(", processInstanceId=").append(processInstanceId);
        sb.append(", adjustType=").append(adjustType);
        sb.append(", adjustForWho=").append(adjustForWho);
        sb.append(", adjustForReason=").append(adjustForReason);
        sb.append(", createUserName=").append(createUserName);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        adjustId,
        adjustNo,
        tenantId,
        tenantCode,
        areaCode,
        reconcilicationNo,
        beforeAdjustAmount,
        beforeAdjustWithselfAmount,
        adjustAmount,
        adjustAbsAmount,
        otherSideAmount,
        otherSideWithselfAmount,
        approvalTime,
        approvalStatus,
        processInstanceId,
        adjustType,
        adjustForWho,
        adjustForReason,
        createUserName,
        createUserId,
        createTime,
        isDelete;

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