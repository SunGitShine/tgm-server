package com.juma.tgm.truck.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by shawn_lin on 2017/5/31.
 */
public class TruckDriverVo implements Serializable {
    private static final long serialVersionUID = 1160634902608822300L;
    private String driverName;
    private String driverPhone;
    private String truckTypeName;
    private String plateNumber;
    private Integer truckId;
    private boolean paceWith;
    private String truckInfoStr;
    private Integer load;
    private BigDecimal volume;

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

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public boolean isPaceWith() {
        return paceWith;
    }

    public void setPaceWith(boolean paceWith) {
        this.paceWith = paceWith;
    }

    public String getTruckInfoStr() {
        return truckInfoStr;
    }

    public void setTruckInfoStr(String truckInfoStr) {
        this.truckInfoStr = truckInfoStr;
    }

    public Integer getLoad() {
        return load;
    }

    public void setLoad(Integer load) {
        this.load = load;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

}
