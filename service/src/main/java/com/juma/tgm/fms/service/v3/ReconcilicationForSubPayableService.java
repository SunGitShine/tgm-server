package com.juma.tgm.fms.service.v3;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.fms.domain.v3.ReconcilicationForSubPayable;

import java.util.List;

/**
 * 应付子账单
 */
public interface ReconcilicationForSubPayableService {

    /**
     *  添加应付子账单
     * @param reconcilicationId
     * @param vendorId
     * @param loginUser
     * @throws BusinessException
     */
    String insert(Integer reconcilicationId, Integer vendorId, LoginUser loginUser) throws BusinessException;

    /**
     * 根据子对账单号获取
     * @param subReconcilicationNo
     * @return
     */
    ReconcilicationForSubPayable findBySubReconcilicationNo(String subReconcilicationNo);

    /**
     * 根据对账单ID获取
     * @param reconcilicationId
     * @return
     */
    List<ReconcilicationForSubPayable> listByReconcilicationId(Integer reconcilicationId);

    /**
     * 根据对账单ID验证是否所有的子对账单都已结算
     * @param reconcilicationId 若为空，返回true
     * @return  true:所有的子对账单都已结算   false:不是所有的子对账单都已结算
     */
    boolean checkIsAllHasClear(Integer reconcilicationId);

    /**
     * 根据对账单ID和承运商ID获取
     * @param reconcilicationId
     * @param vendorId
     * @return
     */
    ReconcilicationForSubPayable findByReconcilicationIdAndVendorId(Integer reconcilicationId, Integer vendorId);
}
