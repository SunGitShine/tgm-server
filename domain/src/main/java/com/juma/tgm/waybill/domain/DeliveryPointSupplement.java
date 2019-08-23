package com.juma.tgm.waybill.domain;


import java.io.Serializable;
import java.util.Date;

/**
 * delivery_point_supplement - 补充配送点
 * 
 * @author  2017-04-27
 * @version 1.0 
 */
public class DeliveryPointSupplement implements Serializable{
	private Integer deliveryPointSupplementId;
	private Integer waybillId;
	private String img;
	private Byte orders;
	private Byte isDelete;
	private Date createTime;
	private Integer createUserId;
	private Integer lastUpdateUserId;
	private Date lastUpdateTime;

	public Integer getDeliveryPointSupplementId() {
		return deliveryPointSupplementId;
	}

	public void setDeliveryPointSupplementId(Integer deliveryPointSupplementId) {
		this.deliveryPointSupplementId = deliveryPointSupplementId;
	}

	public Integer getWaybillId() {
		return waybillId;
	}

	public void setWaybillId(Integer waybillId) {
		this.waybillId = waybillId;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Byte getOrders() {
		return orders;
	}

	public void setOrders(Byte orders) {
		this.orders = orders;
	}

	public Byte getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Byte isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getLastUpdateUserId() {
		return lastUpdateUserId;
	}

	public void setLastUpdateUserId(Integer lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}