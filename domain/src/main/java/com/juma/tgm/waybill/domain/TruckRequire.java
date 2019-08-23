/**
* @Title: WaybillRequire.java
* @Package com.juma.tgm.waybill.domain
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年7月1日 下午5:05:54
* @version V1.0  
 */
package com.juma.tgm.waybill.domain;

import com.juma.tgm.base.domain.BaseDomain;

import java.math.BigDecimal;

/**
 * @Description: 用车要求
 * @author Administrator
 * @date 2016年7月1日 下午5:05:54
 * @version V1.0
 */
public class
TruckRequire extends BaseDomain {

    private static final long serialVersionUID = -3671945910308976395L;

    private Integer truckRequireId;

    private Integer waybillId;

    private Integer truckTypeId;

    private String additionalFunctionIds;

    private String goodsType;

    private String goodsWeight;

    private String goodsVolume;

    private String goodsAmount;

    private Integer isTailBoard;

    private String remark;

    private BigDecimal taxRateValue;

    /**
     * 箱型
     */
    private Integer vehicleBoxType;

    /**
     * 冗余：用车要求字符串
     */
    private String truckRequireStr;
    // TODO
    private Integer taxRateId;

    public int getEntryLicense() {
        if (additionalFunctionIds != null && additionalFunctionIds.contains("9")) {
            return 1;
        }
        return 0;
    }

    public boolean isCollectPayment() {
        if (additionalFunctionIds != null && additionalFunctionIds.contains("8")) {
            return true;
        }
        return false;
    }

    public boolean isNeedBackStorage() {
        if (additionalFunctionIds != null && additionalFunctionIds.contains("7")) {
            return true;
        }
        return false;
    }

    public boolean isReceipt() {
        if (additionalFunctionIds != null && additionalFunctionIds.contains("6")) {
            return true;
        }
        return false;
    }

    public boolean isColdChain() {
        if (additionalFunctionIds != null && additionalFunctionIds.contains("2")) {
            return true;
        }
        return false;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(String goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public String getGoodsVolume() {
        return goodsVolume;
    }

    public void setGoodsVolume(String goodsVolume) {
        this.goodsVolume = goodsVolume;
    }

    public Integer getIsTailBoard() {
        return isTailBoard;
    }

    public void setIsTailBoard(Integer isTailBoard) {
        this.isTailBoard = isTailBoard;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(Integer truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public Integer getTruckRequireId() {
        return truckRequireId;
    }

    public void setTruckRequireId(Integer truckRequireId) {
        this.truckRequireId = truckRequireId;
    }

    public String getAdditionalFunctionIds() {
        return additionalFunctionIds;
    }

    public void setAdditionalFunctionIds(String additionalFunctionIds) {
        this.additionalFunctionIds = additionalFunctionIds;
    }

    public BigDecimal getTaxRateValue() {
        return taxRateValue;
    }

    public void setTaxRateValue(BigDecimal taxRateValue) {
        this.taxRateValue = taxRateValue;
    }

    public String getTruckRequireStr() {
        return truckRequireStr;
    }

    public void setTruckRequireStr(String truckRequireStr) {
        this.truckRequireStr = truckRequireStr;
    }

    public String getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(String goodsAmount) {
        this.goodsAmount = goodsAmount;
    }


    public Integer getVehicleBoxType() {
        return vehicleBoxType;
    }

    public void setVehicleBoxType(Integer vehicleBoxType) {
        this.vehicleBoxType = vehicleBoxType;
    }

    public Integer getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Integer taxRateId) {
        this.taxRateId = taxRateId;
    }
    
}
