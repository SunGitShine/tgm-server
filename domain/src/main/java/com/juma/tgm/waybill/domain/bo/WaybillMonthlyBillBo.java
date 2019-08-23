package com.juma.tgm.waybill.domain.bo;

import com.juma.tgm.waybill.domain.Waybill;

import java.io.Serializable;
import java.math.BigDecimal;

public class WaybillMonthlyBillBo extends Waybill implements Serializable {

    private static final long serialVersionUID = -5061971984580160119L;

    // 车架号
    private String frameNo;

    //税率
    private BigDecimal taxRateValue;

    public String getFrameNo() {
        return frameNo;
    }

    public void setFrameNo(String frameNo) {
        this.frameNo = frameNo;
    }

    public BigDecimal getTaxRateValue() {
        return taxRateValue;
    }

    public void setTaxRateValue(BigDecimal taxRateValue) {
        this.taxRateValue = taxRateValue;
    }
}
