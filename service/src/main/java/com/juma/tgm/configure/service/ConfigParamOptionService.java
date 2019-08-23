package com.juma.tgm.configure.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.configure.domain.ConfigParam.ParamKey;
import com.juma.tgm.configure.domain.ConfigParamOption;

/**
 * @ClassName ConfParamService.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2016年12月29日 上午11:11:44
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface ConfigParamOptionService {

    /**
     * 
     * @Description 分页查询
     * @author Libin.Wei
     * @Date 2016年12月29日 下午2:44:59
     * @param pageCondition
     * @return Page<ConfigParamOptionBo>
     */
    Page<ConfigParamOption> search(PageCondition pageCondition, LoginUser loginUser);

    /**
     * 
     * @Description 根据ID获取
     * @author Libin.Wei
     * @Date 2016年12月29日 下午2:45:12
     * @param optionId
     *            主键ID
     * @return ConfParamOption
     */
    ConfigParamOption getConfigParamOption(Integer optionId);

    /**
     * 
     * @Description 查询所有启用
     * @author Libin.Wei
     * @Date 2016年12月29日 下午2:45:09
     * @return List<ConfParamOption>
     */
    List<ConfigParamOption> loadAll();

    /**
     * 
     * @Description 根据ID获取
     * @author Libin.Wei
     * @Date 2016年12月29日 下午2:45:12
     * @param paramId
     *            关联ConfParam表的主键ID
     * @return ConfParamOption
     */
    List<ConfigParamOption> findByParamId(Integer paramId, LoginUser loginUser);

    /**
     * 
     * @Description 添加
     * @author Libin.Wei
     * @Date 2016年12月29日 下午2:45:18
     * @param confParamOption
     * @throws BusinessException
     */
    void insert(ConfigParamOption confParamOption, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 
     * @Description 修改
     * @author Libin.Wei
     * @Date 2016年12月29日 下午2:45:22
     * @param confParamOption
     * @throws BusinessException
     */
    void update(ConfigParamOption confParamOption, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 
     * @Description 删除
     * @author Libin.Wei
     * @Date 2016年12月29日 下午2:45:25
     * @param confParamOption
     * @throws BusinessException
     */
    void delete(ConfigParamOption confParamOption, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 
     * @Description 根据paramId删除
     * @author Libin.Wei
     * @Date 2016年12月29日 下午3:04:29
     * @param paramId
     */
    void deleteByParamId(Integer paramId, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 
     * @Description 启用
     * @author Libin.Wei
     * @Date 2016年12月29日 下午2:45:25
     * @param optionId
     * @throws BusinessException
     */
    void enable(Integer optionId, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 
     * @Description 禁用
     * @author Libin.Wei
     * @Date 2016年12月29日 下午2:45:25
     * @param optionId
     * @throws BusinessException
     */
    void disable(Integer optionId, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 
     * @Description 根据业务区域编码集合获取需要发送的人员信息
     * @author Libin.Wei
     * @Date 2016年12月29日 下午3:04:29
     * @param areaCodeList
     *            业务区域编码集合
     * @param paramKey
     *            枚举，通知的业务
     * @return List<ConfigParamOption>
     */
    List<ConfigParamOption> listPushParamOptionBy(String areaCode, ParamKey paramKey, LoginUser loginUser);
}
