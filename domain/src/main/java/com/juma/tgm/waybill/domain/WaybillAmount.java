package com.juma.tgm.waybill.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel("运单费用表")
public class WaybillAmount implements Serializable {
    private Integer waybillAmountId;

    private Integer waybillId;

    @ApiModelProperty("客户含税")
    private BigDecimal customerFreightWithTax;

    @ApiModelProperty("承运商含税")
    private BigDecimal vendorFreightWithTax;

    @ApiModelProperty("最后一次调整客户含税")
    private BigDecimal lastCustomerFreightWithTax;

    @ApiModelProperty("最后一次调整承运商含税")
    private BigDecimal lastVendorFreightWithTax;

    @ApiModelProperty("运费状态:0未确认,1已确认,2超时未确认")
    private Integer amountStatus;

    private Integer createUserId;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getWaybillAmountId() {
        return waybillAmountId;
    }

    public void setWaybillAmountId(Integer waybillAmountId) {
        this.waybillAmountId = waybillAmountId;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public BigDecimal getCustomerFreightWithTax() {
        return customerFreightWithTax;
    }

    public void setCustomerFreightWithTax(BigDecimal customerFreightWithTax) {
        this.customerFreightWithTax = customerFreightWithTax;
    }

    public BigDecimal getVendorFreightWithTax() {
        return vendorFreightWithTax;
    }

    public void setVendorFreightWithTax(BigDecimal vendorFreightWithTax) {
        this.vendorFreightWithTax = vendorFreightWithTax;
    }

    public BigDecimal getLastCustomerFreightWithTax() {
        return lastCustomerFreightWithTax;
    }

    public void setLastCustomerFreightWithTax(BigDecimal lastCustomerFreightWithTax) {
        this.lastCustomerFreightWithTax = lastCustomerFreightWithTax;
    }

    public BigDecimal getLastVendorFreightWithTax() {
        return lastVendorFreightWithTax;
    }

    public void setLastVendorFreightWithTax(BigDecimal lastVendorFreightWithTax) {
        this.lastVendorFreightWithTax = lastVendorFreightWithTax;
    }

    public Integer getAmountStatus() {
        return amountStatus;
    }

    public void setAmountStatus(Integer amountStatus) {
        this.amountStatus = amountStatus;
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
        WaybillAmount other = (WaybillAmount) that;
        return (this.getWaybillAmountId() == null ? other.getWaybillAmountId() == null : this.getWaybillAmountId().equals(other.getWaybillAmountId()))
            && (this.getWaybillId() == null ? other.getWaybillId() == null : this.getWaybillId().equals(other.getWaybillId()))
            && (this.getCustomerFreightWithTax() == null ? other.getCustomerFreightWithTax() == null : this.getCustomerFreightWithTax().equals(other.getCustomerFreightWithTax()))
            && (this.getVendorFreightWithTax() == null ? other.getVendorFreightWithTax() == null : this.getVendorFreightWithTax().equals(other.getVendorFreightWithTax()))
            && (this.getLastCustomerFreightWithTax() == null ? other.getLastCustomerFreightWithTax() == null : this.getLastCustomerFreightWithTax().equals(other.getLastCustomerFreightWithTax()))
            && (this.getLastVendorFreightWithTax() == null ? other.getLastVendorFreightWithTax() == null : this.getLastVendorFreightWithTax().equals(other.getLastVendorFreightWithTax()))
            && (this.getAmountStatus() == null ? other.getAmountStatus() == null : this.getAmountStatus().equals(other.getAmountStatus()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWaybillAmountId() == null) ? 0 : getWaybillAmountId().hashCode());
        result = prime * result + ((getWaybillId() == null) ? 0 : getWaybillId().hashCode());
        result = prime * result + ((getCustomerFreightWithTax() == null) ? 0 : getCustomerFreightWithTax().hashCode());
        result = prime * result + ((getVendorFreightWithTax() == null) ? 0 : getVendorFreightWithTax().hashCode());
        result = prime * result + ((getLastCustomerFreightWithTax() == null) ? 0 : getLastCustomerFreightWithTax().hashCode());
        result = prime * result + ((getLastVendorFreightWithTax() == null) ? 0 : getLastVendorFreightWithTax().hashCode());
        result = prime * result + ((getAmountStatus() == null) ? 0 : getAmountStatus().hashCode());
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
        sb.append(", waybillAmountId=").append(waybillAmountId);
        sb.append(", waybillId=").append(waybillId);
        sb.append(", customerFreightWithTax=").append(customerFreightWithTax);
        sb.append(", vendorFreightWithTax=").append(vendorFreightWithTax);
        sb.append(", lastCustomerFreightWithTax=").append(lastCustomerFreightWithTax);
        sb.append(", lastVendorFreightWithTax=").append(lastVendorFreightWithTax);
        sb.append(", amountStatus=").append(amountStatus);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        waybillAmountId,
        waybillId,
        customerFreightWithTax,
        vendorFreightWithTax,
        lastCustomerFreightWithTax,
        lastVendorFreightWithTax,
        amountStatus,
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