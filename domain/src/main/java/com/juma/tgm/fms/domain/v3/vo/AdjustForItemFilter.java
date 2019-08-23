package com.juma.tgm.fms.domain.v3.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 功能 : 
 *
 * @author : Bruce(刘正航) 11:09 2019-05-16
 */
@ApiModel("调整单详情搜索")
public class AdjustForItemFilter implements Serializable {
    @ApiModelProperty("调整单ID")
    private Integer adjustId;
    @ApiModelProperty("承运商ID")
    private Integer vendorId;
    @ApiModelProperty("运单ID")
    private Integer waybillId;
    @ApiModelProperty("运单ID集合")
    private List<Integer> waybillIds;

    public Integer getAdjustId() {
        return adjustId;
    }

    public void setAdjustId(final Integer adjustId) {
        this.adjustId = adjustId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(final Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public List<Integer> getWaybillIds() {
        return waybillIds;
    }

    public void setWaybillIds(List<Integer> waybillIds) {
        this.waybillIds = waybillIds;
    }
}
