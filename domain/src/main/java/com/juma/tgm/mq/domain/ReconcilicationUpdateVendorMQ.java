package com.juma.tgm.mq.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * 应付对账单结算承运商和合同承运商不一致
 * 由FMS通知TMS正确的承运商，更新子对账单、运单、调整单承运商信息
 * @author: xieqiang
 *
 * @create: 2019-05-23 15:33
 **/
public class ReconcilicationUpdateVendorMQ implements Serializable{

	//对账单类型“AP”
	private String reconcilicationType;

	//新承运商id
	private Integer vendorId;

	//新承运商名称
	private String vendorName;

	//旧承运商id
	private Integer oldVendorId;

	//旧承运商名称
	private String oldVendorName;

	//子对账单及运单编号列表
	private List<ReconcilicationWaybill> reconcilicationWaybillList;

	public String getReconcilicationType() {
		return reconcilicationType;
	}

	public void setReconcilicationType(String reconcilicationType) {
		this.reconcilicationType = reconcilicationType;
	}

	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public Integer getOldVendorId() {
		return oldVendorId;
	}

	public void setOldVendorId(Integer oldVendorId) {
		this.oldVendorId = oldVendorId;
	}

	public String getOldVendorName() {
		return oldVendorName;
	}

	public void setOldVendorName(String oldVendorName) {
		this.oldVendorName = oldVendorName;
	}

	public List<ReconcilicationWaybill> getReconcilicationWaybillList() {
		return reconcilicationWaybillList;
	}

	public void setReconcilicationWaybillList(
		List<ReconcilicationWaybill> reconcilicationWaybillList) {
		this.reconcilicationWaybillList = reconcilicationWaybillList;
	}
}
