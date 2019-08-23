package com.juma.tgm.waybill.service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.domain.Waybill;

/**
 * @ClassName BuildVehicleTypeAndVendorService.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年12月4日 下午6:04:58
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface BuildVehicleTypeAndVendorService {

    public final static String CHANGE_TO_ASSIGN = "errors.vehicleMustBindVendor.changeToAssign";
    public final static String RECEIVE_WAYBILL = "errors.vehicleMustBindVendor.receiveWaybill";

    /**
     * 
     * 检查车辆是否绑定了承运商，若没有绑定，则抛出异常，若绑定了，则组装数据
     * 
     * @author Libin.Wei
     * @Date 2018年12月4日 下午7:57:41
     * @param waybill
     * @param vehicleId
     * @param driverType
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Waybill checkAndBuildVehicleTypeAndVendor(Waybill waybill, Integer vehicleId, Integer driverType,
            String errorMsgKey, LoginUser loginUser) throws BusinessException;
}
