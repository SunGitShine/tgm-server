package com.juma.tgm.task.vo.gateway;

import com.google.common.base.Splitter;
import com.juma.tgm.task.enums.Week;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel(value = "任务信息")
public class Task implements Serializable {

    @ApiModelProperty(value = "任务id")
    private Integer taskId;

    @ApiModelProperty(value = "项目id")
    private Integer projectId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目状态 1未启动 2运行中 3已暂停 4已结束")
    private Integer projectStatus;

    @ApiModelProperty(value = "路线名称")
    private String roadMapName;

    @ApiModelProperty(value = "任务开始日期")
    private Date startDate;

    @ApiModelProperty(value = "任务结束日期")
    private Date endDate;

    @ApiModelProperty(value = "是否标准时间")
    private Boolean isStandardTime;

    @ApiModelProperty(value = "任务结算周期")
    private Integer billPeriod;

    @ApiModelProperty(value = "项目结算周期")
    private Integer projectBillPeriod;

    @ApiModelProperty(value = "配送周期 比如1,2,3,4")
    private String deliveryPeriod;

    @ApiModelProperty(value = "配送周期 比如[\"周一\",\"周二\"]")
    private List<String> deliveryPeriodViews = new ArrayList<>();

    @ApiModelProperty(value = "配送时间")
    private String deliveryPeriodTime;

    @ApiModelProperty(value = "承运商id")
    private Integer vendorId;

    @ApiModelProperty(value = "承运商名字")
    private String vendorName;

    @ApiModelProperty(value = "承运商电话")
    private String vendorContactPhone;

    @ApiModelProperty(value = "司机名字")
    private String driverName;

    @ApiModelProperty(value = "车牌")
    private String plateNumber;

    @ApiModelProperty(value = "任务车型")
    private List<String> truckTypeNames = new ArrayList<>();

    @ApiModelProperty(value = "配送车辆车型")
    private String truckTypeName;

    @ApiModelProperty(value = "任务状态 0:待指派运力1:邀请中2:待生效3:运行中4:已暂停5:已结束6:已取消7:已过期")
    private Integer taskStatus;

    @ApiModelProperty(value = "任务邀请状态 0:待回复1:已中选2:已拒绝3:已失效4:已过期")
    private Integer ackStatus;

    @ApiModelProperty(value = "承运商拒绝原因")
    private String refuseReason;

    public Integer getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Integer getProjectBillPeriod() {
        return projectBillPeriod;
    }

    public void setProjectBillPeriod(Integer projectBillPeriod) {
        this.projectBillPeriod = projectBillPeriod;
    }

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getBillPeriod() {
        return billPeriod;
    }

    public void setBillPeriod(Integer billPeriod) {
        this.billPeriod = billPeriod;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRoadMapName() {
        return roadMapName;
    }

    public void setRoadMapName(String roadMapName) {
        this.roadMapName = roadMapName;
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

    public Boolean getIsStandardTime() {
        return isStandardTime;
    }

    public void setIsStandardTime(Boolean isStandardTime) {
        this.isStandardTime = isStandardTime;
    }

    public String getDeliveryPeriod() {
        return deliveryPeriod;
    }

    public void setDeliveryPeriod(String deliveryPeriod) {
        this.deliveryPeriod = deliveryPeriod;
    }

    public List<String> getDeliveryPeriodViews() {
        deliveryPeriodViews.clear();
        if (StringUtils.isBlank(deliveryPeriod)) return deliveryPeriodViews;
        if (deliveryPeriod.contains("1,2,3,4,5,6,7")) {
            deliveryPeriodViews.add("每天");
            return deliveryPeriodViews;
        }
        List<String> strings = Splitter.on(",").splitToList(deliveryPeriod);
        for (String s : strings) {
            deliveryPeriodViews.add(Week.getWeekDesc(s));
        }
        return deliveryPeriodViews;
    }

    public static void main(String[] args) {
        Task task = new Task();
        task.setDeliveryPeriod("1,2,3,4,5");
        System.out.println(task.getDeliveryPeriodViews());
    }

    public void setDeliveryPeriodViews(List<String> deliveryPeriodViews) {
        this.deliveryPeriodViews = deliveryPeriodViews;
    }

    public String getDeliveryPeriodTime() {
        return deliveryPeriodTime;
    }

    public void setDeliveryPeriodTime(String deliveryPeriodTime) {
        this.deliveryPeriodTime = deliveryPeriodTime;
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

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public List<String> getTruckTypeNames() {
        return truckTypeNames;
    }

    public void setTruckTypeNames(List<String> truckTypeNames) {
        this.truckTypeNames = truckTypeNames;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getAckStatus() {
        return ackStatus;
    }

    public void setAckStatus(Integer ackStatus) {
        this.ackStatus = ackStatus;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }
}
