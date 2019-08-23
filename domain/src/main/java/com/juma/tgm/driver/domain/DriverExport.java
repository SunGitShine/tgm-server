package com.juma.tgm.driver.domain;

import java.io.Serializable;
import java.util.Date;

import me.about.poi.ExcelColumn;

/**
 * @ClassName DriverExport.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年2月21日 下午3:33:51
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class DriverExport implements Serializable {

    private static final long serialVersionUID = -6394951602662095777L;
    private Integer driverId;
    @ExcelColumn(name = "姓名", width = 30)
    private String nickname;
    @ExcelColumn(name = "联系电话", width = 30)
    private String contactPhone;
    @ExcelColumn(name = "注册时间", width = 30)
    private String registerDate;
    @ExcelColumn(name = "最后登录时间", width = 30)
    private Date lastLoginTime;
    @ExcelColumn(name = "用户类型", width = 30)
    private String driverTypeStr;
    @ExcelColumn(name = "居住地", width = 30)
    private String domicile;
    @ExcelColumn(name = "状态", width = 30)
    private String statusStr;
    @ExcelColumn(name = "车牌号", width = 30)
    private String plateNumber;
    @ExcelColumn(name = "车辆类型", width = 30)
    private String truckTypeName;
    /**
     * 停工次数
     */
    private Integer stopWorkCount = 0;

    /**
     * 车辆停放区域
     */
    private String truckParkingName;

    /**
     * 在职状态
     */
    private Integer employeeClass;

    /**
     * 出工状态
     */
    private Integer taskStatus;

    /**
     * 司机类型
     */
    private Integer driverType;

    /**
     * 业务区域
     */
    private String businessArea;

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public Integer getDriverId() {
        return driverId;

    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getDriverTypeStr() {
        return driverTypeStr;
    }

    public void setDriverTypeStr(String driverTypeStr) {
        this.driverTypeStr = driverTypeStr;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
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

    public Integer getStopWorkCount() {
        return stopWorkCount;
    }

    public void setStopWorkCount(Integer stopWorkCount) {
        this.stopWorkCount = stopWorkCount;
    }

    public String getTruckParkingName() {
        return truckParkingName;
    }

    public void setTruckParkingName(String truckParkingName) {
        this.truckParkingName = truckParkingName;
    }

    public Integer getEmployeeClass() {
        return employeeClass;
    }

    public void setEmployeeClass(Integer employeeClass) {
        this.employeeClass = employeeClass;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getDriverType() {
        return driverType;
    }

    public void setDriverType(Integer driverType) {
        this.driverType = driverType;
    }
}
