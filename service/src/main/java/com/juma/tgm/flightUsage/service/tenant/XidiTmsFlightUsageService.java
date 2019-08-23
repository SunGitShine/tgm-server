package com.juma.tgm.flightUsage.service.tenant;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.flight.domain.bo.FlightUsageQuery;

/**
 * 希地租户定制业务
 * @ClassName: XidiTmsFlightUsageService
 * @Description:
 * @author: liang
 * @date: 2018-03-14 18:05
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */

@Deprecated
public interface XidiTmsFlightUsageService {

    Page<FlightUsageQuery> langdingAvailableFlightSearch(PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException;

}
