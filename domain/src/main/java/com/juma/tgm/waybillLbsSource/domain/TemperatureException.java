package com.juma.tgm.waybillLbsSource.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName TemperatureExceptionVo.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年5月22日 下午4:44:38
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class TemperatureException implements Serializable {

    private Integer waybillId;
    private String waybillNo;
    private Date planDeliveryTime;
    private String plateNumber;
    private Integer driverId;
    private String driverName;
    private Integer customerId;
    private String customerName;
    private Integer projectId;
    private String projectName;
    private BigDecimal requiredMaxTemperature;
    private BigDecimal requiredMinTemperature;
    private BigDecimal historyMaxTemperature;
    private BigDecimal historyMinTemperature;

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public Date getPlanDeliveryTime() {
        return planDeliveryTime;
    }

    public void setPlanDeliveryTime(Date planDeliveryTime) {
        this.planDeliveryTime = planDeliveryTime;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getRequiredMaxTemperature() {
        return requiredMaxTemperature;
    }

    public void setRequiredMaxTemperature(BigDecimal requiredMaxTemperature) {
        this.requiredMaxTemperature = requiredMaxTemperature;
    }

    public BigDecimal getRequiredMinTemperature() {
        return requiredMinTemperature;
    }

    public void setRequiredMinTemperature(BigDecimal requiredMinTemperature) {
        this.requiredMinTemperature = requiredMinTemperature;
    }

    public BigDecimal getHistoryMaxTemperature() {
        return historyMaxTemperature;
    }

    public void setHistoryMaxTemperature(BigDecimal historyMaxTemperature) {
        this.historyMaxTemperature = historyMaxTemperature;
    }

    public BigDecimal getHistoryMinTemperature() {
        return historyMinTemperature;
    }

    public void setHistoryMinTemperature(BigDecimal historyMinTemperature) {
        this.historyMinTemperature = historyMinTemperature;
    }

}
