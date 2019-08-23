package com.juma.tgm.redis.service.impl;

import com.juma.auth.user.domain.LoginUser;
import com.juma.monitor.support.domain.DeviceDetectedTemperature;
import com.juma.monitor.support.service.MonitorSupportService;
import com.juma.tgm.common.Constants;
import com.juma.tgm.redis.service.TemperatureAlertService;
import com.juma.tgm.tools.service.MessagePushService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.service.WaybillParamService;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.vms.truck.domain.Truck;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class TemperatureAlertServiceImpl implements TemperatureAlertService, InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(TemperatureAlertServiceImpl.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private WaybillService waybillService;

    @Resource
    private MessagePushService messagePushService;

    @Resource
    private MonitorSupportService monitorSupportService;

    @Resource
    private WaybillParamService waybillParamService;

    @Resource
    private VmsCommonService vmsCommonService;

    // 当前车辆关联的运单
    private static final String TMS_TEMP_ALERT_PLATENUMBER_TO_WAYBILL_HASH = "tms_temp_alert_plate_number_to_waybill_hash";
    // 车辆列表
    private static final String TMS_TEMP_ALERT_PLATENUMBER_SET = "tms_temp_alert_plate_number_set";
    // 车辆业务温度要求
    private static final String TMS_TEMP_ALERT_BIZ_MIN_MAX_HASH = "tms_temp_alert_biz_min_max_hash";
    // 车辆历史温度最值
    private static final String TMS_TEMP_ALERT_HIS_MIN_MAX_HASH = "tms_temp_alert_his_min_max_hash";
    // 车辆超过阈值持续次数
    private static final String TMS_TEMP_ALERT_PLATENUMBER_TIMES_HASH = "tms_temp_alert_plate_number_times_hash";
    // 车辆进入监控时间
    private static final String TMS_TEMP_ALERT_PLATENUMBER_ENTRY_TIME_HASH = "tms_temp_alert_plate_number_entry_time_hash";
    // 超过上限次数
    private static final Long LIMIT_TIMES = 6l;

    // 车辆列表
    BoundSetOperations<String, String> plateNumberSet;
    // 车辆业务上最低、最高温度
    BoundHashOperations<String, String, String> bizHash;
    // 车辆运行过程中最低、最高温度
    BoundHashOperations<String, String, String> hisHash;
    // 车辆运行过程中最低、最高温度
    BoundHashOperations<String, String, Long> timesHash;
    // 车辆进入监控时间
    BoundHashOperations<String, String, String> entryTimeHash;
    // 当前车辆关联的运单
    BoundHashOperations<String, String, String> plateNumberToWaybillHash;

    @Override
    public void afterPropertiesSet() throws Exception {
        plateNumberSet = stringRedisTemplate.boundSetOps(TMS_TEMP_ALERT_PLATENUMBER_SET);
        bizHash = stringRedisTemplate.boundHashOps(TMS_TEMP_ALERT_BIZ_MIN_MAX_HASH);
        hisHash = stringRedisTemplate.boundHashOps(TMS_TEMP_ALERT_HIS_MIN_MAX_HASH);
        timesHash = stringRedisTemplate.boundHashOps(TMS_TEMP_ALERT_PLATENUMBER_TIMES_HASH);
        entryTimeHash = stringRedisTemplate.boundHashOps(TMS_TEMP_ALERT_PLATENUMBER_ENTRY_TIME_HASH);
        plateNumberToWaybillHash = stringRedisTemplate.boundHashOps(TMS_TEMP_ALERT_PLATENUMBER_TO_WAYBILL_HASH);
    }

    @Override
    public void startUp(Integer waybillId) {
        log.info("[TemperatureAlert] {waybillId} Temperature Alert is startup..", waybillId);
        Waybill waybill = waybillService.getWaybill(waybillId);
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
        if (waybillParam == null) {
            log.info("[TemperatureAlert] waybillParam is invalid.");
            return;
        }
        Float minTemp = waybillParam.getRequiredMinTemperature();
        Float maxTemp = waybillParam.getRequiredMaxTemperature();
        if (waybill == null || StringUtils.isBlank(waybill.getPlateNumber()) || minTemp == null || maxTemp == null) {
            log.info("[TemperatureAlert] waybillId or plateNumber or minTemp or maxTemp is invalid.");
            return;
        }
        String plateNumber = waybill.getPlateNumber();
        if (plateNumberToWaybillHash.hasKey(waybillId + "," + plateNumber)) {
            log.info("[TemperatureAlert] key {} is happen.", waybillId + "," + plateNumber);
            return;
        }
        plateNumberSet.add(waybillId + "," + plateNumber);
        entryTimeHash.put(waybillId + "," + plateNumber, new Date().getTime() + "");
        bizHash.put(waybillId + "," + plateNumber, minTemp.floatValue() + "," + maxTemp.floatValue());
        hisHash.put(waybillId + "," + plateNumber, minTemp.floatValue() + "," + maxTemp.floatValue());
        timesHash.increment(waybillId + "," + plateNumber, 1);
        plateNumberToWaybillHash.put(waybillId + "," + plateNumber, waybillId + "");
    }

    @Override
    public void close(String key) {
        if (!plateNumberSet.isMember(key)) return;
        log.info("[TemperatureAlert] clear cache for {}.", key);
        plateNumberSet.remove(key);
        plateNumberToWaybillHash.delete(key);
        entryTimeHash.delete(key);
        bizHash.delete(key);
        hisHash.delete(key);
        timesHash.delete(key);
    }

    private Float[] minMaxTemp(BoundHashOperations<String, String, String> hash, String plateNumber) {
        if (!hash.hasKey(plateNumber)) return new Float[] { 0.0f, 0.0f };
        String minMaxTemp = hash.get(plateNumber);
        if (StringUtils.isBlank(minMaxTemp)) return new Float[] { 0.0f, 0.0f };
        String[] minMaxTemps = minMaxTemp.split(",");
        if (minMaxTemps.length < 2) return new Float[] { 0.0f, 0.0f };
        float min = Float.valueOf(minMaxTemps[0]);
        float max = Float.valueOf(minMaxTemps[1]);
        return new Float[] { min, max };
    }

    //车辆实时温度
    @Override
    public Float[] getMinMaxTemperatureByPlateNumber(String plateNumber, Integer tenantId,String tenantCode ) {
        if(StringUtils.isBlank(plateNumber)) return new Float[] { 0.0f, 0.0f };
        
        LoginUser loginUser = new LoginUser();
        loginUser.setTenantId(tenantId);
        loginUser.setTenantCode(tenantCode);
        Truck truck = vmsCommonService.loadTruckByTenantAndPlateNumber(plateNumber, loginUser);

        if (truck == null || null == truck.getVehicleId()) {
            log.info("[TemperatureAlert] {} not found at ams.", plateNumber);
            return new Float[] { 0.0f, 0.0f };
        }
        List<Float> temperatures = new ArrayList<Float>();
        List<DeviceDetectedTemperature> deviceTemperatures = monitorSupportService
                .getLatestTemperatureOfVehicle(truck.getVehicleId());
        for (DeviceDetectedTemperature deviceDetectedTemperature : deviceTemperatures) {
            Float[] f = deviceDetectedTemperature.getTemperatures();
            for (int i = 0, len = f.length; i < len; i++) {
                temperatures.add(f[i]);
            }
        }

        Collections.sort(temperatures);
        log.info("[TemperatureAlert] {} temperature from monitor is {}.", StringUtils.join(temperatures, ","));
        if (temperatures.size() != 2) {
            return new Float[] { 0.0f, 0.0f };
        }

        return new Float[] { temperatures.get(0), temperatures.get(temperatures.size() - 1) };
    }
    
    private Float[] searchMinMaxFromMonitor(String key, Integer tenantId,String tenantCode ) {
        String plateNumber = key.split(",")[1];
        return getMinMaxTemperatureByPlateNumber(plateNumber, tenantId,tenantCode);
    }

    @Override
    public void scan() {

        Set<String> plateNumbers = plateNumberSet.members();
        
        for (String key : plateNumbers) {

            if (!plateNumberToWaybillHash.hasKey(key)) continue;
            if (!entryTimeHash.hasKey(key)) continue;

            Integer waybillId = Integer.valueOf(plateNumberToWaybillHash.get(key));
            Waybill waybill = waybillService.getWaybill(waybillId);
            // 已配送完成的运单
            if (waybill == null || waybill.getStatus() == null
                    || waybill.getStatus() >= Waybill.Status.DELIVERIED.getCode()) {
                close(key);
                log.info("[TemperatureAlert] clear cache waybill {}.", key);
                continue;
            }

            // 时间超过5天的就删除掉，怕司机没有点结束，一直在缓存里
            long entryTime = Long.parseLong(entryTimeHash.get(key));
            if (entryTime + 5 * 24 * 60 * 60 * 1000 < new Date().getTime()) {
                close(key);
                log.info("[TemperatureAlert] {} entry time is {}.", key, entryTime);
                continue;
            }

            Float[] currTemp = searchMinMaxFromMonitor(key, waybill.getTenantId(),waybill.getTenantCode() );// 调用第三方获取
            log.info("[TemperatureAlert] {} from ams min max is {}.", key, StringUtils.join(currTemp, ","));
            float currMin = currTemp[0];
            float currMax = currTemp[1];
            if (currMin == 0.0 || currMax == 0.0) {
                continue;
            }
            Float[] bizMinMax = minMaxTemp(bizHash, key);
            Float[] hisMinMax = minMaxTemp(hisHash, key);
            log.info("[TemperatureAlert] {} biz min max is {}.", key, StringUtils.join(bizMinMax, ","));
            log.info("[TemperatureAlert]  {} history min max is {}.", key, StringUtils.join(hisMinMax, ","));
            float min = bizMinMax[0];
            float max = bizMinMax[1];
            float hmin = hisMinMax[0];
            float hmax = hisMinMax[1];
            //记录值极
            if (currMin < min) {
                notify(timesHash, key);
                if (currMin < hmin) {
                    overrideMin(key, currMin, hmax);
                }
            } else if (currMax > max) {
                notify(timesHash, key);
                if (currMax > hmax) {
                    overrideMax(key, hmin, currMax);
                }
            } else {
                // reset 次数重置
                log.info("{} times reset.", key);
                timesHash.delete(key);
                timesHash.increment(key, 1);
            }
        }
    }

    //更新最小值
    private void overrideMin(String key, Float min, Float max) {
        log.info("[TemperatureAlert] overrideMin {},{},{}", new Object[] { key, min + "", max + "" });
        hisHash.put(key, min + "," + max);
        // 更新waybill_param 表
        if (!plateNumberToWaybillHash.hasKey(key)) return;
        Integer waybillId = Integer.valueOf(plateNumberToWaybillHash.get(key));
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
        if (waybillParam != null) {
            waybillParam.setHistoryMinTemperature(min);
            waybillParamService.update(waybillParam, Constants.SYS_LOGIN_USER);
        }
    }
    
    //更新最大值
    private void overrideMax(String key, Float min, Float max) {
        log.info("[TemperatureAlert] overrideMax {},{},{}", new Object[] { key, min + "", max + "" });
        hisHash.put(key, min + "," + max);
        // 更新waybill_param 表
        if (!plateNumberToWaybillHash.hasKey(key)) return;
        Integer waybillId = Integer.valueOf(plateNumberToWaybillHash.get(key));
        WaybillParam waybillParam = waybillParamService.findByWaybillId(waybillId);
        if (waybillParam != null) {
            waybillParam.setHistoryMaxTemperature(max);
            waybillParamService.update(waybillParam, Constants.SYS_LOGIN_USER);
        }
    }

    private void notify(BoundHashOperations<String, String, Long> timesHash, String key) {
        if (!plateNumberToWaybillHash.hasKey(key) || !timesHash.hasKey(key)) return;
        Long times = timesHash.increment(key, 1);
        log.info("[TemperatureAlert] {} over limit times {}.", key, times);
        // 大于 超过次数后要报警
        if (times.compareTo(LIMIT_TIMES) == 1) {
            // 通知
            Integer waybillId = Integer.valueOf(plateNumberToWaybillHash.get(key));
            Waybill waybill = waybillService.getWaybill(waybillId);
            if (waybill == null) {
                log.info("[TemperatureAlert] {} link waybill {} is not found.", key, waybillId);
                return;
            }
            if (waybill.getStatus() == Waybill.Status.DELIVERIED.getCode()) {
                log.info("[TemperatureAlert] {} link waybill {} is deliveried.", key, waybillId);
                return;
            }

            // reset 次数重置 重新计
            log.info("{} times reset .", key);
            timesHash.delete(key);
            timesHash.increment(key, 1);

            // 通知司机 、经济人
            LoginUser loginUser = new LoginUser();
            loginUser.setUserId(1);
            loginUser.setTenantId(waybill.getTenantId());
            messagePushService.temperatureAlert(waybillId, loginUser);
        }
    }

    @Override
    public String viewRedisToString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("plateNumberSet:");
        buffer.append(StringUtils.join(plateNumberSet.members(), ";"));

        buffer.append("\r\n");
        buffer.append("bizHash:");
        for (String key : bizHash.keys()) {
            buffer.append(key);
            buffer.append("->");
            buffer.append(bizHash.get(key));
            buffer.append(";");
        }
        buffer.append("\r\n");
        buffer.append("hisHash:");
        for (String key : hisHash.keys()) {
            buffer.append(key);
            buffer.append("->");
            buffer.append(hisHash.get(key));
            buffer.append(";");
        }
        buffer.append("\r\n");
        buffer.append("timesHash:");
        for (String key : timesHash.keys()) {
            buffer.append(key);
            buffer.append("->");
            buffer.append(timesHash.get(key));
            buffer.append(";");
        }
        buffer.append("\r\n");
        buffer.append("entryTimeHash:");
        for (String key : entryTimeHash.keys()) {
            buffer.append(key);
            buffer.append("->");
            buffer.append(entryTimeHash.get(key));
            buffer.append(";");
        }
        buffer.append("\r\n");
        buffer.append("plateNumberToWaybillHash:");
        for (String key : plateNumberToWaybillHash.keys()) {
            buffer.append(key);
            buffer.append("->");
            buffer.append(plateNumberToWaybillHash.get(key));
            buffer.append(";");
        }

        return buffer.toString();
    }

    public static void main(String[] args) {
        List<Float> temperatures = new ArrayList<Float>();
        temperatures.add(1.2f);
        temperatures.add(2.3f);

        temperatures.add(1.3f);
        Collections.sort(temperatures);
        System.out.println(temperatures);
        float f = 0;
        System.out.println(f == 0);
        System.out.println(f == 0.0);
    }

}
