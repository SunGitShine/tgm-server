package com.juma.tgm.waybill.domain;

import com.juma.conf.domain.Region;
import com.juma.tgm.landingWaybill.domain.AtFenceResultVo;
import com.juma.tgm.waybill.domain.drools.PriceProxy;

import java.io.Serializable;
import java.math.BigDecimal;

public class DistanceAndPriceData implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5736941389641486759L;
    // 起始坐标
    private String startCoordinates;
    // 终点坐标
    private String endCoordinates;
    // 距离
    private Integer distance;
    // 预估时间
    private String duration;
    // 详细
    private String calDetail;
    // 价格
    private BigDecimal price;
    // 省份
    private String province;
    // 明细
    private PriceProxy priceProxy;
    // 收费路段预估应收费用
    private BigDecimal tolls;
    // 收费路段预估距离
    private Integer tollDistance;
    // 配送点数
    private Integer siteNo = 0;
    // 取货地城市编码
    private String regionCode;
    //参考价
    private BigDecimal referenceFreight;

    //司机结算价
    private BigDecimal show4DriverFreight;

    //是否需要入城
    private Boolean inCity;

    //是否在业务区域内
    private Boolean inBusinessArea;

    //是否在禁货区域
    private Boolean inForbiddenArea;

    //禁止区域的idx
    private Integer forbiddenAreaIndex;

    //禁货区域类型
    private AtFenceResultVo.ForbiddenType forbiddenType;

    //发货地所属业务区域
    private String waybillAreaCode;

    //行政区域
    private Region region;

    /**
     * 含税价格
     */
    private BigDecimal withTaxPrice;

    /**
     * 不含税价格
     * 不在计价公式内
     */
    private BigDecimal withoutTaxPrice;

    public String getStartCoordinates() {
        return startCoordinates;
    }

    public void setStartCoordinates(String startCoordinates) {
        this.startCoordinates = startCoordinates;
    }

    public String getEndCoordinates() {
        return endCoordinates;
    }

    public void setEndCoordinates(String endCoordinates) {
        this.endCoordinates = endCoordinates;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCalDetail() {
        return calDetail;
    }

    public void setCalDetail(String calDetail) {
        this.calDetail = calDetail;
    }

    public BigDecimal getPrice() {

        if (price != null) {
            return price.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public PriceProxy getPriceProxy() {
        return priceProxy;
    }

    public void setPriceProxy(PriceProxy priceProxy) {
        this.priceProxy = priceProxy;
    }

    public BigDecimal getTolls() {
        return tolls;
    }

    public void setTolls(BigDecimal tolls) {
        this.tolls = tolls;
    }

    public Integer getTollDistance() {
        return tollDistance;
    }

    public void setTollDistance(Integer tollDistance) {
        this.tollDistance = tollDistance;
    }

    public Integer getSiteNo() {
        return siteNo;
    }

    public void setSiteNo(Integer siteNo) {
        this.siteNo = siteNo;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public BigDecimal getReferenceFreight() {
        if (this.getPrice() != null) {
            this.referenceFreight = this.getPrice().multiply(new BigDecimal(1.2)).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return referenceFreight;
    }

    public void setReferenceFreight(BigDecimal referenceFreight) {
        this.referenceFreight = referenceFreight;
    }

    public BigDecimal getShow4DriverFreight() {
        return show4DriverFreight;
    }

    public void setShow4DriverFreight(BigDecimal show4DriverFreight) {
        this.show4DriverFreight = show4DriverFreight;
    }

    public Boolean getInCity() {
        return inCity;
    }

    public void setInCity(Boolean inCity) {
        this.inCity = inCity;
    }

    public Boolean getInBusinessArea() {
        return inBusinessArea;
    }

    public void setInBusinessArea(Boolean inBusinessArea) {
        this.inBusinessArea = inBusinessArea;
    }

    public String getWaybillAreaCode() {
        return waybillAreaCode;
    }

    public void setWaybillAreaCode(String waybillAreaCode) {
        this.waybillAreaCode = waybillAreaCode;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Boolean getInForbiddenArea() {
        return inForbiddenArea;
    }

    public void setInForbiddenArea(Boolean inForbiddenArea) {
        this.inForbiddenArea = inForbiddenArea;
    }

    public AtFenceResultVo.ForbiddenType getForbiddenType() {
        return forbiddenType;
    }

    public void setForbiddenType(AtFenceResultVo.ForbiddenType forbiddenType) {
        this.forbiddenType = forbiddenType;
    }


    public Integer getForbiddenAreaIndex() {
        return forbiddenAreaIndex;
    }

    public void setForbiddenAreaIndex(Integer forbiddenAreaIndex) {
        this.forbiddenAreaIndex = forbiddenAreaIndex;
    }

    public BigDecimal getWithTaxPrice() {
        return withTaxPrice;
    }

    public void setWithTaxPrice(BigDecimal withTaxPrice) {
        this.withTaxPrice = withTaxPrice;
    }

    public BigDecimal getWithoutTaxPrice() {
        return withoutTaxPrice;
    }

    public void setWithoutTaxPrice(BigDecimal withoutTaxPrice) {
        this.withoutTaxPrice = withoutTaxPrice;
    }
}
