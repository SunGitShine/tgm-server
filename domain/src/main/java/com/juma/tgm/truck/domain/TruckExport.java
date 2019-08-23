package com.juma.tgm.truck.domain;

import java.io.Serializable;
import java.util.Date;

import me.about.poi.ExcelColumn;

/**
 * @ClassName TruckExport.java
 * @Description 货车导出
 * @author Libin.Wei
 * @Date 2017年2月21日 下午4:20:19
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class TruckExport implements Serializable {

    private static final long serialVersionUID = -3398192808891275128L;
    @ExcelColumn(name = "车牌号", width = 30)
    private String plateNumber;
    @ExcelColumn(name = "品牌", width = 30)
    private String brandName;
    @ExcelColumn(name = "车型", width = 30)
    private String truckTypeName;
    @ExcelColumn(name = "载重(吨)", width = 30)
    private Integer maxLoadCapacity;
    @ExcelColumn(name = "添加时间", width = 30)
    private String cteateDate;
    @ExcelColumn(name = "入城证", width = 30)
    private String entryLicense;
    @ExcelColumn(name = "入城证有效期", width = 30)
    private Date goCityLicenseExpireTime;
    @ExcelColumn(name = "停放地", width = 30)
    private String parkingRegion;
    @ExcelColumn(name = "司机姓名", width = 30)
    private String driverName;
    @ExcelColumn(name = "司机电话", width = 30)
    private String driverPhone;

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
    }

    public Integer getMaxLoadCapacity() {
        return maxLoadCapacity;
    }

    public void setMaxLoadCapacity(Integer maxLoadCapacity) {
        this.maxLoadCapacity = maxLoadCapacity;
    }

    public String getCteateDate() {
        return cteateDate;
    }

    public void setCteateDate(String cteateDate) {
        this.cteateDate = cteateDate;
    }

    public String getEntryLicense() {
        return entryLicense;
    }

    public void setEntryLicense(String entryLicense) {
        this.entryLicense = entryLicense;
    }

    public Date getGoCityLicenseExpireTime() {
        return goCityLicenseExpireTime;
    }

    public void setGoCityLicenseExpireTime(Date goCityLicenseExpireTime) {
        this.goCityLicenseExpireTime = goCityLicenseExpireTime;
    }

    public String getParkingRegion() {
        return parkingRegion;
    }

    public void setParkingRegion(String parkingRegion) {
        this.parkingRegion = parkingRegion;
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

}
