package com.juma.tgm.mq.rabbit.receive;

import java.nio.charset.Charset;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import com.alibaba.fastjson.JSON;
import com.juma.tgm.crm.domain.CrmEvent;
import com.juma.tgm.mq.rabbit.sync.CustomerInfoSync;

public class MessageCrmCustomerInfoListener implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(MessageCrmCustomerInfoListener.class);

    @Resource
    private CustomerInfoSync customerInfoSync;

    @Override
    public void onMessage(Message message) {

        log.info("同步crm客户信息 start...");
        String jsonStr = new String(message.getBody(), Charset.forName("utf-8"));
        log.info("同步crm客户信息参数{}.", jsonStr);
        
        try {
            if (StringUtils.isBlank(jsonStr)) {
                return;
            }
            CrmEvent crmEvent = JSON.parseObject(jsonStr, CrmEvent.class);

            customerInfoSync.insertOrUpdate(crmEvent.getCustomerId());
        } catch (Exception e) {
            log.warn("同步crm客户信息 异常：{}", e.getMessage(), e);
        }
    }
}
