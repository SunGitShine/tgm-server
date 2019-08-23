package com.juma.tgm.waybill.service.impl;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.juma.tgm.waybill.service.WaybillCronService;

import testng.BaseTestNGTest;

/**
 * @ClassName WaybillCronServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年12月6日 上午11:25:07
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class WaybillCronServiceImplTest extends BaseTestNGTest {

    @Resource
    private WaybillCronService waybillCronService;

    @Test
    public void cronConstraintFinishWaybill() {
        waybillCronService.cronConstraintFinishWaybill();
    }
}
