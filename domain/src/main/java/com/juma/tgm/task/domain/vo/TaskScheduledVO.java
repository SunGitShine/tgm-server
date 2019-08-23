package com.juma.tgm.task.domain.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.juma.tgm.task.domain.TaskFixedDelivery;
import com.juma.tgm.task.domain.TaskNotfixedDelivery;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-07-02 10:52
 **/
public class TaskScheduledVO implements Serializable{

	@ApiModelProperty(value = "项目id")
	private Integer projectId;

	private String regionCode;

	@ApiModelProperty(value = "路线id")
	private Integer roadMapId;

	@ApiModelProperty(value = "计价方式")
	private Integer roadMapPriceRuleId;

	private String projectFreightRuleJson;

	@ApiModelProperty(value = "任务车型id")
	private List<Integer> truckTypeIds;

	@ApiModelProperty(value = "仓库id")
	private Integer depotId;

	@ApiModelProperty(value = "配送点类型  false非固定  true固定")
	private Boolean isFixedDelivery;

	@ApiModelProperty(value = "非固定点")
	private TaskNotfixedDelivery taskNotfixedDelivery;

	@ApiModelProperty(value = "固定点")
	private List<TaskFixedDelivery> taskFixedDeliveries;

	@ApiModelProperty(value = "开始日期")
	private Date startDate;

	@ApiModelProperty(value = "结束日期")
	private Date endDate;

	@ApiModelProperty(value = "统一时间  false非  true是")
	private Boolean isStandardTime;

	@ApiModelProperty(value = "配送周期 比如:1,2,3,4,5")
	private String deliveryPeriod;

	@ApiModelProperty(value = "配送时间 比如:09:00,10:20,11:30")
	private String deliveryPeriodTime;

	@ApiModelProperty(value = "预估里程范围（单位：公里）例：2-4")
	private String estimateMileageRange;

	@ApiModelProperty(value = "预估用时范围（单位：小时）例：2-4")
	private String estimateTimeCostRange;

	@ApiModelProperty(value = "用车要求，多个已英文逗号隔开")
	private String functionIds;

	@ApiModelProperty(value = "用车备注")
	private String requireMark;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getRoadMapId() {
		return roadMapId;
	}

	public void setRoadMapId(Integer roadMapId) {
		this.roadMapId = roadMapId;
	}

	public Integer getRoadMapPriceRuleId() {
		return roadMapPriceRuleId;
	}

	public void setRoadMapPriceRuleId(Integer roadMapPriceRuleId) {
		this.roadMapPriceRuleId = roadMapPriceRuleId;
	}

	public List<Integer> getTruckTypeIds() {
		return truckTypeIds;
	}

	public void setTruckTypeIds(List<Integer> truckTypeIds) {
		this.truckTypeIds = truckTypeIds;
	}

	public Integer getDepotId() {
		return depotId;
	}

	public void setDepotId(Integer depotId) {
		this.depotId = depotId;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getStandardTime() {
		return isStandardTime;
	}

	public void setStandardTime(Boolean standardTime) {
		isStandardTime = standardTime;
	}

	public String getDeliveryPeriod() {
		return deliveryPeriod;
	}

	public void setDeliveryPeriod(String deliveryPeriod) {
		this.deliveryPeriod = deliveryPeriod;
	}

	public String getDeliveryPeriodTime() {
		return deliveryPeriodTime;
	}

	public void setDeliveryPeriodTime(String deliveryPeriodTime) {
		this.deliveryPeriodTime = deliveryPeriodTime;
	}

	public String getEstimateMileageRange() {
		return estimateMileageRange;
	}

	public void setEstimateMileageRange(String estimateMileageRange) {
		this.estimateMileageRange = estimateMileageRange;
	}

	public String getEstimateTimeCostRange() {
		return estimateTimeCostRange;
	}

	public void setEstimateTimeCostRange(String estimateTimeCostRange) {
		this.estimateTimeCostRange = estimateTimeCostRange;
	}

	public String getFunctionIds() {
		return functionIds;
	}

	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}

	public String getRequireMark() {
		return requireMark;
	}

	public void setRequireMark(String requireMark) {
		this.requireMark = requireMark;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getProjectFreightRuleJson() {
		return projectFreightRuleJson;
	}

	public void setProjectFreightRuleJson(String projectFreightRuleJson) {
		this.projectFreightRuleJson = projectFreightRuleJson;
	}
}
