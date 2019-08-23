package com.juma.tgm.project.external.service.impl;

import javax.annotation.Resource;

import com.juma.tgm.project.domain.v2.ProjectDepot;
import com.juma.tgm.project.service.ProjectDepotService;
import org.springframework.stereotype.Service;

import com.juma.tgm.external.service.ProjectExternalService;
import com.juma.tgm.project.domain.Project;
import com.juma.tgm.project.service.ProjectService;

import java.util.List;

/**
 * @ClassName ProjectExternalServiceImpl.java
 * @Description 项目对外类
 * @author Libin.Wei
 * @Date 2018年10月24日 下午3:16:45
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Service
public class ProjectExternalServiceImpl implements ProjectExternalService {

    @Resource
    private ProjectService projectService;

    @Resource
    private ProjectDepotService projectDepotService;

    @Override
    public Project getProject(Integer projectId) {
        if (null == projectId) {
            return null;
        }

        return projectService.getProject(projectId);
    }

    @Override
    public com.juma.tgm.project.domain.v2.Project getProjectV2(Integer projectId) {
        if (null == projectId) {
            return null;
        }

        return projectService.getProjectV2(projectId);
    }

    @Override
    public List<ProjectDepot> listProjectDepotByProject(Integer projectId) {
        return projectDepotService.withProjectId(projectId);
    }

}
