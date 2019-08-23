package com.juma.tgm.base.domain;

import java.io.Serializable;

/**
 * @ClassName UploadExcelResult.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年1月12日 下午5:30:40
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class UploadExcelFailedReason implements Serializable {

    private static final long serialVersionUID = -7813911377624696722L;
    private Integer excelRowNo;
    private Integer waybillId;
    private String waybillNo;
    private String reason;

    public Integer getExcelRowNo() {
        return excelRowNo;
    }

    public void setExcelRowNo(Integer excelRowNo) {
        this.excelRowNo = excelRowNo;
    }

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
