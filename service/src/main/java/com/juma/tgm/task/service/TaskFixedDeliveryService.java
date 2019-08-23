package com.juma.tgm.task.service;

import com.giants.common.exception.BusinessException;
import org.mybatis.generator.my.service.MybatisService;

import com.juma.tgm.task.domain.TaskFixedDelivery;
import com.juma.tgm.task.domain.TaskFixedDeliveryExample;

import java.util.List;

public interface TaskFixedDeliveryService extends MybatisService<TaskFixedDelivery, TaskFixedDeliveryExample> {

    List<TaskFixedDelivery> withTaskId(Integer taskId) throws BusinessException;

}
