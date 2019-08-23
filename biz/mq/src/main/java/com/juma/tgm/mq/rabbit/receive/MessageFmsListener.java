package com.juma.tgm.mq.rabbit.receive;

import java.nio.charset.Charset;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import com.alibaba.fastjson.JSON;
import com.juma.fms.core.domain.SettlementBillDO;
import com.juma.tgm.mq.rabbit.sync.ReconciliationSync;

@Deprecated
public class MessageFmsListener implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(MessageFmsListener.class);
    @Resource
    private ReconciliationSync reconciliationSync;

    @Override
    public void onMessage(Message message) {
        log.info("FMS结算同步start...");
        String jsonStr = new String(message.getBody(), Charset.forName("utf-8"));
        log.info("FMS结算同步：收到FMS-MQ返回信息：{}", jsonStr);
        try {
            if (StringUtils.isBlank(jsonStr)) {
                return;
            }

            SettlementBillDO settlementBillDO = JSON.parseObject(jsonStr, SettlementBillDO.class);
//            reconciliationSync.updateToCheckout(settlementBillDO.getNo());
        } catch (Exception e) {
            log.warn("FMS结算同步：FMS-MQ同步信息未成功，返回信息：{}", jsonStr, e);
        }
    }
}
