package com.juma.tgm.waybill.domain;

import com.juma.tgm.base.domain.BaseDomain;

/**
 * waybill_exception - 运单异常
 * 
 * @author 2016-05-09
 * @version 1.0
 */
public class WaybillException extends BaseDomain {

    private static final long serialVersionUID = -4142348968482915667L;
    private Integer exceptionId;
    private Integer waybillId;
    private Integer exceptionType;
    private Integer status;
    private String note;
    private Integer operatorUserId;

    public Integer getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(Integer exceptionId) {
        this.exceptionId = exceptionId;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public Integer getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(Integer exceptionType) {
        this.exceptionType = exceptionType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

}