package com.juma.tgm.truckType.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.conf.service.ConfParamService;
import com.juma.scm.product.service.PropertyService;
import com.juma.tgm.common.Constants;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.truck.service.TruckTypeService;

import testng.BaseTestNGTest;

/**
 * @ClassName TruckTypeServiceImpl.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年8月16日 下午1:53:21
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class TruckTypeServiceImplTest extends BaseTestNGTest {

    @Resource
    private TruckTypeService truckTypeService;
    @Resource
    private ConfParamService confParamService;
    @Resource
    private PropertyService propertyService;

    @Test
    public void search() {
        PageCondition pageCondition = new PageCondition();
        pageCondition.setPageNo(1);
       // pageCondition.put("tenantCode", "000000000");
        pageCondition.setPageSize(Integer.MAX_VALUE);
        pageCondition.setOrderBy(" order_num asc ");
        Page<TruckType> page = truckTypeService.search(pageCondition, Constants.SYS_LOGIN_USER);

        System.out.println(JSON.toJSONString(page));

        Assert.notNull(page);
    }

    @Test
    public void getTruckType() {
        TruckType truckType = truckTypeService.getTruckType(1);
        System.out.println(JSON.toJSONString(truckType));
        Assert.notNull(truckType);
    }

    @Test
    public void insert() {
        TruckType truckType = new TruckType();
        truckType.setVehicleBoxType(4);
        truckType.setTruckLengthId(3);
        truckTypeService.insert(truckType, Constants.SYS_LOGIN_USER);
    }

    @Test
    public void update() {
        TruckType truckType = new TruckType();
        truckType.setTruckTypeId(1);
        truckType.setVehicleBoxType(4);
        truckType.setTruckLengthId(3);
        truckTypeService.update(truckType, Constants.SYS_LOGIN_USER);
    }

    @Test
    public void checkVehicleBoxTypeExist() {
//        boolean exist = truckTypeService.checkVehicleBoxTypeExist(4);
//        Assert.isTrue(exist);
    }

    @Test
    public void findVehicleBoxTypeName() {
        String name = truckTypeService.findVehicleBoxTypeName(4);
        System.out.println(name);
        Assert.notNull(name);
    }

    @Test
    public void findTruckTypeNameByTypeId() {
        String name = truckTypeService.findTruckTypeNameByTypeId(1);
        System.out.println(name);
        Assert.notNull(name);
    }

    @Test
    public void listAllTruckTypeSimpleByOrderNoAsc() {
//        List<TruckType> list = truckTypeService.listAllTruckTypeSimpleByOrderNoAsc(null);
//        System.out.println(JSON.toJSONString(list));
//        Assert.notEmpty(list);
    }

    @Test
    public void listAllTruckTypeByOrderNoAsc() {
        List<TruckType> list = truckTypeService.listAllTruckTypeByOrderNoAsc(2, false);
        System.out.println(JSON.toJSONString(list));
        Assert.notEmpty(list);
    }

    @Test
    public void findTruckTypeByLengthId() {
        List<TruckType> list = truckTypeService.findTruckTypeByLengthId(3);
        System.out.println(JSON.toJSONString(list));
        Assert.notEmpty(list);
    }

    @Test
    public void listTruckTypeByCache() {
//        List<TruckType> list = truckTypeService.listTruckTypeByCache();
//        System.out.println(JSON.toJSONString(list));
//        Assert.notEmpty(list);
    }
    
    @Test
    public void getBoxLengthId() {
        /*Integer boxLength = 3300;
        
        Integer boxLengthId = null;

        List<ConfParamOption> options = null;//confParamService.findParamOptions(Constants.AMS_BOX_LEVEL);
        for (ConfParamOption option : options) {
            if (StringUtils.isBlank(option.getOptionValue())) {
                continue;
            }

            String[] split = option.getOptionValue().split("-");

            if (split.length != 2 || StringUtils.isBlank(split[0]) || StringUtils.isBlank(split[1])) {
                continue;
            }

            if (boxLength.compareTo(Integer.parseInt(split[0])) != -1
                    && boxLength.compareTo(Integer.parseInt(split[1])) != 1) {
                boxLengthId = StringUtils.isBlank(option.getOptionDescribed()) ? null
                        : Integer.parseInt(option.getOptionDescribed());
            }
        }
        
        System.out.println("获取到的车长ID：" + boxLengthId);*/
    }
    
    @Test
    public void findVehicleBoxLengthName() {
        String name = truckTypeService.findVehicleBoxLengthName(2);
        System.out.println(name);
    }

    @Test
    public void propertyListByKeys(){
        List<String> keys = new ArrayList<>();
        keys.add("vehicleType");
        keys.add("pzlx31");
        System.out.println(JSON.toJSONString(propertyService.listByKeys(keys)));
    }
}
