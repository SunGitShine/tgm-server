package com.juma.tgm.fms.domain.v3;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



@ApiModel("reconcilication_for_company")
public class ReconcilicationForCompany implements Serializable {
    @ApiModelProperty("")
    private Integer reconcilicationCompanyId;

    @ApiModelProperty("公司间对账单号")
    private String reconcilicationCompanyNo;

    @ApiModelProperty("")
    private Integer tenantId;

    @ApiModelProperty("")
    private String tenantCode;

    @ApiModelProperty("")
    private String areaCode;

    @ApiModelProperty("应收对账单id")
    private Integer reconcilicationReceivableId;

    @ApiModelProperty("签约主体")
    private Integer contractToCompany;

    @ApiModelProperty("签约方统一社会信用代码")
    private String contractToCompanyCreditCode;

    @ApiModelProperty("运营主体")
    private Integer payToCompany;

    @ApiModelProperty("运作方统一社会信用代码")
    private String payToCompanyCreditCode;

    @ApiModelProperty("运费（含税）")
    private BigDecimal freightWithTax;

    @ApiModelProperty("")
    private Integer createUserId;

    @ApiModelProperty("")
    private Date createTime;

    @ApiModelProperty("")
    private Integer lastUpdateUserId;

    @ApiModelProperty("")
    private Date lastUpdateTime;

    private static final long serialVersionUID = 1L;

    public Integer getReconcilicationCompanyId() {
        return reconcilicationCompanyId;
    }

    public void setReconcilicationCompanyId(Integer reconcilicationCompanyId) {
        this.reconcilicationCompanyId = reconcilicationCompanyId;
    }

    public String getReconcilicationCompanyNo() {
        return reconcilicationCompanyNo;
    }

    public void setReconcilicationCompanyNo(String reconcilicationCompanyNo) {
        this.reconcilicationCompanyNo = reconcilicationCompanyNo == null ? null : reconcilicationCompanyNo.trim();
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

    public Integer getReconcilicationReceivableId() {
        return reconcilicationReceivableId;
    }

    public void setReconcilicationReceivableId(Integer reconcilicationReceivableId) {
        this.reconcilicationReceivableId = reconcilicationReceivableId;
    }

    public Integer getContractToCompany() {
        return contractToCompany;
    }

    public void setContractToCompany(Integer contractToCompany) {
        this.contractToCompany = contractToCompany;
    }

    public String getContractToCompanyCreditCode() {
        return contractToCompanyCreditCode;
    }

    public void setContractToCompanyCreditCode(String contractToCompanyCreditCode) {
        this.contractToCompanyCreditCode = contractToCompanyCreditCode == null ? null : contractToCompanyCreditCode.trim();
    }

    public Integer getPayToCompany() {
        return payToCompany;
    }

    public void setPayToCompany(Integer payToCompany) {
        this.payToCompany = payToCompany;
    }

    public String getPayToCompanyCreditCode() {
        return payToCompanyCreditCode;
    }

    public void setPayToCompanyCreditCode(String payToCompanyCreditCode) {
        this.payToCompanyCreditCode = payToCompanyCreditCode == null ? null : payToCompanyCreditCode.trim();
    }

    public BigDecimal getFreightWithTax() {
        return freightWithTax;
    }

    public void setFreightWithTax(BigDecimal freightWithTax) {
        this.freightWithTax = freightWithTax;
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
        ReconcilicationForCompany other = (ReconcilicationForCompany) that;
        return (this.getReconcilicationCompanyId() == null ? other.getReconcilicationCompanyId() == null : this.getReconcilicationCompanyId().equals(other.getReconcilicationCompanyId()))
            && (this.getReconcilicationCompanyNo() == null ? other.getReconcilicationCompanyNo() == null : this.getReconcilicationCompanyNo().equals(other.getReconcilicationCompanyNo()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getTenantCode() == null ? other.getTenantCode() == null : this.getTenantCode().equals(other.getTenantCode()))
            && (this.getAreaCode() == null ? other.getAreaCode() == null : this.getAreaCode().equals(other.getAreaCode()))
            && (this.getReconcilicationReceivableId() == null ? other.getReconcilicationReceivableId() == null : this.getReconcilicationReceivableId().equals(other.getReconcilicationReceivableId()))
            && (this.getContractToCompany() == null ? other.getContractToCompany() == null : this.getContractToCompany().equals(other.getContractToCompany()))
            && (this.getContractToCompanyCreditCode() == null ? other.getContractToCompanyCreditCode() == null : this.getContractToCompanyCreditCode().equals(other.getContractToCompanyCreditCode()))
            && (this.getPayToCompany() == null ? other.getPayToCompany() == null : this.getPayToCompany().equals(other.getPayToCompany()))
            && (this.getPayToCompanyCreditCode() == null ? other.getPayToCompanyCreditCode() == null : this.getPayToCompanyCreditCode().equals(other.getPayToCompanyCreditCode()))
            && (this.getFreightWithTax() == null ? other.getFreightWithTax() == null : this.getFreightWithTax().equals(other.getFreightWithTax()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUserId() == null ? other.getLastUpdateUserId() == null : this.getLastUpdateUserId().equals(other.getLastUpdateUserId()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getReconcilicationCompanyId() == null) ? 0 : getReconcilicationCompanyId().hashCode());
        result = prime * result + ((getReconcilicationCompanyNo() == null) ? 0 : getReconcilicationCompanyNo().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getTenantCode() == null) ? 0 : getTenantCode().hashCode());
        result = prime * result + ((getAreaCode() == null) ? 0 : getAreaCode().hashCode());
        result = prime * result + ((getReconcilicationReceivableId() == null) ? 0 : getReconcilicationReceivableId().hashCode());
        result = prime * result + ((getContractToCompany() == null) ? 0 : getContractToCompany().hashCode());
        result = prime * result + ((getContractToCompanyCreditCode() == null) ? 0 : getContractToCompanyCreditCode().hashCode());
        result = prime * result + ((getPayToCompany() == null) ? 0 : getPayToCompany().hashCode());
        result = prime * result + ((getPayToCompanyCreditCode() == null) ? 0 : getPayToCompanyCreditCode().hashCode());
        result = prime * result + ((getFreightWithTax() == null) ? 0 : getFreightWithTax().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
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
        sb.append(", reconcilicationCompanyId=").append(reconcilicationCompanyId);
        sb.append(", reconcilicationCompanyNo=").append(reconcilicationCompanyNo);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", tenantCode=").append(tenantCode);
        sb.append(", areaCode=").append(areaCode);
        sb.append(", reconcilicationReceivableId=").append(reconcilicationReceivableId);
        sb.append(", contractToCompany=").append(contractToCompany);
        sb.append(", contractToCompanyCreditCode=").append(contractToCompanyCreditCode);
        sb.append(", payToCompany=").append(payToCompany);
        sb.append(", payToCompanyCreditCode=").append(payToCompanyCreditCode);
        sb.append(", freightWithTax=").append(freightWithTax);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastUpdateUserId=").append(lastUpdateUserId);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        reconcilicationCompanyId,
        reconcilicationCompanyNo,
        tenantId,
        tenantCode,
        areaCode,
        reconcilicationReceivableId,
        contractToCompany,
        contractToCompanyCreditCode,
        payToCompany,
        payToCompanyCreditCode,
        freightWithTax,
        createUserId,
        createTime,
        lastUpdateUserId,
        lastUpdateTime;

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