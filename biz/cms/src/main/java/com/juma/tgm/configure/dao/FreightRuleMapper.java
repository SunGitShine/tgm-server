package com.juma.tgm.configure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.juma.tgm.configure.domain.FreightRule;
import com.juma.tgm.configure.domain.FreightRuleExample;

public interface FreightRuleMapper {
    long countByExample(FreightRuleExample example);

    int deleteByExample(FreightRuleExample example);

    int deleteByPrimaryKey(Integer freightRuleId);

    int insert(FreightRule record);

    int insertSelective(FreightRule record);

    List<FreightRule> selectByExample(FreightRuleExample example);

    FreightRule selectByPrimaryKey(Integer freightRuleId);

    int updateByExampleSelective(@Param("record") FreightRule record, @Param("example") FreightRuleExample example);

    int updateByExample(@Param("record") FreightRule record, @Param("example") FreightRuleExample example);

    int updateByPrimaryKeySelective(FreightRule record);

    int updateByPrimaryKey(FreightRule record);
}