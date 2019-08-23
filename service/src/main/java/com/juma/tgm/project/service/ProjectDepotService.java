package com.juma.tgm.project.service;

import org.mybatis.generator.my.service.MybatisService;

import com.juma.tgm.project.domain.v2.ProjectDepot;
import com.juma.tgm.project.domain.v2.ProjectDepotExample;

import java.util.List;

public interface ProjectDepotService extends MybatisService<ProjectDepot, ProjectDepotExample> {

    ProjectDepot withDepotId(Integer depotId);

    int countWithProjectId(Integer projectId);

    List<ProjectDepot> withProjectId(Integer projectId);

}
