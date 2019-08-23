package com.juma.tgm.fms.domain.v2.vo;

import java.math.BigDecimal;

/**
 * @ClassName: FreightAdjustVo
 * @Description:
 * @author: liang
 * @date: 2018-06-06 13:48
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class FreightAdjustVo {

    /**
     * 对账单号
     */
    private Integer reconciliationId;
    /**
     * 车牌号
     */
    private String plateNumber;


    /**
     * 承运商 id
     *
     *
     * */
    private Integer vendorId;

    /**
     * 税率
     */
//    private BigDecimal taxRateValue;

    /**
     * 调整数额（税前）
     */
    private BigDecimal adjustAmount;

    public Integer getReconciliationId() {
        return reconciliationId;
    }

    public void setReconciliationId(Integer reconciliationId) {
        this.reconciliationId = reconciliationId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

//    public BigDecimal getTaxRateValue() {
//        return taxRateValue;
//    }
//
//    public void setTaxRateValue(BigDecimal taxRateValue) {
//        this.taxRateValue = taxRateValue;
//    }

    public BigDecimal getAdjustAmount() {
        return adjustAmount;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public void setAdjustAmount(BigDecimal adjustAmount) {
        this.adjustAmount = adjustAmount;
    }
}
