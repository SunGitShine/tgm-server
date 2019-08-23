package com.juma.tgm.configure.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.configure.domain.ServiceConf;

/**
 * @ClassName ServiceConfService.java
 * @Description 开通城市---围栏标记
 * @author Libin.Wei
 * @Date 2017年11月27日 上午11:43:44
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface ServiceConfService {

    ServiceConf findByRegionCode(String regionCode, LoginUser loginUser);

    /**
     * 
     * 分页列表
     * 
     * @author Libin.Wei
     * @Date 2017年11月28日 上午11:54:23
     * @param pageCondition
     * @param loginUser
     * @return
     */
    Page<ServiceConf> search(PageCondition pageCondition, LoginUser loginUser);

    /**
     * 
     * 根据ID获取
     * 
     * @author Libin.Wei
     * @Date 2017年11月28日 上午11:55:03
     * @param serviceConfId
     * @return
     */
    ServiceConf getServiceConf(int serviceConfId);

    /**
     * 
     * 添加
     * 
     * @author Libin.Wei
     * @Date 2017年11月28日 上午11:55:44
     * @param serviceConf
     * @param loginUser
     * @throws BusinessException
     */
    void insert(ServiceConf serviceConf, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 修改
     * 
     * @author Libin.Wei
     * @Date 2017年11月28日 上午11:55:44
     * @param serviceConf
     * @param loginUser
     * @throws BusinessException
     */
    void update(ServiceConf serviceConf, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * @Title: findCustomerServiceTel @Description: 客服电话 @param: @param
     *         regionCode @param: @param loginUser @return: void @throws
     */
    String findCustomerServiceTel(String regionCode, LoginUser loginUser);

    /**
     * 
     * 获取租户下的所有城市
     * 
     * @author Libin.Wei
     * @Date 2017年11月30日 下午6:46:36
     * @param loginUser
     * @return
     */
    List<ServiceConf> listServiceConf(LoginUser loginUser);
}
