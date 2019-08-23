package com.juma.tgm.task.service.impl;

import javax.annotation.Resource;

import com.google.common.collect.Lists;
import org.mybatis.generator.my.mapper.Mapper;
import org.mybatis.generator.my.service.AbstractMybatisService;
import org.springframework.stereotype.Service;

import com.juma.tgm.task.dao.TaskDeviceMapper;
import com.juma.tgm.task.domain.TaskDevice;
import com.juma.tgm.task.domain.TaskDeviceExample;
import com.juma.tgm.task.service.TaskDeviceService;

import java.util.List;

@Service
public class TaskDeviceServiceImpl  extends AbstractMybatisService<TaskDevice, TaskDeviceExample> implements TaskDeviceService{

    @Resource
    private TaskDeviceMapper taskDeviceMapper;
    
    @Override
    public Mapper<TaskDevice, TaskDeviceExample> getMapper() {
        return taskDeviceMapper;
    }

    @Override
    public List<TaskDevice> withTaskId(Integer taskId) {
        if (taskId == null) return Lists.newArrayList();
        List<TaskDevice> taskDevices = selectByExample(new TaskDeviceExample().createCriteria()
                .andTaskIdEqualTo(taskId).example());
        return taskDevices;
    }
}
