package com.juma.tgm.mq.rabbit.receive;

import java.nio.charset.Charset;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import com.alibaba.fastjson.JSON;
import com.juma.crm.customer.domain.MigrateCustomerMq;
import com.juma.tgm.mq.rabbit.sync.CustomerInfoDataMoveSync;

public class MessageCrmCustomerInfoDataMoveListener implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(MessageCrmCustomerInfoDataMoveListener.class);

    @Resource
    private CustomerInfoDataMoveSync customerInfoDataMoveSync;

    @Override
    public void onMessage(Message message) {

        log.info("同步crm数据迁移客户信息 start...");
        String jsonStr = new String(message.getBody(), Charset.forName("utf-8"));
        log.info("同步crm数据迁移客户信息参数{}.", jsonStr);

        try {
            if (StringUtils.isBlank(jsonStr)) {
                return;
            }
            MigrateCustomerMq mq = JSON.parseObject(jsonStr, MigrateCustomerMq.class);

            customerInfoDataMoveSync.update(mq);
        } catch (Exception e) {
            log.warn("同步crm数据迁移客户信息 异常：{}", e.getMessage(), e);
        }
    }
}
