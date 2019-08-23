package com.juma.tgm.fms.domain.v3.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 开票金额信息
 * @author : Bruce(刘正航) 11:59 2019-05-16
 */
@ApiModel("开票信息")
public class InvoiceAmount implements Serializable {
    @ApiModelProperty("来源单号")
    private String sourceDocumentNo;
    @ApiModelProperty("车架号")
    private String vin;
    @ApiModelProperty("车架号")
    private String period;
    @ApiModelProperty("总金额(含税)")
    private BigDecimal totalAmount;
    @ApiModelProperty("已开票金额")
    private BigDecimal invoicedAmount;
    @ApiModelProperty("开票中金额")
    private BigDecimal invoicingAmount;
    @ApiModelProperty("未开票金额")
    private BigDecimal noinvoiceAmount;

    public String getSourceDocumentNo() {
        return sourceDocumentNo;
    }

    public void setSourceDocumentNo(final String sourceDocumentNo) {
        this.sourceDocumentNo = sourceDocumentNo;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(final String vin) {
        this.vin = vin;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(final String period) {
        this.period = period;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(final BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getInvoicedAmount() {
        return invoicedAmount;
    }

    public void setInvoicedAmount(final BigDecimal invoicedAmount) {
        this.invoicedAmount = invoicedAmount;
    }

    public BigDecimal getInvoicingAmount() {
        return invoicingAmount;
    }

    public void setInvoicingAmount(final BigDecimal invoicingAmount) {
        this.invoicingAmount = invoicingAmount;
    }

    public BigDecimal getNoinvoiceAmount() {
        return noinvoiceAmount;
    }

    public void setNoinvoiceAmount(final BigDecimal noinvoiceAmount) {
        this.noinvoiceAmount = noinvoiceAmount;
    }
}
