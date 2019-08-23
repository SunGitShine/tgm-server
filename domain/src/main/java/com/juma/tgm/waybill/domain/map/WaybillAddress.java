package com.juma.tgm.waybill.domain.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WaybillAddress implements Comparable<WaybillAddress>,Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4309578269921287122L;
    private Integer addressId;
    private Integer waybillId;
    private String regionCode;
    private String addressName;
    private String addressDetail;
    private String contactName;
    private String contactPhone;
    private String spareContactPhone;
    private String coordinates;
    private String simpleAddress;
    private String cityname;
    private Integer isArrived;
    private Integer sequence;
    private Integer type;
    
    public Integer getAddressId() {
        return addressId;
    }
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
    public Integer getWaybillId() {
        return waybillId;
    }
    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }
    public String getRegionCode() {
        return regionCode;
    }
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
    public String getAddressName() {
        return addressName;
    }
    public void setAddressName(String addressName) {
        this.addressName = addressName;
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
    public String getSpareContactPhone() {
        return spareContactPhone;
    }
    public void setSpareContactPhone(String spareContactPhone) {
        this.spareContactPhone = spareContactPhone;
    }
    public String getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
    public String getSimpleAddress() {
        return simpleAddress;
    }
    public void setSimpleAddress(String simpleAddress) {
        this.simpleAddress = simpleAddress;
    }
    public String getCityname() {
        return cityname;
    }
    public void setCityname(String cityname) {
        this.cityname = cityname;
    }
    public Integer getIsArrived() {
        return isArrived;
    }
    public void setIsArrived(Integer isArrived) {
        this.isArrived = isArrived;
    }
    public Integer getSequence() {
        return sequence;
    }
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    @Override
    public int compareTo(WaybillAddress o) {
        return this.getSequence().compareTo(o.getSequence());
    }
    
    public static void main(String[] args) {
        
        List<WaybillAddress> rows = new ArrayList<WaybillAddress>();
        WaybillAddress a1 = new WaybillAddress();
        a1.setSequence(6);
        rows.add(a1);
        
        WaybillAddress a2 = new WaybillAddress();
        a2.setSequence(7);
        rows.add(a2);
        
        WaybillAddress a3 = new WaybillAddress();
        a3.setSequence(4);
        rows.add(a3);
        
        Collections.sort(rows);

        
        System.out.println(rows);
    }
    
}
