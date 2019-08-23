package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.util.Date;

public class WaybillNoFinish implements Serializable {

    private static final long serialVersionUID = -5572395525511518452L;

    private Integer waybillId;
    
    private String waybillNo;
    
    private String plateNumber;
    
    private Integer truckId;
    
    private String driverName;
    
    private String customerName;
    
    private String goodsType;
    
    private Date planDeliveryTime;
    
    private Date arriveDepotTime;
    
    private Date deliveryTime;
    
    private Integer distrbutionPointNo;
    
    private Integer estimateDistance;
    
    private Date estimateFinishTime;
    
    private Integer driverId;
    private Integer customerId;
    private Integer projectId;
    private Integer customerManagerId;
    private Integer truckCustomerId;

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public Date getPlanDeliveryTime() {
        return planDeliveryTime;
    }

    public void setPlanDeliveryTime(Date planDeliveryTime) {
        this.planDeliveryTime = planDeliveryTime;
    }

    public Date getArriveDepotTime() {
        return arriveDepotTime;
    }

    public void setArriveDepotTime(Date arriveDepotTime) {
        this.arriveDepotTime = arriveDepotTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getDistrbutionPointNo() {
        return distrbutionPointNo;
    }

    public void setDistrbutionPointNo(Integer distrbutionPointNo) {
        this.distrbutionPointNo = distrbutionPointNo;
    }

    public Integer getEstimateDistance() {
        return estimateDistance;
    }

    public void setEstimateDistance(Integer estimateDistance) {
        this.estimateDistance = estimateDistance;
    }

    public Date getEstimateFinishTime() {
        return estimateFinishTime;
    }

    public void setEstimateFinishTime(Date estimateFinishTime) {
        this.estimateFinishTime = estimateFinishTime;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getCustomerManagerId() {
        return customerManagerId;
    }

    public void setCustomerManagerId(Integer customerManagerId) {
        this.customerManagerId = customerManagerId;
    }

    public Integer getTruckCustomerId() {
        return truckCustomerId;
    }

    public void setTruckCustomerId(Integer truckCustomerId) {
        this.truckCustomerId = truckCustomerId;
    }
    
}
