package com.juma.tgm.fms.service.impl.v2;


import java.math.BigDecimal;

/***
 *
 * @author  huangxing
 *
 * 需要加总的 运单 费用
 *
 * */
public class ReconciliationItemSum {

    //税前总和
    private BigDecimal beforeTaxSum = BigDecimal.ZERO;

    //税后总和
    private BigDecimal afterTaxSum =  BigDecimal.ZERO;


    /**
     * 司机税前总和
     */
    private BigDecimal driverBeforeTaxSum = BigDecimal.ZERO;

    /**
     * 司机税后总和
     */
    private BigDecimal driverAfterTaxSum = BigDecimal.ZERO;

    // 司机搬运费总和
    private BigDecimal driverHandlingCostSum =  BigDecimal.ZERO;


    // 小工搬运费 总和
    private BigDecimal laborerHandlingCostSum =  BigDecimal.ZERO;

    public BigDecimal getBeforeTaxSum() {
        return beforeTaxSum;
    }

    public void setBeforeTaxSum(BigDecimal beforeTaxSum) {
        this.beforeTaxSum = beforeTaxSum;
    }

    public BigDecimal getAfterTaxSum() {
        return afterTaxSum;
    }

    public void setAfterTaxSum(BigDecimal afterTaxSum) {
        this.afterTaxSum = afterTaxSum;
    }

    public BigDecimal getDriverHandlingCostSum() {
        return driverHandlingCostSum;
    }

    public void setDriverHandlingCostSum(BigDecimal driverHandlingCostSum) {
        this.driverHandlingCostSum = driverHandlingCostSum;
    }

    public BigDecimal getLaborerHandlingCostSum() {
        return laborerHandlingCostSum;
    }

    public void setLaborerHandlingCostSum(BigDecimal laborerHandlingCostSum) {
        this.laborerHandlingCostSum = laborerHandlingCostSum;
    }


    public BigDecimal getDriverBeforeTaxSum() {
        return driverBeforeTaxSum;
    }

    public void setDriverBeforeTaxSum(BigDecimal driverBeforeTaxSum) {
        this.driverBeforeTaxSum = driverBeforeTaxSum;
    }

    public BigDecimal getDriverAfterTaxSum() {
        return driverAfterTaxSum;
    }

    public void setDriverAfterTaxSum(BigDecimal driverAfterTaxSum) {
        this.driverAfterTaxSum = driverAfterTaxSum;
    }
}
