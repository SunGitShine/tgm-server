package com.juma.tgm.mq.rabbit.receive;

import com.alibaba.fastjson.JSON;
import com.juma.message.gateway.service.MessageServiceProvider;
import com.juma.tgm.mq.domain.MsgEvent;
import com.juma.tgm.mq.enumeration.MsgEventEnum;
import com.juma.tgm.mq.enumeration.MsgTypeEnum;
import java.nio.charset.Charset;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class MessageMsgEventCommonListener implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(MessageMsgEventCommonListener.class);
    @Resource
    private MessageServiceProvider messageServiceProvider;

    @Override
    public void onMessage(Message message) {
        log.info("msgEventCommonstart...");
        String jsonStr = new String(message.getBody(), Charset.forName("utf-8"));
        log.info("msgEventCommon：{}", jsonStr);

        try {
            MsgEvent msgEvent = JSON.parseObject(jsonStr, MsgEvent.class);
            if (null == msgEvent || null == msgEvent.getMsgTypeEnum() || null == msgEvent.getMsgEventEnum()
                || StringUtils.isBlank(msgEvent.getSceneKey()) || null == msgEvent.getTos() || msgEvent.getTos().length == 0) {
                return;
            }

            if (msgEvent.getMsgTypeEnum().equals(MsgTypeEnum.APP)) {
                this.pushApp(msgEvent);
            } else if (msgEvent.getMsgTypeEnum().equals(MsgTypeEnum.SMS)) {
                this.pushSms(msgEvent);
            } else if (msgEvent.getMsgTypeEnum().equals(MsgTypeEnum.EMAIL)) {
                this.pushEmail(msgEvent);
            } else if (msgEvent.getMsgTypeEnum().equals(MsgTypeEnum.VOICE)) {
                this.pushVoice(msgEvent);
            }


        } catch (Exception e) {
            log.warn("msgEventCommon failed：{}, errorMsg:{}", jsonStr, e.getMessage());
        }
    }

    private void pushApp(MsgEvent msgEvent) {
        if (msgEvent.getMsgEventEnum().equals(MsgEventEnum.STANDARD)) {
            messageServiceProvider.pushAppMessage(msgEvent.getSceneKey(), msgEvent.getExtras(), msgEvent.getTos());
        } else if (msgEvent.getMsgEventEnum().equals(MsgEventEnum.WITH_TENANT)) {
            messageServiceProvider.pushAppMessage(msgEvent.getTenantId(), msgEvent.getSceneKey(),
                msgEvent.getExtras(), msgEvent.getTos());
        } else if (msgEvent.getMsgEventEnum().equals(MsgEventEnum.NEED_MUST_ID)) {
            messageServiceProvider.pushAppMessage(msgEvent.getSceneKey(), msgEvent.getExtras(), msgEvent.isMustRead()
                , msgEvent.getExtMsgId(), msgEvent.getTos());
        }
    }

    private void pushSms(MsgEvent msgEvent) {
        if (msgEvent.getMsgEventEnum().equals(MsgEventEnum.STANDARD)) {
            messageServiceProvider.pushSmsMessage(msgEvent.getSceneKey(), msgEvent.getExtras(), msgEvent.getTos());
        } else if (msgEvent.getMsgEventEnum().equals(MsgEventEnum.WITH_TENANT)) {
            messageServiceProvider.pushSmsMessage(msgEvent.getTenantId(), msgEvent.getSceneKey(),
                msgEvent.getExtras(), msgEvent.getTos());
        }
    }

    private void pushEmail(MsgEvent msgEvent) {
        if (msgEvent.getMsgEventEnum().equals(MsgEventEnum.STANDARD)) {
            messageServiceProvider.pushEmailMessage(msgEvent.getSceneKey(), msgEvent.getExtras(), msgEvent.getTos());
        }
    }

    private void pushVoice(MsgEvent msgEvent) {
        if (msgEvent.getMsgEventEnum().equals(MsgEventEnum.NEED_MUST_ID)) {
            messageServiceProvider.pushVoiceMessage(msgEvent.getSceneKey(), msgEvent.getExtras(),
                msgEvent.getExtMsgId(), msgEvent.getTos());
        } else if (msgEvent.getMsgEventEnum().equals(MsgEventEnum.WITH_TENANT)) {
            messageServiceProvider
                .pushVoiceMessage(msgEvent.getTenantId(), msgEvent.getSceneKey(), msgEvent.getExtras(),
                    msgEvent.getExtMsgId(), msgEvent.getTos());
        }
    }
}
