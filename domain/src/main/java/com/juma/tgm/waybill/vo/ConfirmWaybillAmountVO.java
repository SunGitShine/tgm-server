package com.juma.tgm.waybill.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 功能 :
 * 确认运费:参数
 * @author : Bruce(刘正航) 17:22 2019-06-26
 */
public class ConfirmWaybillAmountVO implements Serializable {
    /**运单ID**/
    private Integer waybillId;
    /**客户侧含税金额**/
    private BigDecimal customerAmount;
    /**承运侧含税金额**/
    private BigDecimal vendorAmount;

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public BigDecimal getCustomerAmount() {
        return customerAmount;
    }

    public void setCustomerAmount(BigDecimal customerAmount) {
        this.customerAmount = customerAmount;
    }

    public BigDecimal getVendorAmount() {
        return vendorAmount;
    }

    public void setVendorAmount(BigDecimal vendorAmount) {
        this.vendorAmount = vendorAmount;
    }
}
