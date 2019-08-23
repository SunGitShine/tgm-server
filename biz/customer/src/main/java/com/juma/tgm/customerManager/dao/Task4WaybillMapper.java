package com.juma.tgm.customerManager.dao;

import com.juma.tgm.customerManager.domain.Task4Waybill;
import com.juma.tgm.customerManager.domain.Task4WaybillExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface Task4WaybillMapper {
    int countByExample(Task4WaybillExample example);

    int deleteByExample(Task4WaybillExample example);

    @Delete({
        "delete from task_4_waybill",
        "where task_id = #{taskId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer taskId);

    @Insert({
        "insert into task_4_waybill (task_waybill_template_id, user_id, ",
        "employee_id, task_start_date, ",
        "task_end_date, task_week_days, ",
        "create_user_id, create_time, ",
        "last_update_time, is_close, ",
        "last_update_user_id)",
        "values (#{taskWaybillTemplateId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{employeeId,jdbcType=INTEGER}, #{taskStartDate,jdbcType=DATE}, ",
        "#{taskEndDate,jdbcType=DATE}, #{taskWeekDays,jdbcType=VARCHAR}, ",
        "#{createUserId,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{lastUpdateTime,jdbcType=TIMESTAMP}, #{isClose,jdbcType=TINYINT}, ",
        "#{lastUpdateUserId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="taskId", before=false, resultType=Integer.class)
    int insert(Task4Waybill record);

    int insertSelective(Task4Waybill record);

    List<Task4Waybill> selectByExample(Task4WaybillExample example);

    @Select({
        "select",
        "task_id, task_waybill_template_id, user_id, employee_id, task_start_date, task_end_date, ",
        "task_week_days, create_user_id, create_time, last_update_time, is_close, last_update_user_id",
        "from task_4_waybill",
        "where task_id = #{taskId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Task4Waybill selectByPrimaryKey(Integer taskId);

    int updateByExampleSelective(@Param("record") Task4Waybill record, @Param("example") Task4WaybillExample example);

    int updateByExample(@Param("record") Task4Waybill record, @Param("example") Task4WaybillExample example);

    int updateByPrimaryKeySelective(Task4Waybill record);

    @Update({
        "update task_4_waybill",
        "set task_waybill_template_id = #{taskWaybillTemplateId,jdbcType=INTEGER},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "employee_id = #{employeeId,jdbcType=INTEGER},",
          "task_start_date = #{taskStartDate,jdbcType=DATE},",
          "task_end_date = #{taskEndDate,jdbcType=DATE},",
          "task_week_days = #{taskWeekDays,jdbcType=VARCHAR},",
          "create_user_id = #{createUserId,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "is_close = #{isClose,jdbcType=TINYINT},",
          "last_update_user_id = #{lastUpdateUserId,jdbcType=INTEGER}",
        "where task_id = #{taskId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Task4Waybill record);
}