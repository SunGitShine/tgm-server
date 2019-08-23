package com.juma.tgm.receiptManage.dao;

import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.receiptManage.domain.ReceiptManage;

/*
 * 
 * @author  2017-07-10
 * @version 1.0 
 */

public interface ReceiptManageDao extends MybatisDao<ReceiptManage> {

    void deleteBy(ReceiptManage example);
}
