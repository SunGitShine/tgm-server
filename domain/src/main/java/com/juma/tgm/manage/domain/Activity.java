package com.juma.tgm.manage.domain;

import java.util.Date;

import com.juma.tgm.base.domain.BaseDomain;

/**
 * activity - 活动
 * 
 * @author 2016-05-09
 * @version 1.0
 */
public class Activity extends BaseDomain {

    private static final long serialVersionUID = -4857893971819559915L;
    private Integer activityId;
    private String activityName;
    private String activityPicUrl;
    private String activityDescription;
    private String activityLinkUrl;
    private Integer status;
    private Date beginTime;
    private Date endTime;
    private Date pushTime;
    private Integer publishUserId;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityPicUrl() {
        return activityPicUrl;
    }

    public void setActivityPicUrl(String activityPicUrl) {
        this.activityPicUrl = activityPicUrl;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public String getActivityLinkUrl() {
        return activityLinkUrl;
    }

    public void setActivityLinkUrl(String activityLinkUrl) {
        this.activityLinkUrl = activityLinkUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public Integer getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(Integer publishUserId) {
        this.publishUserId = publishUserId;
    }

}