package com.juma.tgm.crm.domain;

import java.util.List;

import com.juma.tgm.base.domain.BaseDomain;
import com.juma.tgm.truck.domain.bo.LogisticsProductBo;

public class CustomerInfo extends BaseDomain {

    private static final long serialVersionUID = -424802674793514771L;
    private Integer oldId;
    private Integer customerId;
    private String customerName;
    private String company;
    private Integer employeeId;
    private Integer customerManagerUserId;
    private String linkMan;
    private String linkTel;
    private Boolean isReceiveDailySms;
    private String linkTel2;
    private String industry;
    private String location;
    private String storage;
    private String detailAddress;
    private String contractNo;
    private boolean isProjectCheckOut;
    private String license;
    private String orgCodeCertificate;
    private String taxRegCertificate;
    private Integer crmCustomerId;
    private Integer createUserId;
    private Integer customerType;
    private Integer status;
    /**
     * 成交单数
     */
    private Integer waybillCount;

    /**
     * 业务区域code
     */
    private String areaCode;
    /**
     * 租户code
     */
    private String tenantCode;
    private Integer tenantId;

    /**
     * 行政区域code
     */
    private String regionCode;
    private String sourceChanelCode;
    
    // 显示冗余
    private String customerManagerUserName;


    public Integer getOldId() {
        return oldId;
    }

    public void setOldId(Integer oldId) {
        this.oldId = oldId;
    }

    //物流产品标签
    private List<LogisticsProductBo> logisticsProducts;

    //子公司名称
    private String departmentName;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<LogisticsProductBo> getLogisticsProducts() {
        return logisticsProducts;
    }

    public void setLogisticsProducts(List<LogisticsProductBo> logisticsProducts) {
        this.logisticsProducts = logisticsProducts;
    }

    @Override
    public Integer getCreateUserId() {
        return createUserId;
    }

    @Override
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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

    public Boolean getIsReceiveDailySms() {
        return isReceiveDailySms;
    }

    public void setIsReceiveDailySms(Boolean isReceiveDailySms) {
        this.isReceiveDailySms = isReceiveDailySms;
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

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public boolean getIsProjectCheckOut() {
        return isProjectCheckOut;
    }

    public void setIsProjectCheckOut(boolean isProjectCheckOut) {
        this.isProjectCheckOut = isProjectCheckOut;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getOrgCodeCertificate() {
        return orgCodeCertificate;
    }

    public void setOrgCodeCertificate(String orgCodeCertificate) {
        this.orgCodeCertificate = orgCodeCertificate;
    }

    public String getTaxRegCertificate() {
        return taxRegCertificate;
    }

    public void setTaxRegCertificate(String taxRegCertificate) {
        this.taxRegCertificate = taxRegCertificate;
    }

    public Integer getCrmCustomerId() {
        return crmCustomerId;
    }

    public void setCrmCustomerId(Integer crmCustomerId) {
        this.crmCustomerId = crmCustomerId;
    }

    public Integer getWaybillCount() {
        return waybillCount;
    }

    public void setWaybillCount(Integer waybillCount) {
        this.waybillCount = waybillCount;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getSourceChanelCode() {
        return sourceChanelCode;
    }

    public void setSourceChanelCode(String sourceChanelCode) {
        this.sourceChanelCode = sourceChanelCode;
    }

    public Integer getCustomerManagerUserId() {
        return customerManagerUserId;
    }

    public void setCustomerManagerUserId(Integer customerManagerUserId) {
        this.customerManagerUserId = customerManagerUserId;
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

    public String getCustomerManagerUserName() {
        return customerManagerUserName;
    }

    public void setCustomerManagerUserName(String customerManagerUserName) {
        this.customerManagerUserName = customerManagerUserName;
    }

    @Override
    public String toString() {
        return "CustomerInfo [customerId=" + customerId + ", customerName=" + customerName + ", company=" + company
                + ", employeeId=" + employeeId + ", customerManagerUserId=" + customerManagerUserId + ", linkMan="
                + linkMan + ", linkTel=" + linkTel + ", linkTel2=" + linkTel2 + ", industry=" + industry + ", location="
                + location + ", storage=" + storage + ", detailAddress=" + detailAddress + ", contractNo=" + contractNo
                + ", isProjectCheckOut=" + isProjectCheckOut + ", license=" + license + ", orgCodeCertificate="
                + orgCodeCertificate + ", taxRegCertificate=" + taxRegCertificate + ", crmCustomerId=" + crmCustomerId
                + ", createUserId=" + createUserId + ", customerType=" + customerType + ", status=" + status
                + ", waybillCount=" + waybillCount + ", areaCode=" + areaCode + ", tenantCode=" + tenantCode
                + ", tenantId=" + tenantId + ", regionCode=" + regionCode + ", sourceChanelCode=" + sourceChanelCode
                + ", customerManagerUserName=" + customerManagerUserName + "]";
    }
}