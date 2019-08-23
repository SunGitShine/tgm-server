package com.juma.tgm.fms.domain.v3.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import me.about.poi.ExcelColumn;

public class ReconciliationWaybillExcelVo implements Serializable{

	private Integer waybillId;

	@ExcelColumn(name = "运单号")
	private String waybillNo;

	@ExcelColumn(name = "用车时间" ,width = 30)
	private Date planDeliveryTime;

	@ExcelColumn(name = "司机")
	private String driverName;

	@ExcelColumn(name = "车牌号")
	private String plateNumber;

	@ExcelColumn(name = "含税金额")
	private BigDecimal estimateFreight = BigDecimal.ZERO;

	@ExcelColumn(name = "税率")
	private BigDecimal taxRateValue;

	@ExcelColumn(name = "备注")
	private String updateFreightRemark;

	@ExcelColumn(name = "是否通知发单人")
	private String noticeToWaybillOwner = "否";

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

	public Date getPlanDeliveryTime() {
		return planDeliveryTime;
	}

	public void setPlanDeliveryTime(Date planDeliveryTime) {
		this.planDeliveryTime = planDeliveryTime;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public BigDecimal getEstimateFreight() {
		return estimateFreight;
	}

	public void setEstimateFreight(BigDecimal estimateFreight) {
		this.estimateFreight = estimateFreight;
	}

	public BigDecimal getTaxRateValue() {
		return taxRateValue;
	}

	public void setTaxRateValue(BigDecimal taxRateValue) {
		this.taxRateValue = taxRateValue;
	}

	public String getUpdateFreightRemark() {
		return updateFreightRemark;
	}

	public void setUpdateFreightRemark(String updateFreightRemark) {
		this.updateFreightRemark = updateFreightRemark;
	}

	public String getNoticeToWaybillOwner() {
		return noticeToWaybillOwner;
	}

	public void setNoticeToWaybillOwner(String noticeToWaybillOwner) {
		this.noticeToWaybillOwner = noticeToWaybillOwner;
	}
}
