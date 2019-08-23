package com.juma.tgm.project.domain;

import java.io.Serializable;

public class ProjectFreightRule implements Serializable {
    private Integer roadMapId;
    private String roadMapName;
    private Integer projectId;
    private Integer truckTypeId;
    private Integer whoWriteWork;// 谁填写工作量 1:司机 2:经济人
    private Integer valuationWay;
    private String factorJson;

    public ProjectFreightRule() {
    }

    public ProjectFreightRule(Integer projectId, Integer whoWriteWork, String roadMapName, RoadMapPriceRule roadMapPriceRule) {
        super();
        this.projectId = projectId;
        this.roadMapId = roadMapPriceRule == null ? null : roadMapPriceRule.getRoadMapId();
        this.roadMapName = roadMapName;
        this.truckTypeId = roadMapPriceRule == null ? null : roadMapPriceRule.getTruckTypeId();
        this.whoWriteWork = whoWriteWork;
        this.valuationWay = roadMapPriceRule == null ? null : roadMapPriceRule.getValuationWay().intValue();
        this.factorJson = roadMapPriceRule == null ? null : roadMapPriceRule.getValuationModelJson();
    }

    public Integer getRoadMapId() {
        return roadMapId;
    }

    public void setRoadMapId(Integer roadMapId) {
        this.roadMapId = roadMapId;
    }

    public String getRoadMapName() {
        return roadMapName;
    }

    public void setRoadMapName(String roadMapName) {
        this.roadMapName = roadMapName;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(Integer truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public Integer getWhoWriteWork() {
        return whoWriteWork;
    }

    public void setWhoWriteWork(Integer whoWriteWork) {
        this.whoWriteWork = whoWriteWork;
    }

    public Integer getValuationWay() {
        return valuationWay;
    }

    public void setValuationWay(Integer valuationWay) {
        this.valuationWay = valuationWay;
    }

    public String getFactorJson() {
        return factorJson;
    }

    public void setFactorJson(String factorJson) {
        this.factorJson = factorJson;
    }

}