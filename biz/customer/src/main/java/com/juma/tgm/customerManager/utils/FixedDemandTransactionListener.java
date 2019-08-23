package com.juma.tgm.customerManager.utils;

import com.juma.tgm.customerManager.domain.FixedDemand;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.annotation.Resource;

/**
 * 处理事务提交成功后的事件
 *
 * @ClassName: FixedDemandTransactionListener
 * @Description:
 * @author: liang
 * @date: 2018-03-23 10:37
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
@Component
public class FixedDemandTransactionListener {

    @Resource
    private FixedDemandUtil fixedDemandUtil;

    @TransactionalEventListener
    public void handleCommit(FixedDemand fixedDemand) {
        fixedDemandUtil.delAutoCreateBillDemandFromRedis(fixedDemand.getFixedDemandId());
        fixedDemandUtil.addAutoCreateBillDemandToRedis(fixedDemand);
    }

    @TransactionalEventListener
    public void delAutoCreate(int fixedDemandId) {
        fixedDemandUtil.delAutoCreateBillDemandFromRedis(fixedDemandId);
    }

}
