package com.juma.tgm.driver.domain;

import com.juma.tgm.crm.domain.CustomerInfo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/2.
 */
public class ReportInfoBo implements Serializable {

    private static final long serialVersionUID = -3419894949951344269L;

    private Driver driver;

    private ReportInfo reportInfo;

    private CustomerInfo customerInfo;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public ReportInfo getReportInfo() {
        return reportInfo;
    }

    public void setReportInfo(ReportInfo reportInfo) {
        this.reportInfo = reportInfo;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }
}
