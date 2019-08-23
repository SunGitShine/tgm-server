package com.juma.tgm.waybillStatistics.dao;

import com.juma.tgm.waybill.domain.vo.WaybillStatisticsParamVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @ClassName: WaybillStatisticsDao
 * @Description:
 * @author: liang
 * @date: 2017-11-17 15:16
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public interface WaybillStatisticsDao {

    /**
     * 统计运费总数和运单总数
     *
     * @param waybillStatisticsParamVo
     * @return
     */
    Map<String,Object> countFreightAndBillCount(WaybillStatisticsParamVo waybillStatisticsParamVo);

    /**
     * 统计客户经理 管理的客户数和成单量
     * @param customerManagerId
     * @param type
     * @return
     */
    Map<String,Object> countCustomerAndBill(@Param("customerManagerId") int customerManagerId, @Param("type") Integer type);

}
