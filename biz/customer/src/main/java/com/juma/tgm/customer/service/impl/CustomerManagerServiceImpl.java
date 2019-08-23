package com.juma.tgm.customer.service.impl;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageQueryCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.crm.customer.domain.Customer4TmsBo;
import com.juma.crm.support.service.Crm4TmsService;
import com.juma.server.vm.domain1.VehicleExtraFunction;
import com.juma.tgm.base.domain.RegionBo;
import com.juma.tgm.customer.domain.TruckCustomer;
import com.juma.tgm.customer.domain.TruckCustomerBo;
import com.juma.tgm.customer.service.CustomerManagerService;
import com.juma.tgm.customerManager.domain.vo.TruckFleetParamVo;
import com.juma.tgm.region.service.RegionTgmService;
import com.juma.tgm.tools.service.AmsCommonService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.domain.TruckFleet;
import com.juma.tgm.truck.domain.TruckFleetTruck;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.truck.domain.bo.DriverTruckInfoBo;
import com.juma.tgm.truck.domain.bo.DriverTruckInfoBo.TruckNoMatchType;
import com.juma.tgm.truck.domain.bo.LogisticsProductBo;
import com.juma.tgm.truck.domain.vo.TruckFleetDriverRelationVo;
import com.juma.tgm.truck.domain.vo.TruckFleetQueryVo;
import com.juma.tgm.truck.service.TruckFleetService;
import com.juma.tgm.truck.service.TruckFleetTruckService;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.vms.transport.domain.CapacityPool;
import com.juma.vms.truck.domain.TruckTenant;
import com.juma.vms.truck.enumeration.TruckStatusEnum;
import com.juma.vms.vendor.external.VendorTenantExternal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Libin.Wei
 * @version 1.0.0
 * @ClassName CustomerManagerServiceImpl.java
 * @Description 请填写注释...
 * @Date 2017年5月24日 上午11:46:27
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Deprecated
@Service
public class CustomerManagerServiceImpl implements CustomerManagerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerManagerServiceImpl.class);

    @Resource
    private TruckService truckService;
    @Resource
    private TruckFleetService truckFleetService;
    @Resource
    private TruckFleetTruckService truckFleetTruckService;
    @Resource
    private TruckTypeService truckTypeService;
    @Resource
    private WaybillService waybillService;
    @Resource
    private TruckRequireService truckRequireService;
    @Resource
    private UserService userService;
    @Resource
    private RegionTgmService regionTgmService;
    @Resource
    private VmsCommonService vmsCommonService;
    @Resource
    private Crm4TmsService crm4TmsService;
    @Resource
    private AmsCommonService amsCommonService;

    @Override
    public Page<DriverTruckInfoBo> findTruckFleetBy(Integer waybillId, Integer pageNo, Integer pageSize, LoginUser loginUser) {
        List<DriverTruckInfoBo> driverTruckInfoBos = new ArrayList<>();
        List<Integer> truckFleetIds = new ArrayList<Integer>();

        // 车队
        List<TruckFleet> listTruckFleet = truckFleetService.listTruckFleetByUserId(loginUser);
        for (TruckFleet truckFleet : listTruckFleet) {
            truckFleetIds.add(truckFleet.getTruckFleetId());
        }

        // 分页查询车辆信息
        Page<TruckFleetTruck> page = truckFleetTruckService.listByTruckFleetIds(truckFleetIds, pageNo, pageSize);

        for (TruckFleetTruck truckFleetTruckTmp : page.getResults()) {

            Integer truckId = truckFleetTruckTmp.getTruckId();

            // 货车对象
            com.juma.vms.truck.domain.Truck truck = vmsCommonService.loadTruckByTruckId(truckId);
            if (null == truck) {
                continue;
            }

            com.juma.vms.driver.domain.Driver driver = null;
            CapacityPool capacityPool = vmsCommonService.loadCapacityByTruckId(truckId, loginUser);
            if  (null != capacityPool) {
                driver = vmsCommonService.loadDriverByDriverId(capacityPool.getDriverId());
            }

            Waybill waybill = null;
            TruckRequire truckRequire = null;
            if (waybillId != null) {
                waybill = waybillService.getWaybill(waybillId);
                truckRequire = truckRequireService.findTruckRequireByWaybillId(waybillId, null);
            }

            //组装司机车辆信息
            DriverTruckInfoBo driverTruckInfoBo = this.buildDriverTruckInfoBo(truck, driver, capacityPool, waybill, truckRequire, loginUser);

            driverTruckInfoBos.add(driverTruckInfoBo);
        }

        return new Page<DriverTruckInfoBo>(pageNo, pageSize, page.getTotal(), driverTruckInfoBos);
    }

    /**
     * 组装司机车辆信息
     *
     * @param truck
     * @param driver
     * @param loginUser
     * @return
     */
    private DriverTruckInfoBo buildDriverTruckInfoBo(com.juma.vms.truck.domain.Truck truck, com.juma.vms.driver.domain.Driver driver,
                                                     CapacityPool capacityPool, Waybill waybill, TruckRequire truckRequire, LoginUser loginUser) {
        DriverTruckInfoBo driverTruckInfoBo = this.buildDriverTruckInfo(truck, driver, capacityPool, loginUser);

        // 标注不满足的原因
        this.buildTruckNoMatchType(waybill, truckRequire, truck, driver, capacityPool, driverTruckInfoBo, loginUser);

        return driverTruckInfoBo;
    }

    @Override
    public DriverTruckInfoBo buildDriverTruckInfo(com.juma.vms.truck.domain.Truck truck, com.juma.vms.driver.domain.Driver driver, CapacityPool capacityPoo, LoginUser loginUser) throws BusinessException {
        DriverTruckInfoBo driverTruckInfoBo = new DriverTruckInfoBo();

        driverTruckInfoBo.setTruckId(truck.getTruckId());
        driverTruckInfoBo.setPlateNumber(truck.getPlateNumber());
        driverTruckInfoBo.setVehicleBoxLength(truck.getVehicleBoxLength());
        driverTruckInfoBo.setVehicleBoxType(truck.getVehicleBoxType());
        driverTruckInfoBo.setTruckInfo(truckService.findTruckInfoStrByTruckId(truck.getTruckId(), loginUser));
        driverTruckInfoBo.setTruckTypeName(truckTypeService.findTruckTypeNameBy(truck.getVehicleBoxType(), truck.getVehicleBoxLength()));

        // 附加功能
        driverTruckInfoBo.setAffName(this.getVehicleExtraFunctionString(truck, loginUser));
        TruckType truckType = truckTypeService.findByBoxAndLength(truck.getVehicleBoxType(), truck.getVehicleBoxLength(), loginUser.getTenantId());
        if(truckType != null){
            driverTruckInfoBo.setTruckTypeId(truckType.getTruckTypeId());

        }
        if (driver != null) {
            driverTruckInfoBo.setIsAcceptAllocateOrders(driver.getIsReceiveWaybill() == null ? 0 : (driver.getIsReceiveWaybill() ? 1 : 0));
            driverTruckInfoBo.setDeriverId(driver.getDriverId());
            driverTruckInfoBo.setDervicerName(driver.getName());
            driverTruckInfoBo.setContactPhone(driver.getPhone());
            driverTruckInfoBo.setHeader(driver.getIcon());
        }

        return driverTruckInfoBo;
    }

    /**
     * 获取车辆附加功能字符串
     *
     * @param truck
     * @param loginUser
     * @return
     */
    private String getVehicleExtraFunctionString(com.juma.vms.truck.domain.Truck truck, LoginUser loginUser) {
        if (null == truck.getVehicleId()) {
            return truck.getIsTailBoard() ? "尾板" : null;
        }

        List<VehicleExtraFunction> listExtra = amsCommonService.listExtraByVehicleId(truck.getVehicleId(), loginUser);
        StringBuilder sb = new StringBuilder("");
        if (CollectionUtils.isNotEmpty(listExtra)) {
            for (VehicleExtraFunction extraFunction : listExtra) {
                sb.append(extraFunction.getExtraFunctionName());
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    /**
     * 组装不满足的原因
     */
    private void buildTruckNoMatchType(Waybill waybill, TruckRequire truckRequire, com.juma.vms.truck.domain.Truck truck,
                                       com.juma.vms.driver.domain.Driver driver, CapacityPool capacityPool,
                                       DriverTruckInfoBo driverTruckInfoBo, LoginUser loginUser) {
        if (waybill == null) {
            return;
        }

        // 箱型不符
        if (truckRequire != null) {
            if (truckRequire.getTruckTypeId() != null) {
                // 通过truckRequire的truckTypeId获取箱型，判断箱型是否符合
                TruckType truckType = truckTypeService.getTruckType(truckRequire.getTruckTypeId());

                if (truckType == null) {
                    driverTruckInfoBo
                        .setTruckNoMatchTypeStr(DriverTruckInfoBo.TruckNoMatchType.TRUCK_TYPE_NO_MATCH.getDesc());
                    return;
                }

                if (truck.getVehicleBoxType() == null
                    || NumberUtils.compare(truck.getVehicleBoxType(), truckType.getVehicleBoxType()) != 0) {
                    driverTruckInfoBo
                        .setTruckNoMatchTypeStr(DriverTruckInfoBo.TruckNoMatchType.TRUCK_TYPE_NO_MATCH.getDesc());
                    return;
                }
            }

            // 入城证不符
            String funcIds = truckRequire.getAdditionalFunctionIds();
            if (StringUtils.isNotBlank(funcIds)) {
                String ids[] = funcIds.split(",");
                if (ArrayUtils.contains(ids, "9")
                    && (truck.getGoCityLicenseType() == null || NumberUtils.compare(truck.getGoCityLicenseType(), 0) == 0)) {
                    driverTruckInfoBo.setTruckNoMatchTypeStr(
                        DriverTruckInfoBo.TruckNoMatchType.ENTRY_LICENSE_NO_MATCH.getDesc());
                    return;
                }
            }
        }

        //没有绑定司机
        if (driver == null) {
            driverTruckInfoBo.setTruckNoMatchTypeStr(DriverTruckInfoBo.TruckNoMatchType.NO_BOUND_DRIVER.getDesc());
            return;
        }

        //没有绑定承运商
        if (null != capacityPool && null ==capacityPool.getVendorId()) {
            driverTruckInfoBo.setTruckNoMatchTypeStr(DriverTruckInfoBo.TruckNoMatchType.NO_BOUND_VENDOR.getDesc());
            return;
        }

        // 车辆是否停运
        TruckTenant truckTenant = vmsCommonService.loadTruckTenantByTruckId(truck.getTruckId(), loginUser);
        if (null == truckTenant || null == truckTenant.getStatus()
                || !truckTenant.getStatus().equals(TruckStatusEnum.ENABLE.getCode())) {
            driverTruckInfoBo.setTruckNoMatchTypeStr(DriverTruckInfoBo.TruckNoMatchType.STOP_SERVICE.getDesc());
            return;
        }

        // 承运商停用
        VendorTenantExternal vendorTenantExternal = vmsCommonService
            .loadVendorTenantByVendorId(capacityPool.getVendorId(), loginUser);
        if (null != vendorTenantExternal && null != vendorTenantExternal.getEnable() && !vendorTenantExternal.getEnable()) {
            driverTruckInfoBo.setTruckNoMatchTypeStr(TruckNoMatchType.VENDOR_DISABLED.getDesc());
            return;
        }

    }

    @Override
    public TruckCustomerBo getCustomerManagerForClientUserCenter(LoginEmployee loginEmployee) {
        TruckCustomerBo customerManager = new TruckCustomerBo();
        TruckCustomer truckCustomer = new TruckCustomer();
        customerManager.setTruckCustomer(truckCustomer);

        User user = userService.findUser(loginEmployee.getUserId());
        truckCustomer.setNickname(user.getName());
        truckCustomer.setContactPhone(user.getMobileNumber());
        truckCustomer.setHeadPortrait(user.getIcon());

        customerManager.setCreateTime(user.getCreateTime());
        customerManager.setCreateDate(DateFormatUtils.format(user.getCreateTime(), "yyyy-MM-dd"));
        String regionName = regionTgmService.findSimpleRegionName(user.getLiveRegionCode(),
            RegionBo.SimpleRegionKey.CITY_TOWN);
        customerManager.setRegion(regionName);
        // 是否有车队
        int count = truckFleetService.countTruckFleet(loginEmployee);
        customerManager.setHasTruckFleet(count == 0 ? false : true);
        return customerManager;
    }

    @Override
    public Page<DriverTruckInfoBo> findTruckFleetForFilialeProject(Integer projectId, Integer pageNo, Integer pageSize,
                                                                   LoginUser loginUser) throws BusinessException {
        // 属于经纪人的车队
        // 分页获取
        return this.getManagerTruckFleetTrucks(pageNo, pageSize, loginUser);
    }

    /**
     * 通过当前登录人获取车队信息
     *
     * @param loginUser
     * @return
     */
    public Page<DriverTruckInfoBo> getManagerTruckFleetTrucks(Integer pageNo, Integer pageSize, LoginUser loginUser) {
        List<TruckFleet> listTruckFleet = truckFleetService.listTruckFleetByUserId(loginUser);
        if (CollectionUtils.isEmpty(listTruckFleet))
            return new Page<DriverTruckInfoBo>(pageNo, pageSize, 0, new HashSet<DriverTruckInfoBo>());

        List<Integer> truckFleetIdList = new ArrayList<Integer>();
        for (TruckFleet fleet : listTruckFleet) {
            truckFleetIdList.add(fleet.getTruckFleetId());
        }
        // 分页获取
        Page<TruckFleetTruck> page = truckFleetTruckService.listByTruckFleetIds(truckFleetIdList, pageNo, pageSize);

        List<TruckFleetTruck> truckFleetTruckList = (List<TruckFleetTruck>) page.getResults();
        log.info("fleet truck list 2:{}.", StringUtils.join(truckFleetTruckList, ","));
        if (truckFleetTruckList == null)
            return null;
        Set<DriverTruckInfoBo> result = new HashSet<>();
        // 车队中的车
        for (TruckFleetTruck fleetTruck : truckFleetTruckList) {
            DriverTruckInfoBo driverTruckInfoBo = this.getDriverTruck(fleetTruck.getTruckId(), loginUser);
            if (driverTruckInfoBo == null)
                continue;

            result.add(driverTruckInfoBo);
        }
        return new Page<DriverTruckInfoBo>(page.getPageNo(), page.getPageSize(), page.getTotal(), result);
    }

    /**
     * 通过车辆id获取司机车辆信息
     *
     * @param truckId
     * @return
     */
    private DriverTruckInfoBo getDriverTruck(int truckId, LoginUser loginUser) {
        DriverTruckInfoBo driverTruckInfoBo = new DriverTruckInfoBo();

        // 车辆信息
        Truck truck = truckService.getTruck(truckId);
        if (null == truck) {
            return null;
        }

        // 司机信息
        com.juma.vms.driver.domain.Driver driver = vmsCommonService.loadDriverByTruckId(truckId, loginUser);
        if (null == driver) {
            return null;
        }

        driverTruckInfoBo.setTruckId(truck.getTruckId());
        driverTruckInfoBo.setPlateNumber(truck.getPlateNumber());
        driverTruckInfoBo.setTruckStaus(truck.getStatus());
        driverTruckInfoBo.setTruckInfo(truckService.findTruckInfoStrByTruckId(truck.getTruckId(), loginUser));
        driverTruckInfoBo.setDeriverId(driver.getDriverId());
        driverTruckInfoBo.setDervicerName(driver.getName());
        driverTruckInfoBo.setDeriverStatus(0);
        driverTruckInfoBo.setContactPhone(driver.getPhone());
        driverTruckInfoBo.setHeader(driver.getIcon());
        TruckType truckType = truckTypeService.findByBoxAndLength(loginUser.getTenantId(), truck.getVehicleBoxType(),
            truck.getVehicleBoxLength());
        driverTruckInfoBo.setTruckTypeId(truckType == null ? null : truckType.getTruckTypeId());
        driverTruckInfoBo.setVehicleBoxLength(truck.getVehicleBoxLength());
        driverTruckInfoBo.setVehicleBoxType(truck.getVehicleBoxType());

        return driverTruckInfoBo;
    }

    @Override
    public DriverTruckInfoBo getDriverTruckInfo(int truckId, LoginUser loginUser) throws BusinessException {
        return this.getDriverTruck(truckId, loginUser);
    }

    @Override
    public List<DriverTruckInfoBo> findTruckFleetForFixedDemand(TruckFleetParamVo truckFleetParamVo,
                                                                LoginUser loginUser) throws BusinessException {
        if (loginUser == null)
            throw new BusinessException("loginUserNull", "errors.paramCanNotNullWithName", "登录人信息");
        if (truckFleetParamVo == null) {
            truckFleetParamVo = new TruckFleetParamVo();
        }

        // 获取当前客户经理的车队
        Page<DriverTruckInfoBo> page = this.getManagerTruckFleetTrucks(1, Integer.MAX_VALUE, loginUser);
        Set<DriverTruckInfoBo> fleetTruckList = (Set<DriverTruckInfoBo>) page.getResults();
        // 过滤车型
        if (CollectionUtils.isEmpty(fleetTruckList))
            return null;

        if (truckFleetParamVo.getTruckTypeId() == null)
            return new ArrayList<>(fleetTruckList);

        // 获取车型信息
        TruckType truckType = truckTypeService.getTruckType(truckFleetParamVo.getTruckTypeId());
        if (null == truckType) {
            return new ArrayList<>(fleetTruckList);
        }

        List<DriverTruckInfoBo> removeList = new ArrayList<>();
        for (DriverTruckInfoBo driverTruckInfoBo : fleetTruckList) {
            if (null == driverTruckInfoBo.getVehicleBoxLength() || null == driverTruckInfoBo.getVehicleBoxType()) {
                continue;
            }
            if (NumberUtils.compare(driverTruckInfoBo.getVehicleBoxLength(), truckType.getTruckLengthId()) == 0
                && NumberUtils.compare(driverTruckInfoBo.getVehicleBoxType(), truckType.getVehicleBoxType()) == 0)
                continue;
            removeList.add(driverTruckInfoBo);
        }

        fleetTruckList.removeAll(removeList);

        if (CollectionUtils.isEmpty(fleetTruckList))
            return null;

        return new ArrayList<>(fleetTruckList);
    }

    @Override
    public Page<DriverTruckInfoBo> searchTruckFleet(PageQueryCondition<TruckFleetQueryVo> queryCondition, LoginEmployee loginEmployee) throws BusinessException {
        if (queryCondition == null)
            throw new BusinessException("pageQueryConditionNullError", "errors.paramCanNotNullWithName", "查询参数");
        if (loginEmployee == null)
            throw new BusinessException("loginEmployeeNullError", "errors.paramCanNotNullWithName", "登录人");
        TruckFleetQueryVo queryVo = queryCondition.getFilters();

        queryVo.setEmployeeId(loginEmployee.getEmployeeId());

        Page<DriverTruckInfoBo> tempPage = new Page<>(queryCondition.getPageNo(), queryCondition.getPageSize(), 0);

        Page<TruckFleetDriverRelationVo> relationPage = truckFleetService.searchTruckFleet(queryCondition, loginEmployee);

        if (relationPage == null || CollectionUtils.isEmpty(relationPage.getResults())) return tempPage;

        tempPage.setTotal(relationPage.getTotal());

        List<DriverTruckInfoBo> driverTruckInfoBoList = new ArrayList<>();
        //组装数据
        for (TruckFleetDriverRelationVo relationVo : relationPage.getResults()) {
            DriverTruckInfoBo driverTruckInfoBo = this.buildDriverTruckInfoForSearch(relationVo, queryVo, loginEmployee);
            if (driverTruckInfoBo == null) continue;

            driverTruckInfoBoList.add(driverTruckInfoBo);
        }
        tempPage.setResults(driverTruckInfoBoList);

        return tempPage;
    }

    /**
     * 组装车队搜索中的车辆司机信息
     *
     * @param truckFleetDriverRelationVo
     * @param loginUser
     * @return
     */
    private DriverTruckInfoBo buildDriverTruckInfoForSearch(TruckFleetDriverRelationVo truckFleetDriverRelationVo, TruckFleetQueryVo queryVo, LoginUser loginUser) {
        if (truckFleetDriverRelationVo == null) return null;

        //获取车辆信息
        com.juma.vms.truck.domain.Truck truck = vmsCommonService.loadTruckByTruckId(truckFleetDriverRelationVo.getTruckId());
        if (null == truck) {
            return null;
        }

        com.juma.vms.driver.domain.Driver driver = null;
        // 根据车辆获取运力
        CapacityPool capacityPool = vmsCommonService.loadCapacityByTruckId(truck.getTruckId(), loginUser);
        if (null != capacityPool) {
            driver = vmsCommonService.loadDriverByDriverId(capacityPool.getDriverId());
        }

        DriverTruckInfoBo driverTruckInfoBo = this.buildDriverTruckInfoBo(truck, driver, capacityPool, queryVo.getWaybill(), queryVo.getTruckRequire(), loginUser);

        return driverTruckInfoBo;
    }

    @Override
    public List<LogisticsProductBo> findLogisticsProduct(Integer customerId, LoginUser loginUser) throws BusinessException {

        Customer4TmsBo customer4TmsBo = crm4TmsService.findProductAndDepId(customerId, loginUser);
        Map<String, Object> map = customer4TmsBo.getLogisticsProducts();

        List<LogisticsProductBo> logisticsProductBos = new ArrayList<>();
        if(map != null && !map.isEmpty()){
            for (Map.Entry<String, Object> entry : map.entrySet()){

                LogisticsProductBo logisticsProductBo = new LogisticsProductBo();
                logisticsProductBo.setLogisticsCode(entry.getKey());
                logisticsProductBo.setLogisticsName(entry.getValue() + "");
                logisticsProductBos.add(logisticsProductBo);
            }
        }
        return logisticsProductBos;
    }
}
