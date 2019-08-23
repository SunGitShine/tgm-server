package com.juma.tgm.mq.service.impl.transactionListener;

import com.juma.tgm.mq.domain.WaybillOperateChangeEvent;
import com.juma.tgm.mq.service.MqService;
import com.juma.tgm.waybill.domain.TaxRate;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @ClassName MqTransactionListener
 * @Description TODO
 * @Author weilibin
 * @Date 2019-08-20 19:26
 * @Version 1.0.0
 */

@Component
public class MqTransactionListener {

    private final Logger log = LoggerFactory.getLogger(MqTransactionListener.class);
    @Resource
    private MqService mqService;

    @EventListener
    public void afterWaybillOperateChange(final WaybillOperateChangeEvent event) {
        TransactionSynchronizationManager.registerSynchronization(
            new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() {
                    log.info("afterWaybillOperateChange -> sendStart");
                    mqService.sendWaybillOperateChange(event);
                    log.info("afterWaybillOperateChange -> sendEnd");
                }
            });
    }
}
