package com.juma.tgm.project.dao.ext;

import java.util.List;

import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.vo.ProjectFilter;
import com.juma.tgm.project.vo.v2.ProjectParamVo;
import org.apache.ibatis.annotations.Param;

public interface ProjectExtMapper {

    List<Project> listProjectBy(ProjectFilter projectFilter);

    List<Project> joinSearch(QueryCond<ProjectParamVo> queryCond);

    int joinSearchCount(QueryCond<ProjectParamVo> queryCond);

    List<Project> listProjectDailySms(@Param("startTime") String startTime, @Param("endTime") String endTime);
}