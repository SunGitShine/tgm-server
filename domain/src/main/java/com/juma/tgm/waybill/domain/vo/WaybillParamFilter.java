package com.juma.tgm.waybill.domain.vo;

import com.juma.tgm.waybill.domain.WaybillParam;

import java.io.Serializable;
import java.util.List;

/**
 * 功能 :
 * @author : Bruce(刘正航) 16:22 2019-07-29
 */
public class WaybillParamFilter extends WaybillParam implements Serializable {

    private List<Integer> waybillIds;

    public List<Integer> getWaybillIds() {
        return waybillIds;
    }

    public void setWaybillIds(List<Integer> waybillIds) {
        this.waybillIds = waybillIds;
    }
}
