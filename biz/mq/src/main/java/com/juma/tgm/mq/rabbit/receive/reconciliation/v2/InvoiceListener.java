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

import java.nio.charset.Charset;

/***
 *
 * @author huangxing
 *
 * 开票状态 同步 监听器
 *
 * */
public class InvoiceListener implements MessageListener , ApplicationContextAware{


    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceListener.class);

    private ApplicationContext applicationContext;


    @Override
    public void onMessage(Message message) {
        ReconciliationSyncService reconciliationSyncService = applicationContext.getBean( ReconciliationSyncService.class );
        String jsonStr = new String(message.getBody(), Charset.forName("utf-8"));
        JSONObject jsonObject = JSONObject.parseObject( jsonStr );
        String reconciliationNo = jsonObject.getString("reconciliationNo");
        String invoiceNo = jsonObject.getString("invoiceNo");
        try {
            checkJsonMessage( reconciliationNo , invoiceNo );
            reconciliationSyncService.invoice( reconciliationNo , invoiceNo);
        }
        catch ( Exception e ) {
            LOGGER.error("ReconciliationSync,同步开票状态异常:" + e.getMessage() , e );// 只输出日志，不抛出异常 避免不能ACK 导致 mq 消息阻塞
        }
        finally {
            LOGGER.info("ReconciliationSync,收到开票同步消息：" + jsonStr);
        }
    }


    private void checkJsonMessage( String reconciliationNo ,String invoiceNo ){
        if( StringUtils.isBlank( invoiceNo )) {
            throw new RuntimeException("错误的票号，票号为空" );
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
