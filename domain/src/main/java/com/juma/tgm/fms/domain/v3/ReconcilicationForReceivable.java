package com.juma.tgm.fms.domain.v3;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReconcilicationForReceivable implements Serializable {
    private Integer reconcilicationId;

    private Integer tenantId;

    private String tenantCode;

    private String areaCode;

    private String reconcilicationNo;

    private Integer customerId;

    private String customerName;

    private Integer projectId;

    private String projectName;

    private BigDecimal receivableWithTax;

    private BigDecimal receivableWithoutTax;

    private String processInstanceId;

    private Date submitTime;

    private Integer submitUserId;

    private Integer approvalStatus;

    private Integer receiveStatus;

    private Integer invoiceStatus;

    private Date createTime;

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

    public BigDecimal getReceivableWithTax() {
        return receivableWithTax;
    }

    public void setReceivableWithTax(BigDecimal receivableWithTax) {
        this.receivableWithTax = receivableWithTax;
    }

    public BigDecimal getReceivableWithoutTax() {
        return receivableWithoutTax;
    }

    public void setReceivableWithoutTax(BigDecimal receivableWithoutTax) {
        this.receivableWithoutTax = receivableWithoutTax;
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

    public Integer getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(Integer receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
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
        ReconcilicationForReceivable other = (ReconcilicationForReceivable) that;
        return (this.getReconcilicationId() == null ? other.getReconcilicationId() == null : this.getReconcilicationId().equals(other.getReconcilicationId()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getTenantCode() == null ? other.getTenantCode() == null : this.getTenantCode().equals(other.getTenantCode()))
            && (this.getAreaCode() == null ? other.getAreaCode() == null : this.getAreaCode().equals(other.getAreaCode()))
            && (this.getReconcilicationNo() == null ? other.getReconcilicationNo() == null : this.getReconcilicationNo().equals(other.getReconcilicationNo()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getProjectName() == null ? other.getProjectName() == null : this.getProjectName().equals(other.getProjectName()))
            && (this.getReceivableWithTax() == null ? other.getReceivableWithTax() == null : this.getReceivableWithTax().equals(other.getReceivableWithTax()))
            && (this.getReceivableWithoutTax() == null ? other.getReceivableWithoutTax() == null : this.getReceivableWithoutTax().equals(other.getReceivableWithoutTax()))
            && (this.getProcessInstanceId() == null ? other.getProcessInstanceId() == null : this.getProcessInstanceId().equals(other.getProcessInstanceId()))
            && (this.getSubmitTime() == null ? other.getSubmitTime() == null : this.getSubmitTime().equals(other.getSubmitTime()))
            && (this.getSubmitUserId() == null ? other.getSubmitUserId() == null : this.getSubmitUserId().equals(other.getSubmitUserId()))
            && (this.getApprovalStatus() == null ? other.getApprovalStatus() == null : this.getApprovalStatus().equals(other.getApprovalStatus()))
            && (this.getReceiveStatus() == null ? other.getReceiveStatus() == null : this.getReceiveStatus().equals(other.getReceiveStatus()))
            && (this.getInvoiceStatus() == null ? other.getInvoiceStatus() == null : this.getInvoiceStatus().equals(other.getInvoiceStatus()))
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
        result = prime * result + ((getReconcilicationNo() == null) ? 0 : getReconcilicationNo().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getProjectName() == null) ? 0 : getProjectName().hashCode());
        result = prime * result + ((getReceivableWithTax() == null) ? 0 : getReceivableWithTax().hashCode());
        result = prime * result + ((getReceivableWithoutTax() == null) ? 0 : getReceivableWithoutTax().hashCode());
        result = prime * result + ((getProcessInstanceId() == null) ? 0 : getProcessInstanceId().hashCode());
        result = prime * result + ((getSubmitTime() == null) ? 0 : getSubmitTime().hashCode());
        result = prime * result + ((getSubmitUserId() == null) ? 0 : getSubmitUserId().hashCode());
        result = prime * result + ((getApprovalStatus() == null) ? 0 : getApprovalStatus().hashCode());
        result = prime * result + ((getReceiveStatus() == null) ? 0 : getReceiveStatus().hashCode());
        result = prime * result + ((getInvoiceStatus() == null) ? 0 : getInvoiceStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        return result;
    }
}