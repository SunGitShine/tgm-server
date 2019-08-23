package com.juma.tgm.truck.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.giants.common.tools.PageCondition;
import com.giants.common.tools.PageQueryCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.truck.domain.bo.DriverTruckInfoBo;
import com.juma.tgm.truck.domain.vo.TruckFleetQueryVo;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.giants.common.tools.Page;
import com.juma.tgm.customer.service.CustomerManagerService;
import com.juma.tgm.truck.domain.TruckFleetTruck;
import com.juma.tgm.truck.service.TruckFleetTruckService;

import testng.BaseTestNGTest;

/**
 * @ClassName TruckFleetTruckServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年9月11日 上午10:24:26
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class TruckFleetTruckServiceImplTest extends BaseTestNGTest {

    @Resource
    private TruckFleetTruckService truckFleetTruckService;
    @Resource
    private CustomerManagerService customerManagerService;

    @Test
    public void listByTruckFleetIds() {
        List<Integer> truckFleetIds = new ArrayList<Integer>();
        truckFleetIds.add(2777);
//        truckFleetIds.add(28);

        Page<TruckFleetTruck> page = truckFleetTruckService.listByTruckFleetIds(truckFleetIds, null, null);
        System.out.println(JSON.toJSONString(page));
        Assert.notNull(page);
    }
    
    @Test
    public void test() {
        TruckFleetQueryVo vo = new TruckFleetQueryVo();
//        vo.set
        PageQueryCondition<TruckFleetQueryVo> queryCondition = new PageQueryCondition<TruckFleetQueryVo>(vo);
        queryCondition.setPageNo(1);
        queryCondition.setPageSize(100);

        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setTenantId(2);
        loginEmployee.setEmployeeId(9907);
        loginEmployee.setUserId(7);

//        Page<DriverTruckInfoBo> page = customerManagerService.searchTruckFleet(queryCondition, loginEmployee);

        Page<DriverTruckInfoBo> truckFleetBy = customerManagerService.findTruckFleetBy(1513105, 1, 20, loginEmployee);


//        AppDriverAssignBo bo = customerManagerService.findTruckFleetBy(null, 1, 20, new LoginUser(2, 2986));
        System.out.println(JSON.toJSONString(truckFleetBy));
    }


    @Test
    public void deleteByTrcukFleetId() {
        truckFleetTruckService.deleteByTruckFleetId(1709);
    }
}
