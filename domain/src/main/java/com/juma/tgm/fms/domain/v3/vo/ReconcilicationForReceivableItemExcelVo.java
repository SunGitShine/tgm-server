package com.juma.tgm.fms.domain.v3.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import me.about.poi.ExcelColumn;

public class ReconcilicationForReceivableItemExcelVo implements Serializable{

	@ExcelColumn(name = "运单号")
	private String waybillNo;

	@ExcelColumn(name = "用车时间")
	private Date planDeliveryTime;

	@ExcelColumn(name = "承运商/司机")
	private String vendorOrDriver;

	@ExcelColumn(name = "司机类型")
	private String driverType;

	@ExcelColumn(name = "车牌号")
	private String plateNumber;

	@ExcelColumn(name = "税率")
	private BigDecimal taxRate;

	@ExcelColumn(name = "客户侧含税金额")
	private BigDecimal receivableWithTax;

	@ExcelColumn(name = "客户侧不含税金额")
	private BigDecimal receivableWithoutTax;

	@ExcelColumn(name = "对账后调整金额")
	private BigDecimal adjustForFreight;

	@ExcelColumn(name = "最终含税金额")
	private BigDecimal finalFreightForCustomer;

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

	public String getVendorOrDriver() {
		return vendorOrDriver;
	}

	public void setVendorOrDriver(String vendorOrDriver) {
		this.vendorOrDriver = vendorOrDriver;
	}

	public String getDriverType() {
		return driverType;
	}

	public void setDriverType(String driverType) {
		this.driverType = driverType;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getReceivableWithTax() {
		return receivableWithTax;
	}

	public void setReceivableWithTax(BigDecimal receivableWithTax) {
		this.receivableWithTax = receivableWithTax;
	}

	public BigDecimal getReceivableWithoutTax() {
		return receivableWithoutTax;
	}

	public void setReceivableWithoutTax(BigDecimal receivableWithoutTax) {
		this.receivableWithoutTax = receivableWithoutTax;
	}

	public BigDecimal getAdjustForFreight() {
		return adjustForFreight;
	}

	public void setAdjustForFreight(BigDecimal adjustForFreight) {
		this.adjustForFreight = adjustForFreight;
	}

	public BigDecimal getFinalFreightForCustomer() {
		return finalFreightForCustomer;
	}

	public void setFinalFreightForCustomer(BigDecimal finalFreightForCustomer) {
		this.finalFreightForCustomer = finalFreightForCustomer;
	}
}

