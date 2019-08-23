package com.juma.tgm.fms.domain.v3.bo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.juma.tgm.fms.domain.v3.AdjustForPayable;

/**
 * @ClassName AdjustForPayableQuery.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年11月23日 上午10:51:08
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class AdjustForPayableQuery implements Serializable {

    private AdjustForPayable adjustForPayable;
    // 结算价调整幅度
    private BigDecimal payableWithTaxAdjustRange;
    // 司机搬运费调整幅度
    private BigDecimal driverTransportFeeAdjustRange;
    // 小工搬运费调整幅度
    private BigDecimal temporaryTransportFeeAdjustRange;
    // 操作人姓名
    private String adjustUserName;

    public AdjustForPayable getAdjustForPayable() {
        return adjustForPayable;
    }

    public void setAdjustForPayable(AdjustForPayable adjustForPayable) {
        this.adjustForPayable = adjustForPayable;
    }

    public BigDecimal getPayableWithTaxAdjustRange() {
        if (null == adjustForPayable) {
            return payableWithTaxAdjustRange;
        }

        BigDecimal payableWithTax = adjustForPayable.getPayableWithTax();
        BigDecimal payableWithTaxAdjust = adjustForPayable.getPayableWithTaxAdjust();
        if (null == payableWithTax) {
            payableWithTax = BigDecimal.ZERO;
        }

        if (null == payableWithTaxAdjust) {
            payableWithTaxAdjust = BigDecimal.ZERO;
        }

        return payableWithTaxAdjust.subtract(payableWithTax);
    }

    public BigDecimal getDriverTransportFeeAdjustRange() {
        if (null == adjustForPayable) {
            return driverTransportFeeAdjustRange;
        }

        BigDecimal driverTransportFee = adjustForPayable.getDriverTransportFee();
        BigDecimal driverTransportFeeAdjust = adjustForPayable.getDriverTransportFeeAdjust();
        if (null == driverTransportFee) {
            driverTransportFee = BigDecimal.ZERO;
        }

        if (null == driverTransportFeeAdjust) {
            driverTransportFeeAdjust = BigDecimal.ZERO;
        }

        return driverTransportFeeAdjust.subtract(driverTransportFee);
    }

    public BigDecimal getTemporaryTransportFeeAdjustRange() {
        if (null == adjustForPayable) {
            return temporaryTransportFeeAdjustRange;
        }

        BigDecimal temporaryTransportFee = adjustForPayable.getTemporaryTransportFee();
        BigDecimal temporaryTransportFeeAdjust = adjustForPayable.getTemporaryTransportFeeAdjust();
        if (null == temporaryTransportFee) {
            temporaryTransportFee = BigDecimal.ZERO;
        }

        if (null == temporaryTransportFeeAdjust) {
            temporaryTransportFeeAdjust = BigDecimal.ZERO;
        }

        return temporaryTransportFeeAdjust.subtract(temporaryTransportFee);
    }

    public String getAdjustUserName() {
        return adjustUserName;
    }

    public void setAdjustUserName(String adjustUserName) {
        this.adjustUserName = adjustUserName;
    }

}
