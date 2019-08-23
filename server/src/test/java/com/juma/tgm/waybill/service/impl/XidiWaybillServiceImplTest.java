package com.juma.tgm.waybill.service.impl;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.juma.tgm.waybill.service.WaybillService;
import com.juma.tgm.waybill.service.customize.xidi.XidiWaybillService;

import testng.BaseTestNGTest;

/**
 * @ClassName XidiWaybillServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年7月30日 上午10:41:46
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class XidiWaybillServiceImplTest extends BaseTestNGTest {

    @Resource
    private WaybillService waybillService;
    @Resource
    private XidiWaybillService xidiWaybillService;
    
    @Test
    public void allowChangeCar() {
        boolean allowChangeCar = xidiWaybillService.allowChangeCar(waybillService.getWaybill(149064));
        System.out.println(allowChangeCar);
    }
}
