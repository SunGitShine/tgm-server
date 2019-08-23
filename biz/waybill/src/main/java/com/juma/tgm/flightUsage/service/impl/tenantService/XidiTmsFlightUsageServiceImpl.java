package com.juma.tgm.flightUsage.service.impl.tenantService;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.flight.domain.bo.FlightUsageQuery;
import com.juma.tgm.flightUsage.service.tenant.XidiTmsFlightUsageService;

/**
 * @ClassName: XidiTmsFlightUsageServiceImpl
 * @Description:
 * @author: liang
 * @date: 2018-03-14 18:03
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class XidiTmsFlightUsageServiceImpl implements XidiTmsFlightUsageService {

    @Override
    public Page<FlightUsageQuery> langdingAvailableFlightSearch(PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException {
        throw new BusinessException("nonImplements", "errors.common.prompt", "没有实现");
    }
}
