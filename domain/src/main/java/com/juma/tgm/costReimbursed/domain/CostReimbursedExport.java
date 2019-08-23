package com.juma.tgm.costReimbursed.domain;

import me.about.poi.ExcelColumn;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by shawn_lin on 2017/7/13.
 */
public class CostReimbursedExport implements Serializable {
    @ExcelColumn(name = "流水号", width = 30)
    private String costReimbursedNo;
    @ExcelColumn(name = "报销项目", width = 30)
    private String costReimbursedTypePlus;
    @ExcelColumn(name = "运单号", width = 30)
    private String waybillNo;
    @ExcelColumn(name = "费用产生时间", width = 30)
    private String costProduceTime;
    @ExcelColumn(name = "申报时间", width = 30)
    private String declareTime;
    @ExcelColumn(name = "车牌号", width = 30)
    private String plateNumber;
    @ExcelColumn(name = "司机姓名", width = 30)
    private String driverName;
    @ExcelColumn(name = "报销金额", width = 30)
    private BigDecimal reimbursedAmount;
    @ExcelColumn(name = "凭证数", width = 30)
    private Integer count;
    @ExcelColumn(name = "审核结果", width = 30)
    private String auditResultPlus;


    public String getCostReimbursedNo() {
        return costReimbursedNo;
    }

    public void setCostReimbursedNo(String costReimbursedNo) {
        this.costReimbursedNo = costReimbursedNo;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getCostProduceTime() {
        return costProduceTime;
    }

    public void setCostProduceTime(String costProduceTime) {
        this.costProduceTime = costProduceTime;
    }

    public String getDeclareTime() {
        return declareTime;
    }

    public void setDeclareTime(String declareTime) {
        this.declareTime = declareTime;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public BigDecimal getReimbursedAmount() {
        return reimbursedAmount;
    }

    public void setReimbursedAmount(BigDecimal reimbursedAmount) {
        this.reimbursedAmount = reimbursedAmount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCostReimbursedTypePlus() {
        return costReimbursedTypePlus;
    }

    public void setCostReimbursedTypePlus(String costReimbursedTypePlus) {
        this.costReimbursedTypePlus = costReimbursedTypePlus;
    }

    public String getAuditResultPlus() {
        return auditResultPlus;
    }

    public void setAuditResultPlus(String auditResultPlus) {
        this.auditResultPlus = auditResultPlus;
    }
}
