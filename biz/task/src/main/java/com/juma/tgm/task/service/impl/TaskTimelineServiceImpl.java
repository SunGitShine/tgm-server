package com.juma.tgm.task.service.impl;

import javax.annotation.Resource;

import com.google.common.collect.Lists;
import org.mybatis.generator.my.mapper.Mapper;
import org.mybatis.generator.my.service.AbstractMybatisService;
import org.springframework.stereotype.Service;

import com.juma.tgm.task.dao.TaskTimelineMapper;
import com.juma.tgm.task.domain.TaskTimeline;
import com.juma.tgm.task.domain.TaskTimelineExample;
import com.juma.tgm.task.service.TaskTimelineService;

import java.util.Date;
import java.util.List;

@Service
public class TaskTimelineServiceImpl  extends AbstractMybatisService<TaskTimeline, TaskTimelineExample> implements TaskTimelineService {

    @Resource
    private TaskTimelineMapper taskTimelineMapper;

    @Override
    public Mapper<TaskTimeline, TaskTimelineExample> getMapper() {
        return taskTimelineMapper;
    }


    @Override
    public List<TaskTimeline> rangTimeline(Integer truckId) {
        if (truckId == null) return Lists.newArrayList();
        List<TaskTimeline> taskTimelineList = selectByExample(new TaskTimelineExample().orderBy(TaskTimeline.Column.startTime.asc()).createCriteria()
                .andTruckIdEqualTo(truckId)
                .example());
        return taskTimelineList;
    }

    @Override
    public TaskTimeline withTimelineId(Integer timelineId) {
        return selectByPrimaryKey(timelineId);
    }
}
