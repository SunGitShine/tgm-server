package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * waybill_notice - waybill_notice
 * 运单通知记录
 * 
 * @author  2017-04-05
 * @version 1.0 
 */
public class WaybillNotice implements Serializable {
    
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8391004561108251218L;
    private Integer waybillNoticeId;
	private Integer waybillId;
	private Integer noticeDriverNum;
	private Date noticeTime;

	public Integer getWaybillNoticeId() {
		return waybillNoticeId;
	}

	public void setWaybillNoticeId(Integer waybillNoticeId) {
		this.waybillNoticeId = waybillNoticeId;
	}

	public Integer getWaybillId() {
		return waybillId;
	}

	public void setWaybillId(Integer waybillId) {
		this.waybillId = waybillId;
	}

	public Integer getNoticeDriverNum() {
		return noticeDriverNum;
	}

	public void setNoticeDriverNum(Integer noticeDriverNum) {
		this.noticeDriverNum = noticeDriverNum;
	}

	public Date getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}

}