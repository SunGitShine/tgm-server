package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能 :
 *
 * @author : Bruce(刘正航) 16:52 2019-06-26
 */
public class WaybillVO extends Waybill implements Serializable {
    /**客户含税*/
    private BigDecimal customerFreightWithTax;
    /**承运商含税*/
    private BigDecimal vendorFreightWithTax;
    /**系统时间*/
    private Date systemTime;

    /**承运商运营类型:1自营,2非自营**/
    private Integer vendorSource;
    /**运费状态:0未确认,1已确认,2超时未确认**/
    private Integer amountStatus;
    /**运费状态:未确认,已确认,超时未确认**/
    private String amountStatusDesc;

    public Integer getVendorSource() {
        return vendorSource;
    }

    public void setVendorSource(Integer vendorSource) {
        this.vendorSource = vendorSource;
    }

    public Integer getAmountStatus() {
        return amountStatus;
    }

    public void setAmountStatus(Integer amountStatus) {
        this.amountStatus = amountStatus;
    }

    public String getAmountStatusDesc() {
        return amountStatusDesc;
    }

    public void setAmountStatusDesc(String amountStatusDesc) {
        this.amountStatusDesc = amountStatusDesc;
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

    public Date getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(Date systemTime) {
        this.systemTime = systemTime;
    }
}
