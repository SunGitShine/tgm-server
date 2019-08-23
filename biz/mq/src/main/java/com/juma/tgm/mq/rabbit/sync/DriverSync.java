package com.juma.tgm.mq.rabbit.sync;

import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.tgm.fms.domain.v3.enums.PayableSettleAccountTypeEnum;
import com.juma.tgm.fms.service.v3.ReconcilicationForPayableService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.vms.driver.domain.Driver;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @ClassName TruckSync.java
 * @Description MQ同步司机信息
 * @author Libin.Wei
 * @Date 2017年5月22日 上午9:30:06
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Component
public class DriverSync {

    @Resource
    private VmsCommonService vmsCommonService;
    @Resource
    private UserService userService;
    @Resource
    private WaybillService waybillService;
    @Resource
    private ReconcilicationForPayableService reconcilicationForPayableService;

    /**
     * 
     * 用户中心注册的司机，还未在AMS系统中添加，用户中心更改时同步
     * 
     * @author Libin.Wei
     * @Date 2017年10月24日 上午10:45:43
     */
    public void updateByAuth(int userId) {
        User user = userService.loadUser(userId);
        if (null == user) {
            return;
        }

        Driver driver = vmsCommonService.loadDriverByUserId(userId);
        if (null == driver) {
            return;
        }


        String oldDriverName = driver.getName();

        // 更改运单中的司机姓名
        updateDriverNameInWaybill(driver.getDriverId(), user.getName(), oldDriverName);

        // 修改应付对账承运商名称
        updateReconcilicationForPayableItemSettleAccountName(driver.getDriverId(), user.getName(), oldDriverName);
    }

    // 更改运单中的司机姓名
    private void updateDriverNameInWaybill(int driverId, String driverName, String oldDriverName) {
        if (StringUtils.isBlank(driverName)) {
            return;
        }

        if (StringUtils.isNotBlank(oldDriverName) && oldDriverName.equals(driverName)) {
            return;
        }

        Waybill example = new Waybill();
        example.setDriverId(driverId);
        Waybill newValue = new Waybill();
        newValue.setDriverName(driverName);

        waybillService.updateWaybillBatch(example, newValue, null);
    }

    // 修改应付对账承运商名称
    private void updateReconcilicationForPayableItemSettleAccountName(Integer driverId, String driverName,
            String oldDriverName) {
        if (driverName.equals(oldDriverName)) {
            return;
        }

        reconcilicationForPayableService.updateNameBySettleAccountIdAndType(driverName, driverId,
                PayableSettleAccountTypeEnum.DRIVER.getCode());
    }
}
