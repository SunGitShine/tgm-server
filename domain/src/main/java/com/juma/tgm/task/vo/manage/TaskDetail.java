package com.juma.tgm.task.vo.manage;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-07-10 14:56
 **/
@ApiModel(value = "任务邀请")
public class TaskDetail implements Serializable {

	@ApiModelProperty(value = "任务信息")
	private TaskScheduledDetail taskScheduledDetail;

	@ApiModelProperty(value = "任务邀请")
	private List<TaskAckVO> taskAcks;

	@ApiModelProperty(value = "配送点")
	private TaskDelivery taskDelivery;

	public TaskScheduledDetail getTaskScheduledDetail() {
		return taskScheduledDetail;
	}

	public void setTaskScheduledDetail(TaskScheduledDetail taskScheduledDetail) {
		this.taskScheduledDetail = taskScheduledDetail;
	}

	public List<TaskAckVO> getTaskAcks() {
		return taskAcks;
	}

	public void setTaskAcks(List<TaskAckVO> taskAcks) {
		this.taskAcks = taskAcks;
	}

	public TaskDelivery getTaskDelivery() {
		return taskDelivery;
	}

	public void setTaskDelivery(TaskDelivery taskDelivery) {
		this.taskDelivery = taskDelivery;
	}
}
