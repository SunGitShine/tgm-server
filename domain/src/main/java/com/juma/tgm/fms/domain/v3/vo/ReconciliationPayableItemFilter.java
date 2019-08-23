package com.juma.tgm.fms.domain.v3.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 功能 : 
 *
 * @author : Bruce(刘正航) 17:59 2019-05-22
 */
public class ReconciliationPayableItemFilter implements Serializable {
    /**对账单ID**/
    private Integer reconcilicationId;
    /**运单ID**/
    private Integer waybillId;
    /**运单ID集合**/
    private List<Integer> waybillIds;
    /**运单编号**/
    private String waybillNo;

    public Integer getReconcilicationId() {
        return reconcilicationId;
    }

    public void setReconcilicationId(final Integer reconcilicationId) {
        this.reconcilicationId = reconcilicationId;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(final Integer waybillId) {
        this.waybillId = waybillId;
    }

    public List<Integer> getWaybillIds() {
        return waybillIds;
    }

    public void setWaybillIds(final List<Integer> waybillIds) {
        this.waybillIds = waybillIds;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(final String waybillNo) {
        this.waybillNo = waybillNo;
    }
}
