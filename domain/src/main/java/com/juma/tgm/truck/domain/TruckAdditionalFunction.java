package com.juma.tgm.truck.domain;

import com.juma.tgm.base.domain.BaseDomain;

/**
 * truck_additional_function - 货车附加功能
 * 
 * @author 2016-05-09
 * @version 1.0
 */
public class TruckAdditionalFunction extends BaseDomain {

    private static final long serialVersionUID = 7160439590566530035L;
    private Integer truckId;
    private Integer additionalFunctionId;

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public Integer getAdditionalFunctionId() {
        return additionalFunctionId;
    }

    public void setAdditionalFunctionId(Integer additionalFunctionId) {
        this.additionalFunctionId = additionalFunctionId;
    }

}