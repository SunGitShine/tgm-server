package com.juma.tgm.waybill.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "运单提供给OMS")
public class WaybillToOmsOrder implements Serializable {

    @ApiModelProperty(value = "运单id")
    private Integer waybillId;

    @ApiModelProperty(value = "运单编号")
    private String waybillNo;

    @ApiModelProperty(value = "项目id")
    private Integer projectId;

    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "司机id")
    private Integer driverId;

    @ApiModelProperty(value = "车辆id")
    private Integer truckId;

    @ApiModelProperty(value = "用车时间")
    private Date planDeliveryTime;

    @ApiModelProperty(value = "运单状态 1:派车中 2:待配送 3:配送中 4:待支付 5:已完成 6:已取消")
    private Integer statusView;

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

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public Date getPlanDeliveryTime() {
        return planDeliveryTime;
    }

    public void setPlanDeliveryTime(Date planDeliveryTime) {
        this.planDeliveryTime = planDeliveryTime;
    }

    public Integer getStatusView() {
        return statusView;
    }

    public void setStatusView(Integer statusView) {
        this.statusView = statusView;
    }


}
