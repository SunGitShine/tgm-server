package com.juma.tgm.fms.domain.v3.vo;

import com.juma.tgm.fms.domain.v3.AdjustForFreightAttach;
import com.juma.tgm.fms.domain.v3.AdjustForMaster;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-05-17 14:04
 **/
public class AdjustForMasterDetail implements Serializable{

	private AdjustForMaster adjustForMaster;

	private AdjustForFreightAttach adjustForFreightAttach;

	private Integer waybillCount;

	private BigDecimal adjustPercent;

	private WaybillStatisticsAmountVO waybillStatisticsAmountVO;

	public Integer getWaybillCount() {
		return waybillCount;
	}

	public void setWaybillCount(Integer waybillCount) {
		this.waybillCount = waybillCount;
	}

	public BigDecimal getAdjustPercent() {
		return adjustPercent;
	}

	public void setAdjustPercent(BigDecimal adjustPercent) {
		this.adjustPercent = adjustPercent;
	}

	public AdjustForMaster getAdjustForMaster() {
		return adjustForMaster;
	}

	public void setAdjustForMaster(AdjustForMaster adjustForMaster) {
		this.adjustForMaster = adjustForMaster;
	}

	public AdjustForFreightAttach getAdjustForFreightAttach() {
		return adjustForFreightAttach;
	}

	public void setAdjustForFreightAttach(AdjustForFreightAttach adjustForFreightAttach) {
		this.adjustForFreightAttach = adjustForFreightAttach;
	}

	public WaybillStatisticsAmountVO getWaybillStatisticsAmountVO() {
		return waybillStatisticsAmountVO;
	}

	public void setWaybillStatisticsAmountVO(WaybillStatisticsAmountVO waybillStatisticsAmountVO) {
		this.waybillStatisticsAmountVO = waybillStatisticsAmountVO;
	}
}
