package com.juma.tgm.project.vo;

import com.juma.tgm.project.domain.RoadMap;
import com.juma.tgm.project.domain.RoadMapDestAdress;
import com.juma.tgm.project.domain.RoadMapSrcAdress;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName RoadMapVo.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年9月28日 上午10:45:51
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class RoadMapQuery implements Serializable {

    private RoadMap roadMap;
    /**
     * 第一条取货地
     */
    private RoadMapSrcAdress roadMapSrcAdress;
    /**
     * 第一条配送地
     */
    private RoadMapDestAdress roadMapDestAdress;

    /**
     * 所有配送地
     */
    private List<RoadMapDestAdress> listRoadMapDestAdress;

    /**
     * 所有客户计价
     */
    private List<RoadMapPriceRuleVo> listRoadMapPriceRule;

    public List<RoadMapPriceRuleVo> getListRoadMapPriceRule() {
        return listRoadMapPriceRule;
    }

    public void setListRoadMapPriceRule(List<RoadMapPriceRuleVo> listRoadMapPriceRule) {
        this.listRoadMapPriceRule = listRoadMapPriceRule;
    }

    public List<RoadMapDestAdress> getListRoadMapDestAdress() {
        return listRoadMapDestAdress;
    }

    public void setListRoadMapDestAdress(List<RoadMapDestAdress> listRoadMapDestAdress) {
        this.listRoadMapDestAdress = listRoadMapDestAdress;
    }

    public RoadMap getRoadMap() {
        return roadMap;
    }

    public void setRoadMap(RoadMap roadMap) {
        this.roadMap = roadMap;
    }

    public RoadMapSrcAdress getRoadMapSrcAdress() {
        return roadMapSrcAdress;
    }

    public void setRoadMapSrcAdress(RoadMapSrcAdress roadMapSrcAdress) {
        this.roadMapSrcAdress = roadMapSrcAdress;
    }

    public RoadMapDestAdress getRoadMapDestAdress() {
        return roadMapDestAdress;
    }

    public void setRoadMapDestAdress(RoadMapDestAdress roadMapDestAdress) {
        this.roadMapDestAdress = roadMapDestAdress;
    }

}
