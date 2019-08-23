package com.juma.tgm.project.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.project.domain.RoadMapSrcAdress;

/**
 * @ClassName RoadMapSrcAdressService.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年9月28日 上午10:16:59
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface RoadMapSrcAdressService {

    /**
     * 
     * 根据主键获取
     * 
     * @author Libin.Wei
     * @Date 2018年9月28日 上午10:22:25
     * @param roadMapSrcAdressId
     * @return
     */
    RoadMapSrcAdress getRoadMapSrcAdress(Integer roadMapSrcAdressId);

    /**
     * 
     * 添加
     * 
     * @author Libin.Wei
     * @Date 2018年9月28日 上午10:22:42
     * @param roadMapSrcAdress
     * @param loginUser
     * @throws BusinessException
     */
    void insert(RoadMapSrcAdress roadMapSrcAdress, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 批量添加
     * 
     * @author Libin.Wei
     * @Date 2018年9月28日 下午2:39:28
     * @param roadMapId
     * @param listRoadMapSrcAdress
     * @param loginUser
     * @throws BusinessException
     */
    void batchInsert(Integer roadMapId, List<RoadMapSrcAdress> listRoadMapSrcAdress, LoginUser loginUser)
            throws BusinessException;

    /**
     * 
     * 批量修改（全删全插）
     * 
     * @author Libin.Wei
     * @Date 2018年9月28日 上午10:22:52
     * @param roadMapId
     * @param listRoadMapSrcAdress
     * @param loginUser
     * @throws BusinessException
     */
    void batchUpdate(Integer roadMapId, List<RoadMapSrcAdress> listRoadMapSrcAdress, LoginUser loginUser)
            throws BusinessException;

    /**
     * 
     * 根据路线ID删除
     * 
     * @author Libin.Wei
     * @Date 2018年9月28日 上午10:23:01
     * @param roadMapSrcAdressId
     * @param loginUser
     * @throws BusinessException
     */
    void delete(Integer roadMapSrcAdressId, LoginUser loginUser) throws BusinessException;

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
    List<RoadMapSrcAdress> listByRoadMapId(Integer roadMapId);
}
