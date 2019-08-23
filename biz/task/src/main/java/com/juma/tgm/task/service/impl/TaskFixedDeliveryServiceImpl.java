package com.juma.tgm.task.service.impl;

import javax.annotation.Resource;

import com.giants.common.exception.BusinessException;
import com.google.common.collect.Lists;
import org.mybatis.generator.my.mapper.Mapper;
import org.mybatis.generator.my.service.AbstractMybatisService;
import org.springframework.stereotype.Service;

import com.juma.tgm.task.dao.TaskFixedDeliveryMapper;
import com.juma.tgm.task.domain.TaskFixedDelivery;
import com.juma.tgm.task.domain.TaskFixedDeliveryExample;
import com.juma.tgm.task.service.TaskFixedDeliveryService;

import java.util.List;

@Service
public class TaskFixedDeliveryServiceImpl  extends AbstractMybatisService<TaskFixedDelivery, TaskFixedDeliveryExample> implements TaskFixedDeliveryService{

    @Resource
    private TaskFixedDeliveryMapper taskFixedDeliveryMapper;

    @Override
    public Mapper<TaskFixedDelivery, TaskFixedDeliveryExample> getMapper() {
        return taskFixedDeliveryMapper;
    }

    @Override
    public List<TaskFixedDelivery> withTaskId(Integer taskId) throws BusinessException {
        if (taskId == null) return Lists.newArrayList();
        return selectByExample(new TaskFixedDeliveryExample().createCriteria()
        .andTaskIdEqualTo(taskId).example());
    }
}
