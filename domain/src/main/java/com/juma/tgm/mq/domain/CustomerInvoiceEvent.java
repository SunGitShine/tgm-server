package com.juma.tgm.mq.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 功能 :
 *
 * @author : Bruce(刘正航) 15:35 2019-05-28
 */
public class CustomerInvoiceEvent implements Serializable {
    /**来源单号（物流传对账单号）**/
    private String sourceDocumentNo;
    /**来源单类型:RECONCILIATION(对账单),RENT_SALE(租售订单)**/
    private String sourceDocumentType;
    /**业务类型:LOGISTICS(物流),SALE(销售),RENT(租赁),AFFILIATED(挂靠)**/
    private String businessType;
    private Integer customerId;
    private String customerName;
    /**客户类型:PERSONAL(个人),ENTERPRISE(企业)**/
    private String customerType;
    /**项目合同编号**/
    private String contractCode;
    /**单据日期:对账单创建日期,格式yyyy-MM-dd**/
    private String sourceDocumentCreateDate;
    /**车牌号**/
    private String plateNumber;
    /**车架号**/
    private String vin;
    /**本次不传:数量/期数**/
    private Integer count;
    /**其他信息:物流格式:项目名称/运单完成时间,格式:测试项目/yyyy-mm-dd HH:mm:ss**/
    private String otherInfo;
    /**运单税率: 运单 客户侧 税率 (默认该对账单下运单税率一致)**/
    private BigDecimal taxRate;
    /**客户侧:运单含税金额(所有运单含税金额求和)**/
    private BigDecimal amountIncludeTax;
    /**运单签约主体公司**/
    private Integer companyId;
    private String companyCode;
    private String companyName;
    /**公司纳税人识别号**/
    private String companyTaxNumber;
    /**对账单:业务区域id**/
    private Integer businessAreaId;
    /**对账单:业务区域编码**/
    private String businessAreaCode;
    /**对账单:业务区域名称**/
    private String businessAreaName;
    /**租户id**/
    private Integer tenantId;
    /**是否是关联单中的主单**/
    private Boolean isMain;
    /**是否是关联单**/
    private Boolean isRelation;
    /**开票相关的运单信息**/
    private CustomerInvoiceWaybill payload;

    public String getSourceDocumentNo() {
        return sourceDocumentNo;
    }

    public void setSourceDocumentNo(String sourceDocumentNo) {
        this.sourceDocumentNo = sourceDocumentNo;
    }

    public String getSourceDocumentType() {
        return sourceDocumentType;
    }

    public void setSourceDocumentType(String sourceDocumentType) {
        this.sourceDocumentType = sourceDocumentType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
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

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getSourceDocumentCreateDate() {
        return sourceDocumentCreateDate;
    }

    public void setSourceDocumentCreateDate(String sourceDocumentCreateDate) {
        this.sourceDocumentCreateDate = sourceDocumentCreateDate;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getAmountIncludeTax() {
        return amountIncludeTax;
    }

    public void setAmountIncludeTax(BigDecimal amountIncludeTax) {
        this.amountIncludeTax = amountIncludeTax;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyTaxNumber() {
        return companyTaxNumber;
    }

    public void setCompanyTaxNumber(String companyTaxNumber) {
        this.companyTaxNumber = companyTaxNumber;
    }

    public Integer getBusinessAreaId() {
        return businessAreaId;
    }

    public void setBusinessAreaId(Integer businessAreaId) {
        this.businessAreaId = businessAreaId;
    }

    public String getBusinessAreaCode() {
        return businessAreaCode;
    }

    public void setBusinessAreaCode(String businessAreaCode) {
        this.businessAreaCode = businessAreaCode;
    }

    public String getBusinessAreaName() {
        return businessAreaName;
    }

    public void setBusinessAreaName(String businessAreaName) {
        this.businessAreaName = businessAreaName;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Boolean getIsMain() {
        return isMain;
    }

    public void setIsMain(Boolean main) {
        isMain = main;
    }

    public Boolean getIsRelation() {
        return isRelation;
    }

    public void setIsRelation(Boolean relation) {
        isRelation = relation;
    }

    public CustomerInvoiceWaybill getPayload() {
        return payload;
    }

    public void setPayload(CustomerInvoiceWaybill payload) {
        this.payload = payload;
    }
}
