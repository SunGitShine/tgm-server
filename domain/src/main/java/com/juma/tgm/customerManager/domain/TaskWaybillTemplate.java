package com.juma.tgm.customerManager.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TaskWaybillTemplate implements Serializable {
    private Integer taskWaybillTemplateId;

    private Integer customerManagerId;

    private Integer customerId;

    private Integer truckCustomerId;

    private Byte businessBranch;

    private Float requiredMinTemperature;

    private Float requiredMaxTemperature;

    private String remark;

    private BigDecimal estimateFreight;

    private BigDecimal show4DriverFreight;

    private String deliveryTimePoint;

    private Integer finishTimePoint;

    private Byte receiptType;

    private String requireJson;

    private Integer vehicleCount;

    private Byte needDeliveryPointNote;

    private Integer projectId;

    private Byte receiveWay;

    private Byte onlyLoadCargo;

    private BigDecimal agencyTakeFreight;

    private Date createTime;

    private Integer createUserId;

    private Date lastUpdateTime;

    private Integer lastUpdateUserId;

    private Byte valuationWay;

    private String projectFreightRuleJson;

    private Integer roadMapId;
    
    private String departmentCode;

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    private static final long serialVersionUID = 1L;

    public Integer getTaskWaybillTemplateId() {
        return taskWaybillTemplateId;
    }

    public void setTaskWaybillTemplateId(Integer taskWaybillTemplateId) {
        this.taskWaybillTemplateId = taskWaybillTemplateId;
    }

    public Integer getCustomerManagerId() {
        return customerManagerId;
    }

    public void setCustomerManagerId(Integer customerManagerId) {
        this.customerManagerId = customerManagerId;
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

    public Byte getBusinessBranch() {
        return businessBranch;
    }

    public void setBusinessBranch(Byte businessBranch) {
        this.businessBranch = businessBranch;
    }

    public Float getRequiredMinTemperature() {
        return requiredMinTemperature;
    }

    public void setRequiredMinTemperature(Float requiredMinTemperature) {
        this.requiredMinTemperature = requiredMinTemperature;
    }

    public Float getRequiredMaxTemperature() {
        return requiredMaxTemperature;
    }

    public void setRequiredMaxTemperature(Float requiredMaxTemperature) {
        this.requiredMaxTemperature = requiredMaxTemperature;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getEstimateFreight() {
        return estimateFreight;
    }

    public void setEstimateFreight(BigDecimal estimateFreight) {
        this.estimateFreight = estimateFreight;
    }

    public BigDecimal getShow4DriverFreight() {
        return show4DriverFreight;
    }

    public void setShow4DriverFreight(BigDecimal show4DriverFreight) {
        this.show4DriverFreight = show4DriverFreight;
    }

    public String getDeliveryTimePoint() {
        return deliveryTimePoint;
    }

    public void setDeliveryTimePoint(String deliveryTimePoint) {
        this.deliveryTimePoint = deliveryTimePoint == null ? null : deliveryTimePoint.trim();
    }

    public Integer getFinishTimePoint() {
        return finishTimePoint;
    }

    public void setFinishTimePoint(Integer finishTimePoint) {
        this.finishTimePoint = finishTimePoint;
    }

    public Byte getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(Byte receiptType) {
        this.receiptType = receiptType;
    }

    public String getRequireJson() {
        return requireJson;
    }

    public void setRequireJson(String requireJson) {
        this.requireJson = requireJson == null ? null : requireJson.trim();
    }

    public Integer getVehicleCount() {
        return vehicleCount;
    }

    public void setVehicleCount(Integer vehicleCount) {
        this.vehicleCount = vehicleCount;
    }

    public Byte getNeedDeliveryPointNote() {
        return needDeliveryPointNote;
    }

    public void setNeedDeliveryPointNote(Byte needDeliveryPointNote) {
        this.needDeliveryPointNote = needDeliveryPointNote;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Byte getReceiveWay() {
        return receiveWay;
    }

    public void setReceiveWay(Byte receiveWay) {
        this.receiveWay = receiveWay;
    }

    public Byte getOnlyLoadCargo() {
        return onlyLoadCargo;
    }

    public void setOnlyLoadCargo(Byte onlyLoadCargo) {
        this.onlyLoadCargo = onlyLoadCargo;
    }

    public BigDecimal getAgencyTakeFreight() {
        return agencyTakeFreight;
    }

    public void setAgencyTakeFreight(BigDecimal agencyTakeFreight) {
        this.agencyTakeFreight = agencyTakeFreight;
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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(Integer lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public Byte getValuationWay() {
        return valuationWay;
    }

    public void setValuationWay(Byte valuationWay) {
        this.valuationWay = valuationWay;
    }

    public String getProjectFreightRuleJson() {
        return projectFreightRuleJson;
    }

    public void setProjectFreightRuleJson(String projectFreightRuleJson) {
        this.projectFreightRuleJson = projectFreightRuleJson == null ? null : projectFreightRuleJson.trim();
    }

    public Integer getRoadMapId() {
        return roadMapId;
    }

    public void setRoadMapId(Integer roadMapId) {
        this.roadMapId = roadMapId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TaskWaybillTemplate other = (TaskWaybillTemplate) that;
        return (this.getTaskWaybillTemplateId() == null ? other.getTaskWaybillTemplateId() == null : this.getTaskWaybillTemplateId().equals(other.getTaskWaybillTemplateId()))
            && (this.getCustomerManagerId() == null ? other.getCustomerManagerId() == null : this.getCustomerManagerId().equals(other.getCustomerManagerId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getTruckCustomerId() == null ? other.getTruckCustomerId() == null : this.getTruckCustomerId().equals(other.getTruckCustomerId()))
            && (this.getBusinessBranch() == null ? other.getBusinessBranch() == null : this.getBusinessBranch().equals(other.getBusinessBranch()))
            && (this.getRequiredMinTemperature() == null ? other.getRequiredMinTemperature() == null : this.getRequiredMinTemperature().equals(other.getRequiredMinTemperature()))
            && (this.getRequiredMaxTemperature() == null ? other.getRequiredMaxTemperature() == null : this.getRequiredMaxTemperature().equals(other.getRequiredMaxTemperature()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getEstimateFreight() == null ? other.getEstimateFreight() == null : this.getEstimateFreight().equals(other.getEstimateFreight()))
            && (this.getShow4DriverFreight() == null ? other.getShow4DriverFreight() == null : this.getShow4DriverFreight().equals(other.getShow4DriverFreight()))
            && (this.getDeliveryTimePoint() == null ? other.getDeliveryTimePoint() == null : this.getDeliveryTimePoint().equals(other.getDeliveryTimePoint()))
            && (this.getFinishTimePoint() == null ? other.getFinishTimePoint() == null : this.getFinishTimePoint().equals(other.getFinishTimePoint()))
            && (this.getReceiptType() == null ? other.getReceiptType() == null : this.getReceiptType().equals(other.getReceiptType()))
            && (this.getRequireJson() == null ? other.getRequireJson() == null : this.getRequireJson().equals(other.getRequireJson()))
            && (this.getVehicleCount() == null ? other.getVehicleCount() == null : this.getVehicleCount().equals(other.getVehicleCount()))
            && (this.getNeedDeliveryPointNote() == null ? other.getNeedDeliveryPointNote() == null : this.getNeedDeliveryPointNote().equals(other.getNeedDeliveryPointNote()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getReceiveWay() == null ? other.getReceiveWay() == null : this.getReceiveWay().equals(other.getReceiveWay()))
            && (this.getOnlyLoadCargo() == null ? other.getOnlyLoadCargo() == null : this.getOnlyLoadCargo().equals(other.getOnlyLoadCargo()))
            && (this.getAgencyTakeFreight() == null ? other.getAgencyTakeFreight() == null : this.getAgencyTakeFreight().equals(other.getAgencyTakeFreight()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getLastUpdateUserId() == null ? other.getLastUpdateUserId() == null : this.getLastUpdateUserId().equals(other.getLastUpdateUserId()))
            && (this.getValuationWay() == null ? other.getValuationWay() == null : this.getValuationWay().equals(other.getValuationWay()))
            && (this.getProjectFreightRuleJson() == null ? other.getProjectFreightRuleJson() == null : this.getProjectFreightRuleJson().equals(other.getProjectFreightRuleJson()))
            && (this.getRoadMapId() == null ? other.getRoadMapId() == null : this.getRoadMapId().equals(other.getRoadMapId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTaskWaybillTemplateId() == null) ? 0 : getTaskWaybillTemplateId().hashCode());
        result = prime * result + ((getCustomerManagerId() == null) ? 0 : getCustomerManagerId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getTruckCustomerId() == null) ? 0 : getTruckCustomerId().hashCode());
        result = prime * result + ((getBusinessBranch() == null) ? 0 : getBusinessBranch().hashCode());
        result = prime * result + ((getRequiredMinTemperature() == null) ? 0 : getRequiredMinTemperature().hashCode());
        result = prime * result + ((getRequiredMaxTemperature() == null) ? 0 : getRequiredMaxTemperature().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getEstimateFreight() == null) ? 0 : getEstimateFreight().hashCode());
        result = prime * result + ((getShow4DriverFreight() == null) ? 0 : getShow4DriverFreight().hashCode());
        result = prime * result + ((getDeliveryTimePoint() == null) ? 0 : getDeliveryTimePoint().hashCode());
        result = prime * result + ((getFinishTimePoint() == null) ? 0 : getFinishTimePoint().hashCode());
        result = prime * result + ((getReceiptType() == null) ? 0 : getReceiptType().hashCode());
        result = prime * result + ((getRequireJson() == null) ? 0 : getRequireJson().hashCode());
        result = prime * result + ((getVehicleCount() == null) ? 0 : getVehicleCount().hashCode());
        result = prime * result + ((getNeedDeliveryPointNote() == null) ? 0 : getNeedDeliveryPointNote().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getReceiveWay() == null) ? 0 : getReceiveWay().hashCode());
        result = prime * result + ((getOnlyLoadCargo() == null) ? 0 : getOnlyLoadCargo().hashCode());
        result = prime * result + ((getAgencyTakeFreight() == null) ? 0 : getAgencyTakeFreight().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getLastUpdateUserId() == null) ? 0 : getLastUpdateUserId().hashCode());
        result = prime * result + ((getValuationWay() == null) ? 0 : getValuationWay().hashCode());
        result = prime * result + ((getProjectFreightRuleJson() == null) ? 0 : getProjectFreightRuleJson().hashCode());
        result = prime * result + ((getRoadMapId() == null) ? 0 : getRoadMapId().hashCode());
        return result;
    }
}