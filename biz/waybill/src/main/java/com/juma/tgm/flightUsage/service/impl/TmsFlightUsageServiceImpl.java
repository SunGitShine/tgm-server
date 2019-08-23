package com.juma.tgm.flightUsage.service.impl;

import com.alibaba.fastjson.JSON;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.server.vm.domain.Driver;
import com.juma.server.vm.domain.FlightUsage;
import com.juma.server.vm.domain.Vehicle;
import com.juma.server.vm.domain.bo.FlightBo;
import com.juma.server.vm.domain.dao.VehicleDriverDo;
import com.juma.server.vm.domain.dto.DriverQueryConditionDTO;
import com.juma.server.vm.domain.dto.VehicleQueryConditionDTO;
import com.juma.server.vm.domain1.bo.VehicleBo;
import com.juma.server.vm.service.vehicle.AmsServiceV2;
import com.juma.tgm.base.domain.RegionBo;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.flight.domain.bo.FlightUsageQuery;
import com.juma.tgm.flight.domain.bo.RunningWaybill;
import com.juma.tgm.flight.domain.bo.TransportCapacityBo;
import com.juma.tgm.flight.domain.bo.VehicleMismatchReason;
import com.juma.tgm.flightUsage.service.TmsFlightUsageService;
import com.juma.tgm.region.service.RegionTgmService;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.util.TransportCapacityUtils;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.vms.driver.enumeration.DriverTypeEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class TmsFlightUsageServiceImpl implements TmsFlightUsageService {

    private final Logger log = LoggerFactory.getLogger(TmsFlightUsageServiceImpl.class);
    @Resource
    private AmsServiceV2 amsServiceV2;
    @Resource
    private WaybillService waybillService;
    @Resource
    private DriverService driverService;
    @Resource
    private TruckService truckService;
    @Resource
    private TruckTypeService truckTypeService;
    @Resource
    private TruckRequireService truckRequireService;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private RegionTgmService regionTgmService;

    @Resource
    private TransportCapacityUtils transportCapacityUtils;

    @Override
    public Integer findFlightIdBy(Date startTime, Integer spendTime, Integer amsDriverId, LoginUser loginUser) {
        if (null == startTime || null == spendTime) {
            throw new BusinessException("planDeliveryTimeOrspendTimeIsNull",
                    "errors.planDeliveryTimeOrspendTimeIsNull");
        }
        Date endTime = new Date(startTime.getTime() + spendTime * 60 * 1000 + Constants.REDUNDANCY_TIME * 1000);
        return findFlightIdBy(startTime, endTime, amsDriverId, loginUser);
    }

    @Override
    public Integer findFlightIdBy(Date startTime, Date endTime, Integer amsDriverId, LoginUser loginUser) {
        FlightBo flightBo = this.findFlightBy(startTime, endTime, amsDriverId, loginUser);
        return flightBo == null ? null : flightBo.getId();
    }

    private FlightBo findFlightBy(Date startTime, Date endTime, Integer amsDriverId, LoginUser loginUser) {
        if (null == startTime || null == endTime) {
            throw new BusinessException("planDeliveryTimeOrspendTimeIsNull",
                    "errors.planDeliveryTimeOrspendTimeIsNull");
        }

        if (null == amsDriverId) {
            throw new BusinessException("amsDriverIdIsNull", "errors.amsDriverIdIsNull");
        }

        DriverQueryConditionDTO dto = new DriverQueryConditionDTO();
        dto.setDriverId(amsDriverId);
        dto.setTenantId(loginUser.getTenantId());
        Vehicle vehicle = amsServiceV2.getVehicleByDriverId(dto);
        if (null == vehicle) {
            throw new BusinessException("truckNobindDriver", "truck.nobind.driver");
        }
        List<String> areaCodeLikeList = new ArrayList<String>();
        areaCodeLikeList.add(vehicle.getAreaCode());

        PageCondition pageCondition = new PageCondition();
        Map<String, Object> filters = pageCondition.getFilters();
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(1);
        filters.put("startTime", startTime);
        filters.put("endTime", endTime);
        filters.put("driverId", amsDriverId);
        filters.put("areaCodeLikeList", areaCodeLikeList);
        log.info("获取班次pageCondition：{}", JSON.toJSONString(pageCondition));
        Page<FlightBo> page = amsServiceV2.getAvailableFlightByPage(pageCondition, loginUser);
        log.info("获取班次loginUser：{}", JSON.toJSONString(loginUser));
        if (null == page || CollectionUtils.isEmpty(page.getResults())) {
            log.info("获取班次page返回总数：{}", page.getTotal());
            throw new BusinessException("planDeliveryTimeAndCurrentTaskConflict",
                    "errors.planDeliveryTimeAndCurrentTaskConflict");
        }
        List<FlightBo> result = (List<FlightBo>) page.getResults();
        return result.isEmpty() ? null : result.get(0);
    }

    private FlightBo findFlightBy(Date startTime, Date endTime, String plateNumber, LoginUser loginUser) {
        if (null == startTime || null == endTime) {
            throw new BusinessException("planDeliveryTimeOrspendTimeIsNull",
                    "errors.planDeliveryTimeOrspendTimeIsNull");
        }

        if (null == plateNumber) {
            throw new BusinessException("amsDriverIdIsNull", "errors.amsDriverIdIsNull");
        }

        VehicleQueryConditionDTO dto = new VehicleQueryConditionDTO();
        dto.setPlateNumber(plateNumber);
        dto.setTenantCode(loginUser.getTenantCode());
        dto.setTenantId(loginUser.getTenantId());
        VehicleBo bo = amsServiceV2.getByPlateNumber(dto);
        if (null == bo) {
            throw new BusinessException("truckNobindDriver", "truck.nobind.driver");
        }
        List<String> areaCodeLikeList = new ArrayList<String>();
        areaCodeLikeList.add(bo.getAreaCode());

        PageCondition pageCondition = new PageCondition();
        Map<String, Object> filters = pageCondition.getFilters();
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(1);
        filters.put("startTime", startTime);
        filters.put("endTime", endTime);
        filters.put("plateNumber", plateNumber);
        filters.put("areaCodeLikeList", areaCodeLikeList);
        log.info("获取班次pageCondition：{}", JSON.toJSONString(pageCondition));
        Page<FlightBo> page = amsServiceV2.getAvailableFlightByPage(pageCondition, loginUser);
        log.info("获取班次loginUser：{}", JSON.toJSONString(loginUser));
        if (null == page || CollectionUtils.isEmpty(page.getResults())) {
            log.info("获取班次page返回总数：{}", page.getTotal());
            throw new BusinessException("planDeliveryTimeAndCurrentTaskConflict",
                    "errors.planDeliveryTimeAndCurrentTaskConflict");
        }
        List<FlightBo> result = (List<FlightBo>) page.getResults();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Integer findFlightUsageIdBy(Date startTime, Integer spendTime, Integer amsDriverId, String remark,
            LoginUser loginUser) {
        if (null == startTime || null == spendTime) {
            throw new BusinessException("planDeliveryTimeOrspendTimeIsNull",
                    "errors.planDeliveryTimeOrspendTimeIsNull");
        }
        Date endTime = new Date(startTime.getTime() + spendTime * 60 * 1000 + Constants.REDUNDANCY_TIME * 1000);
        Integer flightId = findFlightIdBy(startTime, endTime, amsDriverId, loginUser);

        return findFlightUsageIdBy(flightId, startTime, endTime, remark, loginUser);
    }

    @Override
    public Integer findFlightUsageIdBy(Integer flightId, Date startTime, Integer spendTime, String remark,
            LoginUser loginUser) {
        if (null == startTime || null == spendTime) {
            throw new BusinessException("planDeliveryTimeOrspendTimeIsNull",
                    "errors.planDeliveryTimeOrspendTimeIsNull");
        }
        Date endTime = new Date(startTime.getTime() + spendTime * 60 * 1000 + Constants.REDUNDANCY_TIME * 1000);
        return findFlightUsageIdBy(flightId, startTime, endTime, remark, loginUser);
    }

    // 添加班次占用记录
    @Override
    public Integer findFlightUsageIdBy(Integer flightId, Date startTime, Date endTime, String remark,
            LoginUser loginUser) {
        FlightUsage flightUsage = new FlightUsage();
        flightUsage.setFlightId(flightId);
        flightUsage.setStartTime(startTime);
        flightUsage.setEndTime(endTime);
        flightUsage.setRemark(remark);
        try {
            log.info("添加班次占用记录", JSON.toJSONString(flightUsage));
            FlightUsage usage = amsServiceV2.add(flightUsage, loginUser.getUserId());
            return usage.getFlightUsageId();
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            throw new BusinessException("planDeliveryTimeAndCurrentTaskConflict",
                    "errors.planDeliveryTimeAndCurrentTaskConflict");
        }
    }

    @Override
    public TransportCapacityBo countFreeTransportCapacity(TransportCapacityBo transportCapacityBo,
            LoginEmployee loginEmployee) throws BusinessException {
        if (transportCapacityBo.getCountStartTime() == null)
            return transportCapacityBo;
        if (transportCapacityBo.getCountEndTime() == null)
            return transportCapacityBo;

        // 获取每个时段的空闲车辆
        transportCapacityBo.setOverViewList(
                transportCapacityUtils.getFreeVehicleInfoByValidTimeLength(transportCapacityBo, loginEmployee));

        return transportCapacityBo;
    }

    @Override
    public void cancelFlightUsage(int flightUsageId, String remark, int userId) throws BusinessException {
        amsServiceV2.cancel(flightUsageId, remark, userId);
    }

    @Override
    public Page<FlightUsageQuery> search(PageCondition pageCondition, LoginUser loginUser) {
        List<FlightUsageQuery> result = new ArrayList<FlightUsageQuery>();
        Map<String, Object> filters = pageCondition.getFilters();
        filters.put("areaCodeLikeList", filters.get("areaCodeList"));
        filters.remove("areaCodeList");

        Page<FlightBo> page = amsServiceV2.getFlightByPage(pageCondition, loginUser);
        List<FlightBo> rows = (List<FlightBo>) page.getResults();
        if (CollectionUtils.isEmpty(rows)) {
            return new Page<FlightUsageQuery>(page.getPageNo(), page.getPageSize(), page.getTotal(), result);
        }

        for (FlightBo flightBo : rows) {
            result.add(buildFlightUsageVo(flightBo, loginUser));
        }
        return new Page<FlightUsageQuery>(page.getPageNo(), page.getPageSize(), page.getTotal(), result);
    }

    @Override
    public Page<FlightUsageQuery> availableFlightSearch(PageCondition pageCondition, LoginUser loginUser) {
        List<FlightUsageQuery> result = new ArrayList<FlightUsageQuery>();
        buildAvailableFlightPageCondition(pageCondition);
        Map<String, Object> filters = pageCondition.getFilters();
        filters.put("areaCodeLikeList", filters.get("areaCodeList"));
        filters.remove("areaCodeList");

        log.info("获取可用班次条件pageCondition:{}", JSON.toJSONString(pageCondition));
        Page<FlightBo> page = amsServiceV2.getAvailableFlightByPage(pageCondition, loginUser);
        log.info("获取可用班次结果:{}", JSON.toJSONString(page == null ? "" : page));
        if(page == null){
            return new Page<FlightUsageQuery>(0, 0);
        }
        Collection<FlightBo> rows = page.getResults();
        if (CollectionUtils.isEmpty(rows)) {
            return new Page<FlightUsageQuery>(page.getPageNo(), page.getPageSize(), page.getTotal(), result);
        }

        for (FlightBo flightBo : rows) {
            FlightUsageQuery flightUsageQuery = buildFlightUsageVo(flightBo, loginUser);
            flightUsageQuery.setGoCityLicenseType(flightBo.getGoCityLicenseType());
            flightUsageQuery.setListRunningWaybill(
                    buildRunningWaybillList(flightBo.getVehicleId(), flightBo.getDriverId(), loginUser.getTenantId()));
            buildRunningWaybillDesc(flightUsageQuery);
            result.add(flightUsageQuery);
        }
        return new Page<FlightUsageQuery>(page.getPageNo(), page.getPageSize(), page.getTotal(), result);
    }

    @Override
    public Page<FlightUsageQuery> langdingAvailableFlightSearch(PageCondition pageCondition, LoginUser loginUser) {
        List<FlightUsageQuery> result = new ArrayList<FlightUsageQuery>();
        Integer waybillId = buildLangdingAvailableFlightPageCondition(pageCondition);
        if (null != pageCondition.getFilters()) {
            pageCondition.getFilters().put("areaCodeLikeList", pageCondition.getFilters().get("areaCodeList"));
            pageCondition.getFilters().remove("areaCodeList");
        }

        log.info("获取可用班次条件pageCondition:{}", JSON.toJSONString(pageCondition));
        Page<FlightBo> page = amsServiceV2.getAvailableFlightByPage(pageCondition, loginUser);
        log.info("获取可用班次结果:{}", JSON.toJSONString(page == null ? "" : page));

        if(page == null){
            return new Page<FlightUsageQuery>(0, 0);
        }
        Collection<FlightBo> rows = page.getResults();
        if (CollectionUtils.isEmpty(rows)) {
            return new Page<FlightUsageQuery>(page.getPageNo(), page.getPageSize(), page.getTotal(), result);
        }

        for (FlightBo flightBo : rows) {
            FlightUsageQuery query = buildFlightUsageVo(flightBo, loginUser);
            query.setGoCityLicenseType(flightBo.getGoCityLicenseType());
            query.setListRunningWaybill(buildLandingRunningWaybillList(flightBo.getVehicleId(), flightBo.getDriverId(),
                    waybillId, loginUser));
            buildRunningWaybillDesc(query);
            result.add(query);
        }
        return new Page<FlightUsageQuery>(page.getPageNo(), page.getPageSize(), page.getTotal(), result);
    }

    private Integer buildAvailableFlightPageCondition(PageCondition pageCondition) {
        Map<String, Object> filters = pageCondition.getFilters();
        if (null == filters || filters.isEmpty()) {
            throw new BusinessException("noSpecifyWaybill", "waybill.error.noSpecifyWaybill");
        }

        if (null == filters.get("waybillId")) {
            throw new BusinessException("noSpecifyWaybill", "waybill.error.noSpecifyWaybill");
        }

        Waybill waybill = waybillService.getWaybill(BaseUtil.strToNum(filters.get("waybillId").toString()));
        if (null == waybill) {
            throw new BusinessException("noSpecifyWaybill", "waybill.error.noSpecifyWaybill");
        }
        filters.remove("waybillId");

        // 添加租户信息
        filters.put("tenantId", waybill.getTenantId());
        filters.put("tenantCode", waybill.getTenantCode());

        this.buildCommonAvailableFlightPageCondition(filters, waybill);
        // 预估用车时间段+冗余时间
//        Integer amount = waybill.getEstimateTimeConsumption() == null ? 60 : waybill.getEstimateTimeConsumption() + 60;
        // 运单预估结束时间点(包含冗余时间)
//        String endTime = DateUtil.format(DateUtil.addMinutes(waybill.getPlanDeliveryTime(), amount));
        filters.put("startTime", waybill.getPlanDeliveryTime());
        filters.put("endTime", waybill.getCmEstimateFinishTime());

        pageCondition.setFilters(filters);

        return waybill.getWaybillId();
    }

    // 组装查询可用班次的条件:返回用车时间
    private Integer buildLangdingAvailableFlightPageCondition(PageCondition pageCondition) {
        Map<String, Object> filters = pageCondition.getFilters();
        if (null == filters || filters.isEmpty()) {
            throw new BusinessException("noSpecifyWaybill", "waybill.error.noSpecifyWaybill");
        }

        if (null == filters.get("waybillId")) {
            throw new BusinessException("noSpecifyWaybill", "waybill.error.noSpecifyWaybill");
        }

        Waybill waybill = waybillService.getWaybill(BaseUtil.strToNum(filters.get("waybillId").toString()));
        if (null == waybill) {
            throw new BusinessException("noSpecifyWaybill", "waybill.error.noSpecifyWaybill");
        }
        filters.remove("waybillId");

        // 添加租户信息
        filters.put("tenantId", waybill.getTenantId());
        filters.put("tenantCode", waybill.getTenantCode());

        this.buildCommonAvailableFlightPageCondition(filters, waybill);
        filters.put("startTime", waybill.getPlanDeliveryTime());
        filters.put("endTime", waybill.getCmEstimateFinishTime());

        pageCondition.setFilters(filters);
        return waybill.getWaybillId();
    }

    private void buildCommonAvailableFlightPageCondition(Map<String, Object> filters, Waybill waybill) {
        // 车型条件
        if (null != filters.get("truckTypeId")) {
            Integer truckTypeId = BaseUtil.strToNum(filters.get("truckTypeId").toString());
            filters.remove("truckTypeId");
            TruckType truckType = truckTypeService.getTruckType(truckTypeId);
            if (null != truckType) {
                filters.put("vehicleBoxType", truckType.getVehicleBoxType());// 厢型
                filters.put("boxLevel",
                        truckType.getTruckLengthId() == null ? null : truckType.getTruckLengthId().toString());// 厢长
            }
        }

        // 添加载重条件
        filters.put("maxLoadCapacity", waybill.getGoodsWeight());
    }

    // 根据车辆获取进行中的运单集合
    private List<RunningWaybill> buildRunningWaybillList(Integer vehicleId, Integer amsDriverId, Integer tenantId) {
        List<RunningWaybill> listRunningWaybill = new ArrayList<RunningWaybill>();
        Truck truck = truckService.findTruckByVehicleId(vehicleId);
        if (null == truck) {
            return listRunningWaybill;
        }

        com.juma.tgm.driver.domain.Driver driver = driverService.findDriverByAmsDriverId(amsDriverId);
        if (null == driver) {
            return listRunningWaybill;
        }

        List<Waybill> list = waybillService.findRunningWaybillByTruckId(truck.getTruckId());
        for (Waybill waybill : list) {
            if (!driver.getDriverId().equals(waybill.getDriverId())) {
                continue;
            }

            if (!tenantId.equals(waybill.getTenantId())) {
                continue;
            }

            RunningWaybill runningWaybill = new RunningWaybill();
            StringBuffer sf = new StringBuffer();
            // 离仓时间：没有离仓时间取用车时间
            sf.append(DateUtil.format(
                    waybill.getDeliveryTime() == null ? waybill.getPlanDeliveryTime() : waybill.getDeliveryTime(),
                    DateUtil.MMDDHHMM)).append(" ");

            if (null != waybill.getBusinessBranch()) {
                if (Waybill.BusinessBranch.BRANCH_SCATTERED.getCode() == waybill.getBusinessBranch()) {
                    sf.append(Waybill.BusinessBranch.BRANCH_SCATTERED.getDescr()).append(" ");
                }
                if (Waybill.BusinessBranch.BRANCH_FULL.getCode() == waybill.getBusinessBranch()) {
                    sf.append(Waybill.BusinessBranch.BRANCH_FULL.getDescr()).append(" ");
                }
            }

            CustomerInfo customerInfo = customerInfoService.findCusInfoById(waybill.getCustomerId());
            if (customerInfo != null) {
                sf.append(customerInfo.getCustomerName());
            }
            runningWaybill.setWaybillId(waybill.getWaybillId());
            runningWaybill.setDesc(sf.toString());
            listRunningWaybill.add(runningWaybill);
        }
        return listRunningWaybill;
    }

    // 根据车辆获取进行中的运单集合：落地配
    private List<RunningWaybill> buildLandingRunningWaybillList(Integer vehicleId, Integer amsDriverId,
            Integer waybillId, LoginUser loginUser) {
        List<RunningWaybill> listRunningWaybill = new ArrayList<RunningWaybill>();
        Truck truck = truckService.findTruckByVehicleId(vehicleId);
        if (null == truck) {
            return listRunningWaybill;
        }

        com.juma.tgm.driver.domain.Driver driver = driverService.findDriverByAmsDriverId(amsDriverId);
        if (null == driver) {
            return listRunningWaybill;
        }

        Waybill waybill = waybillService.getWaybill(waybillId);
        if (null == waybill) {
            return listRunningWaybill;
        }

        PageCondition pageCondition = new PageCondition();
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(Integer.MAX_VALUE);
        pageCondition.getFilters().put("truckId", truck.getTruckId());

        List<Integer> statusViewList = new ArrayList<Integer>();
        statusViewList.add(Waybill.StatusView.WATING_DELIVERY.getCode());
        statusViewList.add(Waybill.StatusView.DELIVERYING.getCode());
        pageCondition.getFilters().put("statusViewList", statusViewList);

        pageCondition.getFilters().put("startTime", DateUtil.dayStartReturnDate(waybill.getPlanDeliveryTime()));
        pageCondition.getFilters().put("endTime", DateUtil.dayEndReturnDate(waybill.getPlanDeliveryTime()));

        // 零担
        buildScatteredWaybillRunning(listRunningWaybill, pageCondition, waybillId, driver.getDriverId(), loginUser);

        // 整车
        buildFullWaybillRunning(listRunningWaybill, pageCondition, waybillId, driver.getDriverId(), loginUser);
        return listRunningWaybill;
    }

    // 进行中的零担运单
    private void buildScatteredWaybillRunning(List<RunningWaybill> listRunningWaybill, PageCondition pageCondition,
            Integer waybillId, Integer driverId, LoginUser loginUser) {
        pageCondition.getFilters().put("businessBranch", Waybill.BusinessBranch.BRANCH_SCATTERED.getCode());
        Page<Waybill> pageScattered = waybillService.search(loginUser, pageCondition);

        RunningWaybill runningWaybill = new RunningWaybill();
        StringBuffer sf = new StringBuffer("零担：");
        BigDecimal goodsWeight = BigDecimal.ZERO;
        BigDecimal goodsVolume = BigDecimal.ZERO;
        String entryLicenseStr = "";

        for (Waybill waybill : pageScattered.getResults()) {
            if (!driverId.equals(waybill.getDriverId())) {
                continue;
            }

            if (!loginUser.getTenantId().equals(waybill.getTenantId())) {
                continue;
            }

            if (waybillId.equals(waybill.getWaybillId())) {
                continue;
            }

            runningWaybill.getListWaybillId().add(waybill.getWaybillId());

            // 重量与体积
            TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), null);
            if (null != truckRequire) {
                goodsWeight = goodsWeight.add(new BigDecimal(truckRequire.getGoodsWeight()));
                goodsVolume = goodsVolume.add(new BigDecimal(truckRequire.getGoodsVolume()));
            }

            // 入城的判断
            if (null != waybill.getEntryLicense() && waybill.getEntryLicense().equals(1)
                    && StringUtils.isBlank(entryLicenseStr)) {
                entryLicenseStr = "入城";
            }
        }

        if (!runningWaybill.getListWaybillId().isEmpty()) {
            sf.append(runningWaybill.getListWaybillId().size()).append("单 ");

            if (goodsWeight.compareTo(BigDecimal.ZERO) == 1) {
                sf.append(goodsWeight).append("吨 ");
            }

            if (goodsVolume.compareTo(BigDecimal.ZERO) == 1) {
                sf.append(goodsVolume).append("方 ");
            }

            sf.append(entryLicenseStr);
            runningWaybill.setDesc(sf.toString());
            listRunningWaybill.add(runningWaybill);
        }
    }

    // 进行中的整车运单
    private void buildFullWaybillRunning(List<RunningWaybill> listRunningWaybill, PageCondition pageCondition,
            Integer waybillId, Integer driverId, LoginUser loginUser) {
        pageCondition.getFilters().put("businessBranch", Waybill.BusinessBranch.BRANCH_FULL.getCode());
        Page<Waybill> pageFull = waybillService.search(loginUser, pageCondition);
        for (Waybill waybill : pageFull.getResults()) {
            if (!driverId.equals(waybill.getDriverId())) {
                continue;
            }

            if (!loginUser.getTenantId().equals(waybill.getTenantId())) {
                continue;
            }

            if (waybillId.equals(waybill.getWaybillId())) {
                continue;
            }

            RunningWaybill runningFullWaybill = new RunningWaybill();
            runningFullWaybill.setWaybillId(waybill.getWaybillId());
            StringBuffer sfr = new StringBuffer("整车：");
            sfr.append(DateUtil.format(waybill.getPlanDeliveryTime(), DateUtil.YYYYMMDDHHMM));
            runningFullWaybill.setDesc(sfr.toString());
            listRunningWaybill.add(runningFullWaybill);
        }
    }

    // 组装VO数据
    private FlightUsageQuery buildFlightUsageVo(FlightBo flightBo, LoginUser loginUser) {
        FlightUsageQuery vo = new FlightUsageQuery();
        vo.setFlightId(flightBo.getId());
        vo.setVehicleId(flightBo.getVehicleId());
        if (flightBo.getVehicleId() != null) {
            VehicleQueryConditionDTO dto = new VehicleQueryConditionDTO();
            dto.setVehicleId(flightBo.getVehicleId());
            dto.setTenantId(loginUser.getTenantId());
            dto.setTenantCode(loginUser.getTenantCode());
            VehicleBo vehicleBo = amsServiceV2.getVehicleById(dto);
            if (vehicleBo != null && null != vehicleBo.getVehicleExtend()) {
                vo.setMaxLoadCapacity(vehicleBo.getVehicleExtend().getMaxLoadCapacity());
                vo.setLoadVolume(vehicleBo.getVehicleExtend().getLoadVolume());
            }
        }

        vo.setPlateNumber(flightBo.getPlateNumber());
        vo.setVehicleStatusName(flightBo.getVehicleStatusName());
        vo.setCapacityStatusName(flightBo.getFlightStatusName());
        vo.setStartTime(DateUtil.format(flightBo.getStartTime(), DateUtil.YYYYMMDDHHMM));
        vo.setEndTime(DateUtil.format(flightBo.getEndTime(), DateUtil.YYYYMMDDHHMM));

        // 司机信息
        vo.setAmsDriverId(flightBo.getDriverId());
        vo.setDriverStatusName(flightBo.getDriverStatusName());

        DriverQueryConditionDTO dto = new DriverQueryConditionDTO();
        dto.setTenantId(loginUser.getTenantId());
        dto.setDriverId(flightBo.getDriverId());
        Driver driver = amsServiceV2.getDriverById(dto);
        if (null != driver) {
            vo.setDriverName(driver.getName());
            vo.setDriverPhone(driver.getPhone());
            vo.setCapacityTypeName(DriverTypeEnum.getDescByCode(driver.getType().intValue()));
            // 放车区域
            String regionName = regionTgmService.findSimpleRegionName(driver.getParkRegionCode(),
                    RegionBo.SimpleRegionKey.CITY_TOWN);
            vo.setParkingArea((StringUtils.isBlank(regionName) ? "" : regionName)
                    + (StringUtils.isBlank(driver.getParkAddress()) ? "" : ("(" + driver.getParkAddress() + ")")));
        }

        com.juma.tgm.driver.domain.Driver tmsDriver = driverService.findDriverByAmsDriverId(flightBo.getDriverId());
        if (null != tmsDriver && null != tmsDriver.getWhetherAcceptAllocateOrder()
                && tmsDriver.getWhetherAcceptAllocateOrder().equals(1)) {
            vo.setIsAcceptAllocateOrder(true);
            vo.setDriverId(tmsDriver.getDriverId());
        }

        Truck truck = truckService.findTruckByVehicleId(flightBo.getVehicleId());
        if (null != truck) {
            vo.setTruckTypeName(
                    truckTypeService.findTruckTypeNameBy(truck.getVehicleBoxType(), truck.getVehicleBoxLength()));
            vo.setTruckId(truck.getTruckId());
        }

        vo.setListRunningWaybill(
                buildRunningWaybillList(flightBo.getVehicleId(), flightBo.getDriverId(), loginUser.getTenantId()));
        buildRunningWaybillDesc(vo);
        return vo;
    }

    // 组装进行中的运单显示
    private void buildRunningWaybillDesc(FlightUsageQuery flightUsageQuery) {
        StringBuffer runningWaybillDescSf = new StringBuffer("");
        for (RunningWaybill runningWaybill : flightUsageQuery.getListRunningWaybill()) {
            runningWaybillDescSf.append(runningWaybill.getDesc()).append("，");
        }

        String runningWaybillDesc = runningWaybillDescSf.toString();
        if (runningWaybillDesc.length() > 0) {
            flightUsageQuery.setRunningWaybillDesc(runningWaybillDesc.substring(0, runningWaybillDesc.length() - 1));
        } else {
            flightUsageQuery.setRunningWaybillDesc(null);
        }
    }

    @Override
    public Page<FlightUsageQuery> allVehicleHasBindDriverSearch(PageCondition pageCondition, LoginUser loginUser) {
        Page<FlightUsageQuery> page = this.getVehicleHasBindDriver(pageCondition, loginUser);
        for (FlightUsageQuery f : page.getResults()) {
            if (null == f.getFlightId()) {
                continue;
            }
            f.setListRunningWaybill(
                    buildRunningWaybillList(f.getVehicleId(), f.getAmsDriverId(), loginUser.getTenantId()));
            buildRunningWaybillDesc(f);
        }

        return page;
    }

    @Override
    public Page<FlightUsageQuery> landingAllVehicleHasBindDriverSearch(PageCondition pageCondition,
            LoginUser loginUser) {
        Page<FlightUsageQuery> page = this.getVehicleHasBindDriver(pageCondition, loginUser);
        for (FlightUsageQuery f : page.getResults()) {
            f.setListRunningWaybill(buildLandingRunningWaybillList(f.getVehicleId(), f.getAmsDriverId(),
                    Integer.parseInt(pageCondition.getFilters().get("waybillId").toString()), loginUser));
            buildRunningWaybillDesc(f);
        }

        return page;
    }

    // 获取绑定车辆的司机
    private Page<FlightUsageQuery> getVehicleHasBindDriver(PageCondition pageCondition, LoginUser loginUser) {
        List<FlightUsageQuery> result = new ArrayList<FlightUsageQuery>();
        if (null == pageCondition.getFilters()) {
            return new Page<FlightUsageQuery>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0, result);
        }

        // 运单
        Object obj = pageCondition.getFilters().get("waybillId");
        if (null == obj) {
            return new Page<FlightUsageQuery>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0, result);
        }

        Waybill waybill = waybillService
                .getWaybill(Integer.parseInt(pageCondition.getFilters().get("waybillId").toString()));
        if (null == waybill) {
            return new Page<FlightUsageQuery>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0, result);
        }

        pageCondition.getFilters().put("areaCodeLikeList", pageCondition.getFilters().get("areaCodeList"));
        pageCondition.getFilters().remove("areaCodeList");

        // 车型
        if (null != pageCondition.getFilters().get("truckTypeId")) {
            Integer truckTypeId = BaseUtil.strToNum(pageCondition.getFilters().get("truckTypeId").toString());
            TruckType truckType = truckTypeService.getTruckType(truckTypeId);
            if (null != truckType) {
                pageCondition.getFilters().put("vehicleBoxType", truckType.getVehicleBoxType());// 厢型
                pageCondition.getFilters().put("boxLevel",
                        truckType.getTruckLengthId() == null ? null : truckType.getTruckLengthId().toString());// 厢长
            }
            pageCondition.getFilters().remove("truckTypeId");
        }

        Page<VehicleDriverDo> p = amsServiceV2.queryVehicleHasBindDriver(pageCondition, loginUser);
        if (null == p || CollectionUtils.isEmpty(p.getResults())) {
            return new Page<FlightUsageQuery>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0, result);
        }

        for (VehicleDriverDo d : p.getResults()) {
            FlightUsageQuery query = this.buildVehicleAndDriver(d, waybill, waybill.getPlanDeliveryTime(),
                    waybill.getCmEstimateFinishTime(), loginUser);
            result.add(query);
        }
        return new Page<FlightUsageQuery>(pageCondition.getPageNo(), pageCondition.getPageSize(), p.getTotal(), result);
    }

    // 组装司机车辆信息
    private FlightUsageQuery buildVehicleAndDriver(VehicleDriverDo d, Waybill waybill, Date startTime, Date endTime,
            LoginUser loginUser) {
        FlightUsageQuery query = new FlightUsageQuery();

        try {
            // 获取班次
            FlightBo bo = findFlightBy(startTime, endTime, d.getPlateNumber(), loginUser);
            query.setFlightId(bo.getId());
            query.setStartTime(DateUtil.format(bo.getStartTime(), DateUtil.YYYYMMDDHHMM));
            query.setEndTime(DateUtil.format(bo.getEndTime(), DateUtil.YYYYMMDDHHMM));
        } catch (Exception e) {
            query.getListVehicleMismatchReason()
            .add(new VehicleMismatchReason("用车时间段", DateUtil.format(startTime, DateUtil.YYYYMMDDHHMM) + " - "
                    + DateUtil.format(endTime, DateUtil.YYYYMMDDHHMM), "该时间段车辆被占用"));
        }

        // 车辆信息
        query.setVehicleId(d.getVehicleId());
        query.setPlateNumber(d.getPlateNumber());
        VehicleQueryConditionDTO dto = new VehicleQueryConditionDTO();
        dto.setVehicleId(d.getVehicleId());
        dto.setTenantId(loginUser.getTenantId());
        VehicleBo vehicleBo = amsServiceV2.getVehicleById(dto);
        if (vehicleBo != null && null != vehicleBo.getVehicleExtend()) {
            query.setMaxLoadCapacity(vehicleBo.getVehicleExtend().getMaxLoadCapacity());
            query.setLoadVolume(vehicleBo.getVehicleExtend().getLoadVolume());
            if (null != waybill.getGoodsWeight()) {
                if (null == vehicleBo.getVehicleExtend().getMaxLoadCapacity()) {
                    query.getListVehicleMismatchReason().add(new VehicleMismatchReason("车辆载重", 0 + "KG", "不符合用车需求"));
                } else {
                    if (new BigDecimal(waybill.getGoodsWeight() + "")
                            .compareTo(new BigDecimal(vehicleBo.getVehicleExtend().getMaxLoadCapacity() + "")) == 1) {
                        query.getListVehicleMismatchReason().add(new VehicleMismatchReason("车辆载重",
                                vehicleBo.getVehicleExtend().getMaxLoadCapacity() + "KG", "不符合用车需求"));

                    }
                }
            }

            // 车型
            if (null != vehicleBo.getVehicleExtend().getVehicleBoxType()) {
                query.setTruckTypeName(truckTypeService.findTruckTypeNameBy(
                        vehicleBo.getVehicleExtend().getVehicleBoxType().intValue(),
                        vehicleBo.getVehicleExtend().getBoxLevel()));
            }

            query.setGoCityLicenseType(vehicleBo.getVehicleExtend().getGoCityLicenseType());
            query.setGoCityLicenseTypeText(truckService.getEntryLicenseCnName(vehicleBo.getVehicleExtend().getGoCityLicenseType()));
        }

        // 司机信息
        query.setAmsDriverId(d.getDriverId());
        query.setDriverName(d.getDriverName());
        query.setDriverPhone(d.getPhone());
        DriverQueryConditionDTO driverDto = new DriverQueryConditionDTO();
        driverDto.setTenantId(loginUser.getTenantId());
        driverDto.setDriverId(d.getDriverId());
        Driver driver = amsServiceV2.getDriverById(driverDto);
        if (null != driver) {
            query.setCapacityTypeName(DriverTypeEnum.getDescByCode(driver.getType().intValue()));
            // 放车区域
            String regionName = regionTgmService.findSimpleRegionName(driver.getParkRegionCode(),
                    RegionBo.SimpleRegionKey.CITY_TOWN);
            query.setParkingArea((StringUtils.isBlank(regionName) ? "" : regionName)
                    + (StringUtils.isBlank(driver.getParkAddress()) ? "" : ("(" + driver.getParkAddress() + ")")));
        }

        com.juma.tgm.driver.domain.Driver tmsDriver = driverService.findDriverByAmsDriverId(d.getDriverId());
        if (null != tmsDriver && null != tmsDriver.getWhetherAcceptAllocateOrder()
                && tmsDriver.getWhetherAcceptAllocateOrder().equals(1)) {
            query.setIsAcceptAllocateOrder(true);
            query.setDriverId(tmsDriver.getDriverId());
        }

        return query;
    }
}
