package com.juma.tgm.waybill.service.impl;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.juma.monitor.service.FenceService;
import com.juma.monitor.truck.domain.Fence;
import com.juma.tgm.waybill.service.WaybillAutoFenceServicve;

import testng.BaseTestNGTest;

/**
 * @ClassName WaybillAutoFenceServicveImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年9月11日 下午5:09:30
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class WaybillAutoFenceServicveImplTest extends BaseTestNGTest{

    @Resource
    private WaybillAutoFenceServicve waybillAutoFenceServicve;
    @Resource
    private FenceService fenceServicve;
    
    @Test
    public void getFence() {
        Fence fence = fenceServicve.queryFence(27969);
        System.out.println(JSON.toJSONString(fence));
    }
    
    @Test
    public void changeDeliveryStartTime() {
//        waybillAutoFenceServicve.changeDeliveryStartTime(13330, 1);
    }
}
