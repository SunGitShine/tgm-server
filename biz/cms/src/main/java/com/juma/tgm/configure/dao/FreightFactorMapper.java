package com.juma.tgm.configure.dao;

import com.juma.tgm.configure.domain.FreightFactor;
import com.juma.tgm.configure.domain.FreightFactorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FreightFactorMapper {
    long countByExample(FreightFactorExample example);

    int deleteByExample(FreightFactorExample example);

    int deleteByPrimaryKey(Integer freightFactorId);

    int insert(FreightFactor record);

    int insertSelective(FreightFactor record);

    List<FreightFactor> selectByExample(FreightFactorExample example);

    FreightFactor selectByPrimaryKey(Integer freightFactorId);

    int updateByExampleSelective(@Param("record") FreightFactor record, @Param("example") FreightFactorExample example);

    int updateByExample(@Param("record") FreightFactor record, @Param("example") FreightFactorExample example);

    int updateByPrimaryKeySelective(FreightFactor record);

    int updateByPrimaryKey(FreightFactor record);
}