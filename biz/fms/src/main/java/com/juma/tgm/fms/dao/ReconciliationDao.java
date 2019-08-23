package com.juma.tgm.fms.dao;

import java.util.List;

import com.giants.common.tools.PageCondition;
import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.fms.domain.Reconciliation;
import com.juma.tgm.fms.domain.bo.ReconciliationMaster;

/*
 * 
 * @author  2017-10-16
 * @version 1.0 
 */

public interface ReconciliationDao extends MybatisDao<Reconciliation> {
    
    List<ReconciliationMaster> searchMaster(PageCondition pageCondition);
    
    Integer getNextSequence(String dataBaseName);
    
    List<ReconciliationMaster> findItemsById(PageCondition cond);

    int countItemsPageById(PageCondition cond);

}
