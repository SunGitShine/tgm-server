package com.juma.tgm.util;


import com.giants.cache.redis.SpringDataRedisClient;
import com.juma.auth.employee.domain.Employee;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.service.ConfParamService;
import com.juma.tgm.cache.domain.WaybillWaitHandleCache;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.common.RedisKeyConstants;
import com.juma.tgm.tools.service.MessagePushService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.WaybillService;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName: CustomerManWaybillUtils
 * @Description: 用于处理客户经理抢单超时业务
 * @author: liang
 * @date: 2017-03-31 16:56
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
@Component
public class CustomerManWaybillUtils {

    Logger log = LoggerFactory.getLogger(getClass());


    /**
     * userId_key
     */
    private final static String KEY_USER_ID = "user_id";

    /**
     * planDeliveryTime_key
     */
    private final static String KEY_PLAN_DELIVERY_TIME = "planDeliveryTime";

    /**
     * waybillNo_key
     */
    private final static String KEY_WAYBILL_NO = "waybillNo";

    @Resource
    private SpringDataRedisClient redisClient;

    @Resource
    private MessagePushService messagePushService;

    @Resource
    private WaybillService waybillService;

    @Resource
    private ConfParamService confParamService;

    @Resource
    private EmployeeService employeeService;


    /**
     * 下单时添加倒计时记录
     * 通过waybillSource判断是否需要发送订单超时信息
     *
     * @param waybill   需要倒计时的订单 waybillId、waybillNo、planDeliveryTime、waybillSource不能为空
     * @param loginUser 客户经理userId
     */
    public void addWaitHandleWaybill(Waybill waybill, LoginUser loginUser) {
        if (waybill == null) {
            log.info("添加待派单倒计时错误;订单为空");
            return;
        }

        if (waybill.getWaybillId() == null) {
            log.info("添加待派单倒计时错误;订单id为空;订单id:{}", waybill.getWaybillId());
            return;
        }

        if (waybill.getWaybillSource() == null) {
            log.info("添加待派单倒计时错误;WaybillSource为空;订单id:{}", waybill.getWaybillId());
            return;
        }

        //不是司机抢单的运单不用发送
        if (NumberUtils.compare(Waybill.ReceiveWay.RECEIVED.getCode(), waybill.getReceiveWay()) != 0) {
            return;
        }

        String planDeliveryTime = "";
        if (waybill.getPlanDeliveryTime() != null) {
            planDeliveryTime = DateUtil.format(waybill.getPlanDeliveryTime(), DateUtil.YYYYMMDDHHMM);
        }

        long expireTime = getExpireTimeLength();

        WaybillWaitHandleCache waybillWaitHandleCache = new WaybillWaitHandleCache();
        waybillWaitHandleCache.setExpireTime(System.currentTimeMillis() + expireTime);
        waybillWaitHandleCache.setWaybillId(waybill.getWaybillId());
        waybillWaitHandleCache.setHandled(false);
        waybillWaitHandleCache.setAreaCode(waybill.getAreaCode());
        Map<String, String> data = new HashMap<String, String>();
        data.put(KEY_WAYBILL_NO, waybill.getWaybillNo());
        data.put(KEY_PLAN_DELIVERY_TIME, planDeliveryTime);
        
        
        Employee employee = employeeService.loadEmployee(waybill.getCustomerManagerId(), loginUser);
        
        data.put(KEY_USER_ID, employee == null || employee.getUserId() == null ? null :employee.getUserId() + "");
        waybillWaitHandleCache.setExtraData(data);
        
        try {
            redisClient.hmset(RedisKeyConstants.TMS_WAIT_HANDLE_WAYBILLS, waybill.getWaybillId(), waybillWaitHandleCache);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }

    /**
     * 获取超时时长（毫秒）
     *
     * @return
     */
    public long getExpireTimeLength() {
        long expireTimeLength = 10 * 60 * 1000;

        List<ConfParamOption> optionList = confParamService.findParamOptions(Constants.COMPETE_BILL_KEY_EXPIRE_TIME);

        if (CollectionUtils.isNotEmpty(optionList)) {
            ConfParamOption option = optionList.get(0);
            if (StringUtils.isNumeric(option.getOptionValue())) {
                expireTimeLength = Long.parseLong(option.getOptionValue()) * 60 * 1000;
            }
        } else {
            log.info("没有配置经纪人运单超时时间，使用默认值10分钟");
        }

        return expireTimeLength;
    }

    /**
     * 状态变更为非待派单后删除记录
     *
     * @param waybillId
     */
    public void delHandledWaybill(int waybillId) {
        try {
            redisClient.hdel(RedisKeyConstants.TMS_WAIT_HANDLE_WAYBILLS, waybillId);
        } catch (Exception e) {
            log.warn("删除经纪人订单超时通知错误wabbillId:{}", waybillId, e);
        }
        
    }

    /**
     * 轮询触发发送消息
     */
    public void sendWaitHandleExpireMsg(LoginUser loginUser) {
        Map<Serializable, Serializable> allBill = redisClient.hgetall(RedisKeyConstants.TMS_WAIT_HANDLE_WAYBILLS);

        if (MapUtils.isEmpty(allBill)) {
            return;
        }

        Long now = System.currentTimeMillis();
        for (Map.Entry<Serializable, Serializable> entry : allBill.entrySet()) {
            WaybillWaitHandleCache cache = (WaybillWaitHandleCache) entry.getValue();
            if (BooleanUtils.isFalse(cache.getHandled()) && NumberUtils.compare(now, cache.getExpireTime()) >= 0) {
                Map<String, String> cacheMap = cache.getExtraData();
                //发送给调度 !!!确认需要发送给哪些调度!!! 已确认接收人由后台配置来定
                if (cacheMap != null) {
                    Waybill waybill = waybillService.getWaybill(cache.getWaybillId());

                    if (waybill == null) {
                        //防止事务回滚导致的异常消息
                        this.delHandledWaybill(cache.getWaybillId());
                        continue;
                    }
                    try {
                        messagePushService.robWaybillTimeoutMessage(waybill.getWaybillId(), cacheMap,loginUser);
                    } catch (Exception e) {
                        log.warn("推送抢单超时消息错误,waybillId:{}", waybill.getWaybillId(), e);
                    }
                }

                // 修改消息发送状态
                cache.setHandled(true);
                redisClient.hmset(RedisKeyConstants.TMS_WAIT_HANDLE_WAYBILLS, cache.getWaybillId(), cache);
            }
        }
    }
}
