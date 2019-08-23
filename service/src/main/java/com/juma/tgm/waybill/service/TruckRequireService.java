package com.juma.tgm.waybill.service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.vo.TruckRequireFilter;

import java.util.List;
import java.util.Map;

/**
 * 
 * @Description: 用车要求
 * @author weilibin
 * @date 2016年8月3日 下午1:54:16
 * @version V1.0
 */

public interface TruckRequireService {

    /**
     * 
     * @Title: getWaybillRequireByWaybillId
     * @Description: 根据运单ID获取运单的用车要求
     * @param waybillId 运单ID
     * @return WaybillRequire
     */
    TruckRequire findTruckRequireByWaybillId(Integer waybillId, LoginUser loginUser);

    /**
     * 根据运单ID获取
     *
     * @param waybillId
     * @return
     */
    TruckRequire loadReuckRequireByWaybillId(Integer waybillId);

    /**
     * 
     * @Title: insert
     * @Description: 添加用车要求
     * @param waybillRequire 用车要求信息
     */
    void insert(TruckRequire truckRequire) throws BusinessException;

    /**
     * 
     * @Title: update
     * @Description: 修改用车要求
     * @param waybillRequire 用车要求信息
     */
    void update(TruckRequire truckRequire) throws BusinessException;

    /**
     * 
     * @Description 清空不需要的信息(例如：页面没有勾选利率，清空数据库中利率)
     * @author Libin.Wei
     * @Date 2017年2月16日 上午9:50:22
     * @param truckRequire 用车要求
     * @throws BusinessException
     */
    void removeNullInfo(TruckRequire truckRequire) throws BusinessException;

    /**
     * 
     * @Description 获取用车要求中文展示
     * @author Libin.Wei
     * @Date 2017年2月9日 下午2:28:34
     * @param truckRequire 用车要求
     * @param waybillId 运单ID
     * @return
     */
    String getTruckRequire(TruckRequire truckRequire, Integer waybillId);

    /**
     * 
     * @Title: isNeedInvoice
     * @Description: 根据运单ID判断是不是需要开票
     * @param waybillId 运单ID
     */
    boolean isNeedInvoice(Integer waybillId);

    /**
     * 
     * @Title: isCoolChain
     * @Description: 判断冷链是否选择
     * @param truckRequire 用车要求
     * @param waybillId 运单ID
     * @return boolean
     */
    boolean isColdChain(TruckRequire truckRequire, Integer waybillId);

    /**
     * 
     * @Title: needCollectionPayment
     * @Description: 判断是否代收货款
     * @param truckRequire 用车要求
     * @param waybillId 运单ID
     * @return boolean
     */
    boolean needCollectionPayment(TruckRequire truckRequire, Integer waybillId);

    /**
     * 
     * @Title: needCollectionPayment
     * @Description: 判断是否回单
     * @param truckRequire 用车要求
     * @param waybillId 运单ID
     * @return boolean
     */
    boolean needNeedReceipt(TruckRequire truckRequire, Integer waybillId);

    /**
     * 
     * @Title: getImportantInfo
     * @Description: 根据运单ID获取用车重要信息
     * @param waybillId 运单ID
     */
    String getImportantInfo(Integer waybillId);

    /**
     * 
     * @Title: getGoodsFullName
     * @Description: 货物信息全称
     * @param truckRequire
     * @param waybillId
     */
    String getGoodsFullName(TruckRequire truckRequire, Integer waybillId);

    /**
     * 
     * 判断货物类型是不是自定义的
     * @author Libin.Wei
     * @Date 2017年5月5日 上午9:58:57
     * @param goodsType 货物类型,为空返回false
     * @return true:是自定义；    false:不是自定义
     */
    boolean whetherGoodsTypeCustomized(String goodsType);


    /**
     * 删除运单用车要求
     * @throws BusinessException
     */
    void deleteTruckRequiredByWaybill(Integer waybillId) throws BusinessException;

    /**
     * 组装用车要求
     * @param truckRequire
     * @param sf
     * @return
     * @throws BusinessException
     */
    String getTruckRequireString(TruckRequire truckRequire, StringBuffer sf) throws BusinessException;

    /**根据查询条件返回税率**/
    List<TruckRequire> findByFilter(TruckRequireFilter filter, LoginUser loginUser) throws BusinessException;

    /**根据运单ID,查询税率等信息**/
    List<TruckRequire> findByWaybillIds(List<Integer> waybillIds) throws BusinessException;

    /**根据运单ID,查询税率等信息**/
    Map<Integer,TruckRequire> findDictionaryByWaybillIds(List<Integer> waybillIds) throws BusinessException;
}
