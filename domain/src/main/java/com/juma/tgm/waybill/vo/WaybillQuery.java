package com.juma.tgm.waybill.vo;

import com.juma.tgm.project.vo.v2.ProjectVo;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName WaybillQuery
 * @Description
 * @Author weilibin
 * @Date 2019-06-12 16:07
 * @Version 1.0.0
 */

public class WaybillQuery implements Serializable {

    // 运单信息
    private Waybill waybill;
    // 取货地信息
    private List<WaybillDeliveryAddress> listWaybillDeliveryAddress;
    // 配送地信息
    private List<WaybillReceiveAddress> listWaybillReceiveAddress;
    // 项目信息
    private ProjectVo projectVo;
    // 用车要求
    private TruckRequire truckRequire;

    public Waybill getWaybill() {
        return waybill;
    }

    public void setWaybill(Waybill waybill) {
        this.waybill = waybill;
    }

    public List<WaybillDeliveryAddress> getListWaybillDeliveryAddress() {
        return listWaybillDeliveryAddress;
    }

    public void setListWaybillDeliveryAddress(
        List<WaybillDeliveryAddress> listWaybillDeliveryAddress) {
        this.listWaybillDeliveryAddress = listWaybillDeliveryAddress;
    }

    public List<WaybillReceiveAddress> getListWaybillReceiveAddress() {
        return listWaybillReceiveAddress;
    }

    public void setListWaybillReceiveAddress(
        List<WaybillReceiveAddress> listWaybillReceiveAddress) {
        this.listWaybillReceiveAddress = listWaybillReceiveAddress;
    }

    public ProjectVo getProjectVo() {
        return projectVo;
    }

    public void setProjectVo(ProjectVo projectVo) {
        this.projectVo = projectVo;
    }

    public TruckRequire getTruckRequire() {
        return truckRequire;
    }

    public void setTruckRequire(TruckRequire truckRequire) {
        this.truckRequire = truckRequire;
    }
}
