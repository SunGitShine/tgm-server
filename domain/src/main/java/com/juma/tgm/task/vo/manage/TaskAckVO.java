package com.juma.tgm.task.vo.manage;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-07-10 10:50
 **/
@ApiModel(value = "任务邀请")
public class TaskAckVO implements Serializable{

	private Integer ackId;

	private Integer taskId;

	@ApiModelProperty(value = "承运商id")
	private Integer vendorId;

	@ApiModelProperty(value = "承运商名字")
	private String vendorName;

	@ApiModelProperty(value = "车牌号")
	private String plateNumber;

	@ApiModelProperty(value = "司机名字")
	private String driverName;

	@ApiModelProperty(value = "邀请状态 0：待回复1：已中选2：已拒绝3：已失效4：已过期")
	private Integer ackStatus;

	@ApiModelProperty(value = "拒绝原因")
	private String refuseReason;

	@ApiModelProperty(value = "账期")
	private Integer billPeriod;

	@ApiModelProperty(value = "创建人")
	private String createUserName;

	@ApiModelProperty(value = "创建人电话")
	private String createUserContactPhone;

	@ApiModelProperty(value = "邀请时间")
	private Date createTime;

	@ApiModelProperty(value = "回复时间")
	private Date lastUpdateTime;

	public Integer getAckId() {
		return ackId;
	}

	public void setAckId(Integer ackId) {
		this.ackId = ackId;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
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

	public Integer getBillPeriod() {
		return billPeriod;
	}

	public void setBillPeriod(Integer billPeriod) {
		this.billPeriod = billPeriod;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getCreateUserContactPhone() {
		return createUserContactPhone;
	}

	public void setCreateUserContactPhone(String createUserContactPhone) {
		this.createUserContactPhone = createUserContactPhone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}
}
