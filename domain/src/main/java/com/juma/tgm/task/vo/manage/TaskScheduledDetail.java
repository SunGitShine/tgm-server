package com.juma.tgm.task.vo.manage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Splitter;
import com.juma.tgm.task.enums.Week;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-07-10 14:28
 **/
@ApiModel(value = "任务信息")
public class TaskScheduledDetail implements Serializable{

	@ApiModelProperty(value = "任务id")
	private Integer taskId;

	private Integer depotId;

	@ApiModelProperty(value = "任务编号")
	private String taskNo;

	@ApiModelProperty(value = "派车有效期")
	private Date dispatchVehicleDate;

	@ApiModelProperty(value = "任务状态 0:待指派运力1:邀请中2:待生效3:运行中4:已暂停5:已结束6:已取消")
	private Integer taskStatus;

	@ApiModelProperty(value = "项目名称")
	private String projectName;

	@ApiModelProperty(value = "项目编号")
	private String projectNo;

	@ApiModelProperty(value = "线路名称")
	private String roadMapName;

	@ApiModelProperty(value = "承运商名称")
	private String vendorName;

	@ApiModelProperty(value = "承运商id")
	private Integer vendorId;

	@ApiModelProperty(value = "承运商联系电话")
	private String vendorContactPhone;

	@ApiModelProperty(value = "结算账期")
	private Integer billPeriod;

	@ApiModelProperty(value = "货物类型")
	private String goodsType;

	@ApiModelProperty(value = "计价规则")
	private String pricingRule;

	@ApiModelProperty(value = "配送车型")
	private List<String> truckTypeNames;

	@ApiModelProperty(value = "开始日期")
	private Date startDate;

	@ApiModelProperty(value = "结束日期")
	private Date endDate;

	@ApiModelProperty(value = "是否统一时间")
	private Boolean isStandardTime;

	//配送点类型  false非固定  true固定
	private Boolean isFixedDelivery;

	@ApiModelProperty(value = "配送周期")
	private String deliveryPeriod;

	@ApiModelProperty(value = "配送时间")
	private String deliveryPeriodTime;

	@ApiModelProperty(value = "预估里程")
	private String estimateMileageRange;

	@ApiModelProperty(value = "预估用时")
	private String estimateTimeCostRange;

	@ApiModelProperty(value = "用车要求")
	private List<String> functions;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "创建人")
	private String createUserName;

	@ApiModelProperty(value = "创建人电话")
	private String createUserContactPhone;

	@ApiModelProperty(value = "配送周期 比如[\"周一\",\"周二\"]")
	private List<String> deliveryPeriodViews = new ArrayList<>();

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	public Date getDispatchVehicleDate() {
		return dispatchVehicleDate;
	}

	public void setDispatchVehicleDate(Date dispatchVehicleDate) {
		this.dispatchVehicleDate = dispatchVehicleDate;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getRoadMapName() {
		return roadMapName;
	}

	public void setRoadMapName(String roadMapName) {
		this.roadMapName = roadMapName;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorContactPhone() {
		return vendorContactPhone;
	}

	public void setVendorContactPhone(String vendorContactPhone) {
		this.vendorContactPhone = vendorContactPhone;
	}

	public Integer getBillPeriod() {
		return billPeriod;
	}

	public void setBillPeriod(Integer billPeriod) {
		this.billPeriod = billPeriod;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getPricingRule() {
		return pricingRule;
	}

	public void setPricingRule(String pricingRule) {
		this.pricingRule = pricingRule;
	}

	public List<String> getTruckTypeNames() {
		return truckTypeNames;
	}

	public void setTruckTypeNames(List<String> truckTypeNames) {
		this.truckTypeNames = truckTypeNames;
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

	public List<String> getFunctions() {
		return functions;
	}

	public void setFunctions(List<String> functions) {
		this.functions = functions;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public List<String> getDeliveryPeriodViews() {
		deliveryPeriodViews.clear();
		if (StringUtils.isBlank(deliveryPeriod)) return deliveryPeriodViews;
		if (deliveryPeriod.contains("1,2,3,4,5,6,7")) {
			deliveryPeriodViews.add("每天");
			return deliveryPeriodViews;
		}
		List<String> strings = Splitter.on(",").splitToList(deliveryPeriod);
		for (String s : strings) {
			deliveryPeriodViews.add(Week.getWeekDesc(s));
		}
		return deliveryPeriodViews;
	}

	public void setDeliveryPeriodViews(List<String> deliveryPeriodViews) {
		this.deliveryPeriodViews = deliveryPeriodViews;
	}
}
