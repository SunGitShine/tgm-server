package com.juma.tgm.waybill.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("改价内容")
public class WaybillAdjustPriceVo implements Serializable {

    @ApiModelProperty("运单ID")
    private Integer waybillId;
    @ApiModelProperty("修改后的含税价")
    private BigDecimal priceWithTax;
    @ApiModelProperty("修改后的税率")
    private BigDecimal taxValue;
    @ApiModelProperty("改价==>客户侧：customer;承运侧：venodr")
    private String whoAdjustPrice;
    @ApiModelProperty("改价备注")
    private String remark;

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public BigDecimal getPriceWithTax() {
        return priceWithTax;
    }

    public void setPriceWithTax(BigDecimal priceWithTax) {
        this.priceWithTax = priceWithTax;
    }

    public BigDecimal getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(BigDecimal taxValue) {
        this.taxValue = taxValue;
    }

    public String getWhoAdjustPrice() {
        return whoAdjustPrice;
    }

    public void setWhoAdjustPrice(String whoAdjustPrice) {
        this.whoAdjustPrice = whoAdjustPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
