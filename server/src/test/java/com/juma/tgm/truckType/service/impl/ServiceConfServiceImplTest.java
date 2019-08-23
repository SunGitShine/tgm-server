package com.juma.tgm.truckType.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.juma.tgm.common.Constants;
import com.juma.tgm.configure.domain.ServiceConf;
import com.juma.tgm.configure.service.ServiceConfService;

import testng.BaseTestNGTest;

/**
 * @ClassName ServiceConfItemServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年11月29日 下午6:29:00
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class ServiceConfServiceImplTest extends BaseTestNGTest {

    @Resource
    private ServiceConfService serviceConfService;


    @Test
    public void insert() {
//        serviceConfService.insert(28093, 1, Constants.SYS_LOGIN_USER);
    }

    @Test
    public void update() {
        ServiceConf conf = new ServiceConf();
        conf.setServiceConfId(1);
        conf.setIsOpenServiceRegion(true);
        serviceConfService.update(conf, null);
    }
    
    @Test
    public void listServiceConf() {
        List<ServiceConf> list = serviceConfService.listServiceConf(Constants.SYS_LOGIN_USER);
        System.out.println(JSON.toJSONString(list));
    }
}
