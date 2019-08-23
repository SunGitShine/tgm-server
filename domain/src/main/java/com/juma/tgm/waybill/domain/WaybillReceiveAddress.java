package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * waybill_receive_address - 运单收货地址
 * 
 * @author 2016-05-09
 * @version 1.0
 */
public class WaybillReceiveAddress implements Serializable {

    private static final long serialVersionUID = -2784675903534079729L;
    private Integer addressId;
    private Integer waybillId;
    private String regionCode;
    private String addressName;
    private String addressDetail;
    private String contactName;
    private String contactPhone;
    private String coordinates;
    private String simpleAddress;
    private String cityname;
    private Integer isArrived;
    private Integer sequence;
    private Integer fenceId;
    private Integer status;
    private Boolean isDelete;
    private Date createTime;
    private Integer createUserId;
    private Integer lastUpdateUserId;
    private Date lastUpdateTime;

    public enum Arrive {
        NO(0,"未到达"),YES(1,"已到达");
        
        private int code;
        
        private String msg;
        
        private Arrive(int code,String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
        
    }
    
    public enum Status {
        NOT_FINISH(1, "未完成"),
        FINISH(2, "已完成");

        private int code;

        private String descr;

        private Status(int code, String descr) {
            this.code = code;
            this.descr = descr;
        }

        public int getCode() {
            return code;
        }

        public String getDescr() {
            return descr;
        }

    }
    
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

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
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

    public Integer getFenceId() {
        return fenceId;
    }

    public void setFenceId(Integer fenceId) {
        this.fenceId = fenceId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
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

    public Integer getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(Integer lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "WaybillReceiveAddress{" +
                "addressId=" + addressId +
                ", waybillId=" + waybillId +
                ", regionCode='" + regionCode + '\'' +
                ", addressName='" + addressName + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", coordinates='" + coordinates + '\'' +
                ", simpleAddress='" + simpleAddress + '\'' +
                ", cityname='" + cityname + '\'' +
                ", isArrived=" + isArrived +
                '}';
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}