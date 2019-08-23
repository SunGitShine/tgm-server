package com.juma.tgm.scatteredWaybill.service;

import com.juma.auth.user.domain.LoginEcoUser;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.domain.Waybill;

import java.math.BigDecimal;

/**
 * @ClassName: ScatteredMsgService
 * @Description:
 * @author: liang
 * @date: 2017-11-28 09:26
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public interface ScatteredMsgService {
    //货主消息------
    //客户经理代发单
    void customerManagerCreateBillMsg(Waybill waybill, LoginUser loginUser);

    //经纪人确认已收款
    void customerManConfirmFreightAcceptMsg(Waybill waybill, LoginUser loginUser);

    void customerManConfirmFeeDeliveryMsg(Waybill waybill, LoginUser loginUser);

    //运单被取消
    void customerManBillCancelMsg(Waybill waybill, LoginUser loginUser);

    //经纪人端消息------
    //客户已下单
    void cargoOwnerCreateBillMsg(Waybill waybill, LoginEcoUser loginEcoUser);

    //派车成功
    //开始配送
    //结束配送
    //运单被取消
    void cargoOwnerCancelBillMsg(Waybill waybill, LoginUser loginUser);

    // 司机到仓通知
    void pushArriveDepotMsg(Waybill waybill, LoginUser loginUser);

    void pushLeaveDepotMsg(Waybill waybill, BigDecimal oldEstimateFreight, LoginUser loginUser);

    void pushFinishWaybillMsg(Waybill waybill, LoginUser loginUser);

    void pushAssignedWaybillMsg(Waybill waybill, LoginUser loginUser);

    // 派车成功，推送给司机APP
    void pushAssignedWaybillToDriverAppMsg(Waybill waybill, LoginUser loginUser);
    
    // 派车成功，推送给司机SMS
    void pushAssignedWaybillToDriverSmsMsg(Waybill waybill, LoginUser loginUser);

    void pushCancelWaybillMsg(Waybill waybill, LoginUser loginUser);

    // 推送取消运单消息给司机
    void pushCancelWaybillToDriverMsg(Waybill waybill, Integer oldDriverId, LoginUser loginUser);

    // 推送换车消息给货主与经纪人
    void pushChangeCarMsg(Waybill waybill, LoginUser loginUser);

    /**
     * 发送重新派车消息给司机
     * @param waybill
     * @param oldDriverId
     * @param loginUser
     */
    void pushChangeCarMsgToDriver(Waybill waybill, Integer oldDriverId,LoginUser loginUser);
    
    //通知消息给调度
    void pushMsgToAdmin(Waybill waybill, LoginUser loginUser);

    /**
     * 客户经理端发起取消运单推送消息
     * @param waybill
     * @param loginUser
     */
    void pushCancelWaybillToDriverAndDispatcher(Waybill waybill,LoginUser loginUser);

    void pushNoticeCustomerManageCompleteWork(Integer waybillId, LoginUser loginUser);
}
