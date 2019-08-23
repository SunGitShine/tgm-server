package com.juma.tgm.mq.rabbit.receive;

import com.juma.tgm.mq.domain.VmsEvent;
import java.nio.charset.Charset;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import com.alibaba.fastjson.JSON;
import com.juma.tgm.mq.rabbit.sync.VendorMappingSync;

public class MessageVmsVendorListener implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(MessageVmsVendorListener.class);

    @Resource
    private VendorMappingSync vendorMappingSync;

    @Override
    public void onMessage(Message message) {
        log.info("承运商信息同步start...");
        String jsonStr = new String(message.getBody(), Charset.forName("utf-8"));
        log.info("承运商信息同步：收到资产管理MQ返回信息：{}", jsonStr);
        try {
            if (StringUtils.isBlank(jsonStr)) {
                return;
            }

            VmsEvent vmEvent = JSON.parseObject(jsonStr, VmsEvent.class);
            vendorMappingSync.modify(vmEvent.getVendorId(), vmEvent.getTenantId());
        } catch (Exception e) {
            log.warn("承运商信息同步：资产管理MQ同步信息未成功，失败原因：{}，返回信息：{}", e.getMessage(), jsonStr);
        }
    }

}
