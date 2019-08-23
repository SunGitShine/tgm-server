package com.juma.tgm.scatteredWaybill.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginEcoUser;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.landingWaybill.domain.AtFenceResultVo;
import com.juma.tgm.waybill.domain.CityAdressData;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillDetailInfo;
import com.juma.tgm.waybill.domain.vo.DistanceAndPriceParamVo;
import com.juma.tgm.waybill.domain.vo.ScatteredWaybillCreateVo;
import com.juma.tgm.waybill.domain.vo.ScatteredWaybillViewVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 落地配Service
 * @ClassName: scatteredwaybillservice
 * @Description:
 * @author: liang
 * @date: 2017-11-13 11:27
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public interface ScatteredWaybillService {


    //运单列表

    /**
     * 落地配app运单列表
     * @param pageCondition
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Page<ScatteredWaybillViewVo> searchForApp(PageCondition pageCondition, LoginUser loginUser) throws BusinessException;
    //运单详情

    /**
     * 通过运单id获取运单详情
     * @param waybillId
     * @return
     * @throws BusinessException
     */
    ScatteredWaybillViewVo getDetail(int waybillId, LoginUser loginUser) throws BusinessException;

    //新建运单

    /**
     * 货主新建落地配运单
     * @param scatteredWaybillCreateVo
     * @param loginEcoUser
     * @return
     * @throws BusinessException
     */
    Integer createScatteredWaybillForCargoOwner(ScatteredWaybillCreateVo scatteredWaybillCreateVo, LoginEcoUser loginEcoUser) throws BusinessException;

    /**
     * 客户经理新建落地配运单
     * @param scatteredWaybillCreateVo
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    List<Integer> createScatteredWaybillForCustomerManager(ScatteredWaybillCreateVo scatteredWaybillCreateVo, LoginUser loginUser) throws BusinessException;
    
    
    /**
     * @throws
     * @Title: changeToDeliveried
     * @Description: 司机配送完成
     * @return: void
     */
    void changeToDeliveried(Waybill waybill, LoginUser loginUser) throws BusinessException;

    /**
     * 确认已收到代收货款
     *
     * @param waybillId
     * @param loginEmployee
     * @throws BusinessException
     */
    void confirmFeeDelivery(int waybillId, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 确认运费已收到
     * @param waybillId
     * @param loginEmployee
     * @throws BusinessException
     */
    void confirmFreightAccept(int waybillId, LoginEmployee loginEmployee) throws BusinessException;


    /**
     * 判断 运单是否需要入城，是否在业务区域
     * @param srcAddress
     * @param toAddress
     * @throws BusinessException
     */
    AtFenceResultVo isAtFenceArea(CityAdressData srcAddress, List<CityAdressData> toAddress, LoginUser loginUser) throws BusinessException;

    /**
     * 客户经理取消运单
     * @param waybill
     * @param loginEmployee
     * @throws BusinessException
     */
    void customerManagerCancelBill(Waybill waybill, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 货主取消运单
     * @param waybill
     * @param loginEcoUser
     * @throws BusinessException
     */
    void cargoOwnerCancelBill(Waybill waybill, LoginEcoUser loginEcoUser) throws BusinessException;
    
    /**
     * 算价（不含税价格）
     * @Title: computeForCarpool   
     * @Description: 算价
     * @param:  distanceAndPriceParamVo
     * @return: void      
     * @throws
     */
    BigDecimal computeFreight(DistanceAndPriceParamVo distanceAndPriceParamVo, LoginUser loginUser) throws BusinessException;

    /**
     * 再次下单
     * @param waybillId
     * @return
     * @throws BusinessException
     */
    WaybillDetailInfo getScatteredBillDetail(int waybillId, LoginUser loginUser) throws BusinessException;

}
