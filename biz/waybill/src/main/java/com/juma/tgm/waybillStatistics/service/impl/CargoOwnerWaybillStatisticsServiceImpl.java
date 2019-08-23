package com.juma.tgm.waybillStatistics.service.impl;

import com.giants.common.exception.BusinessException;
import com.juma.tgm.customer.domain.vo.CargoOwnerUserInfo;
import com.juma.tgm.waybill.domain.vo.WaybillStatisticsParamVo;
import com.juma.tgm.waybillStatistics.dao.WaybillStatisticsDao;
import com.juma.tgm.waybillStatistics.service.CargoOwnerWaybillStatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName: CargoOwnerWaybillStatisticsServiceImpl
 * @Description:
 * @author: liang
 * @date: 2017-11-17 15:14
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
@Service
public class CargoOwnerWaybillStatisticsServiceImpl implements CargoOwnerWaybillStatisticsService {

    /**
     * 运费总数
     */
    private final String KEY_TOTALFREIGHT = "totalFreight";

    /**
     * 运单总数
     */
    private final String KEY_TOTALBILLAMOUNT = "totalBillAmount";
    @Resource
    private WaybillStatisticsDao waybillStatisticsDao;

    @Override
    public CargoOwnerUserInfo countFreightAndBillCount(WaybillStatisticsParamVo waybillStatisticsParamVo) throws BusinessException {
        if(waybillStatisticsParamVo == null) return null;

        CargoOwnerUserInfo userInfo = new CargoOwnerUserInfo();
        Map<String, Object> rst =  waybillStatisticsDao.countFreightAndBillCount(waybillStatisticsParamVo);
        userInfo.setTotalBillAmount(rst.get(KEY_TOTALBILLAMOUNT).toString());
        userInfo.setTotalFreight(rst.get(KEY_TOTALFREIGHT).toString());

        return userInfo;
    }
}
