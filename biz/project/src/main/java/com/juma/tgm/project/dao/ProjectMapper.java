package com.juma.tgm.project.dao;

import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.domain.v2.ProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.generator.my.mapper.Mapper;

public interface ProjectMapper extends Mapper {
    long countByExample(ProjectExample example);

    int deleteByExample(ProjectExample example);

    int deleteByPrimaryKey(Integer projectId);

    int insert(Project record);

    int insertSelective(Project record);

    List<Project> selectByExample(ProjectExample example);

    Project selectByPrimaryKey(Integer projectId);

    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
}