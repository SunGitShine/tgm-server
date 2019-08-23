package com.juma.tgm.task.service.impl;

import javax.annotation.Resource;

import com.giants.common.exception.BusinessException;
import org.mybatis.generator.my.mapper.Mapper;
import org.mybatis.generator.my.service.AbstractMybatisService;
import org.springframework.stereotype.Service;

import com.juma.tgm.task.dao.TaskPeriodMapper;
import com.juma.tgm.task.domain.TaskPeriod;
import com.juma.tgm.task.domain.TaskPeriodExample;
import com.juma.tgm.task.service.TaskPeriodService;

import java.util.List;

@Service
public class TaskPeriodServiceImpl  extends AbstractMybatisService<TaskPeriod, TaskPeriodExample> implements TaskPeriodService{

    @Resource
    private TaskPeriodMapper taskPeriodMapper;

    @Override
    public Mapper<TaskPeriod, TaskPeriodExample> getMapper() {
        return taskPeriodMapper;
    }

    @Override
    public TaskPeriod withTaskId(Integer taskId) {
        if (taskId == null) return null;
       List<TaskPeriod> rows = taskPeriodMapper.selectByExample(new TaskPeriodExample()
               .limit(1,1).createCriteria()
               .andTaskIdEqualTo(taskId).example());
       return rows.isEmpty() ? null : rows.get(0);

    };

    
    
    

}
