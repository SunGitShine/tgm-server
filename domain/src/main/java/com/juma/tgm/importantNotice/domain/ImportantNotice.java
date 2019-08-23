package com.juma.tgm.importantNotice.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName ImportantNotice.java 重要通知
 * @author Libin.Wei
 * @Date 2017年8月9日 上午11:03:31
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class ImportantNotice implements Serializable {
    private static final long serialVersionUID = -8031581506732999035L;
    private Integer noticeId;
    private Integer noticeApplication;
    private String noticeTitle;
    private String noticeContent;
    private Integer noticePersonnelType;
    private String noticePersonnelUserIds;
    private Date effectiveTime;
    private Date expiryTime;
    private Integer tenantId;
    private String tenantCode;
    private String areaCode;
    private String regionCode;
    private Integer noticeUserNo;
    private Boolean isMustRead;
    private String changeSign;
    private Date createTime;
    private Integer createUserId;
    private Boolean isDelete;
    private Integer lastUpdateUserId;
    private Date lastUpdateTime;

    // 显示冗余
    // 未阅读人数
    private Integer hasNotReadUserNo = 0;
    private String noticeUserNames;

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public Integer getNoticeApplication() {
        return noticeApplication;
    }

    public void setNoticeApplication(Integer noticeApplication) {
        this.noticeApplication = noticeApplication;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Integer getNoticePersonnelType() {
        return noticePersonnelType;
    }

    public void setNoticePersonnelType(Integer noticePersonnelType) {
        this.noticePersonnelType = noticePersonnelType;
    }

    public String getNoticePersonnelUserIds() {
        return noticePersonnelUserIds;
    }

    public void setNoticePersonnelUserIds(String noticePersonnelUserIds) {
        this.noticePersonnelUserIds = noticePersonnelUserIds;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public Integer getNoticeUserNo() {
        return noticeUserNo;
    }

    public void setNoticeUserNo(Integer noticeUserNo) {
        this.noticeUserNo = noticeUserNo;
    }

    public Boolean getIsMustRead() {
        return isMustRead;
    }

    public void setIsMustRead(Boolean isMustRead) {
        this.isMustRead = isMustRead;
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

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
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

    public String getChangeSign() {
        return changeSign;
    }

    public void setChangeSign(String changeSign) {
        this.changeSign = changeSign;
    }

    public Integer getHasNotReadUserNo() {
        return hasNotReadUserNo;
    }

    public void setHasNotReadUserNo(Integer hasNotReadUserNo) {
        this.hasNotReadUserNo = hasNotReadUserNo;
    }

    public String getNoticeUserNames() {
        return noticeUserNames;
    }

    public void setNoticeUserNames(String noticeUserNames) {
        this.noticeUserNames = noticeUserNames;
    }
}