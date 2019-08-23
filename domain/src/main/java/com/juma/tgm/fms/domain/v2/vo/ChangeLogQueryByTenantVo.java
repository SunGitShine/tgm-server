package com.juma.tgm.fms.domain.v2.vo;

import java.io.Serializable;

public class ChangeLogQueryByTenantVo implements Serializable{
    private Integer reconciliationId;



    public Integer getReconciliationId() {
        return reconciliationId;
    }

    public void setReconciliationId(Integer reconciliationId) {
        this.reconciliationId = reconciliationId;
    }

}
