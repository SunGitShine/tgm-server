package com.juma.tgm.project.dao;

import com.juma.tgm.project.domain.v2.ProjectMember;
import com.juma.tgm.project.domain.v2.ProjectMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectMemberMapper {
    long countByExample(ProjectMemberExample example);

    int deleteByExample(ProjectMemberExample example);

    int deleteByPrimaryKey(Integer memberId);

    int insert(ProjectMember record);

    int insertSelective(ProjectMember record);

    List<ProjectMember> selectByExample(ProjectMemberExample example);

    ProjectMember selectByPrimaryKey(Integer memberId);

    int updateByExampleSelective(@Param("record") ProjectMember record, @Param("example") ProjectMemberExample example);

    int updateByExample(@Param("record") ProjectMember record, @Param("example") ProjectMemberExample example);

    int updateByPrimaryKeySelective(ProjectMember record);

    int updateByPrimaryKey(ProjectMember record);

    int insertBatch(List<ProjectMember> list);

    int updateBatchSelective(List<ProjectMember> list);
}