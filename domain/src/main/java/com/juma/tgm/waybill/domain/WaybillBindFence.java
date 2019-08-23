package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName WaybillBindFence.java 运单绑定电子围栏
 * @author Libin.Wei
 * @Date 2017年4月19日 下午2:48:52
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */
public class WaybillBindFence implements Serializable {

    private static final long serialVersionUID = 8601879246809806265L;
    private Integer waybillBindFenceId;
    private Integer addressId;
    private Integer waybillId;
    private Integer fenceId;
    private Integer status;
    private Integer sign;
    private String deviceNo;
    private Integer deviceType;
    private Integer createUserId;
    private Date createTime;
    private Integer lastUpdateUserId;
    private Date lastUpdateTime;

    public Integer getWaybillBindFenceId() {
        return waybillBindFenceId;
    }

    public void setWaybillBindFenceId(Integer waybillBindFenceId) {
        this.waybillBindFenceId = waybillBindFenceId;
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

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        WaybillBindFence that = (WaybillBindFence) o;
        return waybillBindFenceId == that.waybillBindFenceId
                && com.google.common.base.Objects.equal(addressId, that.addressId)
                && com.google.common.base.Objects.equal(waybillId, that.waybillId)
                && com.google.common.base.Objects.equal(fenceId, that.fenceId)
                && com.google.common.base.Objects.equal(sign, that.sign);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(waybillBindFenceId, addressId, waybillId, fenceId, sign);
    }

    public enum Sign {
        DELIVERY_ADDRESS(0, "取货地"),
        RECEIVE_ADDRESS(1, "目的地");

        private int code;
        private String descr;

        private Sign(int code, String descr) {
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

    public enum Status {
        EFFECTIVE(1, "有效"),
        INVALID(2, "失效"),
        TRUCK_ENTRY_INVALID(3, "失效(车辆驶入)");

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

    @Override
    public String toString() {
        return "WaybillBindFence [waybillBindFenceId=" + waybillBindFenceId + ", addressId=" + addressId
                + ", waybillId=" + waybillId + ", fenceId=" + fenceId + ", status=" + status + ", sign=" + sign
                + ", createUserId=" + createUserId + ", createTime=" + createTime + "]";
    }

}
