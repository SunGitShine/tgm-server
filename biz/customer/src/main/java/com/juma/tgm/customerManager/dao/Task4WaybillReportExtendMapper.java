package com.juma.tgm.customerManager.dao;

import com.giants.common.tools.PageQueryCondition;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillReportCountVo;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillReportQueryVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * 任务报告mapper
 *
 * @ClassName: Task4WaybillReportExtendMapper
 * @Description:
 * @author: liang
 * @date: 2018-10-08 15:43
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public interface Task4WaybillReportExtendMapper {

    @SelectProvider(type = SqlBuilder.class, method = "queryReportCount")
    List<Task4WaybillReportCountVo> countReportOverviewList(@Param("queryCondition") PageQueryCondition<Task4WaybillReportQueryVo> queryCondition);


    class SqlBuilder {

        // If use @Param, you can define only arguments to be used
        public static String queryReportCount(@Param("queryCondition") PageQueryCondition<Task4WaybillReportQueryVo> queryCondition) {
            //SELECT sum(CASE WHEN task_status = 2 THEN 1 ELSE 0 END) as successCount, SUM(CASE WHEN task_status = 1 THEN 1 WHEN task_status = 3 THEN 1 ELSE 0 END) AS failCount, create_time AS executeDate from task_4_waybill_report GROUP BY DATE(create_time);
            SQL sql = new SQL();
            sql.SELECT("count(task_report_id) AS total, sum(CASE WHEN task_status = 2 THEN 1 ELSE 0 END) as success_count, SUM(CASE WHEN task_status = 1 THEN 1 WHEN task_status = 3 THEN 1 ELSE 0 END) AS fail_count, task_excute_date AS execute_date").FROM("task_4_waybill_report")
                .WHERE("employee_id = #{queryCondition.filters.employeeId}")
                .GROUP_BY("DATE(task_excute_date)");

            sql.ORDER_BY("task_report_id desc");

            StringBuilder sb = new StringBuilder(sql.toString());

            sb.append(" limit " + queryCondition.getStartOffSet() + "," + queryCondition.getEndOffSet());

            return sb.toString();

        }
    }

    @SelectProvider(type = SqlCountBuilder.class, method = "countReport")
    int countReportOverview(@Param("queryCondition") PageQueryCondition<Task4WaybillReportQueryVo> queryCondition);

    class SqlCountBuilder {

        // If use @Param, you can define only arguments to be used
        public static String countReport(@Param("queryCondition") PageQueryCondition<Task4WaybillReportQueryVo> queryCondition) {
            //SELECT COUNT(DATE(create_time)) from task_4_waybill_report WHERE create_user_id =1 GROUP BY DATE(create_time) ;
            SQL sql = new SQL();
            sql.SELECT("COUNT(task_report_id) ").FROM("task_4_waybill_report")
                .WHERE("employee_id = #{queryCondition.filters.employeeId}")
                .GROUP_BY("DATE(task_excute_date)");

            SQL countSql = new SQL();
            countSql.SELECT("COUNT(*)").FROM("(" + sql.toString() + ") tmp");

            return countSql.toString();

        }
    }

    @SelectProvider(type = FindReportOverviewBuilder.class, method = "buildFindSql")
    Task4WaybillReportCountVo findReportOverview(@Param("queryVo") Task4WaybillReportQueryVo queryVo);


    class FindReportOverviewBuilder {
        public static String buildFindSql(@Param("queryVo") Task4WaybillReportQueryVo queryVo){
//            SELECT
//            count(task_report_id) AS total,
//            sum(
//                CASE
//                WHEN task_status = 2 THEN
//                1
//                ELSE
//                0
//                END
//            ) AS success_count,
//            SUM(
//                CASE
//                WHEN task_status = 1 THEN
//                1
//                WHEN task_status = 3 THEN
//                1
//                ELSE
//                0
//                END
//            ) AS fail_count,
//            task_excute_date AS execute_date FROM task_4_waybill_report WHERE create_user_id = 1 AND task_excute_date = DATE(NOW());

            SQL sql = new SQL();
            sql.SELECT("count(task_report_id) AS total, sum(CASE WHEN task_status = 2 THEN 1 ELSE 0 END) as success_count, SUM(CASE WHEN task_status = 1 THEN 1 WHEN task_status = 3 THEN 1 ELSE 0 END) AS fail_count, task_excute_date AS execute_date")
                .FROM("task_4_waybill_report")
                .WHERE("employee_id = #{queryVo.employeeId}")
                .AND().WHERE("task_excute_date = DATE(#{queryVo.taskExcuteDate})");

            return sql.toString();
        }
    }

}
