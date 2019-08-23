package com.juma.tgm.task.service.impl;

import javax.annotation.Resource;

import com.giants.common.exception.BusinessException;
import org.mybatis.generator.my.mapper.Mapper;
import org.mybatis.generator.my.service.AbstractMybatisService;
import org.springframework.stereotype.Service;

import com.juma.tgm.task.dao.TaskNotfixedDeliveryMapper;
import com.juma.tgm.task.domain.TaskNotfixedDelivery;
import com.juma.tgm.task.domain.TaskNotfixedDeliveryExample;
import com.juma.tgm.task.service.TaskNotfixedDeliveryService;

import java.util.List;

@Service
public class TaskNotFixedDeliveryServiceImpl  extends AbstractMybatisService<TaskNotfixedDelivery, TaskNotfixedDeliveryExample> implements TaskNotfixedDeliveryService{

    @Resource
    private TaskNotfixedDeliveryMapper taskNotfixedDeliveryMapper;

    @Override
    public Mapper<TaskNotfixedDelivery, TaskNotfixedDeliveryExample> getMapper() {
        return taskNotfixedDeliveryMapper;
    }


    @Override
    public TaskNotfixedDelivery withTaskId(Integer taskId) throws BusinessException {
        if (taskId == null) return null;
        List<TaskNotfixedDelivery> taskNotfixedDeliveryList = selectByExample(new TaskNotfixedDeliveryExample()
                .limit(1, 1)
                .createCriteria()
                .andTaskIdEqualTo(taskId).example());
        return taskNotfixedDeliveryList.isEmpty() ? null : taskNotfixedDeliveryList.get(0);
    }
}
