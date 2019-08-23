package com.juma.tgm.cron.vo;

import java.io.Serializable;

/**
 * @ClassName WaybillMileageBo
 * @Description TODO
 * @Author weilibin
 * @Date 2019-06-11 11:23
 * @Version 1.0.0
 */

public class WaybillMileageBo implements Serializable {

    private Integer waybillId;
    private Integer actualMileage;
    private String deviceNo;
    private Integer deviceType;

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public Integer getActualMileage() {
        return actualMileage;
    }

    public void setActualMileage(Integer actualMileage) {
        this.actualMileage = actualMileage;
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
}
