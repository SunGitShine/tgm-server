package com.juma.tgm.task.service.impl;

import javax.annotation.Resource;

import org.mybatis.generator.my.mapper.Mapper;
import org.mybatis.generator.my.service.AbstractMybatisService;
import org.springframework.stereotype.Service;

import com.juma.tgm.task.dao.TaskParamMapper;
import com.juma.tgm.task.domain.TaskParam;
import com.juma.tgm.task.domain.TaskParamExample;
import com.juma.tgm.task.service.TaskParamService;

import java.util.List;

@Service
public class TaskParamServiceImpl  extends AbstractMybatisService<TaskParam, TaskParamExample> implements TaskParamService{

    @Resource
    private TaskParamMapper taskParamMapper;

    @Override
    public Mapper<TaskParam, TaskParamExample> getMapper() {
        return taskParamMapper;
    }


    @Override
    public TaskParam withTaskId(Integer taskId) {
        if (taskId == null) return null;
        List<TaskParam> taskParamList = selectByExample(new TaskParamExample()
                .limit(1,1).createCriteria().andTaskIdEqualTo(taskId).example());
        return taskParamList.isEmpty() ? null : taskParamList.get(0);
    }
}
