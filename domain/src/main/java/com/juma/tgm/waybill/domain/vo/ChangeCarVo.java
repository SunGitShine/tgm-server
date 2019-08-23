package com.juma.tgm.waybill.domain.vo;

import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.waybill.domain.Waybill;

import java.io.Serializable;

/**
 * @ClassName: ChangeCarVo
 * @Description:
 * @author: liang
 * @date: 2018-09-07 19:25
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class ChangeCarVo implements Serializable {

    private Integer waybillId;
    private Integer driverId;
    private Integer truckId;
    private Integer vendorId;
    private Integer receiveWay;
    private String remark;
    private Waybill wb;
    private Driver driver;

    public ChangeCarVo() {}

    public ChangeCarVo(Integer waybillId, Integer driverId, Integer truckId, Integer vendorId, Integer receiveWay, String remark, Waybill wb, Driver driver) {
        this.waybillId = waybillId;
        this.driverId = driverId;
        this.truckId = truckId;
        this.vendorId = vendorId;
        this.receiveWay = receiveWay;
        this.remark = remark;
        this.wb = wb;
        this.driver = driver;
    }

    public int getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(int waybillId) {
        this.waybillId = waybillId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getTruckId() {
        return truckId;
    }

    public void setTruckId(int truckId) {
        this.truckId = truckId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public int getReceiveWay() {
        return receiveWay;
    }

    public void setReceiveWay(int receiveWay) {
        this.receiveWay = receiveWay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Waybill getWb() {
        return wb;
    }

    public void setWb(Waybill wb) {
        this.wb = wb;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
