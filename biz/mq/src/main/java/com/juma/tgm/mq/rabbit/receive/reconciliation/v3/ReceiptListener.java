package com.juma.tgm.mq.rabbit.receive.reconciliation.v3;

import java.math.BigDecimal;
import java.nio.charset.Charset;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.juma.tgm.fms.service.v3.ReconciliationReceivableSyncService;

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
        ReconciliationReceivableSyncService reconciliationSyncService = applicationContext.getBean( ReconciliationReceivableSyncService.class );
        String jsonStr = new String(message.getBody(), Charset.forName("utf-8"));
        try {
            JSONObject json = JSONObject.parseObject(jsonStr);
            String data = json.get("data").toString();
            JSONArray jsonArray = JSONArray.parseArray(data);

            for( Object object : jsonArray.toArray()) {

                JSONObject jsonObject = (JSONObject) object;
                //对账单号
                String reconciliationNo = jsonObject.getString("sourceNoteNo");
                //应收金额
                BigDecimal receivableFreight = jsonObject.getBigDecimal("receivableAmount").divide(new BigDecimal(100));
                //已收金额
                BigDecimal hasReceiveFreight = jsonObject.getBigDecimal("receivedAmount").divide(new BigDecimal(100));
                //对账状态
                String receiveStatusStr = jsonObject.getString("receiveStatus");
                Integer receiveStatus = transReceiveStatus(receiveStatusStr);
                checkJsonMessage(reconciliationNo ,receivableFreight);
                reconciliationSyncService.receipt(reconciliationNo ,  receivableFreight, hasReceiveFreight, receiveStatus);
            }
        }catch ( Exception e ) {
            LOGGER.error("ReconciliationSync,同步收款状态异常:" + e.getMessage() , e );// 只输出日志，不抛出异常 避免不能ACK 导致 mq 消息阻塞
        }
        finally {
            LOGGER.info("ReconciliationSync,收到收款状态同步消息：" + jsonStr);
        }
    }

    //转化收款状态
    private Integer transReceiveStatus(String receiveStatusStr){

        Integer receiveStatus = null;

        if(StringUtils.isBlank(receiveStatusStr)){
            LOGGER.info("收款状态为空");
        }else if(receiveStatusStr.equals("never")){
            receiveStatus = 1;
        }else if(receiveStatusStr.equals("part")){
            receiveStatus = 2;
        }else if(receiveStatusStr.equals("whole")){
            receiveStatus = 3;
        }
        return receiveStatus;
    }

    private void checkJsonMessage(String reconciliationNo, BigDecimal receivableFreight){

        if( receivableFreight == null || receivableFreight.equals( BigDecimal.ZERO) ) {
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
