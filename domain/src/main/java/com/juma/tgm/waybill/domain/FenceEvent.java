package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @ClassName FenceEvent.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年2月27日 下午5:32:40
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class FenceEvent implements Serializable {

    private static final long serialVersionUID = 7629008598796570513L;
    private Integer fenceId;
    private String plateNumber;
    private String deviceId;
    private Integer deviceType;
    private String event;
    private String lat;
    private String lng;
    private String coordinates;
    private String callback;
    private Date timestamp;

    public Integer getFenceId() {
        return fenceId;
    }

    public void setFenceId(Integer fenceId) {
        this.fenceId = fenceId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getCoordinates() {
        if (StringUtils.isNotBlank(lat) && StringUtils.isNotBlank(lng)) {
            return StringUtils.isNotBlank(coordinates) ? coordinates : (lng + "," + lat);
        }
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    // 电子围栏出发类型
    public enum TouchType {
        ENTRY(1,"车辆进入"),
        EXIT(2,"车辆离开");
        
        private int code;
        
        private String descr;
        
        private TouchType(int code,String descr){
            this.code = code;
            this.descr = descr;
        }

        public int getCode() {
            return code;
        }

        public String getDescr() {
            return descr;
        }
    }

    @Override
    public String toString() {
        return "FenceEvent [fenceId=" + fenceId + ", plateNumber=" + plateNumber + ", deviceId=" + deviceId
                + ", deviceType=" + deviceType + ", event=" + event + ", lat=" + lat + ", lng=" + lng + ", coordinates="
                + coordinates + ", callback=" + callback + "]";
    }

}
