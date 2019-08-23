package com.juma.tgm.waybill.service;

import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.waybill.domain.Waybill;
import java.math.BigDecimal;

/**
 *
 * @ClassName: MsgWithBusinessService
 * @Description:
 * @author: liang
 * @date: 2017-11-21 16:43
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public interface MsgWithBusinessService {
    
    void modifyPlanDeliveryTimeMsg(Waybill waybill);

    void pushWaitDeliveryAppMsgToDriver(Waybill waybill);

    void pushFenceEntryMsg(Integer waybillId);

    void pushFenceExitMsg(Integer waybillId);

    void pushChangeCarMsg(Integer waybillId, Integer oldDriverId,LoginUser loginUser);

    void pushDriverReceiveWaybillMsg(Waybill waybill, Driver driver, LoginUser loginUser);

    void pushPointCarMsg(Integer waybillId,LoginUser loginUser);

    void pushNotAssignCarFeedback(Waybill waybill,LoginUser loginUser);

    void pushLeaveDepotMsg(Waybill waybill,LoginUser loginUser);

    void pushArriveDepotMsg(Waybill waybill,LoginUser loginUser);

    void pushChangePriceMsgToDriver(Integer waybillId, BigDecimal oldShow4DriverFreight);

    void pushChangePricelMsgToWaybillOwner(Integer waybillId, BigDecimal oldFreight,LoginUser loginUser);

    void pushWaybillShouldFinishMsg(int waybillId);

    void pushFinishWaybillMsg(Waybill waybill,LoginUser loginUser);

    void pushFinishPayMsg(Waybill waybill);

    void pushDeliveryPointSupplementHasUpdateMsg(int waybillId);

    void pushDeliveryPointSupplementInvalidMsg(int waybillId);

    void pushFreightAuditNoPassMsg(int waybillId, LoginUser loginUser);

    void pushNoticeCustomerManageCompleteWork(Integer waybillId, LoginUser loginUser);
}
