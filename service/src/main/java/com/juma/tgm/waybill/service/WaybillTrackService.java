package com.juma.tgm.waybill.service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.cron.vo.WaybillMileageBo;
import com.juma.tgm.waybill.domain.Waybill;

import java.util.Date;

public interface WaybillTrackService {

    /**
     * 维护运单实际距离
     *
     * @param waybillMileageBo
     * @param loginUser
     *
     * @throws BusinessException
     */
    void changeActualMileage(WaybillMileageBo waybillMileageBo, LoginUser loginUser) throws BusinessException;

    /**
     * 获取运单设备轨迹信息
     *
     * @param waybill
     *
     * @throws BusinessException
     */
    void getWaybillTraceInfo(Waybill waybill) throws BusinessException;

    /**
     * 获取运单里程信息
     *
     * @param waybillId
     * @param loginUser
     *
     * @return
     */
    WaybillMileageBo getMileageInfo(Integer waybillId, LoginUser loginUser);

    /**
     * dubbo异步获取并记录运单的实际里程
     *
     * @param waybillId
     * @param loginUser
     */
    void syncChangeActualMileage(Integer waybillId, LoginUser loginUser);
}