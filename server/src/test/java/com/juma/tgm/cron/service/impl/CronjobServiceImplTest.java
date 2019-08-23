package com.juma.tgm.cron.service.impl;

import com.juma.tgm.cron.service.CronjobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import testng.BaseTestNGTest;

public class CronjobServiceImplTest extends BaseTestNGTest {

    @Autowired
    private CronjobService cronjobService;
    

    @Test
    public void planDeliveryTimeRemind() {
        cronjobService.planDeliveryTimeRemind();
    }
    
    @Test
    public void transportReportForTruckCustomerSms() {
        cronjobService.transportReportForTruckCustomerSms();
    }

    @Test
    public void task4WaybillTest(){
        cronjobService.task4Waybill();
    }
}
