package com.juma.tgm.project.vo;

import com.juma.tgm.project.domain.ProjectFreightRule;
import com.juma.tgm.project.domain.ValuationWay;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ProjectFreightRuleVo
 * @Description:
 * @author: liang
 * @date: 2017-09-28 16:14
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class ProjectFreightRuleVo extends ProjectFreightRule implements Serializable {

    /**
     * 计价方式
     */
//    @JSONField(serialize = false)
    private List<ValuationWay> valuationWays = new ArrayList<ValuationWay>();

    private String truckTypeName;


    /**
     * 车型名称
     * @return
     */
    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
    }

    public List<ValuationWay> getValuationWays() {
        return valuationWays;
    }

    public void setValuationWays(List<ValuationWay> valuationWays) {
        this.valuationWays = valuationWays;
    }
    
    

}
