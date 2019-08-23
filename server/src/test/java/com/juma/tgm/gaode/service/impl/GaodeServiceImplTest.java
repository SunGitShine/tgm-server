package com.juma.tgm.gaode.service.impl;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.juma.tgm.common.id.IdGeneratorTable;
import com.juma.tgm.gaode.domain.IpAddressComponent;
import com.juma.tgm.id.service.IdGeneratorService;
import com.juma.tgm.waybill.service.GaoDeMapService;

import testng.BaseTestNGTest;

/**
 * @ClassName GaodeServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年11月21日 下午5:28:34
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class GaodeServiceImplTest extends BaseTestNGTest {

    @Resource
    private GaoDeMapService gaoDeMapService;
    
    @Resource
    private IdGeneratorService idGeneratorService;
    
    @Test
    public void test(){
        
        System.out.println(idGeneratorService.genId(IdGeneratorTable.IdType.AP));
        
    }
    
    @Test
    public void ipAddess() {
        IpAddressComponent ipAddress = gaoDeMapService.findRegionCodeByIpAddress("119.96.63.130");
        System.out.println(JSON.toJSONString(ipAddress));
    }

    @Test
    public void getRouteInfoByLatLng(){

        System.out.println("========="+gaoDeMapService.getRouteInfoByLatLng("103.987514","30.601487"));
    }
}
