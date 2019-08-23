package com.juma.tgm.fms.domain.v3.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 功能 :
 *
 * @author : Bruce(刘正航) 16:23 2019-06-20
 */
@ApiModel("调整单-调整金额数据")
public class WaybillStatisticsAmountVO implements Serializable {

    @ApiModelProperty("客户侧1/承运侧2")
    private Integer adjustMasterType;

    @ApiModelProperty("对账前1/对账后2")
    private Integer adjustType;

    @ApiModelProperty("客户侧调整前-含税")
    private BigDecimal customerBeforeHasTaxAmount;
    @ApiModelProperty("客户侧调整后-含税")
    private BigDecimal customerAfterHasTaxAmount;
    @ApiModelProperty("承运侧-含税金额")
    private BigDecimal vendorHasTaxAmount;

    @ApiModelProperty("承运侧调整前-含税")
    private BigDecimal vendorBeforeHasTaxAmount;
    @ApiModelProperty("承运侧调整后-含税")
    private BigDecimal vendorAfterHasTaxAmount;
    @ApiModelProperty("客户侧-含税金额")
    private BigDecimal customerHasTaxAmount;

    @ApiModelProperty("客户侧调整前-不含自营-含税")
    private BigDecimal customerBeforeHasTaxAmountWithoutSelf;
    @ApiModelProperty("客户侧调整后-不含自营-含税")
    private BigDecimal customerAfterHasTaxAmountWithoutSelf;
    @ApiModelProperty("承运侧-不含自营-含税金额")
    private BigDecimal vendorHasTaxAmountWithoutSelf;

    @ApiModelProperty("承运侧调整前-不含自营-含税")
    private BigDecimal vendorBeforeHasTaxAmountWithoutSelf;
    @ApiModelProperty("承运侧调整后-不含自营-含税")
    private BigDecimal vendorAfterHasTaxAmountWithoutSelf;
    @ApiModelProperty("客户侧-不含自营-含税金额")
    private BigDecimal customerHasTaxAmountWithoutSelf;

    @ApiModelProperty("调整前-毛利额")
    private BigDecimal beforeProfitAmount;
    @ApiModelProperty("调整前-毛利率")
    private String beforeProportion;
    @ApiModelProperty("调整前-与承诺毛利率差额")
    private String beforeProportionGap;
    @ApiModelProperty("调整后-毛利额")
    private BigDecimal afterProfitAmount;
    @ApiModelProperty("调整后-毛利率")
    private String afterProportion;
    @ApiModelProperty("调整后-与承诺毛利率差额")
    private String afterProportionGap;

    @ApiModelProperty("项目承诺毛利率")
    private BigDecimal profitRate;
    @ApiModelProperty("项目承诺毛利率(百分比)")
    private String profitRateDesc;

    @ApiModelProperty("月份:yyyy年-m月")
    private String time;
    @ApiModelProperty("按月项目整体毛利额")
    private BigDecimal monthProfitAmount;
    @ApiModelProperty("按月项目整体毛利率")
    private BigDecimal monthProportion;
    @ApiModelProperty("按月项目整体毛利率差额")
    private BigDecimal monthProportionGap;
    @ApiModelProperty("按月项目整体毛利率（百分比）")
    private String monthProportionDesc;
    @ApiModelProperty("按月项目整体毛利率差额（百分比）")
    private String monthProportionGapDesc;

    public Integer getAdjustMasterType() {
        return adjustMasterType;
    }

    public void setAdjustMasterType(Integer adjustMasterType) {
        this.adjustMasterType = adjustMasterType;
    }

    public Integer getAdjustType() {
        return adjustType;
    }

    public void setAdjustType(Integer adjustType) {
        this.adjustType = adjustType;
    }

    public BigDecimal getCustomerBeforeHasTaxAmount() {
        return customerBeforeHasTaxAmount;
    }

    public void setCustomerBeforeHasTaxAmount(BigDecimal customerBeforeHasTaxAmount) {
        this.customerBeforeHasTaxAmount = customerBeforeHasTaxAmount;
    }

    public BigDecimal getCustomerAfterHasTaxAmount() {
        return customerAfterHasTaxAmount;
    }

    public void setCustomerAfterHasTaxAmount(BigDecimal customerAfterHasTaxAmount) {
        this.customerAfterHasTaxAmount = customerAfterHasTaxAmount;
    }

    public BigDecimal getVendorHasTaxAmount() {
        return vendorHasTaxAmount;
    }

    public void setVendorHasTaxAmount(BigDecimal vendorHasTaxAmount) {
        this.vendorHasTaxAmount = vendorHasTaxAmount;
    }

    public BigDecimal getVendorBeforeHasTaxAmount() {
        return vendorBeforeHasTaxAmount;
    }

    public void setVendorBeforeHasTaxAmount(BigDecimal vendorBeforeHasTaxAmount) {
        this.vendorBeforeHasTaxAmount = vendorBeforeHasTaxAmount;
    }

    public BigDecimal getVendorAfterHasTaxAmount() {
        return vendorAfterHasTaxAmount;
    }

    public void setVendorAfterHasTaxAmount(BigDecimal vendorAfterHasTaxAmount) {
        this.vendorAfterHasTaxAmount = vendorAfterHasTaxAmount;
    }

    public BigDecimal getCustomerHasTaxAmount() {
        return customerHasTaxAmount;
    }

    public void setCustomerHasTaxAmount(BigDecimal customerHasTaxAmount) {
        this.customerHasTaxAmount = customerHasTaxAmount;
    }

    public BigDecimal getCustomerBeforeHasTaxAmountWithoutSelf() {
        return customerBeforeHasTaxAmountWithoutSelf;
    }

    public void setCustomerBeforeHasTaxAmountWithoutSelf(
            BigDecimal customerBeforeHasTaxAmountWithoutSelf) {
        this.customerBeforeHasTaxAmountWithoutSelf = customerBeforeHasTaxAmountWithoutSelf;
    }

    public BigDecimal getCustomerAfterHasTaxAmountWithoutSelf() {
        return customerAfterHasTaxAmountWithoutSelf;
    }

    public void setCustomerAfterHasTaxAmountWithoutSelf(
            BigDecimal customerAfterHasTaxAmountWithoutSelf) {
        this.customerAfterHasTaxAmountWithoutSelf = customerAfterHasTaxAmountWithoutSelf;
    }

    public BigDecimal getVendorHasTaxAmountWithoutSelf() {
        return vendorHasTaxAmountWithoutSelf;
    }

    public void setVendorHasTaxAmountWithoutSelf(BigDecimal vendorHasTaxAmountWithoutSelf) {
        this.vendorHasTaxAmountWithoutSelf = vendorHasTaxAmountWithoutSelf;
    }

    public BigDecimal getVendorBeforeHasTaxAmountWithoutSelf() {
        return vendorBeforeHasTaxAmountWithoutSelf;
    }

    public void setVendorBeforeHasTaxAmountWithoutSelf(
            BigDecimal vendorBeforeHasTaxAmountWithoutSelf) {
        this.vendorBeforeHasTaxAmountWithoutSelf = vendorBeforeHasTaxAmountWithoutSelf;
    }

    public BigDecimal getVendorAfterHasTaxAmountWithoutSelf() {
        return vendorAfterHasTaxAmountWithoutSelf;
    }

    public void setVendorAfterHasTaxAmountWithoutSelf(
            BigDecimal vendorAfterHasTaxAmountWithoutSelf) {
        this.vendorAfterHasTaxAmountWithoutSelf = vendorAfterHasTaxAmountWithoutSelf;
    }

    public BigDecimal getCustomerHasTaxAmountWithoutSelf() {
        return customerHasTaxAmountWithoutSelf;
    }

    public void setCustomerHasTaxAmountWithoutSelf(BigDecimal customerHasTaxAmountWithoutSelf) {
        this.customerHasTaxAmountWithoutSelf = customerHasTaxAmountWithoutSelf;
    }

    public BigDecimal getBeforeProfitAmount() {
        return beforeProfitAmount;
    }

    public void setBeforeProfitAmount(BigDecimal beforeProfitAmount) {
        this.beforeProfitAmount = beforeProfitAmount;
    }

    public String getBeforeProportion() {
        return beforeProportion;
    }

    public void setBeforeProportion(String beforeProportion) {
        this.beforeProportion = beforeProportion;
    }

    public String getBeforeProportionGap() {
        return beforeProportionGap;
    }

    public void setBeforeProportionGap(String beforeProportionGap) {
        this.beforeProportionGap = beforeProportionGap;
    }

    public BigDecimal getAfterProfitAmount() {
        return afterProfitAmount;
    }

    public void setAfterProfitAmount(BigDecimal afterProfitAmount) {
        this.afterProfitAmount = afterProfitAmount;
    }

    public String getAfterProportion() {
        return afterProportion;
    }

    public void setAfterProportion(String afterProportion) {
        this.afterProportion = afterProportion;
    }

    public String getAfterProportionGap() {
        return afterProportionGap;
    }

    public void setAfterProportionGap(String afterProportionGap) {
        this.afterProportionGap = afterProportionGap;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public String getProfitRateDesc() {
        return profitRateDesc;
    }

    public void setProfitRateDesc(String profitRateDesc) {
        this.profitRateDesc = profitRateDesc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BigDecimal getMonthProfitAmount() {
        return monthProfitAmount;
    }

    public void setMonthProfitAmount(BigDecimal monthProfitAmount) {
        this.monthProfitAmount = monthProfitAmount;
    }

    public BigDecimal getMonthProportion() {
        return monthProportion;
    }

    public void setMonthProportion(BigDecimal monthProportion) {
        this.monthProportion = monthProportion;
    }

    public BigDecimal getMonthProportionGap() {
        return monthProportionGap;
    }

    public void setMonthProportionGap(BigDecimal monthProportionGap) {
        this.monthProportionGap = monthProportionGap;
    }

    public String getMonthProportionDesc() {
        return monthProportionDesc;
    }

    public void setMonthProportionDesc(String monthProportionDesc) {
        this.monthProportionDesc = monthProportionDesc;
    }

    public String getMonthProportionGapDesc() {
        return monthProportionGapDesc;
    }

    public void setMonthProportionGapDesc(String monthProportionGapDesc) {
        this.monthProportionGapDesc = monthProportionGapDesc;
    }
}
