package com.juma.tgm.configure.dao;

import java.util.List;

import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.configure.domain.ConfigParamOption;

/**
 * @ClassName ConfParamDao.java
 * @Description 参数配置项
 * @author Libin.Wei
 * @Date 2016年12月29日 下午2:14:04
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface ConfigParamOptionDao extends MybatisDao<ConfigParamOption> {

    /**
     * 
     * @Description 根据ID获取
     * @author Libin.Wei
     * @Date 2016年12月29日 下午2:45:12
     * @param paramId
     *            关联ConfParam表的主键ID
     * @return ConfParamOption
     */
    List<ConfigParamOption> findByParamId(ConfigParamOption configParamOption);

    /**
     * 
     * @Description 根据paramId删除
     * @author Libin.Wei
     * @Date 2016年12月29日 下午3:04:29
     * @param paramId
     */
    void deleteByParamId(Integer paramId);

    /**
     * 
     * @Description 根据userId与paramId删除
     * @author Libin.Wei
     * @Date 2017年2月27日 上午11:00:38
     * @param configParamOption
     */
    void deleteByUserIdAndParamId(ConfigParamOption configParamOption);

    /**
     * 
     * @Description 根据userId修改
     * @author Libin.Wei
     * @Date 2017年2月27日 下午3:35:12
     * @param configParamOption
     */
    void updateByUserId(ConfigParamOption configParamOption);
}
