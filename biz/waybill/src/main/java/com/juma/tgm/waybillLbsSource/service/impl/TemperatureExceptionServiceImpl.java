package com.juma.tgm.waybillLbsSource.service.impl;

import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybillLbsSource.dao.TemperatureExceptionDao;
import com.juma.tgm.waybillLbsSource.domain.TemperatureException;
import com.juma.tgm.waybillLbsSource.service.TemperatureExceptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TemperatureExceptionServiceImpl implements TemperatureExceptionService {

    @Resource
    private TemperatureExceptionDao temperatureExceptionDao;
    @Resource
    private WaybillCommonService waybillCommonService;

    @Override
    public Page<TemperatureException> search(PageCondition pageCondition, LoginUser loginUser) {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new Page<TemperatureException>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                    new ArrayList<TemperatureException>());
        }
        Object driverName = pageCondition.getFilters().get("driverName");
        waybillCommonService.structPageCondition(pageCondition, loginUser);
        pageCondition.getFilters().put("driverName",driverName);
        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());
        int count = temperatureExceptionDao.searchCount(pageCondition);
        List<TemperatureException> rows = temperatureExceptionDao.search(pageCondition);
        return new Page<TemperatureException>(pageCondition.getPageNo(), pageCondition.getPageSize(), count, rows);
    }

}
