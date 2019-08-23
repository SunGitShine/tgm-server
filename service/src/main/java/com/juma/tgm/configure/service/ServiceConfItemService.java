package com.juma.tgm.configure.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.configure.domain.ServiceConfItem;

/**
 * @ClassName ServiceConfItemService.java
 * @Description 城市管理扩展
 * @author Libin.Wei
 * @Date 2017年11月29日 下午5:19:24
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface ServiceConfItemService {

    /**
     * 
     * 分页列表
     * 
     * @author Libin.Wei
     * @Date 2017年11月29日 下午5:20:40
     * @param pageCondition
     * @param loginUser
     * @return
     */
    Page<ServiceConfItem> search(PageCondition pageCondition, LoginUser loginUser);

    /**
     * 
     * 根据条件查询
     * 
     * @author Libin.Wei
     * @Date 2017年11月30日 上午9:29:20
     * @param serviceConfId
     * @param fenceId
     * @return
     */
    ServiceConfItem findByServiceConfAndFence(Integer serviceConfId, Integer fenceId);

    /**
     * 
     * 根据条件查询
     * 
     * @author Libin.Wei
     * @Date 2017年11月30日 上午9:29:20
     * @param serviceConfId
     * @param loginUser
     * @return
     */
    List<ServiceConfItem> listByServiceConf(Integer serviceConfId, Integer fenceType);

    /**
     * 
     * 添加
     * 
     * @author Libin.Wei
     * @Date 2017年11月29日 下午5:22:22
     * @param fenceId
     * @param serviceConfId
     * @param loginUser
     * @throws BusinessException
     */
    void insert(Integer fenceId, Integer serviceConfId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 修改
     * 
     * @author Libin.Wei
     * @Date 2017年11月29日 下午5:23:37
     * @param serviceConfItemId
     *            扩展表主键
     * @param loginUser
     * @throws BusinessException
     */
    void update(Integer serviceConfItemId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 删除
     * 
     * @author Libin.Wei
     * @Date 2017年11月29日 下午5:26:32
     * @param serviceConfItemId
     *            扩展表主键
     * @param loginUser
     * @throws BusinessException
     */
    void delete(Integer serviceConfItemId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 回调
     * 
     * @author Libin.Wei
     * @Date 2017年11月29日 下午7:02:05
     * @param serviceConfId
     * @param fenceId
     * @param loginUser
     * @throws BusinessException
     */
    void doCallBack(Integer serviceConfId, Integer fenceId, LoginUser loginUser) throws BusinessException;
}
