package com.juma.tgm.waybill.domain;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
public class DistributedOrder {
    String orderId;
    String plateNumber;
    Integer flightId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

}
