package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WaybillMonitor implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1497133236510416784L;

    private Integer waybillId;
    
    private Integer truckId;
    
    private String waybillNo;
    
    private List<WaybillDeliveryAddress> deliveryAddr = new ArrayList<WaybillDeliveryAddress>();
    
    private List<WaybillReceiveAddress> receiveAddr  = new ArrayList<WaybillReceiveAddress>();
    
    private String plateNumber;
    
    private String driverName;
    
    private String driverPhone;
    
    private String poi;
    
    private Integer statusView;

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public List<WaybillDeliveryAddress> getDeliveryAddr() {
        return deliveryAddr;
    }

    public void setDeliveryAddr(List<WaybillDeliveryAddress> deliveryAddr) {
        this.deliveryAddr = deliveryAddr;
    }

    public List<WaybillReceiveAddress> getReceiveAddr() {
        return receiveAddr;
    }

    public void setReceiveAddr(List<WaybillReceiveAddress> receiveAddr) {
        this.receiveAddr = receiveAddr;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getPoi() {
        return poi;
    }

    public void setPoi(String poi) {
        this.poi = poi;
    }

    public Integer getStatusView() {
        return statusView;
    }

    public void setStatusView(Integer statusView) {
        this.statusView = statusView;
    }
    
}
