package com.juma.tgm.auth.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.giants.common.exception.BusinessException;
import com.giants.common.lang.exception.CategoryCodeFormatException;
import com.juma.auth.employee.domain.Department;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.DepartmentService;
import com.juma.auth.user.domain.LoginUser;

import testng.BaseTestNGTest;

/**
 * @ClassName DepartmentServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年8月15日 下午6:58:06
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class DepartmentServiceImplTest extends BaseTestNGTest {

    @Resource
    private DepartmentService departmentService;
    
    @Test
    public void listDepartmentBusinessArea() throws BusinessException, CategoryCodeFormatException {
        
        LoginEmployee loginEmployee = new LoginEmployee();
//        loginEmployee.setEmployeeId(employeeId);
        
//        DepartmentBo departmentBo = departmentService.findDepartmentBo("001605", loginEmployee);
        LoginUser loginUser = new LoginUser(2965);
        loginUser.setTenantId(2);
        
        List<Department> list = departmentService.findChildDepartment(201, loginUser);
        
        System.out.println(JSON.toJSONString(list));
    }
  
}
