package com.juma.tgm.landingWaybill.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.Assert;
import org.testng.annotations.Test;

import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.Constants;
import com.juma.tgm.landingWaybill.domain.LandingWaybill;
import com.juma.tgm.landingWaybill.service.LandingWaybillService;
import com.juma.tgm.waybill.domain.Waybill;

import testng.BaseTestNGTest;

/**
 * @ClassName LandingWaybillServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年11月22日 下午4:35:34
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class LandingWaybillServiceImplTest extends BaseTestNGTest {

    @Resource
    private LandingWaybillService landingWaybillService;

    @Test
    public void list() {
        PageCondition pageCondition = new PageCondition();
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(10);
        
        LoginUser loginUser = Constants.SYS_LOGIN_USER;
        loginUser.setTenantId(1);

        List<LandingWaybill> list = landingWaybillService.listLandingWatingReceiveWaybill(pageCondition, loginUser);

        Assert.notNull(list);
    }

    @Test
    public void getWaybill() {
        LandingWaybill waybill = landingWaybillService.getLandingWaybill(13672, Constants.SYS_LOGIN_USER);
        
        Assert.notNull(waybill);
    }
}
