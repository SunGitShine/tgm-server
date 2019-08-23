package com.juma.tgm.driver.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName ReportInfo.java 路况报备
 * @author Libin.Wei
 * @Date 2017年4月27日 下午4:57:50
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */
public class ReportInfo implements Serializable {

    private static final long serialVersionUID = 17279694873737153L;
    private Integer reportInfoId;
    private Integer reportInfoType;
    private Integer driverId;
    private Integer customerId;
    private String customerName;
    private Integer waybillId;
    private String waybilNo;
    private String plateNumber;
    private String regionCode;
    private Integer tenantId;
    private String tenantCode;
    private String areaCode;
    private Date firstReportTime;
    private String remark;

    // 冗余显示
    private String areaName;
    private String reportInfoTypeView;
    // 报备次数
    private Integer ReportInfoCount;
    private List<String> imgList = new ArrayList<String>();

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getReportInfoId() {
        return reportInfoId;
    }

    public void setReportInfoId(Integer reportInfoId) {
        this.reportInfoId = reportInfoId;
    }

    public Integer getReportInfoType() {
        return reportInfoType;
    }

    public void setReportInfoType(Integer reportInfoType) {
        this.reportInfoType = reportInfoType;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
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

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public String getWaybilNo() {
        return waybilNo;
    }

    public void setWaybilNo(String waybilNo) {
        this.waybilNo = waybilNo;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Date getFirstReportTime() {
        return firstReportTime;
    }

    public void setFirstReportTime(Date firstReportTime) {
        this.firstReportTime = firstReportTime;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public String toString() {
        return "ReportInfo{" + "reportInfoId=" + reportInfoId + ", reportInfoType=" + reportInfoType + ", driverId="
                + driverId + ", customerId=" + customerId + ", waybillId=" + waybillId + ", waybilNo='" + waybilNo
                + '\'' + ", plateNumber='" + plateNumber + '\'' + ", regionCode='" + regionCode + '\''
                + ", tenantCode='" + tenantCode + '\'' + ", areaCode='" + areaCode + '\'' + ", firstReportTime="
                + firstReportTime + ", areaName='" + areaName + '\'' + '}';
    }

    public void setReportInfoTypeView(String reportInfoTypeView) {
        this.reportInfoTypeView = reportInfoTypeView;
    }

    public String getReportInfoTypeView() {
        return reportInfoTypeView;
    }

    public Integer getReportInfoCount() {
        return ReportInfoCount;
    }

    public void setReportInfoCount(Integer reportInfoCount) {
        ReportInfoCount = reportInfoCount;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }
}