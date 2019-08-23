package com.juma.tgm.task.vo.gateway;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "线路计价规则")
public class RoadMapPriceRuleVo implements Serializable {

    @ApiModelProperty(value = "线路id")
    private Integer roadMapId;

    @ApiModelProperty(value = "线路计价规则id")
    private Integer roadMapPriceRuleId;

    @ApiModelProperty(value = "车型id")
    private Integer truckTypeId;

    @ApiModelProperty(value = "车型name")
    private String truckTypeName;

    @ApiModelProperty(value = "计价规则")
    private String valuationModelJson;

    @ApiModelProperty(value = "计价方式")
    private Integer valuationWay;

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

    public Integer getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(Integer truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
    }

    public String getValuationModelJson() {
        return valuationModelJson;
    }

    public void setValuationModelJson(String valuationModelJson) {
        this.valuationModelJson = valuationModelJson;
    }

    public Integer getValuationWay() {
        return valuationWay;
    }

    public void setValuationWay(Integer valuationWay) {
        this.valuationWay = valuationWay;
    }
}
