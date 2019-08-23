package com.juma.tgm.tools.service.impl;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.project.vo.ContractFilter;
import com.juma.tgm.project.vo.ContractVo;
import com.juma.tgm.tools.service.CrmCommonService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.vms.driver.domain.Driver;
import com.juma.vms.transport.request.CapacityPoolFilter;
import org.testng.annotations.Test;

import com.juma.tgm.common.Constants;
import com.juma.tgm.tools.service.BusinessAreaCommonService;

import testng.BaseTestNGTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BusinessCommonServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年9月20日 下午5:39:31
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class BusinessCommonServiceImplTest extends BaseTestNGTest {

    @Resource
    private BusinessAreaCommonService businessCommonService;
    @Resource
    private CrmCommonService crmCommonService;
    @Resource
    private VmsCommonService vmsCommonService;

    @Test
    public void loadAllNodeAreaName() {
        String name = businessCommonService.loadAllLinkNodeAreaName("00", Constants.SYS_LOGIN_USER);
        System.out.println("loadAllNodeAreaName:" + name);
    }

    @Test
    public void loadPreAndSelfAreaName() {
        String name = businessCommonService.loadPreAndSelfAreaName("00", Constants.SYS_LOGIN_USER);
        System.out.println("loadPreAndSelfAreaName:" + name);
    }

    @Test
    public void loadLogicAndSelfAreaName() {
        String name = businessCommonService.loadLogicAndSelfAreaName("00", Constants.SYS_LOGIN_USER);
        System.out.println("loadLogicAndSelfAreaName:" + name);
    }

    @Test
    public void loadAreaName() {
        String name = businessCommonService.loadAreaName("00", Constants.SYS_LOGIN_USER);
        System.out.println("loadLogicAndSelfAreaName:" + name);
    }

    @Test
    public void listContractByContractFilter() {
        ContractFilter contractFilter = new ContractFilter();
        contractFilter.setCustomerId(104637);
        List<ContractVo> contractVos = crmCommonService.listContractByContractFilter(contractFilter, new LoginUser(19, 1));

        System.out.println("loadLogicAndSelfAreaName:" + JSON.toJSONString(contractVos));
    }

    @Test
    public void listEffDriver() {
        CapacityPoolFilter filter = new CapacityPoolFilter();

        filter.setAreaCodeList(new ArrayList<String>());

        List<Driver> drivers = vmsCommonService.listEffectiveCapacityDriver(filter, new LoginUser(4, 1));

        System.out.println(JSON.toJSONString(drivers));
    }
}
