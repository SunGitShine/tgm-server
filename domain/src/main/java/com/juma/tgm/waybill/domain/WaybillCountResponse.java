package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName WaybillCountResponse.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年9月5日 下午6:24:13
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class WaybillCountResponse implements Serializable {

    private static final long serialVersionUID = -1414285802530138527L;

    /**
     * 运单数
     */
    private Integer total = 0;

    /**
     * 税前费用
     */
    private BigDecimal estimateFreight = BigDecimal.ZERO;

    /**
     * 税后运费
     */
    private BigDecimal afterTaxFreight = BigDecimal.ZERO;

    /**
     * 司机结算价
     */
    private BigDecimal show4DriverFreight = BigDecimal.ZERO;

    /**
     * 税费
     */
    private BigDecimal tax = BigDecimal.ZERO;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public BigDecimal getEstimateFreight() {
        return estimateFreight;
    }

    public void setEstimateFreight(BigDecimal estimateFreight) {
        this.estimateFreight = estimateFreight;
    }

    public BigDecimal getAfterTaxFreight() {
        return afterTaxFreight;
    }

    public void setAfterTaxFreight(BigDecimal afterTaxFreight) {
        this.afterTaxFreight = afterTaxFreight;
    }

    public BigDecimal getShow4DriverFreight() {
        return show4DriverFreight;
    }

    public void setShow4DriverFreight(BigDecimal show4DriverFreight) {
        this.show4DriverFreight = show4DriverFreight;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

}
