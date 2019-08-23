package com.juma.tgm.project.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * project - 项目
 *
 * @author 2017-09-26
 * @version 1.0
 */
public class Project implements Serializable {

    private Integer projectId;
    private Integer tenantId;
    private String tenantCode;
    private String areaCode;
    private String name;
    private Integer customerId;
    private Integer truckCustomerId;
    private Integer managerId;
    private String goodsType;
    private BigDecimal taxRateValue;
    private BigDecimal rebateRate;
    private BigDecimal estimateTimeConsumption;
    private String truckRequireRemark;
    private String additionalFunctionIds;
    private Integer fixedNo;
    private Boolean isEnable;
    private Date createTime;
    private Integer createUserId;
    private Boolean isReceivableFirst;
    /**
     * 是否在装货后第二天开始配送
     */
    private Integer onlyLoadCargo;

    /**
     * 是否需要上传配送单
     */
    private Integer needDeliveryPointNote;

    // 以下冗余显示
    private String truckCustomerName;

    // 后台使用
    private String customerManagerName;
    private String customerName;
    private String areaName;

    // 查询专用
    private Integer timeOrderCount;// 定时发单 统计

    private Integer roadCount;// 线路条数统计

    //物流产品标签
    private String logisticsLabel;
    //物流产品名称
    private String logisticsName;
    // 是否产生过运单
    private boolean isUsedCreateWaybill;
    //项目状态
    private Integer projectStatus;

    private Date projectStartDate;

    private Date projectEndDate;

    private Integer billPeriod;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getTruckCustomerId() {
        return truckCustomerId;
    }

    public void setTruckCustomerId(Integer truckCustomerId) {
        this.truckCustomerId = truckCustomerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getTruckCustomerName() {
        return truckCustomerName;
    }

    public void setTruckCustomerName(String truckCustomerName) {
        this.truckCustomerName = truckCustomerName;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCustomerManagerName() {
        return customerManagerName;
    }

    public void setCustomerManagerName(String customerManagerName) {
        this.customerManagerName = customerManagerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public BigDecimal getRebateRate() {
        return rebateRate;
    }

    public void setRebateRate(BigDecimal rebateRate) {
        this.rebateRate = rebateRate;
    }

    public BigDecimal getEstimateTimeConsumption() {
        return estimateTimeConsumption;
    }

    public void setEstimateTimeConsumption(BigDecimal estimateTimeConsumption) {
        this.estimateTimeConsumption = estimateTimeConsumption;
    }

    public String getTruckRequireRemark() {
        return truckRequireRemark;
    }

    public void setTruckRequireRemark(String truckRequireRemark) {
        this.truckRequireRemark = truckRequireRemark;
    }

    public String getAdditionalFunctionIds() {
        return additionalFunctionIds;
    }

    public void setAdditionalFunctionIds(String additionalFunctionIds) {
        this.additionalFunctionIds = additionalFunctionIds;
    }

    public Integer getFixedNo() {
        return fixedNo;
    }

    public void setFixedNo(Integer fixedNo) {
        this.fixedNo = fixedNo;
    }

    public Integer getOnlyLoadCargo() {
        return onlyLoadCargo;
    }

    public void setOnlyLoadCargo(Integer onlyLoadCargo) {
        this.onlyLoadCargo = onlyLoadCargo;
    }

    public Integer getNeedDeliveryPointNote() {
        return needDeliveryPointNote;
    }

    public void setNeedDeliveryPointNote(Integer needDeliveryPointNote) {
        this.needDeliveryPointNote = needDeliveryPointNote;
    }

    public BigDecimal getTaxRateValue() {
        return taxRateValue;
    }

    public void setTaxRateValue(BigDecimal taxRateValue) {
        this.taxRateValue = taxRateValue;
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

    public Boolean getIsReceivableFirst() {
        return isReceivableFirst;
    }

    public void setIsReceivableFirst(Boolean isReceivableFirst) {
        this.isReceivableFirst = isReceivableFirst;
    }

    public String getLogisticsLabel() {
        return logisticsLabel;
    }

    public void setLogisticsLabel(String logisticsLabel) {
        this.logisticsLabel = logisticsLabel;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public boolean getIsUsedCreateWaybill() {
        return isUsedCreateWaybill;
    }

    public void setIsUsedCreateWaybill(boolean isUsedCreateWaybill) {
        this.isUsedCreateWaybill = isUsedCreateWaybill;
    }

    public Integer getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public Integer getBillPeriod() {
        return billPeriod;
    }

    public void setBillPeriod(Integer billPeriod) {
        this.billPeriod = billPeriod;
    }
}