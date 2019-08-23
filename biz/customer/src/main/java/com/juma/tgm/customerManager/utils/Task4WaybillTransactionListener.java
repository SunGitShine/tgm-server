package com.juma.tgm.customerManager.utils;

import com.juma.tgm.customerManager.domain.Task4Waybill;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.annotation.Resource;

/**
 * @ClassName: Task4WaybillTransactionListener
 * @Description:
 * @author: liang
 * @date: 2018-09-28 14:55
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
@Component
public class Task4WaybillTransactionListener {

    @Resource
    private Task4WaybillRedisUtil task4WaybillRedisUtil;

    @TransactionalEventListener
    public void handleCommit(Task4Waybill task4Waybill) {
        task4WaybillRedisUtil.delAutoCreateBillDemandFromRedis(task4Waybill.getTaskId());
        task4WaybillRedisUtil.addAutoCreateBillDemandToRedis(task4Waybill);
    }

    @TransactionalEventListener
    public void delAutoCreate(int taskId) {
        task4WaybillRedisUtil.delAutoCreateBillDemandFromRedis(taskId);
    }
}
