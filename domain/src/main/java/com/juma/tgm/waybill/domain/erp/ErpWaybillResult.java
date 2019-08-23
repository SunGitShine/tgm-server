package com.juma.tgm.waybill.domain.erp;

import java.io.Serializable;

public class ErpWaybillResult implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8256894795013574738L;

    private Integer waybillId;
    
    private Boolean isSubmitToErp;
    
    private String erpResult;

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public Boolean getIsSubmitToErp() {
        return isSubmitToErp;
    }

    public void setIsSubmitToErp(Boolean isSubmitToErp) {
        this.isSubmitToErp = isSubmitToErp;
    }

    public String getErpResult() {
        return erpResult;
    }

    public void setErpResult(String erpResult) {
        this.erpResult = erpResult;
    }
    
}
