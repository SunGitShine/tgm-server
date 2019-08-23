package com.juma.tgm.crm.external;

import java.io.Serializable;

/**
 * @ClassName CustomerInfoExternal
 * @Description TODO
 * @Author weilibin
 * @Date 2019-07-30 14:53
 * @Version 1.0.0
 */

public class CustomerInfoExternal implements Serializable {

    // tms的customerId
    private Integer customerId;
    // crm的customerId
    private Integer crmCustomerId;
    // 客户名称
    private String customerName;
    // 客户类型
    private Integer customerType;
    // 客户状态
    private Integer status;
    // 业务区域code
    private String areaCode;
    // 租户ID
    private Integer tenantId;

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

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}
