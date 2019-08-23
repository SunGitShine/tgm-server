package com.juma.tgm.task.dao.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.juma.tgm.task.domain.TaskFixedDelivery;

public interface TaskFixedDeliveryExtMapper {

    List<TaskFixedDelivery> groupTaskFixedDelivery(@Param("taskIds") List<Integer> taskIds,
        @Param("linkName") String linkName, @Param("baskPageSize") Integer baskPageSize);
}