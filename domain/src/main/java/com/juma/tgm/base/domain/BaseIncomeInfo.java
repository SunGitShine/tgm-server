package com.juma.tgm.base.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName BaseIncomeInfo.java
 * @Description 收入基础信息
 * @author Libin.Wei
 * @Date 2017年2月15日 上午10:04:26
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class BaseIncomeInfo implements Serializable {

    private static final long serialVersionUID = 1457634772104341911L;
    /** 税前收入 */
    private BigDecimal preTaxIncome = BigDecimal.ZERO;
    /** 税后收入 */
    private BigDecimal afterTaxIncome = BigDecimal.ZERO;
    /** 司机搬运费 */
    private BigDecimal driverHandlingCost = BigDecimal.ZERO;
    /** 小工搬运费 */
    private BigDecimal laborerHandlingCost = BigDecimal.ZERO;
    /** 运单数 */
    private Integer waybillNumber = 0;

    public BigDecimal getPreTaxIncome() {
        return preTaxIncome;
    }

    public void setPreTaxIncome(BigDecimal preTaxIncome) {
        this.preTaxIncome = preTaxIncome;
    }

    public BigDecimal getAfterTaxIncome() {
        return afterTaxIncome;
    }

    public void setAfterTaxIncome(BigDecimal afterTaxIncome) {
        this.afterTaxIncome = afterTaxIncome;
    }

    public BigDecimal getDriverHandlingCost() {
        return driverHandlingCost;
    }

    public void setDriverHandlingCost(BigDecimal driverHandlingCost) {
        this.driverHandlingCost = driverHandlingCost;
    }

    public BigDecimal getLaborerHandlingCost() {
        return laborerHandlingCost;
    }

    public void setLaborerHandlingCost(BigDecimal laborerHandlingCost) {
        this.laborerHandlingCost = laborerHandlingCost;
    }

    public Integer getWaybillNumber() {
        return waybillNumber;
    }

    public void setWaybillNumber(Integer waybillNumber) {
        this.waybillNumber = waybillNumber;
    }

    @Override
    public String toString() {
        return "BaseIncomeInfo [preTaxIncome=" + preTaxIncome + ", afterTaxIncome=" + afterTaxIncome
                + ", driverHandlingCost=" + driverHandlingCost + ", laborerHandlingCost=" + laborerHandlingCost
                + ", waybillNumber=" + waybillNumber + "]";
    }

}
