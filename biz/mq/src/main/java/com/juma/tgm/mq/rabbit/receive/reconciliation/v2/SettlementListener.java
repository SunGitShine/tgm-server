package com.juma.tgm.mq.rabbit.receive.reconciliation.v2;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.juma.tgm.fms.service.v2.ReconciliationSyncService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.math.BigDecimal;
import java.nio.charset.Charset;

/***
 * @author huangxing
 *
 *
 *  结算状态 同步 监听器
 * */
public class SettlementListener implements MessageListener , ApplicationContextAware{


    private static final Logger LOGGER = LoggerFactory.getLogger( SettlementListener.class );

    private ApplicationContext applicationContext;

    @Override
    public void onMessage(Message message) {
        ReconciliationSyncService reconciliationSyncService = applicationContext.getBean( ReconciliationSyncService.class );
        String jsonStr = new String(message.getBody(), Charset.forName("utf-8"));
        JSONArray jsonArray = JSONArray.parseArray( jsonStr );
        for( Object object : jsonArray.toArray()) {
            JSONObject jsonObject = (JSONObject) object;
            String reconciliationNo = jsonObject.getString("reconciliationNo");
            String plateNumber = jsonObject.getString("plateNumber");
            Integer renterType = jsonObject.getInteger("renterType");
            Integer renterId = jsonObject.getInteger("renterId");
            Integer settlementState = jsonObject.getInteger("settlementState");
            BigDecimal settlement = jsonObject.getBigDecimal("settlement");
            try {
                checkJsonMessage(reconciliationNo, plateNumber, settlementState, settlement,renterType,renterId);
                reconciliationSyncService.settlement(reconciliationNo, plateNumber, settlementState, settlement,renterType,renterId);
            } catch (Exception e) {
                LOGGER.error("ReconciliationSync,同步结算状态异常:" + e.getMessage(), e);// 只输出日志，不抛出异常 避免不能ACK 导致 mq 消息阻塞
            } finally {
                LOGGER.info("ReconciliationSync,收到结算状态同步消息：" + jsonStr);
            }
        }
    }


    private void checkJsonMessage(String reconciliationNo,String plateNumber, Integer settlementState,BigDecimal settlement , Integer renterType,Integer renterId ){
        if( renterType == null ) {
            throw new RuntimeException("承租人类型为空" );
        }
        if(StringUtils.isBlank( reconciliationNo) ) {
            throw new RuntimeException("错误的运单号，运单号为空" );
        }
        if( StringUtils.isBlank( plateNumber ) &&  renterId == null) {
            throw new RuntimeException("车牌号和承租人id 不能同时为空");
        }
        if( settlementState == null ) {
            throw new RuntimeException("错误的结算状态，结算状态为空");
        }
        if( settlement == null || settlement.equals( BigDecimal.ZERO )) {
            throw new RuntimeException("错误的结算金额,结算金额为空或者为0");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
