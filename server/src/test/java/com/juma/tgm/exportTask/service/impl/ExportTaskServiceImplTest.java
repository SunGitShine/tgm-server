package com.juma.tgm.exportTask.service.impl;

import com.juma.auth.user.domain.LoginUser;
import java.util.List;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.juma.tgm.cms.service.ExportTaskService;
import com.juma.tgm.export.domain.ExportDynamicParameter;

import testng.BaseTestNGTest;

/**
 * @ClassName ExportTaskServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年6月13日 上午10:48:23
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class ExportTaskServiceImplTest extends BaseTestNGTest {

    @Resource
    private ExportTaskService exportTaskService;

    @Test
    public void exportFields() {
        exportTaskService.exportFields(new LoginUser(19,1));
//        List<ExportDynamicParameter> exportFields = exportTaskService.exportFields();
//        System.out.println(JSON.toJSONString(exportFields));
    }
}
