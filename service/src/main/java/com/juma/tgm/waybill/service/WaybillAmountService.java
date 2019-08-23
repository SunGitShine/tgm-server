package com.juma.tgm.waybill.service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.domain.WaybillAmount;
import com.juma.tgm.waybill.domain.WaybillAmountValidVO;
import com.juma.tgm.waybill.domain.vo.WaybillAmountFilter;

import java.util.List;
import java.util.Map;

/**
 * 运单费用表->基本功能 功能 : 1.插入 2.更新
 *
 * @author : Bruce(刘正航) 20:21 2019-05-13
 */
public interface WaybillAmountService {

    /**
     * 添加费用表记录
     **/
    void add(WaybillAmount amount, LoginUser loginUser) throws BusinessException;

    /**
     * 添加费用表记录
     **/
    void addBatch(List<WaybillAmount> amounts, LoginUser loginUser) throws BusinessException;

    /**
     * 更新费用表记录
     **/
    void update(WaybillAmount amount, LoginUser loginUser) throws BusinessException;

    /**
     * 批量修改
     *
     * @param amounts
     * @param loginUser
     *
     * @throws BusinessException
     */
    void updateBatch(List<WaybillAmount> amounts, LoginUser loginUser) throws BusinessException;

    /**
     * 根据运单Id
     **/
    void updateByWaybillId(WaybillAmount amount, LoginUser loginUser) throws BusinessException;

    /**
     * 更具运单ID查询运单费用
     **/
    WaybillAmount loadByWaybillId(Integer waybillId) throws BusinessException;

    /**
     * 根据过滤条件查询运单费用列表
     * @param filter
     * @return
     * @throws BusinessException
     */
    List<WaybillAmount> findByFilter(WaybillAmountFilter filter, LoginUser loginUser) throws BusinessException;

    /**
     * 根据过滤条件查询运单费用列表
     * @param filter
     * @return
     * @throws BusinessException
     */
    List<WaybillAmount> findByWaybillIds(List<Integer> waybillIds, LoginUser loginUser) throws BusinessException;

    /**根据过滤条件查询运单费用表**/
    Map<Integer,WaybillAmount> findDictionaryByWaybillIds(List<Integer> waybillIds) throws BusinessException;

    /**
     * 添加或修改运单费用信息： 兼容老数据，数据是在下单时创建，但是老数据没有这条记录
     *
     * @param waybillAmount
     * @param loginUser
     *
     * @throws BusinessException
     */
    void addOrUpdate(WaybillAmount waybillAmount, LoginUser loginUser) throws BusinessException;

    /**
     * 批量添加或修改运单费用信息： 兼容老数据，数据是在下单时创建，但是老数据没有这条记录
     *
     * @param amounts
     * @param loginUser
     *
     * @throws BusinessException
     */
    void addOrUpdateBatch(List<WaybillAmount> amounts, LoginUser loginUser) throws BusinessException;

    /**获取运单金额(运费限制)**/
    WaybillAmountValidVO getWaybillAmountLimit() throws BusinessException;
}
