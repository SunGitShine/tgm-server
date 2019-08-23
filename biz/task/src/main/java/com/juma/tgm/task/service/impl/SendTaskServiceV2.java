package com.juma.tgm.task.service.impl;

import org.springframework.amqp.core.AmqpTemplate;

/**
 * @description: ${description}
 *
 * @author: xieqiang
 *
 * @create: 2019-05-31 15:20
 **/
public class SendTaskServiceV2 {

	private AmqpTemplate amqpTemplate;

	private String exchange;

	public void send(Object transport) {
		amqpTemplate.convertAndSend(exchange, null, transport);
	}

	public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
}
