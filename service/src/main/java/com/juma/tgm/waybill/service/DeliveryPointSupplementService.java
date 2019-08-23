package com.juma.tgm.waybill.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginEcoUser;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.domain.DeliveryPointSupplement;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.domain.vo.WaybillQueryVo;

import java.util.List;

/**
 * 补充配送点
 * @ClassName: DeliveryPointSupplementService
 * @Description:
 * @author: liang
 * @date: 2017-04-27 14:43
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public interface DeliveryPointSupplementService {


    /**
     * 添加补充配送点
     * @param deliveryPointSupplement
     * @param loginEcoUser
     * @return
     * @throws BusinessException
     */
    Integer add(DeliveryPointSupplement deliveryPointSupplement, LoginEcoUser loginEcoUser) throws BusinessException;

    /**
     * 批量新增补充配送点
     * @param deliveryPointSupplements
     * @param loginEcoUser
     * @return
     * @throws BusinessException
     */
    List<Integer> addBatch(List<DeliveryPointSupplement> deliveryPointSupplements, LoginEcoUser loginEcoUser) throws BusinessException;

    /**
     * 修改补充配送点
     * @param deliveryPointSupplement
     * @param loginEcoUser
     * @throws BusinessException
     */
    void update(DeliveryPointSupplement deliveryPointSupplement, LoginEcoUser loginEcoUser) throws BusinessException;

    /**
     * 删除补充配送点
     * @param deliveryPointSupplement
     * @param loginEmployee
     * @throws BusinessException
     */
    void del(DeliveryPointSupplement deliveryPointSupplement, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 标记配送单无效
     * 
     * @param waybillId
     * @return
     * @throws BusinessException
     */
    void invalid(Integer waybillId, LoginUser loginUser) throws BusinessException;

    /**
     * 通过主键获取补充配送点
     * @param id
     * @return
     * @throws BusinessException
     */
    DeliveryPointSupplement get(int id) throws BusinessException;

    /**
     * 通过运单id获取补充配送点
     * @param id
     * @return
     * @throws BusinessException
     */
    List<DeliveryPointSupplement> getByWayBill(Integer id) throws BusinessException;


    /**
     * 通过运单id获取补充配送点数量
     * @param waybillId
     * @return
     * @throws BusinessException
     */
    int countDeliveryPointSupplement(int waybillId) throws BusinessException;


    /******************************************更新运单配送点-开始**************************************/

    /**
     * 更新运单配送目的地并同时更新价格距离信息
     * @param addresses
     * @param loginEmployee
     * @throws BusinessException
     */
    void updateWaybillReceiveAddress(List<WaybillReceiveAddress> addresses, LoginEmployee loginEmployee) throws BusinessException;

    /******************************************更新运单配送点-结束**************************************/

    /**
     * 修改路线列表
     * @param pageCondition
     * @param loginEmployee
     * @return Page<WaybillQueryVo>
     * @throws BusinessException
     */
    Page<WaybillQueryVo> search(PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException;
}
