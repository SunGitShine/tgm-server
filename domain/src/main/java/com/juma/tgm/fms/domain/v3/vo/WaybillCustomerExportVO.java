package com.juma.tgm.fms.domain.v3.vo;

import me.about.poi.ExcelColumn;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 功能 : 
 * 1.运单导出-->成为调整单导入的基础数据
 * @author : Bruce(刘正航) 15:32 2019-05-13
 */
public class WaybillCustomerExportVO implements Serializable {
    @ExcelColumn(name = "运单号")
    private String waybillNo;
    @ExcelColumn(name = "客户")
    private String customerName;
    @ExcelColumn(name = "项目")
    private String projectName;
    @ExcelColumn(name = "承运商")
    private String vendorName;
    @ExcelColumn(name = "司机")
    private String driverName;
    @ExcelColumn(name = "车辆")
    private String plateNumber;
    @ExcelColumn(name = "客户侧含税金额")
    private BigDecimal freightWithTax;
    @ExcelColumn(name = "调整基础运费")
    private BigDecimal adjustForFreight;
    @ExcelColumn(name = "调整超工作量运费")
    private BigDecimal adjustForWorkload;
    @ExcelColumn(name = "调整装卸费")
    private BigDecimal adjustForCarry;
    @ExcelColumn(name = "调整上楼费")
    private BigDecimal adjustForUpstairs;
    @ExcelColumn(name = "调整罚款")
    private BigDecimal adjustForFine;
    @ExcelColumn(name = "调整货损货差")
    private BigDecimal adjustForCargoLoss;
    @ExcelColumn(name = "调整原因说明")
    private String adjustForReason;

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(final String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(final String customerName) {
        this.customerName = customerName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(final String projectName) {
        this.projectName = projectName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(final String vendorName) {
        this.vendorName = vendorName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(final String driverName) {
        this.driverName = driverName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(final String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public BigDecimal getFreightWithTax() {
        return freightWithTax;
    }

    public void setFreightWithTax(final BigDecimal freightWithTax) {
        this.freightWithTax = freightWithTax;
    }

    public BigDecimal getAdjustForFreight() {
        return adjustForFreight;
    }

    public void setAdjustForFreight(final BigDecimal adjustForFreight) {
        this.adjustForFreight = adjustForFreight;
    }

    public BigDecimal getAdjustForCarry() {
        return adjustForCarry;
    }

    public void setAdjustForCarry(final BigDecimal adjustForCarry) {
        this.adjustForCarry = adjustForCarry;
    }

    public BigDecimal getAdjustForWorkload() {
        return adjustForWorkload;
    }

    public void setAdjustForWorkload(final BigDecimal adjustForWorkload) {
        this.adjustForWorkload = adjustForWorkload;
    }

    public BigDecimal getAdjustForUpstairs() {
        return adjustForUpstairs;
    }

    public void setAdjustForUpstairs(final BigDecimal adjustForUpstairs) {
        this.adjustForUpstairs = adjustForUpstairs;
    }

    public BigDecimal getAdjustForFine() {
        return adjustForFine;
    }

    public void setAdjustForFine(final BigDecimal adjustForFine) {
        this.adjustForFine = adjustForFine;
    }

    public BigDecimal getAdjustForCargoLoss() {
        return adjustForCargoLoss;
    }

    public void setAdjustForCargoLoss(final BigDecimal adjustForCargoLoss) {
        this.adjustForCargoLoss = adjustForCargoLoss;
    }

    public String getAdjustForReason() {
        return adjustForReason;
    }

    public void setAdjustForReason(final String adjustForReason) {
        this.adjustForReason = adjustForReason;
    }
}
