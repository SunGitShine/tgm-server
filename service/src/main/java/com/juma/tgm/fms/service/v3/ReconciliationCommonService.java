package com.juma.tgm.fms.service.v3;

import com.giants.common.exception.BusinessException;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.fms.domain.v3.AdjustForMaster;
import com.juma.tgm.fms.domain.v3.ReconcilicationForReceivable;

/**
 * 对账单-公共逻辑
 * 功能 : 
 * 1.关联单逻辑
 * @author : Bruce(刘正航) 15:37 2019-05-22
 */
public interface ReconciliationCommonService {

    /**根据对账单,创建关联单**/
    void validAndCreateReconciliationLink(String reconciliationNo) throws BusinessException;

    /**根据对账单,创建关联单**/
    void validAndCreateReconciliationLink(ReconcilicationForReceivable receivable) throws BusinessException;

    /**创建对账单的时候,创建关联单**/
    void validAndCreateReconciliationAdjustLink(ReconcilicationForReceivable receivable, AdjustForMaster master) throws BusinessException;

    /**开票系统-创建关联单**/
    boolean validAndCreateReconciliationForInvoice(ReconcilicationForReceivable receivable, LoginEmployee loginEmployee) throws BusinessException;
}
