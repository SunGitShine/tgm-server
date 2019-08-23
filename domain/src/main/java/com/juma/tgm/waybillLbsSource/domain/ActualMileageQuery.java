package com.juma.tgm.waybillLbsSource.domain;

import java.io.Serializable;
import java.util.Date;

import me.about.poi.ExcelColumn;

/**
 * @ClassName ActualMileageVO.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年6月21日 下午4:35:03
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class ActualMileageQuery implements Serializable {

    private static final long serialVersionUID = -151125440951457383L;
    /** 运单ID */
    private Integer waybillId;
    /** 车辆ID */
    private Integer truckId;
    /** 司机ID */
    private Integer driverId;
    /** 司机电话 */
    private String driverMoble;
    /** 大客户ID（tms） */
    private Integer customerId;
    /** 大客户客户经理员工ID */
    private Integer customerManagerId;
    @ExcelColumn(name = "运单号")
    private String waybillNo;
    @ExcelColumn(name = "用车时间")
    private String planDeliveryDate;
    @ExcelColumn(name = "车牌号")
    private String plateNumber;
    @ExcelColumn(name = "司机姓名")
    private String driverName;
    @ExcelColumn(name = "司机类型")
    private String driverTypeText;
    @ExcelColumn(name = "所属客户")
    private String customerName;
    @ExcelColumn(name = "客户经理")
    private String customerManagerName;
    @ExcelColumn(name = "修改线路")
    private String changeDeliveryPoint;
    @ExcelColumn(name = "数据源")
    private String lbsSource;
    @ExcelColumn(name = "路线里程")
    private Integer estimateDistance;
    @ExcelColumn(name = "实际里程")
    private String actualMileage;
    private Date planDeliveryTime;

    private Integer projectId;
    private Integer truckCustomerId;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTruckCustomerId() {
        return truckCustomerId;
    }

    public void setTruckCustomerId(Integer truckCustomerId) {
        this.truckCustomerId = truckCustomerId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Date getPlanDeliveryTime() {
        return planDeliveryTime;
    }

    public void setPlanDeliveryTime(Date planDeliveryTime) {
        this.planDeliveryTime = planDeliveryTime;
    }

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

    public String getPlanDeliveryDate() {
        return planDeliveryDate;
    }

    public void setPlanDeliveryDate(String planDeliveryDate) {
        this.planDeliveryDate = planDeliveryDate;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverTypeText() {
        return driverTypeText;
    }

    public void setDriverTypeText(String driverTypeText) {
        this.driverTypeText = driverTypeText;
    }

    public String getDriverMoble() {
        return driverMoble;
    }

    public void setDriverMoble(String driverMoble) {
        this.driverMoble = driverMoble;
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

    public String getCustomerManagerName() {
        return customerManagerName;
    }

    public void setCustomerManagerName(String customerManagerName) {
        this.customerManagerName = customerManagerName;
    }

    public String getChangeDeliveryPoint() {
        return changeDeliveryPoint;
    }

    public void setChangeDeliveryPoint(String changeDeliveryPoint) {
        this.changeDeliveryPoint = changeDeliveryPoint;
    }

    public String getLbsSource() {
        return lbsSource;
    }

    public void setLbsSource(String lbsSource) {
        this.lbsSource = lbsSource;
    }

    public Integer getEstimateDistance() {
        return estimateDistance;
    }

    public void setEstimateDistance(Integer estimateDistance) {
        this.estimateDistance = estimateDistance;
    }

    public String getActualMileage() {
        return actualMileage;
    }

    public void setActualMileage(String actualMileage) {
        this.actualMileage = actualMileage;
    }

    public Integer getCustomerManagerId() {
        return customerManagerId;
    }

    public void setCustomerManagerId(Integer customerManagerId) {
        this.customerManagerId = customerManagerId;
    }

}
