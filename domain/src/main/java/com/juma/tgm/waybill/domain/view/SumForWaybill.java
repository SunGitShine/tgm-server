package com.juma.tgm.waybill.domain.view;

import java.io.Serializable;
import java.math.BigDecimal;

public class SumForWaybill implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5000995649545423874L;
    
    private BigDecimal sumFreightWithTax;
    
    private String plateNumber;

    public BigDecimal getSumFreightWithTax() {
        return sumFreightWithTax;
    }

    public void setSumFreightWithTax(BigDecimal sumFreightWithTax) {
        this.sumFreightWithTax = sumFreightWithTax;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }



}
