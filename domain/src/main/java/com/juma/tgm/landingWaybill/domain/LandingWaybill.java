package com.juma.tgm.landingWaybill.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName LandingWaybillBo.java
 * @Description 落地配派车页面返回BEAN
 * @author Libin.Wei
 * @Date 2017年11月21日 下午4:21:11
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class LandingWaybill implements Serializable {

    private static final long serialVersionUID = -4417434782144117160L;
    // 运单ID
    private Integer waybillId;
    // 运单号
    private String waybillNo;
    // 是否入城
    private boolean isEnterCity;
    // 是否冷链
    private boolean isColdChainCar;
    // 取货地坐标
    private String receiveAddressCoordinate;
    // 配送地坐标
    private List<DeliveryAddress> deliveryAddressCoordinateList  = new ArrayList<DeliveryAddress>();
    // 客户名称
    private String customerName;
    // 货物信息
    private String goodsInfo;
    // 用车时间
    private String planDeliveryDate;
    // 取货地地址
    private String receiveDetailAddress;
    // 配送地地址
    private String deliveryDetailAddress;
    // 逻辑区域
    private String logicAreaCode;
    // 是否预约单：true、预约单；false、临时单
    private boolean isAppointmentWaybill;
    // 运单取货城市
    private String regionCode;
    
    private Integer businessBranch;
    
    private Integer vehicleBoxType;
    
    private Integer truckTypeId;

    /**
     * 冗余：业务区域中文名
     */
    private String areaName;

    /**
     * 车型
     */
    private String truckTypeName;

    /**
     * 重量
     */
    private String goodsWeight;

    /**
     * 计划配送时间
     */
    private Date planDeliveryTime;

    /**
     * 经济人预计完成时间
     */
    private Date cmEstimateFinishTime;

    /**
     * 运单的业务区域的逻辑业务区域名称
     */
    private String logicAreaCodeName;

    public String getLogicAreaCodeName() {
        return logicAreaCodeName;
    }

    public void setLogicAreaCodeName(String logicAreaCodeName) {
        this.logicAreaCodeName = logicAreaCodeName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
    }

    public String getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(String goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public Date getPlanDeliveryTime() {
        return planDeliveryTime;
    }

    public void setPlanDeliveryTime(Date planDeliveryTime) {
        this.planDeliveryTime = planDeliveryTime;
    }

    public Date getCmEstimateFinishTime() {
        return cmEstimateFinishTime;
    }

    public void setCmEstimateFinishTime(Date cmEstimateFinishTime) {
        this.cmEstimateFinishTime = cmEstimateFinishTime;
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

    public boolean isEnterCity() {
        return isEnterCity;
    }

    public void setEnterCity(boolean isEnterCity) {
        this.isEnterCity = isEnterCity;
    }

    public boolean isColdChainCar() {
        return isColdChainCar;
    }

    public void setColdChainCar(boolean isColdChainCar) {
        this.isColdChainCar = isColdChainCar;
    }

    public List<DeliveryAddress> getDeliveryAddressCoordinateList() {
        return deliveryAddressCoordinateList;
    }

    public void setDeliveryAddressCoordinateList(List<DeliveryAddress> deliveryAddressCoordinateList) {
        this.deliveryAddressCoordinateList = deliveryAddressCoordinateList;
    }

    public String getReceiveAddressCoordinate() {
        return receiveAddressCoordinate;
    }

    public void setReceiveAddressCoordinate(String receiveAddressCoordinate) {
        this.receiveAddressCoordinate = receiveAddressCoordinate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public String getPlanDeliveryDate() {
        return planDeliveryDate;
    }

    public void setPlanDeliveryDate(String planDeliveryDate) {
        this.planDeliveryDate = planDeliveryDate;
    }

    public String getDeliveryDetailAddress() {
        return deliveryDetailAddress;
    }

    public void setDeliveryDetailAddress(String deliveryDetailAddress) {
        this.deliveryDetailAddress = deliveryDetailAddress;
    }

    public String getReceiveDetailAddress() {
        return receiveDetailAddress;
    }

    public void setReceiveDetailAddress(String receiveDetailAddress) {
        this.receiveDetailAddress = receiveDetailAddress;
    }

    public String getLogicAreaCode() {
        return logicAreaCode;
    }

    public void setLogicAreaCode(String logicAreaCode) {
        this.logicAreaCode = logicAreaCode;
    }

    public boolean isAppointmentWaybill() {
        return isAppointmentWaybill;
    }

    public void setAppointmentWaybill(boolean isAppointmentWaybill) {
        this.isAppointmentWaybill = isAppointmentWaybill;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public Integer getBusinessBranch() {
        return businessBranch;
    }

    public void setBusinessBranch(Integer businessBranch) {
        this.businessBranch = businessBranch;
    }

    public Integer getVehicleBoxType() {
        return vehicleBoxType;
    }

    public void setVehicleBoxType(Integer vehicleBoxType) {
        this.vehicleBoxType = vehicleBoxType;
    }

    public Integer getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(Integer truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

}
