package com.juma.tgm.waybill.service.impl;

import com.alibaba.fastjson.JSON;
import com.giants.cache.redis.RedisClient;
import com.giants.common.collections.CollectionUtils;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.EmployeeInfo;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.waybill.dao.WaybillDao;
import com.juma.tgm.waybill.domain.*;
import com.juma.tgm.waybill.service.*;
import com.juma.vms.external.service.VmsService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class WaybillAutoMatchServiceImpl implements WaybillAutoMatchService {

    
    private static Logger log = LoggerFactory.getLogger(WaybillAutoMatchServiceImpl.class);
    @Resource
    private WaybillDao waybillDao;
    @Resource
    private WaybillDeliveryAddressService waybillDeliveryAddressService;
    @Resource
    private WaybillReceiveAddressService waybillReceiveAddressService;
    @Resource
    private DriverService driverService;
    @Resource
    private TruckService truckService;
    @Resource
    private TruckTypeService truckTypeService;
    @Resource
    private WaybillCommonService waybillCommonService;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private TruckRequireService truckRequireService;
    @Resource
    private UserService userService;
    @Resource
    private RedisClient redisClient;
    @Resource
    private WaybillParamService waybillParamService;
    @Resource
    private com.juma.crm.customer.service.CustomerInfoService cmsCustomerInfoService;
    @Resource
    private VmsService  vmsService;
    
    @Override
    public Page<WaybillNoFinish> searchNoFinishWaybill(PageCondition pageCondition) {
        List<Integer> statusViewList = new ArrayList<Integer>();
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(Integer.MAX_VALUE);
        statusViewList.add(Waybill.StatusView.WATING_DELIVERY.getCode());
        statusViewList.add(Waybill.StatusView.DELIVERYING.getCode());
        pageCondition.getFilters().put("statusViewList", statusViewList);

        Map<Integer, String> truckMapping = new HashMap<Integer, String>();

        List<WaybillNoFinish> results = new ArrayList<WaybillNoFinish>();
        pageCondition.getFilters().put("isTest", false);
        pageCondition.getFilters().put("tenantId", 2);
        pageCondition.getFilters().put("startTime", DateUtil.dayAddStart(-29));
        pageCondition.getFilters().put("endTime", DateUtil.dayAddEnd(0));

        List<String> areaCodeList = new ArrayList<String>();
        areaCodeList.add("000000");
        areaCodeList.add("00010000");
        pageCondition.getFilters().put("areaCodeList", areaCodeList);
        
//        int count = waybillDao.searchCount(pageCondition);
        List<Waybill> rows = waybillDao.search(pageCondition);
        for (Waybill waybill : rows) {
            if (truckMapping.containsKey(waybill.getTruckId())) continue;
            truckMapping.put(waybill.getTruckId(), "");

            WaybillNoFinish waybillNoFinish = new WaybillNoFinish();

            waybillNoFinish.setDriverId(waybill.getDriverId());
            waybillNoFinish.setTruckCustomerId(waybill.getTruckCustomerId());
            waybillNoFinish.setCustomerId(waybill.getCustomerId());
            waybillNoFinish.setProjectId(waybill.getProjectId());
            waybillNoFinish.setCustomerManagerId(waybill.getCustomerManagerId());

            waybillNoFinish.setArriveDepotTime(waybill.getArriveDepotTime());
            // 大客户名字
            if (waybill.getCustomerId() != null) {
                com.juma.crm.customer.domain.CustomerInfo customerInfo = customerInfoService.getCrmCustomerInfoByTgmCustomerId(waybill.getCustomerId());
                if (customerInfo != null) {
                    String customerName = customerInfo.getCustomerName();
                    waybillNoFinish.setCustomerName(customerName);
                }
            }
            waybillNoFinish.setDeliveryTime(waybill.getDeliveryTime());
            // 配送点
            WaybillParam waybillParam = waybillParamService.findByWaybillId(waybill.getWaybillId());
            if (waybillParam != null) {
                if (waybillParam.getDistributionPointNo() == null) {
                    waybillNoFinish.setDistrbutionPointNo(waybillReceiveAddressService.findNumByWaybillId(waybill.getWaybillId()));
                } else {
                    waybillNoFinish.setDistrbutionPointNo(waybillParam.getDistributionPointNo());
                }
            } else {
                waybillNoFinish.setDistrbutionPointNo(waybillReceiveAddressService.findNumByWaybillId(waybill.getWaybillId()));
            }
            // 司机名字
            if (waybill.getDriverId() != null) {
                Driver driver = driverService.getDriver(waybill.getDriverId());
                if (driver != null) {
                    waybillNoFinish.setTruckId(waybill.getTruckId());
                    waybillNoFinish.setDriverName(driver.getNickname());
                }
            }
            waybillNoFinish.setEstimateDistance(waybill.getEstimateDistance());
            TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), null);
            if (truckRequire != null) {
                waybillNoFinish.setGoodsType(truckRequire.getGoodsType());
            }
            waybillNoFinish.setPlanDeliveryTime(waybill.getPlanDeliveryTime());
            // 车牌号
            if (waybill.getTruckId() != null) {
                Truck truck = truckService.getTruck(waybill.getTruckId());
                if (truck != null) {
                    waybillNoFinish.setPlateNumber(truck.getPlateNumber());
                    waybillNoFinish.setEstimateFinishTime(truck.getEstimateFinishTime());
                }
            }
            waybillNoFinish.setWaybillId(waybill.getWaybillId());
            waybillNoFinish.setWaybillNo(waybill.getWaybillNo());

            results.add(waybillNoFinish);
        }
        return new Page<WaybillNoFinish>(pageCondition.getPageNo(), pageCondition.getPageSize(), results.size(), results);
    }

    @Override
    public Page<WaybillBo> findToAutoMatchWaybillList(PageCondition condition, LoginEmployee loginEmployee) {
        List<WaybillBo> result = new ArrayList<WaybillBo>();
        List<WaybillBo> rows = new ArrayList<WaybillBo>();
        int total = 0;
        // 先在本地缓存获取
        log.info("findToAutoMatchWaybillList start....");
        Object object = redisClient.get(Constants.WAYBILL_AUTO_MATCH_LIST);
        log.info("findToAutoMatchWaybillList 本地获取object:{}", object == null ? "本地没有存取值" : object);
        if (null != object) {
            rows = JSON.parseArray(object.toString(), WaybillBo.class);
        }
        if (CollectionUtils.isEmpty(rows)) {
            // 获取（其他系统）
            Object obj = redisClient.get(Constants.TRUCK_MATCH_RESULT);
            log.info("findToAutoMatchWaybillList 其他系统返回obj:{}", obj == null ? "没有计算结果" : obj.toString());
            if (null != obj && obj.toString().length() != 0) {
                log.info("findToAutoMatchWaybillList 其他系统返回值:{}", obj.toString());
                WaybillResponse waybillResponse = JSON.parseObject(obj.toString(), WaybillResponse.class);
                // 处理匹配成功的运单信息
                List<WaybillBo> distributedOrderResult = buildDistributedOrder(waybillResponse.getDistributedOrders());
                // 处理匹配失败的运单信息
                List<WaybillBo> filteredOrderResult = buildFilteredOrder(waybillResponse.getFilteredOrders());
                // 处理未匹配的运单信息
                List<WaybillBo> unDistributedOrderResult = buildUnDistributedOrder(waybillResponse.getUnDistributedOrderIds());
                rows.addAll(distributedOrderResult);
                rows.addAll(filteredOrderResult);
                rows.addAll(unDistributedOrderResult);
                // 将结果放入本地缓存
                redisClient.set(Constants.WAYBILL_AUTO_MATCH_LIST, JSON.toJSONString(rows), (2 * 60 * 60));
                redisClient.set(Constants.WAYBILL_AUTO_MATCH_DATE, System.currentTimeMillis(), (2 * 60 * 60));
            }
        }
        Map<String, Object> filters = condition.getFilters();
        if (null != filters && !filters.isEmpty()) {
            Object areaCodeObj = filters.get("areaCodeList");
            Object matchStatusObj = filters.get("matchStatus");
            Object plateNumberObj = filters.get("plateNumber");
            // 有部门选项
            if (null != areaCodeObj) {
                List<String> areaCodeList = (List<String>) areaCodeObj;
                if (null != matchStatusObj && null != plateNumberObj) {
                    for (WaybillBo waybillBo : rows) {
                        Waybill waybill = waybillBo.getWaybill();
                        Truck truck = waybillBo.getTruck();
                        String plateNumber = truck.getPlateNumber();
                        Integer matchStatus = waybillBo.getMatchStatus();
                        if (matchAreaCode(areaCodeList, waybill.getAreaCode())
                                && plateNumber.equals(plateNumberObj.toString())
                                && matchStatus.equals(BaseUtil.strToNum(matchStatusObj.toString()))) {
                            result.add(waybillBo);
                        }
                    }
                } else if (null != matchStatusObj) {
                    for (WaybillBo waybillBo : rows) {
                        Waybill waybill = waybillBo.getWaybill();
                        Integer matchStatus = waybillBo.getMatchStatus();
                        if (matchAreaCode(areaCodeList, waybill.getAreaCode())
                                && matchStatus.equals(BaseUtil.strToNum(matchStatusObj.toString()))) {
                            result.add(waybillBo);
                        }
                    }
                } else if (null != plateNumberObj) {
                    for (WaybillBo waybillBo : rows) {
                        Waybill waybill = waybillBo.getWaybill();
                        Truck truck = waybillBo.getTruck();
                        String plateNumber = truck.getPlateNumber();
                        if (matchAreaCode(areaCodeList, waybill.getAreaCode())
                                && plateNumber.equals(plateNumberObj.toString())) {
                            result.add(waybillBo);
                        }
                    }
                } else {
                    for (WaybillBo waybillBo : rows) {
                        Waybill waybill = waybillBo.getWaybill();
                        if (matchAreaCode(areaCodeList, waybill.getAreaCode())) {
                            result.add(waybillBo);
                        }
                    }
                }
            } else {
                if (null != matchStatusObj && null != plateNumberObj) {
                    for (WaybillBo waybillBo : rows) {
                        Truck truck = waybillBo.getTruck();
                        String plateNumber = truck.getPlateNumber();
                        Integer matchStatus = waybillBo.getMatchStatus();
                        if (plateNumber.equals(plateNumberObj.toString())
                                && matchStatus.equals(BaseUtil.strToNum(matchStatusObj.toString()))) {
                            result.add(waybillBo);
                        }
                    }
                } else if (null != matchStatusObj) {
                    for (WaybillBo waybillBo : rows) {
                        Integer matchStatus = waybillBo.getMatchStatus();
                        if (matchStatus.equals(BaseUtil.strToNum(matchStatusObj.toString()))) {
                            result.add(waybillBo);
                        }
                    }
                } else if (null != plateNumberObj) {
                    for (WaybillBo waybillBo : rows) {
                        Truck truck = waybillBo.getTruck();
                        String plateNumber = truck.getPlateNumber();
                        if (plateNumber.equals(plateNumberObj.toString())) {
                            result.add(waybillBo);
                        }
                    }
                } else {
                    result = rows;
                }
            }
        }
        total = result.size();
        log.info("findToAutoMatchWaybillList 结果result:{}", result.toArray());
        return new Page<WaybillBo>(1, total, total, result);
    }

    // 匹配业务区域
    private boolean matchAreaCode(List<String> areaCodeList, String areaCode) {
        if (areaCodeList.isEmpty() || StringUtils.isBlank(areaCode)) {
            return false;
        }

        for (String code : areaCodeList) {
            if (areaCode.startsWith(code)) {
                return true;
            }
        }

        return false;
    }

    // 处理匹配成功的运单信息
    private List<WaybillBo> buildDistributedOrder(List<DistributedOrder> distributedOrders) {
        List<WaybillBo> result = new ArrayList<WaybillBo>();
        if (CollectionUtils.isNotEmpty(distributedOrders)) {
            for (DistributedOrder distributedOrder : distributedOrders) {
                WaybillBo bo = new WaybillBo();
                bo.setMatchStatus(1);
                // 获取运单信息
                Waybill waybill = findByWaybillNo(distributedOrder.getOrderId());
                if (null != waybill) {
                    bo.setWaybillId(waybill.getWaybillId());
                    waybill.setIsSubmitMatch(0);
                    waybill.setFlightId(distributedOrder.getFlightId());
                    bo.setWaybill(waybill);
                    // 获取车辆信息
                    Truck truck = truckService.findTruckByPlateNumber(distributedOrder.getPlateNumber());
                    if (null != truck) {
                        bo.setTruck(truck);
                        // 获取司机信息
                        com.juma.vms.driver.domain.Driver vmsDriver = vmsService.loadByDriverId(waybill.getDriverId());
                        if (null != vmsDriver) {
                            bo.setDriver(driverService.getDriver(vmsDriver.getDriverId()));
                        }
                    }
                    buildManageAndTruckRequire(bo, waybill);
                    result.add(bo);
                }
            }
        }
        return result;
    }

    // 处理匹配失败的运单信息
    private List<WaybillBo> buildFilteredOrder(List<FilteredOrder> filteredOrders) {
        List<WaybillBo> result = new ArrayList<WaybillBo>();
        if (CollectionUtils.isNotEmpty(filteredOrders)) {
            for (FilteredOrder filteredOrder : filteredOrders) {
                WaybillBo bo = new WaybillBo();
                bo.setMatchStatus(2);
                // 获取运单信息
                Waybill waybill = findByWaybillNo(filteredOrder.getOrderId());
                if (null != waybill) {
                    bo.setWaybillId(waybill.getWaybillId());
                    waybill.setIsSubmitMatch(0);
                    bo.setWaybill(waybill);
                    bo.setFilterReason(filteredOrder.getFilterReason());
                    buildManageAndTruckRequire(bo, waybill);
                    result.add(bo);
                }
            }
        }
        return result;
    }

    // 处理未匹配的运单信息
    private List<WaybillBo> buildUnDistributedOrder(List<String> unDistributedOrderIds) {
        List<WaybillBo> result = new ArrayList<WaybillBo>();
        if (CollectionUtils.isNotEmpty(unDistributedOrderIds)) {
            for (String str : unDistributedOrderIds) {
                WaybillBo bo = new WaybillBo();
                bo.setMatchStatus(2);
                // 获取运单信息
                Waybill waybill = findByWaybillNo(str);
                if (null != waybill) {
                    bo.setWaybillId(waybill.getWaybillId());
                    waybill.setIsSubmitMatch(0);
                    bo.setWaybill(waybill);
                    buildManageAndTruckRequire(bo, waybill);
                    result.add(bo);
                }
            }
        }
        return result;
    }
    
    private void buildManageAndTruckRequire(WaybillBo bo, Waybill waybill) {
        // 获取客户信息
        try {
            Integer customerId = waybill.getCustomerId();
            if (null != customerId) {
                CustomerInfo info = customerInfoService.findCusInfoById(customerId);
                if (null != info) {
                    Integer crmCustomerId = info.getCrmCustomerId();
                    if (null != crmCustomerId) {
                        com.juma.crm.customer.domain.CustomerInfo customerInfo = cmsCustomerInfoService.findIgnoreDelete(crmCustomerId);
                        bo.getWaybill().setCustomerName(customerInfo.getCustomerName());
                        // 获取客户经理
                        Integer userId = customerInfo.getUserId();
                        if (null != userId) {
                            User user = userService.loadUser(userId);
                            bo.setCustomerManageName(user.getName());
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        // 获取货物类型
        bo.setTruckRequire(truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), null));
    }

    // 根据运单号获取
    private Waybill findByWaybillNo(String waybillNo) {
        if (StringUtils.isBlank(waybillNo)) {
            return null;
        }
        Waybill example = new Waybill();
        example.setWaybillNo(waybillNo);
        List<Waybill> list = waybillDao.findByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public ToAutoMatchWaybill findToAutoMatchWaybillPlan(List<String> areaCodeList, LoginUser loginUser) {
        ToAutoMatchWaybill result = new ToAutoMatchWaybill();
        List<WaybillPlan> rows = new ArrayList<WaybillPlan>();
        List<Waybill> results = waybillDao.findWaybillPlan();//预约单
        for (Waybill waybill : results) {
            WaybillPlan plan = new WaybillPlan();
            plan.setWaybillNo(waybill.getWaybillNo());
            plan.setCustomerId(waybill.getCustomerId());
            CustomerInfo customerInfo = customerInfoService.findCusInfoById(waybill.getCustomerId());
            if(customerInfo != null) {
                plan.setCustomerName(customerInfo.getCustomerName());
            }
            if (waybill.getCustomerManagerId() != null) {
               EmployeeInfo employee =  waybillCommonService.loadEmployeeInfo(waybill.getCustomerManagerId());
               if(employee != null) {
                   plan.setEmployeeId(employee.getEmployeeId());
                   plan.setEmployeeName(employee.getName());
               }
            }
            
            plan.setPlanDeliveryTime(waybill.getPlanDeliveryTime());
            plan.setEstimateFreight(waybill.getEstimateFreight());
            plan.setCalculatedFreight(waybill.getCalculatedFreight());
            plan.setEstimateDistance(waybill.getEstimateDistance());
            plan.setDeliveryAddr(waybillDeliveryAddressService.findAllByWaybillId(waybill.getWaybillId()));
            plan.setReceiveAddr(waybillReceiveAddressService.findAllByWaybillId(waybill.getWaybillId()));

            // 用车要求
            TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), null);
            TruckType truckType = truckTypeService.getTruckType(truckRequire.getTruckTypeId());
            WaybillTruckRequire require = new WaybillTruckRequire();
            require.setTruckClassName(truckTypeService.findVehicleBoxTypeName(truckType.getVehicleBoxType()));
            require.setGoodsType(truckRequire.getGoodsType());
            require.setGoodsVolume(truckRequire.getGoodsVolume());
            require.setGoodsWeight(truckRequire.getGoodsWeight());
            require.setTruckLengthName(truckTypeService.findVehicleBoxLengthName(truckType.getTruckLengthId()));
            String remark = truckRequire.getRemark();

            int needWithBody = 3;
            if (remark != null) {
                if (remark.contains("宽")) {
                    needWithBody = 1;
                } else if (remark.contains("窄")) {
                    needWithBody = 2;
                }
            }
            require.setNeedWithBody(needWithBody);

            String additionalFunctionIds = truckRequire.getAdditionalFunctionIds();
            if (StringUtils.isNotBlank(additionalFunctionIds)) {
                if (additionalFunctionIds.contains("9")) {
                    require.setIsEntryLicense(true);
                }
            }
            plan.setTruckRequire(require);
            rows.add(plan);
            result.setOrders(rows);// 设置待自动匹配的运单
        }

        
        //可用运力
        /**
         * 1. 业务区域
         * 2. 预约时间段 第二天 00:00:00-23:59:59
         */
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        Date startTime = cal.getTime();
        
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        Date endTime = cal.getTime();
        
        PageCondition condition = new PageCondition();
        condition.setPageNo(1);
        condition.setPageSize(Integer.MAX_VALUE);
        condition.getFilters().put("areaCodeLikeList", areaCodeList);
        condition.getFilters().put("startTime", startTime);
        condition.getFilters().put("endTime", endTime);
        /*Page<FlightBo> page = amsServiceV2.getAvailableFlightByPage(condition, loginUser);
       log.info("AMS flight condition:{}.",condition.getFilters());
       log.info("AMS flight result:{}.",JSON.toJSON(page.getResults()));
        for (FlightBo flightBo : page.getResults()) {
            VehicleBo vehicleBo = this.buildVehicle(flightBo.getVehicleId(), loginUser);
            vehicleBo.setFlightId(flightBo.getId());
            vehicleBo.setDriver(amsServiceV2.getDriverById(flightBo.getDriverId()));
            result.addVehicle(vehicleBo);
        }*/

        return result;
    }

    // 构建车辆信息
    /*private VehicleBo buildVehicle(Integer vehicleId, LoginUser loginUser) {
        VehicleQueryConditionDTO dto = new VehicleQueryConditionDTO();
        dto.setVehicleId(vehicleId);
        dto.setTenantId(loginUser.getTenantId());
        dto.setTenantCode(loginUser.getTenantCode());
        com.juma.server.vm.domain1.bo.VehicleBo bo = amsServiceV2.getVehicleBoById(dto);
        if (null == bo || null == bo.getVehicleExtend()) {
            return null;
        }
        
        VehicleBo vehicleBo = new VehicleBo();
        vehicleBo.setVehicleId(bo.getVehicleId());
        vehicleBo.setPlateNumber(bo.getVehicleExtend().getPlateNumber());
        vehicleBo.setLoadVolume(bo.getVehicleExtend().getLoadVolume());
        vehicleBo.setMaxLoadCapacity(bo.getVehicleExtend().getMaxLoadCapacity());
        vehicleBo.setVehicleBoxType(bo.getVehicleExtend().getVehicleBoxType());
        vehicleBo.setGoCityLicenseType(bo.getVehicleExtend().getGoCityLicenseType());
        vehicleBo.setBoxLength(bo.getVehicleExtend().getBoxLength());
        return vehicleBo;
    }*/

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        Date startTime = cal.getTime();
        System.out.println(startTime);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        Date endTime = cal.getTime();
        System.out.println(endTime);
    }

    @Override
    public void updateWaybillUnbundling(int waybillId, String unbundlingReason, LoginUser loginUser) throws BusinessException {
        Waybill waybill = new Waybill();
        waybill.setIsSubmitMatch(1);
        waybill.setWaybillUnbundlingReason(unbundlingReason);
        waybill.setLastUpdateUserId(loginUser.getUserId());
        waybillDao.update(waybill);
    }
    
}
