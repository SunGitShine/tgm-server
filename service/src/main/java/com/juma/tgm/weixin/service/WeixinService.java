package com.juma.tgm.weixin.service;

/**
 * @ClassName WeixinService.java
 * @Description 微信服务
 * @author Libin.Wei
 * @Date 2017年1月4日 下午3:37:09
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Deprecated
public interface WeixinService {

    /**
     * 
     * @Description 根据code获取openId
     * @author Libin.Wei
     * @Date 2017年1月4日 下午4:00:24
     * @param code
     * @return String
     */
    String getCustomerOpenId(String code);
}
