/**
* @Title: TaxRateInfo.java
* @Package com.juma.tgm.waybill.domain.drools
*<B>Copyright</B> Copyright (c) 2016 www.jumapeisong.com All rights reserved. <br />
* 本软件源代码版权归驹马,未经许可不得任意复制与传播.<br />
* <B>Company</B> 驹马配送
* @date 2016年9月7日 下午8:05:28
* @version V1.0  
 */
package com.juma.tgm.waybill.domain.drools;

import java.io.Serializable;
import java.math.BigDecimal;

import com.juma.tgm.waybill.domain.WaybillParam;

/**
 * @Description:
 * @author Administrator
 * @date 2016年9月7日 下午8:05:28
 * @version V1.0
 */
public class WaybillParamInfo implements Serializable{

    private static final long serialVersionUID = -2070185769774185856L;
    // 是否计算搬运费
    private boolean flag = false;
    // 司机搬运费
    private BigDecimal driverHandlingCost = BigDecimal.ZERO;
    // 小工搬运费
    private BigDecimal laborerHandlingCost = BigDecimal.ZERO;
    // 司机搬运费
    private BigDecimal oldDriverHandlingCost = BigDecimal.ZERO;
    // 小工搬运费
    private BigDecimal oldLaborerHandlingCost = BigDecimal.ZERO;
    // 税率
    private BigDecimal rate = BigDecimal.ZERO;

    public WaybillParamInfo() {
    }

    public WaybillParamInfo(WaybillParam waybillParam, WaybillParam oldWaybillParam, BigDecimal rate) {
        if (null != waybillParam) {
            this.driverHandlingCost = waybillParam.getDriverHandlingCost();
            this.laborerHandlingCost = waybillParam.getLaborerHandlingCost();
            if (null != oldWaybillParam) {
                this.oldDriverHandlingCost = oldWaybillParam.getDriverHandlingCost() == null ? BigDecimal.ZERO
                        : oldWaybillParam.getDriverHandlingCost();
                this.oldLaborerHandlingCost = oldWaybillParam.getLaborerHandlingCost() == null ? BigDecimal.ZERO
                        : oldWaybillParam.getLaborerHandlingCost();
            }
            this.rate = rate;
            if (null != this.driverHandlingCost && null != this.laborerHandlingCost && null != rate) {
                this.flag = true;
            }
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public BigDecimal getDriverHandlingCost() {
        return driverHandlingCost;
    }

    public void setDriverHandlingCost(BigDecimal driverHandlingCost) {
        this.driverHandlingCost = driverHandlingCost;
    }

    public BigDecimal getLaborerHandlingCost() {
        return laborerHandlingCost;
    }

    public void setLaborerHandlingCost(BigDecimal laborerHandlingCost) {
        this.laborerHandlingCost = laborerHandlingCost;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getOldDriverHandlingCost() {
        return oldDriverHandlingCost;
    }

    public void setOldDriverHandlingCost(BigDecimal oldDriverHandlingCost) {
        this.oldDriverHandlingCost = oldDriverHandlingCost;
    }

    public BigDecimal getOldLaborerHandlingCost() {
        return oldLaborerHandlingCost;
    }

    public void setOldLaborerHandlingCost(BigDecimal oldLaborerHandlingCost) {
        this.oldLaborerHandlingCost = oldLaborerHandlingCost;
    }

}
