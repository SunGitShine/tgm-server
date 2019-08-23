package com.juma.tgm.customerManager.dao;

import com.giants.common.tools.PageQueryCondition;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillQueryVo;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillRelationVo;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * @ClassName: Task4WaybillExtendMapper
 * @Description:
 * @author: liang
 * @date: 2018-10-08 17:15
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public interface Task4WaybillExtendMapper {


    @SelectProvider(type = SearchCountSqlBuilder.class, method = "searchCount")
    int searchCount(@Param("queryCondition") PageQueryCondition<Task4WaybillQueryVo> queryCondition);


    class SearchCountSqlBuilder {

        // If use @Param, you can define only arguments to be used
        public static String searchCount(@Param("queryCondition") PageQueryCondition<Task4WaybillQueryVo> queryCondition) {
            SQL sql = new SQL();
            sql.SELECT("COUNT(DISTINCT(tw.task_id))");
            sql.FROM("task_4_waybill tw");
            SearchSqlBuilder.buildSql(queryCondition, sql);

            return sql.toString();

        }
    }


    @SelectProvider(type = SearchSqlBuilder.class, method = "search")
    List<Task4WaybillRelationVo> search(@Param("queryCondition") PageQueryCondition<Task4WaybillQueryVo> queryCondition);

    class SearchSqlBuilder {

        // If use @Param, you can define only arguments to be used
        public static String search(@Param("queryCondition") PageQueryCondition<Task4WaybillQueryVo> queryCondition) {

//            SELECT
//            p.project_id, twt.task_waybill_template_id, tw.task_id
//            FROM
//            task_4_waybill tw
//            LEFT JOIN task_waybill_template twt ON tw.task_waybill_template_id = twt.task_waybill_template_id
//            LEFT JOIN customer_info ci ON ci.customer_id = twt.customer_id
//            LEFT JOIN truck_customer tc ON tc.truck_customer_id = twt.truck_customer_id
//            LEFT JOIN project p ON p.project_id = twt.project_id
//            WHERE
//            tw.employee_id = 1
//            AND ci.customer_name LIKE "%"
//            AND tc.nickname LIKE "%"
//            AND p.`name` LIKE "%"
//            AND (
//                DATE(tw.task_start_date) <= DATE(NOW())
//                AND DATE(tw.task_end_date) >= DATE(NOW())
//                AND FIND_IN_SET("fri", "")
//            );

            SQL sql = new SQL();
            sql.SELECT("p.project_id, twt.task_waybill_template_id, tw.task_id");
            sql.FROM("task_4_waybill tw");

            buildSql(queryCondition, sql);

            sql.ORDER_BY("tw.task_id desc");

            StringBuilder sb = new StringBuilder(sql.toString());

            sb.append(" limit " + queryCondition.getStartOffSet() + "," + queryCondition.getEndOffSet());

            return sb.toString();

        }

        public static void buildSql(@Param("queryCondition") PageQueryCondition<Task4WaybillQueryVo> queryCondition, SQL sql) {

            StringBuilder sb = new StringBuilder();
            if(queryCondition.getFilters().getProjectIds() != null
                && !queryCondition.getFilters().getProjectIds().isEmpty()){
                sb.append("(");
                for (Integer projectId : queryCondition.getFilters().getProjectIds()) {
                    sb.append(projectId + ",");
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append(")");
            }

            sql.LEFT_OUTER_JOIN("task_waybill_template twt ON tw.task_waybill_template_id = twt.task_waybill_template_id");
            sql.LEFT_OUTER_JOIN("customer_info ci ON ci.customer_id = twt.customer_id");
            sql.LEFT_OUTER_JOIN("truck_customer tc ON tc.truck_customer_id = twt.truck_customer_id");
            sql.LEFT_OUTER_JOIN("project p ON p.project_id = twt.project_id");
            sql.WHERE("1=1");

//            if (queryCondition.getFilters().getEmployeeId() != null) {
//                sql.AND().WHERE("tw.employee_id = #{queryCondition.filters.employeeId}");
//            }
            if (StringUtils.isNotEmpty(queryCondition.getFilters().getKey())) {
                sql.AND().WHERE("ci.customer_name LIKE CONCAT(#{queryCondition.filters.key},'%') OR tc.nickname LIKE CONCAT(#{queryCondition.filters.key},'%') OR p.`name` LIKE CONCAT(#{queryCondition.filters.key},'%')");
            }

            if (queryCondition.getFilters().getTodayTask() != null && queryCondition.getFilters().getTodayTask()) {
                sql.AND().WHERE("DATE_SUB(DATE(tw.task_start_date),INTERVAL 1 day) <= DATE(NOW()) AND DATE_SUB(DATE(tw.task_end_date),INTERVAL 1 day) >= DATE(NOW()) AND FIND_IN_SET(#{queryCondition.filters.dayCode}, tw.task_week_days)");
            }

            if(queryCondition.getFilters().getProjectIds() != null && !queryCondition.getFilters().getProjectIds().isEmpty()){
                sql.AND().WHERE("p.project_id IN " + sb.toString());
            }
        }
    }

}
