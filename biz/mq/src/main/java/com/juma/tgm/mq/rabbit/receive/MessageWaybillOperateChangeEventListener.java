package com.juma.tgm.mq.rabbit.receive;

import com.alibaba.fastjson.JSON;
import com.juma.tgm.mq.domain.WaybillOperateChangeEvent;
import com.juma.tgm.mq.enumeration.WaybillOperateChangeEnum;
import com.juma.tgm.task.enums.TaskEnum;
import com.juma.tgm.task.service.TaskCalendarService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import javax.annotation.Resource;
import java.nio.charset.Charset;

public class MessageWaybillOperateChangeEventListener implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(MessageWaybillOperateChangeEventListener.class);

    @Resource
    private TaskCalendarService taskCalendarService;

    @Override
    public void onMessage(Message message) {

        String jsonStr = new String(message.getBody(), Charset.forName("utf-8"));
        log.info("运单操作节点MQ：{}", jsonStr);
        try {
            if (StringUtils.isBlank(jsonStr)) {
                return;
            }

            WaybillOperateChangeEvent waybillOperateChangeEvent = JSON.parseObject(jsonStr, WaybillOperateChangeEvent.class);

            if (null == waybillOperateChangeEvent || null == waybillOperateChangeEvent.getWaybillOperateChangeEnum()) {
                return;
            }

            //到仓
            if (waybillOperateChangeEvent.getWaybillOperateChangeEnum() == WaybillOperateChangeEnum.DRIVER_ARRIVE_DEPOT) {
                taskCalendarService.updateDeliveryStatus(waybillOperateChangeEvent.getWaybillId(), TaskEnum.DeliveryStatus.Arrive_Depot);
            }
            //离仓
            if (waybillOperateChangeEvent.getWaybillOperateChangeEnum() == WaybillOperateChangeEnum.DRIVER_LEAVE_DEPOT) {
                taskCalendarService.updateDeliveryStatus(waybillOperateChangeEvent.getWaybillId(), TaskEnum.DeliveryStatus.Leave_Depot);
            }

        } catch (Exception e) {
            log.warn("运单操作节点MQ信息未成功，返回信息：{}", jsonStr, e);
        }

    }

    public static void main(String[] args) {
        WaybillOperateChangeEvent waybillOperateChangeEvent = JSON.parseObject(" {\"waybillId\":1523170,\"waybillOperateChangeEnum\":\"DRIVER_ARRIVE_DEPOT\"}", WaybillOperateChangeEvent.class);
        System.out.println(waybillOperateChangeEvent.getWaybillOperateChangeEnum());
    }
}
