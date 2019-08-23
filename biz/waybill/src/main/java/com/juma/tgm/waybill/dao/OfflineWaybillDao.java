package com.juma.tgm.waybill.dao;

import java.util.List;

import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.waybillReport.domain.OfflineWaybill;

/*
 * 
 * @author  2017-02-08
 * @version 1.0 
 */

public interface OfflineWaybillDao extends MybatisDao<OfflineWaybill> {

        List<OfflineWaybill> findOfflineWaybillByIds(List<Integer> offlineWaybillIds);
        
        void deleteByIds(List<Integer> offlineWaybillIds);
    
}
