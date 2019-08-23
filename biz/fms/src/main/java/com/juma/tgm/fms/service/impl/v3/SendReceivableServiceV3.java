package com.juma.tgm.fms.service.impl.v3;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;

public class SendReceivableServiceV3 {

    private final static Logger log = LoggerFactory.getLogger("mqAppender");

    private AmqpTemplate amqpTemplate;

    private String exchange;

    public void send(Object transport) {
        log.info("广播消息发送开始-exchange:{},消息体:{}",exchange, JSON.toJSONString(transport));
        amqpTemplate.convertAndSend(exchange, null, transport);
        log.info("广播消息发送完毕-exchange:{}",exchange);
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}
