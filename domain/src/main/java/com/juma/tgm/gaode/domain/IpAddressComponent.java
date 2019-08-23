package com.juma.tgm.gaode.domain;

import java.io.Serializable;

/**
 * @ClassName IpAddressComponent.java
 * @Description ip定位返回值
 * @author Libin.Wei
 * @Date 2017年11月21日 下午5:13:49
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class IpAddressComponent implements Serializable {

    private static final long serialVersionUID = -2332337120064955848L;
    private Integer status;
    private String info;
    private String infocode;
    private String province;
    private String city;
    // 城市的adcode编码
    private String adcode;
    // 所在城市矩形区域范围:所在城市范围的左下右上对标对
    private String rectangle;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getRectangle() {
        return rectangle;
    }

    public void setRectangle(String rectangle) {
        this.rectangle = rectangle;
    }

}
