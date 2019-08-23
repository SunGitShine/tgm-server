package com.juma.tgm.task.service;

import org.mybatis.generator.my.service.MybatisService;

import com.juma.tgm.task.domain.TaskCalendarReason;
import com.juma.tgm.task.domain.TaskCalendarReasonExample;

public interface TaskCalendarReasonService extends MybatisService<TaskCalendarReason,TaskCalendarReasonExample> {

    TaskCalendarReason withLastCalendarReason(Integer calendarId);

}
