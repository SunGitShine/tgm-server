package com.juma.tgm.fms.domain.v3.bo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName ReconcilicationForPayApply.java
 * @Description 客户对账申请
 * @author Libin.Wei
 * @Date 2018年11月26日 上午11:19:10
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class ReconcilicationForPayApply implements Serializable {

    private Integer customerId;
    private String customerName;
    private Integer projectId;
    private String projectName;
    private Integer departmentId;
    private String businessLicenceName;
    private BigDecimal vendorAmountNotReconciled;

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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getBusinessLicenceName() {
        return businessLicenceName;
    }

    public void setBusinessLicenceName(String businessLicenceName) {
        this.businessLicenceName = businessLicenceName;
    }

    public BigDecimal getVendorAmountNotReconciled() {
        return vendorAmountNotReconciled;
    }

    public void setVendorAmountNotReconciled(BigDecimal vendorAmountNotReconciled) {
        this.vendorAmountNotReconciled = vendorAmountNotReconciled;
    }

}
