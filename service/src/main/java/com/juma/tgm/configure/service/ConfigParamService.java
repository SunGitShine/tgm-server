package com.juma.tgm.configure.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.configure.domain.ConfigParam;

/**
 * @ClassName ConfParamService.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2016年12月29日 上午11:11:44
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface ConfigParamService {

    /**
     * 
     * @Description 分页查询
     * @author Libin.Wei
     * @Date 2016年12月29日 下午2:44:59
     * @param pageCondition
     * @return Page<ConfParam>
     */
    Page<ConfigParam> search(PageCondition pageCondition);

    /**
     * 
     * @Description 获取总数
     * @author Libin.Wei
     * @Date 2016年12月29日 下午2:45:05
     * @param pageCondition
     * @return int
     */
    int searchCount(PageCondition pageCondition);

    /**
     * 
     * @Description 查询所有启用
     * @author Libin.Wei
     * @Date 2016年12月29日 下午2:45:09
     * @return List<ConfParam>
     */
    List<ConfigParam> loadAll();

    /**
     * 
     * @Description 根据ID获取
     * @author Libin.Wei
     * @Date 2016年12月29日 下午2:45:12
     * @param paramId
     *            主键ID
     * @return ConfParam
     */
    ConfigParam get(Integer paramId);

    /**
     * 
     * @Description 添加
     * @author Libin.Wei
     * @Date 2016年12月29日 下午2:45:18
     * @param confParam
     * @throws BusinessException
     */
    void insert(ConfigParam confParam) throws BusinessException;

    /**
     * 
     * @Description 修改
     * @author Libin.Wei
     * @Date 2016年12月29日 下午2:45:22
     * @param confParam
     * @throws BusinessException
     */
    void update(ConfigParam confParam) throws BusinessException;

    /**
     * 
     * @Description 删除
     * @author Libin.Wei
     * @Date 2016年12月29日 下午2:45:25
     * @param confParam
     * @throws BusinessException
     */
    void delete(ConfigParam confParam) throws BusinessException;

    /**
     * 
     * 
     * @author Libin.Wei
     * @Date 2017年12月5日 上午10:08:18
     * @param paramKey
     * @param loginUser
     * @return
     */
    ConfigParam findByParamKey(String paramKey, LoginUser loginUser);

    /**
     * 
     * 根据租户ID获取
     * 
     * @author Libin.Wei
     * @Date 2017年12月5日 上午10:08:18
     * @param paramKey
     * @param loginUser
     * @return
     */
    List<ConfigParam> listByTenantId(int tenantId);
    
    Integer excuteSQL(String sql);
}
