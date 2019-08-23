package com.juma.tgm.truck.domain;

import java.math.BigDecimal;

import com.juma.tgm.base.domain.BaseDomain;

/**
 * additional_function_freight - 附加功能运费
 * 
 * @author 2016-05-09
 * @version 1.0
 */
public class AdditionalFunctionFreight extends BaseDomain {

    private static final long serialVersionUID = 8598366672128152375L;
    private Integer additionalFunctionFreightId;
    private Integer additionalFunctionId;
    private BigDecimal freightPerWaybill;
    private BigDecimal lowestFreight;
    private Integer lowestMileage;
    private BigDecimal beyondUnitPrice;
    private BigDecimal highestFreight;
    private String note;
    private Integer freightId;

    public Integer getAdditionalFunctionId() {
        return additionalFunctionId;
    }

    public void setAdditionalFunctionId(Integer additionalFunctionId) {
        this.additionalFunctionId = additionalFunctionId;
    }

    public BigDecimal getFreightPerWaybill() {
        return freightPerWaybill;
    }

    public void setFreightPerWaybill(BigDecimal freightPerWaybill) {
        this.freightPerWaybill = freightPerWaybill;
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

    public Integer getAdditionalFunctionFreightId() {
        return additionalFunctionFreightId;
    }

    public void setAdditionalFunctionFreightId(Integer additionalFunctionFreightId) {
        this.additionalFunctionFreightId = additionalFunctionFreightId;
    }

    public Integer getFreightId() {
        return freightId;
    }

    public void setFreightId(Integer freightId) {
        this.freightId = freightId;
    }

}