package com.juma.tgm.truckType.service.impl;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.tgm.common.Constants;
import com.juma.tgm.configure.domain.ServiceConfItem;
import com.juma.tgm.configure.service.ServiceConfItemService;

import testng.BaseTestNGTest;

/**
 * @ClassName ServiceConfItemServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年11月29日 下午6:29:00
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class ServiceConfItemServiceImplTest extends BaseTestNGTest {

    @Resource
    private ServiceConfItemService serviceConfItemService;

    @Test
    public void search() {
        PageCondition pageCondition = new PageCondition();
        pageCondition.setPageNo(0);
        pageCondition.setPageSize(10);
        //pageCondition.put("serviceConfId", 1);
        //pageCondition.put("fenceType", 110);

        Page<ServiceConfItem> search = serviceConfItemService.search(pageCondition, Constants.SYS_LOGIN_USER);

        System.out.println(JSON.toJSONString(search));
    }

    @Test
    public void insert() {
        serviceConfItemService.insert(28093, 1, Constants.SYS_LOGIN_USER);
    }

    @Test
    public void update() {
        serviceConfItemService.update(1, Constants.SYS_LOGIN_USER);
    }

    @Test
    public void delete() {
        serviceConfItemService.delete(2, Constants.SYS_LOGIN_USER);
    }
}
