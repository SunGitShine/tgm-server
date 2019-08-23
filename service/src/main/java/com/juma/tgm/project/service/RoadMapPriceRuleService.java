package com.juma.tgm.project.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.project.domain.RoadMapPriceRule;
import com.juma.tgm.project.domain.ValuationWay;
import com.juma.tgm.project.vo.RoadMapPriceRuleVo;

/**
 * @ClassName RoadMapPriceModelService.java
 * @Description 路线价格信息
 * @author Libin.Wei
 * @Date 2018年9月28日 上午10:16:27
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface RoadMapPriceRuleService {

    /**
     * 
     * 根据主键获取
     * 
     * @author Libin.Wei
     * @Date 2018年9月28日 上午10:22:25
     * @param roadMapPriceRuleId
     * @return
     */
    RoadMapPriceRule getRoadMapPriceModel(Integer roadMapPriceRuleId);

    /**
     * 
     * 添加
     * 
     * @author Libin.Wei
     * @Date 2018年9月28日 上午10:22:42
     * @param roadMapPriceRule
     * @param loginUser
     * @throws BusinessException
     */
    void insert(RoadMapPriceRule roadMapPriceRule, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 批量添加
     * 
     * @author Libin.Wei
     * @Date 2018年9月28日 下午2:39:28
     * @param roadMapId
     * @param listRoadMapPriceRule
     * @param loginUser
     * @throws BusinessException
     */
    void batchInsert(Integer roadMapId, List<RoadMapPriceRule> listRoadMapPriceRule, LoginUser loginUser)
            throws BusinessException;

    /**
     * 
     * 批量修改（全删全插）
     * 
     * @author Libin.Wei
     * @Date 2018年9月28日 上午10:22:52
     * @param roadMapId
     * @param listRoadMapPriceRule
     * @param loginUser
     * @throws BusinessException
     */
    void batchUpdate(Integer roadMapId, List<RoadMapPriceRule> listRoadMapPriceRule, LoginUser loginUser)
            throws BusinessException;

    /**
     * 
     * 根据路线ID删除
     * 
     * @author Libin.Wei
     * @Date 2018年9月28日 上午10:23:01
     * @param roadMapPriceRuleId
     * @param loginUser
     * @throws BusinessException
     */
    void delete(Integer roadMapPriceRuleId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 根据路线ID删除
     * 
     * @author Libin.Wei
     * @Date 2018年9月28日 上午10:23:01
     * @param roadMapId
     * @param loginUser
     * @throws BusinessException
     */
    void deleteByRoadMapId(Integer roadMapId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 根据路线ID获取
     * 
     * @author Libin.Wei
     * @Date 2018年9月28日 上午10:23:13
     * @param roadMapId
     * @return
     */
    List<RoadMapPriceRule> listByRoadMapId(Integer roadMapId);

    /**
     * 
     * 根据路线ID和车型ID获取
     * 
     * @author Libin.Wei
     * @Date 2018年9月28日 上午10:23:13
     * @param roadMapId
     * @return
     */
    RoadMapPriceRule findByRoadMapIdAndTypeId(Integer roadMapId, Integer truckTypeId);

    /**
     * 
     * 计价方式转为list
     * 
     * @author Libin.Wei
     * @Date 2018年9月29日 下午4:47:10
     * @param json
     * @return
     */
    List<ValuationWay> buildValuationWays(String json);

    /**
     * 组装名称
     */
    List<RoadMapPriceRuleVo> buidBeanName(List<RoadMapPriceRule> roadMapPriceRules,LoginUser loginUser);

}
