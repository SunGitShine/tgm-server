package com.juma.tgm.fms.domain.v3;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



@ApiModel("应付对账")
public class ReconcilicationForPayable implements Serializable {
    @ApiModelProperty("")
    private Integer reconcilicationId;

    @ApiModelProperty("")
    private Integer tenantId;

    @ApiModelProperty("")
    private String tenantCode;

    @ApiModelProperty("客户业务区域")
    private String areaCode;

    @ApiModelProperty("")
    private Integer departmentId;

    @ApiModelProperty("运作方统一社会信用代码")
    private String payToCompanyCreditCode;

    @ApiModelProperty("对账单号")
    private String reconcilicationNo;

    @ApiModelProperty("")
    private Integer customerId;

    @ApiModelProperty("")
    private String customerName;

    @ApiModelProperty("")
    private Integer projectId;

    @ApiModelProperty("")
    private String projectName;

    @ApiModelProperty("应付（其实是税后）")
    private BigDecimal payableWithTax;

    @ApiModelProperty("司机搬运费")
    private BigDecimal driverTransportFee;

    @ApiModelProperty("小工搬运费")
    private BigDecimal temporaryTransportFee;

    @ApiModelProperty("工作流ID")
    private String processInstanceId;

    @ApiModelProperty("提交审核时间")
    private Date submitTime;

    @ApiModelProperty("提交人")
    private Integer submitUserId;

    @ApiModelProperty("审核状态 0,\"未提1,\"审核中\"2,\"被驳回\"3,\"已通过\"")
    private Integer approvalStatus;

    @ApiModelProperty("")
    private Date createTime;

    @ApiModelProperty("")
    private Integer createUserId;

    private static final long serialVersionUID = 1L;

    public Integer getReconcilicationId() {
        return reconcilicationId;
    }

    public void setReconcilicationId(Integer reconcilicationId) {
        this.reconcilicationId = reconcilicationId;
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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getPayToCompanyCreditCode() {
        return payToCompanyCreditCode;
    }

    public void setPayToCompanyCreditCode(String payToCompanyCreditCode) {
        this.payToCompanyCreditCode = payToCompanyCreditCode == null ? null : payToCompanyCreditCode.trim();
    }

    public String getReconcilicationNo() {
        return reconcilicationNo;
    }

    public void setReconcilicationNo(String reconcilicationNo) {
        this.reconcilicationNo = reconcilicationNo == null ? null : reconcilicationNo.trim();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public BigDecimal getPayableWithTax() {
        return payableWithTax;
    }

    public void setPayableWithTax(BigDecimal payableWithTax) {
        this.payableWithTax = payableWithTax;
    }

    public BigDecimal getDriverTransportFee() {
        return driverTransportFee;
    }

    public void setDriverTransportFee(BigDecimal driverTransportFee) {
        this.driverTransportFee = driverTransportFee;
    }

    public BigDecimal getTemporaryTransportFee() {
        return temporaryTransportFee;
    }

    public void setTemporaryTransportFee(BigDecimal temporaryTransportFee) {
        this.temporaryTransportFee = temporaryTransportFee;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId == null ? null : processInstanceId.trim();
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getSubmitUserId() {
        return submitUserId;
    }

    public void setSubmitUserId(Integer submitUserId) {
        this.submitUserId = submitUserId;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
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
        ReconcilicationForPayable other = (ReconcilicationForPayable) that;
        return (this.getReconcilicationId() == null ? other.getReconcilicationId() == null : this.getReconcilicationId().equals(other.getReconcilicationId()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getTenantCode() == null ? other.getTenantCode() == null : this.getTenantCode().equals(other.getTenantCode()))
            && (this.getAreaCode() == null ? other.getAreaCode() == null : this.getAreaCode().equals(other.getAreaCode()))
            && (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()))
            && (this.getPayToCompanyCreditCode() == null ? other.getPayToCompanyCreditCode() == null : this.getPayToCompanyCreditCode().equals(other.getPayToCompanyCreditCode()))
            && (this.getReconcilicationNo() == null ? other.getReconcilicationNo() == null : this.getReconcilicationNo().equals(other.getReconcilicationNo()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getProjectName() == null ? other.getProjectName() == null : this.getProjectName().equals(other.getProjectName()))
            && (this.getPayableWithTax() == null ? other.getPayableWithTax() == null : this.getPayableWithTax().equals(other.getPayableWithTax()))
            && (this.getDriverTransportFee() == null ? other.getDriverTransportFee() == null : this.getDriverTransportFee().equals(other.getDriverTransportFee()))
            && (this.getTemporaryTransportFee() == null ? other.getTemporaryTransportFee() == null : this.getTemporaryTransportFee().equals(other.getTemporaryTransportFee()))
            && (this.getProcessInstanceId() == null ? other.getProcessInstanceId() == null : this.getProcessInstanceId().equals(other.getProcessInstanceId()))
            && (this.getSubmitTime() == null ? other.getSubmitTime() == null : this.getSubmitTime().equals(other.getSubmitTime()))
            && (this.getSubmitUserId() == null ? other.getSubmitUserId() == null : this.getSubmitUserId().equals(other.getSubmitUserId()))
            && (this.getApprovalStatus() == null ? other.getApprovalStatus() == null : this.getApprovalStatus().equals(other.getApprovalStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getReconcilicationId() == null) ? 0 : getReconcilicationId().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getTenantCode() == null) ? 0 : getTenantCode().hashCode());
        result = prime * result + ((getAreaCode() == null) ? 0 : getAreaCode().hashCode());
        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
        result = prime * result + ((getPayToCompanyCreditCode() == null) ? 0 : getPayToCompanyCreditCode().hashCode());
        result = prime * result + ((getReconcilicationNo() == null) ? 0 : getReconcilicationNo().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getProjectName() == null) ? 0 : getProjectName().hashCode());
        result = prime * result + ((getPayableWithTax() == null) ? 0 : getPayableWithTax().hashCode());
        result = prime * result + ((getDriverTransportFee() == null) ? 0 : getDriverTransportFee().hashCode());
        result = prime * result + ((getTemporaryTransportFee() == null) ? 0 : getTemporaryTransportFee().hashCode());
        result = prime * result + ((getProcessInstanceId() == null) ? 0 : getProcessInstanceId().hashCode());
        result = prime * result + ((getSubmitTime() == null) ? 0 : getSubmitTime().hashCode());
        result = prime * result + ((getSubmitUserId() == null) ? 0 : getSubmitUserId().hashCode());
        result = prime * result + ((getApprovalStatus() == null) ? 0 : getApprovalStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", reconcilicationId=").append(reconcilicationId);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", tenantCode=").append(tenantCode);
        sb.append(", areaCode=").append(areaCode);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", payToCompanyCreditCode=").append(payToCompanyCreditCode);
        sb.append(", reconcilicationNo=").append(reconcilicationNo);
        sb.append(", customerId=").append(customerId);
        sb.append(", customerName=").append(customerName);
        sb.append(", projectId=").append(projectId);
        sb.append(", projectName=").append(projectName);
        sb.append(", payableWithTax=").append(payableWithTax);
        sb.append(", driverTransportFee=").append(driverTransportFee);
        sb.append(", temporaryTransportFee=").append(temporaryTransportFee);
        sb.append(", processInstanceId=").append(processInstanceId);
        sb.append(", submitTime=").append(submitTime);
        sb.append(", submitUserId=").append(submitUserId);
        sb.append(", approvalStatus=").append(approvalStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        reconcilicationId,
        tenantId,
        tenantCode,
        areaCode,
        departmentId,
        payToCompanyCreditCode,
        reconcilicationNo,
        customerId,
        customerName,
        projectId,
        projectName,
        payableWithTax,
        driverTransportFee,
        temporaryTransportFee,
        processInstanceId,
        submitTime,
        submitUserId,
        approvalStatus,
        createTime,
        createUserId;

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