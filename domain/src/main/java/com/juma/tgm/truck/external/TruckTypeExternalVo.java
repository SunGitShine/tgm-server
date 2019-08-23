package com.juma.tgm.truck.external;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 * @ClassName TruckTypeExternalVo
 * @Description TODO
 * @Author weilibin
 * @Date 2019-07-09 09:13
 * @Version 1.0.0
 */

public class TruckTypeExternalVo implements Serializable {

    private Integer truckTypeId;
    private Integer vehicleBoxType;
    private Integer vehicleBoxLenght;
    private String truckTypeName;
    private String vehicleBoxTypeName;
    private String vehicleBoxLenghtName;

    public Integer getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(Integer truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public Integer getVehicleBoxType() {
        return vehicleBoxType;
    }

    public void setVehicleBoxType(Integer vehicleBoxType) {
        this.vehicleBoxType = vehicleBoxType;
    }

    public Integer getVehicleBoxLenght() {
        return vehicleBoxLenght;
    }

    public void setVehicleBoxLenght(Integer vehicleBoxLenght) {
        this.vehicleBoxLenght = vehicleBoxLenght;
    }

    public String getTruckTypeName() {
        if (StringUtils.isNotBlank(truckTypeName)) {
            return truckTypeName;
        }

        return (StringUtils.isBlank(vehicleBoxTypeName) ? "" : vehicleBoxTypeName)
            + (StringUtils.isBlank(vehicleBoxLenghtName) ? "" : vehicleBoxLenghtName);
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
    }

    public String getVehicleBoxTypeName() {
        return vehicleBoxTypeName;
    }

    public void setVehicleBoxTypeName(String vehicleBoxTypeName) {
        this.vehicleBoxTypeName = vehicleBoxTypeName;
    }

    public String getVehicleBoxLenghtName() {
        return vehicleBoxLenghtName;
    }

    public void setVehicleBoxLenghtName(String vehicleBoxLenghtName) {
        this.vehicleBoxLenghtName = vehicleBoxLenghtName;
    }
}
