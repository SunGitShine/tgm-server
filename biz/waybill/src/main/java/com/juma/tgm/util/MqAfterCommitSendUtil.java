package com.juma.tgm.util;

import com.juma.tgm.mq.domain.WaybillOperateChangeEvent;
import com.juma.tgm.mq.enumeration.WaybillOperateChangeEnum;
import javax.annotation.Resource;
import me.about.example.Waybill;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @ClassName MqAfterCommitSendUtil
 * @Description TODO
 * @Author weilibin
 * @Date 2019-08-21 10:04
 * @Version 1.0.0
 */

@Component
public class MqAfterCommitSendUtil {

    @Resource
    private ApplicationEventPublisher publisher;

    public void afterWaybillOperateChangeCommit(Integer waybillId,
        WaybillOperateChangeEnum waybillOperateChangeEnum) {

        if (null == waybillId || null == waybillOperateChangeEnum) {
            return;
        }

        WaybillOperateChangeEvent event = new WaybillOperateChangeEvent();
        event.setWaybillId(waybillId);
        event.setWaybillOperateChangeEnum(waybillOperateChangeEnum);

        publisher.publishEvent(event);
    }
}
