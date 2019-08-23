package com.juma.tgm.customer.domain;

import com.juma.tgm.base.domain.BaseDomain;
import me.about.poi.ExcelColumn;

import java.io.Serializable;

/**
 * @author vencent.lu
 *
 */
public class TruckCustomer extends BaseDomain implements Serializable {

    private static final long serialVersionUID = 3246694859235654303L;
    private Integer truckCustomerId;
    private Integer status;
    @ExcelColumn(name = "姓名", width = 30)
    private String nickname;
    private String headPortrait;
    @ExcelColumn(name = "联系电话", width = 30)
    private String contactPhone;
    @ExcelColumn(name = "身份证号", width = 30)
    private String identityCardNo;
    private String identityCardPhotoUrl;
    private String remark;
    private Integer ecoUserId;
    private Integer classId;
    private String openId;
    private String addressDetail;

    // 查询冗余
    private Integer userId;
    private String regionCode;
    private String areaCode;
    private String tenantCode;
    private Integer tenantId;

    // 显示冗余
    private String regionName;
    private String areaName;
    private String tenantName;

    public Integer getTruckCustomerId() {
        return truckCustomerId;
    }

    public void setTruckCustomerId(Integer truckCustomerId) {
        this.truckCustomerId = truckCustomerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getIdentityCardNo() {
        return identityCardNo;
    }

    public void setIdentityCardNo(String identityCardNo) {
        this.identityCardNo = identityCardNo;
    }

    public String getIdentityCardPhotoUrl() {
        return identityCardPhotoUrl;
    }

    public void setIdentityCardPhotoUrl(String identityCardPhotoUrl) {
        this.identityCardPhotoUrl = identityCardPhotoUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getEcoUserId() {
        return ecoUserId;
    }

    public void setEcoUserId(Integer ecoUserId) {
        this.ecoUserId = ecoUserId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}