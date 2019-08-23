package com.juma.tgm.project.vo;

import java.io.Serializable;
import java.util.List;

import com.juma.tgm.project.domain.RoadMap;
import com.juma.tgm.project.domain.RoadMapDestAdress;
import com.juma.tgm.project.domain.RoadMapPriceRule;
import com.juma.tgm.project.domain.RoadMapSrcAdress;

/**
 * @ClassName RoadMapVo.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年9月28日 上午10:45:51
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class RoadMapVo implements Serializable {

    private RoadMap roadMap;

    /**
     * 价格信息
     */
    private List<RoadMapPriceRule> listRoadMapPriceRule;
    /**
     * 取货地
     */
    private List<RoadMapSrcAdress> listRoadMapSrcAdress;
    /**
     * 配送地
     */
    private List<RoadMapDestAdress> listRoadMapDestAdress;

    public RoadMap getRoadMap() {
        return roadMap;
    }

    public void setRoadMap(RoadMap roadMap) {
        this.roadMap = roadMap;
    }

    public List<RoadMapPriceRule> getListRoadMapPriceRule() {
        return listRoadMapPriceRule;
    }

    public void setListRoadMapPriceRule(List<RoadMapPriceRule> listRoadMapPriceRule) {
        this.listRoadMapPriceRule = listRoadMapPriceRule;
    }

    public List<RoadMapSrcAdress> getListRoadMapSrcAdress() {
        return listRoadMapSrcAdress;
    }

    public void setListRoadMapSrcAdress(List<RoadMapSrcAdress> listRoadMapSrcAdress) {
        this.listRoadMapSrcAdress = listRoadMapSrcAdress;
    }

    public List<RoadMapDestAdress> getListRoadMapDestAdress() {
        return listRoadMapDestAdress;
    }

    public void setListRoadMapDestAdress(List<RoadMapDestAdress> listRoadMapDestAdress) {
        this.listRoadMapDestAdress = listRoadMapDestAdress;
    }

}
