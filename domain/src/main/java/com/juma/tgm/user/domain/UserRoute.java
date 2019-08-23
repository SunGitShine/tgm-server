package com.juma.tgm.user.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserRoute implements Serializable {

    private static final long serialVersionUID = 8017154459807188105L;

    private UserRouteMaster deliverAddress;
    
    private List<UserRouteDetail> receiveAddressList = new ArrayList<UserRouteDetail>();

    public UserRouteMaster getDeliverAddress() {
        return deliverAddress;
    }

    public void setDeliverAddress(UserRouteMaster deliverAddress) {
        this.deliverAddress = deliverAddress;
    }

    public List<UserRouteDetail> getReceiveAddressList() {
        return receiveAddressList;
    }

    public void setReceiveAddressList(List<UserRouteDetail> receiveAddressList) {
        this.receiveAddressList = receiveAddressList;
    }
    
}
