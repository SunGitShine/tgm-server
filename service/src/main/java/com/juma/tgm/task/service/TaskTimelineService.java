package com.juma.tgm.task.service;

import org.mybatis.generator.my.service.MybatisService;

import com.juma.tgm.task.domain.TaskTimeline;
import com.juma.tgm.task.domain.TaskTimelineExample;

import java.util.Date;
import java.util.List;

public interface TaskTimelineService extends MybatisService<TaskTimeline, TaskTimelineExample> {

    List<TaskTimeline> rangTimeline(Integer truckId);

    TaskTimeline withTimelineId(Integer timelineId);

}
