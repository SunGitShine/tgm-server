package com.juma.tgm.waybill.domain;

import java.io.Serializable;

public class WaybillReceiveAddressCargo implements Serializable {
    private Integer cargoId;

    private Integer addressId;

    private String cargoName;

    private String cargoType;

    private Float cargoVolume;

    private Integer cargoPackages;

    private Float cargoWeight;

    private static final long serialVersionUID = 1L;

    public Integer getCargoId() {
        return cargoId;
    }

    public void setCargoId(Integer cargoId) {
        this.cargoId = cargoId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName == null ? null : cargoName.trim();
    }

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType == null ? null : cargoType.trim();
    }

    public Float getCargoVolume() {
        return cargoVolume;
    }

    public void setCargoVolume(Float cargoVolume) {
        this.cargoVolume = cargoVolume;
    }

    public Integer getCargoPackages() {
        return cargoPackages;
    }

    public void setCargoPackages(Integer cargoPackages) {
        this.cargoPackages = cargoPackages;
    }

    public Float getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(Float cargoWeight) {
        this.cargoWeight = cargoWeight;
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
        WaybillReceiveAddressCargo other = (WaybillReceiveAddressCargo) that;
        return (this.getCargoId() == null ? other.getCargoId() == null : this.getCargoId().equals(other.getCargoId()))
            && (this.getAddressId() == null ? other.getAddressId() == null : this.getAddressId().equals(other.getAddressId()))
            && (this.getCargoName() == null ? other.getCargoName() == null : this.getCargoName().equals(other.getCargoName()))
            && (this.getCargoType() == null ? other.getCargoType() == null : this.getCargoType().equals(other.getCargoType()))
            && (this.getCargoVolume() == null ? other.getCargoVolume() == null : this.getCargoVolume().equals(other.getCargoVolume()))
            && (this.getCargoPackages() == null ? other.getCargoPackages() == null : this.getCargoPackages().equals(other.getCargoPackages()))
            && (this.getCargoWeight() == null ? other.getCargoWeight() == null : this.getCargoWeight().equals(other.getCargoWeight()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCargoId() == null) ? 0 : getCargoId().hashCode());
        result = prime * result + ((getAddressId() == null) ? 0 : getAddressId().hashCode());
        result = prime * result + ((getCargoName() == null) ? 0 : getCargoName().hashCode());
        result = prime * result + ((getCargoType() == null) ? 0 : getCargoType().hashCode());
        result = prime * result + ((getCargoVolume() == null) ? 0 : getCargoVolume().hashCode());
        result = prime * result + ((getCargoPackages() == null) ? 0 : getCargoPackages().hashCode());
        result = prime * result + ((getCargoWeight() == null) ? 0 : getCargoWeight().hashCode());
        return result;
    }
}