package com.juma.tgm.flight.domain.bo;

import java.io.Serializable;

/**
 * @ClassName VehicleMismatchReason.java
 * @Description 车辆不符合原因
 * @author Libin.Wei
 * @Date 2018年7月31日 下午2:25:43
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class VehicleMismatchReason implements Serializable {

    // 不符合项
    private String name;
    // 值
    private String value;
    // 原因
    private String reason;

    public VehicleMismatchReason() {
    }

    public VehicleMismatchReason(String name, String value, String reason) {
        super();
        this.name = name;
        this.value = value;
        this.reason = reason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
