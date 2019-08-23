package com.juma.tgm.configure.domain;

import java.io.Serializable;
import java.util.Date;

import com.juma.tgm.truck.domain.TruckType;

/**
 * @ClassName TruckTypeCity.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年11月14日 下午6:47:14
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class TruckTypeCity implements Serializable {

    private static final long serialVersionUID = 4215353012954812652L;
    private Integer truckTypeCityId;
    private String regionCode;
    private Integer tenantId;
    private String tenantCode;
    private Integer truckTypeId;
    private Boolean isEnable;
    private Integer orderNo;
    private Date createTime;
    private Integer createUserId;
    private Date lastUpdateTime;
    private Integer lastUpdateUserId;

    // 冗余
    private String regionName;
    private TruckType truckType;

    public Integer getTruckTypeCityId() {
        return truckTypeCityId;
    }

    public void setTruckTypeCityId(Integer truckTypeCityId) {
        this.truckTypeCityId = truckTypeCityId;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public Integer getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(Integer truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(Integer lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public TruckType getTruckType() {
        return truckType;
    }

    public void setTruckType(TruckType truckType) {
        this.truckType = truckType;
    }

}
