package com.juma.tgm.base.domain;

import java.io.Serializable;

/**
 * @ClassName BaseTruckInfo.java
 * @Description 车辆设备基础信息
 * @author Libin.Wei
 * @Date 2017年2月16日 下午8:48:06
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class BaseTruckDeviceInfo implements Serializable {

    private static final long serialVersionUID = -1812914887832589317L;
    private String plateNumber;
    private String deviceNo;
    private Integer deviceType;

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
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

    @Override
    public String toString() {
        return "BaseTruckDeviceInfo [plateNumber=" + plateNumber + ", deviceNo=" + deviceNo + ", deviceType="
                + deviceType + "]";
    }

}
