package com.juma.tgm.waybill.domain.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 功能 : 
 *
 * @author : Bruce(刘正航) 18:35 2019-05-22
 */
public class WaybillAmountFilter implements Serializable {
    private Integer waybillId;
    private List<Integer> waybillIds;
    @ApiModelProperty("运费状态:0未确认,1已确认,2超时未确认")
    private Integer amountStatus;

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(final Integer waybillId) {
        this.waybillId = waybillId;
    }

    public List<Integer> getWaybillIds() {
        return waybillIds;
    }

    public void setWaybillIds(final List<Integer> waybillIds) {
        this.waybillIds = waybillIds;
    }

    public Integer getAmountStatus() {
        return amountStatus;
    }

    public void setAmountStatus(Integer amountStatus) {
        this.amountStatus = amountStatus;
    }
}
