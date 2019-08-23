package com.juma.tgm.messagePush.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.Assert;
import org.testng.annotations.Test;

import com.giants.common.tools.Page;
import com.juma.auth.user.domain.LoginUser;
import com.juma.message.domain.MsgAppRecord;
import com.juma.message.record.service.MsgAppRecordService;
import com.juma.tgm.common.Constants;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.tools.service.MessagePushService;
import com.juma.tgm.waybill.service.MsgWithBusinessService;
import com.juma.tgm.waybill.service.WaybillService;

import testng.BaseTestNGTest;

/**
 * @ClassName MessagePushServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年8月10日 下午5:22:06
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class MessagePushServiceImplTest  extends BaseTestNGTest {

    @Resource
    private MessagePushService messagePushService;
    @Resource
    private MsgAppRecordService msgAppRecordService;
    @Resource
    private WaybillService waybillService;
    @Resource
    private DriverService driverService;
    @Resource
    private MsgWithBusinessService msgWithBusinessService;
    
    @Test
    public void demo() {
        LoginUser loginUser = new LoginUser();
        loginUser.setTenantId(2);
        
        Map<String, Object> extras = new HashMap<String,Object>();
        extras.put("plateNumber", "川A12345");
        
        //司机
        //pushAppMessage("TEMPERATURE_ALERT_TO_DRIVER_APP", extras, loginUser, driver.getUserId()+"");
        messagePushService.pushVoiceMessage("TTS_134323957", extras, null, loginUser, "18782243933");
        
        //经济人
        //pushAppMessage("TEMPERATURE_ALERT_TO_CUSTOMER_MANAGER_APP", extras, loginUser, employeeInfo.getUserId()+"");
        //pushVoiceMessage("TTS_134323961", extras, null, loginUser, employeeInfo.getMobileNumber());
    }
    
    @Test
    public void robWaybillMessage() {
        messagePushService.robWaybillMessage(1513102, new LoginUser(2, 1));
    }
    
    @Test
    public void unreadRecordList() {
        Page<MsgAppRecord> unreadRecordList = msgAppRecordService.unreadRecordList("NOTICE", 22L, 1, 10);
        Assert.notEmpty(unreadRecordList.getResults());
    }
    
    @Test
    public void pushWaybillShouldFinishMsg() {
        waybillService.updateWaybillStatusToDeliverying(null, Constants.SYS_LOGIN_USER);
    }

    @Test
    public void test() {
        LoginUser loginUser = new LoginUser();
        loginUser.setTenantId(3);
        
        messagePushService.pushSmsMessage("SCATTERED_ASSIGNED_WAYBILL", null, loginUser, "15729678002");
//        messagePushService.pushSmsMessage("12354", new HashMap<String, Object>(), "1234567899");
//        messagePushService.pushSmsMessage("12354", new HashMap<String, Object>(), "1234567899", "1545784154");
    }
    
    @Test
    public void pushPointCarMsg() {
        msgWithBusinessService.pushPointCarMsg(150738, new LoginUser(4, 1));
    }
}
