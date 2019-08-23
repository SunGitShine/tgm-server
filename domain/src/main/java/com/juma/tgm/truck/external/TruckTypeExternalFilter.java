package com.juma.tgm.truck.external;

import java.io.Serializable;

/**
 * @ClassName TruckTypeExternalFilter
 * @Description TODO
 * @Author weilibin
 * @Date 2019-07-09 09:26
 * @Version 1.0.0
 */

public class TruckTypeExternalFilter implements Serializable {

    private Integer vehicleBoxType;
    private Integer vehicleBoxLength;

    public Integer getVehicleBoxType() {
        return vehicleBoxType;
    }

    public void setVehicleBoxType(Integer vehicleBoxType) {
        this.vehicleBoxType = vehicleBoxType;
    }

    public Integer getVehicleBoxLength() {
        return vehicleBoxLength;
    }

    public void setVehicleBoxLength(Integer vehicleBoxLength) {
        this.vehicleBoxLength = vehicleBoxLength;
    }
}
