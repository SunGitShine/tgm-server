package com.juma.tgm.waybill.domain.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.giants.common.tools.Page;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.waybill.domain.Waybill;

import java.io.Serializable;

/**
 * @ClassName: CustomerManagerDebtDetailVo
 * @Description:
 * @author: liang
 * @date: 2017-08-22 15:40
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class CustomerManagerDebtDetailVo implements Serializable {

    //客户名称
    private String customerName;

    //客户id
    private Integer customerId;

    //运单数量
    private int billCount;

    //欠款数
    private String fee;

    //欠款运单
    private Page<Waybill> waybills;

    @JSONField(serialize = false)
    private CustomerInfo customerInfo;

    public String getCustomerName() {
        if (this.customerInfo != null) {
            return this.customerInfo.getCustomerName();
        } else {
            return customerName;
        }

    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getBillCount() {
        return billCount;
    }

    public void setBillCount(int billCount) {
        this.billCount = billCount;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public Page<Waybill> getWaybills() {
        return waybills;
    }

    public void setWaybills(Page<Waybill> waybills) {
        this.waybills = waybills;
    }
}
