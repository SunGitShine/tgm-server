package com.juma.tgm.project.vo;

import com.juma.tgm.project.domain.RoadMapPriceRule;
import com.juma.tgm.project.domain.ValuationWay;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class RoadMapPriceRuleVo extends RoadMapPriceRule implements Serializable {
    private Integer waybillId;

    /**
     * 车型名称
     */
    private String truckName;

    /**
     * 计价维度
     */
    private List<ValuationWay> valuationWays = new ArrayList<ValuationWay>();

    /**
     * 税前运费
     */
    private BigDecimal estimateFreight;

    public BigDecimal getEstimateFreight() {
        return estimateFreight;
    }

    public void setEstimateFreight(BigDecimal estimateFreight) {
        this.estimateFreight = estimateFreight;
    }

    public List<ValuationWay> getValuationWays() {
        return valuationWays;
    }

    public void setValuationWays(List<ValuationWay> valuationWays) {
        this.valuationWays = valuationWays;
    }

    public String getTruckName() {
        return truckName;
    }

    public void setTruckName(String truckName) {
        this.truckName = truckName;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }
}