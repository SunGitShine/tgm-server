package com.juma.tgm.waybill.service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.erp.ErpWaybillResult;
import com.juma.tgm.waybill.domain.vo.WaybillParamFilter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 运单费用
 * 
 * @author weilibin
 *
 */

public interface WaybillParamService {

    /**
     * 添加
     * 
     * @param waybillParam
     * @param loginUser
     */
    void insert(WaybillParam waybillParam, LoginUser loginUser) throws BusinessException;

    /**
     * @Description 添加或修改配送点数
     * @author Libin.Wei
     * @Date 2016年12月21日 下午2:47:45
     * @param waybillParam
     *            运单条件信息
     * @param loginUser
     *            当前登录人信息
     * @throws BusinessException
     * @return void
     */
    void addOrUpdateOnly(WaybillParam waybillParam, LoginUser loginUser) throws BusinessException;
    
    /**
     * @Description 添加或修改搬运费
     * @author Libin.Wei
     * @Date 2016年12月21日 下午2:47:45
     * @param waybillParam
     *            运单条件信息
     * @param loginUser
     *            当前登录人信息
     * @throws BusinessException
     * @return void
     */
    void addOrUpdate(WaybillParam waybillParam, LoginUser loginUser) throws BusinessException;

    /**
     * 更新
     * 
     * @param waybillParam
     * @param loginUser
     */
    void update(WaybillParam waybillParam, LoginUser loginUser) throws BusinessException;

    /**
     * 更新
     * 
     * @param waybillParam
     * @param loginUser
     */
    void batchUpdateErpResult(List<ErpWaybillResult> rows, LoginUser loginUser) throws BusinessException;

    /**
     * 根据运单Id查询对应的model
     * 
     * @param waybillId
     *            运单ID
     * @return WaybillParam
     */
    WaybillParam findByWaybillId(Integer waybillId);

    /**
     * 根据运单Id获取搬运费
     * 
     * @param waybillId
     *            运单ID
     * @return BigDecimal
     */
    BigDecimal buildHandlingCost(Integer waybillId);

    /**
     * 根据运单ID集合获取费用
     * 
     * @param list
     * @return WaybillParam
     */
    WaybillParam findCountCost(List<Integer> list);
    
    /**
     * 
     * @Title:       driverReadWaybill   
     * @Description: 司机已阅读 
     * @return:      void      
     * @throws
     */
    void driverReadWaybill(Integer waybillId,LoginUser loginUser);

    /**
     * 
     * @Title:       driverReadWaybill   
     * @Description: 司机未阅读 
     * @return:      void      
     * @throws
     */
    void driverUnReadWaybill(Integer waybillId,LoginUser loginUser);

    /**
     * 
     * 确认已验收货物
     * @author Libin.Wei
     * @Date 2018年7月26日 下午3:20:14
     * @param waybillId
     * @param loginUser
     * @throws BusinessException
     */
    void confirmHasCheckGoods(Integer waybillId,LoginUser loginUser) throws BusinessException;

    /**
     * 
     * @Title: doCompleteWaybillParam   
     * @Description: 完善运单信息
     * @param: @param waybillId
     * @param: @param valuationConstJson      
     * @return: void      
     * @throws
     */
    void doCompleteWaybillParam(Integer waybillId,String valuationConstJson,LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 根据承运商运单ID获取
     * 
     * @author Libin.Wei
     * @Date 2018年8月30日 下午4:18:01
     * @param transformBillLinkId
     * @return
     */
    WaybillParam findByTransformBillLinkId(Integer transformBillLinkId);

    /**根据条件查询运单参数列表**/
    List<WaybillParam> findByFilter(WaybillParamFilter filter) throws BusinessException;

    /**根据运单ID查询运单参数列表**/
    List<WaybillParam> findByWaybillIds(List<Integer> waybillIds) throws BusinessException;
}
