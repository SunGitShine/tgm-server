package com.juma.tgm.fms.service;

import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.fms.service.v3.ReconcilicationForPayableService;
import javax.annotation.Resource;
import org.testng.annotations.Test;
import testng.BaseTestNGTest;

/**
 * @ClassName ReconcilicationForPayableServiceImplTest
 * @Description TODO
 * @Author weilibin
 * @Date 2019-07-15 16:48
 * @Version 1.0.0
 */

public class ReconcilicationForPayableServiceImplTest extends BaseTestNGTest {

    @Resource
    private ReconcilicationForPayableService reconcilicationForPayableService;

    @Test
    public void sendToFMS() {
        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setTenantId(19);
        loginEmployee.setUserId(32571);
        reconcilicationForPayableService.sendToFMS("AP20190613112507396008484", loginEmployee);
    }
}
