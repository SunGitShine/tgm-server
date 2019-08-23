package com.juma.tgm.operateLog.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.operateLog.domain.OperateLog;
import com.juma.tgm.operateLog.vo.OperateLogQuery;

/**
 * @ClassName OperateLogService.java
 * @Description 操作记录公共类
 * @author Libin.Wei
 * @Date 2018年10月30日 上午10:52:23
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface OperateLogService {

    /**
     * 
     * 分页获取
     * 
     * @author Libin.Wei
     * @Date 2018年10月30日 上午11:23:29
     * @param pageCondition
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Page<OperateLogQuery> search(PageCondition pageCondition, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 根据主键获取
     * 
     * @author Libin.Wei
     * @Date 2018年10月31日 上午11:19:05
     * @param operateLogId
     * @return
     * @throws BusinessException
     */
    OperateLogQuery getOperateLogQuery(Integer operateLogId) throws BusinessException;

    /**
     * 
     * 添加
     * 
     * @author Libin.Wei
     * @Date 2018年10月30日 上午11:24:20
     * @param operateLog
     * @param loginUser
     */
    void insert(OperateLog operateLog, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 异步添加:异步需要在dubbo消费端配置，若没有配置，则仍是同步方法
     * 
     * @author Libin.Wei
     * @Date 2018年11月21日 下午4:29:39
     * @param operateLog
     * @param loginUser
     * @throws BusinessException
     */
    void insertByDubboAsync(OperateLog operateLog, LoginUser loginUser) throws BusinessException;
}
