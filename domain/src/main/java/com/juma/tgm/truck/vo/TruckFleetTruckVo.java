package com.juma.tgm.truck.vo;

import com.juma.tgm.truck.domain.TruckFleetTruck;

/**
 * @ClassName TruckFleetTruckVo
 * @Description TODO
 * @Author weilibin
 * @Date 2019-05-17 16:33
 * @Version 1.0.0
 */

public class TruckFleetTruckVo extends TruckFleetTruck {

    private String driverName;
    private String driverPhone;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }
}
