package com.juma.tgm.task.vo.gateway;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel(value = "项目对应的任务")
public class GroupTaskDetail implements Serializable {

    @ApiModelProperty(value = "任务id")
    private Integer taskId;

    @ApiModelProperty(value = "项目id")
    private Integer projectId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "路线id")
    private Integer roadMapId;

    @ApiModelProperty(value = "线路名称")
    private String roadMapName;

    @ApiModelProperty(value = "任务编号")
    private String taskNo;

    @ApiModelProperty(value = "开始日期")
    private Date startDate;

    @ApiModelProperty(value = "结束日期")
    private Date endDate;

    @ApiModelProperty(value = "是否标准时间")
    private Boolean isStandardTime;

    @ApiModelProperty(value = "配送周期 比如:1,2,3,4")
    private String deliveryPeriod;

    @ApiModelProperty(value = "配送时间 比如:09:30,10:30")
    private String deliveryPeriodTime;

    @ApiModelProperty(value = "用车要求")
    private String functions;

    @ApiModelProperty(value = "用车备注")
    private String requireMark;

    @ApiModelProperty(value = "结算账期")
    private Integer billPeriod;

    @ApiModelProperty(value = "仓库")
    private Depot depot;

    @ApiModelProperty(value = "是否固定点")
    private Boolean isFixedDelivery;

    @ApiModelProperty(value = "固定点")
    private List<FixedDeliveryInfo> fixedDeliveries = new ArrayList<>();

    @ApiModelProperty(value = "非固定点")
    private NotFixedDeliveryInfo notFixedDeliveryInfo;

    @ApiModelProperty(value = "任务状态 0:待指派运力1:邀请中2:待生效3:运行中4:已暂停5:已结束6:已取消7:已过期")
    private Integer taskStatus;

    @ApiModelProperty(value = "邀请状态 0：待回复1：已中选2：已拒绝3：已失效4：已过期")
    private Integer ackStatus;

    @ApiModelProperty(value = "邀请时间")
    private Date inviteTime;

    @ApiModelProperty(value = "司机id")
    private Integer driverId;

    @ApiModelProperty(value = "车辆id")
    private Integer truckId;

    public Boolean getIsStandardTime() {
        return isStandardTime;
    }

    public void setIsStandardTime(Boolean isStandardTime) {
        this.isStandardTime = isStandardTime;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }

    public String getRequireMark() {
        return requireMark;
    }

    public void setRequireMark(String requireMark) {
        this.requireMark = requireMark;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getAckStatus() {
        return ackStatus;
    }

    public void setAckStatus(Integer ackStatus) {
        this.ackStatus = ackStatus;
    }

    public Date getInviteTime() {
        return inviteTime;
    }

    public void setInviteTime(Date inviteTime) {
        this.inviteTime = inviteTime;
    }

    public Integer getRoadMapId() {
        return roadMapId;
    }

    public void setRoadMapId(Integer roadMapId) {
        this.roadMapId = roadMapId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRoadMapName() {
        return roadMapName;
    }

    public void setRoadMapName(String roadMapName) {
        this.roadMapName = roadMapName;
    }

    public String getDeliveryPeriod() {
        return deliveryPeriod;
    }

    public void setDeliveryPeriod(String deliveryPeriod) {
        this.deliveryPeriod = deliveryPeriod;
    }

    public String getDeliveryPeriodTime() {
        return deliveryPeriodTime;
    }

    public void setDeliveryPeriodTime(String deliveryPeriodTime) {
        this.deliveryPeriodTime = deliveryPeriodTime;
    }

    public Integer getBillPeriod() {
        return billPeriod;
    }

    public void setBillPeriod(Integer billPeriod) {
        this.billPeriod = billPeriod;
    }

    public Boolean getIsFixedDelivery() {
        return isFixedDelivery;
    }

    public void setIsFixedDelivery(Boolean isFixedDelivery) {
        this.isFixedDelivery = isFixedDelivery;
    }

    public List<FixedDeliveryInfo> getFixedDeliveries() {
        return fixedDeliveries;
    }

    public void setFixedDeliveries(List<FixedDeliveryInfo> fixedDeliveries) {
        this.fixedDeliveries = fixedDeliveries;
    }

    public NotFixedDeliveryInfo getNotFixedDeliveryInfo() {
        return notFixedDeliveryInfo;
    }

    public void setNotFixedDeliveryInfo(NotFixedDeliveryInfo notFixedDeliveryInfo) {
        this.notFixedDeliveryInfo = notFixedDeliveryInfo;
    }

}
