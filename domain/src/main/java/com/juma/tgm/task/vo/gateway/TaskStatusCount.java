package com.juma.tgm.task.vo.gateway;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-08-06 09:51
 **/
@ApiModel(value = "任务数量")
public class TaskStatusCount implements Serializable{

	@ApiModelProperty(value = "待指派运力数量")
	private Integer waitingInviteCount;

	@ApiModelProperty(value = "承运商邀请中数量")
	private Integer invitingCount;

	public Integer getWaitingInviteCount() {
		return waitingInviteCount;
	}

	public void setWaitingInviteCount(Integer waitingInviteCount) {
		this.waitingInviteCount = waitingInviteCount;
	}

	public Integer getInvitingCount() {
		return invitingCount;
	}

	public void setInvitingCount(Integer invitingCount) {
		this.invitingCount = invitingCount;
	}
}
