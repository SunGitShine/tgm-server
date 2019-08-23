package com.juma.tgm.waybill.domain.ai;

import java.io.Serializable;
import java.math.BigDecimal;

public class TruckFeature implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2150284630843105830L;

    private Integer truckId;
    
    private String plateNumber;//车牌
    
    private BigDecimal load;//载重
    
    private BigDecimal volume;//体积
    
    //===== 车辆实时 ======
    private Double currLng;//经度

    private Double currLat;//纬度
    
    //======停放地 =====
    private Double parkLng;//经度

    private Double parkLat;//纬度
    
    //============= 司机信息 ===============
    private Integer driverId;
    
    private String driverName;//司机名字
    
    

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public BigDecimal getLoad() {
        return load;
    }

    public void setLoad(BigDecimal load) {
        this.load = load;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

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

    public Double getCurrLng() {
        return currLng;
    }

    public void setCurrLng(Double currLng) {
        this.currLng = currLng;
    }

    public Double getCurrLat() {
        return currLat;
    }

    public void setCurrLat(Double currLat) {
        this.currLat = currLat;
    }

    public Double getParkLng() {
        return parkLng;
    }

    public void setParkLng(Double parkLng) {
        this.parkLng = parkLng;
    }

    public Double getParkLat() {
        return parkLat;
    }

    public void setParkLat(Double parkLat) {
        this.parkLat = parkLat;
    }
    
    
}
