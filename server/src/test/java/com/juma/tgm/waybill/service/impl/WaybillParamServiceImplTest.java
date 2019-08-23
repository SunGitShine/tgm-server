package com.juma.tgm.waybill.service.impl;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.juma.tgm.common.Constants;
import com.juma.tgm.waybill.service.WaybillParamService;

import testng.BaseTestNGTest;

public class WaybillParamServiceImplTest extends BaseTestNGTest {

    @Resource
    private WaybillParamService waybillParamService;

    @Test
    public void driverReadWaybill() {
        waybillParamService.driverReadWaybill(12834, Constants.SYS_LOGIN_USER);
    }

    @Test
    public void driverUnReadWaybill() {
        waybillParamService.driverUnReadWaybill(12834, Constants.SYS_LOGIN_USER);
    }
    
    @Test
    public void doCompleteWaybillParam() {
        waybillParamService.doCompleteWaybillParam(167818, "{\"byDay\":\"10\",\"byPoint\":\"2\",\"byKm\":\"2\"}", Constants.SYS_LOGIN_USER);
    }
    
}
