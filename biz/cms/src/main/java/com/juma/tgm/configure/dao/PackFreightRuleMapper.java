package com.juma.tgm.configure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.juma.tgm.configure.domain.PackFreightRule;
import com.juma.tgm.configure.domain.PackFreightRuleExample;

public interface PackFreightRuleMapper {
    long countByExample(PackFreightRuleExample example);

    int deleteByExample(PackFreightRuleExample example);

    int deleteByPrimaryKey(Integer packFreightRuleId);

    int insert(PackFreightRule record);

    int insertSelective(PackFreightRule record);

    List<PackFreightRule> selectByExample(PackFreightRuleExample example);

    PackFreightRule selectByPrimaryKey(Integer packFreightRuleId);

    int updateByExampleSelective(@Param("record") PackFreightRule record, @Param("example") PackFreightRuleExample example);

    int updateByExample(@Param("record") PackFreightRule record, @Param("example") PackFreightRuleExample example);

    int updateByPrimaryKeySelective(PackFreightRule record);

    int updateByPrimaryKey(PackFreightRule record);
}