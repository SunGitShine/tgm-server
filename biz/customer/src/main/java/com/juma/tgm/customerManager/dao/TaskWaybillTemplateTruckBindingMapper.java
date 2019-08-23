package com.juma.tgm.customerManager.dao;

import com.juma.tgm.customerManager.domain.TaskWaybillTemplateTruckBinding;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateTruckBindingExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface TaskWaybillTemplateTruckBindingMapper {
    int countByExample(TaskWaybillTemplateTruckBindingExample example);

    int deleteByExample(TaskWaybillTemplateTruckBindingExample example);

    @Delete({
        "delete from task_waybill_template_truck_binding",
        "where task_waybill_template_truck_binding_id = #{taskWaybillTemplateTruckBindingId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer taskWaybillTemplateTruckBindingId);

    @Insert({
        "insert into task_waybill_template_truck_binding (task_waybill_template_id, truck_id)",
        "values (#{taskWaybillTemplateId,jdbcType=INTEGER}, #{truckId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="taskWaybillTemplateTruckBindingId", before=false, resultType=Integer.class)
    int insert(TaskWaybillTemplateTruckBinding record);

    int insertSelective(TaskWaybillTemplateTruckBinding record);

    List<TaskWaybillTemplateTruckBinding> selectByExample(TaskWaybillTemplateTruckBindingExample example);

    @Select({
        "select",
        "task_waybill_template_truck_binding_id, task_waybill_template_id, truck_id",
        "from task_waybill_template_truck_binding",
        "where task_waybill_template_truck_binding_id = #{taskWaybillTemplateTruckBindingId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    TaskWaybillTemplateTruckBinding selectByPrimaryKey(Integer taskWaybillTemplateTruckBindingId);

    int updateByExampleSelective(@Param("record") TaskWaybillTemplateTruckBinding record, @Param("example") TaskWaybillTemplateTruckBindingExample example);

    int updateByExample(@Param("record") TaskWaybillTemplateTruckBinding record, @Param("example") TaskWaybillTemplateTruckBindingExample example);

    int updateByPrimaryKeySelective(TaskWaybillTemplateTruckBinding record);

    @Update({
        "update task_waybill_template_truck_binding",
        "set task_waybill_template_id = #{taskWaybillTemplateId,jdbcType=INTEGER},",
          "truck_id = #{truckId,jdbcType=INTEGER}",
        "where task_waybill_template_truck_binding_id = #{taskWaybillTemplateTruckBindingId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TaskWaybillTemplateTruckBinding record);
}