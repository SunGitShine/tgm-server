package com.juma.tgm.vendor.service.impl;

import com.alibaba.fastjson.JSON;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.vendor.domain.VendorProjectMapping;
import com.juma.tgm.vendor.service.VendorMappingService;
import com.juma.vms.external.service.VmsService;
import com.juma.vms.vendor.vo.VendorQuery;
import org.testng.annotations.Test;
import testng.BaseTestNGTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName VendorMappingServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年8月29日 上午11:49:51
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class VendorMappingServiceImplTest extends BaseTestNGTest {

    @Resource
    private VendorMappingService vendorMappingService;
    @Resource
    private VmsService vmsService;

    @Test
    public void listVendorMapping() {
//        List<VendorMapping> list = vendorMappingService.listVendorMapping(2, "a", 10);
//        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void findVendorProjectMappingBy() {
        VendorProjectMapping projectMapping = vendorMappingService.findVendorProjectMappingBy(2, 1, 12, null);
        System.out.println(JSON.toJSONString(projectMapping));
    }
    
    @Test
    public void findProjectMappingByVendorMappingIdAndProjectId() {
        VendorProjectMapping projectMapping = vendorMappingService.findProjectMappingByVendorMappingIdAndProjectId(2, null);
        System.out.println(JSON.toJSONString(projectMapping));
    }
    
    @Test
    public void test() {
        PageCondition page = new PageCondition();
        page.setPageNo(1);
        page.setPageSize(20);
        Map<String,Object> filters = new HashMap<String,Object>();
        filters.put("name","任宗波");
        page.setFilters(filters);
        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setTenantId(9);
//        Page<RenterBo> pages = amsServiceV2.getByPageCarrier(page, loginEmployee);
//        System.out.println(JSON.toJSONString(pages));
    }

    @Test
    public void listVendorByVendorNameLike() {

        List<VendorQuery> vendorQueries = vmsService.listVendorByVendorNameLike(null, null, 15, new LoginUser(19, 1));
        System.out.println(JSON.toJSONString(vendorQueries));
    }
}
