package com.juma.tgm.truck.service.impl;

import com.juma.server.vm.domain.dto.VehicleQueryConditionDTO;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.truck.service.TruckTypeService;
import org.springframework.util.Assert;
import org.testng.annotations.Test;
import testng.BaseTestNGTest;

import javax.annotation.Resource;

/**
 * @ClassName TruckServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年8月8日 下午5:03:08
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class TruckServiceImplTest extends BaseTestNGTest{

    @Resource
    private TruckService truckService;
    @Resource
    private TruckTypeService truckTypeService;
    /*@Resource
    private PropertyService propertyService;*/
    
//    @Resource
//    private TruckTypeService truckTypeService;
    
    @Test
    public void demo() {
        
        System.out.println(truckTypeService.findTruckTypeNameBy(31, 57));
        
    }
    
    @Test
    public void getVehicle() {
//        Vehicle vehicle = amsService.getVehicleByDriverId(76);
//        Assert.isNull(vehicle);
    }
    
    @Test
    public void getTruck() {
        Truck truck = truckService.getTruck(null);
        Assert.isNull(truck);
    }
    
    @Test
    public void getVehicleById() {
//        Vehicle vehicle = amsService.getVehicleById(1);
//        
//        System.out.println(JSON.toJSONString(vehicle));
    }
    
    /*@Test
    public void testC() {
        List<String> keys = new ArrayList<String>();
        keys.add("boxType");
        Map<String, List<PropertyBO>> map = propertyService.listByKeys(keys);
        System.out.println(JSON.toJSONString(map));
    }*/
    
    @Test
    public void test() {
        VehicleQueryConditionDTO dto = new VehicleQueryConditionDTO();
        dto.setVehicleId(4766);
        dto.setTenantId(2);
        dto.setTenantCode("000000000");
//        VehicleBo vehicleBo = amsServiceV2.getVehicleById(dto);
//        dto.setPlateNumber("川B310KD");
//        VehicleBo vehicleBo = amsServiceV2.getVehicleBOByQueryCondition(dto);
//        System.out.println(JSON.toJSONString(vehicleBo));
    }

    @Test
    public void delete() {
        truckService.delete(6867, null);
    }
}
