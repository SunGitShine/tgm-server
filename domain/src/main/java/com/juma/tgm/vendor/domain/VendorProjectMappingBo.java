package com.juma.tgm.vendor.domain;

public class VendorProjectMappingBo extends VendorProjectMapping {

    //客户名称
    private String customerName;
    //项目名称
    private String projectName;
    //承运商名称
    private String vendorProjectName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getVendorProjectName() {
        return vendorProjectName;
    }

    public void setVendorProjectName(String vendorProjectName) {
        this.vendorProjectName = vendorProjectName;
    }
}