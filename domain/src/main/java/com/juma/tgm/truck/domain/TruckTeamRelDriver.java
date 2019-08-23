package com.juma.tgm.truck.domain;

import com.juma.tgm.base.domain.BaseDomain;

/**
 * truck_team_rel_driver - 参考司机
 * 
 * @author 2016-05-09
 * @version 1.0
 */
public class TruckTeamRelDriver extends BaseDomain {

    private static final long serialVersionUID = -5306477708842996630L;
    private Integer driverId;
    private Integer truckFleetId;

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getTruckFleetId() {
        return truckFleetId;
    }

    public void setTruckFleetId(Integer truckFleetId) {
        this.truckFleetId = truckFleetId;
    }

}