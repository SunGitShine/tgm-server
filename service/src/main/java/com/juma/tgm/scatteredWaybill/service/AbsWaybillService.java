package com.juma.tgm.scatteredWaybill.service;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.service.WaybillCheckService;
import com.juma.tgm.waybill.service.WaybillParamService;

public abstract class AbsWaybillService implements ScatteredWaybillService {

    @Resource
    private DriverService driverService;
    @Resource
    private WaybillParamService waybillParamService;
    @Resource
    private WaybillCheckService waybillCheckService;

    public void changeToDeliveriedAtBefore(Waybill dbEntity, LoginUser loginUser) {
        if (dbEntity == null) {
            throw new BusinessException("waybillNotfound", "waybill.error.notfound");
        }
        if (dbEntity.getStatus() == Waybill.Status.DELIVERIED.getCode()) {
            throw new BusinessException("waybillBeDeliveried", "waybill.error.deliveried");
        }
        if (dbEntity.getStatus() == Waybill.Status.SYS_CANCEL.getCode()) {
            throw new BusinessException("waybillSysCancel", "waybill.error.syscancel");
        }
        if (dbEntity.getStatus() != Waybill.Status.DELIVERYING.getCode()) {
            throw new BusinessException("waybillNotDeliverying", "waybill.error.waybillNotDeliverying");
        }
        Integer driverId = dbEntity.getDriverId();
        if (null == driverId) {
            throw new BusinessException("waybillNotAssignDriver", "waybill.error.waybillNotAssignDriver");
        }
        Driver driver = driverService.findDriverByUserId(loginUser.getUserId());
        if (null == driver) {
            throw new BusinessException("waybillAssignDriverNotExist", "waybill.error.waybillAssignDriverNotExist");
        }
        BaseUtil.checkSelf(driver.getDriverId(), driverId);

        WaybillParam waybillParam = waybillParamService.findByWaybillId(dbEntity.getWaybillId());
        if (null == waybillParam) {
            return;
        }

        // 已经填写了工作量则不判断
        if (StringUtils.isNotBlank(waybillParam.getValuationConstJson())) {
            return;
        }

        if (waybillCheckService.checkProjectIsWorkload(dbEntity.getWaybillId())
                && !waybillCheckService.checkIsDriverWriteWork(dbEntity.getWaybillId())) {
            throw new BusinessException("pleaseNoticeToCustomerManageCompleteWorkLoad",
                    "waybill.error.pleaseNoticeToCustomerManageCompleteWorkLoad");
        }
    }
}
