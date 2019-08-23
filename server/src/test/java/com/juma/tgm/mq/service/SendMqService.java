package com.juma.tgm.mq.service;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.juma.tgm.configure.service.ConfigParamService;
import com.juma.tgm.fms.service.impl.SendService;

import testng.BaseTestNGTest;

public class SendMqService extends BaseTestNGTest {

    @Resource
    private SendService sendService;
    
    @Resource
    private ConfigParamService configParamService;
    
    @Test
    public void sendTest() {
       // sendService.send("hello");
        
        configParamService.excuteSQL("update waybill set driver_id = 44 where waybill_id = 1779;");
    }
    
}
