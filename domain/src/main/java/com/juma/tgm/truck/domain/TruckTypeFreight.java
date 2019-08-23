package com.juma.tgm.truck.domain;

import java.math.BigDecimal;

import com.juma.tgm.base.domain.BaseDomain;

/**
 * truck_type_freight - 车型运费
 * 
 * @author 2016-05-09
 * @version 1.0
 */
public class TruckTypeFreight extends BaseDomain {

    private static final long serialVersionUID = -531008943241296617L;
    private Integer freightId;
    /**
     * 车型id
     */
    private Integer truckTypeId;

    /**
     * 时间成本基础价 元/天
     */
    private BigDecimal pricePerDay;

    /**
     * 起步价
     */
    private BigDecimal lowestFreight;
    /**
     * 起步里程
     */
    private Integer lowestMileage;
    /**
     * 里程单价
     */
    private BigDecimal beyondUnitPrice;
    /**
     * 配送点单价
     */
    private BigDecimal distributionPointPrice;
    /**
     *
     */
    private BigDecimal highestFreight;

    private String note;

    /**
     * 地区id
     */
    private Integer cityManageId;

    // 冗余
    private String truckTypeName;
    private String cityManageName;

    public Integer getFreightId() {
        return freightId;
    }

    public void setFreightId(Integer freightId) {
        this.freightId = freightId;
    }

    public Integer getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(Integer truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public BigDecimal getLowestFreight() {
        return lowestFreight;
    }

    public void setLowestFreight(BigDecimal lowestFreight) {
        this.lowestFreight = lowestFreight;
    }

    public Integer getLowestMileage() {
        return lowestMileage;
    }

    public void setLowestMileage(Integer lowestMileage) {
        this.lowestMileage = lowestMileage;
    }

    public BigDecimal getBeyondUnitPrice() {
        return beyondUnitPrice;
    }

    public BigDecimal getDistributionPointPrice() {
        return distributionPointPrice;
    }

    public void setDistributionPointPrice(BigDecimal distributionPointPrice) {
        this.distributionPointPrice = distributionPointPrice;
    }

    public void setBeyondUnitPrice(BigDecimal beyondUnitPrice) {
        this.beyondUnitPrice = beyondUnitPrice;
    }

    public BigDecimal getHighestFreight() {
        return highestFreight;
    }

    public void setHighestFreight(BigDecimal highestFreight) {
        this.highestFreight = highestFreight;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getCityManageId() {
        return cityManageId;
    }

    public void setCityManageId(Integer cityManageId) {
        this.cityManageId = cityManageId;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
    }

    public String getCityManageName() {
        return cityManageName;
    }

    public void setCityManageName(String cityManageName) {
        this.cityManageName = cityManageName;
    }

    @Override
    public String toString() {
        return "TruckTypeFreight [freightId=" + freightId + ", truckTypeId=" + truckTypeId + ", pricePerDay="
                + pricePerDay + ", lowestFreight=" + lowestFreight + ", lowestMileage=" + lowestMileage
                + ", beyondUnitPrice=" + beyondUnitPrice + ", distributionPointPrice=" + distributionPointPrice
                + ", highestFreight=" + highestFreight + ", note=" + note + ", cityManageId=" + cityManageId + "]";
    }

}