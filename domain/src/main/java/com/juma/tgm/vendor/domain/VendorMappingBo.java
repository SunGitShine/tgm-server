package com.juma.tgm.vendor.domain;

import java.util.List;

public class VendorMappingBo extends VendorMapping{

    //承运商租户名称
    private String vendorTenantName;
    //承运商客户租户名称
    private String customerTenantName;
    //子公司名称
    private String businessLicenceName;

    private Integer customerManagerUserId;

    public Integer getCustomerManagerUserId() {
        return customerManagerUserId;
    }

    public void setCustomerManagerUserId(Integer customerManagerUserId) {
        this.customerManagerUserId = customerManagerUserId;
    }

    public String getBusinessLicenceName() {
        return businessLicenceName;
    }

    public void setBusinessLicenceName(String businessLicenceName) {
        this.businessLicenceName = businessLicenceName;
    }

    private List<VendorProjectMappingBo> vendorProjectMappings;

    public String getVendorTenantName() {
        return vendorTenantName;
    }

    public void setVendorTenantName(String vendorTenantName) {
        this.vendorTenantName = vendorTenantName;
    }

    public String getCustomerTenantName() {
        return customerTenantName;
    }

    public void setCustomerTenantName(String customerTenantName) {
        this.customerTenantName = customerTenantName;
    }

    public List<VendorProjectMappingBo> getVendorProjectMappings() {
        return vendorProjectMappings;
    }

    public void setVendorProjectMappings(List<VendorProjectMappingBo> vendorProjectMappings) {
        this.vendorProjectMappings = vendorProjectMappings;
    }
}