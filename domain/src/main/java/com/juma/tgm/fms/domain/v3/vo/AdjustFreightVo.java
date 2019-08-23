package com.juma.tgm.fms.domain.v3.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName AdjustFreightVo
 * @Description 调整金额、调整绝对金额
 * @Author weilibin
 * @Date 2019-05-22 17:07
 * @Version 1.0.0
 */

public class AdjustFreightVo implements Serializable {

    // 调整金额
    private BigDecimal adjustFreight;
    // 调整绝对金额
    private BigDecimal adjustAbsFreight;

    public BigDecimal getAdjustFreight() {
        return adjustFreight;
    }

    public void setAdjustFreight(BigDecimal adjustFreight) {
        this.adjustFreight = adjustFreight;
    }

    public BigDecimal getAdjustAbsFreight() {
        return adjustAbsFreight;
    }

    public void setAdjustAbsFreight(BigDecimal adjustAbsFreight) {
        this.adjustAbsFreight = adjustAbsFreight;
    }
}
