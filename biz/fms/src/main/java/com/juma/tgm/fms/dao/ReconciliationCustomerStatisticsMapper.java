package com.juma.tgm.fms.dao;

import com.juma.tgm.fms.domain.v2.vo.CustomerStatisticsVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

/**
 * @ClassName: RecontiliationCustomerStatisticsMapper
 * @Description:
 * @author: liang
 * @date: 2018-06-10 15:55
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */

public interface ReconciliationCustomerStatisticsMapper {

    @SelectProvider(type = SqlBuilder.class, method = "buildGetUsersByName")
    CustomerStatisticsVo countCustomerReconciliationFee(@Param(value = "customerId") Integer customerId, @Param(value = "projectId") Integer projectId);

    class SqlBuilder {

        // If not use @Param, you should be define same arguments with mapper method
//        public static String buildGetUsersByName(
//                final String name, final String orderByColumn) {
//            return new SQL(){{
//                SELECT("*");
//                FROM("users");
//                WHERE("name like #{name} || '%'");
//                ORDER_BY(orderByColumn);
//            }}.toString();
//        }

        // If use @Param, you can define only arguments to be used
        public static String buildGetUsersByName(@Param(value = "customerId") Integer customerId, @Param(value = "projectId") Integer projectId) {
//SELECT COUNT(reconciliation_id) AS reconciliationCount,SUM(customer_final_before_tax) AS totalWithoutTaxFee, SUM(customer_final_after_tax) AS totalWithTaxFee from reconciliation_new WHERE customer_id = 19 ;

            final StringBuffer where = new StringBuffer("customer_id = #{customerId}");
            if (projectId != null) {
                where.append(" and project_id = #{projectId}");
            }

            return new SQL() {{
                SELECT("COUNT(reconciliation_id) AS reconciliationCount,SUM(customer_final_before_tax) AS totalWithoutTaxFee, SUM(customer_final_after_tax) AS totalWithTaxFee");
                FROM("reconciliation_new");
                WHERE(where.toString());
            }}.toString();
        }
    }

}

