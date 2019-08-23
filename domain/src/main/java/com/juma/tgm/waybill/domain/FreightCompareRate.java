package com.juma.tgm.waybill.domain;

import java.math.BigDecimal;

import com.juma.tgm.base.domain.BaseDomain;

/**
 * 价格对比度配置表
 * 
 * @author weilibin
 *
 */

public class FreightCompareRate extends BaseDomain {

    private static final long serialVersionUID = 8438053142100342128L;
    private Integer compareId;
    private BigDecimal compareRate;
    private String compareName;
    private String compareKey;
    private String descr;

    public Integer getCompareId() {
        return compareId;
    }

    public void setCompareId(Integer compareId) {
        this.compareId = compareId;
    }

    public BigDecimal getCompareRate() {
        return compareRate;
    }

    public void setCompareRate(BigDecimal compareRate) {
        this.compareRate = compareRate;
    }

    public String getCompareName() {
        return compareName;
    }

    public void setCompareName(String compareName) {
        this.compareName = compareName;
    }

    public String getCompareKey() {
        return compareKey;
    }

    public void setCompareKey(String compareKey) {
        this.compareKey = compareKey;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

}
