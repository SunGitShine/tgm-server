package com.juma.tgm.mq.rabbit.receive.reconciliation.v2;


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
 * @author  huangxing
 *
 *
 * 收款状态 同步 监听器
 *
 *
 * */
public class ReceiptListener implements MessageListener, ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger( ReceiptListener.class );

    private ApplicationContext applicationContext;

    @Override
    public void onMessage(Message message) {
        ReconciliationSyncService reconciliationSyncService = applicationContext.getBean( ReconciliationSyncService.class );
        String jsonStr = new String(message.getBody(), Charset.forName("utf-8"));
        JSONObject jsonObject = JSONObject.parseObject( jsonStr );
        String reconciliationNo = jsonObject.getString("reconciliationNo");
//        Integer customerId = jsonObject.getInteger("customerId");
//        String customerName = jsonObject.getString("customerName");
        BigDecimal receipt = jsonObject.getBigDecimal("receipt");
        try {
            checkJsonMessage( reconciliationNo ,receipt);
            reconciliationSyncService.receipt( reconciliationNo ,  receipt);
        }
        catch ( Exception e ) {
            LOGGER.error("ReconciliationSync,同步收款状态异常:" + e.getMessage() , e );// 只输出日志，不抛出异常 避免不能ACK 导致 mq 消息阻塞
        }
        finally {
            LOGGER.info("ReconciliationSync,收到收款状态同步消息：" + jsonStr);
        }
    }

    private void checkJsonMessage(  String reconciliationNo,BigDecimal receipt ){

        if( receipt == null || receipt.equals( BigDecimal.ZERO) ) {
            throw new RuntimeException("错误的收款金额,收款金额为空或者为0");
        }
        if(StringUtils.isBlank( reconciliationNo ) ) {
            throw new RuntimeException("错误的运单号，运单号为空" );
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
