package com.juma.tgm.tools.service.impl;

import com.alibaba.fastjson.JSON;
import com.giants.cache.redis.RedisClient;
import com.juma.auth.common.SystemAuthKey;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.conf.service.BusinessAreaService;
import com.juma.auth.employee.domain.Employee;
import com.juma.auth.employee.domain.EmployeeInfo;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.EcoUser;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.cms.wx.service.OpenApiService;
import com.juma.message.cloud.domain.UpComingTask;
import com.juma.message.cloud.service.CloudMessageService;
import com.juma.message.domain.MsgConf;
import com.juma.message.gateway.service.MessageServiceProvider;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.common.RedisKeyConstants;
import com.juma.tgm.common.StringTemplateUtils;
import com.juma.tgm.configure.domain.ConfigParam.ParamKey;
import com.juma.tgm.configure.domain.ConfigParamOption;
import com.juma.tgm.configure.service.ConfigParamOptionService;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.domain.Driver.TaskStatus;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.mq.domain.MsgEvent;
import com.juma.tgm.mq.enumeration.MsgEventEnum;
import com.juma.tgm.mq.enumeration.MsgTypeEnum;
import com.juma.tgm.mq.service.MqService;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.task.domain.TaskAck;
import com.juma.tgm.task.domain.TaskScheduled;
import com.juma.tgm.task.service.TaskAckService;
import com.juma.tgm.task.service.TaskScheduledService;
import com.juma.tgm.tools.service.AuthCommonService;
import com.juma.tgm.tools.service.MessagePushService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillNotice;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillDeliveryAddressService;
import com.juma.tgm.waybill.service.WaybillNoticeService;
import com.juma.tgm.waybill.service.WaybillParamService;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.vms.transport.request.CapacityPoolFilter;
import com.juma.vms.vendor.domain.Vendor;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class MessagePushServiceImpl implements MessagePushService {

    private static final Logger log = LoggerFactory.getLogger(MessagePushServiceImpl.class);
    @Resource
    private MqService mqService;
    @Resource
    private MessageServiceProvider messageServiceProvider;
    @Resource
    private OpenApiService openApiService;
    @Resource
    private ConfigParamOptionService configParamOptionService;
    @Resource
    private WaybillNoticeService waybillNoticeService;
    @Resource
    private WaybillService waybillService;
    @Resource
    private WaybillCommonService waybillCommonService;
    @Resource
    private DriverService driverService;
    @Resource
    private WaybillParamService waybillParamService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private TruckRequireService truckRequireService;
    @Resource
    private TruckTypeService truckTypeService;
    @Resource
    private BusinessAreaService businessAreaService;
    @Resource
    private WaybillDeliveryAddressService waybillDeliveryAddressService;
    @Resource
    private RedisClient redisClient;
    @Resource
    private VmsCommonService vmsCommonService;
    @Resource
    private AuthCommonService authCommonService;
    @Resource
    private CloudMessageService cloudMessageService;
    @Resource
    private TaskScheduledService taskScheduledService;
    @Resource
    private ProjectService projectService;
    @Resource
    private UserService userService;
    @Resource
    private TaskAckService taskAckService;
    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

    @Value("${cloud.message.push.url}")
    private String cloudMessagePushUrl;

    @Override
    public void pushAppMessage(String sceneKey, Map<String, Object> extras, boolean isMustRead, String extMsgId,
            String... tos) {
        log.info("pushAppMessageextMsgId->sceneKey:{}->extMsgId: {}", sceneKey, extMsgId);
        MsgEvent msgEvent = new MsgEvent(MsgTypeEnum.APP, MsgEventEnum.NEED_MUST_ID, sceneKey, extras, tos);
        msgEvent.setMustRead(isMustRead);
        msgEvent.setExtMsgId(extMsgId);
        mqService.sendMsgCommonMsg(msgEvent);
    }

    @Override
    public void pushAppMessage(String sceneKey, Map<String, Object> extras, LoginUser loginUser, String... tos) {
        log.info("pushAppMessage->sceneKey:{}->tos: {}", sceneKey, JSON.toJSON(tos));
        if (loginUser == null) {
            MsgEvent msgEvent = new MsgEvent(MsgTypeEnum.APP, MsgEventEnum.STANDARD, sceneKey, extras, tos);
            mqService.sendMsgCommonMsg(msgEvent);
        } else {
            MsgEvent msgEvent = new MsgEvent(MsgTypeEnum.APP, MsgEventEnum.WITH_TENANT, sceneKey, extras, tos);
            msgEvent.setTenantId(loginUser.getTenantId());
            mqService.sendMsgCommonMsg(msgEvent);
        }
    }

    @Override
    public void pushEmailMessage(String sceneKey, Map<String, Object> extras, String... email) {
        MsgEvent msgEvent = new MsgEvent(MsgTypeEnum.EMAIL, MsgEventEnum.STANDARD, sceneKey, extras, email);
        mqService.sendMsgCommonMsg(msgEvent);
    }

    @Override
    public void pushSmsMessage(String sceneKey, Map<String, Object> extras, LoginUser loginUser, String... mobile) {
        log.info("pushSmsMessage->sceneKey:{}->mobile: {}", sceneKey, JSON.toJSON(mobile));
        log.info("pushSmsMessage->extras: {}", JSON.toJSON(extras));
        if (loginUser == null) {
            MsgEvent msgEvent = new MsgEvent(MsgTypeEnum.SMS, MsgEventEnum.STANDARD, sceneKey, extras, mobile);
            mqService.sendMsgCommonMsg(msgEvent);
        } else {
            MsgEvent msgEvent = new MsgEvent(MsgTypeEnum.SMS, MsgEventEnum.WITH_TENANT, sceneKey, extras, mobile);
            msgEvent.setTenantId(loginUser.getTenantId());
            mqService.sendMsgCommonMsg(msgEvent);
        }
    }

    /**
     * <p>
     * Title: pushVoiceMessage
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param sceneKey
     * @param extras
     * @param extMsgId 说明 extMsgId = "短信场景Key" + 运单Id
     * @param mobile
     */
    @Override
    public void pushVoiceMessage(String sceneKey, Map<String, Object> extras, String extMsgId, LoginUser loginUser,
            String... mobile) {
        log.info("pushVoiceMessage->sceneKey:{}->mobile: {}", sceneKey, JSON.toJSON(mobile));
        log.info("pushVoiceMessage->extras: {}", JSON.toJSON(extras));
        if (loginUser == null) {
            MsgEvent msgEvent = new MsgEvent(MsgTypeEnum.VOICE, MsgEventEnum.STANDARD, sceneKey, extras, mobile);
            msgEvent.setExtMsgId(extMsgId);
            mqService.sendMsgCommonMsg(msgEvent);
        } else {
            MsgEvent msgEvent = new MsgEvent(MsgTypeEnum.VOICE, MsgEventEnum.WITH_TENANT, sceneKey, extras, mobile);
            msgEvent.setExtMsgId(extMsgId);
            msgEvent.setTenantId(loginUser.getTenantId());
            mqService.sendMsgCommonMsg(msgEvent);
        }
    }

    @Override
    public void pushWechatMessage(String toUser, String templateId, String url, Map<String, Object> dataMap) {
        log.info("wechat msg toUser:{}", toUser);
        log.info("wechat msg templateId:{}", templateId);
        log.info("wechat msg url:{}", url);
        log.info("wechat msg dataMap:{}", dataMap == null ? null : dataMap.toString());
        try {
            openApiService.sendTemplateMsg("gh_0b2818295118", toUser, templateId, url, dataMap);
        } catch (Exception e) {
            log.warn("pushSmsMessage->mobile->{}->Exception: {}", JSON.toJSON(url), e);
        }
    }

    @Override
    public void cloudMessagePush(final String authKey,final String sourceId, final String title, final String content, final List<String> phoneList, final LoginUser loginUser) {

        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                UpComingTask upComingTask = new UpComingTask();
                upComingTask.setSourceId(System.nanoTime()+"");
                upComingTask.setTitle(title);
                upComingTask.setContent(content);
                upComingTask.setPhoneList(phoneList);
                User user = userService.loadUser(loginUser.getUserId());
                upComingTask.setSender(user == null ? "" : user.getName());

                String cloudUrl = String.format(cloudMessagePushUrl, sourceId, title, new Date());
                upComingTask.setUrl(getURLEncoderString(cloudUrl));

                cloudMessageService.upcomingTasks(upComingTask, new SystemAuthKey(authKey), loginUser);
            }
        });

    }

    @Override
    public void inviteVendor(Integer taskId,Integer vendorId,LoginUser loginUser) {
        log.info("inviteVendor");
        Vendor vendor = vmsCommonService.loadVendorByVendorId(vendorId);
        if (vendor != null) {
            Map<String, Object> extras = new HashMap<>();
            extras.put("taskId",taskId);
            extras.put("vendorName",vendor.getVendorName());
            if (vendor.getUserId() != null) {
                pushAppMessage("Task_Invite_Vendor",extras,loginUser,vendor.getUserId()+"");
            }
            pushSmsMessage("Task_Invite_Vendor",extras,loginUser,vendor.getContactPhone());
        }
    }

    @Override
    public void vendorReceive(Integer taskId, Integer vendorId,LoginUser loginUser) {
        log.info("vendorReceive");
        TaskScheduled taskScheduled = taskScheduledService.withTaskId(taskId);
        if (taskScheduled == null) return;
        Project projectV2 = projectService.getProjectV2(taskScheduled.getProjectId());
        if (projectV2 == null) return;
        User user = userService.loadUser(taskScheduled.getCreateUserId());
        if (user == null) return;

        Map<String,String> context = new HashMap<>();
        context.put("projectName",projectV2.getName());
        context.put("projectNo",projectV2.getProjectNo());
        String content = "项目：${projectName}(${projectNo})有1个任务承运商已接受！请到市场助手APP查看。";
        List<String> phoneList = new ArrayList<>();
        phoneList.add(user.getMobileNumber());

        cloudMessagePush(Constants.AUTH_KEY_TGM_DRIVER,
                taskId+"",
                "承运商接受任务",
                StringTemplateUtils.render(content,context),
                phoneList,loginUser);
    }

    @Override
    public void vendorRefuse(Integer taskId, Integer vendorId,LoginUser loginUser) {
        log.info("vendorRefuse");
        TaskScheduled taskScheduled = taskScheduledService.withTaskId(taskId);
        if (taskScheduled == null) return;
        Project projectV2 = projectService.getProjectV2(taskScheduled.getProjectId());
        if (projectV2 == null) return;
        TaskAck taskAck = taskAckService.withLastAck(taskId, vendorId);
        if (taskAck == null) return;
        User user = userService.loadUser(taskAck.getCreateUserId());
        if (user == null) return;
        Vendor vendor = vmsCommonService.loadVendorByVendorId(vendorId);
        if (vendor == null) return;

        Map<String,String> context = new HashMap<>();
        context.put("projectName",projectV2.getName());
        context.put("projectNo",projectV2.getProjectNo());
        context.put("vendorName",vendor.getVendorName());
        String content = "项目：${projectName}(${projectNo})有1个任务承运商：${vendorName}已拒绝，请通过【任务改派】重新指派承运商。";
        List<String> phoneList = new ArrayList<>();
        phoneList.add(user.getMobileNumber());

        cloudMessagePush(Constants.AUTH_KEY_TGM_DRIVER,
                taskId+"",
                "承运商拒绝任务",
                StringTemplateUtils.render(content,context),
                phoneList,loginUser);

    }

    @Override
    public void finishTask(Integer taskId) {
        log.info("finishTask");
        TaskScheduled taskScheduled = taskScheduledService.withTaskId(taskId);
        if (taskScheduled == null) return;
        Vendor vendor = vmsCommonService.loadVendorByVendorId(taskScheduled.getVendorId());
        if (vendor == null) return;
        Project projectV2 = projectService.getProjectV2(taskScheduled.getProjectId());
        if (projectV2 == null) return;

        LoginUser loginUser = new LoginUser(taskScheduled.getTenantId(),1);

        Map<String, Object> extras = new HashMap<>();
        extras.put("taskId",taskId);
        extras.put("vendorName",vendor.getVendorName());
        extras.put("projectName",projectV2.getName());
        if (vendor.getUserId() != null) {
            pushAppMessage("Task_Finish",extras,loginUser,vendor.getUserId()+"");
        }
        pushSmsMessage("Task_Finish",extras,loginUser,vendor.getContactPhone());
    }

    @Override
    public void pauseTask(Integer taskId) {
        log.info("pauseTask");
        TaskScheduled taskScheduled = taskScheduledService.withTaskId(taskId);
        if (taskScheduled == null) return;
        Vendor vendor = vmsCommonService.loadVendorByVendorId(taskScheduled.getVendorId());
        if (vendor == null) return;
        Project projectV2 = projectService.getProjectV2(taskScheduled.getProjectId());
        if (projectV2 == null) return;

        LoginUser loginUser = new LoginUser(taskScheduled.getTenantId(),1);

        Map<String, Object> extras = new HashMap<>();
        extras.put("taskId",taskId);
        extras.put("vendorName",vendor.getVendorName());
        extras.put("projectName",projectV2.getName());
        if (vendor.getUserId() != null) {
            pushAppMessage("Task_Pause",extras,loginUser,vendor.getUserId()+"");
        }
        pushSmsMessage("Task_Pause",extras,loginUser,vendor.getContactPhone());
    }

    @Override
    public void expireTask(Integer taskId) {
        log.info("pauseTask");
        TaskScheduled taskScheduled = taskScheduledService.withTaskId(taskId);
        if (taskScheduled == null) return;
        Project projectV2 = projectService.getProjectV2(taskScheduled.getProjectId());
        if (projectV2 == null) return;
        User user = userService.loadUser(taskScheduled.getCreateUserId());
        if (user == null) return;

        Map<String,String> context = new HashMap<>();
        context.put("taskId",taskId+"");
        context.put("projectName",projectV2.getName());
        context.put("projectNo",projectV2.getProjectNo());
        String content = "项目：${projectName}(${projectNo})有1个任务在派车有效期内没有承运商接受任务，已过期！请到市场助手APP查看。";
        List<String> phoneList = new ArrayList<>();
        phoneList.add(user.getMobileNumber());

        LoginUser loginUser = new LoginUser(taskScheduled.getTenantId(),1);

        cloudMessagePush(Constants.AUTH_KEY_TGM_DRIVER,
                taskId+"",
                "任务过期",
                StringTemplateUtils.render(content,context),
                phoneList,loginUser);


    }

    @Override
    public void robWaybillMessage(int waybillId, LoginUser loginUser) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        log.info("robWaybillMessage新运单消息waybillId：{}", waybillId);
        List<String> areaCodeLikeList = new ArrayList<String>();
        if (waybill != null) {
            TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), loginUser);

            CapacityPoolFilter filter = new CapacityPoolFilter();

            BusinessArea businessArea = businessAreaService.loadLogicBusinessArea(waybill.getAreaCode(), loginUser);
            if (businessArea != null) {
                areaCodeLikeList.add(businessArea.getAreaCode());
            }

            if (areaCodeLikeList.isEmpty()) {
                areaCodeLikeList.add(-999 + "");
            }

            filter.setAreaCodeList(areaCodeLikeList);
            if (truckRequire != null) {
                if (truckRequire.getEntryLicense() == 1) {
                    filter.setGoCityLicenseType(1);// A1 入城证
                }
                if (truckRequire.getTruckTypeId() != null) {
                    TruckType truckType = truckTypeService.getTruckType(truckRequire.getTruckTypeId());
                    if (truckType != null) {
                        filter.setVehicleBoxType(truckType.getVehicleBoxType());
                        //condition.getFilters().put("boxLevel", truckType.getTruckLengthId().toString());// 厢长
                    }
                }
                if (truckRequire.getVehicleBoxType() != null) {
                    filter.setVehicleBoxType(truckRequire.getVehicleBoxType());
                }
            }

            // 添加载重条件
//            condition.getFilters().put("maxLoadCapacity", waybill.getGoodsWeight());

            List<String> driverUserIds = new ArrayList<String>();

            log.info("robWaybillMessage获取可推行司机条件：{}", JSON.toJSONString(filter));
            List<com.juma.vms.driver.domain.Driver> list = vmsCommonService.listEffectiveCapacityDriver(filter, loginUser);
            log.info("robWaybillMessage获取可推行司机结果：{}", JSON.toJSONString(list));

            for (com.juma.vms.driver.domain.Driver d : list) {
                if (null == d.getUserId() || null == d.getIsReceiveWaybill() || !d.getIsReceiveWaybill()) {
                    continue;
                }

                driverUserIds.add(d.getUserId() + "");
            }

            log.info("robWaybillMessage获取可推行司机ID集合：{}", JSON.toJSONString(driverUserIds));
            if (!driverUserIds.isEmpty()) {
                pushAppMessage("NEW_WAYBILL", buildAppMsgParameter(waybillId), loginUser,
                        driverUserIds.toArray(new String[driverUserIds.size()]));
                dataCollect(waybill, driverUserIds);
            }
        }
    }

    @Override
    public void passDriverMessage(int driverId) {
        Driver driver = driverService.getDriver(driverId);
        if (driver == null)
            return;
        if (TaskStatus.ABLE.getCode().equals(driver.getStatus())) {
            Map<String, Object> extras = new HashMap<String, Object>();
            if (null != driver.getSmsRemindSwitch()
                    && NumberUtils.compare(driver.getSmsRemindSwitch(), Driver.RemindSwitchValue.ON.getCode()) == 0) {
                pushSmsMessage("PASS_CERTIFICATION", extras, null, driver.getContactPhone());
            }
            pushAppMessage("PASS_CERTIFICATION", extras, null, driver.getUserId() + "");
        }
    }

    @Override
    public void carryFeeMessage(int waybillId, LoginUser loginUser) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (waybill == null) return;
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
        if (waybillParam == null) return;
        Map<String, Object> extras = buildAppMsgParameter(waybillId);
        extras.put("driverHandlingCost", waybillParam.getDriverHandlingCost());
        com.juma.vms.driver.domain.Driver driver = vmsCommonService.loadDriverByDriverId(waybill.getDriverId());
        // 推送司机
        if (waybill.getBusinessBranch() == null
                || Waybill.BusinessBranch.SPECIAL_CAR.getCode() == waybill.getBusinessBranch()) {
            if (null != driver && waybillParam.getDriverHandlingCost() != null
                    && waybillParam.getDriverHandlingCost().floatValue() > 0) {
                pushAppMessage("DRIVER_HANDLING_COST", extras, loginUser, driver.getUserId() + "");
                pushSmsMessage("DRIVER_HANDLING_COST", extras, loginUser, driver.getPhone());
            }
        }

        try {
            // 推送发单人
            extras.put("handlingCost", waybillParam.getDriverHandlingCost().add(waybillParam.getLaborerHandlingCost()));
            if (waybill.getCustomerManagerId() != null) {
                EmployeeInfo employee = employeeService.findEmployeeInfo(waybill.getCustomerManagerId(), loginUser);
                if(employee != null) {
                    pushAppMessage("CUSTOMER_HANDLING_COST", extras, loginUser, employee.getUserId() + "");
                }
//                pushSmsMessage("CUSTOMER_HANDLING_COST", extras, loginUser, employee.getMobileNumber());
            }
//            if (waybill.getOwnerEcoUserId() != null) {
//                User user = ecoUserService.findUserByEcoUserId(waybill.getOwnerEcoUserId(), loginUser);
//                pushSmsMessage("CUSTOMER_HANDLING_COST", extras, loginUser, user.getMobileNumber());
//            }
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }

    private void dataCollect(Waybill waybill, List<String> driverUserIds) {
        // 运单通知时间、数量入库 数据收集
        WaybillNotice waybillNotice = new WaybillNotice();
        waybillNotice.setWaybillId(waybill.getWaybillId());
        waybillNotice.setNoticeDriverNum(driverUserIds.size());
        waybillNotice.setNoticeTime(new Date());
        try {
            waybillNoticeService.save(waybillNotice);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }

    @Override
    public void truckExitFenceMessage(int waybillId) {
        fenceMessage(waybillId, "MONITOR_TRUCK_LEAVE");
    }

    @Override
    public void truckEntryFenceMessage(int waybillId) {
        fenceMessage(waybillId, "MONITOR_TRUCK_ENTRY");
    }

    private void fenceMessage(int waybillId, String key) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (waybill == null) return;
        com.juma.vms.driver.domain.Driver driver = vmsCommonService.loadDriverByDriverId(waybill.getDriverId());

        if (driver == null) return;

        LoginUser loginUser = new LoginUser(waybill.getTenantId(), 1);
        Map<String, Object> extras = buildAppMsgParameter(waybillId);
        pushAppMessage(key, extras, loginUser, driver.getUserId() + "");
    }

    @Override
    public void robWaybillTimeoutMessage(int waybillId, Map<String, String> cacheMap, LoginUser loginUser) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (waybill == null) return;
        Map<String, Object> extras = buildAppMsgParameter(waybillId);
        // 通知后台配置人员
//        pushConfigureMessage("CUST_MAN_TIMEOUT_NO_MATCH_SMS", "CUST_MAN_TIMEOUT_NO_MATCH_MAIL", waybill.getTenantId(),
//                waybill.getAreaCode(), ConfigParam.ParamKey.MANAGE_CREATE_EXCEPTION_WAYBILL, extras, loginUser);
        if (cacheMap == null) return;
        String userId = cacheMap.get("user_id");
        loginUser.setTenantId(waybill.getTenantId());
        if (StringUtils.isNotBlank(userId)) {
            extras.put("planDeliveryTime", cacheMap.get("planDeliveryTime"));
            pushAppMessage(Constants.MSG_CUST_MAN_BILL_HANDLE_TIME_OUT, extras, loginUser, userId);
        }
    }

    @Override
    public void planTimeChangeMessage(int waybillId) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (waybill == null) return;
        com.juma.vms.driver.domain.Driver driver = vmsCommonService.loadDriverByDriverId(waybill.getDriverId());
        if (driver == null) return;

        LoginUser loginUser = new LoginUser(waybill.getTenantId(), 0);
        Map<String, Object> extras = buildAppMsgParameter(waybillId);
        // App通知
        pushAppMessage(Constants.PLAN_DELIVERY_TIME_CHANGE_APP, extras, loginUser, driver.getUserId() + "");
        // 电话通知
        Map<String, Object> voiceMap = new HashMap<String, Object>();
        voiceMap.put("waybillNo", extras.get("waybillNo"));
        callToDriver(Constants.PLAN_DELIVERY_TIME_CHANGE_APP, "TTS_94380035", waybill, driver, voiceMap);
    }

    private void callToDriver(String smsKey, String callKey, Waybill waybill, com.juma.vms.driver.domain.Driver driver,
            Map<String, Object> voiceMap) {
        if (null == driver.getTelRemindSwitch() || !driver.getTelRemindSwitch()) {
            return;
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setTenantId(waybill.getTenantId());
        MsgConf msgConf = messageServiceProvider.getMsgTemplate(loginUser.getTenantId(), smsKey, MsgConf.MsgType.SMS);
        if (msgConf != null) {
            pushVoiceMessage(callKey, voiceMap, msgConf.getMsgConfId() + "-" + waybill.getWaybillId() + "", loginUser,
                    driver.getPhone());
        }
    }

    @Override
    public void timeoutNoReceived(int waybillId, LoginUser loginUser) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (waybill == null) return;

        loginUser.setTenantId(waybill.getTenantId());

        // 通知客户经理
        if (null == waybill.getCustomerManagerId()) {
            return;
        }
        Employee employee = employeeService.loadEmployee(waybill.getCustomerManagerId(), loginUser);
        if (employee == null || employee.getUserId() == null) return;
        Map<String, Object> extras = buildAppMsgParameter(waybillId);

        pushAppMessage("TIME_OUT_TO_RECEIVED", extras, loginUser, employee.getUserId() + "");
    }

    @Override
    public void pushConfigureMessage(String smsKey, String emailKey, Integer tenantId, String areaCode,
            ParamKey paramKey, Map<String, Object> dataMap, LoginUser loginUser) {
        log.info("pushConfigureMessage->start->paramKey:{}->departmentId:{}", paramKey, areaCode);

        if (null == loginUser) {
            loginUser = Constants.SYS_LOGIN_USER;
            loginUser.setTenantId(tenantId);
        }

        BusinessArea businessArea = businessAreaService.loadLogicBusinessArea(areaCode, loginUser);
        if (null == businessArea) {
            return;
        }
        log.info("pushConfigureMessage->businessArea:{}", JSON.toJSONString(businessArea));

        List<ConfigParamOption> admins = configParamOptionService.listPushParamOptionBy(businessArea.getAreaCode(),
                paramKey, loginUser);
        if (!admins.isEmpty()) {
            log.info("pushConfigureMessage->start->admins:{}", admins.toString());
            int len = admins.size();
            List<String> phones = new ArrayList<String>();
            List<String> emails = new ArrayList<String>();
            for (int i = 0; i < len; i++) {
                ConfigParamOption option = admins.get(i);
                if (StringUtils.isNotBlank(option.getOptionName())) {
                    emails.add(option.getOptionName());
                }
                if (StringUtils.isNotBlank(option.getOptionValue())) {
                    phones.add(option.getOptionValue());
                }
            }

            log.info("pushConfigureMessage->size->phones:{}", phones.size());
            log.info("pushConfigureMessage->size->emails:{}", emails.size());
            if (StringUtils.isNotBlank(smsKey) && phones.size() > 0) {
                log.info("pushConfigureMessage->pushSmsMessage->phones:{}", JSON.toJSON(phones));
                pushSmsMessage(smsKey, dataMap, loginUser, (String[]) phones.toArray(new String[phones.size()]));
            }
            if (StringUtils.isNotBlank(emailKey) && emails.size() > 0) {
                log.info("pushConfigureMessage->pushEmailMessage->emails:{}", JSON.toJSON(emails));
                pushEmailMessage(emailKey, dataMap, (String[]) emails.toArray(new String[emails.size()]));
            }
        }
    }

    @Override
    public Map<String, Object> buildAppMsgParameter(int waybillId) {
        Map<String, Object> extras = new HashMap<String, Object>();
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (waybill != null) {
            extras.put("waybillId", waybill.getWaybillId());
            extras.put("status", waybill.getStatus());
            extras.put("statusView", waybill.getStatusView());
            extras.put("freight", waybill.getEstimateFreight());

            extras.put("estimateDistance", waybill.getEstimateDistance());

            extras.put("planDeliveryTime", DateUtil.format(waybill.getPlanDeliveryTime(), "yyyy年MM月dd日 HH:mm"));
            extras.put("showForDriverFreight", waybill.getShow4DriverFreight());
            // 兼容老模版
            extras.put("show4DriverFreight", waybill.getShow4DriverFreight());
            String waybillNo = waybill.getWaybillNo();
            extras.put("waybillNo", waybillNo);
            if (waybillNo != null && waybillNo.length() > 4) {
                int length = waybillNo.length();
                extras.put("waybillNo", waybillNo.substring(length - 4, length));
            }
            if (waybill.getBusinessBranch() != null) {
                if (waybill.getBusinessBranch() == Waybill.BusinessBranch.BRANCH_FULL.getCode()) {
                    extras.put("msgTitle", "新运单（整车）");
                } else if (waybill.getBusinessBranch() == Waybill.BusinessBranch.BRANCH_SCATTERED.getCode()) {
                    extras.put("msgTitle", "新运单（零担）");
                }
            }
            com.juma.vms.driver.domain.Driver driver = vmsCommonService.loadDriverByDriverId(waybill.getDriverId());
            if (driver != null) {
                extras.put("nickname", driver.getName());
                extras.put("driverMobile", driver.getPhone());
                // 兼容老模版
                extras.put("mobile", driver.getPhone());

                LoginUser loginUser = new LoginUser();
                loginUser.setTenantId(waybill.getTenantId());
                loginUser.setTenantCode(waybill.getTenantCode());
                EcoUser ecoUser = authCommonService.loadEcoUserByUserId(driver.getUserId(), loginUser);
                if (null != ecoUser) {
                    extras.put("ecoUserId", ecoUser.getEcoUserId());
                }
            }
            extras.put("plateNumber", waybill.getPlateNumber());

            // 客户信息
            extras.put("customerName", waybill.getCustomerName());

            String deliveryAddressStr = waybillDeliveryAddressService.findByWaybillId(waybill.getWaybillId()) == null
                    ? "" : waybillDeliveryAddressService.findByWaybillId(waybill.getWaybillId()).getAddressName();
            extras.put("deliveryAddressStr", deliveryAddressStr);
            String googsInfoStr = truckRequireService.getGoodsFullName(null, waybill.getWaybillId());
            String truckRequireStr = truckRequireService.getTruckRequire(null, waybill.getWaybillId());
            extras.put("googsInfoStr", googsInfoStr);
            extras.put("truckRequireStr", truckRequireStr);
        }
        log.info("buildAppMsgParameter {}.", extras);
        return extras;
    }

    // 下单成功
    private Map<String, Object> buildNewWechatWaybillParameter(int waybillId) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        if (waybill != null) {
            dataMap.put("first", "您的订单已经成功提交！");
            dataMap.put("keyword1", waybill.getWaybillNo());
            dataMap.put("keyword2", DateUtil.format(waybill.getCreateTime(), DateUtil.YYYYMMDDHHMM));
            dataMap.put("keyword3", "人工沟通后确定");
            dataMap.put("keyword4", "派车后确定");
            dataMap.put("keyword5", "派车后确定");
            dataMap.put("remark", "稍后会有客服与您沟通运单详情。客服电话：400-184-9156");
        }
        return dataMap;
    }

    // 支付成功
    private Map<String, Object> buildPayWechatWaybill(int waybillId) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        if (waybill != null) {
            dataMap.put("first", "运单支付成功！");
            dataMap.put("keyword1", waybill.getWaybillNo());
            dataMap.put("keyword2", "￥" + waybill.getEstimateFreight() + "元");
            dataMap.put("remark", "感谢您对我们的信赖和惠顾。");
        }
        return dataMap;
    }

    // 派车通知
    @Override
    public Map<String, Object> assignCarSuccessTemplate(Driver driver, String goodsInfo, Date planDeliveryTime) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("first", "您的运单已派车成功！");
        dataMap.put("keyword1", driver.getNickname() + " 电话:" + driver.getContactPhone());
        dataMap.put("keyword2", "货物信息(" + goodsInfo + ")");
        dataMap.put("keyword3", DateUtil.format(planDeliveryTime, DateUtil.YYYYMMDDHHMM));
        dataMap.put("remark", "点击查看运单详情");
        return dataMap;
    }

    // 司机到仓
    @Override
    public Map<String, Object> arriveDepotTemplate(String plateNumber, Date arriveDepotTime) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("first", "司机已到仓，等待装货！");
        dataMap.put("keyword1", DateUtil.format(arriveDepotTime, DateUtil.YYYYMMDDHHMM));
        dataMap.put("keyword2", plateNumber);
        dataMap.put("remark", "点击查看运单详情");
        return dataMap;
    }

    // 司机离仓
    @Override
    public Map<String, Object> leaveDepotTemplate(Waybill waybill, Driver driver, String truckTypeName,
            String goodsInfo, String plateNumber) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("first", "司机已离仓，开始配送！");
        dataMap.put("keyword1", waybill.getWaybillNo());
        dataMap.put("keyword2", DateUtil.format(waybill.getDeliveryTime(), DateUtil.YYYYMMDDHHMM));
        dataMap.put("keyword3", goodsInfo);
        dataMap.put("keyword4", driver.getNickname() + "(" + driver.getContactPhone() + ")");
        dataMap.put("keyword5", truckTypeName + "(" + plateNumber + ")");
        dataMap.put("remark", "点击查看运单详情");
        return dataMap;
    }

    // 配送完成
    @Override
    public Map<String, Object> waybillDeliveriedTemplate(Waybill waybill, String plateNumber) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("first", "运单已配送完成！");
        dataMap.put("keyword1", waybill.getWaybillNo());
        dataMap.put("keyword2", plateNumber);
        dataMap.put("keyword3", DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMM));
        dataMap.put("remark", "请尽量提前预约车辆，以便更好为您服务。");
        return dataMap;
    }

    // 提醒支付
    @Override
    public Map<String, Object> waybillWatingPayTemplate(Waybill waybill, String goodsWeight) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("first", "您还有一笔订单未支付哦！");
        dataMap.put("keyword1", waybill.getWaybillNo());
        dataMap.put("keyword2", StringUtils.isNotBlank(goodsWeight) ? (goodsWeight + "吨") : null);
        dataMap.put("keyword3", "￥ " + waybill.getEstimateFreight() + "元");
        dataMap.put("keyword4", Waybill.ReceiptType.getPayWayStr(waybill.getReceiptType()));
        dataMap.put("remark", "请尽快完成支付。点击查看详情");
        return dataMap;
    }

    // 提醒评价
    @Override
    public Map<String, Object> waybillEvaluateTemplate(Waybill waybill, Driver driver) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("first", "司机已确认付款，邀请您进行评价哦！");
        dataMap.put("keyword1", waybill.getWaybillNo());
        dataMap.put("keyword2", driver.getNickname() + "(" + driver.getContactPhone() + ")");
        dataMap.put("keyword3", DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMM));
        dataMap.put("remark", "您的评价是我们持续改善的动力，谢谢");
        return dataMap;
    }

    // 收到回单
    @Override
    public Map<String, Object> waybillHasNeedReceipTTemplate(Waybill waybill) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("first", "回单已送达至您的专属客户经理！");
        dataMap.put("keyword1", waybill.getWaybillId());
        dataMap.put("keyword2", DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMM));
        dataMap.put("keyword3", "￥" + waybill.getEstimateFreight() + "元");
        dataMap.put("remark", "我们将尽快将回单送回给您。");
        return dataMap;
    }

    // 用车时间提醒
    @Override
    public void planDeliveryTimeRemind(int waybillId) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (waybill == null)
            return;
        com.juma.vms.driver.domain.Driver driver = vmsCommonService.loadDriverByDriverId(waybill.getDriverId());
        if (driver == null)
            return;
        Map<String, Object> extras = buildAppMsgParameter(waybillId);
        LoginUser loginUser = new LoginUser(waybill.getTenantId(), 1);
        extras.put("timeRemindText",
                redisClient.get(RedisKeyConstants.TMS_PLAN_DELIVERY_TIME_REMIND_KEY + "_" + waybillId));// 时间提醒文本
        if (null != driver.getSmsRemindSwitch() && driver.getSmsRemindSwitch()) {
            pushSmsMessage("PLAN_DELIVERY_TIME_REMIND", extras, loginUser, driver.getPhone());
        }
        pushAppMessage("PLAN_DELIVERY_TIME_REMIND", extras, loginUser, driver.getUserId() + "");
    }

    // 司机确认指派
    @Override
    public void driverConfirmAssigned(int waybillId, LoginUser loginUser) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (waybill == null) return;
        if (null == waybill.getCustomerManagerId()) {
            return;
        }
        try {
            EmployeeInfo employeeInfo = waybillCommonService.loadEmployeeInfo(waybill.getCustomerManagerId());
            if (employeeInfo == null || employeeInfo.getUserId() == null) return;
            Map<String, Object> extras = buildAppMsgParameter(waybillId);
//            if (waybill.getBusinessBranch() != null
//                    && waybill.getBusinessBranch() == Waybill.BusinessBranch.SPECIAL_CAR.getCode()) {
//                pushSmsMessage("DRIVER_CONFIRM_ASSIGNED", extras, loginUser, employeeInfo.getMobileNumber());
//            }
            pushAppMessage("DRIVER_CONFIRM_ASSIGNED", extras, loginUser, employeeInfo.getUserId() + "");
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }

    @Override
    public void temperatureAlert(Integer waybillId, LoginUser loginUser) {
        // 司机语音
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (waybill == null) return;
        com.juma.vms.driver.domain.Driver driver = vmsCommonService.loadDriverByDriverId(waybill.getDriverId());
        if (driver == null) return;
        EmployeeInfo employeeInfo = waybillCommonService.loadEmployeeInfo(waybill.getCustomerManagerId());
        if (employeeInfo == null || employeeInfo.getUserId() == null) return;
        Map<String, Object> extras = new HashMap<String,Object>();
        extras.put("plateNumber", waybill.getPlateNumber());
        extras.put("driverName", waybill.getDriverName());
        
        //司机
        pushAppMessage("TEMPERATURE_ALERT_TO_DRIVER_APP", extras, loginUser, driver.getUserId()+"");
        if (null != driver.getTelRemindSwitch() && driver.getTelRemindSwitch()) {
            pushVoiceMessage("TTS_134323957", extras, null, loginUser, driver.getPhone());
        }
        
        //经济人
        pushAppMessage("TEMPERATURE_ALERT_TO_CUSTOMER_MANAGER_APP", extras, loginUser, employeeInfo.getUserId()+"");
        pushVoiceMessage("TTS_134323961", extras, null, loginUser, employeeInfo.getMobileNumber());
        
    }

    private String getURLEncoderString(String url) {
        String result = "";
        if (null == url) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
