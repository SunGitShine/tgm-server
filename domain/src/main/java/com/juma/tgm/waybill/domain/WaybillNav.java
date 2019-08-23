package com.juma.tgm.waybill.domain;

import java.io.Serializable;

public class WaybillNav implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5493438258509763347L;

    private Integer waybillId;
    
    private String waybillNo;

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }
    
}
