package com.juma.tgm.customerManager.domain;


import java.io.Serializable;
import java.util.Date;

/**
 * fixed_demand_delivery_point - 用于记录固定需求的取货地和配送地
 * 
 * @author  2017-07-24
 * @version 1.0 
 */
public class FixedDemandDeliveryPoint implements Serializable{
	private Integer fixedDemandDeliveryPointId;
	private Integer fixedDemandId;
	private Integer startPoint;
	private String regionCode;
	private String addressName;
	private String addressDetail;
	private String contactName;
	private String contactPhone;
	private String coordinates;
	private Date createTime;
	private Integer createUserId;
	private Date lastUpdateTime;
	private Integer lastUpdateUserId;


	public enum PointType{
		receive_address(1),delivery_address(0);

		private Integer code;

		 PointType(int code){
			this.code = code;
		}

		public int getCode(){
			return code;
		}
	}

	public Integer getFixedDemandDeliveryPointId() {
		return fixedDemandDeliveryPointId;
	}

	public void setFixedDemandDeliveryPointId(Integer fixedDemandDeliveryPointId) {
		this.fixedDemandDeliveryPointId = fixedDemandDeliveryPointId;
	}

	public Integer getFixedDemandId() {
		return fixedDemandId;
	}

	public void setFixedDemandId(Integer fixedDemandId) {
		this.fixedDemandId = fixedDemandId;
	}

	public Integer getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Integer startPoint) {
		this.startPoint = startPoint;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
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

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getLastUpdateUserId() {
		return lastUpdateUserId;
	}

	public void setLastUpdateUserId(Integer lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}

}