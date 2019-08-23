package com.juma.tgm.external.domain;

import java.io.Serializable;

/**
 * 对外类
 */

public class ReconcilicationForPayableExternal implements Serializable {

    // 应付对账单ID
    private String reconcilicationNo;
    // 应付对账单编号
    private Integer reconcilicationId;
    // tms系统的客户ID
    private Integer customerId;
    // crm系统的客户ID
    private Integer crmCustomerId;
    // 客户名称
    private String customerName;

    public String getReconcilicationNo() {
        return reconcilicationNo;
    }

    public void setReconcilicationNo(String reconcilicationNo) {
        this.reconcilicationNo = reconcilicationNo;
    }

    public Integer getReconcilicationId() {
        return reconcilicationId;
    }

    public void setReconcilicationId(Integer reconcilicationId) {
        this.reconcilicationId = reconcilicationId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCrmCustomerId() {
        return crmCustomerId;
    }

    public void setCrmCustomerId(Integer crmCustomerId) {
        this.crmCustomerId = crmCustomerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
