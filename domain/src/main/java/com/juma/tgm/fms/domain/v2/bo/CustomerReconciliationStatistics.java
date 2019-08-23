package com.juma.tgm.fms.domain.v2.bo;


import java.io.Serializable;
import java.math.BigDecimal;

/***
 *
 * @author huangxing
 *
 * 运单 对账 初始 按用户 统计 的返回
 *
 *
 * */
public class CustomerReconciliationStatistics implements Serializable {

    // 客户id
    private Integer customerId;

    // 客户 名称
    private String customerName ;

    //税前未对账金额
    private BigDecimal beforeTaxFreightSumNot;

    //税后 未对账 金额
    private BigDecimal afterTaxFreightSumNot;

    // 税前已对账金额
    private BigDecimal beforeTaxFreightSumHas;

    // 税后 已对账金额
    private BigDecimal afterTaxFreightSumHas;


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getBeforeTaxFreightSumNot() {
        return beforeTaxFreightSumNot;
    }

    public void setBeforeTaxFreightSumNot(BigDecimal beforeTaxFreightSumNot) {
        this.beforeTaxFreightSumNot = beforeTaxFreightSumNot;
    }

    public BigDecimal getAfterTaxFreightSumNot() {
        return afterTaxFreightSumNot;
    }

    public void setAfterTaxFreightSumNot(BigDecimal afterTaxFreightSumNot) {
        this.afterTaxFreightSumNot = afterTaxFreightSumNot;
    }

    public BigDecimal getBeforeTaxFreightSumHas() {
        return beforeTaxFreightSumHas;
    }

    public void setBeforeTaxFreightSumHas(BigDecimal beforeTaxFreightSumHas) {
        this.beforeTaxFreightSumHas = beforeTaxFreightSumHas;
    }

    public BigDecimal getAfterTaxFreightSumHas() {
        return afterTaxFreightSumHas;
    }

    public void setAfterTaxFreightSumHas(BigDecimal afterTaxFreightSumHas) {
        this.afterTaxFreightSumHas = afterTaxFreightSumHas;
    }
}
