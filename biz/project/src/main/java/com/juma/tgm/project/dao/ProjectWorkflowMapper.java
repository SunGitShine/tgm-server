package com.juma.tgm.project.dao;

import com.juma.tgm.project.domain.v2.ProjectWorkflow;
import com.juma.tgm.project.domain.v2.ProjectWorkflowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectWorkflowMapper {
    long countByExample(ProjectWorkflowExample example);

    int deleteByExample(ProjectWorkflowExample example);

    int deleteByPrimaryKey(Integer projectWorkflowId);

    int insert(ProjectWorkflow record);

    int insertSelective(ProjectWorkflow record);

    List<ProjectWorkflow> selectByExample(ProjectWorkflowExample example);

    ProjectWorkflow selectByPrimaryKey(Integer projectWorkflowId);

    int updateByExampleSelective(@Param("record") ProjectWorkflow record, @Param("example") ProjectWorkflowExample example);

    int updateByExample(@Param("record") ProjectWorkflow record, @Param("example") ProjectWorkflowExample example);

    int updateByPrimaryKeySelective(ProjectWorkflow record);

    int updateByPrimaryKey(ProjectWorkflow record);

    int insertBatch(List<ProjectWorkflow> list);

    int updateBatchSelective(List<ProjectWorkflow> list);
}