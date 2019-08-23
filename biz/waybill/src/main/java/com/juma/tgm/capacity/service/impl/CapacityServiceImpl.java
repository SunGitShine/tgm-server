package com.juma.tgm.capacity.service.impl;

import com.giants.common.tools.Page;
import com.juma.auth.user.domain.LoginUser;
import com.juma.server.vm.domain1.bo.VehicleBo;
import com.juma.tgm.base.domain.RegionBo;
import com.juma.tgm.capacity.domian.vo.CapacityFilter;
import com.juma.tgm.capacity.domian.vo.CapacityMismatchReason;
import com.juma.tgm.capacity.domian.vo.CapacityQuery;
import com.juma.tgm.capacity.service.CapacityService;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.flight.domain.bo.RunningWaybill;
import com.juma.tgm.region.service.RegionTgmService;
import com.juma.tgm.tools.service.AmsCommonService;
import com.juma.tgm.tools.service.AuthCommonService;
import com.juma.tgm.tools.service.BusinessAreaCommonService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.vms.driver.domain.Driver;
import com.juma.vms.transport.domain.CapacityPool;
import com.juma.vms.transport.external.CapacityPoolExternalQuery;
import com.juma.vms.truck.domain.Truck;
import com.juma.vms.truck.domain.TruckTenant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class CapacityServiceImpl implements CapacityService {

    @Resource
    private VmsCommonService vmsCommonService;
    @Resource
    private AmsCommonService amsCommonService;
    @Resource
    private AuthCommonService authCommonService;
    @Resource
    private RegionTgmService regionTgmService;
    @Resource
    private WaybillService waybillService;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private BusinessAreaCommonService businessAreaCommonService;

    @Override
    public Page<CapacityQuery> searchCapacity(QueryCond<CapacityFilter> queryCond, LoginUser loginUser) {
        List<CapacityQuery> result = new ArrayList<>();
        queryCond.setOrderBy(CapacityPool.Column.truckId.desc());
        Page<CapacityPoolExternalQuery> page = vmsCommonService.seachCapacity(queryCond, loginUser);

        for (CapacityPoolExternalQuery q : page.getResults()) {
            CapacityQuery c = new CapacityQuery();
            BeanUtils.copyProperties(q, c);
            buildTruck(c, q.getStatus(), loginUser);
            buildDriver(c, q.getStatus(), loginUser);

            // 兼容若VMS数据异常
            if (CollectionUtils.isEmpty(c.getListCapacityMismatchReason()) && !q.getStatus()) {
                c.getListCapacityMismatchReason().add(new CapacityMismatchReason("车牌号", c.getPlateNumber(), "运力状态无效"));
            }

            // 进行中的运单
            buildRunningWaybillList(c, loginUser.getTenantId());
            result.add(c);
        }
        return new Page<>(page.getPageNo(), page.getPageSize(), page.getTotal(), result);
    }

    // 司机信息
    private void buildDriver(CapacityQuery c, Boolean capacityStatus, LoginUser loginUser) {
        if (null == c.getDriverId()) {
            c.getListCapacityMismatchReason().add(new CapacityMismatchReason("车牌号", c.getPlateNumber(), "没有设置司机"));
            return;
        }

        Driver driver = vmsCommonService.loadDriverByDriverId(c.getDriverId());
        if (null == driver || null == driver.getAmsDriverId()) {
            return;
        }

        com.juma.server.vm.domain.Driver amsDriver = amsCommonService.findDriver(driver.getAmsDriverId(), loginUser);
        if (null == amsDriver) {
            return;
        }

        // 放车区域
        String regionName = regionTgmService.findSimpleRegionName(amsDriver.getParkRegionCode(),
                RegionBo.SimpleRegionKey.CITY_TOWN);
        c.setParkingArea((StringUtils.isBlank(regionName) ? "" : regionName)
                + (StringUtils.isBlank(amsDriver.getParkAddress()) ? "" : ("(" + amsDriver.getParkAddress() + ")")));

        if (null != capacityStatus && capacityStatus) {
            return;
        }
//
//        if (NumberUtils.compare(driver.getStatus(), DriverStatusEnum.STOP_WORK.getCode()) == 0) {
//            c.getListCapacityMismatchReason().add(new CapacityMismatchReason("司机", c.getDriverName(), DriverStatusEnum.STOP_WORK.getDesc()));
//        }
    }

    // 车辆信息
    private void buildTruck(CapacityQuery c, Boolean capacityStatus, LoginUser loginUser) {
        c.setGoCityLicenseTypeName(authCommonService.getEntryLicenseCnName(c.getGoCityLicenseType()));
        if (null == c.getVendorId()) {
            c.getListCapacityMismatchReason().add(new CapacityMismatchReason("车牌号", c.getPlateNumber(), "没有设置承运商"));
            return;
        }

        Truck truck = vmsCommonService.loadTruckByTruckId(c.getTruckId());
        if (null == truck) {
            return;
        }

        TruckTenant truckTenant = vmsCommonService.loadTruckTenantByTruckId(c.getTruckId(), loginUser);
        if (null != truckTenant) {
            c.setAreaCode(truckTenant.getAreaCode());
            c.setAreaName(businessAreaCommonService.loadAreaName(truckTenant.getAreaCode(), loginUser));
        }

        if (null == truck.getVehicleId()) {
            return;
        }


        VehicleBo vehicleBo = amsCommonService.findVehicle(truck.getVehicleId(), loginUser);
        if (null == vehicleBo) {
            return;
        }

        c.setMaxLoadCapacity(vehicleBo.getVehicleExtend().getMaxLoadCapacity());
        c.setLoadVolume(vehicleBo.getVehicleExtend().getLoadVolume());

        if (null != capacityStatus && capacityStatus) {
            return;
        }

//        if (NumberUtils.compare(truck.getStatus(), TruckStatusEnum.ENABLE.getCode()) == 0) {
//            c.getListCapacityMismatchReason().add(new CapacityMismatchReason("车牌号", c.getPlateNumber(), "没有启用"));
//        }
    }

    // 根据车辆获取进行中的运单集合
    private void buildRunningWaybillList(CapacityQuery c, Integer tenantId) {
        List<RunningWaybill> listRunningWaybill = new ArrayList<RunningWaybill>();
        if (null == c.getTruckId()) {
            return;
        }

        if (null == c.getDriverId()) {
            return;
        }

        List<Waybill> list = waybillService.findRunningWaybillByTruckId(c.getTruckId());
        StringBuffer runningWaybillDescSf = new StringBuffer("");
        for (Waybill waybill : list) {
            if (!c.getDriverId().equals(waybill.getDriverId())) {
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
            runningWaybillDescSf.append(runningWaybill.getDesc()).append("，");
        }
        c.setListRunningWaybill(listRunningWaybill);
        if (runningWaybillDescSf.length() > 0) {
            c.setRunningWaybillDesc(runningWaybillDescSf.deleteCharAt(runningWaybillDescSf.length() - 1).toString());
        }
    }
}
