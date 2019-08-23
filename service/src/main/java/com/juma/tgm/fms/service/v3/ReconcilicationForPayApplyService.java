package com.juma.tgm.fms.service.v3;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.fms.domain.v3.bo.ReconciliationWaybillDetailBo;
import com.juma.tgm.fms.domain.v3.bo.ReconcilicationForPayApply;
import com.juma.tgm.fms.domain.v3.bo.WaybillAdjustFrightForPayable;
import com.juma.tgm.waybill.domain.Waybill;

/**
 * @ClassName ReconcilicationForPayApplyService.java
 * @Description 应付账单对账申请
 * @author Libin.Wei
 * @Date 2018年11月26日 上午11:00:18
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface ReconcilicationForPayApplyService {

    /**
     * 
     * 承运商对账申请列表
     * 
     * @author Libin.Wei
     * @Date 2018年11月26日 上午11:22:41
     * @param pageCondition
     * @param loginUser
     * @return
     */
    Page<ReconcilicationForPayApply> search(PageCondition pageCondition, LoginUser loginUser);

    /**
     * 
     * 承运商对账运单列表
     * 
     * @author Libin.Wei
     * @Date 2018年11月26日 上午11:23:08
     * @param pageCondition
     * @param loginEmployee
     * @return
     */
    Page<ReconciliationWaybillDetailBo> searchWaybills(PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 
     * 按运单 ids 获取 运单列表
     * 
     * @author Libin.Wei
     * @Date 2018年11月26日 上午11:43:06
     * @param waybillIds
     * @param loginUser
     * @return
     */
    List<Waybill> listByWaybillIds(List<Integer> waybillIds, LoginUser loginUser);

    /**
     * 
     * 批量改价
     * 
     * @author Libin.Wei
     * @Date 2018年11月26日 下午2:32:29
     * @param waybillAdjustFrightForPayables
     * @param loginUser
     * @throws BusinessException
     */
    void updateCostBatch(List<WaybillAdjustFrightForPayable> waybillAdjustFrightForPayables, LoginUser loginUser)
            throws BusinessException;

    /**
     * 
     * 生成对账单
     * 
     * @author Libin.Wei
     * @Date 2018年11月26日 下午2:40:34
     * @param waybillIds
     * @param loginUser
     * @throws BusinessException
     */
    String createReconcilication(List<Integer> waybillIds, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 获取改价页面的价格详情
     * 
     * @author Libin.Wei
     * @Date 2018年11月28日 下午9:15:05
     * @param waybillId
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    WaybillAdjustFrightForPayable findupdateFrightDetails(Integer waybillId, LoginUser loginUser)
            throws BusinessException;
}
