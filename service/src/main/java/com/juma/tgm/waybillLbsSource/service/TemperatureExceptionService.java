package com.juma.tgm.waybillLbsSource.service;

import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybillLbsSource.domain.TemperatureException;

/**
 * @ClassName TemperatureExceptionService.java
 * @Description 温度异常
 * @author Libin.Wei
 * @Date 2018年5月22日 下午4:54:14
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface TemperatureExceptionService {

    /**
     * 
     * 列表
     * 
     * @author Libin.Wei
     * @Date 2018年5月22日 下午4:55:35
     * @param pageCondition
     * @param loginUser
     * @return
     */
    Page<TemperatureException> search(PageCondition pageCondition, LoginUser loginUser);
}
