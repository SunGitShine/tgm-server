package com.juma.tgm.truck.domain;

import java.io.Serializable;

/**
 * @ClassName TruckExtend.java
 * @Description 车辆扩展信息
 * @author Libin.Wei
 * @Date 2018年8月22日 下午2:26:28
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class TruckExtend implements Serializable {

    private String plateNumber;
    private Integer maxLoadCapacity;
    private String vehicleFrameNo;

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getMaxLoadCapacity() {
        return maxLoadCapacity;
    }

    public void setMaxLoadCapacity(Integer maxLoadCapacity) {
        this.maxLoadCapacity = maxLoadCapacity;
    }

    public String getVehicleFrameNo() {
        return vehicleFrameNo;
    }

    public void setVehicleFrameNo(String vehicleFrameNo) {
        this.vehicleFrameNo = vehicleFrameNo;
    }

}
