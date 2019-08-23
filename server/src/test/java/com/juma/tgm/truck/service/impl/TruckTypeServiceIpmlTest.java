package com.juma.tgm.truck.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import com.juma.auth.tenant.domain.Tenant;
import com.juma.auth.tenant.service.TenantService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.Constants;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.truck.service.TruckTypeService;

import testng.BaseTestNGTest;

/**
 * @ClassName TruckTypeServiceIpmlTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年8月7日 上午9:57:38
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class TruckTypeServiceIpmlTest extends BaseTestNGTest {

    @Resource
    private TruckTypeService truckTypeService;
    @Resource
    private TenantService tenantService;

    @Test
    public void insertTruckType() {
        Integer tenantId = 3;
        Integer vehicleBoxType = 31;
        Integer vehicleBoxLength = 57;
        Integer maxLoadCapacity = null;
        Double loadVolume = null;

        if (null == tenantId || null == vehicleBoxType || null == vehicleBoxLength) {
            return;
        }

        TruckType truckType = truckTypeService.findByBoxAndLength(vehicleBoxType, vehicleBoxLength, tenantId);
        if (null != truckType) {
            // 已存在
            return;
        }

        Tenant tenant = tenantService.loadTenant(tenantId);
        if (null == tenant) {
            return;
        }

        TruckType t = new TruckType();
        t.setVehicleBoxType(vehicleBoxType);
        t.setTruckLengthId(vehicleBoxLength);
        if (null != maxLoadCapacity) {
            t.setTruckTypeLoad(new BigDecimal(maxLoadCapacity + "").multiply(new BigDecimal("1.5"))
                    .divide(new BigDecimal("1000"), 2, BigDecimal.ROUND_HALF_UP));
        }
        if (null != loadVolume) {
            t.setTruckTypeVolume(new BigDecimal(loadVolume + "").multiply(new BigDecimal("1.5")));
        }
        t.setRedundancyLoad(BigDecimal.ZERO);
        t.setRedundancyVolume(BigDecimal.ZERO);

        LoginUser loginUser = Constants.SYS_LOGIN_USER;
        loginUser.setTenantId(tenantId);
        loginUser.setTenantCode(tenant.getTenantCode());
        truckTypeService.insert(t, loginUser);
    }

    @Test
    public void listAllTruckTypeByOrderNoAsc() {
        truckTypeService.listAllTruckTypeByOrderNoAsc(2, false);
    }
}
