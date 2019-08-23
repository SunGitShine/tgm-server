package com.juma.tgm.task.dto.gateway;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-08-06 15:48
 **/
@ApiModel(value = "任务日历分组")
public class GroupTaskCalendarFilter implements Serializable{

	@ApiModelProperty(value = "项目id")
	private Integer projectId;

	@ApiModelProperty(value = "开始时间")
	private Date startTime;

	@ApiModelProperty(value = "结束时间")
	private Date endTime;

	@ApiModelProperty(value = "配送日状态 0运行中 1已完成 2不配送")
	private Integer workStatus;

	@ApiModelProperty(value = "运单配送状态 0未签到 1已到仓 2已离仓")
	private Integer deliveryStatus;

	@ApiModelProperty(value = "排序类型 1时间排序 2路线排序")
	private Integer orderType = 1;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(Integer workStatus) {
		this.workStatus = workStatus;
	}

	public Integer getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(Integer deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
}
