package com.juma.tgm.fms.domain.v2.bo;


import java.io.Serializable;
import java.math.BigDecimal;

/***
 *
 * @author huangxing
 *
 * 运单 对账 初始 按用户 统计 的返回
 *
 *
 * */
public class ReconciliationStatistics implements Serializable {

    // 客户id
    private Integer customerId;


    // crm customerId
    private Integer crmCustomerId;

    // 客户 名称
    private String customerName ;

    // 项目名称
    private String projectName;

    // 项目 id
    private Integer projectId ;

    //税前未对账金额
    private BigDecimal beforeTaxFreightSum;

    //税后 未对账 金额
    private BigDecimal afterTaxFreightSum;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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

    public BigDecimal getBeforeTaxFreightSum() {
        return beforeTaxFreightSum;
    }

    public void setBeforeTaxFreightSum(BigDecimal beforeTaxFreightSum) {
        this.beforeTaxFreightSum = beforeTaxFreightSum;
    }

    public Integer getCrmCustomerId() {
        return crmCustomerId;
    }

    public void setCrmCustomerId(Integer crmCustomerId) {
        this.crmCustomerId = crmCustomerId;
    }

    public BigDecimal getAfterTaxFreightSum() {
        return afterTaxFreightSum;
    }

    public void setAfterTaxFreightSum(BigDecimal afterTaxFreightSum) {
        this.afterTaxFreightSum = afterTaxFreightSum;
    }
}
