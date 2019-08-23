package com.juma.tgm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class DriverArriveRedisUtils implements InitializingBean {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final String TMS_WAYBILL_DRIVER_ARRIVE_DEPOT_SET = "tms_waybill_driver_arrive_depot_set";

    BoundSetOperations<String, String> driverArriveDepotSet;

    @Override
    public void afterPropertiesSet() throws Exception {
        driverArriveDepotSet = stringRedisTemplate.boundSetOps(TMS_WAYBILL_DRIVER_ARRIVE_DEPOT_SET);
        driverArriveDepotSet.expire(3, TimeUnit.DAYS);
    }

    public void remove(Integer waybillId) {
        driverArriveDepotSet.remove(waybillId + "");
    }

    public void driverArriveDept(Integer waybillId) {
        driverArriveDepotSet.add(waybillId + "");
    }

    public List<Integer> waitingToDeliveriedWaybills() {
        List<Integer> waybillIds = new ArrayList<Integer>();
        Set<String> waybills = driverArriveDepotSet.members();
        for (String id : waybills) {
            waybillIds.add(Integer.valueOf(id));
        }
        return waybillIds;
    }
}
