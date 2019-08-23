package com.juma.tgm.auth.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.juma.tgm.tools.service.BusinessAreaCommonService;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.conf.domain.BusinessAreaNode;
import com.juma.auth.conf.service.BusinessAreaService;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.tgm.common.Constants;

import testng.BaseTestNGTest;

/**
 * @ClassName BusinessAreaServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年12月5日 下午2:25:46
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class BusinessAreaServiceImplTest extends BaseTestNGTest {

    @Resource
    private BusinessAreaService businessAreaService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private BusinessAreaCommonService businessAreaCommonService;

    @Test
    public void test() {
        LoginUser loginUser = Constants.SYS_LOGIN_USER;
        //loginUser.setTenantId(2);
        
        BusinessArea businessArea = businessAreaService.loadLogicBusinessArea("0004", loginUser);
        System.out.println(JSON.toJSONString(businessArea));
    }
    
    @Test
    public void getUser() {
        LoginUser loginUser = new LoginUser(2, 2141);
        User user = employeeService.loadUserByEmployeeId(null, loginUser);
        System.out.println(JSON.toJSONString(user));
    }
    
    @Test
    public void getAreaCode() {
        List<BusinessAreaNode> areaTree = businessAreaService.findBusinessAreaTree(2);
        
        System.out.println(JSON.toJSONString(areaTree));
        System.out.println(JSON.toJSONString(areaTree));
    }

    @Test
    public void findAreaCode() {
        String areaName = businessAreaCommonService.loadLogicAndSelfAreaName("01", new LoginUser(2, 1));

        System.out.println(areaName);
    }
}
