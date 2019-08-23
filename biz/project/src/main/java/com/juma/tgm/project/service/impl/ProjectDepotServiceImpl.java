package com.juma.tgm.project.service.impl;

import javax.annotation.Resource;

import com.google.common.collect.Lists;
import org.mybatis.generator.my.mapper.Mapper;
import org.mybatis.generator.my.service.AbstractMybatisService;
import org.springframework.stereotype.Service;

import com.juma.tgm.project.dao.ProjectDepotMapper;
import com.juma.tgm.project.domain.v2.ProjectDepot;
import com.juma.tgm.project.domain.v2.ProjectDepotExample;
import com.juma.tgm.project.service.ProjectDepotService;

import java.util.List;

@Service
public class ProjectDepotServiceImpl extends AbstractMybatisService<ProjectDepot, ProjectDepotExample> implements ProjectDepotService {

    @Resource
    private ProjectDepotMapper projectDepotMapper;

    @Override
    public Mapper<ProjectDepot, ProjectDepotExample> getMapper() {
        return projectDepotMapper;
    }


    @Override
    public ProjectDepot withDepotId(Integer depotId) {
        if (depotId == null) return null;
        return selectByPrimaryKey(depotId);
    }

    @Override
    public int countWithProjectId(Integer projectId) {
        if (projectId == null) return 0;
        long l = countByExample(new ProjectDepotExample().createCriteria().andProjectIdEqualTo(projectId).andIsDeleteEqualTo(false).example());
        return (int) l;
    }

    @Override
    public List<ProjectDepot> withProjectId(Integer projectId) {
        if (projectId == null) return Lists.newArrayList();
        List<ProjectDepot> projectDepots = selectByExample(new ProjectDepotExample().createCriteria().andProjectIdEqualTo(projectId).andIsDeleteEqualTo(false).example());
        return projectDepots;
    }
}
