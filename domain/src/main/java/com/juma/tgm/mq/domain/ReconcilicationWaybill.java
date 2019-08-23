package com.juma.tgm.mq.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-05-24 14:11
 **/
public class ReconcilicationWaybill implements Serializable{

	//子对账单号
	private String subReconcilicationNo;

	//运单编号
	private List<String> waybillNoList;

	public String getSubReconcilicationNo() {
		return subReconcilicationNo;
	}

	public void setSubReconcilicationNo(String subReconcilicationNo) {
		this.subReconcilicationNo = subReconcilicationNo;
	}

	public List<String> getWaybillNoList() {
		return waybillNoList;
	}

	public void setWaybillNoList(List<String> waybillNoList) {
		this.waybillNoList = waybillNoList;
	}
}
