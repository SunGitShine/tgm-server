package com.juma.tgm.fms.domain.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReconciliationItemToFms implements Serializable {

    private static final long serialVersionUID = 5489640791890326656L;
    private Integer waybillId;
    private String waybillNo;
    private Integer customerId;
    private Integer crmCustomerId;
    private String customerName;
    private Integer projectId;
    private String projectName;
    private BigDecimal beforeTaxCost;
    private BigDecimal taxRate;
    private BigDecimal taxAmount;
    private BigDecimal afterTaxCost;
    private String plateNumber;
    private String areaCode;
    private String remark;
    private Date createTime;

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCrmCustomerId() {
        return crmCustomerId;
    }

    public void setCrmCustomerId(Integer crmCustomerId) {
        this.crmCustomerId = crmCustomerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
        this.projectName = projectName;
    }

    public BigDecimal getBeforeTaxCost() {
        return beforeTaxCost;
    }

    public void setBeforeTaxCost(BigDecimal beforeTaxCost) {
        this.beforeTaxCost = beforeTaxCost;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getAfterTaxCost() {
        return afterTaxCost;
    }

    public void setAfterTaxCost(BigDecimal afterTaxCost) {
        this.afterTaxCost = afterTaxCost;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ReconciliationItemToFms [waybillId=" + waybillId + ", waybillNo=" + waybillNo + ", customerId="
                + customerId + ", crmCustomerId=" + crmCustomerId + ", customerName=" + customerName + ", projectId="
                + projectId + ", projectName=" + projectName + ", beforeTaxCost=" + beforeTaxCost + ", taxRate="
                + taxRate + ", taxAmount=" + taxAmount + ", afterTaxCost=" + afterTaxCost + ", plateNumber="
                + plateNumber + ", areaCode=" + areaCode + ", remark=" + remark + ", createTime=" + createTime + "]";
    }

}
