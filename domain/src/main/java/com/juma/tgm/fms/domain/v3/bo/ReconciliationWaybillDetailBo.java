package com.juma.tgm.fms.domain.v3.bo;

import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillParam;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @ClassName ReconciliationWaybillDetailBo.java 请填写注释...
 * @author Libin.Wei
 * @Date 2018年11月27日 上午10:04:58
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */
public class ReconciliationWaybillDetailBo implements Serializable {

    private Waybill waybill;

    private WaybillParam waybillParam;

    private String driverTypeName;
    private String vehicleTypeName;
    private String vendorOrDriverName;
    private String receivableReconcilicationStatusDesc;
    private BigDecimal taxRateValue;

    //改价申请
    private boolean hasAdjustApply;

    public boolean isHasAdjustApply() {
        return hasAdjustApply;
    }

    public void setHasAdjustApply(boolean hasAdjustApply) {
        this.hasAdjustApply = hasAdjustApply;
    }

    public Waybill getWaybill() {
        return waybill;
    }

    public void setWaybill(Waybill waybill) {
        this.waybill = waybill;
    }

    public WaybillParam getWaybillParam() {
        return waybillParam;
    }

    public void setWaybillParam(WaybillParam waybillParam) {
        this.waybillParam = waybillParam;
    }

    public String getDriverTypeName() {
        return driverTypeName;
    }

    public void setDriverTypeName(String driverTypeName) {
        this.driverTypeName = driverTypeName;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public String getVendorOrDriverName() {
        return vendorOrDriverName;
    }

    public void setVendorOrDriverName(String vendorOrDriverName) {
        this.vendorOrDriverName = vendorOrDriverName;
    }

    public String getReceivableReconcilicationStatusDesc() {
        return receivableReconcilicationStatusDesc;
    }

    public void setReceivableReconcilicationStatusDesc(String receivableReconcilicationStatusDesc) {
        this.receivableReconcilicationStatusDesc = receivableReconcilicationStatusDesc;
    }

    public BigDecimal getTaxRateValue() {
        return taxRateValue;
    }

    public void setTaxRateValue(BigDecimal taxRateValue) {
        this.taxRateValue = taxRateValue;
    }
}
