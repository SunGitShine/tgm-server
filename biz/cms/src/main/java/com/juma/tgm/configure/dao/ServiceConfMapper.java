package com.juma.tgm.configure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.juma.tgm.configure.domain.ServiceConf;
import com.juma.tgm.configure.domain.ServiceConfExample;

public interface ServiceConfMapper {
    long countByExample(ServiceConfExample example);

    int deleteByExample(ServiceConfExample example);

    int deleteByPrimaryKey(Integer serviceConfId);

    int insert(ServiceConf record);

    int insertSelective(ServiceConf record);

    List<ServiceConf> selectByExample(ServiceConfExample example);

    ServiceConf selectByPrimaryKey(Integer serviceConfId);

    int updateByExampleSelective(@Param("record") ServiceConf record, @Param("example") ServiceConfExample example);

    int updateByExample(@Param("record") ServiceConf record, @Param("example") ServiceConfExample example);

    int updateByPrimaryKeySelective(ServiceConf record);

    int updateByPrimaryKey(ServiceConf record);
}