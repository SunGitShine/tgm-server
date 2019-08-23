package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName:   WaybillMap   
 * @Description: 在途监控
 * @author:      Administrator
 * @date:        2017年4月6日 下午12:34:44  
 *
 * @Copyright:   2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class WaybillMap implements Serializable {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4269422259361458854L;
    //运单Id
    private Integer waybillId;
    //运单编号
    private String waybillNo;
    //车辆ID
    private Integer vehicleId;
    //车牌号
    private String plateNumber;
    //停放区域
    private String parkingRegionCode;
    //停放区域
    private String parkingRegionCodeText;
    //设备号
    private String deviceNo;
    //司机
    private String driverName;
    //状态
    private Integer statusView;
    //状态文本
    private String statusViewText;
    //配送时间
    private Date planDeliveryTime;
    
    //到仓时间
    private Date arriveDepotTime;
    //离仓时间
    private Date leaveDepotTime;
    //手动到仓位置
    private String manualArriveLocation;
    
    //电子围栏到仓时间
    private Date fenceArriveDepotTime;
    //电子围栏离仓时间
    private Date fenceLeaveDepotTime;
    //电子围栏到仓位置
    private String fenceArriveLocation;
    //预估完成时间
    private String estimateFinishTime;
    //结束时间
    private Date finishTime;
    //客户名称
    private String customerName;
    //客户经理
    private String customerManagerName;
    //规划里程
    private Integer estimateDistance;
    //实际里程
    private Integer actualMileage;
    //配送点
    private Integer distributionPointNo;
    //用车要求
    private String truckRequire;
    //最低温度
    private Float requiredMinTemperature;
    //最高温度
    private Float requiredMaxTemperature;
    
    //取货点
    private List<WaybillDeliveryAddress> deliveryAddress = new ArrayList<WaybillDeliveryAddress>();
    //配送点
    private List<WaybillReceiveAddress> receiveAddress = new ArrayList<WaybillReceiveAddress>();

    public String getEstimateFinishTime() {
        return estimateFinishTime;
    }

    public void setEstimateFinishTime(String estimateFinishTime) {
        this.estimateFinishTime = estimateFinishTime;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Date getPlanDeliveryTime() {
        return planDeliveryTime;
    }

    public void setPlanDeliveryTime(Date planDeliveryTime) {
        this.planDeliveryTime = planDeliveryTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getEstimateDistance() {
        return estimateDistance;
    }

    public void setEstimateDistance(Integer estimateDistance) {
        this.estimateDistance = estimateDistance;
    }

    public Integer getActualMileage() {
        return actualMileage;
    }

    public void setActualMileage(Integer actualMileage) {
        this.actualMileage = actualMileage;
    }

    public List<WaybillDeliveryAddress> getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(List<WaybillDeliveryAddress> deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<WaybillReceiveAddress> getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(List<WaybillReceiveAddress> receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getArriveDepotTime() {
        return arriveDepotTime;
    }

    public void setArriveDepotTime(Date arriveDepotTime) {
        this.arriveDepotTime = arriveDepotTime;
    }

    public Date getLeaveDepotTime() {
        return leaveDepotTime;
    }

    public void setLeaveDepotTime(Date leaveDepotTime) {
        this.leaveDepotTime = leaveDepotTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerManagerName() {
        return customerManagerName;
    }

    public void setCustomerManagerName(String customerManagerName) {
        this.customerManagerName = customerManagerName;
    }

    public String getStatusViewText() {
        return statusViewText;
    }

    public void setStatusViewText(String statusViewText) {
        this.statusViewText = statusViewText;
    }

    public void setStatusView(Integer statusView) {
        this.statusView = statusView;
    }

    public Integer getStatusView() {
        return statusView;
    }

    public String getParkingRegionCode() {
        return parkingRegionCode;
    }

    public void setParkingRegionCode(String parkingRegionCode) {
        this.parkingRegionCode = parkingRegionCode;
    }

    public String getParkingRegionCodeText() {
        return parkingRegionCodeText;
    }

    public void setParkingRegionCodeText(String parkingRegionCodeText) {
        this.parkingRegionCodeText = parkingRegionCodeText;
    }

    public Integer getDistributionPointNo() {
        return distributionPointNo;
    }

    public void setDistributionPointNo(Integer distributionPointNo) {
        this.distributionPointNo = distributionPointNo;
    }

    public String getTruckRequire() {
        return truckRequire;
    }

    public void setTruckRequire(String truckRequire) {
        this.truckRequire = truckRequire;
    }

    public Date getFenceArriveDepotTime() {
        return fenceArriveDepotTime;
    }

    public void setFenceArriveDepotTime(Date fenceArriveDepotTime) {
        this.fenceArriveDepotTime = fenceArriveDepotTime;
    }

    public Date getFenceLeaveDepotTime() {
        return fenceLeaveDepotTime;
    }

    public void setFenceLeaveDepotTime(Date fenceLeaveDepotTime) {
        this.fenceLeaveDepotTime = fenceLeaveDepotTime;
    }

    public String getFenceArriveLocation() {
        return fenceArriveLocation;
    }

    public void setFenceArriveLocation(String fenceArriveLocation) {
        this.fenceArriveLocation = fenceArriveLocation;
    }

    public String getManualArriveLocation() {
        return manualArriveLocation;
    }

    public void setManualArriveLocation(String manualArriveLocation) {
        this.manualArriveLocation = manualArriveLocation;
    }

    public Float getRequiredMinTemperature() {
        return requiredMinTemperature;
    }

    public void setRequiredMinTemperature(Float requiredMinTemperature) {
        this.requiredMinTemperature = requiredMinTemperature;
    }

    public Float getRequiredMaxTemperature() {
        return requiredMaxTemperature;
    }

    public void setRequiredMaxTemperature(Float requiredMaxTemperature) {
        this.requiredMaxTemperature = requiredMaxTemperature;
    }

    
    
}
