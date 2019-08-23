package com.juma.tgm.waybill.domain.vo;

import com.juma.tgm.waybill.domain.TruckRequire;

import java.io.Serializable;
import java.util.List;

/**
 * 功能 :
 *
 * @author : Bruce(刘正航) 18:31 2019-06-20
 */
public class TruckRequireFilter extends TruckRequire implements Serializable {

    private List<Integer> waybillIds;

    public List<Integer> getWaybillIds() {
        return waybillIds;
    }

    public void setWaybillIds(List<Integer> waybillIds) {
        this.waybillIds = waybillIds;
    }
}
