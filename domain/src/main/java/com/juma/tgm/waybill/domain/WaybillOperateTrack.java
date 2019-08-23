package com.juma.tgm.waybill.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;



@ApiModel("运单操作轨迹")
public class WaybillOperateTrack implements Serializable {
    @ApiModelProperty("")
    private Integer trackId;

    @ApiModelProperty("运单ID")
    private Integer waybillId;

    @ApiModelProperty("操作类型")
    private Integer operateType;

    @ApiModelProperty("操作杨应用")
    private Integer operateApplication;

    @ApiModelProperty("操作地址")
    private String operateAddress;

    @ApiModelProperty("操作时位置坐标")
    private String operateAddressCoordinates;

    @ApiModelProperty("实际地址")
    private String actualAddress;

    @ApiModelProperty("实际坐标")
    private String actualAddressCoordinates;

    @ApiModelProperty("操作地与实际地的距离（米）")
    private Integer distance;

    @ApiModelProperty("")
    private Date createTime;

    @ApiModelProperty("")
    private Integer createUserId;

    @ApiModelProperty("数据来源")
    private Integer dataSource;

    @ApiModelProperty("设备号")
    private String deviceNo;

    @ApiModelProperty("设备类型")
    private Integer deviceType;

    @ApiModelProperty("电子围栏设备号")
    private String fenceDeviceNo;

    @ApiModelProperty("电子围栏设备类型")
    private Integer fenceDeviceType;

    @ApiModelProperty("备注（json数据）")
    private String remark;

    @ApiModelProperty("记录时间")
    private Date declareTime;

    @ApiModelProperty("车牌号")
    private String plateNumber;

    @ApiModelProperty("司机ID")
    private Integer driverId;

    private static final long serialVersionUID = 1L;

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public Integer getOperateApplication() {
        return operateApplication;
    }

    public void setOperateApplication(Integer operateApplication) {
        this.operateApplication = operateApplication;
    }

    public String getOperateAddress() {
        return operateAddress;
    }

    public void setOperateAddress(String operateAddress) {
        this.operateAddress = operateAddress == null ? null : operateAddress.trim();
    }

    public String getOperateAddressCoordinates() {
        return operateAddressCoordinates;
    }

    public void setOperateAddressCoordinates(String operateAddressCoordinates) {
        this.operateAddressCoordinates = operateAddressCoordinates == null ? null : operateAddressCoordinates.trim();
    }

    public String getActualAddress() {
        return actualAddress;
    }

    public void setActualAddress(String actualAddress) {
        this.actualAddress = actualAddress == null ? null : actualAddress.trim();
    }

    public String getActualAddressCoordinates() {
        return actualAddressCoordinates;
    }

    public void setActualAddressCoordinates(String actualAddressCoordinates) {
        this.actualAddressCoordinates = actualAddressCoordinates == null ? null : actualAddressCoordinates.trim();
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
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

    public Integer getDataSource() {
        return dataSource;
    }

    public void setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo == null ? null : deviceNo.trim();
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getFenceDeviceNo() {
        return fenceDeviceNo;
    }

    public void setFenceDeviceNo(String fenceDeviceNo) {
        this.fenceDeviceNo = fenceDeviceNo == null ? null : fenceDeviceNo.trim();
    }

    public Integer getFenceDeviceType() {
        return fenceDeviceType;
    }

    public void setFenceDeviceType(Integer fenceDeviceType) {
        this.fenceDeviceType = fenceDeviceType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getDeclareTime() {
        return declareTime;
    }

    public void setDeclareTime(Date declareTime) {
        this.declareTime = declareTime;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber == null ? null : plateNumber.trim();
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
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
        WaybillOperateTrack other = (WaybillOperateTrack) that;
        return (this.getTrackId() == null ? other.getTrackId() == null : this.getTrackId().equals(other.getTrackId()))
            && (this.getWaybillId() == null ? other.getWaybillId() == null : this.getWaybillId().equals(other.getWaybillId()))
            && (this.getOperateType() == null ? other.getOperateType() == null : this.getOperateType().equals(other.getOperateType()))
            && (this.getOperateApplication() == null ? other.getOperateApplication() == null : this.getOperateApplication().equals(other.getOperateApplication()))
            && (this.getOperateAddress() == null ? other.getOperateAddress() == null : this.getOperateAddress().equals(other.getOperateAddress()))
            && (this.getOperateAddressCoordinates() == null ? other.getOperateAddressCoordinates() == null : this.getOperateAddressCoordinates().equals(other.getOperateAddressCoordinates()))
            && (this.getActualAddress() == null ? other.getActualAddress() == null : this.getActualAddress().equals(other.getActualAddress()))
            && (this.getActualAddressCoordinates() == null ? other.getActualAddressCoordinates() == null : this.getActualAddressCoordinates().equals(other.getActualAddressCoordinates()))
            && (this.getDistance() == null ? other.getDistance() == null : this.getDistance().equals(other.getDistance()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getDataSource() == null ? other.getDataSource() == null : this.getDataSource().equals(other.getDataSource()))
            && (this.getDeviceNo() == null ? other.getDeviceNo() == null : this.getDeviceNo().equals(other.getDeviceNo()))
            && (this.getDeviceType() == null ? other.getDeviceType() == null : this.getDeviceType().equals(other.getDeviceType()))
            && (this.getFenceDeviceNo() == null ? other.getFenceDeviceNo() == null : this.getFenceDeviceNo().equals(other.getFenceDeviceNo()))
            && (this.getFenceDeviceType() == null ? other.getFenceDeviceType() == null : this.getFenceDeviceType().equals(other.getFenceDeviceType()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getDeclareTime() == null ? other.getDeclareTime() == null : this.getDeclareTime().equals(other.getDeclareTime()))
            && (this.getPlateNumber() == null ? other.getPlateNumber() == null : this.getPlateNumber().equals(other.getPlateNumber()))
            && (this.getDriverId() == null ? other.getDriverId() == null : this.getDriverId().equals(other.getDriverId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTrackId() == null) ? 0 : getTrackId().hashCode());
        result = prime * result + ((getWaybillId() == null) ? 0 : getWaybillId().hashCode());
        result = prime * result + ((getOperateType() == null) ? 0 : getOperateType().hashCode());
        result = prime * result + ((getOperateApplication() == null) ? 0 : getOperateApplication().hashCode());
        result = prime * result + ((getOperateAddress() == null) ? 0 : getOperateAddress().hashCode());
        result = prime * result + ((getOperateAddressCoordinates() == null) ? 0 : getOperateAddressCoordinates().hashCode());
        result = prime * result + ((getActualAddress() == null) ? 0 : getActualAddress().hashCode());
        result = prime * result + ((getActualAddressCoordinates() == null) ? 0 : getActualAddressCoordinates().hashCode());
        result = prime * result + ((getDistance() == null) ? 0 : getDistance().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getDataSource() == null) ? 0 : getDataSource().hashCode());
        result = prime * result + ((getDeviceNo() == null) ? 0 : getDeviceNo().hashCode());
        result = prime * result + ((getDeviceType() == null) ? 0 : getDeviceType().hashCode());
        result = prime * result + ((getFenceDeviceNo() == null) ? 0 : getFenceDeviceNo().hashCode());
        result = prime * result + ((getFenceDeviceType() == null) ? 0 : getFenceDeviceType().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getDeclareTime() == null) ? 0 : getDeclareTime().hashCode());
        result = prime * result + ((getPlateNumber() == null) ? 0 : getPlateNumber().hashCode());
        result = prime * result + ((getDriverId() == null) ? 0 : getDriverId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", trackId=").append(trackId);
        sb.append(", waybillId=").append(waybillId);
        sb.append(", operateType=").append(operateType);
        sb.append(", operateApplication=").append(operateApplication);
        sb.append(", operateAddress=").append(operateAddress);
        sb.append(", operateAddressCoordinates=").append(operateAddressCoordinates);
        sb.append(", actualAddress=").append(actualAddress);
        sb.append(", actualAddressCoordinates=").append(actualAddressCoordinates);
        sb.append(", distance=").append(distance);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", dataSource=").append(dataSource);
        sb.append(", deviceNo=").append(deviceNo);
        sb.append(", deviceType=").append(deviceType);
        sb.append(", fenceDeviceNo=").append(fenceDeviceNo);
        sb.append(", fenceDeviceType=").append(fenceDeviceType);
        sb.append(", remark=").append(remark);
        sb.append(", declareTime=").append(declareTime);
        sb.append(", plateNumber=").append(plateNumber);
        sb.append(", driverId=").append(driverId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        trackId,
        waybillId,
        operateType,
        operateApplication,
        operateAddress,
        operateAddressCoordinates,
        actualAddress,
        actualAddressCoordinates,
        distance,
        createTime,
        createUserId,
        dataSource,
        deviceNo,
        deviceType,
        fenceDeviceNo,
        fenceDeviceType,
        remark,
        declareTime,
        plateNumber,
        driverId;

        public String asc() {
            return column() + " ASC";
        }

        public String desc() {
            return column() + " DESC";
        }

        private String column() {
            StringBuilder buffer = new StringBuilder();
            char[] charArray = this.name().toCharArray();
            for(char ch : charArray) {
                if(Character.isUpperCase(ch)){
                    buffer.append("_");
                    buffer.append(Character.toLowerCase(ch));
                } else {
                    buffer.append(ch);
                }
            }
            return buffer.toString();
        }
    }
}