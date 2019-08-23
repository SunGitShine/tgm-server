package com.juma.tgm.driver.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.juma.tgm.common.Constants;

/**
 * @ClassName ReportInfoParam.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年6月20日 下午4:36:44
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class ReportInfoParam implements Serializable {

    private static final long serialVersionUID = 5755142433648336916L;
    /** 司机ID */
    private Integer driverId;
    /** 运单ID，必填 */
    private Integer waybillId;
    /** 运单ID，必填 */
    private Integer reportInfoType;
    /** 上传图片URL，必填 */
    private List<String> imageUrlList = new ArrayList<String>();
    /** 上传图片坐标 */
    private String coordinate;
    /** 上传图片设备编码 */
    private String deviceNo;
    /** 上传图片设备类型 */
    private Integer deviceType;
    /** 首次上报时间 */
    private Date firstReportTime;
    /** 备注 */
    private String remark;
    /** 上传坐标类型：GPS, MAPBAR, BAIDU */
    private String coordsys = Constants.Coordsys.GPS.toString();
    private String regionCode;
    private String tenantCode;
    private String areaCode;

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public Integer getReportInfoType() {
        return reportInfoType;
    }

    public void setReportInfoType(Integer reportInfoType) {
        this.reportInfoType = reportInfoType;
    }

    public List<String> getImageUrlList() {
        return imageUrlList;
    }

    public void setImageUrlList(List<String> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
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

    public Date getFirstReportTime() {
        return firstReportTime;
    }

    public void setFirstReportTime(Date firstReportTime) {
        this.firstReportTime = firstReportTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCoordsys() {
        return coordsys;
    }

    public void setCoordsys(String coordsys) {
        this.coordsys = coordsys;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    
}
