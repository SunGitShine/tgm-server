package com.juma.tgm.waybill.service.impl;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.juma.tgm.waybill.service.WaybillBindFenceService;

import testng.BaseTestNGTest;

/**
 * @ClassName WaybillBindFenceServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年9月29日 下午5:47:24
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class WaybillBindFenceServiceImplTest extends BaseTestNGTest {

    @Resource
    private WaybillBindFenceService WaybillBindFenceService;
    
    @Test
    public void getWaybillBindFenceForUpdate() {
        WaybillBindFenceService.getWaybillBindFenceForUpdate(741);
    }
    
    @Test
    public void findByFenceIdForUpdate() {
        WaybillBindFenceService.findByFenceIdForUpdate(28013);
    }
    
    @Test
    public void listByWaybillIdForUpdate() {
        WaybillBindFenceService.listByWaybillIdForUpdate(13487);
    }
}
