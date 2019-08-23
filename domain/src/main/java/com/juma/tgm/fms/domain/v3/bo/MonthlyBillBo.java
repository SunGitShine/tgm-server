package com.juma.tgm.fms.domain.v3.bo;

import java.io.Serializable;
import java.util.List;

public class MonthlyBillBo implements Serializable {

    private List<String> waybillNos;

    private String type;

    public List<String> getWaybillNos() {
        return waybillNos;
    }

    public void setWaybillNos(List<String> waybillNos) {
        this.waybillNos = waybillNos;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
