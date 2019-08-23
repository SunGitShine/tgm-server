package com.juma.tgm.tools.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.giants.common.SpringContextHelper;
import com.giants.common.exception.BusinessException;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.service.ConfParamService;
import com.juma.message.gateway.service.MessageServiceProvider;
import com.juma.tgm.tools.service.CheckInterfaceIsOnUserdService;

/**
 * @ClassName CheckInterfaceIsOnUserdServiceImpl.java
 * @Description 检查接口，工具类，不对外提供功能
 * @author Libin.Wei
 * @Date 2018年10月25日 上午11:43:00
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Service
public class CheckInterfaceIsOnUserdServiceImpl implements CheckInterfaceIsOnUserdService {

    @Resource
    private ConfParamService confParamService;
    @Resource
    private MessageServiceProvider messageServiceProvider;

    private final static String CHECK_METHOD_IS_USED_KEY = "CHECK_METHOD_IS_USED_KEY";

    private final static String SCENE_KEY = "CHECK_METHOD_IS_USED_SCENE_KEY";

    private final static String IS_CHECK_METHOD__KEY = "YES";

    @Override
    public void checkMethodIsUsedThenThrewException(String methodName) throws BusinessException {
        if (StringUtils.isBlank(methodName)) {
            return;
        }

        List<ConfParamOption> list = null;
        try {
            list = confParamService.findParamOptions(CHECK_METHOD_IS_USED_KEY);
        } catch (Exception e) {
        }

        // 没有配置信息则不进行判断
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        for (ConfParamOption c : list) {
            if (StringUtils.isBlank(c.getOptionName()) || StringUtils.isBlank(c.getOptionValue())) {
                continue;
            }

            if (c.getOptionName().equals(methodName) && c.getOptionValue().equals(IS_CHECK_METHOD__KEY)) {
                throw new BusinessException("theMethodNotAllowUsed", "errors.theMethodNotAllowUsed");
            }
        }
    }

    @Override
    public void checkMethodIsUsedThenPushEmail(final String methodName) throws BusinessException {
        SpringContextHelper.getSpringBean(TaskExecutor.class).execute(new Runnable() {
            @Override
            public void run() {
                if (StringUtils.isBlank(methodName)) {
                    return;
                }
                
                List<ConfParamOption> list = null;
                try {
                    list = confParamService.findParamOptions(CHECK_METHOD_IS_USED_KEY);
                } catch (Exception e) {
                }
                
                // 没有配置信息则不进行判断
                if (CollectionUtils.isEmpty(list)) {
                    return;
                }
                
                for (ConfParamOption c : list) {
                    if (StringUtils.isBlank(c.getOptionName()) || StringUtils.isBlank(c.getOptionValue())) {
                        continue;
                    }
                    
                    if (StringUtils.isBlank(c.getOptionDescribed())) {
                        continue;
                    }
                    
                    if (c.getOptionName().equals(methodName) && c.getOptionValue().equals(IS_CHECK_METHOD__KEY)) {
                        Map<String, Object> extras = new HashMap<String, Object>();
                        extras.put("methodName", methodName);
                        messageServiceProvider.pushEmailMessage(SCENE_KEY, extras, c.getOptionDescribed());
                        break;
                    }
                }
            }
        });
    }

}
