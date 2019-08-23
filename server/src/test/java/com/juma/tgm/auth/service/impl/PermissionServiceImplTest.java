package com.juma.tgm.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.juma.tgm.driver.domain.Driver;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.juma.auth.conf.domain.Permission;
import com.juma.auth.conf.service.PermissionService;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.service.ConfParamService;
import com.juma.tgm.common.Constants;

import testng.BaseTestNGTest;

/**
 * @ClassName PermissionServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年9月28日 上午11:39:33
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class PermissionServiceImplTest extends BaseTestNGTest {

    @Resource
    private PermissionService permissionService;
    @Resource
    private ConfParamService confParamService;

    @Test
    public void permissionList() {
        List<Permission> list = permissionService.findPermissions(Constants.AUTH_KEY_TGM_MANAGE);

        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void listConfParamOption() {
        List<ConfParamOption> list = confParamService.findParamOptions("TGM_SWITCH");
        System.out.println(JSON.toJSONString(list));
    }


    public static void main (String [] args) {
        List<Driver> list = new ArrayList<>();

        list.add(null);

        for (Driver d : list) {

            System.out.println(d);
            d.getDriverId();
        }
    }

}
