package com.juma.tgm.task.vo.gateway;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "邀请承运商")
public class InviteRequest implements Serializable {

    @ApiModelProperty(value = "任务id")
    private Integer taskId;

    @ApiModelProperty(value = "项目id")
    private Integer projectId;

    @ApiModelProperty(value = "承运商id")
    private Integer vendorId;

    @ApiModelProperty(value = "司机id")
    private Integer driverId;

    @ApiModelProperty(value = "车辆id")
    private Integer truckId;

    @ApiModelProperty(value = "账期")
    private Integer billPeriod;

    @ApiModelProperty(value = "账期原因")
    private String billPeriodReason;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public Integer getBillPeriod() {
        return billPeriod;
    }

    public void setBillPeriod(Integer billPeriod) {
        this.billPeriod = billPeriod;
    }

    public String getBillPeriodReason() {
        return billPeriodReason;
    }

    public void setBillPeriodReason(String billPeriodReason) {
        this.billPeriodReason = billPeriodReason;
    }
}
