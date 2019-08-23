package com.juma.tgm.fms.domain.v3;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel("运费调整临时表")
public class AdjustForWaybillTemp implements Serializable {
    private Integer adjustTempId;

    @ApiModelProperty("调整单主键ID")
    private Integer adjustId;

    private Integer tenantId;

    private String tenantCode;

    private Integer waybillId;

    private String waybillNo;

    @ApiModelProperty("承运商ID")
    private Integer vendorId;

    @ApiModelProperty("含税金额")
    private BigDecimal freightWithTax;

    @ApiModelProperty("调整时,另外一侧(客户侧/承运侧)的运单金额")
    private BigDecimal otherSideWithTax;

    @ApiModelProperty("调整基础运费金额")
    private BigDecimal adjustForFreight;

    @ApiModelProperty("搬运费")
    private BigDecimal adjustForCarry;

    @ApiModelProperty("工作量")
    private BigDecimal adjustForWorkload;

    @ApiModelProperty("上楼")
    private BigDecimal adjustForUpstairs;

    @ApiModelProperty("罚款")
    private BigDecimal adjustForFine;

    @ApiModelProperty("货损货差")
    private BigDecimal adjustForCargoLoss;

    @ApiModelProperty("原因")
    private String adjustForReason;

    @ApiModelProperty("校验状态:0未通过,1通过")
    private Integer validStatus;

    @ApiModelProperty("校验结果")
    private String validResult;

    private Integer createUserId;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getAdjustTempId() {
        return adjustTempId;
    }

    public void setAdjustTempId(Integer adjustTempId) {
        this.adjustTempId = adjustTempId;
    }

    public Integer getAdjustId() {
        return adjustId;
    }

    public void setAdjustId(Integer adjustId) {
        this.adjustId = adjustId;
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
        this.waybillNo = waybillNo == null ? null : waybillNo.trim();
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public BigDecimal getFreightWithTax() {
        return freightWithTax;
    }

    public void setFreightWithTax(BigDecimal freightWithTax) {
        this.freightWithTax = freightWithTax;
    }

    public BigDecimal getOtherSideWithTax() {
        return otherSideWithTax;
    }

    public void setOtherSideWithTax(BigDecimal otherSideWithTax) {
        this.otherSideWithTax = otherSideWithTax;
    }

    public BigDecimal getAdjustForFreight() {
        return adjustForFreight;
    }

    public void setAdjustForFreight(BigDecimal adjustForFreight) {
        this.adjustForFreight = adjustForFreight;
    }

    public BigDecimal getAdjustForCarry() {
        return adjustForCarry;
    }

    public void setAdjustForCarry(BigDecimal adjustForCarry) {
        this.adjustForCarry = adjustForCarry;
    }

    public BigDecimal getAdjustForWorkload() {
        return adjustForWorkload;
    }

    public void setAdjustForWorkload(BigDecimal adjustForWorkload) {
        this.adjustForWorkload = adjustForWorkload;
    }

    public BigDecimal getAdjustForUpstairs() {
        return adjustForUpstairs;
    }

    public void setAdjustForUpstairs(BigDecimal adjustForUpstairs) {
        this.adjustForUpstairs = adjustForUpstairs;
    }

    public BigDecimal getAdjustForFine() {
        return adjustForFine;
    }

    public void setAdjustForFine(BigDecimal adjustForFine) {
        this.adjustForFine = adjustForFine;
    }

    public BigDecimal getAdjustForCargoLoss() {
        return adjustForCargoLoss;
    }

    public void setAdjustForCargoLoss(BigDecimal adjustForCargoLoss) {
        this.adjustForCargoLoss = adjustForCargoLoss;
    }

    public String getAdjustForReason() {
        return adjustForReason;
    }

    public void setAdjustForReason(String adjustForReason) {
        this.adjustForReason = adjustForReason == null ? null : adjustForReason.trim();
    }

    public Integer getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(Integer validStatus) {
        this.validStatus = validStatus;
    }

    public String getValidResult() {
        return validResult;
    }

    public void setValidResult(String validResult) {
        this.validResult = validResult == null ? null : validResult.trim();
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
        AdjustForWaybillTemp other = (AdjustForWaybillTemp) that;
        return (this.getAdjustTempId() == null ? other.getAdjustTempId() == null : this.getAdjustTempId().equals(other.getAdjustTempId()))
            && (this.getAdjustId() == null ? other.getAdjustId() == null : this.getAdjustId().equals(other.getAdjustId()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getTenantCode() == null ? other.getTenantCode() == null : this.getTenantCode().equals(other.getTenantCode()))
            && (this.getWaybillId() == null ? other.getWaybillId() == null : this.getWaybillId().equals(other.getWaybillId()))
            && (this.getWaybillNo() == null ? other.getWaybillNo() == null : this.getWaybillNo().equals(other.getWaybillNo()))
            && (this.getVendorId() == null ? other.getVendorId() == null : this.getVendorId().equals(other.getVendorId()))
            && (this.getFreightWithTax() == null ? other.getFreightWithTax() == null : this.getFreightWithTax().equals(other.getFreightWithTax()))
            && (this.getOtherSideWithTax() == null ? other.getOtherSideWithTax() == null : this.getOtherSideWithTax().equals(other.getOtherSideWithTax()))
            && (this.getAdjustForFreight() == null ? other.getAdjustForFreight() == null : this.getAdjustForFreight().equals(other.getAdjustForFreight()))
            && (this.getAdjustForCarry() == null ? other.getAdjustForCarry() == null : this.getAdjustForCarry().equals(other.getAdjustForCarry()))
            && (this.getAdjustForWorkload() == null ? other.getAdjustForWorkload() == null : this.getAdjustForWorkload().equals(other.getAdjustForWorkload()))
            && (this.getAdjustForUpstairs() == null ? other.getAdjustForUpstairs() == null : this.getAdjustForUpstairs().equals(other.getAdjustForUpstairs()))
            && (this.getAdjustForFine() == null ? other.getAdjustForFine() == null : this.getAdjustForFine().equals(other.getAdjustForFine()))
            && (this.getAdjustForCargoLoss() == null ? other.getAdjustForCargoLoss() == null : this.getAdjustForCargoLoss().equals(other.getAdjustForCargoLoss()))
            && (this.getAdjustForReason() == null ? other.getAdjustForReason() == null : this.getAdjustForReason().equals(other.getAdjustForReason()))
            && (this.getValidStatus() == null ? other.getValidStatus() == null : this.getValidStatus().equals(other.getValidStatus()))
            && (this.getValidResult() == null ? other.getValidResult() == null : this.getValidResult().equals(other.getValidResult()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAdjustTempId() == null) ? 0 : getAdjustTempId().hashCode());
        result = prime * result + ((getAdjustId() == null) ? 0 : getAdjustId().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getTenantCode() == null) ? 0 : getTenantCode().hashCode());
        result = prime * result + ((getWaybillId() == null) ? 0 : getWaybillId().hashCode());
        result = prime * result + ((getWaybillNo() == null) ? 0 : getWaybillNo().hashCode());
        result = prime * result + ((getVendorId() == null) ? 0 : getVendorId().hashCode());
        result = prime * result + ((getFreightWithTax() == null) ? 0 : getFreightWithTax().hashCode());
        result = prime * result + ((getOtherSideWithTax() == null) ? 0 : getOtherSideWithTax().hashCode());
        result = prime * result + ((getAdjustForFreight() == null) ? 0 : getAdjustForFreight().hashCode());
        result = prime * result + ((getAdjustForCarry() == null) ? 0 : getAdjustForCarry().hashCode());
        result = prime * result + ((getAdjustForWorkload() == null) ? 0 : getAdjustForWorkload().hashCode());
        result = prime * result + ((getAdjustForUpstairs() == null) ? 0 : getAdjustForUpstairs().hashCode());
        result = prime * result + ((getAdjustForFine() == null) ? 0 : getAdjustForFine().hashCode());
        result = prime * result + ((getAdjustForCargoLoss() == null) ? 0 : getAdjustForCargoLoss().hashCode());
        result = prime * result + ((getAdjustForReason() == null) ? 0 : getAdjustForReason().hashCode());
        result = prime * result + ((getValidStatus() == null) ? 0 : getValidStatus().hashCode());
        result = prime * result + ((getValidResult() == null) ? 0 : getValidResult().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", adjustTempId=").append(adjustTempId);
        sb.append(", adjustId=").append(adjustId);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", tenantCode=").append(tenantCode);
        sb.append(", waybillId=").append(waybillId);
        sb.append(", waybillNo=").append(waybillNo);
        sb.append(", vendorId=").append(vendorId);
        sb.append(", freightWithTax=").append(freightWithTax);
        sb.append(", otherSideWithTax=").append(otherSideWithTax);
        sb.append(", adjustForFreight=").append(adjustForFreight);
        sb.append(", adjustForCarry=").append(adjustForCarry);
        sb.append(", adjustForWorkload=").append(adjustForWorkload);
        sb.append(", adjustForUpstairs=").append(adjustForUpstairs);
        sb.append(", adjustForFine=").append(adjustForFine);
        sb.append(", adjustForCargoLoss=").append(adjustForCargoLoss);
        sb.append(", adjustForReason=").append(adjustForReason);
        sb.append(", validStatus=").append(validStatus);
        sb.append(", validResult=").append(validResult);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        adjustTempId,
        adjustId,
        tenantId,
        tenantCode,
        waybillId,
        waybillNo,
        vendorId,
        freightWithTax,
        otherSideWithTax,
        adjustForFreight,
        adjustForCarry,
        adjustForWorkload,
        adjustForUpstairs,
        adjustForFine,
        adjustForCargoLoss,
        adjustForReason,
        validStatus,
        validResult,
        createUserId,
        createTime;

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