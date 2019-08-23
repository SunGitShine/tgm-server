package com.juma.tgm.user.dao;

import java.util.List;

import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.user.domain.DepartmentAC;

/*
 * 
 * @author  2017-02-22
 * @version 1.0 
 */

public interface DepartmentAcDao extends MybatisDao<DepartmentAC> {

    List<DepartmentAC> findByRegionCode(String regionCode);
    
    DepartmentAC findByDepartmentId(Integer departmentId);
    
    List<DepartmentAC> findByDepartmentIds(List<Integer> departmentIds);
    
    void updateByDepartmentId(DepartmentAC departmentAC);
}
