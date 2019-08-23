package com.juma.tgm.waybill.domain;

import java.io.Serializable;

/**
 * @ClassName WaybillOperateTrackNotRequieParam.java
 * @Description 运单轨迹非必填项
 * @author Libin.Wei
 * @Date 2017年6月23日 下午5:01:56
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class WaybillOperateTrackNotRequieParam implements Serializable {

    private static final long serialVersionUID = 5997220993468646218L;
    private String coordinate;
    private String deviceNo;
    private Integer deviceType;
    private String fenceDeviceNo;
    private Integer fenceDeviceType;
    private String remark;

    public WaybillOperateTrackNotRequieParam() {
    }

    public WaybillOperateTrackNotRequieParam(String coordinate, String deviceNo, Integer deviceType,  String remark) {
        this.coordinate = coordinate;
        this.deviceNo = deviceNo;
        this.deviceType = deviceType;
        this.remark = remark;
    }

    public WaybillOperateTrackNotRequieParam(String remark) {
        this.remark = remark;
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

    public String getFenceDeviceNo() {
        return fenceDeviceNo;
    }

    public void setFenceDeviceNo(String fenceDeviceNo) {
        this.fenceDeviceNo = fenceDeviceNo;
    }

    public Integer getFenceDeviceType() {
        return fenceDeviceType;
    }

    public void setFenceDeviceType(Integer fenceDeviceType) {
        this.fenceDeviceType = fenceDeviceType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WaybillOperateTrackNotRequieParam [coordinate=" + coordinate + ", deviceNo=" + deviceNo
                + ", deviceType=" + deviceType + ", fenceDeviceNo=" + fenceDeviceNo + ", fenceDeviceType="
                + fenceDeviceType + ", remark=" + remark + "]";
    }

}
