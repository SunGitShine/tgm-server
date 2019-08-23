package com.juma.tgm.landing.waybill;

import com.alibaba.fastjson.JSON;
import com.giants.cache.redis.RedisClient;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.conf.service.BusinessAreaService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.service.ConfParamService;
import com.juma.monitor.service.RealTimePositionService;
import com.juma.monitor.truck.domain.RealTimePosition;
import com.juma.server.vm.domain.bo.FlightBo;
import com.juma.server.vm.domain.dto.VehicleQueryConditionDTO;
import com.juma.server.vm.domain1.bo.VehicleBo;
import com.juma.server.vm.service.vehicle.AmsServiceV2;
import com.juma.tgm.common.Constants;
import com.juma.tgm.configure.service.ServiceConfService;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.landing.waybill.service.DispatchingTruckService;
import com.juma.tgm.mq.service.MqService;
import com.juma.tgm.scatteredWaybill.service.ScatteredMsgService;
import com.juma.tgm.tools.service.MessagePushService;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillBindFence;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.domain.WaybillReceiveAddressResponse;
import com.juma.tgm.waybill.domain.ai.Feature;
import com.juma.tgm.waybill.domain.ai.Feature.Operate;
import com.juma.tgm.waybill.domain.ai.Node;
import com.juma.tgm.waybill.domain.ai.TruckFeature;
import com.juma.tgm.waybill.domain.example.WaybillExample;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateApplication;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateType;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillAutoFenceServicve;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillDeliveryAddressService;
import com.juma.tgm.waybill.service.WaybillOperateTrackService;
import com.juma.tgm.waybill.service.WaybillReceiveAddressService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

/*
 * 对接数据平台
 */
@Service
public class DispatchingTruckServiceImpl implements DispatchingTruckService {

    private static final Logger logger = LoggerFactory.getLogger(DispatchingTruckServiceImpl.class);

    @Resource
    private AmsServiceV2 amsServiceV2;

    @Resource
    private DriverService driverService;

    @Resource
    private TruckService truckService;

    @Resource
    private TruckTypeService truckTypeService;

    @Resource
    private RealTimePositionService realTimePositionService;

    @Resource
    private TruckRequireService truckRequireService;

    @Resource
    private WaybillCommonService waybillCommonService;

    @Resource
    private WaybillReceiveAddressService waybillReceiveAddressService;

    @Resource
    private WaybillDeliveryAddressService waybillDeliveryAddressService;

    @Resource
    private MqService mqService;

    @Resource
    private TaskExecutor executor;

    @Resource
    private ConfParamService confParamService;

    @Resource
    private ServiceConfService serviceConfService;

    @Resource
    private ScatteredMsgService scatteredMsgService;

    @Resource
    private WaybillAutoFenceServicve waybillAutoFenceServicve;

    @Resource
    private MessagePushService messagePushService;

    @Resource
    private WaybillOperateTrackService waybillOperateTrackService;

    @Resource
    private RedisClient redisClient;

    @Resource
    private BusinessAreaService businessAreaService;

    @Override
    public void findTruck(final Integer waybillId, final LoginUser loginUser) {

        final Waybill waybill = waybillCommonService.getWaybillById(waybillId);
        final TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybillId, loginUser);
        if (waybill == null || truckRequire == null) return;
        final TruckType truckType = truckTypeService.getTruckType(truckRequire.getTruckTypeId());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                prepareFindTruck(waybill, truckRequire, truckType, loginUser);
            }
        });
    }

    private void prepareFindTruck(Waybill waybill, TruckRequire truckRequire, TruckType truckType,
            LoginUser loginUser) {
        logger.info("feature new waybill,id is {}", waybill.getWaybillId());
        Integer waybillId = waybill.getWaybillId();
        Feature feature = new Feature();
        feature.setOperate(Feature.Operate.Request_Match.getCode());
        feature.setWaybillId(waybillId);
        feature.setType(waybill.getBusinessBranch());
        feature.setDeliveryTime(waybill.getPlanDeliveryTime());

        // 组装条件
        PageCondition condition = new PageCondition();
        if (waybill.getEntryLicense() != null && waybill.getEntryLicense() == 1) {
            condition.getFilters().put("goCityLicenseType", waybill.getEntryLicense());
        }

        // 逻辑区域
        BusinessArea businessArea = businessAreaService.loadLogicBusinessArea(waybill.getAreaCode(), loginUser);
        if (businessArea != null) {
            condition.getFilters().put("areaCodeLike", businessArea.getAreaCode());
            logger.info("business area is :{}.", businessArea.getAreaCode());
        } else {
            logger.info("business area is not found :{}.", waybill.getAreaCode());
            condition.getFilters().put("areaCodeLike", waybill.getAreaCode());
        }

        if (truckType != null) {
            condition.getFilters().put("vehicleBoxType", truckType.getVehicleBoxType());// 厢型
            condition.getFilters().put("boxLevel", truckType.getTruckLengthId());// 厢长
        } else {
            condition.getFilters().put("vehicleBoxType", waybill.getVehicleBoxType());// 厢型 零担
        }
        condition.getFilters().put("startTime", waybill.getPlanDeliveryTime());
        condition.getFilters().put("endTime", waybill.getCmEstimateFinishTime());
        condition.setPageNo(1);
        condition.setPageSize(Integer.MAX_VALUE);
        Page<FlightBo> page = amsServiceV2.getAvailableFlightByPage(condition, loginUser);

        buildSourceNode(waybillId, feature, waybill, truckRequire);

        buildTargetNode(waybillId, feature, waybill, truckRequire);
        logger.info("feature ams find truck,condition is {}", condition.getFilters());
        logger.info("feature ams truck count {}", page.getResults());
        for (FlightBo flightBo : page.getResults()) {
            Driver driver = driverService.findDriverByAmsDriverId(flightBo.getDriverId());
            // 司机没有开启接单
            if (driver == null || driver.getWhetherAcceptAllocateOrder() == null
                    || driver.getWhetherAcceptAllocateOrder().equals(Driver.AcceptAllocateOrders.BUSY.getCode())) {
                logger.info("feature driver is null or acceptAllocateOrder is busy,ams driver is {}",
                        flightBo.getDriverId());
                continue;
            }
            Truck truck = truckService.findTruckByVehicleId(flightBo.getVehicleId());
            if (truck == null) {
                logger.info("feature truck is null,ams vehicle id is {}", flightBo.getVehicleId());
                continue;
            }
            VehicleBo vehicleBo = this.getVehicleBo(truck.getVehicleId(), loginUser);
            if (vehicleBo == null || null == vehicleBo.getVehicleExtend()) {
                logger.info("feature vehicle is null,ams id is {}", truck.getVehicleId());
                continue;
            }
            ;

            TruckFeature tf = new TruckFeature();

            BigDecimal load = vehicleBo.getVehicleExtend().getMaxLoadCapacity() == null ? BigDecimal.ZERO
                    : new BigDecimal(vehicleBo.getVehicleExtend().getMaxLoadCapacity() + "");
            BigDecimal l = load.divide(new BigDecimal(1000 + ""));
            tf.setLoad(l);// 默认
            BigDecimal volume = vehicleBo.getVehicleExtend().getLoadVolume() == null ? BigDecimal.ZERO
                    : new BigDecimal(vehicleBo.getVehicleExtend().getLoadVolume() + "");
            tf.setVolume(volume);// 默认

            TruckType _truckType = truckTypeService.findByBoxAndLength(truck.getVehicleBoxType(),
                    truck.getVehicleBoxLength(), loginUser.getTenantId());
            if (_truckType == null) {
                logger.info("feature _truckType is null,truck is {}", truck.getTruckId());
            } else {
                // 车辆能拉的货物 = 载重 - 冗余
                if (_truckType.getRedundancyLoad() != null) {
                    tf.setLoad(tf.getLoad().subtract(_truckType.getRedundancyLoad()));
                }
                if (_truckType.getRedundancyVolume() != null) {
                    tf.setVolume(tf.getVolume().subtract(_truckType.getRedundancyVolume()));
                }

                // 整车业务 重量 体积不匹配
                if (waybill.getBusinessBranch() != null
                        && waybill.getBusinessBranch() == Waybill.BusinessBranch.BRANCH_FULL.getCode()) {
                    // 车型不匹配
                    if (truckRequire != null && !truckRequire.getTruckTypeId().equals(_truckType.getTruckTypeId())) {
                        logger.info("feature truck type no match,truck is {},type is {}.", truck.getTruckId(),
                                _truckType.getTruckTypeId());
                        continue;
                    }
                    BigDecimal customer_v = new BigDecimal(truckRequire.getGoodsVolume());
                    BigDecimal customer_w = new BigDecimal(truckRequire.getGoodsWeight());
                    if (customer_v.compareTo(tf.getVolume()) == 1) {
                        logger.info("feature customer volume is greater than truck volume.{},{}.", customer_v,
                                tf.getVolume());
                        continue;
                    }
                    if (customer_w.compareTo(tf.getLoad()) == 1) {
                        logger.info("feature customer weight is greater than truck weight.{},{}.", customer_w,
                                tf.getLoad());
                        continue;
                    }
                }
            }

            // 同样的订单 车辆已经匹配过
            String key = Constants.DATA_CENTER_WAYBILL + "_" + feature.getWaybillId();
            StringBuilder buffer = (StringBuilder) redisClient.get(key);
            logger.info("[2] feature black list is {}.", buffer);
            if (buffer != null) {
                boolean b = buffer.toString().contains("," + truck.getTruckId() + ",");
                if (b) {
                    logger.info("[2] feature {} at black list .", truck.getTruckId());
                    continue;
                }
            }

            tf.setDriverId(driver.getDriverId());
            tf.setDriverName(driver.getNickname());

            tf.setParkLng(flightBo.getLongitude() == null ? null : flightBo.getLongitude().doubleValue());
            tf.setParkLat(flightBo.getLatitude() == null ? null : flightBo.getLatitude().doubleValue());

            RealTimePosition realTimePosition = realTimePositionService.queryLastPosByPlateNum(truck.getPlateNumber());
            if (realTimePosition != null) {
                tf.setCurrLng(realTimePosition.getLng());
                tf.setCurrLat(realTimePosition.getLat());
            }
            tf.setTruckId(truck.getTruckId());
            tf.setPlateNumber(truck.getPlateNumber());

            feature.addTruck(tf);
        }
        // 发送到数据平台
        mqService.sendToDataCenter(feature);
        logger.info("new waybill,feature is {}", JSON.toJSON(feature));
    }

    private void buildSourceNode(Integer waybillId, Feature feature, Waybill waybill, TruckRequire truckRequire) {
        List<WaybillDeliveryAddress> addr2 = waybillDeliveryAddressService.findAllByWaybillId(waybillId);
        for (WaybillDeliveryAddress addr : addr2) {
            Node n = new Node();
            n.setWaybillId(waybillId);
            n.setTime(waybill.getPlanDeliveryTime());// targer node不要这个字段
            n.setType(waybill.getBusinessBranch());
            n.setNodeId("d" + addr.getAddressId());
            if (!StringUtils.isBlank(truckRequire.getGoodsVolume())) {
                n.setVolume(new BigDecimal(truckRequire.getGoodsVolume()));
            }
            if (!StringUtils.isBlank(truckRequire.getGoodsWeight())) {
                n.setWeight(new BigDecimal(truckRequire.getGoodsWeight()));
            }
            if (!StringUtils.isBlank(addr.getCoordinates()) && addr.getCoordinates().split(",").length > 1) {
                n.setLng(Double.valueOf(addr.getCoordinates().split(",")[0]));
                n.setLat(Double.valueOf(addr.getCoordinates().split(",")[1]));
            }
            feature.addNode(n);
        }
    }

    private void buildTargetNode(Integer waybillId, Feature feature, Waybill waybill, TruckRequire truckRequire) {
        List<WaybillReceiveAddress> addr1 = waybillReceiveAddressService.findAllByWaybillId(waybillId);

        for (WaybillReceiveAddress addr : addr1) {
            Node n = new Node();
            n.setWaybillId(waybillId);
            n.setType(waybill.getBusinessBranch());
            n.setNodeId("r" + addr.getAddressId());
            if (!StringUtils.isBlank(truckRequire.getGoodsVolume())) {
                n.setVolume(BigDecimal.ZERO.subtract(new BigDecimal(truckRequire.getGoodsVolume())));
            }
            if (!StringUtils.isBlank(truckRequire.getGoodsWeight())) {
                n.setWeight(BigDecimal.ZERO.subtract(new BigDecimal(truckRequire.getGoodsWeight())));
            }
            if (!StringUtils.isBlank(addr.getCoordinates()) && addr.getCoordinates().split(",").length > 1) {
                n.setLng(Double.valueOf(addr.getCoordinates().split(",")[0]));
                n.setLat(Double.valueOf(addr.getCoordinates().split(",")[1]));
            }
            feature.addNode(n);
        }
    }

    @Override
    public void updateAddressSequence(Feature feature) {
        logger.info("come from datacenter,feature is {}", JSON.toJSON(feature));
        List<WaybillDeliveryAddress> waybillDeliveryAddresses = new ArrayList<WaybillDeliveryAddress>();

        List<WaybillReceiveAddress> waybillReceiveAddresses = new ArrayList<WaybillReceiveAddress>();

        for (int i = 0, len = feature.getNodes().size(); i < len; i++) {
            Node n = feature.getNodes().get(i);
            if (n.getNodeId() == null) continue;
            Integer addressId = Integer.valueOf(n.getNodeId().substring(1));
            String pre = n.getNodeId().substring(0, 1);
            if (pre.equals("d")) {
                // 取货地
                WaybillDeliveryAddress d = new WaybillDeliveryAddress();
                d.setSequence(i);
                d.setAddressId(addressId);
                waybillDeliveryAddresses.add(d);
            } else if (pre.equals("r")) {
                // 收货地
                WaybillReceiveAddress r = new WaybillReceiveAddress();
                r.setSequence(i);
                r.setAddressId(addressId);
                waybillReceiveAddresses.add(r);
            }
        }

        if (!waybillDeliveryAddresses.isEmpty()) {
            waybillDeliveryAddressService.batchUpdate(waybillDeliveryAddresses);
        }

        if (!waybillReceiveAddresses.isEmpty()) {
            List<WaybillReceiveAddressResponse> listResponse = new ArrayList<WaybillReceiveAddressResponse>();
            for (WaybillReceiveAddress address : waybillReceiveAddresses) {
                listResponse.add(new WaybillReceiveAddressResponse(address, null));
            }

            waybillReceiveAddressService.batchUpdate(listResponse);
        }
    }

    @Override
    public void buildFeature(final Integer waybillId, final Operate operate, final LoginUser loginUser) {
        final Waybill waybill = waybillCommonService.getWaybillById(waybillId);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                notifyDateCenter(waybill, operate, loginUser);
            }
        });
    }

    private void notifyDateCenter(Waybill waybill, Operate operate, LoginUser loginUser) {
        if (waybill == null) {
            logger.info("waybill is null.");
            return;
        }

        if (null == loginUser) {
            loginUser = Constants.SYS_LOGIN_USER;
            loginUser.setTenantId(waybill.getTenantId());
            loginUser.setTenantCode(waybill.getTenantCode());
        }

        Integer waybillId = waybill.getWaybillId();
        logger.info("start build feature {},{}.", waybillId, operate.getCode());
        Feature feature = new Feature();
        TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybillId, null);
        if (waybill == null || truckRequire == null) return;
        feature.setWaybillId(waybillId);
        feature.setType(waybill.getBusinessBranch());
        feature.setDeliveryTime(waybill.getPlanDeliveryTime());
        feature.setDriverId(waybill.getDriverId());
        feature.setTruckId(waybill.getTruckId());
        feature.setOperate(operate.getCode());

        Truck truck = truckService.getTruck(waybill.getTruckId());
        if (truck == null) return;
        VehicleBo vehicleBo = this.getVehicleBo(truck.getVehicleId(), loginUser);
        if (vehicleBo == null || null == vehicleBo.getVehicleExtend()) return;

        TruckFeature tf = new TruckFeature();
        tf.setDriverId(waybill.getDriverId());

        RealTimePosition realTimePosition = realTimePositionService.queryLastPosByPlateNum(truck.getPlateNumber());
        tf.setTruckId(truck.getTruckId());
        tf.setPlateNumber(truck.getPlateNumber());
        if (realTimePosition != null) {
            tf.setCurrLng(realTimePosition.getLng());
            tf.setCurrLat(realTimePosition.getLat());
        }

        BigDecimal load = vehicleBo.getVehicleExtend().getMaxLoadCapacity() == null ? BigDecimal.ZERO
                : new BigDecimal(vehicleBo.getVehicleExtend().getMaxLoadCapacity() + "");
        BigDecimal l = load.divide(new BigDecimal(1000 + ""));
        tf.setLoad(l);// 默认
        BigDecimal volume = vehicleBo.getVehicleExtend().getLoadVolume() == null ? BigDecimal.ZERO
                : new BigDecimal(vehicleBo.getVehicleExtend().getLoadVolume() + "");
        tf.setVolume(volume);// 默认

        // 放车地点
        com.juma.server.vm.domain.Driver amsDriver = amsServiceV2.getByBindVehicleId(vehicleBo.getVehicleId());
        if (null != amsDriver) {
            tf.setParkLng(amsDriver.getLongitude() == null ? null : amsDriver.getLongitude().doubleValue());
            tf.setParkLat(amsDriver.getLatitude() == null ? null : amsDriver.getLatitude().doubleValue());
        }

        TruckType truckType = truckTypeService.findByBoxAndLength(truck.getVehicleBoxType(),
                truck.getVehicleBoxLength(), waybill.getTenantId());
        if (truckType == null) {
            logger.info("feature _truckType is null,truck is {}", truck.getTruckId());
        } else {
            // 车辆能拉的货物 = 载重 - 冗余
            if (truckType.getRedundancyLoad() != null) {
                tf.setLoad(tf.getLoad().subtract(truckType.getRedundancyLoad()));
            }
            if (truckType.getRedundancyVolume() != null) {
                tf.setVolume(tf.getVolume().subtract(truckType.getRedundancyVolume()));
            }
        }

        feature.addTruck(tf);

        buildSourceNode(waybillId, feature, waybill, truckRequire);

        if (operate != Operate.Start_Delivery) {
            buildTargetNode(waybillId, feature, waybill, truckRequire);
        }

        // 发送到数据平台
        mqService.sendToDataCenter(feature);

        logger.info("end feature {}.", JSON.toJSON(feature));
    }

    // AMS获取车辆
    public VehicleBo getVehicleBo(Integer vehicleId, LoginUser loginUser) {
        VehicleQueryConditionDTO vehicleDTO = new VehicleQueryConditionDTO();
        vehicleDTO.setVehicleId(vehicleId);
        vehicleDTO.setTenantId(loginUser.getTenantId());
        vehicleDTO.setTenantCode(loginUser.getTenantCode());
        return amsServiceV2.getVehicleById(vehicleDTO);
    }

    @Override
    public void doNoDriverAnswerWaybill() {
        logger.info("doNoDriverAnswerWaybill.");

        noMatchAnswer();

        noDriverAnswer();

    }

    private void noMatchAnswer() {
        Date d = new Date();
        List<ConfParamOption> _options = confParamService.findParamOptions("NO_MATCH_ANSWER");
        int _min = _options == null || _options.isEmpty() || _options.get(0).getOptionValue() == null ? 20
                : Integer.parseInt(_options.get(0).getOptionValue());
        WaybillExample eg = new WaybillExample();
        // 希地
        eg.createCriteria().andTenantIdEqualTo(3)
                .andPlanDeliveryTimeBetween(new Date(d.getTime() - 1 * 24 * 60 * 60 * 1000),
                        new Date(d.getTime() + 7 * 24 * 60 * 60 * 1000))
                .andStatusEqualTo((byte) Waybill.Status.WATING_RECEIVE.getCode())
                .andCreateTimeLessThan(new Date(d.getTime() - _min * 60 * 1000)).andIsDeleteEqualTo(false);
        List<Waybill> waybills = waybillCommonService.selectByExample(eg);
        for (Waybill waybill : waybills) {
            waybill.setStatus(Waybill.Status.NO_DRIVER_ANSWER.getCode());
            waybill.setReceiveWay(Waybill.ReceiveWay.MANUAL_ASSIGN.getCode());
            LoginUser loginUser = new LoginUser();
            loginUser.setTenantId(waybill.getTenantId());
            buildFeature(waybill.getWaybillId(), Feature.Operate.Dispatcher_Cancel, null);

            // 通知运营
            scatteredMsgService.pushMsgToAdmin(waybill, loginUser);

            // 操作轨迹
            waybillOperateTrackService.insert(waybill.getWaybillId(), OperateType.AI_MATCH_TIMEOUT,
                    OperateApplication.AI_SYS, null, loginUser);
        }
        if (!waybills.isEmpty()) {
            waybillCommonService.batchUpdate(waybills);
        }
    }

    private void noDriverAnswer() {
        List<ConfParamOption> options = confParamService.findParamOptions("NO_DRIVER_ANSWER");
        int min = options == null || options.isEmpty() || options.get(0).getOptionValue() == null ? 6
                : Integer.parseInt(options.get(0).getOptionValue());
        Date d = new Date();
        // 数据平台派了车没有响应
        WaybillExample example = new WaybillExample();
        example.createCriteria().andTenantIdEqualTo(3)
                .andPlanDeliveryTimeBetween(new Date(d.getTime() - 1 * 24 * 60 * 60 * 1000),
                        new Date(d.getTime() + 7 * 24 * 60 * 60 * 1000))
                .andStatusEqualTo((byte) Waybill.Status.WAITINT_DRIVER_ANSWER.getCode())
                .andReceivingTimeLessThan(new Date(d.getTime() - min * 60 * 1000)).andIsDeleteEqualTo(false);
        List<Waybill> waybills = waybillCommonService.selectByExample(example);
        for (Waybill waybill : waybills) {
            waybill.setDriverId(null);// 取消绑定关系 司机没有响应 不可见
            waybill.setStatus(Waybill.Status.WATING_RECEIVE.getCode());
            waybill.setStatusView(Waybill.StatusView.WATING_RECEIVE.getCode());
            LoginUser loginUser = new LoginUser();
            loginUser.setTenantId(waybill.getTenantId());
            // 通知数据平台
            buildFeature(waybill.getWaybillId(), Feature.Operate.Driver_No_Response, null);
            findTruck(waybill.getWaybillId(), loginUser);

            // 操作轨迹
            waybillOperateTrackService.insert(waybill.getWaybillId(),
                    OperateType.AI_DRIVER_NO_ANSWER, OperateApplication.AI_SYS,
                    null, loginUser);
        }
        if (!waybills.isEmpty()) {
            waybillCommonService.batchUpdate(waybills);
        }
    }

    @Override
    public void doDriverAnswerWaybill(Integer waybillId, LoginUser loginUser) throws BusinessException {
        Waybill waybillDb = waybillCommonService.getWaybillById(waybillId);
        if (waybillDb.getStatus() != Waybill.Status.WAITINT_DRIVER_ANSWER.getCode()) {
            throw new BusinessException("waybill.error.unreceived", "waybill.error.unreceived");
        }
        Waybill waybill = new Waybill();
        waybill.setWaybillId(waybillId);
        waybill.setStatus(Waybill.Status.ASSIGNED.getCode());
        waybill.setStatusView(Waybill.StatusView.WATING_DELIVERY.getCode());
        waybill.setReceivingTime(new Date());
        waybillCommonService.update(waybill, loginUser);

        removeBlacklistCache(waybillId);

        // 通知货主
        scatteredMsgService.pushAssignedWaybillMsg(waybillCommonService.getWaybillById(waybillId), loginUser);

        // 电子围栏
        waybillAutoFenceServicve.bindWaybillIdAndFenceId(waybillId, WaybillBindFence.Sign.DELIVERY_ADDRESS, loginUser);

        // 操作轨迹
        waybillOperateTrackService.insert(waybill.getWaybillId(), OperateType.AI_DRIVER_ANSWER,
                OperateApplication.AI_SYS, null, loginUser);
    }

    public static void main(String[] args) {
        boolean b = ",0,2104,2104,".toString().contains(",2104,");
        System.out.println(b);
        String node = "d123";
        Integer addressId = Integer.valueOf(node.substring(1));
        String pre = node.substring(0, 1);
        System.out.println(addressId);
        System.out.println(pre);
        Date d = new Date();
        System.out.println(new Date(d.getTime() - 1 * 24 * 60 * 60 * 1000));
        System.out.println(new Date(d.getTime() + 7 * 24 * 60 * 60 * 1000));
    }

    @Override
    public void removeBlacklistCache(Integer waybillId) {
        String key = Constants.DATA_CENTER_WAYBILL + "_" + waybillId;
        redisClient.del(key);
    }
}