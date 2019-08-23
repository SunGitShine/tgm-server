package com.juma.tgm.task.service;

import org.mybatis.generator.my.service.MybatisService;

import com.juma.tgm.task.domain.TaskParam;
import com.juma.tgm.task.domain.TaskParamExample;

public interface TaskParamService extends MybatisService<TaskParam, TaskParamExample> {

    TaskParam withTaskId(Integer taskId);

}
