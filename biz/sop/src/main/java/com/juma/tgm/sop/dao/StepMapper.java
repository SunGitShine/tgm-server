package com.juma.tgm.sop.dao;

import com.juma.tgm.sop.domain.Step;
import com.juma.tgm.sop.domain.StepExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StepMapper {
    long countByExample(StepExample example);

    int deleteByExample(StepExample example);

    int deleteByPrimaryKey(Integer stepId);

    int insert(Step record);

    int insertSelective(Step record);

    List<Step> selectByExample(StepExample example);

    Step selectByPrimaryKey(Integer stepId);

    int updateByExampleSelective(@Param("record") Step record, @Param("example") StepExample example);

    int updateByExample(@Param("record") Step record, @Param("example") StepExample example);

    int updateByPrimaryKeySelective(Step record);

    int updateByPrimaryKey(Step record);
}