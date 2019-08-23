package com.juma.tgm.mq.rabbit.receive;

import com.alibaba.fastjson.JSON;
import com.giants.cache.redis.RedisClient;
import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.Constants;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.landing.waybill.service.DispatchingTruckService;
import com.juma.tgm.scatteredWaybill.service.ScatteredMsgService;
import com.juma.tgm.tools.service.AmsCommonService;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.ai.Feature;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateApplication;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateType;
import com.juma.tgm.waybill.service.BuildVehicleTypeAndVendorService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillOperateTrackService;
import java.nio.charset.Charset;
import java.util.Date;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @ClassName: MessageUpdateDeliveryOrderListener
 * @Description: 配送顺序更新
 * @author: Administrator
 * @date: 2017年11月17日 上午9:57:00
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class MessageUpdateDeliveryOrderListener implements MessageListener {

    private final Logger logger = LoggerFactory.getLogger(MessageUpdateDeliveryOrderListener.class);

    @Resource
    private WaybillCommonService waybillCommonService;

    @Resource
    private DispatchingTruckService dispatchingTruckService;

    @Resource
    private DriverService driverService;

    @Resource
    private TruckService truckService;

    @Resource
    private AmsCommonService amsCommonService;

    @Resource
    private RedisClient redisClient;

    @Resource
    private ScatteredMsgService scatteredMsgService;

    @Resource
    private WaybillOperateTrackService waybillOperateTrackService;

    @Resource
    private BuildVehicleTypeAndVendorService buildVehicleTypeAndVendorService;

    @Override
    public void onMessage(Message message) {

        String jsonStr = new String(message.getBody(), Charset.forName("utf-8"));
        logger.info("feature from data center:{}.", jsonStr);
        Feature feature = JSON.parseObject(jsonStr, Feature.class);
        if (feature == null) {
            return;
        }
        // 运单配送顺序
        dispatchingTruckService.updateAddressSequence(feature);
        if (feature.getWaybillId() != null && feature.getOperate() == Feature.Operate.Request_Match.getCode()) {
            Waybill waybill = waybillCommonService.getWaybillById(feature.getWaybillId());
            if (waybill == null) {
                return;
            }
            LoginUser loginUser = new LoginUser();
            loginUser.setTenantId(waybill.getTenantId());
            loginUser.setTenantCode(waybill.getTenantCode());
            if (Waybill.ReceiveWay.AUTO_ASSIGN.getCode() == waybill.getReceiveWay()
                && Waybill.StatusView.WATING_RECEIVE.getCode() == waybill.getStatusView()) {
                if (feature.getDriverId() != null && feature.getTruckId() != null) {
                    Driver driver = driverService.getDriver(feature.getDriverId());
                    Truck truck = truckService.getTruck(feature.getTruckId());
                    // 更新匹配结果
                    Waybill w = match(feature, loginUser, driver, truck, waybill);
                    if (null == w) {
                        // 派车失败
                        return;
                    }

                    // 黑名单
                    String key = Constants.DATA_CENTER_WAYBILL + "_" + feature.getWaybillId();
                    StringBuilder buffer = new StringBuilder(",0,");
                    if (redisClient.get(key) != null) {
                        buffer = (StringBuilder) redisClient.get(key);
                        logger.info("[1] feature black list is {}.", buffer.toString());
                    }
                    buffer.append(w.getTruckId() + ",");
                    redisClient.set(key, buffer);

                    // 推送给司机消息
                    scatteredMsgService.pushAssignedWaybillToDriverAppMsg(
                        waybillCommonService.getWaybillById(feature.getWaybillId()), loginUser);

                    // 操作轨迹
                    waybillOperateTrackService.insert(waybill.getWaybillId(),
                            OperateType.AI_MATCH_SUCCESS,
                            OperateApplication.AI_SYS, null, loginUser);
                } else {
                    this.noDriverAnswer(feature, waybill, loginUser);
                }
            } else {
                // 先通知匹配 然后后台取消 然后又收到匹配结果 没派车前被取消 需要通知数据平台
                if (feature.getDriverId() != null && feature.getTruckId() != null) {
                    logger.info("已经被人工派车了，通知数据平台取消匹配结果:{},{}.", feature.getWaybillId(), feature.getDriverId());
                    dispatchingTruckService.buildFeature(waybill.getWaybillId(), Feature.Operate.Dispatcher_Cancel,
                        null);
                }
            }
        }
    }

    // 没有匹配的司机或超时
    private void noDriverAnswer(Feature feature, Waybill waybill, LoginUser loginUser) {
        Waybill w = new Waybill();
        w.setWaybillId(feature.getWaybillId());
        w.setStatus(Waybill.Status.NO_DRIVER_ANSWER.getCode());
        waybillCommonService.update(w, null);

        // 通知运营
        scatteredMsgService.pushMsgToAdmin(waybill, loginUser);
        // 操作轨迹
        waybillOperateTrackService.insert(w.getWaybillId(), OperateType.AI_MATCH_EMPTY,
                OperateApplication.AI_SYS, null, loginUser);
    }

    private Waybill match(Feature feature, LoginUser loginUser, Driver driver, Truck truck, Waybill waybill) {
        com.juma.server.vm.domain.Driver amsDriver = amsCommonService.findDriver(driver.getAmsDriverId(), loginUser);
        if (null == amsDriver || null == amsDriver.getType()) {
            // 通知数据平台
            dispatchingTruckService.buildFeature(waybill.getWaybillId(), Feature.Operate.Driver_No_Response, null);
            return null;
        }

        Waybill w = new Waybill();
        w.setDriverType(amsDriver.getType().intValue());
        w.setWaybillId(feature.getWaybillId());
        w.setTruckId(feature.getTruckId());
        w.setDriverId(feature.getDriverId());
        w.setDriverName(driver.getNickname());
        w.setPlateNumber(truck.getPlateNumber());
        w.setStatus(Waybill.Status.WAITINT_DRIVER_ANSWER.getCode());
        w.setReceivingTime(new Date());
        w.setStatusView(Waybill.StatusView.TEMP.getCode());
        w.setReceiveWay(Waybill.ReceiveWay.AUTO_ASSIGN.getCode());

        if (null == truck || null == truck.getVehicleId()) {
            // 通知数据平台
            dispatchingTruckService.buildFeature(waybill.getWaybillId(), Feature.Operate.Driver_No_Response, null);
            return null;
        }

        try {
            w = buildVehicleTypeAndVendorService.checkAndBuildVehicleTypeAndVendor(w, truck.getVehicleId(),
                w.getDriverType(), null, loginUser);
        } catch (BusinessException e) {
            logger.info("希地自动派车失败，校验承运商未通过：{}, 组装的运单信息：{}", e.getMessage(), JSON.toJSONString(w));
            // 通知数据平台
            dispatchingTruckService.buildFeature(waybill.getWaybillId(), Feature.Operate.Driver_No_Response, null);
            return null;
        }

        waybillCommonService.update(w, loginUser);
        return w;
    }
}
