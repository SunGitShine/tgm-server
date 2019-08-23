package com.juma.tgm.waybill.vo;

import java.io.Serializable;

public class TruckDeviceVo implements Serializable {

    private String deviceNo;
    private Integer deviceType;

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
}
