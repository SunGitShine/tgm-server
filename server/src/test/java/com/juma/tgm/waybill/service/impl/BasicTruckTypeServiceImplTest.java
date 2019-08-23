package com.juma.tgm.waybill.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.juma.tgm.basicTruckType.service.BasicTruckTypeService;
import com.juma.tgm.truck.domain.AdditionalFunction;
import com.juma.tgm.truck.domain.AdditionalFunctionBo;
import com.juma.tgm.waybill.domain.TruckRequireInfo;

import testng.BaseTestNGTest;

/**
 * @ClassName BasicTruckTypeServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年8月8日 下午2:55:02
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class BasicTruckTypeServiceImplTest extends BaseTestNGTest {

    @Resource
    private BasicTruckTypeService basicTruckTypeService;
    
    @Test
    public void funlist() {
        TruckRequireInfo requireInfo = basicTruckTypeService.getBasicTruckRequireInfo(2, null);
        if (null == requireInfo) {
            return;
        }
        
        System.out.println(JSON.toJSONString(requireInfo));
    }
}
