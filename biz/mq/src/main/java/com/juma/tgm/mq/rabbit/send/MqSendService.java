package com.juma.tgm.mq.rabbit.send;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;

public class MqSendService {

    private final static Logger log = LoggerFactory.getLogger("mqAppender");

    private AmqpTemplate amqpTemplate;

    // 只能发广播
    public void send(String exchange,Object transport) {
        log.info("广播消息发送开始-exchange:{},消息体:{}",exchange, JSON.toJSONString(transport));
        amqpTemplate.convertAndSend(exchange, null, transport);
        log.info("广播消息发送完毕-exchange:{}",exchange);
    }

    // 广播或点对点
    public void send(String exchange, String routingKey,Object transport) {
        log.info("点对点消息发送开始-exchange:{},queue:{},消息体:{}",exchange, routingKey, JSON.toJSONString(transport));
        amqpTemplate.convertAndSend(exchange, routingKey, transport);
        log.info("点对点消息发送完毕-exchange:{},queue:{},",exchange, routingKey);
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }
    

}
