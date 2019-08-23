package com.juma.tgm.truckType.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.Constants;
import com.juma.tgm.configure.domain.FreightRule;
import com.juma.tgm.configure.service.FreightRuleService;

import testng.BaseTestNGTest;

/**
 * @ClassName LandingDistributionFreightServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年11月17日 下午2:16:09
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class FreightRuleServiceImplTest extends BaseTestNGTest {

    @Resource
    private FreightRuleService freightRuleService;

    @Test
    public void insert() {
        FreightRule f = new FreightRule();
        f.setRegionCode("22000");
        f.setRegionName("成都市");
        f.setBaseMiles(5);
        f.setBaseVolume(3);
        f.setBaseWeight(3);
        f.setBaseFreight(new BigDecimal("50"));
        f.setOverMilesUnit(new BigDecimal("5"));
        f.setOverVolumeUnit(new BigDecimal("0.2"));
        f.setOverWeightUnit(new BigDecimal("0.5"));
        f.setMaxMiles(100);
        f.setMaxVolume(10);
        f.setMaxWeight(12);
        f.setIsEnable(true);
        f.setCreateUserId(1);

        LoginUser loginUser = Constants.SYS_LOGIN_USER;
        loginUser.setTenantId(3);
        loginUser.setTenantCode("000000001");

        freightRuleService.insert(f, loginUser);
    }

    @Test
    public void update() {
        FreightRule f = new FreightRule();
        f.setFreightRuleId(1);
        f.setRegionCode("22000");
        f.setRegionName("成都市");
        f.setBaseMiles(7);
        f.setBaseVolume(3);
        f.setBaseWeight(3);
        f.setBaseFreight(new BigDecimal("50"));
        f.setOverMilesUnit(new BigDecimal("5"));
        f.setOverVolumeUnit(new BigDecimal("0.2"));
        f.setOverWeightUnit(new BigDecimal("0.5"));
        f.setMaxMiles(100);
        f.setMaxVolume(10);
        f.setMaxWeight(12);
        f.setIsEnable(true);
        f.setCreateUserId(1);

        LoginUser loginUser = Constants.SYS_LOGIN_USER;
        loginUser.setTenantId(2);
        loginUser.setTenantCode("000000000");

        freightRuleService.update(f, loginUser);
    }

    @Test
    public void get() {
        FreightRule freightRule = freightRuleService.getFreightRule(1);
        System.out.println(JSON.toJSONString(freightRule));
    }

    @Test
    public void enable() {
        freightRuleService.updateToEnable(1, Constants.SYS_LOGIN_USER);
    }

    @Test
    public void disable() {
        freightRuleService.updateToDisable(1, Constants.SYS_LOGIN_USER);
    }
}
