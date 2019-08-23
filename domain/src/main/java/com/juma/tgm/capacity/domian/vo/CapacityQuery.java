package com.juma.tgm.capacity.domian.vo;

import com.juma.tgm.flight.domain.bo.RunningWaybill;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel
public class CapacityQuery implements Serializable {

    //
    @ApiModelProperty(value = "车辆ID")
    private Integer truckId;
    // 车牌号
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;
    // 司机ID
    @ApiModelProperty(value = "司机ID")
    private Integer driverId;
    // 司机姓名
    @ApiModelProperty(value = "司机姓名")
    private String driverName;
    // 司机电话
    @ApiModelProperty(value = "司机电话")
    private String driverPhone;
    // 承运商ID
    @ApiModelProperty(value = "承运商ID")
    private Integer vendorId;
    // 承运商名称
    @ApiModelProperty(value = "承运商名称")
    private String vendorName;
    // 入城证
    @ApiModelProperty(value = "入城证")
    private Integer goCityLicenseType;
    // 入城证名称
    @ApiModelProperty(value = "入城证名称")
    private String goCityLicenseTypeName;
    // 放车区域
    @ApiModelProperty(value = "放车区域")
    private String parkingArea;
    // 方量（载货体积）
    @ApiModelProperty(value = "方量（载货体积")
    private Double loadVolume;
    //载重取值（最大载重）
    @ApiModelProperty(value = "载重取值（最大载重）")
    private Integer maxLoadCapacity;
    // 进行中的运单
    @ApiModelProperty(value = "进行中的运单")
    private List<RunningWaybill> listRunningWaybill;
    // 进行中的运单中文描述
    @ApiModelProperty(value = "进行中的运单中文描述")
    private String runningWaybillDesc;
    // 车辆不匹配原因
    @ApiModelProperty(value = "车辆不匹配原因")
    private List<CapacityMismatchReason> listCapacityMismatchReason = new ArrayList<>();
    @ApiModelProperty(value = "车辆业务范围编码")
    private String areaCode;
    @ApiModelProperty(value = "车辆业务范围名称")
    private String areaName;

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

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Integer getGoCityLicenseType() {
        return goCityLicenseType;
    }

    public void setGoCityLicenseType(Integer goCityLicenseType) {
        this.goCityLicenseType = goCityLicenseType;
    }

    public String getGoCityLicenseTypeName() {
        return goCityLicenseTypeName;
    }

    public void setGoCityLicenseTypeName(String goCityLicenseTypeName) {
        this.goCityLicenseTypeName = goCityLicenseTypeName;
    }

    public String getParkingArea() {
        return parkingArea;
    }

    public void setParkingArea(String parkingArea) {
        this.parkingArea = parkingArea;
    }

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

    public List<CapacityMismatchReason> getListCapacityMismatchReason() {
        return listCapacityMismatchReason;
    }

    public void setListCapacityMismatchReason(List<CapacityMismatchReason> listCapacityMismatchReason) {
        this.listCapacityMismatchReason = listCapacityMismatchReason;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
