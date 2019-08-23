package com.juma.tgm.truck.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * truck_type - 车型
 * 
 * @author 2016-05-09
 * @version 1.0
 */
public class TruckType implements Serializable {

    private static final long serialVersionUID = 6309402120874864598L;
    private Integer truckTypeId;
    private Integer truckLengthId;
    private Integer vehicleBoxType;
    private String typeDescription;
    private BigDecimal truckTypeLoad;
    private BigDecimal truckTypeVolume;
    private BigDecimal redundancyLoad;
    private BigDecimal redundancyVolume;
    private Integer orderNum;
    private Integer lengthLowerLimit;
    private Integer lengthUpperLimit;
    private Integer tenantId;
    private String tenantCode;
    private Boolean isDelete;
    private Date createTime;
    private Integer createUserId;
    private Integer lastUpdateUserId;
    private Date lastUpdateTime;

    // 冗余：车型名称
    private String truckTypeName;

    public TruckType() {
    }

    public TruckType(Integer truckLengthId, Integer vehicleBoxType) {
        this.truckLengthId = truckLengthId;
        this.vehicleBoxType = vehicleBoxType;
    }

//    public TruckType(TruckBo truckBo) {
//        this.truckTypeId = truckBo.getTruckTypeId();
//    }

    public Integer getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(Integer truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public Integer getTruckLengthId() {
        return truckLengthId;
    }

    public void setTruckLengthId(Integer truckLengthId) {
        this.truckLengthId = truckLengthId;
    }

    public Integer getVehicleBoxType() {
        return vehicleBoxType;
    }

    public void setVehicleBoxType(Integer vehicleBoxType) {
        this.vehicleBoxType = vehicleBoxType;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public BigDecimal getTruckTypeLoad() {
        return truckTypeLoad;
    }

    public void setTruckTypeLoad(BigDecimal truckTypeLoad) {
        this.truckTypeLoad = truckTypeLoad;
    }

    public BigDecimal getTruckTypeVolume() {
        return truckTypeVolume;
    }

    public void setTruckTypeVolume(BigDecimal truckTypeVolume) {
        this.truckTypeVolume = truckTypeVolume;
    }

    public BigDecimal getRedundancyLoad() {
        return redundancyLoad;
    }

    public void setRedundancyLoad(BigDecimal redundancyLoad) {
        this.redundancyLoad = redundancyLoad;
    }

    public BigDecimal getRedundancyVolume() {
        return redundancyVolume;
    }

    public void setRedundancyVolume(BigDecimal redundancyVolume) {
        this.redundancyVolume = redundancyVolume;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getLengthLowerLimit() {
        return lengthLowerLimit;
    }

    public void setLengthLowerLimit(Integer lengthLowerLimit) {
        this.lengthLowerLimit = lengthLowerLimit;
    }

    public Integer getLengthUpperLimit() {
        return lengthUpperLimit;
    }

    public void setLengthUpperLimit(Integer lengthUpperLimit) {
        this.lengthUpperLimit = lengthUpperLimit;
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

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
    }

}