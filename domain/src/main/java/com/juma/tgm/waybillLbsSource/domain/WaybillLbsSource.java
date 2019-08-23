package com.juma.tgm.waybillLbsSource.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName WaybillLbsSource.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年6月19日 下午5:29:44
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class WaybillLbsSource implements Serializable {

    private static final long serialVersionUID = -5049348178616800893L;
    private Integer waybillLbsSourceId;
    private Integer waybillId;
    private Integer sign;
    private Integer timeConsuming;
    private String coordinate;
    private String deviceNo;
    private Integer deviceType;
    private Date createTime;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public Integer getTimeConsuming() {
        return timeConsuming;
    }

    public void setTimeConsuming(Integer timeConsuming) {
        this.timeConsuming = timeConsuming;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public enum Sign {

        DRIVER_LATE(1, "司机迟到"),
        ACTUAL_MILEAGE(2, "实际里程");

        private int code;
        private String desc;

        private Sign(int code, String desc) {
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
