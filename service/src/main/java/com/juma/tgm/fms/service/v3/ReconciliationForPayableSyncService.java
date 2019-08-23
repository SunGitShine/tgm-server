package com.juma.tgm.fms.service.v3;

import java.util.List;

/***
 * 运单对账同步接口定义
 * */

public interface ReconciliationForPayableSyncService {


    /***
     * 结算状态 同步
     *
     * @param subReconciliationNo 子对账单号
     * @param settlementStatus 子对账单结算状态
     *
     * */
    void settlement(String subReconciliationNo, String settlementStatus) ;

    /**
     * FMS应付对账单结算时发现承运商信息与合同不一致，
     * 通知TMS修改子对账单、运单、调整单的承运商信息
     * @param subReconciliationNos
     */
    void updateSubPayableVendor(List<String> subReconciliationNos, Integer vendorId);

    /**
     * 修改应付对账单副表承运商id和承运商名称
     * @param waybillNos
     * @param vendorId
     * @param vendorName
     */
    void updatePayableItemVendor(List<String> waybillNos, Integer vendorId, String vendorName);


}
