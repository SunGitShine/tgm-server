package com.juma.tgm.configure.dao;

import com.juma.tgm.configure.domain.PrivateFreightFactor;
import com.juma.tgm.configure.domain.PrivateFreightFactorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PrivateFreightFactorMapper {
    long countByExample(PrivateFreightFactorExample example);

    int deleteByExample(PrivateFreightFactorExample example);

    int deleteByPrimaryKey(Integer privateFreightFactorId);

    int insert(PrivateFreightFactor record);

    int insertSelective(PrivateFreightFactor record);

    List<PrivateFreightFactor> selectByExample(PrivateFreightFactorExample example);

    PrivateFreightFactor selectByPrimaryKey(Integer privateFreightFactorId);

    int updateByExampleSelective(@Param("record") PrivateFreightFactor record, @Param("example") PrivateFreightFactorExample example);

    int updateByExample(@Param("record") PrivateFreightFactor record, @Param("example") PrivateFreightFactorExample example);

    int updateByPrimaryKeySelective(PrivateFreightFactor record);

    int updateByPrimaryKey(PrivateFreightFactor record);
}