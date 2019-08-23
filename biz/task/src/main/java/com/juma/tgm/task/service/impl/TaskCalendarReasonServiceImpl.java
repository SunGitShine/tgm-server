package com.juma.tgm.task.service.impl;

import javax.annotation.Resource;

import org.mybatis.generator.my.mapper.Mapper;
import org.mybatis.generator.my.service.AbstractMybatisService;
import org.springframework.stereotype.Service;

import com.juma.tgm.task.dao.TaskCalendarReasonMapper;
import com.juma.tgm.task.domain.TaskCalendarReason;
import com.juma.tgm.task.domain.TaskCalendarReasonExample;
import com.juma.tgm.task.service.TaskCalendarReasonService;

import java.util.List;

@Service
public class TaskCalendarReasonServiceImpl extends AbstractMybatisService<TaskCalendarReason, TaskCalendarReasonExample> implements TaskCalendarReasonService {

    @Resource
    private TaskCalendarReasonMapper taskCalendarReasonMapper;
    
    @Override
    public Mapper<TaskCalendarReason, TaskCalendarReasonExample> getMapper() {
        return taskCalendarReasonMapper;
    }

    @Override
    public TaskCalendarReason withLastCalendarReason(Integer calendarId) {
        if (calendarId == null) return null;
        List<TaskCalendarReason> taskCalendarReasonList = selectByExample(new TaskCalendarReasonExample()
                .limit(1,1)
                .orderBy(TaskCalendarReason.Column.reasonId.desc())
                .createCriteria().andCalendarIdEqualTo(calendarId).example());
        return taskCalendarReasonList.isEmpty() ? null : taskCalendarReasonList.get(0);
    }
}
