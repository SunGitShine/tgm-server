package com.juma.tgm.fms.domain.v2;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReconciliationNew implements Serializable {
    private Integer reconciliationId;

    private String reconciliationNo;

    private Integer reconciliationType;

    private Byte reconciliationStatus;

    private Date submitTime;

    private Integer submitter;

    private String processInstanceId;

    private Integer tenantId;

    private String tenantCode;

    private BigDecimal taxRateValue;

    private BigDecimal hasReceiveFreight;

    private Byte receiveStatus;

    private String invoiceNo;

    private Integer projectId;

    private String projectName;

    private Integer customerId;

    private String customerName;

    private BigDecimal customerInitialBeforeTax;

    private BigDecimal customerInitialAfterTax;

    private BigDecimal customerFinalBeforeTax;

    private BigDecimal customerFinalAfterTax;

    private BigDecimal driverInitialBeforeTax;

    private BigDecimal driverInitialAfterTax;

    private BigDecimal driverFinalBeforeTax;

    private BigDecimal driverFinalAfterTax;

    private BigDecimal driverHandlingFee;

    private BigDecimal laborerHandlingFee;

    private String areaCode;

    private Integer createUserId;

    private Date createTime;

    private Integer lastUpdateUserId;

    private Date lastUpdateTime;

    private static final long serialVersionUID = 1L;

    /**
     * 对账单类型
     */
    public enum Type {
        ORDINARY(0, "普通"),// 只有司机的
        ENTRUSTED_TRANSPORT(1, "转运"),// 只有承运商的
        ENTRUSTED_BLEND(2, "混合");// 司机和承运商 两种都有
        private Integer code;
        private String desc;

        Type(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }



        public Integer getReconciliationId() {
        return reconciliationId;
    }

    public void setReconciliationId(Integer reconciliationId) {
        this.reconciliationId = reconciliationId;
    }

    public String getReconciliationNo() {
        return reconciliationNo;
    }

    public void setReconciliationNo(String reconciliationNo) {
        this.reconciliationNo = reconciliationNo == null ? null : reconciliationNo.trim();
    }

    public Integer getReconciliationType() {
        return reconciliationType;
    }

    public void setReconciliationType(Integer reconciliationType) {
        this.reconciliationType = reconciliationType;
    }

    public Byte getReconciliationStatus() {
        return reconciliationStatus;
    }

    public void setReconciliationStatus(Byte reconciliationStatus) {
        this.reconciliationStatus = reconciliationStatus;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getSubmitter() {
        return submitter;
    }

    public void setSubmitter(Integer submitter) {
        this.submitter = submitter;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId == null ? null : processInstanceId.trim();
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

    public BigDecimal getTaxRateValue() {
        return taxRateValue;
    }

    public void setTaxRateValue(BigDecimal taxRateValue) {
        this.taxRateValue = taxRateValue;
    }

    public BigDecimal getHasReceiveFreight() {
        return hasReceiveFreight;
    }

    public void setHasReceiveFreight(BigDecimal hasReceiveFreight) {
        this.hasReceiveFreight = hasReceiveFreight;
    }

    public Byte getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(Byte receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo == null ? null : invoiceNo.trim();
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

    public BigDecimal getCustomerInitialBeforeTax() {
        return customerInitialBeforeTax;
    }

    public void setCustomerInitialBeforeTax(BigDecimal customerInitialBeforeTax) {
        this.customerInitialBeforeTax = customerInitialBeforeTax;
    }

    public BigDecimal getCustomerInitialAfterTax() {
        return customerInitialAfterTax;
    }

    public void setCustomerInitialAfterTax(BigDecimal customerInitialAfterTax) {
        this.customerInitialAfterTax = customerInitialAfterTax;
    }

    public BigDecimal getCustomerFinalBeforeTax() {
        return customerFinalBeforeTax;
    }

    public void setCustomerFinalBeforeTax(BigDecimal customerFinalBeforeTax) {
        this.customerFinalBeforeTax = customerFinalBeforeTax;
    }

    public BigDecimal getCustomerFinalAfterTax() {
        return customerFinalAfterTax;
    }

    public void setCustomerFinalAfterTax(BigDecimal customerFinalAfterTax) {
        this.customerFinalAfterTax = customerFinalAfterTax;
    }

    public BigDecimal getDriverInitialBeforeTax() {
        return driverInitialBeforeTax;
    }

    public void setDriverInitialBeforeTax(BigDecimal driverInitialBeforeTax) {
        this.driverInitialBeforeTax = driverInitialBeforeTax;
    }

    public BigDecimal getDriverInitialAfterTax() {
        return driverInitialAfterTax;
    }

    public void setDriverInitialAfterTax(BigDecimal driverInitialAfterTax) {
        this.driverInitialAfterTax = driverInitialAfterTax;
    }

    public BigDecimal getDriverFinalBeforeTax() {
        return driverFinalBeforeTax;
    }

    public void setDriverFinalBeforeTax(BigDecimal driverFinalBeforeTax) {
        this.driverFinalBeforeTax = driverFinalBeforeTax;
    }

    public BigDecimal getDriverFinalAfterTax() {
        return driverFinalAfterTax;
    }

    public void setDriverFinalAfterTax(BigDecimal driverFinalAfterTax) {
        this.driverFinalAfterTax = driverFinalAfterTax;
    }

    public BigDecimal getDriverHandlingFee() {
        return driverHandlingFee;
    }

    public void setDriverHandlingFee(BigDecimal driverHandlingFee) {
        this.driverHandlingFee = driverHandlingFee;
    }

    public BigDecimal getLaborerHandlingFee() {
        return laborerHandlingFee;
    }

    public void setLaborerHandlingFee(BigDecimal laborerHandlingFee) {
        this.laborerHandlingFee = laborerHandlingFee;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
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
        ReconciliationNew other = (ReconciliationNew) that;
        return (this.getReconciliationId() == null ? other.getReconciliationId() == null : this.getReconciliationId().equals(other.getReconciliationId()))
            && (this.getReconciliationNo() == null ? other.getReconciliationNo() == null : this.getReconciliationNo().equals(other.getReconciliationNo()))
            && (this.getReconciliationType() == null ? other.getReconciliationType() == null : this.getReconciliationType().equals(other.getReconciliationType()))
            && (this.getReconciliationStatus() == null ? other.getReconciliationStatus() == null : this.getReconciliationStatus().equals(other.getReconciliationStatus()))
            && (this.getSubmitTime() == null ? other.getSubmitTime() == null : this.getSubmitTime().equals(other.getSubmitTime()))
            && (this.getSubmitter() == null ? other.getSubmitter() == null : this.getSubmitter().equals(other.getSubmitter()))
            && (this.getProcessInstanceId() == null ? other.getProcessInstanceId() == null : this.getProcessInstanceId().equals(other.getProcessInstanceId()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getTenantCode() == null ? other.getTenantCode() == null : this.getTenantCode().equals(other.getTenantCode()))
            && (this.getTaxRateValue() == null ? other.getTaxRateValue() == null : this.getTaxRateValue().equals(other.getTaxRateValue()))
            && (this.getHasReceiveFreight() == null ? other.getHasReceiveFreight() == null : this.getHasReceiveFreight().equals(other.getHasReceiveFreight()))
            && (this.getReceiveStatus() == null ? other.getReceiveStatus() == null : this.getReceiveStatus().equals(other.getReceiveStatus()))
            && (this.getInvoiceNo() == null ? other.getInvoiceNo() == null : this.getInvoiceNo().equals(other.getInvoiceNo()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getProjectName() == null ? other.getProjectName() == null : this.getProjectName().equals(other.getProjectName()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getCustomerInitialBeforeTax() == null ? other.getCustomerInitialBeforeTax() == null : this.getCustomerInitialBeforeTax().equals(other.getCustomerInitialBeforeTax()))
            && (this.getCustomerInitialAfterTax() == null ? other.getCustomerInitialAfterTax() == null : this.getCustomerInitialAfterTax().equals(other.getCustomerInitialAfterTax()))
            && (this.getCustomerFinalBeforeTax() == null ? other.getCustomerFinalBeforeTax() == null : this.getCustomerFinalBeforeTax().equals(other.getCustomerFinalBeforeTax()))
            && (this.getCustomerFinalAfterTax() == null ? other.getCustomerFinalAfterTax() == null : this.getCustomerFinalAfterTax().equals(other.getCustomerFinalAfterTax()))
            && (this.getDriverInitialBeforeTax() == null ? other.getDriverInitialBeforeTax() == null : this.getDriverInitialBeforeTax().equals(other.getDriverInitialBeforeTax()))
            && (this.getDriverInitialAfterTax() == null ? other.getDriverInitialAfterTax() == null : this.getDriverInitialAfterTax().equals(other.getDriverInitialAfterTax()))
            && (this.getDriverFinalBeforeTax() == null ? other.getDriverFinalBeforeTax() == null : this.getDriverFinalBeforeTax().equals(other.getDriverFinalBeforeTax()))
            && (this.getDriverFinalAfterTax() == null ? other.getDriverFinalAfterTax() == null : this.getDriverFinalAfterTax().equals(other.getDriverFinalAfterTax()))
            && (this.getDriverHandlingFee() == null ? other.getDriverHandlingFee() == null : this.getDriverHandlingFee().equals(other.getDriverHandlingFee()))
            && (this.getLaborerHandlingFee() == null ? other.getLaborerHandlingFee() == null : this.getLaborerHandlingFee().equals(other.getLaborerHandlingFee()))
            && (this.getAreaCode() == null ? other.getAreaCode() == null : this.getAreaCode().equals(other.getAreaCode()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUserId() == null ? other.getLastUpdateUserId() == null : this.getLastUpdateUserId().equals(other.getLastUpdateUserId()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getReconciliationId() == null) ? 0 : getReconciliationId().hashCode());
        result = prime * result + ((getReconciliationNo() == null) ? 0 : getReconciliationNo().hashCode());
        result = prime * result + ((getReconciliationType() == null) ? 0 : getReconciliationType().hashCode());
        result = prime * result + ((getReconciliationStatus() == null) ? 0 : getReconciliationStatus().hashCode());
        result = prime * result + ((getSubmitTime() == null) ? 0 : getSubmitTime().hashCode());
        result = prime * result + ((getSubmitter() == null) ? 0 : getSubmitter().hashCode());
        result = prime * result + ((getProcessInstanceId() == null) ? 0 : getProcessInstanceId().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getTenantCode() == null) ? 0 : getTenantCode().hashCode());
        result = prime * result + ((getTaxRateValue() == null) ? 0 : getTaxRateValue().hashCode());
        result = prime * result + ((getHasReceiveFreight() == null) ? 0 : getHasReceiveFreight().hashCode());
        result = prime * result + ((getReceiveStatus() == null) ? 0 : getReceiveStatus().hashCode());
        result = prime * result + ((getInvoiceNo() == null) ? 0 : getInvoiceNo().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getProjectName() == null) ? 0 : getProjectName().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getCustomerInitialBeforeTax() == null) ? 0 : getCustomerInitialBeforeTax().hashCode());
        result = prime * result + ((getCustomerInitialAfterTax() == null) ? 0 : getCustomerInitialAfterTax().hashCode());
        result = prime * result + ((getCustomerFinalBeforeTax() == null) ? 0 : getCustomerFinalBeforeTax().hashCode());
        result = prime * result + ((getCustomerFinalAfterTax() == null) ? 0 : getCustomerFinalAfterTax().hashCode());
        result = prime * result + ((getDriverInitialBeforeTax() == null) ? 0 : getDriverInitialBeforeTax().hashCode());
        result = prime * result + ((getDriverInitialAfterTax() == null) ? 0 : getDriverInitialAfterTax().hashCode());
        result = prime * result + ((getDriverFinalBeforeTax() == null) ? 0 : getDriverFinalBeforeTax().hashCode());
        result = prime * result + ((getDriverFinalAfterTax() == null) ? 0 : getDriverFinalAfterTax().hashCode());
        result = prime * result + ((getDriverHandlingFee() == null) ? 0 : getDriverHandlingFee().hashCode());
        result = prime * result + ((getLaborerHandlingFee() == null) ? 0 : getLaborerHandlingFee().hashCode());
        result = prime * result + ((getAreaCode() == null) ? 0 : getAreaCode().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUserId() == null) ? 0 : getLastUpdateUserId().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        return result;
    }
}