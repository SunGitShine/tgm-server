package com.juma.tgm.manage.domain;

import com.juma.tgm.base.domain.BaseDomain;

/**
 * activity_region - 活动地区
 * 
 * @author 2016-05-09
 * @version 1.0
 */
public class ActivityRegion extends BaseDomain {

    private static final long serialVersionUID = 4569944090438300905L;
    private Integer activityRegionId;
    private Integer activityId;
    private String regionCode;

    public Integer getActivityRegionId() {
        return activityRegionId;
    }

    public void setActivityRegionId(Integer activityRegionId) {
        this.activityRegionId = activityRegionId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

}