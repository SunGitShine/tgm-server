package com.juma.tgm.project.domain;

import java.io.Serializable;
import java.util.Date;

public class RoadMapPriceRule implements Serializable {
    private Integer roadMapPriceRuleId;

    private Integer roadMapId;

    private Byte valuationWay;

    private Integer truckTypeId;

    private String valuationModelJson;

    private Integer createUserId;

    private Date createTime;

    private String truckTypeName;//计价车型

    private static final long serialVersionUID = 1L;

    public Integer getRoadMapPriceRuleId() {
        return roadMapPriceRuleId;
    }

    public void setRoadMapPriceRuleId(Integer roadMapPriceRuleId) {
        this.roadMapPriceRuleId = roadMapPriceRuleId;
    }

    public Integer getRoadMapId() {
        return roadMapId;
    }

    public void setRoadMapId(Integer roadMapId) {
        this.roadMapId = roadMapId;
    }

    public Byte getValuationWay() {
        return valuationWay;
    }

    public void setValuationWay(Byte valuationWay) {
        this.valuationWay = valuationWay;
    }

    public Integer getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(Integer truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public String getValuationModelJson() {
        return valuationModelJson;
    }

    public void setValuationModelJson(String valuationModelJson) {
        this.valuationModelJson = valuationModelJson == null ? null : valuationModelJson.trim();
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
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
        RoadMapPriceRule other = (RoadMapPriceRule) that;
        return (this.getRoadMapPriceRuleId() == null ? other.getRoadMapPriceRuleId() == null : this.getRoadMapPriceRuleId().equals(other.getRoadMapPriceRuleId()))
            && (this.getRoadMapId() == null ? other.getRoadMapId() == null : this.getRoadMapId().equals(other.getRoadMapId()))
            && (this.getValuationWay() == null ? other.getValuationWay() == null : this.getValuationWay().equals(other.getValuationWay()))
            && (this.getTruckTypeId() == null ? other.getTruckTypeId() == null : this.getTruckTypeId().equals(other.getTruckTypeId()))
            && (this.getValuationModelJson() == null ? other.getValuationModelJson() == null : this.getValuationModelJson().equals(other.getValuationModelJson()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getTruckTypeName() == null ? other.getTruckTypeName() == null : this.getTruckTypeName().equals(other.getTruckTypeName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRoadMapPriceRuleId() == null) ? 0 : getRoadMapPriceRuleId().hashCode());
        result = prime * result + ((getRoadMapId() == null) ? 0 : getRoadMapId().hashCode());
        result = prime * result + ((getValuationWay() == null) ? 0 : getValuationWay().hashCode());
        result = prime * result + ((getTruckTypeId() == null) ? 0 : getTruckTypeId().hashCode());
        result = prime * result + ((getValuationModelJson() == null) ? 0 : getValuationModelJson().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getTruckTypeName() == null) ? 0 : getTruckTypeName().hashCode());
        return result;
    }
}