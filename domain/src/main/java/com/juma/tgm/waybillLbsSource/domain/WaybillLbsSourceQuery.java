package com.juma.tgm.waybillLbsSource.domain;

import java.io.Serializable;
import java.util.Date;

import me.about.poi.ExcelColumn;

/**
 * @ClassName WaybillLbsSource.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年6月19日 下午5:29:44
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class WaybillLbsSourceQuery implements Serializable {

    private static final long serialVersionUID = 427165269686661645L;
    private Integer waybillLbsSourceId;
    private Integer waybillId;
    private Integer sign;
    private String deviceNo;
    private Integer deviceType;
    private Date createTime;
    private Date planDeliveryTime;
    private String waybillTypeName;
    @ExcelColumn(name = "运单号")
    private String waybillNo;
    @ExcelColumn(name = "用车时间")
    private String planDeliveryDate;
    @ExcelColumn(name = "运单类型")
    private String waybillTypeStr;
    @ExcelColumn(name = "车牌号")
    private String plateNumber;
    @ExcelColumn(name = "司机姓名")
    private String driverName;
    @ExcelColumn(name = "司机电话")
    private String driverMobile;
    @ExcelColumn(name = "所属客户")
    private String customerName;
    @ExcelColumn(name = "客户经理")
    private String customerManagerName;
    @ExcelColumn(name = "迟到时长")
    private String timeConsuming;
    @ExcelColumn(name= "是否报备")
    private String reportStr;

    private Integer driverId;
    private Integer truckId;
    private Integer customerId;
    private Integer customerManagerId;

    public Integer getWaybillLbsSourceId() {
        return waybillLbsSourceId;
    }

    public void setWaybillLbsSourceId(Integer waybillLbsSourceId) {
        this.waybillLbsSourceId = waybillLbsSourceId;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public String getTimeConsuming() {
        return timeConsuming;
    }

    public void setTimeConsuming(String timeConsuming) {
        this.timeConsuming = timeConsuming;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getPlanDeliveryDate() {
        return planDeliveryDate;
    }

    public void setPlanDeliveryDate(String planDeliveryDate) {
        this.planDeliveryDate = planDeliveryDate;
    }

    public String getWaybillTypeName() {
        return waybillTypeName;
    }

    public void setWaybillTypeName(String waybillTypeName) {
        this.waybillTypeName = waybillTypeName;
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

    public String getDriverMobile() {
        return driverMobile;
    }

    public void setDriverMobile(String driverMobile) {
        this.driverMobile = driverMobile;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getReportStr() {
        return reportStr;
    }

    public void setReportStr(String reportStr) {
        this.reportStr = reportStr;
    }

    public String getWaybillTypeStr() {
        return waybillTypeStr;
    }

    public void setWaybillTypeStr(String waybillTypeStr) {
        this.waybillTypeStr = waybillTypeStr;
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerManagerName() {
        return customerManagerName;
    }

    public void setCustomerManagerName(String customerManagerName) {
        this.customerManagerName = customerManagerName;
    }

    public Integer getCustomerManagerId() {
        return customerManagerId;
    }

    public void setCustomerManagerId(Integer customerManagerId) {
        this.customerManagerId = customerManagerId;
    }

    public enum WaybillType {

        BESPEAK_WAYBILL(1, "预约单"),
        TEMPORARY_WAYBILL(2, "临时单");

        private int code;
        private String desc;

        private WaybillType(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDescr() {
            return desc;
        }
    }
}
