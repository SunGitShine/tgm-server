package com.juma.tgm.fms.domain.v3.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="客户对账改价对象",description="客户对账改价对象")
public class AdjustForReceivableVo implements Serializable {

    private Integer adjustId;

    @ApiModelProperty(value="运单id",name="waybillId")
    private Integer waybillId;

    @ApiModelProperty(value="运单号",name="waybillNo")
    private String waybillNo;

    @ApiModelProperty(value="含税金额初始值",name="receivableWithTax")
    private BigDecimal receivableWithTax;

    @ApiModelProperty(value="含税金额调整值",name="receivableWithTaxAdjust")
    private BigDecimal receivableWithTaxAdjust;

    @ApiModelProperty(value="税率初始值",name="taxRate")
    private BigDecimal taxRate;

    @ApiModelProperty(value="税率调整值",name="taxRateAdjust")
    private BigDecimal taxRateAdjust;

    @ApiModelProperty(value="改价备注",name="adjustRemark")
    private String adjustRemark;

    private Integer adjustUserId;

    private Date adjustTime;

    private String noticeToWaybillOwner = "否";

    @ApiModelProperty(value="操作人",name="adjustUserName")
    private String adjustUserName;

    public String getNoticeToWaybillOwner() {
        return noticeToWaybillOwner;
    }

    public void setNoticeToWaybillOwner(String noticeToWaybillOwner) {
        this.noticeToWaybillOwner = noticeToWaybillOwner;
    }

    public String getAdjustUserName() {
        return adjustUserName;
    }

    public void setAdjustUserName(String adjustUserName) {
        this.adjustUserName = adjustUserName;
    }

    private static final long serialVersionUID = 1L;

    public Integer getAdjustId() {
        return adjustId;
    }

    public void setAdjustId(Integer adjustId) {
        this.adjustId = adjustId;
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

    public BigDecimal getReceivableWithTax() {
        return receivableWithTax;
    }

    public void setReceivableWithTax(BigDecimal receivableWithTax) {
        this.receivableWithTax = receivableWithTax;
    }

    public BigDecimal getReceivableWithTaxAdjust() {
        return receivableWithTaxAdjust;
    }

    public void setReceivableWithTaxAdjust(BigDecimal receivableWithTaxAdjust) {
        this.receivableWithTaxAdjust = receivableWithTaxAdjust;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxRateAdjust() {
        return taxRateAdjust;
    }

    public void setTaxRateAdjust(BigDecimal taxRateAdjust) {
        this.taxRateAdjust = taxRateAdjust;
    }

    public String getAdjustRemark() {
        return adjustRemark;
    }

    public void setAdjustRemark(String adjustRemark) {
        this.adjustRemark = adjustRemark == null ? null : adjustRemark.trim();
    }

    public Integer getAdjustUserId() {
        return adjustUserId;
    }

    public void setAdjustUserId(Integer adjustUserId) {
        this.adjustUserId = adjustUserId;
    }

    public Date getAdjustTime() {
        return adjustTime;
    }

    public void setAdjustTime(Date adjustTime) {
        this.adjustTime = adjustTime;
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
        AdjustForReceivableVo other = (AdjustForReceivableVo) that;
        return (this.getAdjustId() == null ? other.getAdjustId() == null : this.getAdjustId().equals(other.getAdjustId()))
            && (this.getWaybillId() == null ? other.getWaybillId() == null : this.getWaybillId().equals(other.getWaybillId()))
            && (this.getWaybillNo() == null ? other.getWaybillNo() == null : this.getWaybillNo().equals(other.getWaybillNo()))
            && (this.getReceivableWithTax() == null ? other.getReceivableWithTax() == null : this.getReceivableWithTax().equals(other.getReceivableWithTax()))
            && (this.getReceivableWithTaxAdjust() == null ? other.getReceivableWithTaxAdjust() == null : this.getReceivableWithTaxAdjust().equals(other.getReceivableWithTaxAdjust()))
            && (this.getTaxRate() == null ? other.getTaxRate() == null : this.getTaxRate().equals(other.getTaxRate()))
            && (this.getTaxRateAdjust() == null ? other.getTaxRateAdjust() == null : this.getTaxRateAdjust().equals(other.getTaxRateAdjust()))
            && (this.getAdjustRemark() == null ? other.getAdjustRemark() == null : this.getAdjustRemark().equals(other.getAdjustRemark()))
            && (this.getAdjustUserId() == null ? other.getAdjustUserId() == null : this.getAdjustUserId().equals(other.getAdjustUserId()))
            && (this.getAdjustTime() == null ? other.getAdjustTime() == null : this.getAdjustTime().equals(other.getAdjustTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAdjustId() == null) ? 0 : getAdjustId().hashCode());
        result = prime * result + ((getWaybillId() == null) ? 0 : getWaybillId().hashCode());
        result = prime * result + ((getWaybillNo() == null) ? 0 : getWaybillNo().hashCode());
        result = prime * result + ((getReceivableWithTax() == null) ? 0 : getReceivableWithTax().hashCode());
        result = prime * result + ((getReceivableWithTaxAdjust() == null) ? 0 : getReceivableWithTaxAdjust().hashCode());
        result = prime * result + ((getTaxRate() == null) ? 0 : getTaxRate().hashCode());
        result = prime * result + ((getTaxRateAdjust() == null) ? 0 : getTaxRateAdjust().hashCode());
        result = prime * result + ((getAdjustRemark() == null) ? 0 : getAdjustRemark().hashCode());
        result = prime * result + ((getAdjustUserId() == null) ? 0 : getAdjustUserId().hashCode());
        result = prime * result + ((getAdjustTime() == null) ? 0 : getAdjustTime().hashCode());
        return result;
    }
}