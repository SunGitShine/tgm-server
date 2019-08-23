package com.juma.tgm.fms.domain.v2.vo;

import java.io.Serializable;

/**
 * 根据车辆去查询对账单
 */
public class ChangeLogQueryByCarVo implements Serializable{

    /**
     * 车牌号
     */
    private String plateNumber;


    /**
     * 对账单号
     */
    private Integer reconciliationId;

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }


    public Integer getReconciliationId() {
        return reconciliationId;
    }

    public void setReconciliationId(Integer reconciliationId) {
        this.reconciliationId = reconciliationId;
    }
}
