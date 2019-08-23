package com.juma.tgm.fms.domain.v3.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillParam;

import com.juma.vms.driver.enumeration.DriverTypeEnum;
import me.about.poi.ExcelColumn;

/**
 * 
 * @ClassName WaybillAdjustFrightForPayable.java 应付价格调整
 * @author Libin.Wei
 * @Date 2018年11月27日 下午8:17:34
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class WaybillAdjustFrightForPayable implements Serializable {

    @ExcelColumn(name = "运单号")
    private String waybillNo;
    @ExcelColumn(name = "用车时间", width = 30)
    private Date planDeliveryTime;
    @ExcelColumn(name = "司机/承运商")
    private String driverOrVendorName;
    @ExcelColumn(name = "司机类型")
    private String driverTypeName;
    @ExcelColumn(name = "车牌号")
    private String plateNumber;
    @ExcelColumn(name = "承运商/司机含税金额")
    private BigDecimal show4DriverFreight = BigDecimal.ZERO;
    @ExcelColumn(name = "司机搬运费")
    private BigDecimal driverHandlingCost = BigDecimal.ZERO;
    @ExcelColumn(name = "小工搬运费")
    private BigDecimal laborerHandlingCost = BigDecimal.ZERO;
    @ExcelColumn(name = "改价备注（必填）")
    private String adjustRemark;
    @ExcelColumn(name = "是否通知司机")
    private String noticeToDriver = "否";

    private Integer waybillId;
    // 初始承运商含税金额
    private BigDecimal initShow4DriverFreight = BigDecimal.ZERO;
    // 初始司机搬运费
    private BigDecimal initDriverHandlingCost = BigDecimal.ZERO;
    // 初始小工搬运费
    private BigDecimal initLaborerHandlingCost = BigDecimal.ZERO;

    public WaybillAdjustFrightForPayable() {
    }

    public WaybillAdjustFrightForPayable(Waybill waybill, WaybillParam waybillParam) {
        if (null != waybill) {
            this.waybillId = waybill.getWaybillId();
            this.waybillNo = waybill.getWaybillNo();
            this.planDeliveryTime = waybill.getPlanDeliveryTime();
            this.driverTypeName = waybill.getDriverType() == null ? null
                    : DriverTypeEnum.getDescByCode(waybill.getDriverType());
            this.show4DriverFreight = waybill.getShow4DriverFreight() == null ? BigDecimal.ZERO
                    : waybill.getShow4DriverFreight();
            this.plateNumber = waybill.getPlateNumber();
            this.initShow4DriverFreight = waybill.getShow4DriverFreight() == null ? BigDecimal.ZERO
                    : waybill.getShow4DriverFreight();
        }

        if (null != waybillParam) {
            this.driverHandlingCost = waybillParam.getDriverHandlingCost() == null ? BigDecimal.ZERO
                    : waybillParam.getDriverHandlingCost();
            this.laborerHandlingCost = waybillParam.getLaborerHandlingCost() == null ? BigDecimal.ZERO
                    : waybillParam.getLaborerHandlingCost();
            this.initDriverHandlingCost = waybillParam.getDriverHandlingCost() == null ? BigDecimal.ZERO
                    : waybillParam.getDriverHandlingCost();
            this.initLaborerHandlingCost = waybillParam.getLaborerHandlingCost() == null ? BigDecimal.ZERO
                    : waybillParam.getLaborerHandlingCost();
        }
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public Date getPlanDeliveryTime() {
        return planDeliveryTime;
    }

    public void setPlanDeliveryTime(Date planDeliveryTime) {
        this.planDeliveryTime = planDeliveryTime;
    }

    public String getDriverOrVendorName() {
        return driverOrVendorName;
    }

    public void setDriverOrVendorName(String driverOrVendorName) {
        this.driverOrVendorName = driverOrVendorName;
    }

    public String getDriverTypeName() {
        return driverTypeName;
    }

    public void setDriverTypeName(String driverTypeName) {
        this.driverTypeName = driverTypeName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public BigDecimal getShow4DriverFreight() {
        return show4DriverFreight;
    }

    public void setShow4DriverFreight(BigDecimal show4DriverFreight) {
        this.show4DriverFreight = show4DriverFreight;
    }

    public BigDecimal getDriverHandlingCost() {
        return driverHandlingCost;
    }

    public void setDriverHandlingCost(BigDecimal driverHandlingCost) {
        this.driverHandlingCost = driverHandlingCost;
    }

    public BigDecimal getLaborerHandlingCost() {
        return laborerHandlingCost;
    }

    public void setLaborerHandlingCost(BigDecimal laborerHandlingCost) {
        this.laborerHandlingCost = laborerHandlingCost;
    }

    public String getAdjustRemark() {
        return adjustRemark;
    }

    public void setAdjustRemark(String adjustRemark) {
        this.adjustRemark = adjustRemark;
    }

    public String getNoticeToDriver() {
        return noticeToDriver;
    }

    public void setNoticeToDriver(String noticeToDriver) {
        this.noticeToDriver = noticeToDriver;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public BigDecimal getInitShow4DriverFreight() {
        return initShow4DriverFreight;
    }

    public void setInitShow4DriverFreight(BigDecimal initShow4DriverFreight) {
        this.initShow4DriverFreight = initShow4DriverFreight;
    }

    public BigDecimal getInitDriverHandlingCost() {
        return initDriverHandlingCost;
    }

    public void setInitDriverHandlingCost(BigDecimal initDriverHandlingCost) {
        this.initDriverHandlingCost = initDriverHandlingCost;
    }

    public BigDecimal getInitLaborerHandlingCost() {
        return initLaborerHandlingCost;
    }

    public void setInitLaborerHandlingCost(BigDecimal initLaborerHandlingCost) {
        this.initLaborerHandlingCost = initLaborerHandlingCost;
    }

}
