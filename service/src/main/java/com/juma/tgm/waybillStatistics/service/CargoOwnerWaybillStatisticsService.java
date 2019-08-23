package com.juma.tgm.waybillStatistics.service;

import com.giants.common.exception.BusinessException;
import com.juma.tgm.customer.domain.vo.CargoOwnerUserInfo;
import com.juma.tgm.waybill.domain.vo.WaybillStatisticsParamVo;

/**
 * @ClassName: CargoOwnerWaybillStatisticsService
 * @Description:
 * @author: liang
 * @date: 2017-11-17 15:03
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public interface CargoOwnerWaybillStatisticsService {

    /**
     * 统计总运费和总运单数
     * @param waybillStatisticsParamVo
     * @return
     * @throws BusinessException
     */
    CargoOwnerUserInfo countFreightAndBillCount(WaybillStatisticsParamVo waybillStatisticsParamVo) throws BusinessException;
}
