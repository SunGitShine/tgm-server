package com.juma.tgm.waybill.dao;

import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.waybill.domain.DeliveryPointSupplement;

import java.util.List;

/**
 * @ClassName: DeliveryPointSupplementDao
 * @Description:
 * @author: liang
 * @date: 2017-04-27 14:28
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public interface DeliveryPointSupplementDao extends MybatisDao<DeliveryPointSupplement> {

    /**
     * 通过运单id获取所有运单补充信息
     * @param waybillId
     * @return
     */
    List<DeliveryPointSupplement> findByWayBill(int waybillId);

}
