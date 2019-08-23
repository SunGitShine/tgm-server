package com.juma.tgm.customerManager.domain;

import java.io.Serializable;

public class FixedDemandTruck implements Serializable {
    private Integer fixedDemandTruckId;

    private Integer fixedDemandId;

    private Integer truckId;

    private static final long serialVersionUID = 1L;

    public Integer getFixedDemandTruckId() {
        return fixedDemandTruckId;
    }

    public void setFixedDemandTruckId(Integer fixedDemandTruckId) {
        this.fixedDemandTruckId = fixedDemandTruckId;
    }

    public Integer getFixedDemandId() {
        return fixedDemandId;
    }

    public void setFixedDemandId(Integer fixedDemandId) {
        this.fixedDemandId = fixedDemandId;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FixedDemandTruck other = (FixedDemandTruck) that;
        return (this.getFixedDemandTruckId() == null ? other.getFixedDemandTruckId() == null : this.getFixedDemandTruckId().equals(other.getFixedDemandTruckId()))
            && (this.getFixedDemandId() == null ? other.getFixedDemandId() == null : this.getFixedDemandId().equals(other.getFixedDemandId()))
            && (this.getTruckId() == null ? other.getTruckId() == null : this.getTruckId().equals(other.getTruckId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFixedDemandTruckId() == null) ? 0 : getFixedDemandTruckId().hashCode());
        result = prime * result + ((getFixedDemandId() == null) ? 0 : getFixedDemandId().hashCode());
        result = prime * result + ((getTruckId() == null) ? 0 : getTruckId().hashCode());
        return result;
    }
}