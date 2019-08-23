package com.juma.tgm.task.vo.gateway;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-08-07 10:46
 **/
@ApiModel(value = "任务日历-项目维度")
public class TaskCalendarByProject implements Serializable{

	private Integer taskId;

	private Integer calendarId;

	private Integer truckId;

	private Integer driverId;

	private Integer roadMapId;

	private Integer vendorId;

	@ApiModelProperty(value = "承运商名称")
	private String vendorName;

	@ApiModelProperty(value = "车牌")
	private String plateNumber;

	@ApiModelProperty(value = "司机名字")
	private String driverName;

	@ApiModelProperty(value = "路线名称")
	private String roadMapName;

	@ApiModelProperty(value = "配送开始时间")
	private Date workStartTime;

	@ApiModelProperty(value = "配送结束时间")
	private Date workEndTime;

	private Integer taskStatus;

	private Integer workStatus;

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(Integer calendarId) {
		this.calendarId = calendarId;
	}

	public Integer getTruckId() {
		return truckId;
	}

	public void setTruckId(Integer truckId) {
		this.truckId = truckId;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public Integer getRoadMapId() {
		return roadMapId;
	}

	public void setRoadMapId(Integer roadMapId) {
		this.roadMapId = roadMapId;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getRoadMapName() {
		return roadMapName;
	}

	public void setRoadMapName(String roadMapName) {
		this.roadMapName = roadMapName;
	}

	public Date getWorkStartTime() {
		return workStartTime;
	}

	public void setWorkStartTime(Date workStartTime) {
		this.workStartTime = workStartTime;
	}

	public Date getWorkEndTime() {
		return workEndTime;
	}

	public void setWorkEndTime(Date workEndTime) {
		this.workEndTime = workEndTime;
	}

	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Integer getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(Integer workStatus) {
		this.workStatus = workStatus;
	}
}
