package com.juma.tgm.task.domain.ext;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-08-06 14:54
 **/
public class GroupTaskCalendar implements Serializable{

	@ApiModelProperty(value = "配送日状态 0:运行中 1:已完成 2:不配送 3:运力冲突")
	private Integer workStatus;

	@ApiModelProperty(value = "运行中对应的运单配送状态 0:未签到 1:已到仓 2:已离仓")
	private Integer deliveryStatus;

	@ApiModelProperty(value = "任务日历数量")
	private Integer taskCalendarCount;

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

	public Integer getTaskCalendarCount() {
		return taskCalendarCount;
	}

	public void setTaskCalendarCount(Integer taskCalendarCount) {
		this.taskCalendarCount = taskCalendarCount;
	}
}
