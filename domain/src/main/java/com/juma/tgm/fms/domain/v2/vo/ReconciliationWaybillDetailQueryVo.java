package com.juma.tgm.fms.domain.v2.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: ReconciliationWaybillDetailQueryVo
 * @Description:
 * @author: liang
 * @date: 2018-06-06 12:00
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class ReconciliationWaybillDetailQueryVo extends ReconciliationWaybillDetailVo implements Serializable {

    /**
     * 开始时间
     */
    private Date timeStart;

    /**
     * 结束时间
     */
    private Date timeEnd;

    /**
     * 对账单号
     */
    private String reconciliationNo;

    /**
     * 客户id
     */
    private Integer customerId;

    /**
     * 车牌号
     */
    private String plateNumber;

    /**
     * 运单号
     */
    private String waybillNo;

    /**
     * 承运商 id
     *
     * */
    private Integer vendorId;

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }


    public String getReconciliationNo() {
        return reconciliationNo;
    }

    public void setReconciliationNo(String reconciliationNo) {
        this.reconciliationNo = reconciliationNo;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }


    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }
}
