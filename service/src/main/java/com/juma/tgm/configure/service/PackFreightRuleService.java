package com.juma.tgm.configure.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.configure.domain.PackFreightRule;

/**
 * @ClassName PackFreightRuleService.java
 * @Description 整车计价规则
 * @author Libin.Wei
 * @Date 2017年11月27日 上午11:41:27
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Deprecated
public interface PackFreightRuleService {

    PackFreightRule findByRegionAndTruckType(String regionCode, Integer truckTypeId, LoginUser loginUser);

    /**
     * 
     * 分页列表
     * 
     * @author Libin.Wei
     * @Date 2017年11月27日 下午2:12:07
     * @param pageCondition
     * @param loginUser
     * @return
     */
    Page<PackFreightRule> search(PageCondition pageCondition, LoginUser loginUser);

    /**
     * 
     * 根据主键查询
     * 
     * @author Libin.Wei
     * @Date 2017年11月27日 下午2:14:19
     * @param packFreightRuleId
     * @return
     */
    PackFreightRule getPackFreightRule(Integer packFreightRuleId);

    /**
     * 
     * 添加
     * 
     * @author Libin.Wei
     * @Date 2017年11月27日 下午2:15:19
     * @param packFreightRule
     * @param loginUser
     * @throws BusinessException
     */
    void insert(PackFreightRule packFreightRule, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 修改
     * 
     * @author Libin.Wei
     * @Date 2017年11月27日 下午2:15:39
     * @param packFreightRule
     * @param loginUser
     * @throws BusinessException
     */
    void update(PackFreightRule packFreightRule, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 启用
     * 
     * @author Libin.Wei
     * @Date 2017年11月16日 下午2:55:35
     * @param packFreightRuleId
     * @param loginUser
     * @throws BusinessException
     */
    void updateToEnable(Integer packFreightRuleId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 禁用
     * 
     * @author Libin.Wei
     * @Date 2017年11月16日 下午2:55:35
     * @param packFreightRuleId
     * @param loginUser
     * @throws BusinessException
     */
    void updateToDisable(Integer packFreightRuleId, LoginUser loginUser) throws BusinessException;
}
