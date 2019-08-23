package com.juma.tgm.mq.rabbit.receive;

import java.nio.charset.Charset;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import com.alibaba.fastjson.JSON;
import com.juma.auth.user.domain.LoginUser;
import com.juma.message.basic.service.MsgConfService;
import com.juma.message.domain.MsgConf;
import com.juma.message.domain.aliyun.VoiceReport;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.tools.service.MessagePushService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.WaybillService;

public class MessageVoiceListener implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(MessageVoiceListener.class);

    @Resource
    private MessagePushService messagePushService;

    @Resource
    private WaybillService waybillService;

    @Resource
    private DriverService driverService;
    
    @Resource
    private MsgConfService msgConfService;

    @Override
    public void onMessage(Message message) {

        log.info("消息中心语音电话回调 start...");

        try {
            String jsonStr = new String(message.getBody(), Charset.forName("utf-8"));
            log.info("消息中心语音电话回调 参数{}.",jsonStr);
            VoiceReport report = JSON.parseObject(jsonStr, VoiceReport.class);
            if (!report.getStatus_code().equals(VoiceReport.Status.T_200000.getCode())) {
                // out_id = "场景Id"+"-"+"运单Id"
                String outId = report.getOut_id();
                if(StringUtils.isBlank(outId)) return;
                String[] seg = outId.split("-");
                if (seg.length == 2) {
                    String msgConfId = seg[0];
                    Integer waybillId = Integer.valueOf(seg[1]);
                    Waybill waybill = waybillService.getWaybill(waybillId);
                    if (waybill != null) {
                        Driver driver = driverService.getDriver(waybill.getDriverId());
                        if (driver != null && null != driver.getSmsRemindSwitch() && NumberUtils
                                .compare(driver.getSmsRemindSwitch(), Driver.RemindSwitchValue.ON.getCode()) == 0) {
                            MsgConf msgConf = msgConfService.findMsgConfBy(Integer.valueOf(msgConfId));
                            if (msgConf != null) {
                                LoginUser loginUser = new LoginUser();
                                loginUser.setTenantId(waybill.getTenantId());
                                Map<String, Object> extras = messagePushService.buildAppMsgParameter(waybillId);
                                messagePushService.pushSmsMessage(msgConf.getSceneKey(), extras, loginUser,
                                        driver.getContactPhone());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {

        String jsonStr = "{\"status_code\":\"200005\",\"duration\":\"0\",\"end_time\":\"2017-09-08 16:30:02\",\"status_msg\":\"\",\"start_time\":null,\"out_id\":\"123456\",\"dtmf\":null,\"call_id\":\"112968924565^100589727518\"}";
        VoiceReport report = JSON.parseObject(jsonStr, VoiceReport.class);
        System.out.println(report);
    }

}
