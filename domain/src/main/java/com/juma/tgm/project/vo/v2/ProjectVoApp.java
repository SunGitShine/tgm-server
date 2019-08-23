package com.juma.tgm.project.vo.v2;

import com.juma.tgm.project.domain.v2.Project;
import io.swagger.annotations.ApiModelProperty;

public class ProjectVoApp extends Project{

	//物流产品名称
	private String logisticsName;
	// 是否产生过运单
	private boolean isUsedCreateWaybill;

	private Integer timeOrderCount;// 定时发单 统计

	private Integer roadCount;// 线路条数统计

	private Integer taskWaitingBecomeNum;// 待生效任务数量

	private Integer taskRunningNum;// 运行中任务数量

	private Integer taskPauseNum;// 已暂停任务数量

	@ApiModelProperty("项目经理名称")
	private String projectManagerUserName;

	@ApiModelProperty("项目经理电话")
	private String projectManagerUserPhone;

	public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	public boolean isUsedCreateWaybill() {
		return isUsedCreateWaybill;
	}

	public void setUsedCreateWaybill(boolean usedCreateWaybill) {
		isUsedCreateWaybill = usedCreateWaybill;
	}

	public Integer getTimeOrderCount() {
		return timeOrderCount;
	}

	public void setTimeOrderCount(Integer timeOrderCount) {
		this.timeOrderCount = timeOrderCount;
	}

	public Integer getRoadCount() {
		return roadCount;
	}

	public void setRoadCount(Integer roadCount) {
		this.roadCount = roadCount;
	}

	public String getProjectManagerUserName() {
		return projectManagerUserName;
	}

	public void setProjectManagerUserName(String projectManagerUserName) {
		this.projectManagerUserName = projectManagerUserName;
	}

	public String getProjectManagerUserPhone() {
		return projectManagerUserPhone;
	}

	public void setProjectManagerUserPhone(String projectManagerUserPhone) {
		this.projectManagerUserPhone = projectManagerUserPhone;
	}

	public Integer getTaskWaitingBecomeNum() {
		return taskWaitingBecomeNum;
	}

	public void setTaskWaitingBecomeNum(Integer taskWaitingBecomeNum) {
		this.taskWaitingBecomeNum = taskWaitingBecomeNum;
	}

	public Integer getTaskRunningNum() {
		return taskRunningNum;
	}

	public void setTaskRunningNum(Integer taskRunningNum) {
		this.taskRunningNum = taskRunningNum;
	}

	public Integer getTaskPauseNum() {
		return taskPauseNum;
	}

	public void setTaskPauseNum(Integer taskPauseNum) {
		this.taskPauseNum = taskPauseNum;
	}
}
