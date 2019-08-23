package com.juma.tgm.waybill.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.juma.tgm.waybill.domain.WaybillReceiveAddressCargo;
import com.juma.tgm.waybill.service.WaybillReceiveAddressCargoService;

import testng.BaseTestNGTest;

/**
 * @ClassName WaybillReceiveAddressCargoServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年5月2日 上午8:13:18
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class WaybillReceiveAddressCargoServiceImplTest  extends BaseTestNGTest {

    @Resource
    private WaybillReceiveAddressCargoService waybillReceiveAddressCargoService;
    
    @Test
    public void batchInsert() {
        List<WaybillReceiveAddressCargo> list = new ArrayList<WaybillReceiveAddressCargo>();
        WaybillReceiveAddressCargo cargo = new WaybillReceiveAddressCargo();
        cargo.setAddressId(158454);
        cargo.setCargoPackages(2);
        cargo.setCargoType("测试类型");
        cargo.setCargoVolume(2f);
        cargo.setCargoWeight(3f);
        list.add(cargo);
        
        waybillReceiveAddressCargoService.batchInsert(list);
    }
}
