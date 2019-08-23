package com.juma.tgm.mq.rabbit.receive;

import com.alibaba.fastjson.JSON;
import com.juma.tgm.mq.domain.UserOperateMQ;
import com.juma.tgm.mq.rabbit.sync.DriverSync;
import java.nio.charset.Charset;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class MessageAuthUserListener implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(MessageAuthUserListener.class);
    @Resource
    private DriverSync driverSync;

    @Override
    public void onMessage(Message message) {
        log.info("用户中心用户同步start...");
        String jsonStr = new String(message.getBody(), Charset.forName("utf-8"));
        log.info("用户中心用户同步：收到用户中心MQ返回信息：{}", jsonStr);
        try {
            if (StringUtils.isBlank(jsonStr)) {
                return;
            }

            UserOperateMQ userOperateMQ = JSON.parseObject(jsonStr, UserOperateMQ.class);
            String operate = userOperateMQ.getOperate();
            if (com.juma.auth.user.domain.UserOperateMQ.Operate.UPDATE.toString().equals(operate)) {
                // 司机同步
                driverSync.updateByAuth(userOperateMQ.getUserId());
            }
        } catch (Exception e) {
            log.warn("用户中心用户同步：用户中心MQ同步信息未成功，返回信息：{}", jsonStr, e);
        }
    }
}
