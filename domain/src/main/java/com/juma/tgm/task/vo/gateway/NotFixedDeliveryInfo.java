package com.juma.tgm.task.vo.gateway;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "非固定点")
public class NotFixedDeliveryInfo implements Serializable {

    @ApiModelProperty(value = "最小配送点")
    private Integer minStops;

    @ApiModelProperty(value = "最大配送点")
    private Integer maxStops;

    @ApiModelProperty(value = "地址信息 比如：成都市锦江区")
    private String addressDetail;

    public Integer getMinStops() {
        return minStops;
    }

    public void setMinStops(Integer minStops) {
        this.minStops = minStops;
    }

    public Integer getMaxStops() {
        return maxStops;
    }

    public void setMaxStops(Integer maxStops) {
        this.maxStops = maxStops;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }
}
