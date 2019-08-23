package com.juma.tgm.gaode.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 逆地理编码
 * 
 * @author weilibin
 *
 */

public class Regeocode implements Serializable {

    private static final long serialVersionUID = 6342788735702888608L;
    private String formattedAddress;
    private AddressComponent addressComponent = new AddressComponent();
    private List<Aois> aois = new ArrayList<Aois>();

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public AddressComponent getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(AddressComponent addressComponent) {
        this.addressComponent = addressComponent;
    }

    public List<Aois> getAois() {
        return aois;
    }

    public void setAois(List<Aois> aois) {
        this.aois = aois;
    }

    @Override
    public String toString() {
        return "Regeocode [formattedAddress=" + formattedAddress + ", addressComponent=" + addressComponent + ", aois="
                + aois + "]";
    }

}
