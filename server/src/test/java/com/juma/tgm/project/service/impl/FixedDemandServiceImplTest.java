package com.juma.tgm.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.juma.tgm.customerManager.service.FixedDemandService;
import com.juma.tgm.truck.domain.bo.DriverTruckInfoBo;

import testng.BaseTestNGTest;

/**
 * @ClassName FixedDemandServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年3月28日 下午8:51:14
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class FixedDemandServiceImplTest extends BaseTestNGTest {

    @Resource
    private FixedDemandService fixedDemandService;

    @Test
    public void listFixedDemandTruck() {
//        List<DriverTruckInfoBo> list = fixedDemandService.listFixedDemandTruck(16);
//
//        System.out.println(JSON.toJSONString(list));
    }
}
