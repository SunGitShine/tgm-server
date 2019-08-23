package com.juma.tgm.fms.domain.v3;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel("应付额外项")
public class ReconciliationExtraForPayable implements Serializable {
    private Integer extraId;

    @ApiModelProperty("对账")
    private Integer reconciliationId;

    @ApiModelProperty("承运商")
    private Integer vendorId;

    @ApiModelProperty("油卡费")
    private BigDecimal oilCardFee;

    @ApiModelProperty("管理费")
    private BigDecimal managementFee;

    @ApiModelProperty("是否开票")
    private Boolean isInvoice;

    @ApiModelProperty("进项税率")
    private BigDecimal taxRate;

    @ApiModelProperty("税费参考")
    private BigDecimal referenceTaxFee;

    @ApiModelProperty("承运商开票税率")
    private BigDecimal vendorTaxRate;

    @ApiModelProperty("可抵扣进项税费")
    private BigDecimal deductionTaxFee;

    @ApiModelProperty("调整时间")
    private Date adjustTime;

    @ApiModelProperty("调整人")
    private Integer adjustUserId;

    private static final long serialVersionUID = 1L;

    public Integer getExtraId() {
        return extraId;
    }

    public void setExtraId(Integer extraId) {
        this.extraId = extraId;
    }

    public Integer getReconciliationId() {
        return reconciliationId;
    }

    public void setReconciliationId(Integer reconciliationId) {
        this.reconciliationId = reconciliationId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public BigDecimal getOilCardFee() {
        return oilCardFee;
    }

    public void setOilCardFee(BigDecimal oilCardFee) {
        this.oilCardFee = oilCardFee;
    }

    public BigDecimal getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(BigDecimal managementFee) {
        this.managementFee = managementFee;
    }

    public Boolean getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(Boolean isInvoice) {
        this.isInvoice = isInvoice;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getReferenceTaxFee() {
        return referenceTaxFee;
    }

    public void setReferenceTaxFee(BigDecimal referenceTaxFee) {
        this.referenceTaxFee = referenceTaxFee;
    }

    public BigDecimal getVendorTaxRate() {
        return vendorTaxRate;
    }

    public void setVendorTaxRate(BigDecimal vendorTaxRate) {
        this.vendorTaxRate = vendorTaxRate;
    }

    public BigDecimal getDeductionTaxFee() {
        return deductionTaxFee;
    }

    public void setDeductionTaxFee(BigDecimal deductionTaxFee) {
        this.deductionTaxFee = deductionTaxFee;
    }

    public Date getAdjustTime() {
        return adjustTime;
    }

    public void setAdjustTime(Date adjustTime) {
        this.adjustTime = adjustTime;
    }

    public Integer getAdjustUserId() {
        return adjustUserId;
    }

    public void setAdjustUserId(Integer adjustUserId) {
        this.adjustUserId = adjustUserId;
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
        ReconciliationExtraForPayable other = (ReconciliationExtraForPayable) that;
        return (this.getExtraId() == null ? other.getExtraId() == null : this.getExtraId().equals(other.getExtraId()))
            && (this.getReconciliationId() == null ? other.getReconciliationId() == null : this.getReconciliationId().equals(other.getReconciliationId()))
            && (this.getVendorId() == null ? other.getVendorId() == null : this.getVendorId().equals(other.getVendorId()))
            && (this.getOilCardFee() == null ? other.getOilCardFee() == null : this.getOilCardFee().equals(other.getOilCardFee()))
            && (this.getManagementFee() == null ? other.getManagementFee() == null : this.getManagementFee().equals(other.getManagementFee()))
            && (this.getIsInvoice() == null ? other.getIsInvoice() == null : this.getIsInvoice().equals(other.getIsInvoice()))
            && (this.getTaxRate() == null ? other.getTaxRate() == null : this.getTaxRate().equals(other.getTaxRate()))
            && (this.getReferenceTaxFee() == null ? other.getReferenceTaxFee() == null : this.getReferenceTaxFee().equals(other.getReferenceTaxFee()))
            && (this.getVendorTaxRate() == null ? other.getVendorTaxRate() == null : this.getVendorTaxRate().equals(other.getVendorTaxRate()))
            && (this.getDeductionTaxFee() == null ? other.getDeductionTaxFee() == null : this.getDeductionTaxFee().equals(other.getDeductionTaxFee()))
            && (this.getAdjustTime() == null ? other.getAdjustTime() == null : this.getAdjustTime().equals(other.getAdjustTime()))
            && (this.getAdjustUserId() == null ? other.getAdjustUserId() == null : this.getAdjustUserId().equals(other.getAdjustUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getExtraId() == null) ? 0 : getExtraId().hashCode());
        result = prime * result + ((getReconciliationId() == null) ? 0 : getReconciliationId().hashCode());
        result = prime * result + ((getVendorId() == null) ? 0 : getVendorId().hashCode());
        result = prime * result + ((getOilCardFee() == null) ? 0 : getOilCardFee().hashCode());
        result = prime * result + ((getManagementFee() == null) ? 0 : getManagementFee().hashCode());
        result = prime * result + ((getIsInvoice() == null) ? 0 : getIsInvoice().hashCode());
        result = prime * result + ((getTaxRate() == null) ? 0 : getTaxRate().hashCode());
        result = prime * result + ((getReferenceTaxFee() == null) ? 0 : getReferenceTaxFee().hashCode());
        result = prime * result + ((getVendorTaxRate() == null) ? 0 : getVendorTaxRate().hashCode());
        result = prime * result + ((getDeductionTaxFee() == null) ? 0 : getDeductionTaxFee().hashCode());
        result = prime * result + ((getAdjustTime() == null) ? 0 : getAdjustTime().hashCode());
        result = prime * result + ((getAdjustUserId() == null) ? 0 : getAdjustUserId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", extraId=").append(extraId);
        sb.append(", reconciliationId=").append(reconciliationId);
        sb.append(", vendorId=").append(vendorId);
        sb.append(", oilCardFee=").append(oilCardFee);
        sb.append(", managementFee=").append(managementFee);
        sb.append(", isInvoice=").append(isInvoice);
        sb.append(", taxRate=").append(taxRate);
        sb.append(", referenceTaxFee=").append(referenceTaxFee);
        sb.append(", vendorTaxRate=").append(vendorTaxRate);
        sb.append(", deductionTaxFee=").append(deductionTaxFee);
        sb.append(", adjustTime=").append(adjustTime);
        sb.append(", adjustUserId=").append(adjustUserId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        extraId,
        reconciliationId,
        vendorId,
        oilCardFee,
        managementFee,
        isInvoice,
        taxRate,
        referenceTaxFee,
        vendorTaxRate,
        deductionTaxFee,
        adjustTime,
        adjustUserId;

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