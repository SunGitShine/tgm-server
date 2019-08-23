package com.juma.tgm.tools.service;

import com.giants.common.exception.BusinessException;
import com.juma.tgm.fms.domain.v3.vo.InvoiceAmount;

import java.math.BigDecimal;
import java.util.List;

/**
 * 开票系统调用
 * 功能 : 
 * 1.根据运单号,查询开票数据
 * @author : Bruce(刘正航) 11:56 2019-05-16
 */
public interface InvoiceCommonService {
    /**根据运单号:获取开票信息**/
    List<InvoiceAmount> fetchInvoiceAmount(String reconcilicationNo) throws BusinessException;

    /**对账单调整后的金额 >= 该对账单的（已开票金额+开票中金额）true**/
    boolean validReceivableInvoiceAmount(String reconcilicationNo, BigDecimal afterReconciliationAmount) throws BusinessException;

    /**根据对账单号:冻结开票信息**/
    boolean frozenReceivableInvoice(String reconcilicationNo) throws BusinessException;

    /**根据对账单号:解冻开票信息**/
    boolean unfrozenReceivableInvoice(String reconcilicationNo) throws BusinessException;

}
