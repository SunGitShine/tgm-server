package com.juma.tgm.task.vo.manage;

import java.io.Serializable;
import java.util.List;

import com.juma.tgm.project.domain.v2.ProjectDepot;
import com.juma.tgm.task.domain.TaskFixedDelivery;
import com.juma.tgm.task.domain.TaskNotfixedDelivery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-07-10 15:28
 **/
@ApiModel(value = "配送点")
public class TaskDelivery implements Serializable{

	@ApiModelProperty(value = "仓库")
	private ProjectDepot projectDepot;

	@ApiModelProperty(value = "配送点类型")
	private Boolean isFixedDelivery;

	@ApiModelProperty(value = "非固定点")
	private TaskNotfixedDelivery taskNotfixedDelivery;

	@ApiModelProperty(value = "固定点")
	private List<TaskFixedDelivery> taskFixedDeliveries;

	public ProjectDepot getProjectDepot() {
		return projectDepot;
	}

	public void setProjectDepot(ProjectDepot projectDepot) {
		this.projectDepot = projectDepot;
	}

	public Boolean getFixedDelivery() {
		return isFixedDelivery;
	}

	public void setFixedDelivery(Boolean fixedDelivery) {
		isFixedDelivery = fixedDelivery;
	}

	public TaskNotfixedDelivery getTaskNotfixedDelivery() {
		return taskNotfixedDelivery;
	}

	public void setTaskNotfixedDelivery(TaskNotfixedDelivery taskNotfixedDelivery) {
		this.taskNotfixedDelivery = taskNotfixedDelivery;
	}

	public List<TaskFixedDelivery> getTaskFixedDeliveries() {
		return taskFixedDeliveries;
	}

	public void setTaskFixedDeliveries(List<TaskFixedDelivery> taskFixedDeliveries) {
		this.taskFixedDeliveries = taskFixedDeliveries;
	}
}
