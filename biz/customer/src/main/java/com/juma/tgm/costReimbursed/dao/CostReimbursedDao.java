package com.juma.tgm.costReimbursed.dao;

import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.costReimbursed.domain.CostReimbursed;

/**
 * 
 * @ClassName CostReimbursedDao.java 费用报销
 * @author Libin.Wei
 * @Date 2017年7月10日 下午2:23:38
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface CostReimbursedDao extends MybatisDao<CostReimbursed> {

    /**
     * 
     * 得到下个序列
     * 
     * @author Libin.Wei
     * @Date 2017年7月10日 下午4:19:11
     * @param dataBaseName
     * @return
     */
    Integer getNextSequence(String dataBaseName);

    /**
     * 根据流水号获取报销对象
     */
    CostReimbursed getByCostReimbursedNo(String costReimbursedNo);


}
