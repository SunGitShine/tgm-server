package com.juma.tgm.project.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomerForProductAndDept implements Serializable {

    private Integer deparmentId;
    // 公司统一社会信用代码
    private String companyCreditCode;
    // 部门名称
    private String deparmentName;
    // 子公司名称
    private String businessLicenceName;
    private List<LogisticsProduct> listLogisticsProduct = new ArrayList<>();

    public Integer getDeparmentId() {
        return deparmentId;
    }

    public void setDeparmentId(Integer deparmentId) {
        this.deparmentId = deparmentId;
    }

    public String getCompanyCreditCode() {
        return companyCreditCode;
    }

    public void setCompanyCreditCode(String companyCreditCode) {
        this.companyCreditCode = companyCreditCode;
    }

    public String getDeparmentName() {
        return deparmentName;
    }

    public void setDeparmentName(String deparmentName) {
        this.deparmentName = deparmentName;
    }

    public String getBusinessLicenceName() {
        return businessLicenceName;
    }

    public void setBusinessLicenceName(String businessLicenceName) {
        this.businessLicenceName = businessLicenceName;
    }

    public List<LogisticsProduct> getListLogisticsProduct() {
        return listLogisticsProduct;
    }

    public void setListLogisticsProduct(List<LogisticsProduct> listLogisticsProduct) {
        this.listLogisticsProduct = listLogisticsProduct;
    }
}
