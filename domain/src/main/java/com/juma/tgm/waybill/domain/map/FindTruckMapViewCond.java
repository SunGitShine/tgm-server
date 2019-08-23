package com.juma.tgm.waybill.domain.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FindTruckMapViewCond implements Serializable {


    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 313117646259258089L;

    private Integer waybillId;
    
    //停放区域
    private String parkingRegionCode;
    
    private Integer driverId;
    
    //司机姓名
    private String driverName;
    
    //入城证
    private Integer entryLicense;
    
    //车牌
    private String plateNumber;
    
    //组织ids
    private List<Integer> departmentIds = new ArrayList<Integer>();
    
    //业务区域
    private List<String> areaCodeList = new ArrayList<String>();

    public String getParkingRegionCode() {
        return parkingRegionCode;
    }

    public void setParkingRegionCode(String parkingRegionCode) {
        this.parkingRegionCode = parkingRegionCode;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getEntryLicense() {
        return entryLicense;
    }

    public void setEntryLicense(Integer entryLicense) {
        this.entryLicense = entryLicense;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public List<Integer> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<Integer> departmentIds) {
        this.departmentIds = departmentIds;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public List<String> getAreaCodeList() {
        return areaCodeList;
    }

    public void setAreaCodeList(List<String> areaCodeList) {
        this.areaCodeList = areaCodeList;
    }
    
}
