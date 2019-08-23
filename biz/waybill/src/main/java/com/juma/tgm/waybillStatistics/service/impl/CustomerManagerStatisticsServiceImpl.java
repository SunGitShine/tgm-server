package com.juma.tgm.waybillStatistics.service.impl;

import com.giants.common.exception.BusinessException;
import com.juma.tgm.customer.domain.vo.ScatteredCustomerVo;
import com.juma.tgm.waybill.domain.vo.WaybillStatisticsParamVo;
import com.juma.tgm.waybillStatistics.dao.WaybillStatisticsDao;
import com.juma.tgm.waybillStatistics.service.CustomerManagerStatisticsService;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName: CustomerManagerStatisticsServiceImpl
 * @Description:
 * @author: liang
 * @date: 2017-11-20 11:57
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
@Service
public class CustomerManagerStatisticsServiceImpl implements CustomerManagerStatisticsService {

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
    public ScatteredCustomerVo countFreightAndBillCount(WaybillStatisticsParamVo waybillStatisticsParamVo) throws BusinessException {
        if(waybillStatisticsParamVo == null) return null;

        ScatteredCustomerVo customerVo = new ScatteredCustomerVo();
        Map<String, Object> rst =  waybillStatisticsDao.countFreightAndBillCount(waybillStatisticsParamVo);
        customerVo.setWaybillAmount(NumberUtils.createInteger(rst.get(KEY_TOTALBILLAMOUNT).toString()));
        customerVo.setTotalCost(rst.get(KEY_TOTALFREIGHT).toString());

        return customerVo;
    }

    /**
     * 落地配-客户管理-客户数量
     */
    private static final String SCATTERED_CUSTOMER_COUNT_CUSTOMERAMOUNT = "customerAmount";

    /**
     * 落地配-客户管理-运单数量
     */
    private static final String SCATTERED_CUSTOMER_COUNT_TOTALWAYBILLAMOUNT = "totalWaybillAmount";

    @Override
    public ScatteredCustomerVo.OverViewDataVo countCustomerOverViewData(int customerManagerId, ScatteredCustomerVo.CustomerType type) throws BusinessException {
        Integer cType = null;
        if (type != null) {
            cType = type.getCode();
        }
        ScatteredCustomerVo.OverViewDataVo overViewDataVo = new ScatteredCustomerVo.OverViewDataVo();
        Map<String, Object> rst = waybillStatisticsDao.countCustomerAndBill(customerManagerId, cType);
        Object customerAmount = rst.get(SCATTERED_CUSTOMER_COUNT_CUSTOMERAMOUNT);
        if (customerAmount != null) {
            overViewDataVo.setCustomerAmount(NumberUtils.createInteger(customerAmount.toString()));
        } else {
            overViewDataVo.setCustomerAmount(0);
        }

        if(cType != null && NumberUtils.compare(cType, ScatteredCustomerVo.CustomerType.TYPE_NULL.getCode()) == 0) return overViewDataVo;


        Object totalWaybillAmount = rst.get(SCATTERED_CUSTOMER_COUNT_TOTALWAYBILLAMOUNT);
        if (totalWaybillAmount != null) {
            overViewDataVo.setTotalWaybillAmount(NumberUtils.createInteger(totalWaybillAmount.toString()));
        } else {
            overViewDataVo.setTotalWaybillAmount(0);
        }

        return overViewDataVo;
    }
}
