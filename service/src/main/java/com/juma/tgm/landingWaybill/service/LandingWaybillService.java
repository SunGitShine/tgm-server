package com.juma.tgm.landingWaybill.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.landingWaybill.domain.LandingWaybill;

/**
 * @ClassName LandingWaybillService.java
 * @Description 落地配运单管理
 * @author Libin.Wei
 * @Date 2017年11月21日 下午4:14:00
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Deprecated
public interface LandingWaybillService {

    /**
     * 
     * 落地配运单：系统派车超时的运单列表
     * 
     * @author Libin.Wei
     * @Date 2017年11月21日 下午4:32:52
     * @param pageCondition
     * @param loginUser
     * @return
     */
    List<LandingWaybill> listLandingWatingReceiveWaybill(PageCondition pageCondition, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 根据ID获取并组装数据
     * 
     * @author Libin.Wei
     * @Date 2017年11月22日 下午2:29:50
     * @param waybillId
     * @return
     */
    LandingWaybill getLandingWaybill(int waybillId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 请写注释...
     * 
     * @author Libin.Wei
     * @Date 2017年11月30日 下午9:52:06
     * @param pageCondition
     * @param loginEmployee
     * @return
     */
    Page<LandingWaybill> listScatteredWaybill(PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 
     * 确认收款
     * 
     * @author Libin.Wei
     * @Date 2017年12月6日 下午7:07:10
     * @param waybillId
     * @param loginUser
     */
    void confirmReceipt(Integer waybillId, LoginUser loginUser) throws BusinessException;
}
