package com.juma.tgm.fms.dao;

import java.util.List;

import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.fms.domain.ReconciliationItem;
import com.juma.tgm.fms.domain.bo.ReconciliationMaster;

/*
 * 
 * @author  2017-10-16
 * @version 1.0 
 */

public interface ReconciliationItemDao extends MybatisDao<ReconciliationItem> {

    void updateAll(List<ReconciliationItem> rows);
    
    ReconciliationMaster sumItemByReconciliationId(ReconciliationMaster master);
    
    void updateReconciliationItemBatch(ReconciliationItem item);
}
