package com.juma.tgm.project.dao;

import java.util.List;
import java.util.Map;

import com.giants.common.tools.PageCondition;
import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.project.domain.Project;

/*
 * 
 * @author  2017-09-26
 * @version 1.0 
 */

public interface ProjectDao extends MybatisDao<Project> {

    int checkName(Map<String, Object> map);

    /**
     * 链表查询
     * @param pageCondition
     * @return
     */
    List<Project> joinSearch(PageCondition pageCondition);

    /**
     * 链表查询数量
     * @param pageCondition
     * @return
     */
    int joinSearchCount(PageCondition pageCondition);
}
