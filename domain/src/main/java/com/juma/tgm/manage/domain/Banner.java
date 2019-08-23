package com.juma.tgm.manage.domain;

import com.juma.tgm.base.domain.BaseDomain;

/**
 * banner - Banner
 *
 * @author 2016-05-09
 * @version 1.0
 */
public class Banner extends BaseDomain {

    private static final long serialVersionUID = 4800985920835340528L;
    private Integer bannerId;
    private String bannerTypeKey;
    private String title;
    private String picUrl;
    private String linkUrl;
    private Integer orderNo;

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerTypeKey() {
        return bannerTypeKey;
    }

    public void setBannerTypeKey(String bannerTypeKey) {
        this.bannerTypeKey = bannerTypeKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

}