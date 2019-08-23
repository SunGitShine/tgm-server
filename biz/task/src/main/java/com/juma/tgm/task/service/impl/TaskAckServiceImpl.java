package com.juma.tgm.task.service.impl;

import javax.annotation.Resource;

import com.google.common.collect.Lists;
import org.mybatis.generator.my.mapper.Mapper;
import org.mybatis.generator.my.service.AbstractMybatisService;
import org.springframework.stereotype.Service;

import com.juma.tgm.task.dao.TaskAckMapper;
import com.juma.tgm.task.domain.TaskAck;
import com.juma.tgm.task.domain.TaskAckExample;
import com.juma.tgm.task.service.TaskAckService;

import java.util.List;

@Service
public class TaskAckServiceImpl extends AbstractMybatisService<TaskAck, TaskAckExample> implements TaskAckService {

    @Resource
    private TaskAckMapper taskAckMapper;

    @Override
    public Mapper<TaskAck, TaskAckExample> getMapper() {
        return taskAckMapper;
    }

    @Override
    public List<TaskAck> withTaskId(Integer taskId) {
        if (taskId == null) return Lists.newArrayList();
        List<TaskAck> taskAckList = selectByExample(new TaskAckExample().createCriteria().andTaskIdEqualTo(taskId).example());
        return taskAckList;
    }

    @Override
    public TaskAck withLastAck(Integer taskId, Integer vendorId) {
        if (taskId == null || vendorId == null) return null;
        List<TaskAck> taskAckList = selectByExample(new TaskAckExample()
                .limit(1,1)
                .orderBy(TaskAck.Column.ackId.desc())
                .createCriteria()
                .andTaskIdEqualTo(taskId)
                .andVendorIdEqualTo(vendorId).example());
        return taskAckList.isEmpty() ? null : taskAckList.get(0);
    }

    @Override
    public TaskAck withLastAck(Integer taskId) {
        if (taskId == null) return null;
        List<TaskAck> taskAckList = selectByExample(new TaskAckExample()
                .limit(1,1)
                .orderBy(TaskAck.Column.ackId.desc())
                .createCriteria()
                .andTaskIdEqualTo(taskId).example());
        return taskAckList.isEmpty() ? null : taskAckList.get(0);
    }
}
