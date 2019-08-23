package com.juma.tgm.waybill.domain;

import java.io.Serializable;

public class WaybillReceiveAddressBo implements Serializable {

    private static final long serialVersionUID = 3375940649315102357L;
    private WaybillReceiveAddress receiveAddress;
    private WaybillReceiveAddressCargo waybillReceiveAddressCargo;
    private String city;

    public WaybillReceiveAddress getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(WaybillReceiveAddress receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public WaybillReceiveAddressCargo getWaybillReceiveAddressCargo() {
        return waybillReceiveAddressCargo;
    }

    public void setWaybillReceiveAddressCargo(WaybillReceiveAddressCargo waybillReceiveAddressCargo) {
        this.waybillReceiveAddressCargo = waybillReceiveAddressCargo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
