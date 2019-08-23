package com.juma.tgm.task.service;

import org.mybatis.generator.my.service.MybatisService;

import com.juma.tgm.task.domain.TaskAck;
import com.juma.tgm.task.domain.TaskAckExample;

import java.util.List;

public interface TaskAckService extends MybatisService<TaskAck, TaskAckExample> {

    List<TaskAck> withTaskId(Integer taskId);

    TaskAck withLastAck(Integer taskId,Integer vendorId);

    TaskAck withLastAck(Integer taskId);

}
