package com.juma.tgm.waybill.dao;

import java.util.List;

import com.giants.dal.dao.GiantsDao;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.domain.example.WaybillReceiveAddressExample;

public interface WaybillReceiveAddressDao extends GiantsDao<WaybillReceiveAddress> {

    /**
     * 批量插入
     * @param waybillReceiveAddresses
     */
    void batchInsert(List<WaybillReceiveAddress> waybillReceiveAddresses);
    
    /**
     * 批量更新
     * @param waybillReceiveAddresses
     */
    void batchUpdate(List<WaybillReceiveAddress> waybillReceiveAddresses);

    /**
     * 根据条件查询所有对象
     * @param waybillReceiveAddress
     * @return
     */
    List<WaybillReceiveAddress> selectEntryListByCondition(WaybillReceiveAddress waybillReceiveAddress);

    /**
     * 得到最近使用的5个地址
     * @param userId
     * @return
     */
    List<WaybillReceiveAddress> getWaybillReceiveLastAddress(Integer userId);

    /**
     * 根据运单号获取发货地址
     * @param waybillId
     * @return
     */
    WaybillReceiveAddress findByWaybillId(Integer waybillId);

    /**
     * 根据运单ID查询目的地数量
     * 
     * @param waybillId 运单ID
     * @return int
     */
    int findNumByWaybillId(Integer waybillId);

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
     * 删除
     * 
     * @author Libin.Wei
     * @Date 2017年6月23日 下午2:07:10
     * @param waybillId
     */
    void deleteByWaybillId(Integer waybillId);
    
    List<WaybillReceiveAddress> selectByExample(WaybillReceiveAddressExample example);
    
    int updateBatchByPrimaryKeySelective(List<WaybillReceiveAddress> list);
}
