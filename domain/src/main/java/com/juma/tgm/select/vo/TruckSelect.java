package com.juma.tgm.select.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "车辆")
public class TruckSelect implements Serializable {

    @ApiModelProperty(value = "车辆id")
    private Integer truckId;

    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

    @ApiModelProperty(value = "车型")
    private String truckTypeName;

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
    }
}
