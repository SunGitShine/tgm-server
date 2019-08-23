package com.juma.tgm.flight.domain.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FlightUsageVo.java
 * @Description 班次VO
 * @author Libin.Wei
 * @Date 2017年5月17日 上午11:17:53
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class FlightUsageQuery implements Serializable {

    private static final long serialVersionUID = 7874039315592534908L;
    // 班次ID
    private Integer FlightId;
    // 车牌号
    private String plateNumber;
    // 车辆状态
    private String vehicleStatusName;
    // 排班司机姓名
    private String driverName;
    // 排班司机电话
    private String driverPhone;
    // 班次司机状态
    private String driverStatusName;
    // 班次开始时间
    private String startTime;
    // 班次结束时间
    private String endTime;
    // 运力类型
    private String capacityTypeName;
    // 运力状态
    private String capacityStatusName;
    // TMS车辆ID
    private Integer truckId;
    // 车辆ID
    private Integer vehicleId;
    // AMS司机ID
    private Integer amsDriverId;
    // TMS司机ID
    private Integer driverId;
    // 入城证
    private Byte goCityLicenseType;
    // 放车区域
    private String parkingArea;
    // 进行中的运单
    private List<RunningWaybill> listRunningWaybill;
    // 进行中的运单显示
    private String runningWaybillDesc;
    // 方量（载货体积）
    private Double loadVolume;
    //载重取值（最大载重）
    private Integer maxLoadCapacity;
    // 接单开关: true 开启
    private boolean isAcceptAllocateOrder;
    // 车型名称
    private String truckTypeName;
    // 入城证中文名
    private String goCityLicenseTypeText;
    // 车辆不匹配原因
    private List<VehicleMismatchReason> listVehicleMismatchReason = new ArrayList<VehicleMismatchReason>();

    public Double getLoadVolume() {
        return loadVolume;
    }

    public void setLoadVolume(Double loadVolume) {
        this.loadVolume = loadVolume;
    }

    public Integer getMaxLoadCapacity() {
        return maxLoadCapacity;
    }

    public void setMaxLoadCapacity(Integer maxLoadCapacity) {
        this.maxLoadCapacity = maxLoadCapacity;
    }

    public Integer getFlightId() {
        return FlightId;
    }

    public void setFlightId(Integer flightId) {
        FlightId = flightId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getVehicleStatusName() {
        return vehicleStatusName;
    }

    public void setVehicleStatusName(String vehicleStatusName) {
        this.vehicleStatusName = vehicleStatusName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getDriverStatusName() {
        return driverStatusName;
    }

    public void setDriverStatusName(String driverStatusName) {
        this.driverStatusName = driverStatusName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCapacityTypeName() {
        return capacityTypeName;
    }

    public void setCapacityTypeName(String capacityTypeName) {
        this.capacityTypeName = capacityTypeName;
    }

    public String getCapacityStatusName() {
        return capacityStatusName;
    }

    public void setCapacityStatusName(String capacityStatusName) {
        this.capacityStatusName = capacityStatusName;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getAmsDriverId() {
        return amsDriverId;
    }

    public void setAmsDriverId(Integer amsDriverId) {
        this.amsDriverId = amsDriverId;
    }

    public Byte getGoCityLicenseType() {
        return goCityLicenseType;
    }

    public void setGoCityLicenseType(Byte goCityLicenseType) {
        this.goCityLicenseType = goCityLicenseType;
    }

    public String getParkingArea() {
        return parkingArea;
    }

    public void setParkingArea(String parkingArea) {
        this.parkingArea = parkingArea;
    }

    public List<RunningWaybill> getListRunningWaybill() {
        return listRunningWaybill;
    }

    public void setListRunningWaybill(List<RunningWaybill> listRunningWaybill) {
        this.listRunningWaybill = listRunningWaybill;
    }

    public String getRunningWaybillDesc() {
        return runningWaybillDesc;
    }

    public void setRunningWaybillDesc(String runningWaybillDesc) {
        this.runningWaybillDesc = runningWaybillDesc;
    }

    public boolean getIsAcceptAllocateOrder() {
        return isAcceptAllocateOrder;
    }

    public void setIsAcceptAllocateOrder(boolean isAcceptAllocateOrder) {
        this.isAcceptAllocateOrder = isAcceptAllocateOrder;
    }

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
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

    public String getGoCityLicenseTypeText() {
        return goCityLicenseTypeText;
    }

    public void setGoCityLicenseTypeText(String goCityLicenseTypeText) {
        this.goCityLicenseTypeText = goCityLicenseTypeText;
    }

    public List<VehicleMismatchReason> getListVehicleMismatchReason() {
        return listVehicleMismatchReason;
    }

    public void setListVehicleMismatchReason(List<VehicleMismatchReason> listVehicleMismatchReason) {
        this.listVehicleMismatchReason = listVehicleMismatchReason;
    }

    @Override
    public String toString() {
        return "FlightUsageVo [FlightId=" + FlightId + ", plateNumber=" + plateNumber + ", vehicleStatusName="
                + vehicleStatusName + ", driverName=" + driverName + ", driverPhone=" + driverPhone
                + ", driverStatusName=" + driverStatusName + ", startTime=" + startTime + ", endTime=" + endTime
                + ", capacityTypeName=" + capacityTypeName + ", capacityStatusName=" + capacityStatusName
                + ", vehicleId=" + vehicleId + ", amsDriverId=" + amsDriverId + ", goCityLicenseType="
                + goCityLicenseType + ", parkingArea=" + parkingArea + ", listRunningWaybill=" + listRunningWaybill
                + "]";
    }

}
