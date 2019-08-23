package com.juma.tgm.waybill.service;

import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.domain.FenceEvent;
import com.juma.tgm.waybill.domain.WaybillBindFence;

/**
 * @ClassName WaybillAutoMonitorServicve.java
 * @Description 运单自动监控
 * @author Libin.Wei
 * @Date 2017年2月22日 下午4:47:51
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Deprecated
public interface WaybillAutoFenceServicve {

    /**
     * 
     * @Description 绑定运单ID与电子围栏ID
     * @author Libin.Wei
     * @Date 2017年2月22日 下午5:18:20
     * @param waybillId
     */
    void bindWaybillIdAndFenceId(Integer waybillId, WaybillBindFence.Sign sign, LoginUser loginUser);

    /**
     * 
     * 触发进入电子围栏通知事件
     * 
     * @author Libin.Wei
     * @Date 2017年4月28日 下午1:22:16
     * @param fenceId
     *            电子围栏ID
     */
    void touchEntryPushMessage(FenceEvent fenceEvent, LoginUser loginUser);

    /**
     * 
     * 触发离开电子围栏通知事件
     * 
     * @author Libin.Wei
     * @Date 2017年4月28日 下午1:22:16
     * @param fenceId
     *            电子围栏ID
     */
    void touchExitPushMessage(FenceEvent fenceEvent, LoginUser loginUser);

    /**
     * 
     * @Description 根据运单ID清除所有的电子围栏ID
     * @author Libin.Wei
     * @Date 2017年3月16日 上午9:49:52
     * @param waybillId
     *            运单ID
     * @param loginUserId
     *            登录用户账号ID信息
     */
    void removeAllFenceId(Integer waybillId, LoginUser loginUser);

    /**
     * 
     * 更新取货地电子围栏开始时间
     * @author Libin.Wei
     * @Date 2017年9月11日 下午4:12:32
     * @param waybillId
     * @param loginUserId
     */
    void changeDeliveryStartTime(Integer waybillId, LoginUser loginUser);
}
