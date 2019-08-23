package com.juma.tgm.waybill.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.juma.tgm.waybill.domain.TaxRate;
import com.juma.tgm.waybill.service.TaxRateService;

import testng.BaseTestNGTest;

public class TaxRateServiceImplTest extends BaseTestNGTest {

    @Resource
    private TaxRateService taxRateService;

    @Test
    public void findTaxRateBy() {
        float f = 0.11f;
        TaxRate taxRate = taxRateService.findTaxRateBy(new BigDecimal(f), null);
        Assert.assertNotNull(taxRate);

    }
}
