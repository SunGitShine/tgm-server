package com.juma.tgm.waybill.domain.map;

import java.io.Serializable;

public class TruckMapView implements Serializable {
    


    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3625676705843706644L;

    private Integer truckId;
    
    private String plateNumber;
    
    private Integer driverId;
    
    private String driverName;
    
    private Integer status;//0 空闲 1 有任务
    
    private Integer flightId;

    private Double longitude;

    private Double latitude;

    private String parkAddress;

    private String parkRegionCode;
    
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getParkAddress() {
        return parkAddress;
    }

    public void setParkAddress(String parkAddress) {
        this.parkAddress = parkAddress;
    }

    public String getParkRegionCode() {
        return parkRegionCode;
    }

    public void setParkRegionCode(String parkRegionCode) {
        this.parkRegionCode = parkRegionCode;
    }

}
