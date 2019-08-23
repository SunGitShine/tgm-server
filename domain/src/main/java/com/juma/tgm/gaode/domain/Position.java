package com.juma.tgm.gaode.domain;

import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Position.java
 * @Description 位置信息
 * @author Libin.Wei
 * @Date 2017年2月23日 上午11:37:23
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class Position implements Serializable {

    private static final long serialVersionUID = 6740804525654114112L;
    private String address;
    private String gps;
    private Integer truckId;
    /**
     * 运单开始时间
     */
    private Date beginTime;
    /**
     * 运单结束时间
     */
    private Date endTime;

    /**
     * 设备号
     */
    private String deviceNo;

    /**
     * 设备类型
     */
    private Byte deviceType;

    /**
     * 运单状态
     */
    private Integer waybillStatusView;
    
    // 最低温度
    private Float requiredMinTemperature;
    
    // 最高温度
    private Float requiredMaxTemperature;

    /**
     * 取货地 坐标
     */
    private WaybillDeliveryAddress srcAddrs;

    /**
     * 配送地 坐标
     */
    private List<WaybillReceiveAddress> destAddrs;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Byte getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Byte deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getWaybillStatusView() {
        return waybillStatusView;
    }

    public void setWaybillStatusView(Integer waybillStatusView) {
        this.waybillStatusView = waybillStatusView;
    }

    public WaybillDeliveryAddress getSrcAddrs() {
        return srcAddrs;
    }

    public void setSrcAddrs(WaybillDeliveryAddress srcAddrs) {
        this.srcAddrs = srcAddrs;
    }

    public List<WaybillReceiveAddress> getDestAddrs() {
        return destAddrs;
    }

    public void setDestAddrs(List<WaybillReceiveAddress> destAddrs) {
        this.destAddrs = destAddrs;
    }

    @Override
    public String toString() {
        return "Position [address=" + address + ", gps=" + gps + ", truckId=" + truckId + "]";
    }

    public Float getRequiredMinTemperature() {
        return requiredMinTemperature;
    }

    public void setRequiredMinTemperature(Float requiredMinTemperature) {
        this.requiredMinTemperature = requiredMinTemperature;
    }

    public Float getRequiredMaxTemperature() {
        return requiredMaxTemperature;
    }

    public void setRequiredMaxTemperature(Float requiredMaxTemperature) {
        this.requiredMaxTemperature = requiredMaxTemperature;
    }

}
