package com.juma.tgm.tools.service.impl;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.capacity.domian.vo.CapacityFilter;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.vms.driver.domain.Driver;
import com.juma.vms.driver.domain.DriverTenant;
import com.juma.vms.driver.enumeration.RemindSwitchType;
import com.juma.vms.driver.enumeration.RemindSwitchValue;
import com.juma.vms.driver.external.DriverExternalFilter;
import com.juma.vms.external.service.VmsService;
import com.juma.vms.transport.domain.CapacityPool;
import com.juma.vms.transport.external.CapacityPoolExternalQuery;
import com.juma.vms.transport.request.CapacityPoolFilter;
import com.juma.vms.truck.domain.Truck;
import com.juma.vms.truck.domain.TruckTenant;
import com.juma.vms.truck.external.TruckExternalFilter;
import com.juma.vms.vendor.domain.Vendor;
import com.juma.vms.vendor.domain.VendorWhiteList;
import com.juma.vms.vendor.external.VendorTenantExternal;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName VmsCommonServiceImpl.java
 * @Description vmsservice公共类
 * @author Libin.Wei
 * @Date 2018年11月28日 下午2:16:33
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Service
public class VmsCommonServiceImpl implements VmsCommonService {

    @Resource
    private VmsService vmsService;

    @Resource
    private TruckTypeService truckTypeService;

    @Override
    public Vendor loadVendorByVendorId(Integer vendorId) throws BusinessException {
        if (null == vendorId) {
            return null;
        }
        return vmsService.loadByVenorId(vendorId);
    }

    @Override
    public VendorTenantExternal loadVendorTenantByVendorId(Integer vendorId, LoginUser loginUser) throws BusinessException {
        if (null == vendorId || null == loginUser || null == loginUser.getTenantId()) {
            return null;
        }

        try {
            return vmsService.loadVendorTenantByVendorId(vendorId, loginUser);
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                throw e;
            }

            throw new BusinessException("callVmsSystemException", "errors.callVmsSystemException");
        }
    }

    @Override
    public Page<CapacityPool> searchCommonCapacity(QueryCond<CapacityFilter> queryCond, LoginUser loginUser) {
        List<CapacityPool> result = new ArrayList<>();
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new Page<>(queryCond.getPageNo(), queryCond.getPageSize(), 0, result);
        }

        if(queryCond.getFilters() != null && queryCond.getFilters().getTruckTypeId() != null){
            TruckType truckType = null;
            if(queryCond.getFilters().getTruckTypeId() != null){
                //前端传车型id（通过车型id获取箱型和箱长）
                truckType = truckTypeService.getTruckType(queryCond.getFilters().getTruckTypeId());
            }

            if(truckType != null){
                queryCond.getFilters().setVehicleBoxLength(truckType.getTruckLengthId());
                queryCond.getFilters().setVehicleBoxType(truckType.getVehicleBoxType());
            }
        }

        return vmsService.searchCommonCapacity(buildQueryCond(queryCond), loginUser);
    }

    @Override
    public Page<CapacityPoolExternalQuery> seachCapacity(QueryCond<CapacityFilter> queryCond, LoginUser loginUser) {
        List<CapacityPoolExternalQuery> result = new ArrayList<>();
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new Page<>(queryCond.getPageNo(), queryCond.getPageSize(), 0, result);
        }

        return vmsService.searchCapacity(buildQueryCond(queryCond), loginUser);
    }

    private com.juma.vms.common.query.QueryCond buildQueryCond(QueryCond<CapacityFilter> queryCond) {
        com.juma.vms.common.query.QueryCond q = new com.juma.vms.common.query.QueryCond();
        CapacityFilter filters = queryCond.getFilters();
        CapacityPoolFilter filter = new CapacityPoolFilter();
        if (null != filters) {
            filter.setDriverId(filters.getDriverId());
            filter.setTruckId(filters.getTruckId());
            filter.setVendorId(filters.getVendorId());
            filter.setVehicleBoxType(filters.getVehicleBoxType());
            filter.setVehicleBoxLength(filters.getVehicleBoxLength());
            filter.setGoCityLicenseType(filters.getGoCityLicenseType());
            filter.setAreaCodeList(filters.getAreaCodeList());
            filter.setStatus(filters.getCapacityStatus());
        }

        q.setFilters(filter);
        q.setPageNo(queryCond.getPageNo());
        q.setPageSize(queryCond.getPageSize());
        q.setOrderBy(queryCond.getOrderBy());
        return q;
    }

    @Override
    public Truck loadTruckByTruckId(Integer truckId) {
        if (null == truckId) {
            return null;
        }

        return vmsService.loadByTruckId(truckId);
    }

    @Override
    public Driver loadDriverByDriverId(Integer driverId) {
        if (null == driverId) {
            return null;
        }

        return vmsService.loadByDriverId(driverId);
    }

    @Override
    public Driver loadDriverByPhone(String phone) {
        if(StringUtils.isBlank(phone)) {
            return null;
        }

        return vmsService.loadDriverByPhone(phone);
    }

    @Override
    public List<Driver> listDriverByName(String name) {
        if(StringUtils.isBlank(name)) {
            return null;
        }

        return vmsService.listDriverByName(name);
    }

    @Override
    public Driver loadDriverByAmsDriverId(Integer amsDriverId) {
        if (null == amsDriverId) {
            return null;
        }

        return vmsService.loadDriverByAmsDriverId(amsDriverId);
    }

    @Override
    public Driver loadDriverByUserId(Integer userId) {
        if (null == userId) {
            return null;
        }

        return vmsService.loadDriverByUserId(userId);
    }

    @Override
    public DriverTenant loadDriverTenantByDriverId(Integer driverId, LoginUser loginUser) {
        if (null == driverId || null == loginUser || null == loginUser.getTenantId()) {
            return null;
        }

        return vmsService.loadDriverTenantByDriverId(driverId, loginUser);
    }

    @Override
    public void updateDriverIsAcceptAllocateOrder(Integer driverId, Integer isAcceptAllocateOrder, LoginUser loginUser) {
        if (null == driverId || null == isAcceptAllocateOrder) {
            return;
        }

        vmsService.updateDriverIsAcceptAllocateOrder(driverId, isAcceptAllocateOrder, loginUser);
    }

    @Override
    public Truck loadTruckByVehicleId(Integer vehicleId) {
        if (null == vehicleId) {
            return null;
        }

        return vmsService.loadTruckByVehicleId(vehicleId);
    }

    @Override
    public Truck loadTruckByPlateNumber(String plateNumber) {
        if (StringUtils.isBlank(plateNumber)) {
            return null;
        }

        return vmsService.loadTruckByPlateNumber(plateNumber);
    }

    @Override
    public Truck loadTruckByTenantAndPlateNumber(String plateNumber, LoginUser loginUser) {
        return vmsService.loadTruckByPlateNumberAndTenant(plateNumber, loginUser);
    }

    @Override
    public Truck loadTruckBytruckIdentificationNo(String truckIdentificationNo) {
        if (StringUtils.isBlank(truckIdentificationNo)) {
            return null;
        }

        return vmsService.loadTruckBytruckIdentificationNo(truckIdentificationNo);
    }

    @Override
    public List<Truck> listTruckByStatus(List<Integer> statusList, LoginUser loginUser) {
        if (CollectionUtils.isEmpty(statusList)) {
            return new ArrayList<>();
        }

        return vmsService.listTruckByStatus(statusList, loginUser);
    }

    @Override
    public CapacityPool loadCapacityByDriverId(Integer driverId, LoginUser loginUser) {
        if (null == driverId || null == loginUser || null == loginUser.getTenantId()) {
            return null;
        }

        return vmsService.loadCapacityPoolByDriverId(driverId, loginUser);
    }

    @Override
    public CapacityPool loadCapacityByTruckId(Integer truckId, LoginUser loginUser) {
        if (null == truckId || null == loginUser || null == loginUser.getTenantId()) {
            return null;
        }

        return vmsService.loadCapacityPoolByTruckId(truckId, loginUser);
    }

    @Override
    public Vendor loadVendorByTruckId(Integer truckId, LoginUser loginUser) {
        if (null == truckId) {
            return null;
        }

        return vmsService.loadVendorByTruckId(truckId, loginUser);
    }

    @Override
    public Vendor loadVendorByPlateNumber(String plateNumber, LoginUser loginUser) {
        if (StringUtils.isBlank(plateNumber) || null == loginUser || null == loginUser.getTenantId()) {
            return null;
        }

        List<Vendor> list = vmsService.loadByVendorByPlateNumber(plateNumber, loginUser);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Integer findDriverRunType(Integer driverId) throws BusinessException {
        Driver driver = this.loadDriverByDriverId(driverId);
        if (null == driver) {
            throw new BusinessException("vmsDriverNotExist", "errors.common.prompt", "没有找到VMS系统的司机信息");
        }

        if (null == driver.getDriverRunType()) {
            throw new BusinessException("vmsDriverRunTypeNotExist", "errors.common.prompt", "司机没有运营类型,请先在运力系统维护或联系售后人员");
        }

        return driver.getDriverRunType();
    }

    @Override
    public List<Driver> listDriverBy(DriverExternalFilter driverExternalFilter, Integer callbackPageSize, LoginUser loginUser) {
        return vmsService.listDriverBy(driverExternalFilter, callbackPageSize, loginUser);
    }

    @Override
    public void updateRemindSwitch(Integer driverId, RemindSwitchValue remindSwitchValue, RemindSwitchType remindSwitchType, LoginUser loginUser) {
        vmsService.updateRemindSwitch(driverId, remindSwitchValue, remindSwitchType, loginUser);
    }

    @Override
    public TruckTenant loadTruckTenantByTruckId(Integer truckId, LoginUser loginUser) {
        return vmsService.loadTruckTenantByTruckId(truckId, loginUser);
    }

    @Override
    public List<Driver> listDriverBy(List<String> areaCodeList, Integer driverRunType, Integer callbackPageSize, LoginUser loginUser) {
        return vmsService.listDriverBy(areaCodeList, driverRunType, callbackPageSize, loginUser);
    }

    @Override
    public Driver loadDriverByTruckId(Integer truckId, LoginUser loginUser) {
        if (null == truckId) {
            return null;
        }

        return vmsService.loadDriverByTruckId(truckId, loginUser);
    }

    @Override
    public Driver loadDriverByPlateNumber(String plateNumber, LoginUser loginUser) {
        if (StringUtils.isBlank(plateNumber)) {
            return null;
        }

        return vmsService.loadDriverByPlateNumber(plateNumber, loginUser);
    }

    @Override
    public List<Truck> listTruckBy(TruckExternalFilter truckExternalFilter, Integer callbackPageSize, LoginUser loginUser) {
        return vmsService.listTruckBy(truckExternalFilter, callbackPageSize, loginUser);
    }

    @Override
    public Truck loadTruckByDriverId(Integer driverId, LoginUser loginUser) {
        return vmsService.loadTruckByDriverId(driverId, loginUser);
    }

    @Override
    public List<DriverTenant> listDriverTenantByDriverId(Integer driverId) {
        return vmsService.listDriverTenantByDriverId(driverId);
    }

    @Override
    public Vendor loadVendorByPhone(String phone) {
        if (StringUtils.isBlank(phone)) {
            return null;
        }
        return vmsService.loadVendorByPhone(phone);
    }

    @Override
    public List<Driver> listEffectiveCapacityDriver(CapacityPoolFilter capacityPoolFilter, LoginUser loginUser) {
        return vmsService.listEffectiveCapacityDriver(capacityPoolFilter, loginUser);
    }

    @Override
    public VendorWhiteList loadVendorWhiteListByVendorId(Integer vendorId) {
        return vmsService.loadVendorWhiteListByVedorId(vendorId);
    }

    @Override
    public Vendor loadVendorByUserId(Integer userId) {
        if (null == userId) {
            return null;
        }

        return vmsService.loadVendorByUserId(userId);
    }

    @Override
    public List<Driver> listAllDriverByVendor(Integer vendorId) {
        if (null == vendorId) {
            return new ArrayList<>();
        }

        return vmsService.listAllDriverByVendorId(vendorId);
    }

    @Override
    public Map<Integer,Byte> listVendorSource(List<Integer> vendorIds, Byte vendorSource) throws BusinessException {
        return vmsService.listVendorSource(vendorIds,vendorSource);
    }

}
