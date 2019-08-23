package com.juma.tgm.truckType.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.Constants;
import com.juma.tgm.configure.domain.PackFreightRule;
import com.juma.tgm.configure.service.PackFreightRuleService;

import testng.BaseTestNGTest;

/**
 * @ClassName LandingDistributionFreightServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年11月17日 下午2:16:09
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class PackFreightRuleServiceImplTest extends BaseTestNGTest {

    @Resource
    private PackFreightRuleService packFreightRuleService;

    @Test
    public void insert() {
        PackFreightRule f = new PackFreightRule();
        f.setRegionCode("22000");
        f.setRegionName("成都市");
        f.setTruckTypeId(1);
        f.setTruckTypeName("厢式4.2米");
        f.setBaseMiles(5);
        f.setBaseFreight(new BigDecimal("50"));
        f.setOverMilesUnit(new BigDecimal("5"));
        f.setIsEnable(true);

        LoginUser loginUser = Constants.SYS_LOGIN_USER;
        loginUser.setTenantId(3);
        loginUser.setTenantCode("000000001");

        packFreightRuleService.insert(f, loginUser);
    }

    @Test
    public void update() {
        PackFreightRule f = new PackFreightRule();
        f.setPackFreightRuleId(1);
        f.setRegionCode("22000");
        f.setRegionName("成都市");
        f.setTruckTypeId(1);
        f.setTruckTypeName("厢式4.2米");
        f.setBaseMiles(9);
        f.setBaseFreight(new BigDecimal("50"));
        f.setOverMilesUnit(new BigDecimal("5"));
        f.setIsEnable(true);
        f.setCreateUserId(1);

        LoginUser loginUser = Constants.SYS_LOGIN_USER;
        loginUser.setTenantId(2);
        loginUser.setTenantCode("000000000");

        packFreightRuleService.update(f, loginUser);
    }

    @Test
    public void get() {
        PackFreightRule packFreightRule = packFreightRuleService.getPackFreightRule(1);
        System.out.println(JSON.toJSONString(packFreightRule));
    }

    @Test
    public void enable() {
        packFreightRuleService.updateToEnable(1, Constants.SYS_LOGIN_USER);
    }

    @Test
    public void disable() {
        packFreightRuleService.updateToDisable(1, Constants.SYS_LOGIN_USER);
    }
}
