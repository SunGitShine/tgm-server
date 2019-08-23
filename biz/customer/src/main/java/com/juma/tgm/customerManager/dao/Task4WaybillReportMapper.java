package com.juma.tgm.customerManager.dao;

import com.juma.tgm.customerManager.domain.Task4WaybillReport;
import com.juma.tgm.customerManager.domain.Task4WaybillReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface Task4WaybillReportMapper {
    int countByExample(Task4WaybillReportExample example);

    int deleteByExample(Task4WaybillReportExample example);

    @Delete({
        "delete from task_4_waybill_report",
        "where task_report_id = #{taskReportId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer taskReportId);

    @Insert({
        "insert into task_4_waybill_report (task_id, task_excute_date, ",
        "task_status, task_result, ",
        "has_read, create_time, ",
        "create_user_id, employee_id)",
        "values (#{taskId,jdbcType=INTEGER}, #{taskExcuteDate,jdbcType=DATE}, ",
        "#{taskStatus,jdbcType=TINYINT}, #{taskResult,jdbcType=VARCHAR}, ",
        "#{hasRead,jdbcType=TINYINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{createUserId,jdbcType=INTEGER}, #{employeeId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="taskReportId", before=false, resultType=Integer.class)
    int insert(Task4WaybillReport record);

    int insertSelective(Task4WaybillReport record);

    List<Task4WaybillReport> selectByExample(Task4WaybillReportExample example);

    @Select({
        "select",
        "task_report_id, task_id, task_excute_date, task_status, task_result, has_read, ",
        "create_time, create_user_id, employee_id",
        "from task_4_waybill_report",
        "where task_report_id = #{taskReportId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Task4WaybillReport selectByPrimaryKey(Integer taskReportId);

    int updateByExampleSelective(@Param("record") Task4WaybillReport record, @Param("example") Task4WaybillReportExample example);

    int updateByExample(@Param("record") Task4WaybillReport record, @Param("example") Task4WaybillReportExample example);

    int updateByPrimaryKeySelective(Task4WaybillReport record);

    @Update({
        "update task_4_waybill_report",
        "set task_id = #{taskId,jdbcType=INTEGER},",
          "task_excute_date = #{taskExcuteDate,jdbcType=DATE},",
          "task_status = #{taskStatus,jdbcType=TINYINT},",
          "task_result = #{taskResult,jdbcType=VARCHAR},",
          "has_read = #{hasRead,jdbcType=TINYINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "create_user_id = #{createUserId,jdbcType=INTEGER},",
          "employee_id = #{employeeId,jdbcType=INTEGER}",
        "where task_report_id = #{taskReportId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Task4WaybillReport record);
}