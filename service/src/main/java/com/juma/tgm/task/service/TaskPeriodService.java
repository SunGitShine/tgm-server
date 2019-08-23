package com.juma.tgm.task.service;

import com.giants.common.exception.BusinessException;
import org.mybatis.generator.my.service.MybatisService;

import com.juma.tgm.task.domain.TaskPeriod;
import com.juma.tgm.task.domain.TaskPeriodExample;

public interface TaskPeriodService extends MybatisService<TaskPeriod, TaskPeriodExample> {

    TaskPeriod withTaskId(Integer taskId) throws BusinessException;

}
