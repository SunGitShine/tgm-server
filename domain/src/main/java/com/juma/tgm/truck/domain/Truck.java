package com.juma.tgm.truck.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName Truck.java
 * @Description 车辆基础信息
 * @author Libin.Wei
 * @Date 2017年3月31日 下午2:41:22
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class Truck implements Serializable {

    private static final long serialVersionUID = -763301581007845049L;
    private Integer truckId;
    // 车牌号
    private String plateNumber;
    // 车架号
    private String frameNo;
    // AMS车辆ID
    private Integer vehicleId;
    // 车辆状态
    private Integer status;
    // 入城证
    private Integer entryLicense;
    // 厢长
    private Integer vehicleBoxLength;
    // 厢型
    private Integer vehicleBoxType;
    // 尾板
    private Integer tailBoard;
    // 预估完成时间
    private Date estimateFinishTime;
    private Boolean isDelete;
    private Date createTime;
    private Integer createUserId;
    private Integer lastUpdateUserId;
    private Date lastUpdateTime;

    // 显示冗余
    // 车辆停运计划数量
    private Integer vehicleStopCount;
    private String truckTypeName;
    private Integer waybillId;
    // 车辆信息字符串
    private String truckInfoStr;
    // 车辆停放地
    private String parkingAddress;
    //车辆运营类型
    private Integer truckRunType;

    public enum Status {

        CLOSED(1, "停运"), OPEN(2, "营运");

        private int code;
        private String value;

        private Status(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getFrameNo() {
        return frameNo;
    }

    public void setFrameNo(String frameNo) {
        this.frameNo = frameNo;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getEntryLicense() {
        return entryLicense;
    }

    public void setEntryLicense(Integer entryLicense) {
        this.entryLicense = entryLicense;
    }

    public Integer getVehicleBoxLength() {
        return vehicleBoxLength;
    }

    public void setVehicleBoxLength(Integer vehicleBoxLength) {
        this.vehicleBoxLength = vehicleBoxLength;
    }

    public Integer getVehicleBoxType() {
        return vehicleBoxType;
    }

    public void setVehicleBoxType(Integer vehicleBoxType) {
        this.vehicleBoxType = vehicleBoxType;
    }

    public Integer getTailBoard() {
        return tailBoard;
    }

    public void setTailBoard(Integer tailBoard) {
        this.tailBoard = tailBoard;
    }

    public Date getEstimateFinishTime() {
        return estimateFinishTime;
    }

    public void setEstimateFinishTime(Date estimateFinishTime) {
        this.estimateFinishTime = estimateFinishTime;
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

    public Integer getVehicleStopCount() {
        return vehicleStopCount;
    }

    public void setVehicleStopCount(Integer vehicleStopCount) {
        this.vehicleStopCount = vehicleStopCount;
    }

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public String getTruckInfoStr() {
        return truckInfoStr;
    }

    public void setTruckInfoStr(String truckInfoStr) {
        this.truckInfoStr = truckInfoStr;
    }

    public String getParkingAddress() {
        return parkingAddress;
    }

    public void setParkingAddress(String parkingAddress) {
        this.parkingAddress = parkingAddress;
    }

    public Integer getTruckRunType() {
        return truckRunType;
    }

    public void setTruckRunType(Integer truckRunType) {
        this.truckRunType = truckRunType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((plateNumber == null) ? 0 : plateNumber.hashCode());
        result = prime * result + ((truckId == null) ? 0 : truckId.hashCode());
        result = prime * result + ((vehicleId == null) ? 0 : vehicleId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Truck other = (Truck) obj;
        if (plateNumber == null) {
            if (other.plateNumber != null)
                return false;
        } else if (!plateNumber.equals(other.plateNumber))
            return false;
        if (truckId == null) {
            if (other.truckId != null)
                return false;
        } else if (!truckId.equals(other.truckId))
            return false;
        if (vehicleId == null) {
            if (other.vehicleId != null)
                return false;
        } else if (!vehicleId.equals(other.vehicleId))
            return false;
        return true;
    }

}