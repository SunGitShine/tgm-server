package com.juma.tgm.project.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.juma.tgm.project.domain.v2.ProjectWorkflow;

public interface ProjectWorkflowExtraMapper {

	int insert(ProjectWorkflow record);

	List<ProjectWorkflow> findNeedExecuteData();

	Integer unCompleteCount(@Param("projectId") Integer projectId,
		@Param("excuteTime") Date excuteTime, @Param("projectWorkflowType") Integer projectWorkflowType);
}
