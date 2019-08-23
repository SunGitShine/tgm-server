package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.juma.tgm.truck.domain.VehicleBo;


public class ToAutoMatchWaybill implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6609461840264927246L;

    private List<WaybillPlan> orders = new ArrayList<WaybillPlan>();
    
    private List<VehicleBo> vehicles = new ArrayList<VehicleBo>();

    public List<WaybillPlan> getOrders() {
        return orders;
    }

    public void setOrders(List<WaybillPlan> orders) {
        this.orders = orders;
    }

    public List<VehicleBo> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleBo> vehicles) {
        this.vehicles = vehicles;
    }

    public void addVehicle(VehicleBo vehicle) {
        vehicles.add(vehicle);
    }
    
}
