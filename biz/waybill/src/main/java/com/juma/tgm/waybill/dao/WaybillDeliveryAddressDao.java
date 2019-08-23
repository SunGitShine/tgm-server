package com.juma.tgm.waybill.dao;

import java.util.List;

import com.giants.dal.dao.GiantsDao;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.example.WaybillDeliveryAddressExample;

public interface WaybillDeliveryAddressDao extends GiantsDao<WaybillDeliveryAddress> {

    /**
     * 批量插入
     * 
     * @param waybillDeliveryAddresses
     */
    public void batchInsert(List<WaybillDeliveryAddress> waybillDeliveryAddresses);

    /**
     * 批量更新
     * 
     * @param waybillDeliveryAddresses
     */
    public void batchUpdate(List<WaybillDeliveryAddress> waybillDeliveryAddresses);

    /**
     * 根据条件查询所有对象
     * 
     * @param waybillDeliveryAddress
     * @return
     */
    List<WaybillDeliveryAddress> selectEntryListByCondition(WaybillDeliveryAddress waybillDeliveryAddress);

    /**
     * 得到最近使用的5个地址
     * 
     * @param userId
     * @return
     */
    List<WaybillDeliveryAddress> getWaybillDeliveryLastAddress(Integer userId);

    /**
     * 
     */
    WaybillDeliveryAddress findByWaybillId(Integer waybillId);

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

    List<WaybillDeliveryAddress> selectByExample(WaybillDeliveryAddressExample example);
}
