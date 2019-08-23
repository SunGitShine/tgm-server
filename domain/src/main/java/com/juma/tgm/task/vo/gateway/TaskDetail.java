package com.juma.tgm.task.vo.gateway;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ApiModel(value = "任务详情")
public class TaskDetail implements Serializable {

    @ApiModelProperty(value = "任务id")
    private Integer taskId;

    @ApiModelProperty(value = "任务编号")
    private String taskNo;

    @ApiModelProperty(value = "城市编码")
    private String regionCode;

    @ApiModelProperty(value = "城市编码名称")
    private String regionCodeName;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "项目id")
    private Integer projectId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目结算周期")
    private Integer projectBillPeriod;

    @ApiModelProperty(value = "项目经理名字")
    private String projectManagerName;

    @ApiModelProperty(value = "项目经理电话")
    private String projectManagerPhone;

    @ApiModelProperty(value = "线路id")
    private Integer roadMapId;

    @ApiModelProperty(value = "线路计价规则id")
    private Integer roadMapPriceRuleId;

    @ApiModelProperty(value = "线路名称")
    private String roadMapName;

    @ApiModelProperty(value = "结算账期")
    private Integer billPeriod;

    @ApiModelProperty(value = "货物类型")
    private String goodsType;

    @ApiModelProperty(value = "任务配送车型ids")
    private List<Integer> truckTypeIds = new ArrayList<>();

    @ApiModelProperty(value = "配送车辆车型")
    private String truckTypeName;

    @ApiModelProperty(value = "仓库")
    private Depot depot;

    @ApiModelProperty(value = "是否固定点")
    private Boolean isFixedDelivery;

    @ApiModelProperty(value = "线路")
    private RoadMapVo roadMapVo;

    @ApiModelProperty(value = "线路计价")
    private List<RoadMapPriceRuleVo> roadMapPriceRuleVos = new ArrayList<>();

    @ApiModelProperty(value = "固定点")
    private List<FixedDeliveryInfo> fixedDeliveries = new ArrayList<>();

    @ApiModelProperty(value = "非固定点")
    private NotFixedDeliveryInfo notFixedDeliveryInfo;

    @ApiModelProperty(value = "开始日期")
    private Date startDate;

    @ApiModelProperty(value = "结束日期")
    private Date endDate;

    @ApiModelProperty(value = "是否统一时间")
    private Boolean isStandardTime;

    @ApiModelProperty(value = "配送周期")
    private String deliveryPeriod;

    @ApiModelProperty(value = "配送时间")
    private String deliveryPeriodTime;

    @ApiModelProperty(value = "预估里程")
    private String estimateMileageRange;

    @ApiModelProperty(value = "预估用时")
    private String estimateTimeCostRange;

    @ApiModelProperty(value = "用车要求ids")
    private String functionIds;

    @ApiModelProperty(value = "用车要求")
    private String functions;

    @ApiModelProperty(value = "用车备注")
    private String requireMark;

    @ApiModelProperty(value = "计价方式 1:按工作量  2:一口价")
    private Integer pricingMethod;

    @ApiModelProperty(value = "计价规则")
    private String pricingRule;

    @ApiModelProperty(value = "计价规则")
    private String pricingRuleJson;

    @ApiModelProperty(value = "邀请状态 0：待回复1：已中选2：已拒绝3：已失效4：已过期")
    private Integer ackStatus;

    @ApiModelProperty(value = "任务状态 0:待指派运力1:邀请中2:待生效3:运行中4:已暂停5:已结束6:已取消7:已过期")
    private Integer taskStatus;

    @ApiModelProperty(value = "邀请时间")
    private Date inviteTime;

    @ApiModelProperty(value = "司机id")
    private Integer driverId;

    @ApiModelProperty(value = "车辆id")
    private Integer truckId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    private String createUserName;

    @ApiModelProperty(value = "创建人电话")
    private String createUserContactPhone;

    public Integer getProjectBillPeriod() {
        return projectBillPeriod;
    }

    public void setProjectBillPeriod(Integer projectBillPeriod) {
        this.projectBillPeriod = projectBillPeriod;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getPricingRuleJson() {
        return pricingRuleJson;
    }

    public void setPricingRuleJson(String pricingRuleJson) {
        this.pricingRuleJson = pricingRuleJson;
    }

    public Integer getRoadMapPriceRuleId() {
        return roadMapPriceRuleId;
    }

    public void setRoadMapPriceRuleId(Integer roadMapPriceRuleId) {
        this.roadMapPriceRuleId = roadMapPriceRuleId;
    }

    public String getRegionCodeName() {
        return regionCodeName;
    }

    public void setRegionCodeName(String regionCodeName) {
        this.regionCodeName = regionCodeName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getFunctionIds() {
        return functionIds;
    }

    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }

    public RoadMapVo getRoadMapVo() {
        return roadMapVo;
    }

    public void setRoadMapVo(RoadMapVo roadMapVo) {
        this.roadMapVo = roadMapVo;
    }

    public List<RoadMapPriceRuleVo> getRoadMapPriceRuleVos() {
        return roadMapPriceRuleVos;
    }

    public void setRoadMapPriceRuleVos(List<RoadMapPriceRuleVo> roadMapPriceRuleVos) {
        this.roadMapPriceRuleVos = roadMapPriceRuleVos;
    }

    public List<Integer> getTruckTypeIds() {
        return truckTypeIds;
    }

    public void setTruckTypeIds(List<Integer> truckTypeIds) {
        this.truckTypeIds = truckTypeIds;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public List<FixedDeliveryInfo> getFixedDeliveries() {
        return fixedDeliveries;
    }

    public void setFixedDeliveries(List<FixedDeliveryInfo> fixedDeliveries) {
        this.fixedDeliveries = fixedDeliveries;
    }

    public String getRequireMark() {
        return requireMark;
    }

    public void setRequireMark(String requireMark) {
        this.requireMark = requireMark;
    }

    public Integer getAckStatus() {
        return ackStatus;
    }

    public void setAckStatus(Integer ackStatus) {
        this.ackStatus = ackStatus;
    }

    public Date getInviteTime() {
        return inviteTime;
    }

    public void setInviteTime(Date inviteTime) {
        this.inviteTime = inviteTime;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public String getProjectManagerPhone() {
        return projectManagerPhone;
    }

    public void setProjectManagerPhone(String projectManagerPhone) {
        this.projectManagerPhone = projectManagerPhone;
    }

    public String getProjectManagerName() {
        return projectManagerName;
    }

    public void setProjectManagerName(String projectManagerName) {
        this.projectManagerName = projectManagerName;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
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

    public Integer getPricingMethod() {
        return pricingMethod;
    }

    public void setPricingMethod(Integer pricingMethod) {
        this.pricingMethod = pricingMethod;
    }

    public String getPricingRule() {
        return pricingRule;
    }

    public void setPricingRule(String pricingRule) {
        this.pricingRule = pricingRule;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
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

    public Boolean getIsFixedDelivery() {
        return isFixedDelivery;
    }

    public void setIsFixedDelivery(Boolean isFixedDelivery) {
        this.isFixedDelivery = isFixedDelivery;
    }

    public NotFixedDeliveryInfo getNotFixedDeliveryInfo() {
        return notFixedDeliveryInfo;
    }

    public void setNotFixedDeliveryInfo(NotFixedDeliveryInfo notFixedDeliveryInfo) {
        this.notFixedDeliveryInfo = notFixedDeliveryInfo;
    }

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRoadMapName() {
        return roadMapName;
    }

    public void setRoadMapName(String roadMapName) {
        this.roadMapName = roadMapName;
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

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
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

    public Boolean getIsStandardTime() {
        return isStandardTime;
    }

    public void setIsStandardTime(Boolean isStandardTime) {
        this.isStandardTime = isStandardTime;
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
}
