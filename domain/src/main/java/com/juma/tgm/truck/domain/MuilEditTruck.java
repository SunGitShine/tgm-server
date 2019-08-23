package com.juma.tgm.truck.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MuilEditTruck implements Serializable {

    private static final long serialVersionUID = 4882460931920788514L;

    private Date estimateFinishTime;

    private List<Integer> truckIds = new ArrayList<Integer>();
    private List<Integer> waybillIds = new ArrayList<Integer>();

    public Date getEstimateFinishTime() {
        return estimateFinishTime;
    }

    public void setEstimateFinishTime(Date estimateFinishTime) {
        this.estimateFinishTime = estimateFinishTime;
    }

    public List<Integer> getTruckIds() {
        return truckIds;
    }

    public void setTruckIds(List<Integer> truckIds) {
        this.truckIds = truckIds;
    }

    public List<Integer> getWaybillIds() {
        return waybillIds;
    }

    public void setWaybillIds(List<Integer> waybillIds) {
        this.waybillIds = waybillIds;
    }

}
