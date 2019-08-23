package com.juma.tgm.landing.util;

import com.juma.auth.employee.domain.Employee;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginEcoUser;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.message.domain.MsgConf;
import com.juma.message.gateway.service.MessageServiceProvider;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.MsgScenekeyConstant;
import com.juma.tgm.configure.domain.ConfigParam;
import com.juma.tgm.configure.domain.ServiceConf;
import com.juma.tgm.configure.service.ServiceConfService;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.scatteredWaybill.service.ScatteredMsgService;
import com.juma.tgm.tools.service.MessagePushService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillDeliveryAddressService;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 落地配业务消息
 *
 * @ClassName: MsgWithBusinessUtils
 * @Description:
 * @author: liang
 * @date: 2017-11-22 14:16
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
@Component
public class ScatteredMsgUtils implements ScatteredMsgService {
    private static final Logger log = LoggerFactory.getLogger(ScatteredMsgUtils.class);

    private static final String hotline = "4001849156";
    @Resource
    private MessagePushService messagePushService;

    @Resource
    private UserService userService;

    @Resource
    private EmployeeService employeeService;
    
    @Resource
    private DriverService driverService;
    
    @Resource
    private WaybillDeliveryAddressService waybillDeliveryAddressService;
    
    @Resource
    private TruckRequireService truckRequireService;

    @Resource
    private ServiceConfService serviceConfService;

    @Resource
    private MessageServiceProvider messageServiceProvider;
    
    @Resource
    private WaybillCommonService waybillCommonService;

    //货主消息------
    //客户经理代发单
    @Override
    public void customerManagerCreateBillMsg(Waybill waybill, LoginUser loginUser) {
        if (waybill == null) return;
    }

    private Map<String, Object> buildScatteredBillMsgContent(Waybill waybill, LoginUser loginUser) {
        Map<String, Object> content = new HashMap<>();
        String localLine = "";
        //客户经理
        if (loginUser != null) {
            localLine = serviceConfService.findCustomerServiceTel(waybill.getRegionCode(), loginUser);

            User user = userService.loadUser(loginUser.getUserId());
            if (user != null && loginUser instanceof LoginEmployee) {
                content.put("managerName", user.getName());
            } else if (user != null && loginUser instanceof LoginEcoUser) {
                content.put("cargoOwner", user.getName());
            } else {
                content.put("cargoOwner", "-");
                content.put("managerName", "-");
            }
        }

        if (waybill.getBusinessBranch() != null) {
            if (waybill.getBusinessBranch() == Waybill.BusinessBranch.BRANCH_FULL.getCode()) {
                content.put("msgTitle", "新运单（整车）");
            } else if (waybill.getBusinessBranch() == Waybill.BusinessBranch.BRANCH_SCATTERED.getCode()) {
                content.put("msgTitle", "新运单（零担）");
            }
        }
        
        Driver driver = driverService.getDriver(waybill.getDriverId());
        if (driver != null) {
            content.put("nickname", driver.getNickname());
            content.put("driverMobile", driver.getContactPhone());
            // 兼容老模版
            content.put("mobile", driver.getContactPhone());
        }
        
        String deliveryAddressStr = waybillDeliveryAddressService.findByWaybillId(waybill.getWaybillId()) == null ? ""
                : waybillDeliveryAddressService.findByWaybillId(waybill.getWaybillId()).getAddressName();
        content.put("deliveryAddressStr", deliveryAddressStr);
        String googsInfoStr = truckRequireService.getGoodsFullName(null, waybill.getWaybillId());
        String truckRequireStr = truckRequireService.getTruckRequire(null, waybill.getWaybillId());
        content.put("googsInfoStr", googsInfoStr);
        content.put("truckRequireStr", truckRequireStr);
        
        //运单信息
        content.put("billType", Waybill.BusinessBranch.getByCode(waybill.getBusinessBranch()).getDescr());
        content.put("freight", waybill.getEstimateFreight());
        content.put("estimateDistance", waybill.getEstimateDistance());
        content.put("showForDriverFreight", waybill.getShow4DriverFreight());
        // 兼容老模版
        content.put("show4DriverFreight", waybill.getShow4DriverFreight());
        content.put("planDeliveryTime", Constants.YYYYMMDDHHMMSS.format(waybill.getPlanDeliveryTime()));
        content.put("waybillNo", StringUtils.substring(waybill.getWaybillNo(), waybill.getWaybillNo().length() - 4));
        content.put("customerName", waybill.getCustomerName());
        //客服电话

        content.put("hotline", StringUtils.isBlank(localLine) ? hotline : localLine);
        content.put("waybillId", waybill.getWaybillId());
        content.put("reason", waybill.getWaybillCancelRemark());
        content.put("statusView", waybill.getStatusView());
        return content;
    }

    //派车成功

    //开始配送

    //结束配送

    //经纪人确认已收款
    @Override
    public void customerManConfirmFreightAcceptMsg(Waybill waybill, LoginUser loginUser) {

    }

    //经纪人确认已支付代收货款

    @Override
    public void customerManConfirmFeeDeliveryMsg(Waybill waybill, LoginUser loginUser) {
    }

    //运单被取消
    @Override
    public void customerManBillCancelMsg(Waybill waybill, LoginUser loginUser) {
        if (waybill == null) return;
        
        pushCancelWaybillToDriver(waybill, loginUser);
        
        if (waybill.getCustomerManagerId() == null) return;

        Map<String, Object> content = this.buildScatteredBillMsgContent(waybill, loginUser);

        //通知经纪人
        //app
        messagePushService.pushAppMessage(MsgScenekeyConstant.SCATTERED_CANCEL_BILL_APP_FOR_MAN, content, loginUser,this.employeeIdToUser(waybill.getCustomerManagerId()).getUserId().toString());
        //sms
//        messagePushService.pushSmsMessage(MsgScenekeyConstant.SCATTERED_CANCEL_BILL_SMS_FOR_MAN, content, loginUser,this.employeeIdToUser(waybill.getCustomerManagerId()).getMobileNumber());

        //TODO 通知司机
    }

    //------货主消息

    //经纪人端消息------
    //客户已下单
    @Override
    public void cargoOwnerCreateBillMsg(Waybill waybill, LoginEcoUser loginEcoUser) {
        if (waybill == null) return;
        
        if (waybill.getCustomerManagerId() == null) return;

        Map<String, Object> content = this.buildScatteredBillMsgContent(waybill, loginEcoUser);
        //app
        messagePushService.pushAppMessage(MsgScenekeyConstant.SCATTERED_CARGOOWNER_CREATE_BILL_APP, content, loginEcoUser,this.employeeIdToUser(waybill.getCustomerManagerId()).getUserId().toString());
    }

    //派车成功
    //开始配送
    //结束配送
    //运单被取消
    @Override
    public void cargoOwnerCancelBillMsg(Waybill waybill, LoginUser loginUser) {
        if (waybill == null) return;

        pushCancelWaybillToDriver(waybill, loginUser);
        
        if (waybill.getCustomerManagerId() == null) return;

        Map<String, Object> content = this.buildScatteredBillMsgContent(waybill, null);

        //货主取消经纪人下的运单需要被通知
        if(this.isSelfBill(waybill, loginUser)) return;

        //通知客户经理
        //通知经纪人
        //app
        messagePushService.pushAppMessage(MsgScenekeyConstant.SCATTERED_CANCEL_BILL_APP_FOR_MAN, content, loginUser,this.employeeIdToUser(waybill.getCustomerManagerId()).getUserId().toString());
        //sms
//        messagePushService.pushSmsMessage(MsgScenekeyConstant.SCATTERED_CANCEL_BILL_SMS_FOR_MAN, content, loginUser,this.employeeIdToUser(waybill.getCustomerManagerId()).getMobileNumber());
    }
    //-----经纪人端消息

    /**
     * 通过employeeId获取user
     *
     * @param employeeId
     * @return
     */
    private User employeeIdToUser(int employeeId) {
        Employee employee = employeeService.loadEmployee(employeeId);
        User user = userService.findUser(employee.getUserId());
        return user;
    }

    /**
     * 判断是否为自己的单子
     * @param waybill
     * @param loginUser
     * @return
     */
    private boolean isSelfBill(Waybill waybill, LoginUser loginUser){
        if(NumberUtils.compare(waybill.getCreateUserId(), loginUser.getUserId()) == 0) return true;

        return false;
    }

    @Override
    public void pushArriveDepotMsg(Waybill waybill, LoginUser loginUser) {
    }

    /**
     * 
     * @Title: pushLeaveDepotMsg   
     * @Description: 开始配送
     * @return: void      
     * @throws
     */
    @Override
    public void pushLeaveDepotMsg(Waybill waybill, BigDecimal oldEstimateFreight, LoginUser loginUser) {
        if (null == waybill) {
            return;
        }
        
    }

    @Override
    public void pushNoticeCustomerManageCompleteWork(Integer waybillId, LoginUser loginUser) {
        if (null == waybillId) {
            return;
        }
        
        Waybill waybill = waybillCommonService.getWaybillById(waybillId);
        if (null == waybill) {
            return;
        }

        // 获取经纪人信息
        User user = employeeService.loadUserByEmployeeId(waybill.getCustomerManagerId(), loginUser);
        if (null == user) {
            return;
        }

        Map<String, Object> extras = buildScatteredBillMsgContent(waybill, loginUser);
        messagePushService.pushAppMessage("NOTICE_CUSTOMER_MANAGE_COMPLETE_WORK", extras, loginUser,
                user.getUserId() + "");
    }

    private boolean isCustomerManagerWaybill(Waybill waybill, LoginUser loginUser) {
        if(waybill.getCustomerManagerId() == null) return false;
        Employee employee = employeeService.loadEmployee(waybill.getCustomerManagerId());
        if(employee != null) {
            //是不是客户经理自己发的单子
            return NumberUtils.compare(waybill.getCreateUserId(), employee.getUserId()) == 0;
        }
        return false;
    }
    
    /**
     * 
     * @Title: pushFinishWaybillMsg   
     * @Description: 结束配送
     * @param: @param waybill      
     * @return: void      
     * @throws
     */
    @Override
    public void pushFinishWaybillMsg(Waybill waybill, LoginUser loginUser) {
    }

    /**
     * 
     * @Title: pushFinishWaybillMsg   
     * @Description: 派车成功通知
     * @param: @param waybill      
     * @return: void      
     * @throws
     */
    @Override
    public void pushAssignedWaybillMsg(Waybill waybill, LoginUser loginUser) {
    }

    @Override
    public void pushAssignedWaybillToDriverAppMsg(Waybill waybill, LoginUser loginUser) {
        if (null == waybill) {
            return;
        }
        Map<String, Object> extras = buildScatteredBillMsgContent(waybill, loginUser);
        
        Driver driver = driverService.getDriver(waybill.getDriverId());
        if (null == driver) {
            log.info("pushAssignedWaybillToDriverMsg  driver is empty.");
            return;
        }

        messagePushService.pushAppMessage("SCATTERED_ASSIGNED_WAYBILL", extras, loginUser, driver.getUserId() + "");
        if (null != driver.getSmsRemindSwitch()
                && NumberUtils.compare(driver.getSmsRemindSwitch(), Driver.RemindSwitchValue.ON.getCode()) == 0) {
            messagePushService.pushSmsMessage("SCATTERED_ASSIGNED_WAYBILL_SMS", extras, loginUser, driver.getContactPhone());
        }
//        if (null != driver.getTelRemindSwitch()
//                && NumberUtils.compare(driver.getTelRemindSwitch(), Driver.RemindSwitchValue.ON.getCode()) == 0) {
//            messagePushService.pushVoiceMessage("TTS_94510012", extras, null, loginUser,driver.getContactPhone());
//        }
    }

    @Override
    public void pushAssignedWaybillToDriverSmsMsg(Waybill waybill, LoginUser loginUser) {
        if (null == waybill) {
            return;
        }

        Map<String, Object> extras = buildScatteredBillMsgContent(waybill, loginUser);

        Driver driver = driverService.getDriver(waybill.getDriverId());
        if (null == driver || null == driver.getSmsRemindSwitch()
                || NumberUtils.compare(driver.getSmsRemindSwitch(), Driver.RemindSwitchValue.OFF.getCode()) == 0) {
            log.info("pushAssignedWaybillToDriverMsg  driver is empty.");
            return;
        }

        messagePushService.pushSmsMessage("SCATTERED_ASSIGNED_WAYBILL_SMS", extras, loginUser, driver.getContactPhone());
    }

    private void pushCancelWaybillToDriver(Waybill waybill, LoginUser loginUser) {
        if (waybill.getDriverId() == null)
            return;
        Driver driver = driverService.getDriver(waybill.getDriverId());
        if (driver == null)
            return;
        Map<String, Object> extras = buildScatteredBillMsgContent(waybill, loginUser);
        messagePushService.pushAppMessage("SCATTERED_CANCEL_WAYBILL_DRIVER", extras, loginUser,
                driver.getUserId() + "");
        if (null != driver.getSmsRemindSwitch()
                && NumberUtils.compare(driver.getSmsRemindSwitch(), Driver.RemindSwitchValue.ON.getCode()) == 0) {
            messagePushService.pushSmsMessage("CANCEL_WAYBILL_DRIVER", extras, loginUser, driver.getContactPhone());
        }
        if (null != driver.getTelRemindSwitch()
                && NumberUtils.compare(driver.getTelRemindSwitch(), Driver.RemindSwitchValue.ON.getCode()) == 0) {
            messagePushService.pushVoiceMessage("TTS_94495020", extras, null, loginUser, driver.getContactPhone());
        }
    }
    
    @Override
    public void pushCancelWaybillMsg(Waybill waybill, LoginUser loginUser) {
        if (null == waybill) {
            return;
        }

        pushCancelWaybillToDriver(waybill, loginUser);
        Map<String, Object> extras = buildScatteredBillMsgContent(waybill, null);
        if(isCustomerManagerWaybill(waybill, loginUser)) {
            messagePushService.pushAppMessage(MsgScenekeyConstant.SCATTERED_CANCEL_BILL_APP_FOR_MAN, extras, loginUser,this.employeeIdToUser(waybill.getCustomerManagerId()).getUserId().toString());
            //sms
//            messagePushService.pushSmsMessage(MsgScenekeyConstant.SCATTERED_CANCEL_BILL_SMS_FOR_MAN, extras, loginUser,this.employeeIdToUser(waybill.getCustomerManagerId()).getMobileNumber());
        }
    }

    @Override
    public void pushCancelWaybillToDriverMsg(Waybill waybill, Integer oldDriverId, LoginUser loginUser) {
        if (null == waybill) {
            return;
        }

        Driver oldDriver = driverService.getDriver(oldDriverId);
        if (null != oldDriver) {
            Map<String, Object> extras = buildScatteredBillMsgContent(waybill, null);
            messagePushService.pushAppMessage("SCATTERED_CANCEL_WAYBILL_DRIVER_APP", extras, loginUser, oldDriver.getUserId() + "");
            if (null != oldDriver.getSmsRemindSwitch() && NumberUtils.compare(oldDriver.getSmsRemindSwitch(),
                    Driver.RemindSwitchValue.ON.getCode()) == 0) {
                messagePushService.pushSmsMessage("SCATTERED_CANCEL_WAYBILL_DRIVER_SMS", extras, loginUser,
                        oldDriver.getContactPhone());
            }
//            if (null != oldDriver.getTelRemindSwitch() && NumberUtils.compare(oldDriver.getTelRemindSwitch(),
//                    Driver.RemindSwitchValue.ON.getCode()) == 0) {
//                messagePushService.pushVoiceMessage("TTS_94495020", extras, null, loginUser,
//                        oldDriver.getContactPhone());
//            }
        }
    }

    @Override
    public void pushChangeCarMsg(Waybill waybill, LoginUser loginUser) {
        Map<String, Object> extras = buildScatteredBillMsgContent(waybill, null);
        Driver driver = driverService.getDriver(waybill.getDriverId());
        if (null != driver) {
            extras.put("nickname", driver.getNickname());
            extras.put("driverMobile", driver.getContactPhone());
            // 兼容老模版
            extras.put("mobile", driver.getContactPhone());
        }
        extras.put("plateNumber", waybill.getPlateNumber());

        // 经济人
        User user = employeeService.loadUserByEmployeeId(waybill.getCustomerManagerId(), loginUser);
        if (null != user) {
            messagePushService.pushAppMessage("SCATTERED_CHANGE_CAR_APP_FOR_MAN", extras, loginUser,
                    user.getUserId() + "");
            messagePushService.pushSmsMessage("SCATTERED_CHANGE_CAR_SMS_FOR_MAN", extras, loginUser,
                    user.getMobileNumber());
        }
    }

    @Override
    public void pushMsgToAdmin(Waybill waybill, LoginUser loginUser) {
        ServiceConf serviceConf = serviceConfService.findByRegionCode(waybill.getRegionCode(), loginUser);
        if(serviceConf != null) {
            Map<String, Object> extras = messagePushService.buildAppMsgParameter(waybill.getWaybillId());
            if(StringUtils.isNotBlank(serviceConf.getOperationSpecialistTel())) {
                messagePushService.pushSmsMessage("SCATTERED_AI_ASSIGNED_TIMEOUT", extras,loginUser, serviceConf.getOperationSpecialistTel());
                messagePushService.pushVoiceMessage("TTS_116566343", extras, null,loginUser, serviceConf.getOperationSpecialistTel());
            }
        }
    }

    @Override
    public void pushChangeCarMsgToDriver(Waybill waybill, Integer oldDriverId, LoginUser loginUser) {
        log.info("change car :{}.", waybill.getWaybillId());
        waybill = waybillCommonService.getWaybillById(waybill.getWaybillId());
        if (null == waybill) {
            return;
        }
        // 落地配消息
        this.pushAssignedWaybillToDriverAppMsg(waybill, loginUser);
        //scatteredMsgService.pushAssignedWaybillToDriverSmsMsg(waybill, loginUser);
        this.pushCancelWaybillToDriverMsg(waybill, oldDriverId, loginUser);
        this.pushChangeCarMsg(waybill, loginUser);
    }

    @Override
    public void pushCancelWaybillToDriverAndDispatcher(Waybill waybill, LoginUser loginUser) {
        Map<String, Object> extras = messagePushService.buildAppMsgParameter(waybill.getWaybillId());

        // 通知调度
        messagePushService.pushConfigureMessage("", "CANCEL_WAYBILL_TO_DISPATCHER", waybill.getTenantId(), waybill.getAreaCode(),
                ConfigParam.ParamKey.CANCEL_WAYBILL, extras, loginUser);

        // 通知司机
        Driver driver = driverService.getDriver(waybill.getDriverId());
        if (null == driver) {
            return;
        }

        // app推送
        messagePushService.pushAppMessage("CANCEL_WAYBILL_DRIVER", extras, loginUser, driver.getUserId() + "");
        // 短信通知
        //messagePushService.pushSmsMessage("CANCEL_WAYBILL_DRIVER", extras, driver.getContactPhone());
        //电话通知
        Map<String,Object> voiceMap = new HashMap<String,Object>();
        voiceMap.put("waybillNo", extras.get("waybillNo"));

        callToDriver("CANCEL_WAYBILL_DRIVER","TTS_94495020",waybill, driver, voiceMap);
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
            if(log.isDebugEnabled()){
                log.info("开始推送语音消息");
            }
            messagePushService.pushVoiceMessage(callKey, voiceMap,msgConf.getMsgConfId() + "-" + waybill.getWaybillId()+"", loginUser,driver.getContactPhone());
        }
    }

}
