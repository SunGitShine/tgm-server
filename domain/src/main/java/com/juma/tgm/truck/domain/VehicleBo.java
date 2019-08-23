package com.juma.tgm.truck.domain;

import com.juma.server.vm.domain.Driver;
import com.juma.server.vm.domain.Vehicle;

public class VehicleBo extends Vehicle {

    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6342564469014188100L;

    private Driver driver;
    
    private Integer flightId;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }
    
}
