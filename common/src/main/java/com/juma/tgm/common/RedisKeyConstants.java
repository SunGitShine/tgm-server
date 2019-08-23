package com.juma.tgm.common;

/**
 * @ClassName: RedisKeyConstants
 * @Description: redisKey常量类,用于存放redis中的key值
 *
 * 格式:项目名_功能（任务）_存放内容[_KEY｜ＩＤ］
 *
 * @author: liang
 * @date: 2017-03-30 18:06
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public interface RedisKeyConstants {

    /**
     * 记录订单下单后未处理的运单
     */
    String TMS_WAIT_HANDLE_WAYBILLS = "TMS_WAIT_HANDLE_WAYBILLS";

    /**
     * 当前登录货主缓存业务id
     */
    String TMS_LOGIN_BUSINESS_INFO_TRUCK_CUSTOMER_KEY = "TMS_LOGIN_BUSINESS_INFO_TRUCK_CUSTOMER_KEY_";

    /**
     * 当前登录司机缓存业务id
     */
    String TMS_LOGIN_BUSINESS_INFO_DRIVER_KEY = "TMS_LOGIN_BUSINESS_INFO_DRIVER_KEY_";

    /**
     * 缓存车型详情，包含车型名称
     */
    String TGM_LIST_TRUCK_TYPE_CONTAIN_TYPE_NAME_KEY = "TGM_LIST_TRUCK_TYPE_CONTAIN_TYPE_NAME";
    
    /**
     * 用车时间前2小时 、1小时、半小时提醒
     */
    String TMS_PLAN_DELIVERY_TIME_REMIND_KEY = "TMS_PLAN_DELIVERY_TIME_REMIND_KEY";

    /**
     * 固定需求自动建单redis key
     */
    String TMS_FIXED_DEMAND_AUTO_CREATE_BILL_KEY = "TMS_FIXED_DEMAND_AUTO_CREATE_BILL_KEY_";


    /**
     * 定时下单自动建单redis_key
     */
    String TMS_TASK_4_WAYBILL_AUTO_CREATE_BILL_KEY = "TMS_TASK_4_WAYBILL_AUTO_CREATE_BILL_KEY_";




}
