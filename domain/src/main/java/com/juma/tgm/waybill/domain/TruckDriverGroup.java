package com.juma.tgm.waybill.domain;

import java.io.Serializable;

import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.truck.domain.Truck;

public class TruckDriverGroup implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3862431452295492078L;

    private Truck truck;

    private Driver driver;

    public TruckDriverGroup() {}

    public TruckDriverGroup(Truck truck, Driver driver) {
        this.truck = truck;
        this.driver = driver;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

}
