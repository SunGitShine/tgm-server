package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * waybill_view_history - waybill_view_history
 * 运单被查看历史
 * @author  2017-04-05
 * @version 1.0 
 */
public class WaybillViewHistory implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5753875181781316374L;
    private Integer waybillViewHistoryId;
    private Integer waybillId;
	private Integer driverId;
	private Date viewTime;
	private Long costSecond;

	public Integer getWaybillViewHistoryId() {
		return waybillViewHistoryId;
	}

	public void setWaybillViewHistoryId(Integer waybillViewHistoryId) {
		this.waybillViewHistoryId = waybillViewHistoryId;
	}

	

	public Date getViewTime() {
		return viewTime;
	}

	public void setViewTime(Date viewTime) {
		this.viewTime = viewTime;
	}

	public Long getCostSecond() {
		return costSecond;
	}

	public void setCostSecond(Long costSecond) {
		this.costSecond = costSecond;
	}

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

}