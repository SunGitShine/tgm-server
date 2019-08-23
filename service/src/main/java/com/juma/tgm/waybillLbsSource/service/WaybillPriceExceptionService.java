package com.juma.tgm.waybillLbsSource.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.waybill.domain.WaybillBo;
import com.juma.tgm.waybillLbsSource.domain.WaybillPriceExceptionQuery;

/**
 * Created by shawn_lin on 2017/8/23.
 */
public interface WaybillPriceExceptionService {
    /**
     * @return
     * @throws BusinessException
     */
    Page<WaybillPriceExceptionQuery> search(PageCondition pageCondition, LoginEmployee loginEmployee);

    /**
     * 异步导出价格异常运单
     */
    void asyncExport(PageCondition pageCondition, Integer exportTaskId, LoginEmployee loginEmployee) throws Exception;
}
