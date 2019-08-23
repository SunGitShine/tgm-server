package com.juma.tgm.task.vo.gateway;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "固定点")
public class FixedDeliveryInfo implements Serializable {

    @ApiModelProperty(value = "地址名称")
    private String addressName;

    @ApiModelProperty(value = "地址信息")
    private String addressDetail;

    @ApiModelProperty(value = "地址坐标")
    private String coordinates;

    @ApiModelProperty(value = "联系人")
    private String linkMan;

    @ApiModelProperty(value = "联系电话")
    private String linkManTel;

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getLinkManTel() {
        return linkManTel;
    }

    public void setLinkManTel(String linkManTel) {
        this.linkManTel = linkManTel;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}
