package com.juma.tgm.task.domain;

import java.io.Serializable;

public class TaskParam implements Serializable {
    private Integer paramId;

    private Integer taskId;

    private Integer billPeriod;

    private String goodsType;

    private String estimateMileageRange;

    private String estimateTimeCostRange;

    private String functionIds;

    private String requireMark;

    private Integer pricingMethod;

    private String pricingRule;

    private String cancelReason;

    private static final long serialVersionUID = 1L;

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
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
        this.goodsType = goodsType == null ? null : goodsType.trim();
    }

    public String getEstimateMileageRange() {
        return estimateMileageRange;
    }

    public void setEstimateMileageRange(String estimateMileageRange) {
        this.estimateMileageRange = estimateMileageRange == null ? null : estimateMileageRange.trim();
    }

    public String getEstimateTimeCostRange() {
        return estimateTimeCostRange;
    }

    public void setEstimateTimeCostRange(String estimateTimeCostRange) {
        this.estimateTimeCostRange = estimateTimeCostRange == null ? null : estimateTimeCostRange.trim();
    }

    public String getFunctionIds() {
        return functionIds;
    }

    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds == null ? null : functionIds.trim();
    }

    public String getRequireMark() {
        return requireMark;
    }

    public void setRequireMark(String requireMark) {
        this.requireMark = requireMark == null ? null : requireMark.trim();
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
        this.pricingRule = pricingRule == null ? null : pricingRule.trim();
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason == null ? null : cancelReason.trim();
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
        TaskParam other = (TaskParam) that;
        return (this.getParamId() == null ? other.getParamId() == null : this.getParamId().equals(other.getParamId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getBillPeriod() == null ? other.getBillPeriod() == null : this.getBillPeriod().equals(other.getBillPeriod()))
            && (this.getGoodsType() == null ? other.getGoodsType() == null : this.getGoodsType().equals(other.getGoodsType()))
            && (this.getEstimateMileageRange() == null ? other.getEstimateMileageRange() == null : this.getEstimateMileageRange().equals(other.getEstimateMileageRange()))
            && (this.getEstimateTimeCostRange() == null ? other.getEstimateTimeCostRange() == null : this.getEstimateTimeCostRange().equals(other.getEstimateTimeCostRange()))
            && (this.getFunctionIds() == null ? other.getFunctionIds() == null : this.getFunctionIds().equals(other.getFunctionIds()))
            && (this.getRequireMark() == null ? other.getRequireMark() == null : this.getRequireMark().equals(other.getRequireMark()))
            && (this.getPricingMethod() == null ? other.getPricingMethod() == null : this.getPricingMethod().equals(other.getPricingMethod()))
            && (this.getPricingRule() == null ? other.getPricingRule() == null : this.getPricingRule().equals(other.getPricingRule()))
            && (this.getCancelReason() == null ? other.getCancelReason() == null : this.getCancelReason().equals(other.getCancelReason()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getParamId() == null) ? 0 : getParamId().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getBillPeriod() == null) ? 0 : getBillPeriod().hashCode());
        result = prime * result + ((getGoodsType() == null) ? 0 : getGoodsType().hashCode());
        result = prime * result + ((getEstimateMileageRange() == null) ? 0 : getEstimateMileageRange().hashCode());
        result = prime * result + ((getEstimateTimeCostRange() == null) ? 0 : getEstimateTimeCostRange().hashCode());
        result = prime * result + ((getFunctionIds() == null) ? 0 : getFunctionIds().hashCode());
        result = prime * result + ((getRequireMark() == null) ? 0 : getRequireMark().hashCode());
        result = prime * result + ((getPricingMethod() == null) ? 0 : getPricingMethod().hashCode());
        result = prime * result + ((getPricingRule() == null) ? 0 : getPricingRule().hashCode());
        result = prime * result + ((getCancelReason() == null) ? 0 : getCancelReason().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", paramId=").append(paramId);
        sb.append(", taskId=").append(taskId);
        sb.append(", billPeriod=").append(billPeriod);
        sb.append(", goodsType=").append(goodsType);
        sb.append(", estimateMileageRange=").append(estimateMileageRange);
        sb.append(", estimateTimeCostRange=").append(estimateTimeCostRange);
        sb.append(", functionIds=").append(functionIds);
        sb.append(", requireMark=").append(requireMark);
        sb.append(", pricingMethod=").append(pricingMethod);
        sb.append(", pricingRule=").append(pricingRule);
        sb.append(", cancelReason=").append(cancelReason);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        paramId,
        taskId,
        billPeriod,
        goodsType,
        estimateMileageRange,
        estimateTimeCostRange,
        functionIds,
        requireMark,
        pricingMethod,
        pricingRule,
        cancelReason;

        public String asc() {
            return column() + " ASC";
        }

        public String desc() {
            return column() + " DESC";
        }

        private String column() {
            StringBuilder buffer = new StringBuilder();
            char[] charArray = this.name().toCharArray();
            for(char ch : charArray) {
                if(Character.isUpperCase(ch)){
                    buffer.append("_");
                    buffer.append(Character.toLowerCase(ch));
                } else {
                    buffer.append(ch);
                }
            }
            return buffer.toString();
        }
    }
}