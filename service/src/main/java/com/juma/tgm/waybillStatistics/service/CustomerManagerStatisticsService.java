package com.juma.tgm.waybillStatistics.service;

import com.giants.common.exception.BusinessException;
import com.juma.tgm.customer.domain.vo.ScatteredCustomerVo;
import com.juma.tgm.waybill.domain.vo.WaybillStatisticsParamVo;

/**
 * @ClassName: CustomerManagerStatisticsService
 * @Description:
 * @author: liang
 * @date: 2017-11-20 11:51
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public interface CustomerManagerStatisticsService {
    /**
     * 统计总运费和总运单数
     * @param waybillStatisticsParamVo
     * @return
     * @throws BusinessException
     */
    ScatteredCustomerVo countFreightAndBillCount(WaybillStatisticsParamVo waybillStatisticsParamVo) throws BusinessException;

    /**
     * 客户经理客户管理单量概览
     * @param customerManagerId
     * @param type
     * @return
     * @throws BusinessException
     */
    ScatteredCustomerVo.OverViewDataVo countCustomerOverViewData(int customerManagerId, ScatteredCustomerVo.CustomerType type) throws BusinessException;
}
