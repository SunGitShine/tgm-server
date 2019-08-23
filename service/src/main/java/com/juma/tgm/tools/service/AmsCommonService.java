package com.juma.tgm.tools.service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.server.vm.domain.Driver;
import com.juma.server.vm.domain1.VehicleExtraFunction;
import com.juma.server.vm.domain1.bo.VehicleBo;

import java.util.List;

/**
 * @author Libin.Wei
 * @version 1.0.0
 * @ClassName AmsCommonService.java
 * @Description ams公共类
 * @Date 2018年12月4日 下午8:47:51
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface AmsCommonService {

    /**
     * 获取司机信息
     *
     * @param amsDriverId
     * @param loginUser
     * @return
     * @throws BusinessException
     * @author Libin.Wei
     * @Date 2018年12月4日 下午9:03:05
     */
    Driver findDriver(Integer amsDriverId, LoginUser loginUser) throws BusinessException;

    /**
     * 获取车辆信息
     *
     * @param vehicleId
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    VehicleBo findVehicle(Integer vehicleId, LoginUser loginUser) throws BusinessException;

    /**
     * 根据车牌号获取
     *
     * @param plateNumber
     * @param loginEmployee
     * @return
     * @throws BusinessException
     */
    VehicleBo findVehicleByPlateNumber(String plateNumber, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 获取车辆的附加功能
     *
     * @param vehicleId
     * @param loginUser
     * @return
     */
    List<VehicleExtraFunction> listExtraByVehicleId(Integer vehicleId, LoginUser loginUser);
}
