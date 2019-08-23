package com.juma.tgm.mq.domain;

import java.io.Serializable;

/**
 * @ClassName UserOperateMQ.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年9月6日 上午11:10:41
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class UserOperateMQ implements Serializable {

    private static final long serialVersionUID = -3457610904779481240L;
    private Integer userId;
    private String operate;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

}
