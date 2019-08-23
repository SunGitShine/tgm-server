package com.juma.tgm.waybill.external;

import com.juma.tgm.waybill.domain.WaybillOperateTrack;
import java.io.Serializable;
import java.util.Date;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName WaybillOperateTrackExternalVo
 * @Description TODO
 * @Author weilibin
 * @Date 2019-08-07 16:20
 * @Version 1.0.0
 */

public class WaybillOperateTrackExternalVo implements Serializable {

    // 主键
    private Integer trackId;
    // 运单ID
    private Integer waybillId;
    // 操作类型
    private Integer operateType;
    // 操作杨应用
    private Integer operateApplication;
    // 操作地址
    private String operateAddress;
    // 操作时位置坐标
    private String operateAddressCoordinates;
    // 实际地址（运单对用的地址坐标，例如取货地）
    private String actualAddress;
    // 实际坐标（运单对用的地址坐标，例如取货地）
    private String actualAddressCoordinates;
    // 操作地与实际地的距离（米）
    private Integer distance;
    // 创建时间
    private Date createTime;
    // 创建人
    private Integer createUserId;
    // 数据来源
    private Integer dataSource;
    // 设备号
    private String deviceNo;
    // 设备类型
    private Integer deviceType;
    // 电子围栏设备号
    private String fenceDeviceNo;
    // 电子围栏设备类型
    private Integer fenceDeviceType;
    // 备注（json数据）
    private String remark;
    // 记录时间
    private Date declareTime;
    // 车牌号
    private String plateNumber;
    // 司机ID
    private Integer driverId;

    public WaybillOperateTrackExternalVo() {
    }

    public WaybillOperateTrackExternalVo(WaybillOperateTrack waybillOperateTrack) {
        if (null == waybillOperateTrack) {
            return;
        }

        BeanUtils.copyProperties(waybillOperateTrack, this);
    }

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
        this.operateAddress = operateAddress;
    }

    public String getOperateAddressCoordinates() {
        return operateAddressCoordinates;
    }

    public void setOperateAddressCoordinates(String operateAddressCoordinates) {
        this.operateAddressCoordinates = operateAddressCoordinates;
    }

    public String getActualAddress() {
        return actualAddress;
    }

    public void setActualAddress(String actualAddress) {
        this.actualAddress = actualAddress;
    }

    public String getActualAddressCoordinates() {
        return actualAddressCoordinates;
    }

    public void setActualAddressCoordinates(String actualAddressCoordinates) {
        this.actualAddressCoordinates = actualAddressCoordinates;
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
        this.deviceNo = deviceNo;
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
        this.fenceDeviceNo = fenceDeviceNo;
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
        this.remark = remark;
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
        this.plateNumber = plateNumber;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }
}
