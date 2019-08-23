package com.juma.tgm.waybill.service.impl;

import com.alibaba.fastjson.JSON;
import com.juma.auth.user.domain.LoginUser;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.service.ConfParamService;
import com.juma.monitor.service.FenceService;
import com.juma.monitor.truck.domain.Fence;
import com.juma.monitor.truck.domain.vo.SysFenceVo;
import com.juma.server.vm.domain.DeviceBind;
import com.juma.server.vm.service.vehicle.AmsServiceV2;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.common.DateUtil.TimeUnitEnum;
import com.juma.tgm.gaode.domain.AmapRegeoResponse;
import com.juma.tgm.redis.service.TemperatureAlertService;
import com.juma.tgm.tools.service.MessagePushService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.waybill.domain.DistanceAndPriceData;
import com.juma.tgm.waybill.domain.FenceEvent;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillBindFence;
import com.juma.tgm.waybill.domain.WaybillBindFence.Sign;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillOperateTrack;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateApplication;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateType;
import com.juma.tgm.waybill.service.GaoDeMapService;
import com.juma.tgm.waybill.service.MsgWithBusinessService;
import com.juma.tgm.waybill.service.WaybillAutoFenceServicve;
import com.juma.tgm.waybill.service.WaybillBindFenceService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillDeliveryAddressService;
import com.juma.tgm.waybill.service.WaybillOperateTrackService;
import com.juma.tgm.waybill.service.WaybillReceiveAddressService;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.tgm.waybillLbsSource.domain.WaybillLbsSource;
import com.juma.tgm.waybillLbsSource.service.WaybillLbsSourceService;
import com.juma.vms.driver.domain.Driver;
import com.juma.vms.truck.domain.Truck;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WaybillAutoFenceServicveImpl implements WaybillAutoFenceServicve {

    private final Logger log = LoggerFactory.getLogger(WaybillAutoFenceServicveImpl.class);
    @Resource
    private WaybillService waybillService;
    @Resource
    private FenceService fenceService;
    @Resource
    private WaybillDeliveryAddressService waybillDeliveryAddressService;
    @Resource
    private WaybillReceiveAddressService waybillReceiveAddressService;
    @Resource
    private WaybillBindFenceService waybillBindFenceService;
    @Resource
    private WaybillOperateTrackService waybillOperateTrackService;
    @Resource
    private MsgWithBusinessService msgWithBusinessService;
    @Resource
    private GaoDeMapService gaoDeMapService;
    @Resource
    private WaybillLbsSourceService waybillLbsSourceService;
    @Resource
    private MessagePushService messagePushService;
    @Resource
    private ConfParamService confParamService;
    @Resource
    private WaybillCommonService waybillCommonService;
    @Resource
    private TemperatureAlertService temperatureAlertService;
    @Resource
    private AmsServiceV2 amsServiceV2;
    @Resource
    private VmsCommonService vmsCommonService;

    @Override
    public void bindWaybillIdAndFenceId(Integer waybillId, WaybillBindFence.Sign sign, LoginUser loginUser) {
        if (null == waybillId || null == sign) {
            return;
        }

        try {
            // 获取运单完整信息
            Waybill waybill = waybillService.getWaybill(waybillId);
            log.info("WaybillAutoFenceServicveImpl->bindWaybillIdAndFenceId->waybillId：{}", waybillId);

            if (null == waybill) {
                return;
            }

            // 转运方的转运单不创建电子围栏
            if (NumberUtils.compare(Waybill.ReceiveWay.TRANSFORM_BILL.getCode(), waybill.getReceiveWay()) == 0) {
                return;
            }

            // 获取运单的车辆信息
            Truck truck = vmsCommonService.loadTruckByTruckId(waybill.getTruckId());
            if (null == truck || StringUtils.isBlank(truck.getPlateNumber())) {
                return;
            }

            // 判断车有没有设备号
            List<DeviceBind> devices = amsServiceV2.listByVehicleId(truck.getVehicleId());
            if (CollectionUtils.isEmpty(devices)) {
                return;
            }

            // 运单绑定电子围栏
            log.info("WaybillAutoFenceServicveImpl->bindWaybillIdAndFenceId->truck->info：{}", truck.toString());
            if (WaybillBindFence.Sign.DELIVERY_ADDRESS.equals(sign) && null != waybill.getStatus()
                    && (Waybill.Status.ASSIGNED.getCode() == waybill.getStatus()
                            || Waybill.Status.RECEIVED.getCode() == waybill.getStatus())) {
                buildDeliveryAddressFenceId(waybillId, buildBaseSysFenceInfo(waybill, truck.getPlateNumber()),
                        loginUser);
            }

            if (WaybillBindFence.Sign.RECEIVE_ADDRESS.equals(sign)) {
                buildReceiveAddressFenceId(waybillId, buildBaseSysFenceInfo(waybill, truck.getPlateNumber()),
                        loginUser);
            }
        } catch (Exception e) {
            log.warn("绑定电子围栏失败，运单ID：{}", waybillId, e);
        }
    }

    // 构建绑定电子围栏参数基础信息
    private SysFenceVo buildBaseSysFenceInfo(Waybill waybill, String plateNumber) {
        SysFenceVo sf = new SysFenceVo();
        // 车牌号
        List<String> listPlateNumber = new ArrayList<String>();
        listPlateNumber.add(plateNumber);
        sf.setDevices(listPlateNumber);
        sf.setLinkTxt(waybill.getWaybillNo());
        sf.setSystem(Constants.SYS_TGM_MANAGE__KEY);

        Map<String, Object> callbackMap = new HashMap<String, Object>();
        callbackMap.put("WaybillId", waybill.getWaybillId());
        sf.setCallback(JSON.toJSONString(callbackMap));

        // 电子围栏激活时间：配置时间->用车时间的前一小时
        if (null != waybill.getPlanDeliveryTime()) {
            List<ConfParamOption> list = confParamService.findParamOptions(Constants.WAYBILL_FENCE_START_TIME__KEY);
            if (CollectionUtils.isNotEmpty(list) && StringUtils.isNotBlank(list.get(0).getOptionValue())) {
                sf.setStartTime(DateUtil.addMinutes(waybill.getPlanDeliveryTime(),
                        -Integer.parseInt(list.get(0).getOptionValue())));
            } else {
                sf.setStartTime(DateUtil.addMinutes(waybill.getPlanDeliveryTime(), -60));
            }
        }
        return sf;
    }

    // 绑定电子围栏并获取电子围栏ID
    private Integer addFence(SysFenceVo sf, String coordinates) {
        log.info("WaybillAutoFenceServicveImpl->addFence->start->获取电子围栏fenceId请求参数坐标：{}", coordinates);
        // 坐标
        String[] split = coordinates.split(",");
        sf.setLongitude(Double.valueOf(split[0]));
        sf.setLatitude(Double.valueOf(split[1]));
        // 半径
        Double radius = Constants.WAYBILL_FENCE_RADIUS_DEFAULT;
        try {
            List<ConfParamOption> list = confParamService.findParamOptions(Constants.WAYBILL_FENCE_RADIUS_KEY);
            if (CollectionUtils.isNotEmpty(list) && StringUtils.isNotBlank(list.get(0).getOptionValue())
                    && StringUtils.isNumeric(list.get(0).getOptionValue())) {
                radius = Double.valueOf(list.get(0).getOptionValue());
            }
        } catch (Exception e) {
            log.warn("在用户中心字典表中获取绑定电子围栏半径异常", e);
        }
        sf.setRadius(radius);

        // 绑定接口
        Integer fenceId = fenceService.addFence(sf);
        log.info("WaybillAutoFenceServicveImpl->addFence->end->获取电子围栏结果fenceId：{}", fenceId);
        return fenceId;
    }

    // 取货地
    private void buildDeliveryAddressFenceId(Integer waybillId, SysFenceVo sf, LoginUser loginUser) {
        WaybillDeliveryAddress deliveryAddress = waybillDeliveryAddressService.findByWaybillId(waybillId);
        if (null == deliveryAddress) {
            return;
        }

        String coordinates = deliveryAddress.getCoordinates();
        if (StringUtils.isNotBlank(coordinates)) {
            log.info("buildDeliveryAddressFenceId->获取电子围栏fenceId请求参数目的地->waybillId：{}", waybillId);
            // 获取电子围栏ID
            Integer fenceId = addFence(sf, coordinates);
            // 电子围栏与运单绑定
            insertWaybillBindFence(fenceId, waybillId, deliveryAddress.getAddressId(),
                    WaybillBindFence.Sign.DELIVERY_ADDRESS, loginUser);
        }
    }

    // 目的地
    private void buildReceiveAddressFenceId(Integer waybillId, SysFenceVo sf, LoginUser loginUser) {
        Waybill waybill = waybillService.getWaybill(waybillId);

        if (null == waybill) {
            return;
        }

        // 取货地围栏事件：触发取货地围栏事件之后才能触发目的地的围栏事件
        List<WaybillBindFence> listDeliveryAddressFence = waybillBindFenceService.listByWaybillIdAndSign(waybillId,
                WaybillBindFence.Sign.DELIVERY_ADDRESS.getCode());
        // 没有建立取货地电子围栏
        if (listDeliveryAddressFence.isEmpty()) {
            return;
        }

        // 没有触发取货地电子围栏
        if (NumberUtils.compare(WaybillBindFence.Status.EFFECTIVE.getCode(),
                listDeliveryAddressFence.get(0).getStatus()) == 0) {
            return;
        }

        List<WaybillReceiveAddress> receiveAddress = waybillReceiveAddressService.findAllByWaybillId(waybillId);
        for (WaybillReceiveAddress address : receiveAddress) {
            String coordinates = address.getCoordinates();
            if (StringUtils.isNotBlank(coordinates)) {
                log.info("buildDeliveryAddressFenceId->获取电子围栏fenceId请求参数取货地->waybillId：{}", waybillId);
                // 获取电子围栏ID
                Integer fenceId = addFence(sf, coordinates);
                // 电子围栏与运单绑定
                insertWaybillBindFence(fenceId, waybillId, address.getAddressId(),
                        WaybillBindFence.Sign.RECEIVE_ADDRESS, loginUser);
            }
        }
    }

    // 电子围栏与运单绑定
    private void insertWaybillBindFence(Integer fenceId, Integer waybillId, Integer addressId, Sign sign,
            LoginUser loginUser) {
        if (null == fenceId) {
            return;
        }
        // 电子围栏与运单绑定
        WaybillBindFence waybillBindFence = new WaybillBindFence();
        waybillBindFence.setFenceId(fenceId);
        waybillBindFence.setWaybillId(waybillId);
        waybillBindFence.setAddressId(addressId);
        waybillBindFence.setSign(sign.getCode());
        waybillBindFence.setStatus(WaybillBindFence.Status.EFFECTIVE.getCode());
        waybillBindFenceService.insert(waybillBindFence, loginUser);
    }

    @Override
    public void touchEntryPushMessage(FenceEvent fenceEvent, LoginUser loginUser) {
        log.info("touchEntryPushMessage->消息电子围栏fenceEvent:{}", fenceEvent.toString());
        // 根据电子围栏ID获取绑定信息，若没有绑定信息，则停止操作
        WaybillBindFence bindFence = waybillBindFenceService.findByFenceIdForUpdate(fenceEvent.getFenceId());
        if (null == bindFence
                || NumberUtils.compare(WaybillBindFence.Status.EFFECTIVE.getCode(), bindFence.getStatus()) != 0) {
            log.info("touchEntryPushMessage->bindFence为空或记录已失效:{}", JSON.toJSONString(bindFence));
            return;
        }

        boolean pushMessage = false;
        // 取货地
        if (WaybillBindFence.Sign.DELIVERY_ADDRESS.getCode() == bindFence.getSign()) {
            Waybill waybill = waybillService.getWaybillAndCheckExist(bindFence.getWaybillId());
            if (null == waybill.getArriveDepotTime()) {
                pushMessage = true;
            }
            entry(bindFence, fenceEvent, OperateType.ARRIVE_DEPOT, pushMessage, loginUser);

            // 电子围栏：司机迟到时记录
            insertLbsSource(fenceEvent, bindFence.getWaybillId());

            // 绑定目的地电子围栏
            bindWaybillIdAndFenceId(bindFence.getWaybillId(), WaybillBindFence.Sign.RECEIVE_ADDRESS,
                    loginUser);
            return;
        }

        // 配送地
        entry(bindFence, fenceEvent, WaybillOperateTrackEnum.OperateType.ARRIVE_DESTINATION, pushMessage, loginUser);
    }

    // 电子围栏：司机迟到时记录
    private void insertLbsSource(FenceEvent fenceEvent, Integer waybillId) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (null != waybill && fenceEvent.getTimestamp().after(waybill.getPlanDeliveryTime())) {
            WaybillLbsSource waybillLbsSource = new WaybillLbsSource();
            waybillLbsSource.setWaybillId(waybillId);
            waybillLbsSource.setSign(WaybillLbsSource.Sign.DRIVER_LATE.getCode());
            waybillLbsSource.setCoordinate(fenceEvent.getCoordinates());
            waybillLbsSource.setDeviceNo(fenceEvent.getDeviceId());
            waybillLbsSource.setDeviceType(fenceEvent.getDeviceType());
            BigDecimal calDate = DateUtil.calDate(waybill.getPlanDeliveryTime(), fenceEvent.getTimestamp(), TimeUnitEnum.SECOND);
            if (null != calDate) {
                waybillLbsSource.setTimeConsuming(calDate.intValue());
            }
            waybillLbsSourceService.insert(waybillLbsSource);
        }
    }

    @Override
    public void touchExitPushMessage(FenceEvent fenceEvent, LoginUser loginUser) {
        log.info("touchPushMessage->消息电子围栏fenceEvent:{}", fenceEvent.toString());
        // 根据电子围栏ID获取绑定信息，若没有绑定信息，则停止操作
        WaybillBindFence bindFence = waybillBindFenceService.findByFenceIdForUpdate(fenceEvent.getFenceId());
        if (null == bindFence
                || NumberUtils.compare(WaybillBindFence.Status.INVALID.getCode(), bindFence.getStatus()) == 0) {
            log.info("touchEntryPushMessage->bindFence为空或记录已失效:{}", JSON.toJSONString(bindFence));
            return;
        }

        boolean pushMessage = false;
        if (WaybillBindFence.Sign.DELIVERY_ADDRESS.getCode() == bindFence.getSign()) {
            Waybill waybill = waybillService.getWaybill(bindFence.getWaybillId());
            Date oldDeliveryTime = waybill.getDeliveryTime();
            if (waybill.getStatus() != null && (Waybill.Status.ASSIGNED.getCode() == waybill.getStatus()
                    || Waybill.Status.RECEIVED.getCode() == waybill.getStatus())) {

                //运单取货地标记为已到达
                updateWaybillDeliveryAddressToArrived(waybill, bindFence.getAddressId(), loginUser);;

                // 没有手动点离仓时才发送通知
                pushMessage = pushMessage(waybill.getDriverId(), waybill.getTenantId(), oldDeliveryTime);
            } else if (waybill.getStatus() != null && Waybill.Status.DELIVERYING.getCode() == waybill.getStatus() && waybill.getTenantId() != 3) {
                // 配送中的只改变时间
                waybill.setDeliveryTime(fenceEvent.getTimestamp());
                waybillCommonService.update(waybill, loginUser);
            }

            //温度监控
            temperatureAlertService.startUp(waybill.getWaybillId());
            // 绑定状态变化与操作轨迹
            exit(bindFence, fenceEvent, OperateType.LEAVE_DEPOT, pushMessage, loginUser);
            return;
        }
        
        // 配送地
        exit(bindFence, fenceEvent, WaybillOperateTrackEnum.OperateType.LEAVE_DESTINATION, pushMessage, loginUser);
    }

    // 运单取货地标记为已到达
    private void updateWaybillDeliveryAddressToArrived(Waybill waybill, int addressId, LoginUser loginUser) {
        if (waybill.getTenantId() == 3) {
            // 希地不执行自动离仓
            return;
        }
        WaybillDeliveryAddress waybillDeliveryAddress = new WaybillDeliveryAddress();
        waybillDeliveryAddress.setAddressId(addressId);
        waybillDeliveryAddress.setIsArrived(WaybillDeliveryAddress.Arrive.YES.getCode());
        waybillDeliveryAddressService.update(waybillDeliveryAddress, loginUser);
    }

    // 运单配送地标记为已到达
    private void updateWaybillReceiveAddressToArrived(int addressId, LoginUser loginUser) {
        WaybillReceiveAddress waybillReceiveAddress = new WaybillReceiveAddress();
        waybillReceiveAddress.setAddressId(addressId);
        waybillReceiveAddress.setIsArrived(WaybillReceiveAddress.Arrive.YES.getCode());
        waybillReceiveAddressService.update(waybillReceiveAddress, loginUser);
    }

    // 没有手动点离仓时才发送通知
    private boolean pushMessage(int driverId, int tenantId, Date oldDeliveryTime) {
        Driver driver = vmsCommonService.loadDriverByDriverId(driverId);
        if (null != driver && oldDeliveryTime == null) {
            // 刷新banner
            LoginUser loginUser = Constants.SYS_LOGIN_USER;
            loginUser.setTenantId(tenantId);
            messagePushService.pushAppMessage("WATING_DELIVERY_TO_DELIVERYING", null, loginUser,driver.getUserId() + "");
            return true;
        }
        return false;
    }

    // 进入电子围栏时的操作
    private void entry(WaybillBindFence bindFence, FenceEvent fenceEvent, OperateType operateType,
            boolean pushMessage, LoginUser loginUser) {

        // 记录车辆已进入电子围栏
        bindFence.setStatus(WaybillBindFence.Status.TRUCK_ENTRY_INVALID.getCode());
        waybillBindFenceService.update(bindFence, loginUser);

        // 发送消息
        if (pushMessage) {
            msgWithBusinessService.pushFenceEntryMsg(bindFence.getWaybillId());
        }

        // 操作轨迹
        addBydistribution(bindFence, fenceEvent, operateType);
    }

    // 离开电子围栏时的操作
    private void exit(WaybillBindFence bindFence, FenceEvent fenceEvent, OperateType operateType, boolean pushMessage, LoginUser loginUser) {

        try {
            // 清除在途监控电子围栏fenceId
            fenceService.deleteFenceStatus(bindFence.getFenceId());
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }

        // 将运单与电子围栏的绑定信息置为无效
        bindFence.setStatus(WaybillBindFence.Status.INVALID.getCode());
        waybillBindFenceService.update(bindFence, loginUser);

        // 运单配送地标记为已到达
        updateWaybillReceiveAddressToArrived(bindFence.getAddressId(), Constants.SYS_LOGIN_USER);

        // 发送消息
        if (pushMessage) {
            msgWithBusinessService.pushFenceExitMsg(bindFence.getWaybillId());
        }

        // 操作轨迹
        addBydistribution(bindFence, fenceEvent, operateType);
    }

    @Override
    public void removeAllFenceId(Integer waybillId, LoginUser loginUser) {
        if (null == waybillId) {
            return;
        }
        // 根据运单ID获取运单运电子围栏绑定信息
        List<WaybillBindFence> listWaybillBindFence = waybillBindFenceService.listByWaybillIdForUpdate(waybillId);
        for (WaybillBindFence fence : listWaybillBindFence) {
            try {
                // 清除在途监控电子围栏fenceId
                fenceService.deleteFenceStatus(fence.getFenceId());
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
            }
        }
        // 将运单地址的绑定信息置为无效
        waybillBindFenceService.updateFenceToInvalid(waybillId, loginUser);
    }

    // 分配：添加操作轨迹的方法
    private void addBydistribution(WaybillBindFence fence, FenceEvent fenceEvent, OperateType operateType) {
        if (NumberUtils.compare(WaybillBindFence.Sign.DELIVERY_ADDRESS.getCode(), fence.getSign()) == 0) {
            addDeliveryOperateTrack(fence.getAddressId(), fenceEvent, operateType);
        } else {
            addReceiveOperateTrack(fence.getAddressId(), fenceEvent, operateType);
        }
    }

    // 添加：取货地电子围栏操作轨迹
    private void addDeliveryOperateTrack(Integer addressId, FenceEvent fenceEvent, OperateType operateType) {
        WaybillDeliveryAddress address = waybillDeliveryAddressService.findByAddressId(addressId);
        if (null == address) {
            return;
        }

        add(address.getWaybillId(), fenceEvent, address.getCoordinates(), address.getAddressDetail(), operateType);
    }

    // 添加：目的地电子围栏操作轨迹
    private void addReceiveOperateTrack(Integer addressId, FenceEvent fenceEvent, OperateType operateType) {
        WaybillReceiveAddress address = waybillReceiveAddressService.getWaybillReceiveAddress(addressId);
        if (null == address) {
            return;
        }

        add(address.getWaybillId(), fenceEvent, address.getCoordinates(), address.getAddressDetail(), operateType);
    }

    // 添加：电子围栏操作轨迹
    private void add(Integer waybillId, FenceEvent fenceEvent, String actualCoordinates, String actualAddress,
            OperateType operateType) {
        WaybillOperateTrack track = new WaybillOperateTrack();
        track.setOperateApplication(OperateApplication.FRNCE.getCode());
        track.setOperateType(operateType.getCode());
        track.setWaybillId(waybillId);
        track.setOperateAddressCoordinates(fenceEvent.getCoordinates());
        // 根据坐标获取中文地址
        AmapRegeoResponse regeocode = gaoDeMapService.regeocode(fenceEvent.getCoordinates());
        if (null != regeocode) {
            track.setOperateAddress(regeocode.getRegeocode().getFormattedAddress());
        }
        // 根据坐标获取两点之间的距离
        DistanceAndPriceData distanceAndPriceData = gaoDeMapService.getDistanceSimple(fenceEvent.getCoordinates(),
                actualCoordinates);
        if (null != distanceAndPriceData) {
            track.setDistance(distanceAndPriceData.getDistance());
        }
        track.setActualAddressCoordinates(actualCoordinates);
        track.setActualAddress(actualAddress);
        track.setDeviceNo(fenceEvent.getDeviceId());
        track.setDeviceType(fenceEvent.getDeviceType());
        track.setDeclareTime(fenceEvent.getTimestamp());
        waybillOperateTrackService.insert(track, Constants.SYS_LOGIN_USER);
    }

    @Override
    public void changeDeliveryStartTime(Integer waybillId, LoginUser loginUser) {
        Waybill waybill = waybillService.getWaybillAndCheckExist(waybillId);
        List<WaybillBindFence> list = waybillBindFenceService.listByWaybillIdAndSign(waybillId,
                WaybillBindFence.Sign.DELIVERY_ADDRESS.getCode());
        for (WaybillBindFence waybillBindFence : list) {
            Fence fence = new Fence();
            fence.setFenceId(waybillBindFence.getFenceId());
            // 电子围栏激活时间：配置时间->用车时间的前一小时
            if (null != waybill.getPlanDeliveryTime()) {
                Date startTime = DateUtil.addMinutes(waybill.getPlanDeliveryTime(), -60);
                List<ConfParamOption> listOption = confParamService
                        .findParamOptions(Constants.WAYBILL_FENCE_START_TIME__KEY);
                if (CollectionUtils.isNotEmpty(listOption)
                        && StringUtils.isNotBlank(listOption.get(0).getOptionValue())) {
                    startTime = DateUtil.addMinutes(waybill.getPlanDeliveryTime(),
                            -Integer.parseInt(listOption.get(0).getOptionValue()));
                }
                // 若用车时间减去冗余时间大于当前时间，则使用用车时间减去冗余时间为电子围栏开始时间，否则使用当前系统时间（即即刻生效）
                fence.setTriggerTime(startTime.after(new Date()) ? startTime : new Date());
                fenceService.updateFence(fence);
            }
        }
    }
}
