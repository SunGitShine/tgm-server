package com.juma.tgm.util;

import com.giants.cache.redis.RedisClient;
import com.juma.auth.employee.domain.Employee;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.message.domain.MsgConf;
import com.juma.message.gateway.service.MessageServiceProvider;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.customer.domain.TruckCustomer;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.tools.service.MessagePushService;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.MsgWithBusinessService;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 带业务逻辑的发送消息
 *
 * @ClassName: MsgWithBusinessUtils
 * @author: liang
 * @date: 2017-04-05 18:35
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
@Service
public class MsgWithBusinessUtils implements MsgWithBusinessService {

    private static final Logger log = LoggerFactory.getLogger(MsgWithBusinessUtils.class);

    @Resource
    private MessagePushService messagePushService;

    @Resource
    private DriverService driverService;

    @Resource
    private TruckService truckService;

    @Resource
    private TruckTypeService truckTypeService;

    @Resource
    private TruckRequireService truckRequireService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private WaybillService waybillService;

    @Autowired
    private RedisClient redisClient;
    
    @Resource
    private MessageServiceProvider messageServiceProvider;

    /**
     * 发送修改运单计划时间的推送消息
     *
     * @param waybill
     * @param waybill
     */
    @Override
    public void modifyPlanDeliveryTimeMsg(Waybill waybill) {

        try {
            if (NumberUtils.compare(Waybill.StatusView.WATING_DELIVERY.getCode(), waybill.getStatusView()) == 0) {
                // 待配送的运单，需要发送消息给司机（消息中心和推送通知，跳转到详情页）
                this.pushWaitDeliveryAppMsgToDriver(waybill);
            }
            // 派车中的运单，走新运单通知流程(原来选择后台派车的运单没有消息 ！！！)
        } catch (Exception e) {
            log.warn("修改计划用车时间失败；waybillId:{}, exception:{}", waybill.getWaybillId(), e);
        }

    }

    /**
     * 发送待配送订单消息给司机
     * 
     * @param waybill
     */
    @Override
    public void pushWaitDeliveryAppMsgToDriver(Waybill waybill) {
        if (null == waybill) {
            return;
        }
        messagePushService.planTimeChangeMessage(waybill.getWaybillId());
    }

    /**
     * 
     * 进入电子围栏时的消息
     * 
     * @author Libin.Wei
     * @param waybillId 运单信息
     * @Date 2017年4月28日 上午11:59:19
     */
    @Override
    public void pushFenceEntryMsg(Integer waybillId) {
        messagePushService.truckEntryFenceMessage(waybillId);
    }

    /**
     * 
     * 离开电子围栏时的消息
     * 
     * @author Libin.Wei
     * @param waybillId 运单信息
     * @Date 2017年4月28日 上午11:59:19
     */
    @Override
    public void pushFenceExitMsg(Integer waybillId) {
        messagePushService.truckExitFenceMessage(waybillId);
    }

    /**
     * 
     * 推送更换车辆信息
     * 
     * @author Libin.Wei
     * @Date 2017年4月28日 下午4:29:46
     * @param waybillId
     *            运单ID
     * @param oldDriverId
     *            原司机ID
     */
    @Override
    public void pushChangeCarMsg(Integer waybillId, Integer oldDriverId,LoginUser loginUser) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (null == waybill) {
            return;
        }

        waybill.setStatusView(Waybill.StatusView.WATING_DELIVERY.getCode());
        Map<String, Object> extras = messagePushService.buildAppMsgParameter(waybill.getWaybillId());

        // 老司机:短信--取消通知、推送--取消通知、语音--取消通知
        Driver oldDriver = driverService.getDriver(oldDriverId);
        if (null != oldDriver) {
            messagePushService.pushAppMessage("CANCEL_WAYBILL_DRIVER", extras, loginUser, oldDriver.getUserId() + "");
            if (null != oldDriver.getSmsRemindSwitch() && NumberUtils.compare(oldDriver.getSmsRemindSwitch(),
                    Driver.RemindSwitchValue.ON.getCode()) == 0) {
                messagePushService.pushSmsMessage("CANCEL_WAYBILL_DRIVER", extras, loginUser,
                        oldDriver.getContactPhone());
            }
        }
        
        // 司机信息
        Driver driver = driverService.getDriver(waybill.getDriverId());
        // 代收货款判断
        boolean needCollectionPayment = truckRequireService.needCollectionPayment(null, waybill.getWaybillId());
        extras.put("collectionPayment", (needCollectionPayment ? "有" : "无"));
        // 发单人
        Integer ownerUserId = null;
        User user = employeeService.loadUserByEmployeeId(waybill.getCustomerManagerId(), loginUser);
        if (null != user && loginUser.getUserId().equals(user.getUserId())) {
            ownerUserId = user.getUserId();
        }

        if (NumberUtils.compare(Waybill.WaybillSource.JUMA_CLIENT.getCode(), waybill.getWaybillSource()) == 0) {
            // 发单人:推送--换车通知
            messagePushService.pushAppMessage("BILLING_USER_REPLACEMENT_VEHICLE", extras, loginUser,ownerUserId + "");
        }

        
        // 发单人:短信--换车通知
//        messagePushService.pushSmsMessage("BILLING_USER_REPLACEMENT_VEHICLE", extras, loginUser, mobileNumber);
        // 新司机:短信--指派通知、推送--指派通知、语音--指派通知
        if (null != driver) {
            messagePushService.pushAppMessage("ASSIGNED_WAYBILL", extras, loginUser, driver.getUserId() + "");
            messagePushService.pushAppMessage("ASSIGNED_WAYBILL_ALL_TENANT", extras, null,
                driver.getUserId() + "");
            if (null != driver.getSmsRemindSwitch() && NumberUtils.compare(driver.getSmsRemindSwitch(),
                    Driver.RemindSwitchValue.ON.getCode()) == 0) {
                messagePushService.pushSmsMessage("ASSIGNED_WAYBILL", extras, loginUser, driver.getContactPhone());
            }
            
            //Map<String,Object> voiceMap  = new HashMap<String,Object>();
            //voiceMap.put("nickname", extras.get("nickname"));
            //callToDriver("ASSIGNED_WAYBILL","TTS_94510012",waybill, driver, voiceMap);
        }
    }

    /**
     * 
     * 司机接单推送消息
     * 
     * @author Libin.Wei
     * @Date 2017年5月5日 下午3:47:49
     * @param waybill 运单
     * @param driver 司机
     * @param loginUser 车辆
     */
    @Override
    public void pushDriverReceiveWaybillMsg(Waybill waybill, Driver driver, LoginUser loginUser) {
        if (null == waybill) {
            return;
        }
        waybill = waybillService.getWaybill(waybill.getWaybillId());
        // 发单人
        String mobileNumber = null;
        User user = employeeService.loadUserByEmployeeId(waybill.getCustomerManagerId(), loginUser);
        if (null != user) {
            mobileNumber = user.getMobileNumber();
        }

        // 推送和短信
        Map<String, Object> extras = messagePushService.buildAppMsgParameter(waybill.getWaybillId());
        boolean needCollectionPayment = truckRequireService.needCollectionPayment(null, waybill.getWaybillId());
        extras.put("collectionPayment", (needCollectionPayment ? "有" : "无"));
        // 推送车机消息 刷新banner
        if (null != driver) {
            messagePushService.pushAppMessage("RECEIVER_WAYBILL", extras, loginUser, driver.getUserId() + "");
        }
//        messagePushService.pushAppMessage("BE_RECEIVED_WAYBILL", extras, loginUser, ownerUserId + "");
        // 通知发单人、用车人，不再短信通知取货地联系人
        messagePushService.pushSmsMessage("ASSIGN_CAR", extras, loginUser, mobileNumber);
    }

    /**
     * 
     * 指派运单消息
     * 
     * @author Libin.Wei
     * @Date 2017年5月5日 下午3:46:38
     * @param waybillId
     *            运单ID
     */
    @Override
    public void pushPointCarMsg(Integer waybillId,LoginUser loginUser) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (null == waybill) {
            return;
        }

        // 司机信息
        Driver driver = driverService.getDriver(waybill.getDriverId());

        // 客户经理
        String mobileNumber = null;
        User user = employeeService.loadUserByEmployeeId(waybill.getCustomerManagerId(), loginUser);
        if (null != user) {
            mobileNumber = user.getMobileNumber();
        }

        // 推送和短信
        pushPointCarAppWaybillMsg(waybill, driver, mobileNumber, loginUser);
    }

    // 推送通知和短信
    private void pushPointCarAppWaybillMsg(Waybill waybill, Driver driver, String mobileNumber, LoginUser loginUser) {
        Map<String, Object> extras = messagePushService.buildAppMsgParameter(waybill.getWaybillId());
        boolean needCollectionPayment = truckRequireService.needCollectionPayment(null, waybill.getWaybillId());
        extras.put("collectionPayment", (needCollectionPayment ? "有" : "无"));
        
        // 通知发单人，不再短信通知取货地联系人
        messagePushService.pushSmsMessage("ASSIGN_CAR", extras, loginUser, mobileNumber);

        if (null == driver) {
            return;
        }
        messagePushService.pushAppMessage("ASSIGNED_WAYBILL", extras, loginUser, driver.getUserId() + "");
        messagePushService.pushAppMessage("ASSIGNED_WAYBILL_ALL_TENANT", extras, null,
            driver.getUserId() + "");
        if (null != driver.getSmsRemindSwitch()
                && NumberUtils.compare(driver.getSmsRemindSwitch(), Driver.RemindSwitchValue.ON.getCode()) == 0) {
            messagePushService.pushSmsMessage("ASSIGNED_WAYBILL", extras, loginUser, driver.getContactPhone());
        }
//        Map<String,Object> voiceMap  = new HashMap<String,Object>();
//        voiceMap.put("nickname", extras.get("nickname"));
//        
//        callToDriver("ASSIGNED_WAYBILL","TTS_94510012",waybill, driver, voiceMap);
    }

    private void callToDriver(String smsKey,String callKey,Waybill waybill, Driver driver, Map<String, Object> voiceMap) {
        if (null == driver.getTelRemindSwitch()
                || NumberUtils.compare(driver.getTelRemindSwitch(), Driver.RemindSwitchValue.OFF.getCode()) == 0) {
            return;
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setTenantId(waybill.getTenantId());
        MsgConf msgConf = messageServiceProvider.getMsgTemplate(loginUser.getTenantId(),smsKey, MsgConf.MsgType.SMS);
        if(msgConf != null) {
            log.info("开始推送语音消息");
            messagePushService.pushVoiceMessage(callKey, voiceMap,msgConf.getMsgConfId() + "-" + waybill.getWaybillId()+"", loginUser,driver.getContactPhone());
        }
    }

    /**
     * 
     * 发送不派车反馈消息
     * 
     * @author Libin.Wei
     * @Date 2017年4月28日 下午6:11:01
     * @param waybill 运单信息
     */
    @Override
    public void pushNotAssignCarFeedback(Waybill waybill,LoginUser loginUser) {
        if (null == waybill) {
            return;
        }
        waybill = waybillService.getWaybill(waybill.getWaybillId());
        if (null == waybill.getCustomerManagerId()) {
            return;
        }
        // 发送派车反馈app推送给经纪人
        Map<String, Object> data = messagePushService.buildAppMsgParameter(waybill.getWaybillId());
        data.put("planDeliveryTime", DateUtil.format(waybill.getPlanDeliveryTime(), DateUtil.YYYYMMDDHHMM));

        // 发送派车反馈sms推送给经纪人
        User user = employeeService.loadUserByEmployeeId(waybill.getCustomerManagerId(), loginUser);
        if (null != user && !loginUser.getUserId().equals(user.getUserId())) {
            messagePushService.pushAppMessage(Constants.CUST_MAN_ASSIGN_CAR_FEEDBACK_APP, data, loginUser, user.getUserId() + "");
//            messagePushService.pushSmsMessage(Constants.CUST_MAN_ASSIGN_CAR_FEEDBACK_SMS, data, loginUser, user.getMobileNumber());
        }
    }

    /**
     * 
     * 离仓推送消息
     * 
     * @author Libin.Wei
     * @Date 2017年4月28日 下午6:48:22
     * @param waybill 运单信息
     */
    @Override
    public void pushLeaveDepotMsg(Waybill waybill,LoginUser loginUser) {
        if (null == waybill) {
            return;
        }
        waybill = waybillService.getWaybill(waybill.getWaybillId());
        Driver driver = driverService.getDriver(waybill.getDriverId());

        // 刷新banner  车机
        if (null != driver) {
            messagePushService.pushAppMessage("WATING_DELIVERY_TO_DELIVERYING", null, loginUser, driver.getUserId() + "");
        }
    }

    @Override
    public void pushNoticeCustomerManageCompleteWork(Integer waybillId, LoginUser loginUser) {
        if (null == waybillId) {
            return;
        }
        Waybill waybill = waybillService.getWaybill(waybillId);

        // 获取经纪人信息
        User user = employeeService.loadUserByEmployeeId(waybill.getCustomerManagerId(), loginUser);
        if (null == user) {
            return;
        }
        
        Map<String, Object> extras = messagePushService.buildAppMsgParameter(waybill.getWaybillId());
        messagePushService.pushAppMessage("NOTICE_CUSTOMER_MANAGE_COMPLETE_WORK", extras, loginUser,
                user.getUserId() + "");
    }
    
    // 发送微信离仓消息
    private void pushLeaveDepotWechatMsg(Waybill waybill, TruckCustomer truckCustomer, Driver driver) {
        if (null == truckCustomer) {
            return;
        }

        if (null == driver) {
            return;
        }

        Truck truck = truckService.getTruck(waybill.getTruckId());
        if (null == truck) {
            return;
        }
        String goodsInfo = truckRequireService.getGoodsFullName(null, waybill.getWaybillId());
        Map<String, Object> map = messagePushService.leaveDepotTemplate(waybill, driver,
                truckTypeService.findTruckTypeNameBy(truck.getVehicleBoxType(), truck.getVehicleBoxLength()), goodsInfo,
                truck.getPlateNumber());
        messagePushService.pushWechatMessage(truckCustomer.getOpenId(), "Ab_l3vYkgEo_fbW2gplClvlaIZP1V8Jl1M3YPYeAiQA",
                Constants.WECHAT_WAYBILL_INFO_PAGE_URL + waybill.getWaybillId(), map);
    }

    /**
     * 到仓推送消息
     * 
     * @author Libin.Wei
     * @Date 2017年4月28日 下午6:56:14
     * @param waybill 运单信息
     */
    @Override
    public void pushArriveDepotMsg(Waybill waybill,LoginUser loginUser) {
        if (null == waybill) {
            return;
        }
        waybill = waybillService.getWaybill(waybill.getWaybillId());

        // 推送车机消息 刷新bannerr
        Driver driver = driverService.getDriver(waybill.getDriverId());
        if (null != driver) {
            messagePushService.pushAppMessage("WATING_DELIVERY_TO_DELIVERYING", null, loginUser, driver.getUserId() + "");
        }
    }

    /**
     * 
     * 改价信息,推送给司机
     * 
     * @author Libin.Wei
     * @Date 2017年4月28日 下午7:15:54
     * @param waybillId
     *            运单ID
     * @param oldShow4DriverFreight
     *            老价格
     */
    @Override
    public void pushChangePriceMsgToDriver(Integer waybillId, BigDecimal oldShow4DriverFreight) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (waybill == null) return;
        if(waybill.getBusinessBranch() != null && Waybill.BusinessBranch.BRANCH_COM.getCode() == waybill.getBusinessBranch()) {
            return;
        }
        Driver driver = driverService.getDriver(waybill.getDriverId());
        if (null == driver) {
            return;
        }
        LoginUser loginUser = new LoginUser(waybill.getTenantId(), 1);
        log.info("改价信息,推送给司机(客户端),运单ID:{}", waybill.getWaybillId());
        log.info("改价信息,推送给司机(客户端),新价格:{};老价格:{}", waybill.getShow4DriverFreight(), oldShow4DriverFreight);
        Map<String, Object> extras = messagePushService.buildAppMsgParameter(waybill.getWaybillId());
        extras.put("oldFreight", oldShow4DriverFreight);
        messagePushService.pushAppMessage("CHANGE_PRICE_IN_PAGE_APP", extras, loginUser, driver.getUserId() + "");
        if (null != driver.getSmsRemindSwitch()
                && NumberUtils.compare(driver.getSmsRemindSwitch(), Driver.RemindSwitchValue.ON.getCode()) == 0) {
            messagePushService.pushSmsMessage("CHANGE_PRICE_IN_PAGE", extras, loginUser, driver.getContactPhone());
        }
    }

    /**
     * 
     * 改价信息，推送给发单人
     * 
     * @author Libin.Wei
     * @Date 2017年4月28日 下午7:15:54
     * @param waybillId
     *            运单ID
     * @param oldFreight
     *            老价格
     */
    @Override
    public void pushChangePricelMsgToWaybillOwner(Integer waybillId, BigDecimal oldFreight,LoginUser loginUser) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (null == waybill) {
            return;
        }
        // 发单人
        Integer ownerUserId = null;
        String mobileNumber = null;
        User user = employeeService.loadUserByEmployeeId(waybill.getCustomerManagerId(), loginUser);
        if (null != user && !loginUser.getUserId().equals(user.getUserId())) {
            ownerUserId = user.getUserId();
            mobileNumber = user.getMobileNumber();
        }

        Map<String, Object> extras = messagePushService.buildAppMsgParameter(waybill.getWaybillId());
        extras.put("oldFreight", oldFreight);
        messagePushService.pushAppMessage("CHANGE_PRICE_IN_SYS_APP", extras, loginUser, ownerUserId + "");
        messagePushService.pushSmsMessage("CHANGE_PRICE_IN_SYS", extras, loginUser, mobileNumber);
    }
    
    /**
     * 
     * 运单已经超过预计结束时间30分钟，但是司机还没有点击结束的运单提醒
     * @author Libin.Wei
     * @Date 2017年8月29日 上午10:18:56
     * @param waybillId
     */
    @Override
    public void pushWaybillShouldFinishMsg(int waybillId) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        Map<String, Object> extras = messagePushService.buildAppMsgParameter(waybillId);
        LoginUser loginUser = new LoginUser(waybill.getTenantId(), 1);
        try {
            Driver driver = driverService.getDriver(waybill.getDriverId());
            messagePushService.pushAppMessage("WAYBILL_SHOULD_FINISH", extras, loginUser, driver.getUserId() + "");
        } catch (Exception e) {
        }
    }

    /**
     * 
     * 司机配送完成推送消息
     * 
     * @author Libin.Wei
     * @Date 2017年5月2日 上午9:41:13
     * @param waybill 运单信息
     */
    @Override
    public void pushFinishWaybillMsg(Waybill waybill,LoginUser loginUser) {
        try {
            waybill = waybillService.getWaybill(waybill.getWaybillId());
            // 发单人的消息----------start------------
            Truck truck = truckService.getTruck(waybill.getTruckId());

            // 非微信运单 发单人
            Map<String, Object> extras = messagePushService.buildAppMsgParameter(waybill.getWaybillId());
            //判断项目运单详情和普通运单详情
            User user = employeeService.loadUserByEmployeeId(waybill.getCustomerManagerId(), loginUser);
            if (null != user && !loginUser.getUserId().equals(user.getUserId())) {
                messagePushService.pushAppMessage("DELIVERY_FINISH", extras, loginUser, user.getUserId() + "");
                //项目运单 要发送短信
                if(waybill.getBusinessBranch() != null && waybill.getBusinessBranch() == Waybill.BusinessBranch.BRANCH_COM.getCode()) {
                    extras.put("projectName", waybill.getProjectName());
                    messagePushService.pushSmsMessage("DELIVERY_FINISH", extras, loginUser, user.getMobileNumber());
                }
            }
            // 发单人的消息----------end-----------
            
            Driver driver = driverService.getDriver(waybill.getDriverId());
            if(driver != null) {
                messagePushService.pushAppMessage("DRIVER_DELIVERY_FINISH", extras, loginUser, driver.getUserId() + "");
            }
        } catch (Exception e) {
            log.warn("司机配送完成推送消息异常：{}, exception:{}", e.getMessage(), e);
        }
    }

    // 配送完成
    private void pushFinishWechatWaybillMsg(Waybill waybill, TruckCustomer truckCustomer, Truck truck) {
        if (null == truck) {
            return;
        }

        Map<String, Object> map = messagePushService.waybillDeliveriedTemplate(waybill, truck.getPlateNumber());
        if (null != truckCustomer) {
            messagePushService.pushWechatMessage(truckCustomer.getOpenId(), "AoD1b_dPNKU3TYnYPh_-paS2pQfwCtvWcGLFPLtFDeI",
                    Constants.WECHAT_WAYBILL_INFO_PAGE_URL + waybill.getWaybillId(), map);
        }
    }

    /**
     * 
     * 支付完成消息(现金或项目付款)
     * 
     * @author Libin.Wei
     * @Date 2017年5月2日 上午9:54:48
     * @param waybill 运单信息
     */
    @Override
    public void pushFinishPayMsg(Waybill waybill) {
        if (null == waybill) {
            return;
        }
        waybill = waybillService.getWaybill(waybill.getWaybillId());
        Driver driver = driverService.getDriver(waybill.getDriverId());
        if (null == driver) {
            return;
        }
        LoginUser loginUser = new LoginUser(waybill.getTenantId(), 1);
        Map<String, Object> extras = messagePushService.buildAppMsgParameter(waybill.getWaybillId());
        messagePushService.pushAppMessage("PAIED", extras, loginUser, driver.getUserId() + "");
    }

    // 获取waybillId
    private void deleteWaybillId(String key, Integer waybillId) {
        Serializable serializable = redisClient.get(key);
        if (null != serializable) {
            String waybillIdStr = String.valueOf(serializable);
            log.info("后台取消waybillIdStr：{}", waybillIdStr);
            if (StringUtils.isNotBlank(waybillIdStr)) {
                String[] split = waybillIdStr.split(",");
                String newWaybillIdStr = "";
                for (String string : split) {
                    if (StringUtils.isNotBlank(string) && !string.equals(waybillId + "")) {
                        newWaybillIdStr = newWaybillIdStr + string + ",";
                    }
                }
                log.info("后台取消waybillIdStr：{}", newWaybillIdStr);
                redisClient.set(key, newWaybillIdStr);
            }
        }
    }

    /**
     * 
     * 修改配送地址后通知司机
     * 
     * @author Libin.Wei
     * @Date 2017年7月13日 上午10:09:44
     * @param waybillId
     */
    @Override
    public void pushDeliveryPointSupplementHasUpdateMsg(int waybillId) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (null == waybill) {
            return;
        }

        Driver driver = driverService.getDriver(waybill.getDriverId());
        if (null == driver) {
            return;
        }

        LoginUser loginUser = new LoginUser(waybill.getTenantId(), 1);
        Map<String, Object> extras = messagePushService.buildAppMsgParameter(waybill.getWaybillId());
        messagePushService.pushAppMessage("DELIVERY_POINT_SUPPLEMENT_HAS_UPDATE", extras, loginUser, driver.getUserId() + "");
    }

    /**
     * 
     * 配送单标记为无效后通知司机
     * 
     * @author Libin.Wei
     * @Date 2017年7月13日 上午10:09:44
     * @param waybillId
     */
    @Override
    public void pushDeliveryPointSupplementInvalidMsg(int waybillId) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (null == waybill) {
            return;
        }

        Driver driver = driverService.getDriver(waybill.getDriverId());
        if (null == driver) {
            return;
        }
        LoginUser loginUser = new LoginUser(waybill.getTenantId(), 1);
        Map<String, Object> extras = messagePushService.buildAppMsgParameter(waybill.getWaybillId());
        messagePushService.pushAppMessage("DELIVERY_POINT_SUPPLEMENT_INVALID", extras, loginUser, driver.getUserId() + "");
    }

    /**
     * 改价审核不通过推送给经纪人APP
     */
    @Override
    public void pushFreightAuditNoPassMsg(int waybillId, LoginUser loginUser){
        Waybill waybill = waybillService.getWaybill(waybillId);
        if(waybill==null){
            return;
        }
        Integer customerManageId = waybill.getCustomerManagerId();
        Employee employee = employeeService.loadEmployee(customerManageId,loginUser);
        if (null == employee) {
            return;
        }
        Map<String, Object> extras = messagePushService.buildAppMsgParameter(waybill.getWaybillId());
        extras.put("planDeliveryTime", DateUtil.format(waybill.getPlanDeliveryTime(), "MM月dd日 HH:mm"));
        messagePushService.pushAppMessage("FREIGHT_AUDIT_NO_PASS", extras, loginUser, employee.getUserId() + "");
    }
}
