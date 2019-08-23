package com.juma.tgm.task.service;

import com.giants.common.exception.BusinessException;
import org.mybatis.generator.my.service.MybatisService;

import com.juma.tgm.task.domain.TaskNotfixedDelivery;
import com.juma.tgm.task.domain.TaskNotfixedDeliveryExample;

public interface TaskNotfixedDeliveryService extends MybatisService<TaskNotfixedDelivery, TaskNotfixedDeliveryExample> {

    TaskNotfixedDelivery withTaskId(Integer taskId) throws BusinessException;

}
