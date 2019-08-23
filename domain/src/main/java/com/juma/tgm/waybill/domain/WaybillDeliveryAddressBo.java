package com.juma.tgm.waybill.domain;

import java.io.Serializable;

public class WaybillDeliveryAddressBo implements Serializable {

    private static final long serialVersionUID = -8724520023485234199L;
    private WaybillDeliveryAddress deliveryAddress;
    private String city;

    public WaybillDeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(WaybillDeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
