package com.juma.tgm.task.vo.gateway;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "任务日历")
public class TaskCalendarDetail implements Serializable {

    @ApiModelProperty(value = "任务日历Id")
    private Integer calendarId;

    @ApiModelProperty(value = "工作时间")
    private Date workTime;

    @ApiModelProperty(value = "预估用时")
    private String estimateTimeCostRange;

    @ApiModelProperty(value = "承运商id")
    private Integer vendorId;

    @ApiModelProperty(value = "车辆id")
    private Integer truckId;

    @ApiModelProperty(value = "司机id")
    private Integer driverId;

    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

    @ApiModelProperty(value = "车型")
    private String truckTypeName;

    @ApiModelProperty(value = "司机")
    private String driverName;

    @ApiModelProperty(value = "司机电话")
    private String driverContactPhone;

    @ApiModelProperty(value = "任务日历状态 0:运行中 1:已完成 2:不配送 3:运力冲突")
    private Integer workStatus;

    @ApiModelProperty(value = "不配送原因类别 1:承运商原因 2:客户原因 3:公司原因")
    private Integer reasonSort;

    @ApiModelProperty(value = "不配送原因")
    private String notDeliveryReason;

    public Integer getReasonSort() {
        return reasonSort;
    }

    public void setReasonSort(Integer reasonSort) {
        this.reasonSort = reasonSort;
    }

    public String getNotDeliveryReason() {
        return notDeliveryReason;
    }

    public void setNotDeliveryReason(String notDeliveryReason) {
        this.notDeliveryReason = notDeliveryReason;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(Integer calendarId) {
        this.calendarId = calendarId;
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public String getEstimateTimeCostRange() {
        return estimateTimeCostRange;
    }

    public void setEstimateTimeCostRange(String estimateTimeCostRange) {
        this.estimateTimeCostRange = estimateTimeCostRange;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverContactPhone() {
        return driverContactPhone;
    }

    public void setDriverContactPhone(String driverContactPhone) {
        this.driverContactPhone = driverContactPhone;
    }
}
