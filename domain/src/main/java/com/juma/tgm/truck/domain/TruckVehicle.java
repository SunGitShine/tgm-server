package com.juma.tgm.truck.domain;

import java.io.Serializable;

/**
 * @ClassName TruckVehicle.java
 * @Description 车辆信息
 * @author Libin.Wei
 * @Date 2017年3月9日 下午3:47:40
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class TruckVehicle implements Serializable {

    private static final long serialVersionUID = -3629594420752244380L;
    private Truck truck;
    private TruckExtend vehicle;
    // 入城证中文名
    private String entryLicenseCnName;
    // 尾板中文名
    private String tailBoardCnName;
    // 停放城市中文
    private String parkingTownCn;
    // 停放地简称中文
    private String parkingAddressName;

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public TruckExtend getVehicle() {
        return vehicle;
    }

    public void setVehicle(TruckExtend vehicle) {
        this.vehicle = vehicle;
    }

    public String getParkingTownCn() {
        return parkingTownCn;
    }

    public void setParkingTownCn(String parkingTownCn) {
        this.parkingTownCn = parkingTownCn;
    }

    public String getEntryLicenseCnName() {
        return entryLicenseCnName;
    }

    public void setEntryLicenseCnName(String entryLicenseCnName) {
        this.entryLicenseCnName = entryLicenseCnName;
    }

    public String getTailBoardCnName() {
        return tailBoardCnName;
    }

    public void setTailBoardCnName(String tailBoardCnName) {
        this.tailBoardCnName = tailBoardCnName;
    }

    public String getParkingAddressName() {
        return parkingAddressName;
    }

    public void setParkingAddressName(String parkingAddressName) {
        this.parkingAddressName = parkingAddressName;
    }

}
