package com.juma.tgm.auth.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.juma.auth.tenant.domain.Tenant;
import com.juma.auth.tenant.service.TenantService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.monitor.service.FenceService;
import com.juma.tgm.common.Constants;

import testng.BaseTestNGTest;

/**
 * @ClassName FenceServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年12月5日 下午6:35:36
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class FenceServiceImplTest extends BaseTestNGTest {

    @Resource
    private FenceService fenceService;
    @Resource
    private TenantService tenantService;

    @Test
    public void test() {
        LoginUser loginUser = Constants.SYS_LOGIN_USER;
        loginUser.setTenantId(3);

        List<Integer> fenceTrigger = fenceService.fenceTrigger(loginUser, 100, 104.089602, 30.588892);

        System.out.println(JSON.toJSONString(fenceTrigger));
    }
    
    @Test
    public void findAllTenant() {
        List<Tenant> list = tenantService.findAllTenant();
        System.out.println(JSON.toJSONString(list));
    }
}
