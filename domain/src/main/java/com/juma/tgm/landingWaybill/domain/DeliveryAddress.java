package com.juma.tgm.landingWaybill.domain;

import java.io.Serializable;

/**
 * @ClassName DeliveryAddress.java
 * @Description 落地配取货地信息
 * @author Libin.Wei
 * @Date 2017年12月6日 上午10:23:29
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class DeliveryAddress implements Serializable {

    private static final long serialVersionUID = -1340310635286411464L;
    private String addressDetail;
    private String coordinates;

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

}
