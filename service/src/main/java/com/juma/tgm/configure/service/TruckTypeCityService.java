package com.juma.tgm.configure.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.configure.domain.TruckTypeCity;

/**
 * 
 * @ClassName TruckTypeCityService.java 车型城市
 * @author Libin.Wei
 * @Date 2017年11月14日 下午6:58:59
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */
public interface TruckTypeCityService {

    /**
     * 
     * 列表:不分页
     * 
     * @author Libin.Wei
     * @Date 2017年4月14日 下午1:52:22
     * @param pageCondition
     *            条件
     * @param loginUser
     *            当前登录人信息，数据结构来源于用户中心
     * @return
     */
    Page<TruckTypeCity> search(PageCondition pageCondition, LoginUser loginUser);

    /**
     * 
     * 根据Id返回信息
     * 
     * @author Libin.Wei
     * @Date 2017年4月14日 下午3:12:16
     * @param truckTypeId
     *            车型ID
     * @return TruckTypeCity
     */
    TruckTypeCity getTruckTypeCity(Integer truckTypeCityId);

    /**
     * 
     * 根据城市编码返回基础信息
     * 
     * @author Libin.Wei
     * @Date 2017年11月27日 下午5:47:32
     * @param regionCode
     * @param isEnable
     *            true:启用 ， false:禁用
     * @param loginUser
     * @return
     */
    List<TruckTypeCity> listByRegionCode(String regionCode, boolean isEnable, LoginUser loginUser);

    /**
     * 
     * 根据车型ID返回基础信息
     * 
     * @author Libin.Wei
     * @Date 2017年11月27日 下午5:47:40
     * @param truckTypeId
     * @param loginUser
     * @return
     */
    List<TruckTypeCity> listByTruckTypeId(Integer truckTypeId, LoginUser loginUser);

    /**
     * 
     * 添加新的车型城市
     * 
     * @author Libin.Wei
     * @Date 2017年4月14日 下午3:15:16
     * @param truckTypeCity
     *            车型城市信息
     * @param loginUser
     *            当前登录人信息，数据结构来源于用户中心
     * @throws BusinessException
     */
    void insert(TruckTypeCity truckTypeCity, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 修改车型城市信息
     * 
     * @author Libin.Wei
     * @Date 2017年4月14日 下午3:33:55
     * @param truckTypeCity
     *            车型城市信息
     * @param loginUser
     *            当前登录人信息，数据结构来源于用户中心
     * @throws BusinessException
     */
    void update(TruckTypeCity truckTypeCity, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 启用
     * 
     * @author Libin.Wei
     * @Date 2017年4月14日 下午3:42:39
     * @param truckTypeCityId
     *            城市车型ID
     * @param loginEmployee
     *            当前登录人信息，数据结构来源于用户中心
     * @throws BusinessException
     */
    void updateToEnable(Integer truckTypeCityId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 停用
     * 
     * @author Libin.Wei
     * @Date 2017年4月14日 下午3:42:51
     * @param truckTypeCityId
     *            城市车型ID
     * @param loginEmployee
     *            当前登录人信息，数据结构来源于用户中心
     * @throws BusinessException
     */
    void updateToDisable(Integer truckTypeCityId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 上移
     * 
     * @author Libin.Wei
     * @Date 2018年1月29日 下午3:28:28
     * @param truckTypeCityId
     * @param loginUser
     * @throws BusinessException
     */
    void updateToMoveUp(Integer truckTypeCityId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 下移
     * 
     * @author Libin.Wei
     * @Date 2018年1月29日 下午3:28:51
     * @param truckTypeCityId
     * @param loginUser
     * @throws BusinessException
     */
    void updateToMoveDown(Integer truckTypeCityId, LoginUser loginUser) throws BusinessException;
}
