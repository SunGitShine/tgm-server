package com.juma.tgm.filiale.waybill.utils;

import com.juma.auth.user.domain.LoginUser;
import com.juma.message.basic.service.MsgConfService;
import com.juma.message.domain.MsgConf;
import com.juma.message.gateway.service.MessageServiceProvider;
import com.juma.tgm.common.Constants;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.project.domain.Project;
import com.juma.tgm.tools.service.MessagePushService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.WaybillService;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MsgWithBusinessUtils
 * @Description:
 * @author: liang
 * @date: 2017-10-24 16:57
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
@Component
public class FilialeMsgWithBusinessUtils {

    private static final Logger log = LoggerFactory.getLogger(FilialeMsgWithBusinessUtils.class);

    @Resource
    private WaybillService waybillService;

    @Resource
    private DriverService driverService;

    @Resource
    private MessagePushService messagePushService;

    @Resource
    private MessageServiceProvider messageServiceProvider;

    @Resource
    private MsgConfService msgConfService;

    /**
     * 改价信息,推送给司机
     *
     * @param waybillId             运单ID
     * @param oldShow4DriverFreight 老价格
     * @author Libin.Wei
     * @Date 2017年4月28日 下午7:15:54
     */
    public void pushChangePriceMsgToDriver(Integer waybillId, BigDecimal oldShow4DriverFreight) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (waybill == null) return;

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


    // 发送给司机：指派运单
    public void pushAssignWaybillToDriver(int driverId, int waybillId) {

        Driver driver = driverService.getDriver(driverId);
        if (null == driver) {
            return;
        }
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (waybill == null) return;

        LoginUser loginUser = new LoginUser(waybill.getTenantId(), 1);
        Map<String, Object> extras = messagePushService.buildAppMsgParameter(waybillId);
        // 通知司机被指派了车辆
        messagePushService.pushAppMessage("ASSIGNED_WAYBILL", extras, loginUser, driver.getUserId() + "");
        if (null != driver.getSmsRemindSwitch()
                && NumberUtils.compare(driver.getSmsRemindSwitch(), Driver.RemindSwitchValue.ON.getCode()) == 0) {
            messagePushService.pushSmsMessage("ASSIGNED_WAYBILL", extras, loginUser, driver.getContactPhone());
        }
        Map<String, Object> voiceMap = new HashMap<String, Object>();
        voiceMap.put("nickname", extras.get("nickname"));
        callToDriver("ASSIGNED_WAYBILL", "TTS_94510012", waybill, driver, voiceMap);

    }

    private void callToDriver(String smsKey, String callKey, Waybill waybill, Driver driver, Map<String, Object> voiceMap) {
        if (null == driver.getTelRemindSwitch()
                || NumberUtils.compare(driver.getTelRemindSwitch(), Driver.RemindSwitchValue.OFF.getCode()) == 0) {
            return;
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setTenantId(waybill.getTenantId());
        MsgConf msgConf = null;
        try {
            msgConf = messageServiceProvider.getMsgTemplate(loginUser.getTenantId(), smsKey, MsgConf.MsgType.SMS);
        } catch (Exception e) {
            log.error("没有消息模板", e);
        }
        if (msgConf != null) {
            messagePushService.pushVoiceMessage(callKey, voiceMap, msgConf.getMsgConfId() + "-" + waybill.getWaybillId() + "", loginUser, driver.getContactPhone());
        }
    }

    //客户经理企业客户不匹配
    public void fixedDemandAutoCreateBillCustomerNoBelongToManager(Project project, int manageUserId, LoginUser loginUser) {
        if(project == null) return ;

        try {
            Map<String, Object> extras = new HashMap<>();
            extras.put("projectName", project.getName());
            messagePushService.pushAppMessage(Constants.CUSTOMER_MANAGER_CUSTOMER_NOT_MATCH, extras, loginUser, manageUserId + "");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
    //运力不匹配
    public void fixedDemandAutoCreateBillTruckNoMatch(Project project, int manageUserId, LoginUser loginUser){
//        if(project == null) return ;
//
//        try {
//            Map<String, Object> extras = new HashMap<>();
//            extras.put("projectName", project.getName());
//            messagePushService.pushAppMessage(Constants.TRANSPORT_CAPACITY_NOT_MATCH, extras, loginUser, manageUserId + "");
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
    }


}
