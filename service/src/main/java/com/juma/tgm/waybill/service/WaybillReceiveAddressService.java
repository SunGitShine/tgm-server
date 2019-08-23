package com.juma.tgm.waybill.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.domain.WaybillReceiveAddressBo;
import com.juma.tgm.waybill.domain.WaybillReceiveAddressResponse;

public interface WaybillReceiveAddressService {

    void batchInsert(Integer waybillId,List<WaybillReceiveAddress> waybillReceiveAddressList,LoginUser loginUser) throws BusinessException;

    /**
     * 批量插入
     * 
     * @param listResponse
     */
    void batchInsert(List<WaybillReceiveAddressResponse> listResponse);

    /**
     * 批量更新
     * @param listResponse
     */
    void batchUpdate(List<WaybillReceiveAddressResponse> listResponse);
    
    /**
     * 根据运单ID查询所有
     * 
     * @param waybillId
     *            运单ID
     * @return List<WaybillReceiveAddress>
     */
    List<WaybillReceiveAddress> findAllByWaybillId(Integer waybillId);

    /**
     * 根据userID查询最近5条记录
     * 
     * @param userId
     * @return List<WaybillReceiveAddress>
     */
    List<WaybillReceiveAddress> findAllByUserId(Integer userId);

    /**
     * 根据运单ID获取目的地中文列表
     * 
     * @param waybillId
     *            运单ID
     * @return String
     */
    String getReceiveAddressStr(Integer waybillId);

    /**
     * 根据运单ID查询所有BO
     * 
     * @param waybillId
     *            运单ID
     * @return List<WaybillReceiveAddressBo>
     */
    List<WaybillReceiveAddressBo> findAllBoByWaybillId(Integer waybillId);

    /**
     * 根据运单ID查询目的地数量
     * 
     * @param waybillId
     *            运单ID
     * @return int
     */
    int findNumByWaybillId(Integer waybillId);

    /**
     * 添加目的地信息
     * 
     * @param receiveAddressList
     * @param waybillId
     * @param userId
     */
    void insert(List<WaybillReceiveAddressResponse> listAddressResponse, Integer waybillId,LoginUser loginUser);

    /**
     * 
     * 根据主键ID删除
     * @author Libin.Wei
     * @Date 2017年6月23日 下午2:03:18
     * @param addressId
     */
    void delete(Integer addressId);

    /**
     * 
     * @Description 删除
     * @author Libin.Wei
     * @Date 2017年1月13日 下午2:55:05
     * @param waybillId
     */
    void deleteByWaybillId(Integer waybillId);

    /**
     * 
     * @Description 批量获取目的地数量
     * @author Libin.Wei
     * @Date 2017年1月18日 下午12:46:31
     * @param waybillIdList
     * @return
     */
    List<WaybillReceiveAddress> findNumListByWaybillId(List<Integer> waybillIdList);
    
    
    /**
     * 
     * @Title:       insert   
     * @Description: 新增记录
     * @param:       @param WaybillReceiveAddress
     * @param:       @param loginUser      
     * @return:      void      
     * @throws
     */
    int insert(WaybillReceiveAddress waybillReceiveAddress,LoginUser loginUser);

   /**
    * 
    * @Description 修改
    * @author Libin.Wei
    * @Date 2017年2月28日 上午10:11:03
    * @param waybillReceiveAddress
    * @param loginUser
    */
    void update(WaybillReceiveAddress waybillReceiveAddress,LoginUser loginUser);

    /**
     * 
     * 根据地址ID获取
     * @author Libin.Wei
     * @Date 2017年4月28日 下午2:54:50
     * @param addressId 地址ID
     * @return WaybillReceiveAddress
     */
    WaybillReceiveAddress getWaybillReceiveAddress(Integer addressId);
    
    int updateBatchByPrimaryKeySelective(List<WaybillReceiveAddress> list);

    /**
     * 根据运单ID获取基础的配送地信息
     *
     * @param waybillId
     *
     * @return
     */
    List<WaybillReceiveAddress> listSimpleReceiveAddressByWaybillId(Integer waybillId);
}
