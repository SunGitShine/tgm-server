package com.juma.tgm.waybill.domain.drools;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.juma.tgm.truck.domain.AdditionalFunctionFreight;

public class LaborerCarryInfo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3743502029475792483L;

    // 是否参与计算
    private boolean flag = false;

    private BigDecimal price = BigDecimal.ZERO;

    public LaborerCarryInfo() {}
    
    public LaborerCarryInfo(AdditionalFunctionFreight freight,List<Integer> list) {
        if(freight != null) {
            this.flag = list.contains(freight.getAdditionalFunctionId());
            this.price = freight.getLowestFreight() == null ? BigDecimal.ZERO : freight.getLowestFreight();
        }
    }
    
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "LaborerCarryInfo [flag=" + flag + ", price=" + price + "]";
    }

}
