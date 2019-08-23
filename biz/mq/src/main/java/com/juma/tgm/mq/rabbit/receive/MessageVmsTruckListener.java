package com.juma.tgm.mq.rabbit.receive;

import com.alibaba.fastjson.JSON;
import com.juma.tgm.mq.domain.VmsEvent;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.vms.truck.domain.Truck;
import java.nio.charset.Charset;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class MessageVmsTruckListener implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(MessageVmsTruckListener.class);
    @Resource
    private VmsCommonService vmsCommonService;

    // 资产管理
    @Override
    public void onMessage(Message message) {
        log.info("VMS车辆信息同步start...");
        String jsonStr = new String(message.getBody(), Charset.forName("utf-8"));
        log.info("VMS车辆信息同步：收到MQ返回信息：{}", jsonStr);
        try {
            if (StringUtils.isBlank(jsonStr)) {
                return;
            }

            VmsEvent vmsEvent = JSON.parseObject(jsonStr, VmsEvent.class);
            if (null == vmsEvent || null == vmsEvent.getTruckId()) {
                return;
            }

            Truck truck = vmsCommonService.loadTruckByTruckId(vmsEvent.getTruckId());
            if (null == truck || StringUtils.isBlank(truck.getPlateNumber())) {
                return;
            }

            // TODO 更改车队关联车辆车牌号，未启用，启用时请自xml配置

        } catch (Exception e) {
            log.warn("VMS车辆信息同步：MQ同步信息未成功，返回信息：{}", jsonStr, e);
        }
    }
}
