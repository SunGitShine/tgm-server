package com.juma.tgm.tools.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.capacity.domian.vo.CapacityFilter;
import com.juma.tgm.common.query.QueryCond;
import com.juma.vms.driver.domain.Driver;
import com.juma.vms.driver.domain.DriverTenant;
import com.juma.vms.driver.enumeration.RemindSwitchType;
import com.juma.vms.driver.enumeration.RemindSwitchValue;
import com.juma.vms.driver.external.DriverExternalFilter;
import com.juma.vms.transport.domain.CapacityPool;
import com.juma.vms.transport.external.CapacityPoolExternalQuery;
import com.juma.vms.transport.request.CapacityPoolFilter;
import com.juma.vms.truck.domain.Truck;
import com.juma.vms.truck.domain.TruckTenant;
import com.juma.vms.truck.external.TruckExternalFilter;
import com.juma.vms.vendor.domain.Vendor;
import com.juma.vms.vendor.domain.VendorWhiteList;
import com.juma.vms.vendor.external.VendorTenantExternal;

import java.util.List;
import java.util.Map;

/**
 * @author Libin.Wei
 * @version 1.0.0
 * @ClassName VmsCommonService.java
 * @Description 请填写注释...
 * @Date 2018年11月28日 下午2:13:28
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface VmsCommonService {

    /**
     * 根据承运商ID获取
     *
     * @param vendorId
     * @return
     * @throws BusinessException
     */
    Vendor loadVendorByVendorId(Integer vendorId) throws BusinessException;

    /**
     * 根据承运商ID获取
     *
     * @param vendorId  为空返回NULL
     * @param loginUser 为空或loginUser.tenantId为空返回NULL
     * @return
     * @throws BusinessException
     */
    VendorTenantExternal loadVendorTenantByVendorId(Integer vendorId, LoginUser loginUser) throws BusinessException;

    /**
     * 获取运力基础信息
     *
     * @param queryCond
     * @param loginUser
     * @return
     */
    Page<CapacityPool> searchCommonCapacity(QueryCond<CapacityFilter> queryCond, LoginUser loginUser);

    /**
     * 获取运力信息,包含承运商、车辆、司机等部分信息,限制查询最大200条
     *
     * @param queryCond
     * @param loginUser
     * @return
     */
    Page<CapacityPoolExternalQuery> seachCapacity(QueryCond<CapacityFilter> queryCond, LoginUser loginUser);

    /**
     * 获取车辆信息
     *
     * @param truckId
     * @return
     */
    Truck loadTruckByTruckId(Integer truckId);

    /**
     * 获取司机信息
     *
     * @param driverId
     * @return
     */
    Driver loadDriverByDriverId(Integer driverId);

    /**
     * 根据手机号获取司机信息
     *
     * @param phone
     * @return
     */
    Driver loadDriverByPhone(String phone);

    /**
     * 根据司机姓名获取司机信息
     *
     * @param name
     * @return
     */
    List<Driver> listDriverByName(String name);

    /**
     * 根据amsdriverId获取
     *
     * @param amsDriverId
     * @return
     */
    Driver loadDriverByAmsDriverId(Integer amsDriverId);

    /**
     * 根据USERID获取
     *
     * @param userId
     * @return
     */
    Driver loadDriverByUserId(Integer userId);

    /**
     * 根据司机ID获取司机租户关系
     *
     * @param driverId
     * @param loginUser
     * @return
     */
    DriverTenant loadDriverTenantByDriverId(Integer driverId, LoginUser loginUser);

    /**
     * 修改司机接单开关
     *
     * @param driverId
     * @param isAcceptAllocateOrder
     * @param loginUser
     */
    void updateDriverIsAcceptAllocateOrder(Integer driverId, Integer isAcceptAllocateOrder, LoginUser loginUser);

    /**
     * 根据vehicleID获取
     *
     * @param vehicleId
     * @return
     */
    Truck loadTruckByVehicleId(Integer vehicleId);

    /**
     * 根据车牌号获取
     *
     * @param plateNumber
     * @return
     */
    Truck loadTruckByPlateNumber(String plateNumber);

    /**
     * 根据车牌号获取
     *
     * @param plateNumber
     * @param loginUser
     * @return
     */
    Truck loadTruckByTenantAndPlateNumber(String plateNumber, LoginUser loginUser);

    /**
     * 根据车架号获取
     *
     * @param truckIdentificationNo
     * @return
     */
    Truck loadTruckBytruckIdentificationNo(String truckIdentificationNo);

    /**
     * 根据车辆状态获取
     *
     * @param statusList
     * @return
     */
    List<Truck> listTruckByStatus(List<Integer> statusList, LoginUser loginUser);

    /**
     * 根据司机ID获取运力
     *
     * @param driverId
     * @param loginUser
     * @return
     */
    CapacityPool loadCapacityByDriverId(Integer driverId, LoginUser loginUser);

    /**
     * 根据司机ID获取运力
     *
     * @param truckId
     * @param loginUser
     * @return
     */
    CapacityPool loadCapacityByTruckId(Integer truckId, LoginUser loginUser);

    /**
     * @param truckId
     * @param loginUser
     * @return
     */
    Vendor loadVendorByTruckId(Integer truckId, LoginUser loginUser);

    /**
     * 根据车牌号获取承运商信息
     *
     * @param plateNumber
     * @param loginUser
     *
     * @return
     */
    Vendor loadVendorByPlateNumber(String plateNumber, LoginUser loginUser);

    /**
     * 获取司机的运营类型
     *
     * @param driverId
     * @return
     * @throws BusinessException
     */
    Integer findDriverRunType(Integer driverId) throws BusinessException;


    /**
     * 根据条件查询
     *
     * @param driverExternalFilter
     * @param callbackPageSize
     * @param loginUser
     * @return
     */
    List<Driver> listDriverBy(DriverExternalFilter driverExternalFilter, Integer callbackPageSize, LoginUser loginUser);

    /**
     * 司机提醒开关
     *
     * @param driverId
     * @param remindSwitchValue
     * @param remindSwitchType
     * @param loginUser
     */
    void updateRemindSwitch(Integer driverId, RemindSwitchValue remindSwitchValue, RemindSwitchType remindSwitchType,
                            LoginUser loginUser);

    /**
     * 获取车辆租户关系表
     *
     * @param truckId
     * @param loginUser
     * @return
     */
    TruckTenant loadTruckTenantByTruckId(Integer truckId, LoginUser loginUser);

    /**
     * 获取司机列表
     *
     * @param areaCodeList
     * @param driverRunType
     * @param callbackPageSize
     * @param loginUser
     * @return
     */
    List<Driver> listDriverBy(List<String> areaCodeList, Integer driverRunType, Integer callbackPageSize, LoginUser loginUser);

    /**
     * 根据车辆ID获取司机信息
     *
     * @param truckId
     * @param loginUser
     * @return
     */
    Driver loadDriverByTruckId(Integer truckId, LoginUser loginUser);

    /**
     * 根据车牌号获取司机信息
     *
     * @param plateNumber
     * @param loginUser
     * @return
     */
    Driver loadDriverByPlateNumber(String plateNumber, LoginUser loginUser);

    /**
     * 根据条件模糊匹配
     *
     * @param truckExternalFilter
     * @param callbackPageSize
     * @param loginUser
     * @return
     */
    List<Truck> listTruckBy(TruckExternalFilter truckExternalFilter, Integer callbackPageSize, LoginUser loginUser);

    /**
     * 根据司机ID获取车辆信息
     *
     * @param driverId
     * @param loginUser
     * @return
     */
    Truck loadTruckByDriverId(Integer driverId, LoginUser loginUser);

    /**
     * 获取司机所在的租户
     *
     * @param driverId
     * @return
     */
    List<DriverTenant> listDriverTenantByDriverId(Integer driverId);

    /**
     * 根据手机号获取承运商
     *
     * @param phone
     * @return
     */
    Vendor loadVendorByPhone(String phone);

    /**
     * 获取是有效运力的司机信息
     *
     * @param capacityPoolFilter
     * @param loginUser
     * @return
     */
    List<Driver> listEffectiveCapacityDriver(CapacityPoolFilter capacityPoolFilter, LoginUser loginUser);

    /**
     * 根据承运商ID 获取
     *
     * @param vendorId
     * @return
     */
    VendorWhiteList loadVendorWhiteListByVendorId(Integer vendorId);

    /**
     * 根据用户的userId获取承运商信息
     *
     * @param userId
     * @return
     */
    Vendor loadVendorByUserId(Integer userId);

    /**
     * 获取承运商下的所有司机，不区分租户
     *
     * @param vendorId
     *
     * @return
     */
    List<Driver> listAllDriverByVendor(Integer vendorId);

    /**
     * 批量获取承运商运营方式
     *
     * @param vendorIds
     * @return
     * @throws BusinessException
     */
    Map<Integer,Byte> listVendorSource(List<Integer> vendorIds, Byte vendorSource) throws BusinessException;
}
