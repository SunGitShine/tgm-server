package com.juma.tgm.waybill.domain.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.gaode.domain.Position;
import com.juma.tgm.imageUploadManage.domain.ImageUploadManage;
import com.juma.tgm.truck.domain.AdditionalFunction;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.domain.bo.DriverTruckInfoBo;
import com.juma.tgm.waybill.domain.*;

import java.io.Serializable;
import java.util.List;

/**
 * 客户端落地配运单vo
 *
 * @ClassName: ScatteredWaybillVo
 * @Description:
 * @author: liang
 * @date: 2017-11-13 17:10
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class ScatteredWaybillViewVo implements Serializable {

    @JSONField(serialize = false)
    private Waybill waybill;

    @JSONField(serialize = false)
    private Driver driver;

    @JSONField(serialize = false)
    private Truck truck;

    private String truckInfo;

    /**
     * 箱型名称
     */
    private String vehicleBoxTypeName;

    /**
     * 车型名称
     */
    private String truckTypeName;

    /**
     * 车辆司机信息
     */
    private DriverTruckInfoBo driverTruckInfoBo;

    /**
     * 取货地
     */
    @JSONField(serialize = false)
    private List<WaybillDeliveryAddress> srcAddress;

    /**
     * 配送地
     */
    @JSONField(serialize = false)
    private List<WaybillReceiveAddress> destAddress;

    /**
     * 运单轨迹信息
     */
    private Position position;

    /**
     * 运单状态轨迹
     */
    @JSONField(serialize = false)
    private List<WaybillOperateTrack> operateTracks;


    @JSONField(serialize = false)
    private TruckRequire truckRequire;


    /**
     * 当前运单用到的额外功能枚举
     */
    @JSONField(serialize = false)
    private List<AdditionalFunction> additionalFunctions;

    /**
     * 运单附属信息
     */
    private WaybillParam waybillParam;

    /**
     * 运单所属客户信息
     */
    private CustomerInfo customerInfo;

    /**
     * 客服电话
     */
    private String hotline;

    /**
     * 回单信息
     */
    private List<ImageUploadManage> receiptManageList;

    /**
     * 派单等待倒计时
     */
    private String assignWaitingTime;


    public Waybill getWaybill() {
        return waybill;
    }

    public void setWaybill(Waybill waybill) {
        this.waybill = waybill;
    }

    public String getAssignWaitingTime() {
        return assignWaitingTime;
    }

    public void setAssignWaitingTime(String assignWaitingTime) {
        this.assignWaitingTime = assignWaitingTime;
    }

    public List<WaybillDeliveryAddress> getSrcAddress() {
        return srcAddress;
    }

    public void setSrcAddress(List<WaybillDeliveryAddress> srcAddress) {
        this.srcAddress = srcAddress;
    }

    public List<WaybillReceiveAddress> getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(List<WaybillReceiveAddress> destAddress) {
        this.destAddress = destAddress;
    }


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<WaybillOperateTrack> getOperateTracks() {
        return operateTracks;
    }

    public void setOperateTracks(List<WaybillOperateTrack> operateTracks) {
        this.operateTracks = operateTracks;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public String getTruckInfo() {
        return truckInfo;
    }

    public void setTruckInfo(String truckInfo) {
        this.truckInfo = truckInfo;
    }

    public TruckRequire getTruckRequire() {
        return truckRequire;
    }

    public void setTruckRequire(TruckRequire truckRequire) {
        this.truckRequire = truckRequire;
    }

    public List<AdditionalFunction> getAdditionalFunctions() {
        return additionalFunctions;
    }

    public void setAdditionalFunctions(List<AdditionalFunction> additionalFunctions) {
        this.additionalFunctions = additionalFunctions;
    }

    public WaybillParam getWaybillParam() {
        return waybillParam;
    }

    public void setWaybillParam(WaybillParam waybillParam) {
        this.waybillParam = waybillParam;
    }

    public DriverTruckInfoBo getDriverTruckInfoBo() {
        return driverTruckInfoBo;
    }

    public void setDriverTruckInfoBo(DriverTruckInfoBo driverTruckInfoBo) {
        this.driverTruckInfoBo = driverTruckInfoBo;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public List<ImageUploadManage> getReceiptManageList() {
        return receiptManageList;
    }

    public void setReceiptManageList(List<ImageUploadManage> receiptManageList) {
        this.receiptManageList = receiptManageList;
    }

    public String getVehicleBoxTypeName() {
        return vehicleBoxTypeName;
    }

    public void setVehicleBoxTypeName(String vehicleBoxTypeName) {
        this.vehicleBoxTypeName = vehicleBoxTypeName;
    }

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
    }

}
