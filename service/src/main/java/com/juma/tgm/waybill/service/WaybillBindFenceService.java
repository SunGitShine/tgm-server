package com.juma.tgm.waybill.service;

import java.util.List;

import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.domain.WaybillBindFence;

/**
 * @ClassName WaybillBindFenceService.java
 * @Description 运单绑定电子围栏service
 * @author Libin.Wei
 * @Date 2017年4月19日 下午3:07:18
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface WaybillBindFenceService {

    /**
     * 
     * 分页查询
     * 
     * @author Libin.Wei
     * @Date 2017年4月19日 下午3:12:20
     * @param pageCondition
     *            查询条件
     * @param loginUser
     *            当前登录人
     * @return
     */
    Page<WaybillBindFence> search(PageCondition pageCondition);

    /**
     * 
     * 根据主键查询
     * 
     * @author Libin.Wei
     * @Date 2017年4月19日 下午3:12:52
     * @param waybillBindFenceId
     *            运单电子围栏ID
     * @return WaybillBindFence
     */
    WaybillBindFence getWaybillBindFence(Integer waybillBindFenceId);

    /**
     * 
     * 根据主键查询
     * 
     * @author Libin.Wei
     * @Date 2017年4月19日 下午3:12:52
     * @param waybillBindFenceId
     *            运单电子围栏ID
     * @return WaybillBindFence
     */
    WaybillBindFence getWaybillBindFenceForUpdate(Integer waybillBindFenceId);

    /**
     * 
     * 根据运单ID与地址ID查询
     * 
     * @author Libin.Wei
     * @Date 2017年4月19日 下午3:13:30
     * @param addressId
     *            地址ID
     * @param waybillId
     *            运单ID
     * @return WaybillBindFence
     */
    List<WaybillBindFence> listByAddressIdAndWaybillId(Integer addressId, Integer waybillId);

    /**
     * 
     * 根据电子围栏ID查询:单条
     * 
     * @author Libin.Wei
     * @Date 2017年4月19日 下午3:13:30
     * @param addressId
     *            电子围栏ID
     * @return WaybillBindFence
     */
    WaybillBindFence findByFenceId(Integer fenceId);

    /**
     * 
     * 根据电子围栏ID查询:单条
     * 
     * @author Libin.Wei
     * @Date 2017年4月19日 下午3:13:30
     * @param addressId
     *            电子围栏ID
     * @return WaybillBindFence
     */
    WaybillBindFence findByFenceIdForUpdate(Integer fenceId);

    /**
     * 
     * 根据运单ID运类型查询
     * 
     * @author Libin.Wei
     * @Date 2017年4月19日 下午3:14:14
     * @param waybillId
     *            运单ID
     * @param sign
     *            类型
     * @return List<WaybillBindFence>
     */
    List<WaybillBindFence> listByWaybillIdAndSign(Integer waybillId, Integer sign);
    
    /**
     * 
     * 根据运单ID查询
     * 
     * @author Libin.Wei
     * @Date 2017年4月19日 下午3:14:14
     * @param waybillId
     *            运单ID
     * @return List<WaybillBindFence>
     */
    List<WaybillBindFence> listByWaybillId(Integer waybillId);

    /**
     * 
     * 根据运单ID查询
     * 
     * @author Libin.Wei
     * @Date 2017年4月19日 下午3:14:14
     * @param waybillId
     *            运单ID
     * @return List<WaybillBindFence>
     */
    List<WaybillBindFence> listByWaybillIdForUpdate(Integer waybillId);

    /**
     * 
     * 获取目的地已完成或未完成的配送点的数量
     * @author Libin.Wei
     * @Date 2017年4月19日 下午5:37:08
     * @param waybillId 运单ID
     * @param status 状态
     * @return
     */
    int findReceivePointNo(Integer waybillId, Integer status);

    /**
     * 
     * 添加
     * 
     * @author Libin.Wei
     * @Date 2017年4月19日 下午3:14:59
     * @param waybillBindFence
     *            运单绑定电子围栏信息
     * @param loginUser
     *            当前登录人
     */
    void insert(WaybillBindFence waybillBindFence, LoginUser loginUser);

    /**
     * 
     * 修改
     * 
     * @author Libin.Wei
     * @Date 2017年4月19日 下午3:14:59
     * @param waybillBindFence
     *            运单绑定电子围栏信息
     * @param loginUserId
     *            当前登录人账号Id
     */
    void update(WaybillBindFence waybillBindFence, LoginUser loginUser);

    /**
     * 
     * 根据运单ID将电子围栏ID修改为失效
     * 
     * @author Libin.Wei
     * @Date 2017年4月19日 下午3:14:59
     * @param waybillId
     *            运单ID
     * @param loginUser
     *            当前登录人
     */
    void updateFenceToInvalid(Integer waybillId, LoginUser loginUser);

    /**
     * 
     * @Title:       findByFence   
     * @Description: 查询  到仓
     * @return:      WaybillBindFence      
     * @throws
     */
    WaybillBindFence findByFenceReach(Integer waybillId);
}
