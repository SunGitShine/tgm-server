package com.juma.tgm.log.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.operateLog.domain.OperateLog;
import com.juma.tgm.operateLog.enumeration.LogSignEnum;
import com.juma.tgm.operateLog.enumeration.OperateApplicatoinEnum;
import com.juma.tgm.operateLog.enumeration.OperateTypeEnum;
import com.juma.tgm.operateLog.service.OperateLogService;

import testng.BaseTestNGTest;

/**
 * @ClassName OperateLogServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年10月30日 下午2:46:00
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class OperateLogServiceImplTest extends BaseTestNGTest {

    @Resource
    private OperateLogService operateLogService;

    @Test
    public void search() {
        PageCondition p = new PageCondition();
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("logSign", LogSignEnum.PROJECT.getCode());
        filters.put("relationId", 1);
        p.setFilters(filters);

        operateLogService.search(p, new LoginUser(2, 1));
    }

    @Test
    public void insert() {
        OperateLog operateLog = new OperateLog();
        operateLog.setLogSign(LogSignEnum.PROJECT.getCode());
        operateLog.setRelationTableId(1);
        operateLog.setOperateType(OperateTypeEnum.ADD_PROJECT.getCode());
        operateLog.setOperateApplicatoin(OperateApplicatoinEnum.CUSTOMER_APP.getCode());

        LoginUser loginUser = new LoginUser(2, 1);

        operateLogService.insert(operateLog, loginUser);
    }
}
