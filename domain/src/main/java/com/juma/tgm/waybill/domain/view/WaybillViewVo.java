package com.juma.tgm.waybill.domain.view;

import com.juma.tgm.waybill.domain.Waybill;

/**
 * @ClassName: WaybillViewVo
 * @Description:
 * @author: liang
 * @date: 2018-01-30 17:28
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class WaybillViewVo extends Waybill {

    private Integer truckRequireId;

    private Integer truckTypeId;

    private String additionalFunctionIds;

    public Integer getTruckRequireId() {
        return truckRequireId;
    }

    public void setTruckRequireId(Integer truckRequireId) {
        this.truckRequireId = truckRequireId;
    }

    public Integer getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(Integer truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public String getAdditionalFunctionIds() {
        return additionalFunctionIds;
    }

    public void setAdditionalFunctionIds(String additionalFunctionIds) {
        this.additionalFunctionIds = additionalFunctionIds;
    }
}
