package com.juma.tgm.mq.service.impl;

import com.alibaba.fastjson.JSON;
import com.juma.tgm.mq.domain.AdjustToInvoiceEvent;
import com.juma.tgm.mq.domain.CustomerInvoiceEvent;
import com.juma.tgm.mq.domain.MsgEvent;
import com.juma.tgm.mq.domain.WaybillOperateChangeEvent;
import com.juma.tgm.mq.rabbit.send.MqSendService;
import com.juma.tgm.mq.service.MqService;
import com.juma.tgm.waybill.domain.WaybillOperateTrack;
import com.juma.tgm.waybill.domain.ai.Feature;
import java.math.BigDecimal;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MqServiceImpl implements MqService {

    private final Logger log = LoggerFactory.getLogger(MqServiceImpl.class);
    @Resource
    private MqSendService mqSendService;
    @Value("${ai.fanout.waybill.status.exchange}")
    private String waybillStatusExchange;
    @Value("${tms.waybill.operate.track.exchange}")
    private String waybillOperateTrackExchange;
    @Value("${tms.msg.event.common.exchange}")
    private String msgEventCommonExchange;
    @Value("${tms.msg.event.adjust.receive.invoice.queue}")
    private String adjustReceivableInvoiceQueue;
    @Value("${tms.msg.event.adjust.receive.invoice.exchange}")
    private String adjustReceivableInvoiceExchange;
    @Value("${tms.msg.event.receive.invoice.queue}")
    private String receivableInvoiceQueue;
    @Value("${tms.msg.event.receive.invoice.exchange}")
    private String receivableInvoiceExchange;
    @Value("${tms.waybill.operate.change.exchange}")
    private String tmsWaybillOperateChangeExchange;

    /**开票系统-api调用开关**/
    @Value("${tms.customer.reconciliation.invoice.switch}")
    private String openInvoiceDoor;

    @Override
    public void sendToDataCenter(Feature feature) {
        mqSendService.send(waybillStatusExchange, feature);
        log.info("sendToDataCenter:{}", JSON.toJSONString(feature));
    }

    @Override
    public void sendWaybillOperateTrack(WaybillOperateTrack waybillOperateTrack) {
        mqSendService.send(waybillOperateTrackExchange, waybillOperateTrack);
        log.info("sendWaybillOperateTrack:{}", JSON.toJSONString(waybillOperateTrack));
    }

    @Override
    public void sendMsgCommonMsg(MsgEvent msgEvent) {
        mqSendService.send(msgEventCommonExchange, msgEvent);
        log.info("sendMsgCommonMsg:{}", JSON.toJSONString(msgEvent));
    }

    @Override
    public void sendAdjustReceiveToInvoice(final String reconcilicationNo, final BigDecimal adjustAmount) {
        AdjustToInvoiceEvent event = new AdjustToInvoiceEvent();
        event.setSourceDocumentNo(reconcilicationNo);
        event.setAdjustmentAmount(adjustAmount.toPlainString());
        // 配置开关
        if( StringUtils.isNotBlank(openInvoiceDoor) && !Boolean.parseBoolean(openInvoiceDoor) ){
            log.info("客户-调整单-向开票系统发送点对点消息:{}, API开关未打开",JSON.toJSONString(event));
            return;
        }
        log.info("客户-调整单-对账后-向开票系统发送点对点消息:{}",JSON.toJSONString(event));
        mqSendService.send(adjustReceivableInvoiceExchange,adjustReceivableInvoiceQueue,event);
        log.info("客户-调整单-对账后-向开票系统发送点对点消息-发送成功");
    }

    @Override
    public void sendReceiveToInvoice(CustomerInvoiceEvent event) {
        if( null == event ){ return; }
        // 配置开关
        if( StringUtils.isNotBlank(openInvoiceDoor) && !Boolean.parseBoolean(openInvoiceDoor) ){
            log.info("客户-对账单-向开票系统发送点对点消息:{}, API开关未打开",JSON.toJSONString(event));
            return;
        }
        log.info("客户-对账单-向开票系统发送点对点消息:{}",JSON.toJSONString(event));
        mqSendService.send(receivableInvoiceExchange,receivableInvoiceQueue,event);
        log.info("客户-对账单-向开票系统发送点对点消息-发送成功");
    }

    @Override
    public void sendWaybillOperateChange(WaybillOperateChangeEvent event) {
        log.info("sendWaybillOperateChange:{}",JSON.toJSONString(event));
        mqSendService.send(tmsWaybillOperateChangeExchange, event);
        log.info("sendWaybillOperateChange success");
    }
}
