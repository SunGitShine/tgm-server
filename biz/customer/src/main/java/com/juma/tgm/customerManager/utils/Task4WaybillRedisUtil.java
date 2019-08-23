package com.juma.tgm.customerManager.utils;

import com.giants.cache.redis.RedisClient;
import com.juma.tgm.common.RedisKeyConstants;
import com.juma.tgm.customerManager.domain.Task4Waybill;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.TaskWaybillTemplateEnum;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName: Task4WaybillRedisUtil
 * @Description:
 * @author: liang
 * @date: 2018-09-28 14:56
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
@Component
public class Task4WaybillRedisUtil {

    @Resource
    private RedisClient redisClient;


    //需要自动下单的固定需求存入redis
    public void addAutoCreateBillDemandToRedis(Task4Waybill task4Waybill) {
        if (task4Waybill == null) return;
        if (task4Waybill.getIsClose() == null) return;
        if (task4Waybill.getTaskWaybillTemplateId() == null) return;

        if (NumberUtils.compare(task4Waybill.getIsClose(), TaskWaybillTemplateEnum.AutoCreateBillEnum.NO_AUTO_CREATE.getCode()) == 0) return;



        //写入需要自动简单的固定需求
        redisClient.set(RedisKeyConstants.TMS_TASK_4_WAYBILL_AUTO_CREATE_BILL_KEY + task4Waybill.getTaskId(), task4Waybill);

    }

    //删除自动下单的taskId redis记录
    public void delAutoCreateBillDemandFromRedis(int taskId) {
        redisClient.del(RedisKeyConstants.TMS_TASK_4_WAYBILL_AUTO_CREATE_BILL_KEY + taskId);
    }
}
