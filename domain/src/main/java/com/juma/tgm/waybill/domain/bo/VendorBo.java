package com.juma.tgm.waybill.domain.bo;

import java.io.Serializable;
import java.util.Date;

public class VendorBo implements Serializable {
	private Integer vendorId;
	private Integer userId;
	private String vendorName;
	private String serialNo;
	private String contactUserName;
	private String contactPhone;
	private String idCardNo;
	private Date idCardNoExpireTime;
	private String taxNo;
	private Byte source;
	private String bankOfDeposit;
	private String bankAccount;
	private String enterpriseCode;
	private Date skillLicenseExpireTime;
	private Byte vendorType;
	private String identityCardImg;
	private String qualityCertImg;
	private String roadTransPermitImg;
	private String businessLicenseImg;
	private String vendorAddress;
	private Byte isShowYourPrice;
	private Byte isSyncAsDriver;
	private Byte isEnable;
	private Byte isDelete;
	private Byte isOpen;
	private String remark;
	private Integer createUserId;
	private Date createTime;
	private Date lastUpdateTime;
	private Integer lastUpdateUserId;
	private static final long serialVersionUID = 1L;

	//承运商名称
	private String vehicleToVendorName;
	//承运商电话
	private String vehicleToVendorPhone;

	public String getVehicleToVendorName() {
		return vehicleToVendorName;
	}

	public void setVehicleToVendorName(String vehicleToVendorName) {
		this.vehicleToVendorName = vehicleToVendorName;
	}

	public String getVehicleToVendorPhone() {
		return vehicleToVendorPhone;
	}

	public void setVehicleToVendorPhone(String vehicleToVendorPhone) {
		this.vehicleToVendorPhone = vehicleToVendorPhone;
	}

	public VendorBo() {
	}

	public Integer getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getVendorName() {
		return this.vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName == null ? null : vendorName.trim();
	}

	public String getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo == null ? null : serialNo.trim();
	}

	public String getContactUserName() {
		return this.contactUserName;
	}

	public void setContactUserName(String contactUserName) {
		this.contactUserName = contactUserName == null ? null : contactUserName.trim();
	}

	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone == null ? null : contactPhone.trim();
	}

	public String getIdCardNo() {
		return this.idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo == null ? null : idCardNo.trim();
	}

	public Date getIdCardNoExpireTime() {
		return this.idCardNoExpireTime;
	}

	public void setIdCardNoExpireTime(Date idCardNoExpireTime) {
		this.idCardNoExpireTime = idCardNoExpireTime;
	}

	public String getTaxNo() {
		return this.taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo == null ? null : taxNo.trim();
	}

	public Byte getSource() {
		return this.source;
	}

	public void setSource(Byte source) {
		this.source = source;
	}

	public String getBankOfDeposit() {
		return this.bankOfDeposit;
	}

	public void setBankOfDeposit(String bankOfDeposit) {
		this.bankOfDeposit = bankOfDeposit == null ? null : bankOfDeposit.trim();
	}

	public String getBankAccount() {
		return this.bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount == null ? null : bankAccount.trim();
	}

	public String getEnterpriseCode() {
		return this.enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode == null ? null : enterpriseCode.trim();
	}

	public Date getSkillLicenseExpireTime() {
		return this.skillLicenseExpireTime;
	}

	public void setSkillLicenseExpireTime(Date skillLicenseExpireTime) {
		this.skillLicenseExpireTime = skillLicenseExpireTime;
	}

	public Byte getVendorType() {
		return this.vendorType;
	}

	public void setVendorType(Byte vendorType) {
		this.vendorType = vendorType;
	}

	public String getIdentityCardImg() {
		return this.identityCardImg;
	}

	public void setIdentityCardImg(String identityCardImg) {
		this.identityCardImg = identityCardImg == null ? null : identityCardImg.trim();
	}

	public String getQualityCertImg() {
		return this.qualityCertImg;
	}

	public void setQualityCertImg(String qualityCertImg) {
		this.qualityCertImg = qualityCertImg == null ? null : qualityCertImg.trim();
	}

	public String getRoadTransPermitImg() {
		return this.roadTransPermitImg;
	}

	public void setRoadTransPermitImg(String roadTransPermitImg) {
		this.roadTransPermitImg = roadTransPermitImg == null ? null : roadTransPermitImg.trim();
	}

	public String getBusinessLicenseImg() {
		return this.businessLicenseImg;
	}

	public void setBusinessLicenseImg(String businessLicenseImg) {
		this.businessLicenseImg = businessLicenseImg == null ? null : businessLicenseImg.trim();
	}

	public String getVendorAddress() {
		return this.vendorAddress;
	}

	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress == null ? null : vendorAddress.trim();
	}

	public Byte getIsShowYourPrice() {
		return this.isShowYourPrice;
	}

	public void setIsShowYourPrice(Byte isShowYourPrice) {
		this.isShowYourPrice = isShowYourPrice;
	}

	public Byte getIsSyncAsDriver() {
		return this.isSyncAsDriver;
	}

	public void setIsSyncAsDriver(Byte isSyncAsDriver) {
		this.isSyncAsDriver = isSyncAsDriver;
	}

	public Byte getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(Byte isEnable) {
		this.isEnable = isEnable;
	}

	public Byte getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Byte isDelete) {
		this.isDelete = isDelete;
	}

	public Byte getIsOpen() {
		return this.isOpen;
	}

	public void setIsOpen(Byte isOpen) {
		this.isOpen = isOpen;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getLastUpdateUserId() {
		return this.lastUpdateUserId;
	}

	public void setLastUpdateUserId(Integer lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}

}
