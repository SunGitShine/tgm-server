package com.juma.tgm.waybill.service.impl;

import com.alibaba.fastjson.JSON;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.server.vm.domain.bo.FlightBo;
import com.juma.server.vm.service.vehicle.AmsServiceV2;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.waybill.dao.WaybillDao;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillMap;
import com.juma.tgm.waybill.domain.WaybillNav;
import com.juma.tgm.waybill.domain.WaybillOperateTrack;
import com.juma.tgm.waybill.domain.map.FindTruckMapView;
import com.juma.tgm.waybill.domain.map.FindTruckMapViewCond;
import com.juma.tgm.waybill.domain.map.TruckMapView;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateApplication;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateType;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillOperateTrackService;
import com.juma.tgm.waybill.service.WaybillQueryService;
import com.juma.tgm.waybill.service.WaybillService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WaybillQueryServiceImpl implements WaybillQueryService {

    private final Logger log = LoggerFactory.getLogger(WaybillQueryServiceImpl.class);
    @Resource
    private WaybillDao waybillDao;
    @Resource
    private WaybillService waybillService;
    @Resource
    private DriverService driverService;
    @Resource
    private TruckService truckService;
    @Resource
    private TruckRequireService truckRequireService;
    @Resource
    private TruckTypeService truckTypeService;
    @Resource
    private WaybillOperateTrackService waybillOperateTrackService;
    @Resource
    private AmsServiceV2 amsServiceV2;
    @Resource
    private WaybillCommonService waybillCommonService;

    @Override
    public WaybillNav findLastRunningWaybillByTruck(String plateNumber) {
        Truck truck = truckService.findTruckByPlateNumber(plateNumber);
        if (truck == null) {
            throw new BusinessException("truck.error.notfound", "truck.error.notfound");
        }

        WaybillNav nav = new WaybillNav();
        Waybill waybill = waybillDao.findLastRunningWaybillByTruckId(truck.getTruckId());
        if (waybill != null) {
            nav.setWaybillId(waybill.getWaybillId());
            nav.setWaybillNo(waybill.getWaybillNo());
        }
        return nav;
    }

    @Override
    public WaybillMap findWaybillMapById(Integer waybillId) {
        log.info("WaybillQueryService为在途监控提供的接口findWaybillMapById-waybillId:{}", waybillId);
        Waybill waybill = waybillService.getWaybill(waybillId);
        waybillService.getWaybillTraceInfo(waybill);
        WaybillMap map = waybillCommonService.buildWaybillMap(waybill);

        List<WaybillOperateTrack> arriveDepots = waybillOperateTrackService.listByWaybillId(waybillId);
        for (WaybillOperateTrack operateTrack : arriveDepots) {
            if (operateTrack.getOperateApplication() == null || operateTrack.getOperateType() == null) continue;
            // 司机到仓
            if (
//                    waybill.getBusinessBranch() != Waybill.BusinessBranch.BRANCH_SCATTERED.getCode()
//                    && waybill.getBusinessBranch() != Waybill.BusinessBranch.BRANCH_FULL.getCode() &&
                            operateTrack.getOperateApplication() == OperateApplication.DRIVER_SYS
                    .getCode()
                    && operateTrack.getOperateType() == OperateType.ARRIVE_DEPOT.getCode()) {
                map.setManualArriveLocation(operateTrack.getOperateAddressCoordinates());
                map.setArriveDepotTime(operateTrack.getCreateTime());
            }

            // 电子围栏到仓
            if (operateTrack.getOperateApplication() == OperateApplication.FRNCE.getCode()
                    && operateTrack.getOperateType() == OperateType.ARRIVE_DEPOT.getCode()) {
                map.setFenceArriveDepotTime(operateTrack.getDeclareTime());
                map.setFenceArriveLocation(operateTrack.getOperateAddressCoordinates());
            }

            // 电子围栏到仓
            if (operateTrack.getOperateApplication() == OperateApplication.FRNCE.getCode()
                    && operateTrack.getOperateType() == OperateType.LEAVE_DEPOT.getCode()) {
                map.setFenceLeaveDepotTime(operateTrack.getCreateTime());
            }
        }

        log.info("WaybillQueryService为在途监控提供的接口findWaybillMapById-result:{}", JSON.toJSONString(map));
        return map;
    }

    @Override
    public FindTruckMapView findTruckMapView(FindTruckMapViewCond cond, LoginEmployee loginEmployee) {
        if (cond == null || cond.getWaybillId() == null)
            throw new BusinessException("errors.paramError", "errors.paramError");
        log.info("findTruckMapView-wt-param", JSON.toJSONString(cond));
        FindTruckMapView truckMapView = new FindTruckMapView();
        truckMapView.setWaybillId(cond.getWaybillId());

        List<TruckMapView> views = new ArrayList<TruckMapView>();
        Waybill waybill = waybillService.getWaybill(cond.getWaybillId());
        if (null == waybill) {
            throw new BusinessException("waybill.error.notfound", "waybill.error.notfound");
        }
        TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), loginEmployee);
        if (truckRequire == null) {
            throw new BusinessException("errors.paramError", "errors.paramError");
        }

        TruckType truckType = truckTypeService.getTruckType(truckRequire.getTruckTypeId());
        // 组装条件
        PageCondition pageCondition = new PageCondition();
        if (truckType != null) {
            pageCondition.getFilters().put("vehicleBoxType", truckType.getVehicleBoxType());// 厢型
            pageCondition.getFilters().put("boxLevel", truckType.getTruckLengthId());// 厢长
        }
        pageCondition.getFilters().put("plateNumber", cond.getPlateNumber());
        pageCondition.getFilters().put("driverName", cond.getDriverName());
        pageCondition.getFilters().put("regionCode", cond.getParkingRegionCode());
        pageCondition.getFilters().put("goCityLicenseType", cond.getEntryLicense());
        pageCondition.getFilters().put("areaCodeLikeList", cond.getAreaCodeList());
        pageCondition.getFilters().put("startTime", waybill.getPlanDeliveryTime());
        // 添加载重条件
        pageCondition.getFilters().put("maxLoadCapacity", waybill.getGoodsWeight());

        pageCondition.getFilters().put("endTime", new Date(
                waybill.getPlanDeliveryTime().getTime() + waybill.getEstimateTimeConsumption() + 1 * 60 * 60 * 1000));
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(Integer.MAX_VALUE);
        log.info("findTruckMapView-pageCondition", JSON.toJSONString(pageCondition));

        // --------------------其他条件再确定-----------------------------------
        Page<FlightBo> page = amsServiceV2.getAvailableFlightByPage(pageCondition, loginEmployee);
        if (null == page || CollectionUtils.isEmpty(page.getResults())) {
            return truckMapView;
        }
        log.info("findTruckMapView-vm-return-page", JSON.toJSONString(page));

        List<FlightBo> rows = new ArrayList<FlightBo>(page.getResults());
        for (FlightBo flightBo : rows) {
            TruckMapView view = new TruckMapView();
            // 由于是拉取的AMS系统的可用班次列表，所以车辆都是可用的
            view.setStatus(0);
            view.setFlightId(flightBo.getId());
            // 车辆信息
            Truck truck = truckService.findTruckByVehicleId(flightBo.getVehicleId());
            if (null != truck) {
                view.setTruckId(truck.getTruckId());
                view.setPlateNumber(truck.getPlateNumber());
            }

            com.juma.server.vm.domain.Driver amsDriver = amsServiceV2.getByBindVehicleId(flightBo.getVehicleId());
            if (amsDriver != null) {
                if (amsDriver.getLatitude() != null) {
                    view.setLatitude(amsDriver.getLatitude().doubleValue());
                }
                if (amsDriver.getLongitude() != null) {
                    view.setLongitude(amsDriver.getLongitude().doubleValue());
                }
                view.setParkRegionCode(amsDriver.getParkRegionCode());
                view.setParkAddress(amsDriver.getParkAddress());
            }

            // 司机信息
            Driver driver = driverService.findDriverByAmsDriverId(flightBo.getDriverId());
            if (null != driver) {
                view.setDriverId(driver.getDriverId());
                view.setDriverName(driver.getNickname());
            }
            views.add(view);
        }
        truckMapView.setTruckMapViews(views);
        log.info("findTruckMapView-return", JSON.toJSONString(truckMapView));
        return truckMapView;
    }

    @Override
    public List<WaybillMap> findRunningWaybillByTruck(String plateNumber) {
        List<WaybillMap> waybillMaps = new ArrayList<WaybillMap>();
        Truck truck = truckService.findTruckByPlateNumber(plateNumber);
        if (truck == null) {
            throw new BusinessException("truck.error.notfound", "truck.error.notfound");
        }

        List<Waybill> waybills = waybillDao.findRunningWaybillByTruckId(truck.getTruckId());
        for (Waybill waybill : waybills) {
            WaybillMap map = waybillCommonService.buildWaybillMap(waybill);
            waybillMaps.add(map);
        }

        log.info("findRunningWaybillByTruck response: {}", JSON.toJSONString(waybillMaps));
        return waybillMaps;
    }

    @Override
    public List<WaybillMap> findRunningWaybillByTruck(String plateNumber, Integer tenantId) {
        List<WaybillMap> waybillMaps = new ArrayList<WaybillMap>();
        if (null == tenantId) {
            return waybillMaps;
        }
        
        Truck truck = truckService.findTruckByPlateNumber(plateNumber);
        if (truck == null) {
            throw new BusinessException("truck.error.notfound", "truck.error.notfound");
        }

        List<Waybill> waybills = waybillDao.findRunningWaybillByTruckId(truck.getTruckId());
        for (Waybill waybill : waybills) {
            if (!tenantId.equals(waybill.getTenantId())) {
                continue;
            }
            WaybillMap map = waybillCommonService.buildWaybillMap(waybill);
            waybillMaps.add(map);
        }

        log.info("findRunningWaybillByTruck response: {}", JSON.toJSONString(waybillMaps));
        return waybillMaps;
    }

    @Override
    public void assignTask(int waybillId, int flightId, int truckId, int driverId, String remark, int userId) {
        LoginUser loginUser = new LoginUser(userId);

        Waybill wb = waybillService.getWaybill(waybillId);
        if (null != wb && null != wb.getTruckId() && null != wb.getDriverId()) {
            waybillService.changeCar(waybillId, driverId, truckId, null, Waybill.ReceiveWay.MANUAL_ASSIGN.getCode(),
                    remark, loginUser);
        } else {
            waybillService.changeToAssigned(waybillId, driverId, truckId, null,
                    Waybill.ReceiveWay.MANUAL_ASSIGN.getCode(), remark, loginUser);
        }

        // 操作轨迹
        waybillOperateTrackService.insert(waybillId, OperateType.ASSIGNED_SYS,
                OperateApplication.BACKGROUND_MAP_MONITOR, null, loginUser);
    }

    @Override
    public List<Waybill> findWaybillByIds(List<Integer> waybillIds) {
        Waybill waybill = new Waybill();
        waybill.setWaybillIds(waybillIds);
        return waybillDao.findWaybillByIds(waybill);
    }

}
