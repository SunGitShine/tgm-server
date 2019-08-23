package com.juma.tgm.waybill.domain.drools;

import java.io.Serializable;
import java.math.BigDecimal;

public class RebateRateInfo implements Serializable {

    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3896276514729546759L;
    // 是否计算返点率
    private boolean flag = false;
    // 返点率
    private BigDecimal rate = BigDecimal.ZERO;
    
    public RebateRateInfo() {}
    
    public RebateRateInfo(BigDecimal rate) {
        this.flag = rate == null ? false : true;
        this.rate = rate == null ? BigDecimal.ZERO:rate;
        
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "RebateRateInfo [flag=" + flag + ", rate=" + rate + "]";
    }
    
}
