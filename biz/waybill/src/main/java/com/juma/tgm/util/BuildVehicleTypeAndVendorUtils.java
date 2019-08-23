package com.juma.tgm.util;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.BuildVehicleTypeAndVendorService;
import com.juma.vms.truck.domain.Truck;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName BuildVehicleTypeAndVendorUtils.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年12月4日 下午6:03:58
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Service
public class BuildVehicleTypeAndVendorUtils implements BuildVehicleTypeAndVendorService {

    @Resource
    private VmsCommonService vmsCommonService;

    @Override
    public Waybill checkAndBuildVehicleTypeAndVendor(Waybill waybill, Integer vehicleId, Integer driverType,
            String errorMsgKey, LoginUser loginUser) throws BusinessException {
        Truck truck = vmsCommonService.loadTruckByTruckId(waybill.getTruckId());
        if (null == truck) {
            return waybill;
        }
        waybill.setVehicleType(truck.getTruckRunType());
        return waybill;
    }

}
