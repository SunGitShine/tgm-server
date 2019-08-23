package com.juma.tgm.tools.service.impl;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.server.vm.domain.dto.VehicleQueryConditionDTO;
import com.juma.server.vm.domain1.VehicleExtraFunction;
import com.juma.server.vm.domain1.bo.VehicleBo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.server.vm.domain.Driver;
import com.juma.server.vm.domain.dto.DriverQueryConditionDTO;
import com.juma.server.vm.service.vehicle.AmsServiceV2;
import com.juma.tgm.tools.service.AmsCommonService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AmsCommonServiceImpl implements AmsCommonService {


    private final Logger log = LoggerFactory.getLogger(AmsCommonServiceImpl.class);
    @Resource
    private AmsServiceV2 amsServiceV2;

    @Override
    public Driver findDriver(Integer amsDriverId, LoginUser loginUser) throws BusinessException {
        if (null == amsDriverId) {
            return null;
        }

        DriverQueryConditionDTO dto = new DriverQueryConditionDTO();
        dto.setTenantId(loginUser.getTenantId());
        dto.setTenantCode(loginUser.getTenantCode());
        dto.setDriverId(amsDriverId);
        return amsServiceV2.getDriverById(dto);
    }

    @Override
    public VehicleBo findVehicle(Integer vehicleId, LoginUser loginUser) throws BusinessException {
        if (null == vehicleId) {
            return null;
        }

        VehicleQueryConditionDTO dto = new VehicleQueryConditionDTO();
        dto.setVehicleId(vehicleId);
        dto.setTenantId(loginUser.getTenantId());
        dto.setTenantCode(loginUser.getTenantCode());
        return amsServiceV2.getVehicleById(dto);
    }

    @Override
    public VehicleBo findVehicleByPlateNumber(String plateNumber, LoginEmployee loginEmployee) throws BusinessException {
        if (StringUtils.isBlank(plateNumber)) {
            return null;
        }

        return amsServiceV2.getByPlateNumber(plateNumber, loginEmployee);
    }

    @Override
    public List<VehicleExtraFunction> listExtraByVehicleId(Integer vehicleId, LoginUser loginUser) {
        List<VehicleExtraFunction> list = new ArrayList<>();
        if (null == vehicleId) {
            return list;
        }

        VehicleQueryConditionDTO dto = new VehicleQueryConditionDTO();
        dto.setVehicleId(vehicleId);
        dto.setTenantId(loginUser.getTenantId());
        try {
            list = amsServiceV2.listExtraByVehicleId(dto);
        } catch (Exception e) {
            log.warn("ams获取车辆附加功能错误{}", JSON.toJSONString(dto), e);
        }

        return list;
    }

}
