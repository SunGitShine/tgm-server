package com.juma.tgm.waybillReconciliation.service;

import java.math.BigDecimal;
import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybillReconciliation.domain.WaybillReconciliation;

/**
 * @ClassName WaybillReconciliationService.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年7月26日 下午2:50:42
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface WaybillReconciliationService {

    /**
     * 
     * 分页查询
     * 
     * @author Libin.Wei
     * @Date 2017年7月26日 下午2:51:23
     * @param pageCondition
     * @param loginEmployee
     * @return
     */
    Page<WaybillReconciliation> search(PageCondition pageCondition, List<String> areaCodeList,
            LoginUser loginUser);

    /**
     * 
     * 根据运单ID获取
     * 
     * @author Libin.Wei
     * @Date 2017年7月26日 下午2:52:36
     * @param waybillId
     *            运单ID
     * @return
     */
    WaybillReconciliation findByWaybillId(Integer waybillId);

    /**
     * 
     * 修改价格
     * 
     * @author Libin.Wei
     * @Date 2017年7月26日 下午2:53:46
     * @param waybillReconciliation
     * @param loginUser
     * @throws BusinessException
     */
    void update(WaybillReconciliation waybillReconciliation, List<String> areaNodeList, LoginUser loginUser)
            throws BusinessException;

    /**
     * 
     * 系统计算司机结算价
     * 
     * @author Libin.Wei
     * @Date 2017年7月27日 下午2:05:59
     * @param waybillReconciliation
     * @return
     */
    BigDecimal calSysDriverFreight(WaybillReconciliation waybillReconciliation);

    /**
     * 
     * 确认对账完成
     * 
     * @author Libin.Wei
     * @Date 2017年11月20日 上午11:32:32
     * @param waybillId
     * @throws BusinessException
     */
    void updateReconciliationStatus(int waybillId, LoginUser loginUser) throws BusinessException;
}
