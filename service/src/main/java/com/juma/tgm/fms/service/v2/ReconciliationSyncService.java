package com.juma.tgm.fms.service.v2;


import com.juma.fms.core.domain.ReceiptStatusDO;

import java.math.BigDecimal;

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
public interface ReconciliationSyncService {




    /***
     * 结算状态 同步
     *
     * @param reconciliationNo 对账单号
     *
     * @param plateNumber 车牌号
     *
     * @param settlementState 对账单状态
     *
     * @param settlement 结算金额
     *
     * @param renterType 承租人 类型  为 0 的时候 是 普通 承租人 1 的时候是承运商
     *
     *@param renterId 承租人 id 或者 承运商id
     *
     * */
    void settlement( String reconciliationNo , String plateNumber , Integer settlementState ,BigDecimal settlement, Integer renterType, Integer renterId) ;



    /***
     *
     * 收款状态 同步
     *
     *
     * @param reconciliationNo 对账单号
     *
     *
     *
     * @param receipt 收款金额
     * */
    void receipt( String reconciliationNo, BigDecimal receipt );



    /***
     * 开票状态 同步
     *
     *@param reconciliationNo 对账单号
     *
     *
     * @param invoiceNo 票号
     *
     * */
    void invoice( 	String reconciliationNo , String invoiceNo);

    /**
     * 收款状态 同步(dubbo 接口)
     *
     * @param receiptStatusDO
     */
    void receipt(ReceiptStatusDO receiptStatusDO);
}
