package com.juma.tgm.fms.domain.v2.vo;

import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillParam;

import java.io.Serializable;

/**
 * @ClassName: ReconciliationWaybillDetailVo
 * @Description:
 * @author: liang
 * @date: 2018-06-06 11:30
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class ReconciliationWaybillDetailVo implements Serializable {

    private Waybill waybill;

    private WaybillParam waybillParam;

    private TruckRequire require;

    public Waybill getWaybill() {
        return waybill;
    }

    public void setWaybill(Waybill waybill) {
        this.waybill = waybill;
    }

    public WaybillParam getWaybillParam() {
        return waybillParam;
    }

    public void setWaybillParam(WaybillParam waybillParam) {
        this.waybillParam = waybillParam;
    }

    public TruckRequire getRequire() {
        return require;
    }

    public void setRequire(TruckRequire require) {
        this.require = require;
    }
}
