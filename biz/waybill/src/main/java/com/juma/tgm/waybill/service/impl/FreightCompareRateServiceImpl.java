package com.juma.tgm.waybill.service.impl;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.waybill.dao.FreightCompareRateDao;
import com.juma.tgm.waybill.domain.FreightCompareRate;
import com.juma.tgm.waybill.service.FreightCompareRateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class FreightCompareRateServiceImpl implements FreightCompareRateService {

    @Resource
    private FreightCompareRateDao freightCompareRateDao;

    @Override
    public Page<FreightCompareRate> search(PageCondition pageCondition, LoginEmployee loginEmployee) {
    	int total = freightCompareRateDao.searchCount(pageCondition);
    	List<FreightCompareRate> result = freightCompareRateDao.search(pageCondition);
    	return new Page<FreightCompareRate>(pageCondition.getPageNo(), pageCondition.getPageSize(), total, result);
    }

    @Override
    public FreightCompareRate findOne(Integer compareId) {
        return freightCompareRateDao.get(compareId);
    }

    @Override
    public void update(FreightCompareRate freightCompareRate, LoginEmployee loginEmployee) throws BusinessException {
    	freightCompareRate.setLastUpdateTime(new Date());
    	freightCompareRate.setLastUpdateUserId(loginEmployee.getUserId());
    	freightCompareRateDao.update(freightCompareRate);
    }

}
