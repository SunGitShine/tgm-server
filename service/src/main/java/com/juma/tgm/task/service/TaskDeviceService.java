package com.juma.tgm.task.service;

import org.mybatis.generator.my.service.MybatisService;

import com.juma.tgm.task.domain.TaskDevice;
import com.juma.tgm.task.domain.TaskDeviceExample;

import java.util.List;

public interface TaskDeviceService extends MybatisService<TaskDevice, TaskDeviceExample> {


    List<TaskDevice> withTaskId(Integer taskId);

}
