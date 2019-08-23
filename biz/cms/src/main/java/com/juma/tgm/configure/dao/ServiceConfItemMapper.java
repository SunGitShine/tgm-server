package com.juma.tgm.configure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.juma.tgm.configure.domain.ServiceConfItem;
import com.juma.tgm.configure.domain.ServiceConfItemExample;

public interface ServiceConfItemMapper {
    long countByExample(ServiceConfItemExample example);

    int deleteByExample(ServiceConfItemExample example);

    int deleteByPrimaryKey(Integer serviceConfItemId);

    int insert(ServiceConfItem record);

    int insertSelective(ServiceConfItem record);

    List<ServiceConfItem> selectByExample(ServiceConfItemExample example);

    ServiceConfItem selectByPrimaryKey(Integer serviceConfItemId);

    int updateByExampleSelective(@Param("record") ServiceConfItem record, @Param("example") ServiceConfItemExample example);

    int updateByExample(@Param("record") ServiceConfItem record, @Param("example") ServiceConfItemExample example);

    int updateByPrimaryKeySelective(ServiceConfItem record);

    int updateByPrimaryKey(ServiceConfItem record);
}