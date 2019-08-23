package com.juma.tgm.waybill.domain;

import com.juma.tgm.base.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * waybill_delivery_address - 运单发货地址
 * 
 * @author 2016-05-09
 * @version 1.0
 */
@ApiModel(value = "运单配送地")
public class WaybillDeliveryAddress extends BaseDomain implements Serializable{

    private static final long serialVersionUID = -2530761807060165332L;
    private Integer addressId;
    @ApiModelProperty(value = "运单id")
    private Integer waybillId;
    @ApiModelProperty(value = "地址名称")
    private String addressName;
    @ApiModelProperty(value = "详细地址")
    private String addressDetail;
    @ApiModelProperty(value = "联系人")
    private String contactName;
    @ApiModelProperty(value = "联系电话")
    private String contactPhone;
    @ApiModelProperty(value = "坐标")
    private String coordinates;
    private String regionCode;
    private String spareContactPhone;
    private String simpleAddress;
    private String cityname;
    private Integer isArrived;
    private Integer sequence;
    private Integer fenceId;
    private Integer status;

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

    public String getSpareContactPhone() {
        return spareContactPhone;
    }

    public void setSpareContactPhone(String spareContactPhone) {
        this.spareContactPhone = spareContactPhone;
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

    @Override
    public String toString() {
        return "WaybillDeliveryAddress{" +
                "addressId=" + addressId +
                ", waybillId=" + waybillId +
                ", regionCode='" + regionCode + '\'' +
                ", addressName='" + addressName + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", spareContactPhone='" + spareContactPhone + '\'' +
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