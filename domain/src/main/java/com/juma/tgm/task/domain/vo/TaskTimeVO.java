package com.juma.tgm.task.domain.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-07-09 14:34
 **/
public class TaskTimeVO implements Serializable{

	private Date startTime;

	private Date endTime;

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
}
