package com.juma.tgm.select.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "运力")
public class Capacity implements Serializable {

    @ApiModelProperty(value = "承运商id")
    private Integer vendorId;

    @ApiModelProperty(value = "承运商名称")
    private String vendorName;

    @ApiModelProperty(value = "承运商电话")
    private String vendorContactPhone;

    @ApiModelProperty(value = "车辆id")
    private Integer truckId;

    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

    @ApiModelProperty(value = "司机id")
    private Integer driverId;

    @ApiModelProperty(value = "司机名称")
    private String driverName;

    @ApiModelProperty(value = "司机电话")
    private String driverContactPhone;

    @ApiModelProperty(value = "状态 0:无效 1:有效")
    private Boolean isValid;

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorContactPhone() {
        return vendorContactPhone;
    }

    public void setVendorContactPhone(String vendorContactPhone) {
        this.vendorContactPhone = vendorContactPhone;
    }

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

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverContactPhone() {
        return driverContactPhone;
    }

    public void setDriverContactPhone(String driverContactPhone) {
        this.driverContactPhone = driverContactPhone;
    }
}
