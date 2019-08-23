package com.juma.tgm.waybill.service.impl;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.juma.tgm.crm.service.IncomeStatisticsService;

import testng.BaseTestNGTest;

/**
 * @ClassName IncomeStatisticsServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年12月18日 上午9:42:14
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class IncomeStatisticsServiceImplTest extends BaseTestNGTest {

    @Resource
    private IncomeStatisticsService incomeStatisticsService;

    @Test
    public void insert() {
        incomeStatisticsService.insert(-7);
    }
}
