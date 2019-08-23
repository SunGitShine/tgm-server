package com.juma.tgm.transportReport.domain;

import java.io.Serializable;

import com.juma.tgm.waybill.domain.Waybill;

/**
 * @ClassName WaybillSummary.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年8月15日 下午3:04:25
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class WaybillSummary implements Serializable {

    private Waybill waybill;
    private String goodsType;
    private String goodsWeight;
    private String goodsVolume;
    private String goodsAmount;

    public Waybill getWaybill() {
        return waybill;
    }

    public void setWaybill(Waybill waybill) {
        this.waybill = waybill;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(String goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public String getGoodsVolume() {
        return goodsVolume;
    }

    public void setGoodsVolume(String goodsVolume) {
        this.goodsVolume = goodsVolume;
    }

    public String getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(String goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

}
