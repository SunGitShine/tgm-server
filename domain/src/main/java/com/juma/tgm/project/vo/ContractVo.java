package com.juma.tgm.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 合同信息返回
 */

@ApiModel(value = "合同信息")
public class ContractVo implements Serializable {

    @ApiModelProperty(value = "合同ID")
    private Integer contractId;
    @ApiModelProperty(value = "合同编号")
    private String contractNo;
    @ApiModelProperty(value = "合同名称")
    private String contractName;
    @ApiModelProperty(value = "运营主体")
    private Integer payToCompany;
    @ApiModelProperty(value = "运营主体社会统一信用代码")
    private String payToCompanyCreditCode;
    @ApiModelProperty(value = "运营主体名称")
    private String payToCompanyName;
    @ApiModelProperty(value = "签约主体")
    private Integer contractToCompany;
    @ApiModelProperty(value = "签约主体社会统一信用代码")
    private String contractToCompanyCreditCode;
    @ApiModelProperty(value = "签约主体名称")
    private String contractToCompanyName;
    @ApiModelProperty(value = "合同开始日期")
    private Date contractStartDate;
    @ApiModelProperty(value = "合同截止日期")
    private Date contractEndDate;
    @ApiModelProperty(value = "合同附件(签约方)")
    private String contractEnclosureUrl;
    @ApiModelProperty(value = "合同附件(运作方)")
    private String payToCompanyEnclosureUrl;


    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractToCompanyCreditCode() {
        return contractToCompanyCreditCode;
    }

    public void setContractToCompanyCreditCode(String contractToCompanyCreditCode) {
        this.contractToCompanyCreditCode = contractToCompanyCreditCode;
    }

    public String getPayToCompanyName() {
        return payToCompanyName;
    }

    public void setPayToCompanyName(String payToCompanyName) {
        this.payToCompanyName = payToCompanyName;
    }

    public String getContractToCompanyName() {
        return contractToCompanyName;
    }

    public void setContractToCompanyName(String contractToCompanyName) {
        this.contractToCompanyName = contractToCompanyName;
    }

    public Date getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(Date contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getContractEnclosureUrl() {
        return contractEnclosureUrl;
    }

    public void setContractEnclosureUrl(String contractEnclosureUrl) {
        this.contractEnclosureUrl = contractEnclosureUrl;
    }

    public Integer getPayToCompany() {
        return payToCompany;
    }

    public void setPayToCompany(Integer payToCompany) {
        this.payToCompany = payToCompany;
    }

    public String getPayToCompanyCreditCode() {
        return payToCompanyCreditCode;
    }

    public void setPayToCompanyCreditCode(String payToCompanyCreditCode) {
        this.payToCompanyCreditCode = payToCompanyCreditCode;
    }

    public Integer getContractToCompany() {
        return contractToCompany;
    }

    public void setContractToCompany(Integer contractToCompany) {
        this.contractToCompany = contractToCompany;
    }

    public String getPayToCompanyEnclosureUrl() {
        return payToCompanyEnclosureUrl;
    }

    public void setPayToCompanyEnclosureUrl(String payToCompanyEnclosureUrl) {
        this.payToCompanyEnclosureUrl = payToCompanyEnclosureUrl;
    }
}
