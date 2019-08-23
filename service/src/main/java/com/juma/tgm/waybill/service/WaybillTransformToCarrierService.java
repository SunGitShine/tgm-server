package com.juma.tgm.waybill.service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.transformbill.TransformBillMark;
import com.juma.tgm.waybill.domain.vo.WaybillCarrierVo;

/**
 * @ClassName: WaybillTransformToCarrierService
 * @Description:
 * @author: liang
 * @date: 2018-09-01 19:06
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
@Deprecated
public interface WaybillTransformToCarrierService {

    /**
     * 分发转运运单
     *
     * @param bill
     * @param loginUser
     */
    void dispatch(TransformBillMark bill, LoginUser loginUser) throws BusinessException;

    /**
     * 转运参数校验
     *
     * @param waybillCarrierVo
     */
    void transformBillCheck(WaybillCarrierVo waybillCarrierVo) throws BusinessException;


//    /**
//     * 回写转运单关联id到源运单
//     *
//     * @param sourceWaybillId
//     * @param transformBillId
//     */
//    void saveTransformBillLinkId(Integer sourceWaybillId, Integer transformBillId, LoginUser loginUser) throws BusinessException;


    /**
     * 修改源运单转运信息
     *
     * @param sourceBill 源运单
     * @param waybillParam 源运单param
     * @param waybillCarrierVo 承运信息
     * @param loginUser
     * @throws BusinessException
     */
    void saveTransformBillParam(Waybill sourceBill, WaybillParam waybillParam, WaybillCarrierVo waybillCarrierVo, LoginUser loginUser) throws BusinessException;

}
