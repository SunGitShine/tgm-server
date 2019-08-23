package com.juma.tgm.waybill.domain.vo;

import com.juma.tgm.waybill.domain.*;
import com.juma.tgm.waybill.domain.transformbill.TransformBillMark;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: ScatteredWaybillCreateVo
 * @Description:
 * @author: liang
 * @date: 2017-11-13 17:31
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class ScatteredWaybillCreateVo implements Serializable, TransformBillMark {

    private Waybill waybill;

    private TruckRequire truckRequire;

    /**
     * 取货地
     */
    private List<WaybillDeliveryAddress> srcAddress;

    /**
     * 配送地
     */
    private List<WaybillReceiveAddress> destAddress;

    /**
     * 运单附加数据
     */
    private WaybillParam waybillParam;
    /**
     * 订单批量创建数量
     */
    private Integer createBatchAmount;

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

    public WaybillParam getWaybillParam() {
        return waybillParam;
    }

    public void setWaybillParam(WaybillParam waybillParam) {
        this.waybillParam = waybillParam;
    }

    public WaybillCarrierVo getWaybillCarrierVo() {
        return waybillCarrierVo;
    }

    public void setWaybillCarrierVo(WaybillCarrierVo waybillCarrierVo) {
        this.waybillCarrierVo = waybillCarrierVo;
    }

    public Integer getCreateBatchAmount() {
        return createBatchAmount;
    }

    public void setCreateBatchAmount(Integer createBatchAmount) {
        this.createBatchAmount = createBatchAmount;
    }
}
