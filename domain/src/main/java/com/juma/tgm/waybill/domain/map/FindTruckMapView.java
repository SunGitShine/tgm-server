package com.juma.tgm.waybill.domain.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FindTruckMapView implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7700319530503728445L;

    private Integer waybillId;
    
    private List<TruckMapView> truckMapViews =new ArrayList<TruckMapView>();

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public List<TruckMapView> getTruckMapViews() {
        return truckMapViews;
    }

    public void setTruckMapViews(List<TruckMapView> truckMapViews) {
        this.truckMapViews = truckMapViews;
    }

    
    
}
