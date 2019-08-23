package com.juma.tgm.fms.service.v3;

import java.math.BigDecimal;

import com.juma.fms.core.domain.ReceiptStatusDO;

/***
 * @author huangxing
 *
 *
 *
 *
 * 运单对账同步接口定义
 *
 *
 * */
public interface ReconciliationReceivableSyncService {

    /***
     *
     * 收款状态 同步
     *
     *
     * @param reconciliationNo 对账单号
     *
     *
     *
     * @param receiveStatus 收款状态
     * */
    void receipt(String reconciliationNo, BigDecimal receivableFreight, BigDecimal hasReceiveFreight, Integer receiveStatus);

    /***
     * 开票状态 同步
     *
     *@param reconciliationNo 对账单号
     *
     *
     * */
    void invoice(String reconciliationNo);

}
