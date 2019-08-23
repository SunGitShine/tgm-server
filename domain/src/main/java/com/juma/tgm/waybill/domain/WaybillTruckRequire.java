package com.juma.tgm.waybill.domain;

import java.io.Serializable;

public class WaybillTruckRequire implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8048461474882269475L;

    private String truckClassName;

    private String truckLengthName;

    private String goodsWeight;

    private String goodsType;

    private String goodsVolume;

    private Boolean isEntryLicense = false;
    
    private Integer needWithBody = 3;

    public String getTruckClassName() {
        return truckClassName;
    }

    public void setTruckClassName(String truckClassName) {
        this.truckClassName = truckClassName;
    }

    public String getTruckLengthName() {
        return truckLengthName;
    }

    public void setTruckLengthName(String truckLengthName) {
        this.truckLengthName = truckLengthName;
    }

    public String getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(String goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsVolume() {
        return goodsVolume;
    }

    public void setGoodsVolume(String goodsVolume) {
        this.goodsVolume = goodsVolume;
    }

    public Boolean getIsEntryLicense() {
        return isEntryLicense;
    }

    public void setIsEntryLicense(Boolean isEntryLicense) {
        this.isEntryLicense = isEntryLicense;
    }

    public Integer getNeedWithBody() {
        return needWithBody;
    }

    public void setNeedWithBody(Integer needWithBody) {
        this.needWithBody = needWithBody;
    }


}
