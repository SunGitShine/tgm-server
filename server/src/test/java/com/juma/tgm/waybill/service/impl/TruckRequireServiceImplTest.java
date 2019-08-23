package com.juma.tgm.waybill.service.impl;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.juma.tgm.waybill.service.TruckRequireService;

import testng.BaseTestNGTest;

/**
 * @ClassName TruckRequireServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年4月13日 下午4:12:05
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class TruckRequireServiceImplTest extends BaseTestNGTest {

    @Resource
    private TruckRequireService truckRequireService;
    
    @Test
    public void getTruckRequire() {
        String string = truckRequireService.getTruckRequire(null, 149309);
        System.out.println(string);
    }
}
