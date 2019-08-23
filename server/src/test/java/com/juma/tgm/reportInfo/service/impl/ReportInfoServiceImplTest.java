package com.juma.tgm.reportInfo.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.driver.domain.ReportInfo;
import com.juma.tgm.reportInfo.service.ReportInfoService;

import testng.BaseTestNGTest;

/**
 * @ClassName ReportInfoServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年9月8日 下午2:15:00
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class ReportInfoServiceImplTest extends BaseTestNGTest {

    @Resource
    private ReportInfoService reportInfoService;
    
    @Test
    public void insert() {
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setWaybillId(13142);
        reportInfo.setReportInfoType(6);
        reportInfo.setFirstReportTime(new Date());
        reportInfo.setRemark("测试备注不能为空");
        reportInfo.setRegionCode("22000000");
        reportInfo.setAreaCode("00040000");
        reportInfo.setTenantCode("000000000");
        reportInfoService.insert(reportInfo, new LoginUser(3255));
    }
}
