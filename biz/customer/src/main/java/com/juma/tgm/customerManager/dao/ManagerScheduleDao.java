package com.juma.tgm.customerManager.dao;

import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.customerManager.domain.ManagerSchedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 * 
 * @author  2017-06-15
 * @version 1.0 
 */

public interface ManagerScheduleDao extends MybatisDao<ManagerSchedule> {

    /**
     * 按参数统计客户经理下未处理数量
     * @param managerSchedule
     * @return
     */
    int countByParam(ManagerSchedule managerSchedule);

    /**
     * 客户经理下未处理列表
     * @param customerManagerId
     * @return
     */
    List<ManagerSchedule> unhandledList(@Param("customerManagerId") Integer customerManagerId);

}
