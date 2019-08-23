package com.juma.tgm.fms.domain.v2.vo;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 对账单概览
 *
 * @ClassName: ReconciliationOverview
 * @Description:
 * @author: liang
 * @date: 2018-06-07 17:34
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class ReconciliationOverviewVo implements Serializable {

    //客户名称
    private String customerName;
    //项目名称
    private String projectName;
    //对账单号
    private String reconciliationNo;
    //车数量
    private String vehicleCount;
    //运单数量
    private String waybillCount;
    //开票状态
    private String invoice;
    //收款状态
    private String receiveStatusName;

    //客户初始税前费用
    private BigDecimal customerInitialBeforeTax;

    //客户初始税后费用
    private BigDecimal customerInitialAfterTax;

    //客户最终税前费用
    private BigDecimal customerFinalBeforeTax;

    //客户最终税后费用
    private BigDecimal customerFinalAfterTax;

    //司机初始税前费用
    private BigDecimal driverInitialBeforeTax;

    //司机初始税后费用
    private BigDecimal driverInitialAfterTax;

    //司机最终税前费用
    private BigDecimal driverFinalBeforeTax;

    //司机最终税后费用
    private BigDecimal driverFinalAfterTax;


    public String getWaybillCount() {
        return waybillCount;
    }

    public void setWaybillCount(String waybillCount) {
        this.waybillCount = waybillCount;
    }

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

    public String getReconciliationNo() {
        return reconciliationNo;
    }

    public void setReconciliationNo(String reconciliationNo) {
        this.reconciliationNo = reconciliationNo;
    }

    public String getVehicleCount() {
        return vehicleCount;
    }

    public void setVehicleCount(String vehicleCount) {
        this.vehicleCount = vehicleCount;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getReceiveStatusName() {
        return receiveStatusName;
    }

    public void setReceiveStatusName(String receiveStatusName) {
        this.receiveStatusName = receiveStatusName;
    }

    public BigDecimal getCustomerInitialBeforeTax() {
        return customerInitialBeforeTax;
    }

    public void setCustomerInitialBeforeTax(BigDecimal customerInitialBeforeTax) {
        this.customerInitialBeforeTax = customerInitialBeforeTax;
    }

    public BigDecimal getCustomerInitialAfterTax() {
        return customerInitialAfterTax;
    }

    public void setCustomerInitialAfterTax(BigDecimal customerInitialAfterTax) {
        this.customerInitialAfterTax = customerInitialAfterTax;
    }

    public BigDecimal getCustomerFinalBeforeTax() {
        return customerFinalBeforeTax;
    }

    public void setCustomerFinalBeforeTax(BigDecimal customerFinalBeforeTax) {
        this.customerFinalBeforeTax = customerFinalBeforeTax;
    }

    public BigDecimal getCustomerFinalAfterTax() {
        return customerFinalAfterTax;
    }

    public void setCustomerFinalAfterTax(BigDecimal customerFinalAfterTax) {
        this.customerFinalAfterTax = customerFinalAfterTax;
    }

    public BigDecimal getDriverInitialBeforeTax() {
        return driverInitialBeforeTax;
    }

    public void setDriverInitialBeforeTax(BigDecimal driverInitialBeforeTax) {
        this.driverInitialBeforeTax = driverInitialBeforeTax;
    }

    public BigDecimal getDriverInitialAfterTax() {
        return driverInitialAfterTax;
    }

    public void setDriverInitialAfterTax(BigDecimal driverInitialAfterTax) {
        this.driverInitialAfterTax = driverInitialAfterTax;
    }

    public BigDecimal getDriverFinalBeforeTax() {
        return driverFinalBeforeTax;
    }

    public void setDriverFinalBeforeTax(BigDecimal driverFinalBeforeTax) {
        this.driverFinalBeforeTax = driverFinalBeforeTax;
    }

    public BigDecimal getDriverFinalAfterTax() {
        return driverFinalAfterTax;
    }

    public void setDriverFinalAfterTax(BigDecimal driverFinalAfterTax) {
        this.driverFinalAfterTax = driverFinalAfterTax;
    }

    /**
     * 开票状态
     * @return
     */
    public String getReceiptNoteName() {
        if(StringUtils.isBlank(this.invoice)) return "未开票";

        if(StringUtils.isNotBlank(this.invoice)) return "已开票";

        return null;
    }

}
