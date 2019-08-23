package com.juma.tgm.mq.rabbit.receive.reconciliation.v3;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.juma.tgm.fms.service.v3.ReconciliationForPayableSyncService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.nio.charset.Charset;

/***
 *  结算状态 同步 监听器 v3
 * */

public class SettlementListener implements MessageListener , ApplicationContextAware{

    private static final Logger LOGGER = LoggerFactory.getLogger( SettlementListener.class );

    private ApplicationContext applicationContext;

    @Override
    public void onMessage(Message message) {
        LOGGER.info("结算状态监听start");
        ReconciliationForPayableSyncService reconciliationForPayableSyncService = applicationContext.getBean( ReconciliationForPayableSyncService.class );
        String jsonStr = new String(message.getBody(), Charset.forName("utf-8"));
        try {
            String data = JSONObject.parseObject(jsonStr).getString("data");
            JSONArray jsonArray = JSONArray.parseArray(data);
            LOGGER.info("结算状态监听message :{}",JSONArray.toJSONString(jsonArray));
            for(Object object : jsonArray.toArray()) {
                JSONObject jsonObject = (JSONObject) object;
                //子账单号
                String subReconciliationNo = jsonObject.getString("sourceNoteNo");
                //结算状态
                String settlementStatus = jsonObject.getString("settlementStatus");
                if(StringUtils.isBlank(subReconciliationNo) || StringUtils.isBlank(settlementStatus)){
                    continue;
                }
                reconciliationForPayableSyncService.settlement(subReconciliationNo, settlementStatus);
            }
        }catch (Exception e) {
            // 只输出日志，不抛出异常 避免不能ACK 导致 mq 消息阻塞
            LOGGER.error("ReconciliationSync,同步结算状态异常:" + e.getMessage(), e);
        }finally {
            LOGGER.info("ReconciliationSync,收到结算状态同步消息：" + jsonStr);
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
