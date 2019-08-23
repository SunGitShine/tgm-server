package com.juma.tgm.crm.domain;

import java.io.Serializable;

import me.about.poi.ExcelColumn;

/**
 * @ClassName CustomerInfoExport.java
 * @Description 大客户导出
 * @author Libin.Wei
 * @Date 2017年2月21日 下午3:58:29
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class CustomerInfoExport implements Serializable {

    private static final long serialVersionUID = -1228522286073141191L;
    @ExcelColumn(name = "客户名称", width = 30)
    private String customerName;
    @ExcelColumn(name = "拓展公司", width = 30)
    private String company;
    @ExcelColumn(name = "客户经理", width = 30)
    private String customerManagerUserName;
    @ExcelColumn(name = "联系人", width = 30)
    private String linkMan;
    @ExcelColumn(name = "联系电话", width = 30)
    private String linkTel;
    @ExcelColumn(name = "备用联系电话", width = 30)
    private String linkTel2;
    @ExcelColumn(name = "所属行业", width = 30)
    private String industry;
    @ExcelColumn(name = "公司地址", width = 30)
    private String location;
    @ExcelColumn(name = "合同编码", width = 30)
    private String contractNo;
    @ExcelColumn(name = "取货地", width = 30)
    private String fullRegionName;
    @ExcelColumn(name = "是否项目结算", width = 30)
    private String checkoutStr;

    private Integer customerManagerUserId;
    private Integer customerId;

    public CustomerInfoExport() {
    }

    public CustomerInfoExport(CustomerInfo customerInfo) {
        super();
        this.customerName = customerInfo.getCustomerName();
        this.company = customerInfo.getCompany();
        this.linkMan = customerInfo.getLinkMan();
        this.linkTel = customerInfo.getLinkTel();
        this.linkTel2 = customerInfo.getLinkTel2();
        this.industry = customerInfo.getIndustry();
        this.location = customerInfo.getLocation();
        this.contractNo = customerInfo.getContractNo();
        this.checkoutStr = (customerInfo.getIsProjectCheckOut() ? "是" : "否");
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCustomerManagerUserName() {
        return customerManagerUserName;
    }

    public void setCustomerManagerUserName(String customerManagerUserName) {
        this.customerManagerUserName = customerManagerUserName;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    public String getLinkTel2() {
        return linkTel2;
    }

    public void setLinkTel2(String linkTel2) {
        this.linkTel2 = linkTel2;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getFullRegionName() {
        return fullRegionName;
    }

    public void setFullRegionName(String fullRegionName) {
        this.fullRegionName = fullRegionName;
    }

    public String getCheckoutStr() {
        return checkoutStr;
    }

    public void setCheckoutStr(String checkoutStr) {
        this.checkoutStr = checkoutStr;
    }

    public Integer getCustomerManagerUserId() {
        return customerManagerUserId;
    }

    public void setCustomerManagerUserId(Integer customerManagerUserId) {
        this.customerManagerUserId = customerManagerUserId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

}
