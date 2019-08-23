package com.juma.tgm.crm.domain;

import me.about.poi.ExcelColumn;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CrmCustomerInfo implements Serializable {

    private Integer customerId;
    private Integer tmsCustomerId;
    
    @ExcelColumn(name="客户名称",width=30)
    private String customerName;
    
    @ExcelColumn(name="联系人",width=30)
    private String contactsName;

    @ExcelColumn(name="联系电话",width=30)
    private String contactsPhone;
    @ExcelColumn(name="公司地址",width=30)
    private String region;
    
    private String regionCode;
    @ExcelColumn(name="所属行业",width=30)
    private String customerIndustry;//所属行业
    @ExcelColumn(name="企业性质",width=30)
    private String enterpriseNature;//企业性质
    @ExcelColumn(name="所属组织",width=30)
    private String departmentView;//客户经理所在组织
    
    private Integer customerManagerId;
    @ExcelColumn(name="客户经理",width=30)
    private String customerManagerName;
    
    private Date createTime;
    @ExcelColumn(name="注册时间",width=30)
    private String createTimeFormat;
    @ExcelColumn(name="详细地址",width=30)
    private String livingAddress;//详细地址
    
    private Boolean isProjectCheckOut;//项目结算
    @ExcelColumn(name="项目结算",width=30)
    private String isProjectCheckOutView;
    
    private String loginUserMobile;//发单帐号手机
    @ExcelColumn(name="合同编号",width=30)
    private String contractNo;//合同编号
    
    private String consignorNote;//货主备注
    
    private String license;//营业执照
    
    private String orgCodeCertificate;//组织机构代码
    
    private String taxRegCertificate;//税务登记证
    private String sourceChannelCodeCnName;// 渠道
    private Byte crmStatus;// 渠道
    private Boolean isReceiveDailySms;//是否接收日报短信
    private Integer departmentId;
    private String logisticsLabelNames;

    //查询冗余
    private String areaCode;
    private String tenantCode;

    //显示冗余
    private String regionName;
    private String areaName;
    private String tenantName;
    private BigDecimal rebateRate;

    public Boolean getIsReceiveDailySms() {
        return isReceiveDailySms;
    }

    public void setIsReceiveDailySms(Boolean isReceiveDailySms) {
        this.isReceiveDailySms = isReceiveDailySms;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getLogisticsLabelNames() {
        return logisticsLabelNames;
    }

    public void setLogisticsLabelNames(String logisticsLabelNames) {
        this.logisticsLabelNames = logisticsLabelNames;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getCustomerIndustry() {
        return customerIndustry;
    }

    public void setCustomerIndustry(String customerIndustry) {
        this.customerIndustry = customerIndustry;
    }

    public String getDepartmentView() {
        return departmentView;
    }

    public void setDepartmentView(String departmentView) {
        this.departmentView = departmentView;
    }

    public Integer getCustomerManagerId() {
        return customerManagerId;
    }

    public void setCustomerManagerId(Integer customerManagerId) {
        this.customerManagerId = customerManagerId;
    }

    public String getCustomerManagerName() {
        return customerManagerName;
    }

    public void setCustomerManagerName(String customerManagerName) {
        this.customerManagerName = customerManagerName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLivingAddress() {
        return livingAddress;
    }

    public void setLivingAddress(String livingAddress) {
        this.livingAddress = livingAddress;
    }

    public Boolean getIsProjectCheckOut() {
        return isProjectCheckOut;
    }

    public void setIsProjectCheckOut(Boolean isProjectCheckOut) {
        this.isProjectCheckOut = isProjectCheckOut;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
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

    public String getEnterpriseNature() {
        return enterpriseNature;
    }

    public void setEnterpriseNature(String enterpriseNature) {
        this.enterpriseNature = enterpriseNature;
    }

    public String getLoginUserMobile() {
        return loginUserMobile;
    }

    public void setLoginUserMobile(String loginUserMobile) {
        this.loginUserMobile = loginUserMobile;
    }

    public String getCreateTimeFormat() {
        return createTimeFormat;
    }

    public void setCreateTimeFormat(String createTimeFormat) {
        this.createTimeFormat = createTimeFormat;
    }

    public String getIsProjectCheckOutView() {
        return isProjectCheckOutView;
    }

    public void setIsProjectCheckOutView(String isProjectCheckOutView) {
        this.isProjectCheckOutView = isProjectCheckOutView;
    }

    public String getConsignorNote() {
        return consignorNote;
    }

    public void setConsignorNote(String consignorNote) {
        this.consignorNote = consignorNote;
    }

    public Integer getTmsCustomerId() {
        return tmsCustomerId;
    }

    public void setTmsCustomerId(Integer tmsCustomerId) {
        this.tmsCustomerId = tmsCustomerId;
    }

    public String getSourceChannelCodeCnName() {
        return sourceChannelCodeCnName;
    }

    public void setSourceChannelCodeCnName(String sourceChannelCodeCnName) {
        this.sourceChannelCodeCnName = sourceChannelCodeCnName;
    }

    public Byte getCrmStatus() {
        return crmStatus;
    }

    public void setCrmStatus(Byte crmStatus) {
        this.crmStatus = crmStatus;
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

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public BigDecimal getRebateRate() {
        return rebateRate;
    }

    public void setRebateRate(BigDecimal rebateRate) {
        this.rebateRate = rebateRate;
    }
    
}
