package com.juma.tgm.waybill.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 功能 :
 * 运单运费校验常量
 * @author : Bruce(刘正航) 15:29 2019-06-28
 */
@ApiModel("运单金额校验常量")
public class WaybillAmountValidVO implements Serializable {
    @ApiModelProperty("毛利率下限")
    private BigDecimal grossProfitRateFloor;
    @ApiModelProperty("毛利率上限")
    private BigDecimal grossProfitRateCeiling;
    @ApiModelProperty("运单金额上限")
    private BigDecimal waybillAmountCeiling;

    public BigDecimal getGrossProfitRateFloor() {
        return grossProfitRateFloor;
    }

    public void setGrossProfitRateFloor(BigDecimal grossProfitRateFloor) {
        this.grossProfitRateFloor = grossProfitRateFloor;
    }

    public BigDecimal getGrossProfitRateCeiling() {
        return grossProfitRateCeiling;
    }

    public void setGrossProfitRateCeiling(BigDecimal grossProfitRateCeiling) {
        this.grossProfitRateCeiling = grossProfitRateCeiling;
    }

    public BigDecimal getWaybillAmountCeiling() {
        return waybillAmountCeiling;
    }

    public void setWaybillAmountCeiling(BigDecimal waybillAmountCeiling) {
        this.waybillAmountCeiling = waybillAmountCeiling;
    }
}
