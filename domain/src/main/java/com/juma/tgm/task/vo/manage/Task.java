package com.juma.tgm.task.vo.manage;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "任务")
public class Task implements Serializable {

	@ApiModelProperty(value = "任务id")
	private Integer taskId;

	@ApiModelProperty(value = "任务编号")
	private String taskNo;

	@ApiModelProperty(value = "项目id")
	private Integer projectId;

	@ApiModelProperty(value = "承运商id")
	private Integer vendorId;

	@ApiModelProperty(value = "司机id")
	private Integer driverId;

	@ApiModelProperty(value = "车辆id")
	private Integer truckId;

	private Integer roadMapId;

	private String areaCode;

	@ApiModelProperty(value = "账期")
	private Integer billPeriod;

	@ApiModelProperty(value = "项目名称")
	private String projectName;

	@ApiModelProperty(value = "客户名称")
	private String customerName;

	@ApiModelProperty(value = "路线名称")
	private String roadMapName;

	@ApiModelProperty(value = "承运商名字")
	private String vendorName;

	@ApiModelProperty(value = "承运商运营方式名称")
	private String vendorRunTypeName;

	@ApiModelProperty(value = "车牌")
	private String plateNumber;

	@ApiModelProperty(value = "配送车型")
	private List<String> truckTypeNames;

	@ApiModelProperty(value = "用车要求")
	private List<String> functions;

	@ApiModelProperty(value = "司机名字")
	private String driverName;

	@ApiModelProperty(value = "任务开始日期")
	private Date startDate;

	@ApiModelProperty(value = "任务结束日期")
	private Date endDate;

	@ApiModelProperty(value = "是否标准时间")
	private boolean isStandardTime;

	@ApiModelProperty(value = "配送周期 比如1,2,3,4")
	private String deliveryPeriod;

	@ApiModelProperty(value = "配送时间")
	private String deliveryPeriodTime;

	@ApiModelProperty(value = "邀请状态 0：待回复1：已中选2：已拒绝3：已失效4：已过期")
	private Integer ackStatus;

	@ApiModelProperty(value = "任务状态 0:待指派运力1:邀请中2:待生效3:运行中4:已暂停5:已结束6:已取消")
	private Integer taskStatus;

	@ApiModelProperty(value = "业务区域名称")
	private String areaCodeName;

	@ApiModelProperty(value = "项目业务区域")
	private String projectAreaCode;

	private Date nowDate;

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
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

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
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

	public boolean isStandardTime() {
		return isStandardTime;
	}

	public void setStandardTime(boolean standardTime) {
		isStandardTime = standardTime;
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

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	public String getVendorRunTypeName() {
		return vendorRunTypeName;
	}

	public void setVendorRunTypeName(String vendorRunTypeName) {
		this.vendorRunTypeName = vendorRunTypeName;
	}

	public Integer getAckStatus() {
		return ackStatus;
	}

	public void setAckStatus(Integer ackStatus) {
		this.ackStatus = ackStatus;
	}

	public String getAreaCodeName() {
		return areaCodeName;
	}

	public void setAreaCodeName(String areaCodeName) {
		this.areaCodeName = areaCodeName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<String> getTruckTypeNames() {
		return truckTypeNames;
	}

	public void setTruckTypeNames(List<String> truckTypeNames) {
		this.truckTypeNames = truckTypeNames;
	}

	public List<String> getFunctions() {
		return functions;
	}

	public void setFunctions(List<String> functions) {
		this.functions = functions;
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

	public String getProjectAreaCode() {
		return projectAreaCode;
	}

	public void setProjectAreaCode(String projectAreaCode) {
		this.projectAreaCode = projectAreaCode;
	}

	public Date getNowDate() {
		return nowDate;
	}

	public void setNowDate(Date nowDate) {
		this.nowDate = nowDate;
	}

	public Integer getRoadMapId() {
		return roadMapId;
	}

	public void setRoadMapId(Integer roadMapId) {
		this.roadMapId = roadMapId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
}