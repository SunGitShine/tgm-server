package com.juma.tgm.mq.domain;

import java.io.Serializable;

/**
 * @ClassName FenceEvent.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年2月27日 下午5:32:40
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class VmsEvent implements Serializable {

    private static final long serialVersionUID = 965646829318478642L;
    private Integer driverId;
    private Integer vendorId;
    private Integer tenantId;
    private Integer truckId;
    private String operateKey;

    @Deprecated
    private Integer vehicleId;

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public String getOperateKey() {
        return operateKey;
    }

    public void setOperateKey(String operateKey) {
        this.operateKey = operateKey;
    }

    // 电子围栏出发类型
    public enum OperateKey {
        ADD(1, "添加"), UPDATE(2, "更改"), DELETE(2, "删除");

        private int code;

        private String descr;

        private OperateKey(int code, String descr) {
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
        return "VmsEvent [vehicleId=" + vehicleId + ", driverId=" + driverId + ", operateKey=" + operateKey + "]";
    }

}
