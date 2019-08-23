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
 * @create: 2019-07-10 11:33
 **/
@ApiModel(value = "任务邀请详情")
public class TaskAckDetail implements Serializable{

	@ApiModelProperty(value = "承运商id")
	private Integer vendorId;

	@ApiModelProperty(value = "承运商名字")
	private String vendorName;

	@ApiModelProperty(value = "承运商联系电话")
	private String vendorContactPhone;

	@ApiModelProperty(value = "车辆id")
	private Integer truckId;

	@ApiModelProperty(value = "车牌号")
	private String plateNumber;

	@ApiModelProperty(value = "车辆类型名称")
	private String truckTypeName;

	@ApiModelProperty(value = "司机名字")
	private String driverName;

	@ApiModelProperty(value = "司机联系电话")
	private String driverContactPhone;

	@ApiModelProperty(value = "邀请状态 0：待回复1：已中选2：已拒绝3：已失效4：已过期")
	private Integer ackStatus;

	@ApiModelProperty(value = "账期")
	private Integer billPeriod;

	@ApiModelProperty(value = "邀请时间")
	private Date createTime;

	@ApiModelProperty(value = "回复时间")
	private Date lastUpdateTime;

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

	public String getTruckTypeName() {
		return truckTypeName;
	}

	public void setTruckTypeName(String truckTypeName) {
		this.truckTypeName = truckTypeName;
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

	public Integer getAckStatus() {
		return ackStatus;
	}

	public void setAckStatus(Integer ackStatus) {
		this.ackStatus = ackStatus;
	}

	public Integer getBillPeriod() {
		return billPeriod;
	}

	public void setBillPeriod(Integer billPeriod) {
		this.billPeriod = billPeriod;
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
}
