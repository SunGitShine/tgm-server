package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WaybillReceiveAddressResponse.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年4月23日 下午4:33:36
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class WaybillReceiveAddressResponse implements Serializable {

    private static final long serialVersionUID = -4452960980196338639L;

    private WaybillReceiveAddress waybillReceiveAddress;

    private List<WaybillReceiveAddressCargo> listWaybillReceiveAddressCargo = new ArrayList<WaybillReceiveAddressCargo>();

    public WaybillReceiveAddressResponse() {
    }

    public WaybillReceiveAddressResponse(WaybillReceiveAddress waybillReceiveAddress,
            List<WaybillReceiveAddressCargo> listWaybillReceiveAddressCargo) {
        super();
        this.waybillReceiveAddress = waybillReceiveAddress;
        this.listWaybillReceiveAddressCargo = listWaybillReceiveAddressCargo == null
                ? new ArrayList<WaybillReceiveAddressCargo>() : listWaybillReceiveAddressCargo;
    }

    public WaybillReceiveAddress getWaybillReceiveAddress() {
        return waybillReceiveAddress;
    }

    public void setWaybillReceiveAddress(WaybillReceiveAddress waybillReceiveAddress) {
        this.waybillReceiveAddress = waybillReceiveAddress;
    }

    public List<WaybillReceiveAddressCargo> getListWaybillReceiveAddressCargo() {
        return listWaybillReceiveAddressCargo;
    }

    public void setListWaybillReceiveAddressCargo(List<WaybillReceiveAddressCargo> listWaybillReceiveAddressCargo) {
        this.listWaybillReceiveAddressCargo = listWaybillReceiveAddressCargo;
    }
}
