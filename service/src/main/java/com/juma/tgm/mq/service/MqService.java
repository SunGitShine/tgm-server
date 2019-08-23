package com.juma.tgm.mq.service;

import com.juma.tgm.mq.domain.CustomerInvoiceEvent;
import com.juma.tgm.mq.domain.MsgEvent;
import com.juma.tgm.mq.domain.WaybillOperateChangeEvent;
import com.juma.tgm.mq.enumeration.WaybillOperateChangeEnum;
import com.juma.tgm.waybill.domain.WaybillOperateTrack;
import com.juma.tgm.waybill.domain.ai.Feature;

import java.math.BigDecimal;

public interface MqService {

    void sendToDataCenter(Feature feature);

    /**
     * 运单操作记录
     *
     * @param waybillOperateTrack
     */
    void sendWaybillOperateTrack(WaybillOperateTrack waybillOperateTrack);

    /**
     * 公共MQ发送，用于tgm系统发送且tgm系统消费
     *
     * @param msgEvent
     */
    void sendMsgCommonMsg(MsgEvent msgEvent);

    /**客户-调整单-推送开票系统消息**/
    void sendAdjustReceiveToInvoice(String reconcilicationNo, BigDecimal adjustAmount);

    /**客户-对账单-推送开票系统消息**/
    void sendReceiveToInvoice(CustomerInvoiceEvent event);

    /**
     * 运单派车、到仓等事件通知
     *
     * @param event
     */
    void sendWaybillOperateChange(WaybillOperateChangeEvent event);
}
