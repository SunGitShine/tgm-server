package com.juma.tgm.mq.rabbit.receive;

import java.nio.charset.Charset;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import com.alibaba.fastjson.JSON;
import com.juma.tgm.common.Constants;
import com.juma.tgm.waybill.domain.FenceEvent;
import com.juma.tgm.waybill.service.WaybillAutoFenceServicve;

public class MessageFenceListener implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(MessageFenceListener.class);
    @Resource
    private WaybillAutoFenceServicve waybillAutoFenceServicve;

    @Override
    public void onMessage(Message message) {
        log.info("收到电子围栏MQ返回信息，执行操作Start...");
        try {
            String jsonStr = new String(message.getBody(), Charset.forName("utf-8"));
            log.info("收到电子围栏MQ返回信息：{}", jsonStr);
            if (StringUtils.isBlank(jsonStr)) {
                return;
            }

            FenceEvent fenceEvent = JSON.parseObject(jsonStr, FenceEvent.class);
            // 若MQ消息回传的参数不全，则停止操作
            String event = fenceEvent.getEvent();
            if (null == fenceEvent.getFenceId() || StringUtils.isBlank(event)) {
                return;
            }

            if (FenceEvent.TouchType.ENTRY.toString().toLowerCase().equals(event.toLowerCase())) {
                // 进入电子围栏的操作
                log.info("收到电子围栏MQ进入电子围栏的操作：{}", fenceEvent.getFenceId());
                waybillAutoFenceServicve.touchEntryPushMessage(fenceEvent, Constants.SYS_LOGIN_USER);
            } else if (FenceEvent.TouchType.EXIT.toString().toLowerCase().equals(event.toLowerCase())) {
                // 离开电子围栏的操作
                log.info("收到电子围栏MQ离开电子围栏的操作：{}", fenceEvent.getFenceId());
                waybillAutoFenceServicve.touchExitPushMessage(fenceEvent, Constants.SYS_LOGIN_USER);
            }
        } catch (Exception e) {
            log.warn("收到电子围栏MQ返回异常：{}", e.getMessage(), e);
        }
    }

}
