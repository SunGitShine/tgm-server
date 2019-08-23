package com.juma.tgm.cron.service.impl;

import com.giants.cache.redis.RedisClient;
import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.conf.domain.ConfParamOption;
import com.juma.monitor.service.RealTimePositionService;
import com.juma.monitor.truck.domain.RealTimePosition;
import com.juma.tgm.common.*;
import com.juma.tgm.common.id.IdGeneratorTable;
import com.juma.tgm.cron.bussinessModules.FixedDemandDataTransformModule;
import com.juma.tgm.cron.bussinessModules.Task4WaybillModule;
import com.juma.tgm.cron.service.CronjobService;
import com.juma.tgm.customerManager.domain.Task4Waybill;
import com.juma.tgm.customerManager.service.Task4WaybillService;
import com.juma.tgm.daily.domain.ProjectDaily;
import com.juma.tgm.daily.enums.ProjectDailyEnum;
import com.juma.tgm.daily.service.ProjectDailyService;
import com.juma.tgm.gaode.domain.GaodeDistanceResponse;
import com.juma.tgm.id.service.IdGeneratorService;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.tools.service.AuthCommonService;
import com.juma.tgm.tools.service.MessagePushService;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillBindFence;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.example.WaybillExample;
import com.juma.tgm.waybill.service.GaoDeMapService;
import com.juma.tgm.waybill.service.WaybillBindFenceService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillDeliveryAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CronjobServiceImpl implements CronjobService {

    private static Logger log = LoggerFactory.getLogger(CronjobServiceImpl.class);

    @Resource
    private MessagePushService messagePushService;

    @Resource
    private RealTimePositionService realTimePositionService;

    @Resource
    private TruckService truckService;

    @Resource
    private GaoDeMapService gaoDeMapService;

    @Resource
    private WaybillDeliveryAddressService waybillDeliveryAddressService;

    @Resource
    private RedisClient redisClient;

    @Resource
    private WaybillCommonService waybillCommonService;

    @Resource
    private WaybillBindFenceService waybillBindFenceService;

    @Resource
    private Task4WaybillModule task4WaybillModule;

    @Resource
    private FixedDemandDataTransformModule fixedDemandDataTransformModule;

    @Resource
    private ProjectService projectService;

    @Resource
    private Task4WaybillService task4WaybillService;
    
    @Resource
    private ProjectDailyService projectDailyService;

    @Resource
    private IdGeneratorService idGeneratorService;

    @Resource
    private AuthCommonService authCommonService;


    /**
     * 
     * @Title: whichInterval
     * @Description: 哪个区间
     * @return: 0.5 1 2 @throws
     */
    public static float whichInterval(Date planDeliveryTime) {
        // 画4个点

        // 当前时间
        Calendar spot0 = Calendar.getInstance();
        spot0.setTime(new Date());
        spot0.set(Calendar.SECOND, 0);
        spot0.set(Calendar.MILLISECOND, 0);

        // 起点
        Calendar spot1 = Calendar.getInstance();
        spot1.setTime(planDeliveryTime);
        spot1.set(Calendar.SECOND, 0);
        spot1.set(Calendar.MILLISECOND, 0);
        // 半小时前
        Calendar spot2 = Calendar.getInstance();
        spot2.setTime(planDeliveryTime);
        spot2.add(Calendar.MINUTE, -30);
        spot2.set(Calendar.SECOND, 0);
        spot2.set(Calendar.MILLISECOND, 0);
        // 1小时前
        Calendar spot3 = Calendar.getInstance();
        spot3.setTime(planDeliveryTime);
        spot3.add(Calendar.HOUR_OF_DAY, -1);
        spot3.set(Calendar.SECOND, 0);
        spot3.set(Calendar.MILLISECOND, 0);
        // 2小时前
        Calendar spot4 = Calendar.getInstance();
        spot4.setTime(planDeliveryTime);
        spot4.add(Calendar.HOUR_OF_DAY, -2);
        spot4.set(Calendar.SECOND, 0);
        spot4.set(Calendar.MILLISECOND, 0);

        if (spot0.getTimeInMillis() > spot1.getTimeInMillis())
            return 0;
        if (spot0.getTimeInMillis() > spot2.getTimeInMillis())
            return 0.5f;
        if (spot0.getTimeInMillis() > spot3.getTimeInMillis())
            return 1f;
        if (spot0.getTimeInMillis() > spot4.getTimeInMillis())
            return 2f;
        return 0;
    }

    @Override
    public void planDeliveryTimeRemind() {
        log.info("Cron planDeliveryTimeRemind entry.");
        // 说明：运单完成的时候清除Cache

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -3);

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DAY_OF_MONTH, 3);

        WaybillExample example = new WaybillExample();
        WaybillExample.Criteria criteria = example.createCriteria();

        criteria.andStatusViewEqualTo(Waybill.StatusView.WATING_DELIVERY.getCode());
        criteria.andPlanDeliveryTimeBetween(cal.getTime(), cal2.getTime());

        List<Waybill> rows = waybillCommonService.selectByExample(example);
        for (Waybill waybill : rows) {
            log.info("Cron planDeliveryTimeRemind waybill {}.", waybill.getWaybillId());

            WaybillBindFence waybillBindFence = waybillBindFenceService.findByFenceReach(waybill.getWaybillId());
            if (waybillBindFence != null) {
                log.info("Cron planDeliveryTimeRemind waybillBindFence {}.", waybillBindFence.getWaybillId());
                continue;
            }

            Date planDeliveryTime = waybill.getPlanDeliveryTime();
            float val = whichInterval(planDeliveryTime);
            log.info("Cron planDeliveryTimeRemind waybill {},whichInterval {}.", waybill.getWaybillId(), val);
            String key = RedisKeyConstants.TMS_PLAN_DELIVERY_TIME_REMIND_KEY + "_" + waybill.getWaybillId();
            String cacheValue = (String) redisClient.get(key);
            log.info("Cron planDeliveryTimeRemind cache value {}.", cacheValue);

            if ((val == 0.5 || val == 1 || val == 2)) {

                if (val == 0.5 && !"半".equals(cacheValue)) {
                    redisClient.set(key, "半");
                } else if (val == 1 && !"1".equals(cacheValue)) {
                    redisClient.set(key, "1");
                } else if (val == 2 && !"2".equals(cacheValue)) {
                    redisClient.set(key, "2");
                } else {
                    continue;
                }

                Truck truck = truckService.getTruck(waybill.getTruckId());
                if (truck == null) {
                    log.info("Cron planDeliveryTimeRemind waybill {} no truck {}.", waybill.getWaybillId(),
                            waybill.getTruckId());
                    continue;
                }

                RealTimePosition realTimePosition = realTimePositionService
                        .queryLastPosByPlateNum(truck.getPlateNumber());
                if (realTimePosition != null && realTimePosition.getAmapLng() != null
                        && realTimePosition.getAmapLat() != null) {
                    String truckPosition = realTimePosition.getAmapLng() + "," + realTimePosition.getAmapLat();
                    log.info("车辆实时坐标：{}，{}.", truck.getPlateNumber(), truckPosition);
                    WaybillDeliveryAddress waybillDeliveryAddress = waybillDeliveryAddressService
                            .findByWaybillId(waybill.getWaybillId());

                    GaodeDistanceResponse gaodeDistanceResponse = gaoDeMapService.lineDistance(truckPosition,
                            waybillDeliveryAddress.getCoordinates());
                    log.info("amap line distance {}.", gaodeDistanceResponse.toString());
                    if (gaodeDistanceResponse != null && gaodeDistanceResponse.getResults() != null
                            && !gaodeDistanceResponse.getResults().isEmpty()) {

                        com.juma.tgm.gaode.domain.GaodeDistanceResponse.Result result = gaodeDistanceResponse.getResults().get(0);

                        if (result == null)
                            continue;

                        if (val == 0.5 && result.getDuration() > 30 * 60) {
                            messagePushService.planDeliveryTimeRemind(waybill.getWaybillId());
                        }
                        if (val == 1 && result.getDuration() > 60 * 60) {
                            messagePushService.planDeliveryTimeRemind(waybill.getWaybillId());
                        }
                        if (val == 2 && result.getDuration() > 2 * 60 * 60) {
                            messagePushService.planDeliveryTimeRemind(waybill.getWaybillId());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void transportReportForTruckCustomerSms() {
        List<Project> list = projectService.listProjectDailySms(DateUtil.dayAddStart(-1), DateUtil.dayAddEnd(-1));
        for (Project t : list) {
            if (null == t.getProjectId() || !BaseUtil.checkMobilePhone(t.getBusinessLinktel()) || null == t.getTenantId()) {
                continue;
            }

            LoginUser loginUser = Constants.SYS_LOGIN_USER;
            loginUser.setTenantId(t.getTenantId());

            Map<String, Object> extras = new HashMap<String, Object>();
            try {
                extras.put("truckCustomerId", Base62.encodeByMultiply(t.getProjectId()));
                extras.put("tenantId", Base62.encodeByMultiply(t.getTenantId()));
                extras.put("queryTime", DateUtil.format(DateUtil.MMDDHHMM));
                messagePushService.pushSmsMessage("TRANSPORT_REPORT_SMS", extras, loginUser, t.getBusinessLinktel());
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
            }
        }
    }


    public static void main(String[] args) {
        // Calendar cal = Calendar.getInstance();
        // cal.set(Calendar.YEAR, 2017);
        // cal.set(Calendar.MONTH, 7);
        // cal.set(Calendar.DAY_OF_MONTH, 3);
        // cal.set(Calendar.HOUR_OF_DAY, 19);
        // cal.set(Calendar.MINUTE, 56);
        //
        // System.out.println(whichInterval(cal.getTime()));

        // String key = 1302 + "";
        // byte[] bytes = key.getBytes();
        // System.out.println(bytes);
        // String encode = Base64.encode(key);
        // System.out.println(encode);
        //
        // String decode = Base64.decodeForUrl(encode.getBytes());
        // System.out.println(decode);
        //
//        System.out.println(new BigDecimal(23 + "").divide(new BigDecimal(5 + ""), BigDecimal.ROUND_UP).intValue());
//
//        String encode2 = Base62.encodeByMultiply(2350);
//        System.out.println(encode2);
//
//        BigInteger decode2 = Base62.decodeByDivide(encode2);
//        System.out.println(decode2);
//
//        String encode3 = Base62.encodeByMultiply(2);
//        System.out.println(encode3);
//
//        BigInteger decode3 = Base62.decodeByDivide(encode3);
//        System.out.println(decode3);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date expireTime = sdf.parse(sdf.format(calendar.getTime()));
            System.out.println(sdf.format(calendar.getTime()));
            System.out.println(sdf.parse("2012-03-25"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void task4Waybill() throws BusinessException {
        //执行建单
        task4WaybillModule.sendTaskToMQ();
    }


    @Override
    public void transformFixedDemandData(Boolean newFlag) throws BusinessException{
        fixedDemandDataTransformModule.transformTaskData(newFlag);
    }


    @Override
    public void pointTransformFixedDemandData(List<Integer> fixedDemandIds) throws BusinessException {
        fixedDemandDataTransformModule.pointTransformTaskData(fixedDemandIds);
    }

    @Override
    public void doTaskAutoCreateWaybill(Task4Waybill task4Waybill) {

       task4WaybillModule.processTask4Waybill(task4Waybill);
    }

    @Override
    public void pushTaskReportMsg(Task4Waybill task4Waybill) {
        //发送执行结果推送
        task4WaybillModule.pushTaskReportMsg(task4Waybill);
    }


    @Override
    public void produceDailyReport() {
        List<com.juma.tgm.project.domain.v2.Project> projects = projectService.findAllProjectInRunning();
        if (null == projects || 0 == projects.size()) {
            return;
        }
        for (Project project : projects) {
            if (null == project || null == project.getTenantId() || null == project.getAreaCode() || null == project
                    .getCustomerId() || null == project.getProjectId()) {
                continue;
            }
            ProjectDaily daily = new ProjectDaily();
            BeanUtils.copyProperties(project, daily);
            daily.setProjectDailyNo(idGeneratorService.genProjectDailyId(IdGeneratorTable.IdType.RB));
            daily.setDailyStatus(ProjectDailyEnum.DailyStatus.UNEXPIRED.getCode());
            daily.setFreightStatus(ProjectDailyEnum.FreightStatus.NOT_CONFIRMED.getCode());
            daily.setStandingBookStatus(ProjectDailyEnum.StandingBookStatus.NO_UPLOADED.getCode());
            daily.setLastUpdateTime(new Date());
            daily.setProjectDailyDate(new Date());
            //定时任务，最后更新设置为系统管理员
            daily.setLastUpdateUserId(1);
            daily.setCreateUserId(1);
            daily.setIsDelete(false);
            daily.setCreateTime(new Date());
            try {
                projectDailyService.insert(daily, new LoginUser(1));
            } catch (Exception e) {
                log.error("定时任务-生成日报异常", e);
            }
        }
    }


    @Override
    public void expireDailyReport() {
        List<ConfParamOption> paramOptions = authCommonService.listOption(Constants.ALLOW_CHANGE_PRICE_TIME_LIMIT_KEY);
        if (null == paramOptions || 0 == paramOptions.size()) {
            throw new BusinessException("cannotGetDailyConfig", "无法获取日报配置中最大天数！");
        }
        int expireDays = Integer.parseInt(paramOptions.get(0).getOptionValue());//配置获取最大天数
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - expireDays);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date expireTime = calendar.getTime(); ;//获取超时时间：当前时间-最大天数

        //查询日报中未到期日报，未删除，非当前日日报
        List<ProjectDaily> projectDailys = projectDailyService.getListByFreightStatus();
        // 进行数据整合，按照日期（yyyy-MM-dd）进行统计
        Map<Date, Set<Integer>> dailys = new HashMap<>();
        Map<Date, Set<ProjectDaily>> allProjectdailes = new HashMap<>();//按日期整合后日报
        for (ProjectDaily projectDaily : projectDailys) {
            if (null == projectDaily || null == projectDaily.getProjectId()) {
                continue;
            }
            if (null == dailys.get(projectDaily.getProjectDailyDate())) {
                HashSet<Integer> projectIds = new HashSet<>();
                projectIds.add(projectDaily.getProjectId());
                dailys.put(projectDaily.getProjectDailyDate(), projectIds);
                HashSet<ProjectDaily> projectDailies = new HashSet<>();
                projectDailies.add(projectDaily);
                allProjectdailes.put(projectDaily.getProjectDailyDate(), projectDailies);
            } else {
                dailys.get(projectDaily.getProjectDailyDate()).add(projectDaily.getProjectId());
                allProjectdailes.get(projectDaily.getProjectDailyDate()).add(projectDaily);
            }
        }

        Set<Integer> unExpireProjectIds = new HashSet<>();
        for (Map.Entry<Date, Set<Integer>> entry : dailys.entrySet()) {
            List<Waybill> waybills = waybillCommonService.checkWayBillStatsByDate(entry.getKey());
            for (Waybill waybill : waybills) {
                if (null == waybill || waybill.getWaybillSource()
                        .equals(Waybill.WaybillSource.BACKGROUND_IMPORT.getCode()) || unExpireProjectIds
                        .contains(waybill.getProjectId())) {
                    continue;
                }

                // 不满足失效，添加到未失效集合中：失效集合不包含该项目id|运单状态为非完成
                if (!entry.getValue().contains(waybill.getProjectId()) || !waybill.getStatusView()
                        .equals(Waybill.StatusView.FINISH.getCode())) {
                    unExpireProjectIds.add(waybill.getProjectId());
                    continue;
                }
                // 不满足失效，添加到未失效集合中：最后完成时间大于失效时间，未处于失效期
                if (waybill.getFinishTime().getTime() >= expireTime.getTime()) {
                    unExpireProjectIds.add(waybill.getProjectId());
                }
            }

            List<ProjectDaily> oneDayProjectDailys = new ArrayList<>(allProjectdailes.get(entry.getKey()));
            List<ProjectDaily> unExpireList = new ArrayList<>();
            for (int n = 0; n < oneDayProjectDailys.size(); n++) {
                oneDayProjectDailys.get(n)
                        .setDailyStatus(ProjectDailyEnum.DailyStatus.HAS_EXPIRED.getCode());
                if (unExpireProjectIds.contains(oneDayProjectDailys.get(n).getProjectId())) {
                    unExpireList.add(oneDayProjectDailys.get(n));
                }
            }
            oneDayProjectDailys.removeAll(unExpireList);
            // 进行失效处理，按照天为单位,分段100
            List<ProjectDaily> segProjectDaily = new ArrayList<>();
            for (int i = 0; i < oneDayProjectDailys.size(); i++) {
                segProjectDaily.add(oneDayProjectDailys.get(i));
                if (i % 100 == 0 || i == oneDayProjectDailys.size() - 1) {
                    try {
                        projectDailyService.updateDailysByPrimaryKey(segProjectDaily);
                    } catch (Exception e) {
                        log.error("定时任务-日报过期异常", e);
                    } finally {
                        segProjectDaily.clear();
                    }
                }
            }

            unExpireProjectIds.clear();
        }

    }

    @Override
    public void confirmeAll() {

        List<ProjectDaily> projectDailies = projectDailyService.getListByFreightStatus(ProjectDailyEnum.FreightStatus.PART_CONFIRMED);//获取日报状态为 -部分确认& 未到期
        Map<Date, Set<Integer>> dailys = new HashMap<>();
        Map<Date, Set<ProjectDaily>> allDayProjectDailys = new HashMap<>();// 所有日期内运单
        for (ProjectDaily projectDaily : projectDailies) {
            if (null == projectDaily || null == projectDaily.getProjectId()) {
                continue;
            }
            if (null == dailys.get(projectDaily.getProjectDailyDate())) {
                HashSet<Integer> projectIds = new HashSet<>();
                projectIds.add(projectDaily.getProjectId());
                dailys.put(projectDaily.getProjectDailyDate(), projectIds);
                HashSet<ProjectDaily> projectDailys = new HashSet<>();
                projectDailys.add(projectDaily);
                allDayProjectDailys.put(projectDaily.getProjectDailyDate(), projectDailys);
            } else {
                dailys.get(projectDaily.getProjectDailyDate()).add(projectDaily.getProjectId());
                allDayProjectDailys.get(projectDaily.getProjectDailyDate()).add(projectDaily);
            }
        }
        for (Map.Entry<Date, Set<Integer>> entry : dailys.entrySet()) {
            List<Integer> unconfirmProjectIds = waybillCommonService.countWaybillStatus(new ArrayList<Integer>(entry
                    .getValue()), entry.getKey());
            List<ProjectDaily> oneDayProjectdailes = new ArrayList<>(allDayProjectDailys.get(entry.getKey()));
            List<ProjectDaily> unConfirmed = new ArrayList<>();
            for (int n = 0; n < oneDayProjectdailes.size(); n++) {
                if (unconfirmProjectIds.contains(oneDayProjectdailes.get(n).getProjectId())) {
                    unConfirmed.add(oneDayProjectdailes.get(n));
                } else {
                    oneDayProjectdailes.get(n)
                            .setFreightStatus(ProjectDailyEnum.FreightStatus.HAS_CONFIRMED.getCode());
                }
            }
            oneDayProjectdailes.removeAll(unConfirmed);
            //分段处理,100条一次进行
            List<ProjectDaily> segProjectDaily = new ArrayList<>();
            for (int i = 0; i < oneDayProjectdailes.size(); i++) {
                segProjectDaily.add(oneDayProjectdailes.get(i));
                if (i % 100 == 0 || i == oneDayProjectdailes.size() - 1) {
                    try {
                        projectDailyService.deleteProjectDaily(segProjectDaily);
                    } catch (Exception e) {
                        log.error("定时任务-日报状态确认异常", e);
                    } finally {
                        segProjectDaily.clear();
                    }
                }
            }
        }

    }

    @Override
    public void cleanInvalidDaily(Date dailyDate) {
        //获取日报状态为 未过期，运费状态 为未确认日报，进行第一轮过滤
        List<ProjectDaily> projectDailies = projectDailyService.getListByFreightStatus(ProjectDailyEnum.FreightStatus.NOT_CONFIRMED, dailyDate);
        Map<Date, Set<Integer>> dailys = new HashMap<>();
        Map<Date, Set<ProjectDaily>> allDayProjectDailys = new HashMap<>();
        for (ProjectDaily projectDaily : projectDailies) {
            if (null == projectDaily || null == projectDaily.getProjectId()) {
                continue;
            }
            if (null == dailys.get(projectDaily.getProjectDailyDate())) {
                HashSet<Integer> projectIds = new HashSet<>();
                projectIds.add(projectDaily.getProjectId());
                dailys.put(projectDaily.getProjectDailyDate(), projectIds);
                HashSet<ProjectDaily> projectDailys = new HashSet<>();
                projectDailys.add(projectDaily);
                allDayProjectDailys.put(projectDaily.getProjectDailyDate(), projectDailys);
            } else {
                dailys.get(projectDaily.getProjectDailyDate()).add(projectDaily.getProjectId());
                allDayProjectDailys.get(projectDaily.getProjectDailyDate()).add(projectDaily);
            }
        }

        for (Map.Entry<Date, Set<Integer>> entry : dailys.entrySet()) {
            List<Waybill> waybills = waybillCommonService.checkWayBillStatsByDate(entry.getKey());
            Set<Integer> unDeletebills = new HashSet<>();
            for (Waybill waybill : waybills) {
                if (entry.getValue().contains(waybill.getProjectId())) {
                    unDeletebills.add(waybill.getProjectId());
                }
            }
            List<ProjectDaily> oneDayProjectdailes = new ArrayList<>(allDayProjectDailys.get(entry.getKey()));
            List<ProjectDaily> hasMatchedWaybills = new ArrayList<>();
            for (int n = 0; n < oneDayProjectdailes.size(); n++) {
                if (unDeletebills.contains(oneDayProjectdailes.get(n).getProjectId())) {
                    hasMatchedWaybills.add(oneDayProjectdailes.get(n));
                }
            }
            oneDayProjectdailes.removeAll(hasMatchedWaybills);
            //分段处理,100条一次进行
            List<ProjectDaily> segProjectDaily = new ArrayList<>();
            for (int i = 0; i < oneDayProjectdailes.size(); i++) {
                oneDayProjectdailes.get(i).setIsDelete(Boolean.TRUE);
                segProjectDaily.add(oneDayProjectdailes.get(i));
                if (i % 100 == 0 || i == oneDayProjectdailes.size() - 1) {
                    try {
                        projectDailyService.deleteProjectDaily(segProjectDaily);
                    } catch (Exception e) {
                        log.error("定时任务-无运单日报删除异常", e);
                    } finally {
                        segProjectDaily.clear();
                    }
                }
            }
        }

    }


}
