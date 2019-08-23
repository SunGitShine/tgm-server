package com.juma.tgm.project.domain;

import java.io.Serializable;
import java.util.Date;

public class RoadMapSrcAdress implements Serializable {
    private Integer roadMapSrcAdressId;

    private Integer roadMapId;

    private String regionCode;

    private String addressName;

    private String addressDetail;

    private String coordinates;

    private String contactName;

    private String contactPhone;

    private Date createTime;

    private Integer createUserId;

    private static final long serialVersionUID = 1L;

    public Integer getRoadMapSrcAdressId() {
        return roadMapSrcAdressId;
    }

    public void setRoadMapSrcAdressId(Integer roadMapSrcAdressId) {
        this.roadMapSrcAdressId = roadMapSrcAdressId;
    }

    public Integer getRoadMapId() {
        return roadMapId;
    }

    public void setRoadMapId(Integer roadMapId) {
        this.roadMapId = roadMapId;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode == null ? null : regionCode.trim();
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName == null ? null : addressName.trim();
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail == null ? null : addressDetail.trim();
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates == null ? null : coordinates.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
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
        RoadMapSrcAdress other = (RoadMapSrcAdress) that;
        return (this.getRoadMapSrcAdressId() == null ? other.getRoadMapSrcAdressId() == null : this.getRoadMapSrcAdressId().equals(other.getRoadMapSrcAdressId()))
            && (this.getRoadMapId() == null ? other.getRoadMapId() == null : this.getRoadMapId().equals(other.getRoadMapId()))
            && (this.getRegionCode() == null ? other.getRegionCode() == null : this.getRegionCode().equals(other.getRegionCode()))
            && (this.getAddressName() == null ? other.getAddressName() == null : this.getAddressName().equals(other.getAddressName()))
            && (this.getAddressDetail() == null ? other.getAddressDetail() == null : this.getAddressDetail().equals(other.getAddressDetail()))
            && (this.getCoordinates() == null ? other.getCoordinates() == null : this.getCoordinates().equals(other.getCoordinates()))
            && (this.getContactName() == null ? other.getContactName() == null : this.getContactName().equals(other.getContactName()))
            && (this.getContactPhone() == null ? other.getContactPhone() == null : this.getContactPhone().equals(other.getContactPhone()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRoadMapSrcAdressId() == null) ? 0 : getRoadMapSrcAdressId().hashCode());
        result = prime * result + ((getRoadMapId() == null) ? 0 : getRoadMapId().hashCode());
        result = prime * result + ((getRegionCode() == null) ? 0 : getRegionCode().hashCode());
        result = prime * result + ((getAddressName() == null) ? 0 : getAddressName().hashCode());
        result = prime * result + ((getAddressDetail() == null) ? 0 : getAddressDetail().hashCode());
        result = prime * result + ((getCoordinates() == null) ? 0 : getCoordinates().hashCode());
        result = prime * result + ((getContactName() == null) ? 0 : getContactName().hashCode());
        result = prime * result + ((getContactPhone() == null) ? 0 : getContactPhone().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        return result;
    }
}