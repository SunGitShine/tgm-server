package com.juma.tgm.mq.rabbit.receive;

import com.alibaba.fastjson.JSON;
import com.juma.tgm.waybill.domain.WaybillOperateTrack;
import com.juma.tgm.waybill.service.WaybillOperateTrackService;
import java.nio.charset.Charset;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class MessageWaybillOperateTrackListener implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(MessageWaybillOperateTrackListener.class);
    @Resource
    private WaybillOperateTrackService waybillOperateTrackService;

    @Override
    public void onMessage(Message message) {
        log.info("运单操作记录start...");
        String jsonStr = new String(message.getBody(), Charset.forName("utf-8"));
        log.info("运单操作记录MQ返回信息：{}", jsonStr);
        try {
            if (StringUtils.isBlank(jsonStr)) {
                return;
            }

            WaybillOperateTrack waybillOperateTrack = JSON.parseObject(jsonStr, WaybillOperateTrack.class);

            if (null == waybillOperateTrack) {
                return;
            }

            waybillOperateTrackService.updateByMq(waybillOperateTrack);
        } catch (Exception e) {
            log.warn("运单操作记录MQ信息未成功，返回信息：{}", jsonStr, e);
        }
    }
}
