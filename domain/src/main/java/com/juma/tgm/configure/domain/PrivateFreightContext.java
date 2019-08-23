package com.juma.tgm.configure.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PrivateFreightContext implements Serializable {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3454411302907732126L;

    private BigDecimal km;//公里
    
    private BigDecimal volumn;//体积
    
    private BigDecimal weight;//重量
    
    private BigDecimal pointNum;//配送点
    
    private BigDecimal tollsFee;//高速费
    
    private BigDecimal collectionPayment;//代收货款
    
    private BigDecimal unloadingMin;//卸货分钟
    
    private BigDecimal shipmentMin;//装货分钟
    
    private BigDecimal dayWeight;//天数权重
    
    private String functionIds;//AdditionalFunction 中的条件
    
    private Map<String,Object> filters = new HashMap<String,Object>();

    public BigDecimal getKm() {
        return km;
    }

    public void setKm(BigDecimal km) {
        this.km = km;
    }

    public BigDecimal getVolumn() {
        return volumn;
    }

    public void setVolumn(BigDecimal volumn) {
        this.volumn = volumn;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getUnloadingMin() {
        return unloadingMin;
    }

    public void setUnloadingMin(BigDecimal unloadingMin) {
        this.unloadingMin = unloadingMin;
    }

    public BigDecimal getShipmentMin() {
        return shipmentMin;
    }

    public void setShipmentMin(BigDecimal shipmentMin) {
        this.shipmentMin = shipmentMin;
    }

    public BigDecimal getPointNum() {
        return pointNum;
    }

    public void setPointNum(BigDecimal pointNum) {
        this.pointNum = pointNum;
    }

    public BigDecimal getDayWeight() {
        return dayWeight;
    }

    public void setDayWeight(BigDecimal dayWeight) {
        this.dayWeight = dayWeight;
    }

    public Map<String, Object> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, Object> filters) {
        this.filters = filters;
    }

    public String getFunctionIds() {
        return functionIds;
    }

    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }

    public BigDecimal getTollsFee() {
        return tollsFee;
    }

    public void setTollsFee(BigDecimal tollsFee) {
        this.tollsFee = tollsFee;
    }

    public BigDecimal getCollectionPayment() {
        return collectionPayment;
    }

    public void setCollectionPayment(BigDecimal collectionPayment) {
        this.collectionPayment = collectionPayment;
    }

    @Override
    public String toString() {
        return "PrivateFreightContext [km=" + km + ", volumn=" + volumn + ", weight=" + weight + ", pointNum="
                + pointNum + ", tollsFee=" + tollsFee + ", collectionPayment=" + collectionPayment + ", unloadingMin="
                + unloadingMin + ", shipmentMin=" + shipmentMin + ", dayWeight=" + dayWeight + ", functionIds="
                + functionIds + "]";
    }

    

}
