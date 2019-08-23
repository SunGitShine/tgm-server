package com.juma.tgm.task.vo.gateway;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "仓库")
public class Depot implements Serializable {

    @ApiModelProperty(value = "仓库id")
    private Integer depotId;

    @ApiModelProperty(value = "仓库名字")
    private String depotName;

    @ApiModelProperty(value = "仓库地址")
    private String depotAddress;

    @ApiModelProperty(value = "仓库坐标")
    private String depotCoordinates;

    @ApiModelProperty(value = "联系人")
    private String linkMan;

    @ApiModelProperty(value = "联系电话")
    private String linkManTel;

    public Integer getDepotId() {
        return depotId;
    }

    public void setDepotId(Integer depotId) {
        this.depotId = depotId;
    }

    public String getDepotName() {
        return depotName;
    }

    public void setDepotName(String depotName) {
        this.depotName = depotName;
    }

    public String getDepotAddress() {
        return depotAddress;
    }

    public void setDepotAddress(String depotAddress) {
        this.depotAddress = depotAddress;
    }

    public String getDepotCoordinates() {
        return depotCoordinates;
    }

    public void setDepotCoordinates(String depotCoordinates) {
        this.depotCoordinates = depotCoordinates;
    }

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
}
