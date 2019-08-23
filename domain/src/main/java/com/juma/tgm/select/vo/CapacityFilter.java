package com.juma.tgm.select.vo;

import java.io.Serializable;
import java.util.List;

public class CapacityFilter implements Serializable {

    private Integer tenantId;
    private List<String> areaCodeList;
    private Integer driverId;
    private Integer truckId;
    private Integer vendorId;
    private Integer goCityLicenseType;
    private Integer vehicleBoxType;
    private Integer vehicleBoxLength;
    private Boolean capacityStatus;
    private Integer waybillId;

    private String plateNumber;
    private Boolean status;
    private Boolean isDelete;

    private Integer pageNo = 1;
    private Integer pageSize = 15;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public List<String> getAreaCodeList() {
        return areaCodeList;
    }

    public void setAreaCodeList(List<String> areaCodeList) {
        this.areaCodeList = areaCodeList;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getGoCityLicenseType() {
        return goCityLicenseType;
    }

    public void setGoCityLicenseType(Integer goCityLicenseType) {
        this.goCityLicenseType = goCityLicenseType;
    }

    public Integer getVehicleBoxType() {
        return vehicleBoxType;
    }

    public void setVehicleBoxType(Integer vehicleBoxType) {
        this.vehicleBoxType = vehicleBoxType;
    }

    public Integer getVehicleBoxLength() {
        return vehicleBoxLength;
    }

    public void setVehicleBoxLength(Integer vehicleBoxLength) {
        this.vehicleBoxLength = vehicleBoxLength;
    }

    public Boolean getCapacityStatus() {
        return capacityStatus;
    }

    public void setCapacityStatus(Boolean capacityStatus) {
        this.capacityStatus = capacityStatus;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }
}
