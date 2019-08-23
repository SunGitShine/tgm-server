package com.juma.tgm.customer.domain.vo;

import java.io.Serializable;

/**
 * @ClassName BuidingVo.java
 * @Description 绑定用户VO
 * @author Libin.Wei
 * @Date 2017年1月6日 下午1:34:12
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class BindVo implements Serializable {

    private static final long serialVersionUID = 6523677451432583544L;
    private String mobileNumber;
    private String openId;
    private Integer ecoUserId;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getEcoUserId() {
        return ecoUserId;
    }

    public void setEcoUserId(Integer ecoUserId) {
        this.ecoUserId = ecoUserId;
    }
}
