package com.juma.tgm.task.vo.gateway;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "恢复配送")
public class UpdateToRecoverDelivery implements Serializable {

    @ApiModelProperty(value = "日历id")
    private Integer calendarId;

    @ApiModelProperty(value = "司机id")
    private Integer driverId;

    @ApiModelProperty(value = "车辆id")
    private Integer truckId;

    @ApiModelProperty(value = "原因")
    private String reason;

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public Integer getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(Integer calendarId) {
        this.calendarId = calendarId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
