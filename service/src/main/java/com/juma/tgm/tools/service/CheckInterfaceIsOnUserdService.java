package com.juma.tgm.tools.service;

import com.giants.common.exception.BusinessException;

/**
 * @ClassName CheckInterfaceIsOnUserdService.java
 * @Description 检查接口，工具类，不对外提供功能
 * @author Libin.Wei
 * @Date 2018年10月25日 上午11:41:56
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface CheckInterfaceIsOnUserdService {

    /**
     * 
     * 检查废弃方法其它是不是在使用，通过用户中心配置是否允许这个方法使用，若用户中心长时间配置不允许使用且没有其它系统报异常，则证明可以删除此方法
     * 
     * @author Libin.Wei
     * @Date 2018年10月25日 下午1:53:44
     * @param methodName
     *            方法名
     * @throws BusinessException
     */
    void checkMethodIsUsedThenThrewException(String methodName) throws BusinessException;

    /**
     * 
     * 检查废弃方法其它是不是在使用，通过用户中心配置是否允许这个方法使用，若用户中心长时间配置不允许使用且没有发送邮件，则证明可以删除此方法
     * 
     * @author Libin.Wei
     * @Date 2018年10月25日 下午1:53:44
     * @param methodName
     *            方法名
     * @throws BusinessException
     */
    void checkMethodIsUsedThenPushEmail(final String methodName) throws BusinessException;
}
