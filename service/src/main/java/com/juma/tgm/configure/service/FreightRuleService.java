package com.juma.tgm.configure.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.configure.domain.FreightRule;
import com.juma.tgm.project.domain.ValuationWay;

/**
 * @ClassName LandingDistributionFreightService.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年11月16日 下午2:49:43
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface FreightRuleService {

    /**
     * 
     * 分页列表
     * 
     * @author Libin.Wei
     * @Date 2017年11月16日 下午2:51:10
     * @param pageCondition
     * @param loginUser
     * @return
     */
    Page<FreightRule> search(PageCondition pageCondition, LoginUser loginUser);

    /**
     * 
     * 根据ID获取
     * 
     * @author Libin.Wei
     * @Date 2017年11月16日 下午2:52:01
     * @param freightRuleId
     * @return
     */
    FreightRule getFreightRule(Integer freightRuleId);

    /**
     * 
     * 添加
     * 
     * @author Libin.Wei
     * @Date 2017年11月16日 下午2:55:35
     * @param freightRule
     * @param loginUser
     * @throws BusinessException
     */
    void insert(FreightRule freightRule, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 修改
     * 
     * @author Libin.Wei
     * @Date 2017年11月16日 下午2:55:35
     * @param freightRule
     * @param loginUser
     * @throws BusinessException
     */
    void update(FreightRule freightRule, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 启用
     * 
     * @author Libin.Wei
     * @Date 2017年11月16日 下午2:55:35
     * @param freightRuleId
     * @param loginUser
     * @throws BusinessException
     */
    void updateToEnable(Integer freighRuletId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 禁用
     * 
     * @author Libin.Wei
     * @Date 2017年11月16日 下午2:55:35
     * @param freightRuleId
     * @param loginUser
     * @throws BusinessException
     */
    void updateToDisable(Integer freightRuleId, LoginUser loginUser) throws BusinessException;
    
    
    /**
     * 
     * @Title: findByRegionCode   
     * @Description: 零担城市计价规则
     * @param: @return      
     * @return: FreightRule      
     * @throws
     */
    FreightRule findByRegionCode(String regionCode,LoginUser loginUser);
}
