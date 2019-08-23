package com.juma.tgm.customerManager.service.impl;

import com.alibaba.fastjson.JSON;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageQueryCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.Constants;
import com.juma.tgm.customer.service.CustomerManagerService;
import com.juma.tgm.truck.domain.bo.DriverTruckInfoBo;
import com.juma.tgm.truck.domain.vo.TruckFleetQueryVo;
import org.apache.commons.collections.CollectionUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import testng.BaseTestNGTest;

import javax.annotation.Resource;

/**
 * @author Libin.Wei
 * @version 1.0.0
 * @ClassName CustomerManagerServiceImplTest.java
 * @Description 请填写注释...
 * @Date 2017年9月11日 上午11:20:42
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class CustomerManagerServiceImplTest extends BaseTestNGTest {

    @Resource
    private CustomerManagerService customerManagerService;

    @Test
    public void findTruckFleetBy() {
        LoginUser loginUser = Constants.SYS_LOGIN_USER;
        loginUser.setUserId(7278);
        customerManagerService.findTruckFleetForFilialeProject(1, 1, 10, loginUser);
    }


    @Test
    public void searchTruckFleetTest() {

        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setEmployeeId(2311);
        loginEmployee.setUserId(5382);
        loginEmployee.setTenantId(2);
        loginEmployee.setTenantCode("000000000");

        TruckFleetQueryVo queryVo = new TruckFleetQueryVo();
        PageQueryCondition<TruckFleetQueryVo> queryCondition = new PageQueryCondition<>(queryVo);
        queryVo.setKeyword("皖A7F153");

        Page<DriverTruckInfoBo> driverTruckInfoBoPage = customerManagerService.searchTruckFleet(queryCondition, loginEmployee);

        Assert.assertFalse(CollectionUtils.isEmpty(driverTruckInfoBoPage.getResults()), "结果为空");


    }

    @Test
    public void findLogisticsProduct(){

        Integer customerId = 15;
        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setEmployeeId(2311);
        loginEmployee.setUserId(13168);
        loginEmployee.setTenantId(2);
        loginEmployee.setTenantCode("000000004");
        System.out.println(JSON.toJSONString(customerManagerService.findLogisticsProduct(87279, loginEmployee)));
    }
}
