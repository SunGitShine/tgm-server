package com.juma.tgm.customerManager.utils;

import com.giants.cache.redis.RedisClient;
import com.juma.tgm.common.RedisKeyConstants;
import com.juma.tgm.customerManager.domain.FixedDemand;
import com.juma.tgm.customerManager.service.vo.FixedDemandVo;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 固定需求工具
 *
 * @ClassName: FixedDemandUtil
 * @Description:
 * @author: liang
 * @date: 2018-03-23 10:08
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
@Component
public class FixedDemandUtil {

    @Resource
    private RedisClient redisClient;


    //需要自动下单的固定需求存入redis
    public void addAutoCreateBillDemandToRedis(FixedDemand fixedDemand) {
        if (fixedDemand == null) return;
        if (fixedDemand.getIsAutoCreateBill() == null) return;
        if (fixedDemand.getFixedDemandId() == null) return;

        if (NumberUtils.compare(fixedDemand.getIsAutoCreateBill(), 1) == 0) return;

        FixedDemandVo vo = new FixedDemandVo();
        vo.setFixedDemand(fixedDemand);

        FixedDemand.BillStrategy strategy =  vo.billStrategyToObject();

        if(strategy == null) return ;

        strategy.setFixedDemandId(fixedDemand.getFixedDemandId());

        //写入需要自动简单的固定需求
        redisClient.set(RedisKeyConstants.TMS_FIXED_DEMAND_AUTO_CREATE_BILL_KEY + fixedDemand.getFixedDemandId(), strategy);

    }

    //删除自动下单的固定需求redis记录
    public void delAutoCreateBillDemandFromRedis(int fixedDemandId) {
        redisClient.del(RedisKeyConstants.TMS_FIXED_DEMAND_AUTO_CREATE_BILL_KEY + fixedDemandId);
    }

    //固定需求转为运单格式并建单
}
