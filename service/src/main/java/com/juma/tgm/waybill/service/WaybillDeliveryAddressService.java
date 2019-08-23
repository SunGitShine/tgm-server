package com.juma.tgm.waybill.service;

import com.giants.common.exception.BusinessException;
import java.util.List;

import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddressBo;

/*
 *
 * @author  2016-05-09
 * @version 1.0
 * 运单发货地址
 */

public interface WaybillDeliveryAddressService {

    /**
     * 批量插入
     * 
     * @param waybillDeliveryAddresses
     */
    void batchInsert(List<WaybillDeliveryAddress> waybillDeliveryAddresses);

    /**
     * 批量更新
     * 
     * @param waybillDeliveryAddresses
     */
    void batchUpdate(List<WaybillDeliveryAddress> waybillDeliveryAddresses);
    
    /**
     * 根据条件查询所有对象
     * 
     * @param waybillDeliveryAddress
     * @return
     */
    List<WaybillDeliveryAddress> selectEntryListByCondition(WaybillDeliveryAddress waybillDeliveryAddress);

    /**
     * 根据运单ID查询所有对象
     * 
     * @param waybillId
     *            运单ID
     * @return List<WaybillDeliveryAddress>
     */
    List<WaybillDeliveryAddress> findAllByWaybillId(Integer waybillId);

    /**
     * 根据USERID查询最近5条记录
     * 
     * @param userId
     *            用户ID
     * @return List<WaybillDeliveryAddress>
     */
    List<WaybillDeliveryAddress> findAllByUserId(Integer userId);

    /**
     * 
     * @Title: findByWaybillId @Description: 运单Id找发货信息 @param @param
     * waybillId @param @return @return WaybillDeliveryAddress @throws
     */
    WaybillDeliveryAddress findByWaybillId(Integer waybillId);

    /**
     * 
     * 获取基础的取货地信息
     * @author Libin.Wei
     * @Date 2017年11月6日 下午4:39:20
     * @param waybillId
     * @return
     */
    WaybillDeliveryAddress findSimpleByWaybillId(Integer waybillId);

    /**
     * 根据运单ID获取取货地中文列表
     * 
     * @param waybillId
     *            运单ID
     * @return String
     */
    String getDeliveryAddressStr(Integer waybillId);

    /**
     * 根据运单ID查询所有对象BO
     * 
     * @param waybillId
     *            运单ID
     * @return List<WaybillDeliveryAddressBo>
     */
    List<WaybillDeliveryAddressBo> findAllBoByWaybillId(Integer waybillId);

    /**
     * 添加取货地信息
     * 
     * @param deliveryAddressList
     * @param waybillId
     * @param loginUser
     */
    void insert(List<WaybillDeliveryAddress> deliveryAddressList, Integer waybillId, LoginUser loginUser);

    /**
     * 根据运单ID获取手机号
     * 
     * @param waybillId
     */
    String getPhone(Integer waybillId);

    /**
     * 
     * @Description 删除
     * @author Libin.Wei
     * @Date 2017年1月13日 下午2:54:20
     * @param waybillid
     */
    void delete(Integer waybillid);
    
    
    /**
     * 
     * @Title:       insert   
     * @Description: 新增记录   
     * @param:       @param waybillDeliveryAddress
     * @param:       @param loginUser      
     * @return:      void      
     * @throws
     */
    void insert(WaybillDeliveryAddress waybillDeliveryAddress,LoginUser loginUser);

    /**
     * 
     * @Description 修改
     * @author Libin.Wei
     * @Date 2017年2月28日 上午9:56:23
     * @param waybillDeliveryAddress
     * @param loginUser
     */
    void update(WaybillDeliveryAddress waybillDeliveryAddress, LoginUser loginUser);

    /**
     * 
     * 根据ID获取取货地信息
     * @author Libin.Wei
     * @Date 2017年4月28日 下午2:18:38
     * @param addressId 取货地ID
     * @return WaybillDeliveryAddress
     */
    WaybillDeliveryAddress findByAddressId(Integer addressId);

    /**
     * 
     * 修改取货地
     * 
     * @author Libin.Wei
     * @Date 2017年7月17日 下午2:47:22
     * @param waybillDeliveryAddress
     * @param loginUser
     */
    void updateDeliveryAddress(WaybillDeliveryAddress waybillDeliveryAddress, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 根据运单ID获取配送点数量
     * 
     * @author Libin.Wei
     * @Date 2017年9月26日 上午10:17:27
     * @param waybillId
     * @return
     */
    int findCountByWaybillId(int waybillId);
}
