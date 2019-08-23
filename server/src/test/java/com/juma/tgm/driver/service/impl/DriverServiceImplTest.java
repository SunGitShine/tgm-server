package com.juma.tgm.driver.service.impl;

import com.alibaba.fastjson.JSON;
import com.juma.auth.user.domain.LoginUser;
import com.juma.server.vm.domain.dto.DriverQueryConditionDTO;
import com.juma.server.vm.domain.dto.VehicleQueryConditionDTO;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.tools.service.VmsCommonService;
import org.testng.annotations.Test;
import testng.BaseTestNGTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DriverServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年4月13日 下午3:21:58
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class DriverServiceImplTest extends BaseTestNGTest {

    @Resource
    private DriverService driverService;
    @Resource
    private VmsCommonService vmsCommonService;
    
    @Test
    public void listByAreaCodeLike() {
        List<Driver> list = driverService.listByAreaCodeLike(2, "00", null);
        System.out.println(JSON.toJSONString(list));
        System.out.println("----------------------------------");
    }
    
    @Test
    public void test() {
        DriverQueryConditionDTO dto = new DriverQueryConditionDTO();
        dto.setTenantId(3);
        dto.setTenantCode("000000001");
        dto.setDriverId(1);

//        com.juma.server.vm.domain.Driver amsDriver = amsServiceV2.getDriverById(dto);
//        System.out.println(JSON.toJSON(amsDriver));
    }
    
    @Test
    public void testV() {
        VehicleQueryConditionDTO dto = new VehicleQueryConditionDTO();
        dto.setTenantId(2);
        dto.setTenantCode("000000000");
        dto.setVehicleId(73);
//
//        com.juma.server.vm.domain.Driver amsDriver = amsServiceV2.getByBindVehicleId(dto);
//        System.out.println(JSON.toJSON(amsDriver));
    }

    @Test
    public void updateDriverIsAcceptAllocateOrder() {
        Integer accept = 0;
        vmsCommonService.updateDriverIsAcceptAllocateOrder(2, (accept == 0 ? 0 : 1), new LoginUser(2,2));
    }
}
