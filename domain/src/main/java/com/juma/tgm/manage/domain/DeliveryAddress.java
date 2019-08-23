package com.juma.tgm.manage.domain;

import com.juma.tgm.base.domain.BaseDomain;

/**
 * delivery_address - 发货地址
 * 
 * @author 2016-05-09
 * @version 1.0
 */
public class DeliveryAddress extends BaseDomain {

    private static final long serialVersionUID = 3253455635284441556L;
    private Integer addressId;
    private Integer truckCustomerId;
    private String regionCode;
    private String addressDetail;
    private String contactName;
    private String contactPhone;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getTruckCustomerId() {
        return truckCustomerId;
    }

    public void setTruckCustomerId(Integer truckCustomerId) {
        this.truckCustomerId = truckCustomerId;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

}