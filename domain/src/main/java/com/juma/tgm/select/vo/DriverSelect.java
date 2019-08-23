package com.juma.tgm.select.vo;

import java.io.Serializable;

public class DriverSelect implements Serializable {

    private Integer driverId;

    private String driverName;

    private String driverContactPhone;

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverContactPhone() {
        return driverContactPhone;
    }

    public void setDriverContactPhone(String driverContactPhone) {
        this.driverContactPhone = driverContactPhone;
    }
}
