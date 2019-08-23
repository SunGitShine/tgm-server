package com.juma.tgm.tools.service;

import com.giants.common.exception.BusinessException;
import com.juma.fms.v2.core.payment.payable.domain.WayBillStatus;

import java.util.List;
import java.util.Map;

/**
 * 调用FMS统一类
 * 功能 : 
 * 1.承运商对账后,冻结应付单(如果有多个,则多个一起冻结)
 * 2.承运商对账后,审批通过时,解冻应付单
 * 3.根据对账单号,查询是否允许新建调整单
 * @author : Bruce(刘正航) 13:58 2019-05-16
 */
public interface FmsCommonService {

    /**根据对账单和运单: 判断运单是否可创建调整单**/
    Map<String,WayBillStatus> canCreateAdjust(String reconciliationNo, List<String> waybillNos) throws BusinessException;

    /**根据对账单冻结对应的应付单**/
    void frozenPayable(String reconciliationNo, List<String> vendorIds, String adjustNo) throws BusinessException;

    /**根据对账单解冻对应的应付单**/
    void unfrozenPayable(String reconciliationNo, List<String> vendorIds) throws BusinessException;

}
