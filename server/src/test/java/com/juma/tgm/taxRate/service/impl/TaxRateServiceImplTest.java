package com.juma.tgm.taxRate.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.tgm.common.Constants;
import com.juma.tgm.waybill.domain.TaxRate;
import com.juma.tgm.waybill.service.TaxRateService;

import testng.BaseTestNGTest;

/**
 * 
 * @ClassName TaxRateServiceImplTest.java 请填写注释...
 * @author Libin.Wei
 * @Date 2017年8月7日 下午6:52:54
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */
public class TaxRateServiceImplTest extends BaseTestNGTest {

    @Resource
    private TaxRateService taxRateService;

    @Test
    public void getTaxTate() {
        TaxRate taxRate = taxRateService.getTaxRate(1);
        Assert.notNull(taxRate);
    }

    @Test
    public void loadAll() {
//        List<TaxRate> list = taxRateService.loadAll();
//        Assert.notEmpty(list);
    }

    @Test
    public void findTaxRateValueById() {
        BigDecimal taxRateValue = taxRateService.findTaxRateValueById(2);
        Assert.notNull(taxRateValue);
    }

    @Test
    public void findTaxRateBy() {
        float f = 0.11f;
        TaxRate taxRate = taxRateService.findTaxRateBy(new BigDecimal(f + ""), null);
        Assert.notNull(taxRate);
    }
    
    @Test
    public void search() {
        PageCondition pageCondition = new PageCondition();
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(15);
        Page<TaxRate> search = taxRateService.search(pageCondition, Constants.SYS_LOGIN_USER);
        System.out.println(JSON.toJSONString(search));
    }
}
