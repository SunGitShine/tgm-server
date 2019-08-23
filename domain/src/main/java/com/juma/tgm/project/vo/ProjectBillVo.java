package com.juma.tgm.project.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.giants.common.collections.CollectionUtils;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.domain.WaybillReceiveAddressResponse;
import com.juma.tgm.waybill.domain.transformbill.TransformBillMark;
import com.juma.tgm.waybill.domain.vo.WaybillCarrierVo;

/**
 * @ClassName: ProjectBillVo
 * @Description:
 * @author: liang
 * @date: 2017-09-29 14:35
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class ProjectBillVo implements Serializable, TransformBillMark {

    private Waybill waybill;

    private TruckRequire truckRequire;

    private Project project;

    /**
     * 取货地
     */
    private List<WaybillDeliveryAddress> deliveryAddress = new ArrayList<WaybillDeliveryAddress>();

    /**
     *  配送地
     */
    private List<WaybillReceiveAddress> receiveAddress = new ArrayList<WaybillReceiveAddress>();

    /**
     * 配送地（包含货物信息）
     */
    private List<WaybillReceiveAddressResponse> waybillReceiveAddressResponseList;

    //车型-数量
    private Set<TruckValidator> truckValidator;

    /**
     * 指定车辆id列表
     */
    private Set<Integer> fixedTruckIds;

    /**
     * 调度指派用车数量
     */
    private Integer createBatchAmount;

    /**
     * true: 自动建单
     */
    private Boolean autoCreate;

    /**
     * 固定需求自动建单,派车方式
     */
    private Integer receiveWay;


    private WaybillParam waybillParam;

    /**
     * 转承运商vo
     */
    private WaybillCarrierVo waybillCarrierVo;

    public Waybill getWaybill() {
        return waybill;
    }

    public void setWaybill(Waybill waybill) {
        this.waybill = waybill;
    }

    public TruckRequire getTruckRequire() {
        return truckRequire;
    }

    public void setTruckRequire(TruckRequire truckRequire) {
        this.truckRequire = truckRequire;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Set<TruckValidator> getTruckValidator() {
        return truckValidator;
    }

    public void setTruckValidator(Set<TruckValidator> truckValidator) {
        this.truckValidator = truckValidator;
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

    public Boolean getAutoCreate() {
        return autoCreate;
    }

    public void setAutoCreate(Boolean autoCreate) {
        this.autoCreate = autoCreate;
    }

    public static class TruckValidator implements Serializable{

        /**
         * 车型id
         */
        private Integer truckTypeId;
        
        private Integer vehicleBoxLength;
        
        private Integer vehicleBoxType;

        /**
         * 数量
         */
        private Integer amount;

        public Integer getTruckTypeId() {
            return truckTypeId;
        }

        public void setTruckTypeId(Integer truckTypeId) {
            this.truckTypeId = truckTypeId;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TruckValidator that = (TruckValidator) o;
            return Objects.equals(vehicleBoxLength, that.vehicleBoxLength) &&
                Objects.equals(vehicleBoxType, that.vehicleBoxType);
        }

        @Override
        public int hashCode() {

            return Objects.hash(vehicleBoxLength, vehicleBoxType);
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
    }

    public Integer getCreateBatchAmount() {
        return createBatchAmount;
    }

    public void setCreateBatchAmount(Integer createBatchAmount) {
        this.createBatchAmount = createBatchAmount;
    }

    public WaybillParam getWaybillParam() {
        return waybillParam;
    }

    public void setWaybillParam(WaybillParam waybillParam) {
        this.waybillParam = waybillParam;
    }

    public Integer getReceiveWay() {
        return receiveWay;
    }

    public void setReceiveWay(Integer receiveWay) {
        this.receiveWay = receiveWay;
    }

    public Set<Integer> getTruckTypeSet(){
        if(CollectionUtils.isEmpty(this.getTruckValidator())) return null;
        Set<Integer> typeSet = new HashSet<>();

        for(TruckValidator validator : this.getTruckValidator()){
            typeSet.add(validator.getTruckTypeId());
        }

        return typeSet;
    }


    public List<WaybillReceiveAddressResponse> getWaybillReceiveAddressResponseList() {
        return waybillReceiveAddressResponseList;
    }

    public void setWaybillReceiveAddressResponseList(List<WaybillReceiveAddressResponse> waybillReceiveAddressResponseList) {
        this.waybillReceiveAddressResponseList = waybillReceiveAddressResponseList;
    }

    public WaybillCarrierVo getWaybillCarrierVo() {
        return waybillCarrierVo;
    }

    public void setWaybillCarrierVo(WaybillCarrierVo waybillCarrierVo) {
        this.waybillCarrierVo = waybillCarrierVo;
    }


    public Set<Integer> getFixedTruckIds() {
        return fixedTruckIds;
    }

    public void setFixedTruckIds(Set<Integer> fixedTruckIds) {
        this.fixedTruckIds = fixedTruckIds;
    }
}
