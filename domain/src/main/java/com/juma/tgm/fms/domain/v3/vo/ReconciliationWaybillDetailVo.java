package com.juma.tgm.fms.domain.v3.vo;

import java.io.Serializable;

import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: ReconciliationWaybillDetailVo
 * @Description:
 * @author: liang
 * @date: 2018-06-06 11:30
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */

@ApiModel("对账单申请运单明细")
public class ReconciliationWaybillDetailVo implements Serializable {

    private Waybill waybill;

    private TruckRequire require;
    @ApiModelProperty(value = "是否有改价申请：true、有；false、无")
    private boolean hasAdjustApply;

    public Waybill getWaybill() {
        return waybill;
    }

    public void setWaybill(Waybill waybill) {
        this.waybill = waybill;
    }

    public TruckRequire getRequire() {
        return require;
    }

    public void setRequire(TruckRequire require) {
        this.require = require;
    }

    public boolean isHasAdjustApply() {
        return hasAdjustApply;
    }

    public void setHasAdjustApply(boolean hasAdjustApply) {
        this.hasAdjustApply = hasAdjustApply;
    }
}
