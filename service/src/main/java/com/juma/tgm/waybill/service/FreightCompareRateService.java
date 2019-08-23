package com.juma.tgm.waybill.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.waybill.domain.FreightCompareRate;

/**
 * 价格对比度配置
 * 
 * @author weilibin
 *
 */

public interface FreightCompareRateService {

    /**
     * 分页查询
     * 
     * @param pageCondition
     * @param loginEmployee
     *            当前登录人
     * @return Page<FreightCompareRate>
     */
    Page<FreightCompareRate> search(PageCondition pageCondition, LoginEmployee loginEmployee);

    /**
     * 根据ID获取信息
     * 
     * @param compareId
     *            价格对比度配置ID
     * @return FreightCompareRate
     */
    FreightCompareRate findOne(Integer compareId);

    /**
     * 修改
     * 
     * @param freightCompareRate
     *            价格对比度配置信息
     * @param loginEmployee
     *            当前登录人
     * @return void
     * @throws BusinessException
     */
    void update(FreightCompareRate freightCompareRate, LoginEmployee loginEmployee) throws BusinessException;
}
