package com.juma.tgm.gaode.domain;

import java.io.Serializable;

/**
 * @ClassName CoordinateConvert.java
 * @Description 坐标转换
 * @author Libin.Wei
 * @Date 2017年2月23日 下午5:59:46
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class CoordinateConvert implements Serializable {

    private static final long serialVersionUID = 4018629514995116355L;
    private Integer status;
    private String locations;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }
}
