package com.juma.tgm.fms.dao;

import com.juma.tgm.fms.domain.v2.bo.ReconciliationStatistics;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface ReconciliationBootstrapMapper {


    @SelectProvider(type = Statistics.class, method = "statistics")
    List<ReconciliationStatistics> statistics(@Param("statusView") Integer statusView,
                                              @Param("reconciliationStatus") Integer reconciliationStatus,
                                              @Param("offSet") Integer offSet,
                                              @Param("size") Integer size,
                                              @Param("customerName") String customerName,
                                              @Param("areaCodeList") String areaCodeList, @Param("tenantId") Integer tenantId);

    @SelectProvider(type = Statistics.class, method = "statisticsCount")
    Integer countStatistics(@Param("statusView") Integer statusView,
                         @Param("reconciliationStatus") Integer reconciliationStatus,
                         @Param("offSet") Integer offSet,
                         @Param("size") Integer size,
                         @Param("customerName") String customerName,
                         @Param("areaCodeList") String areaCodeList,@Param("tenantId") Integer tenantId);


    @SelectProvider(type = Statistics.class, method = "statisticsProject")
    List<ReconciliationStatistics> statisticsProject(@Param("customerId") Integer customerId,
                                                     @Param("statusView") Integer statusView,
                                                     @Param("reconciliationStatus") Integer reconciliationStatus,
                                                     @Param("projectName") String projectName,@Param("tenantId") Integer tenantId);

    class Statistics {

        public String statisticsCount(@Param("customerName") String customerName, @Param("areaCodeList") String areaCodeList) {
            return ("SELECT COUNT(*) FROM ( " + this.statistics(customerName, areaCodeList) + ")" + "AS a ").replace("LIMIT #{offSet},#{size}","");
        }


        public String statistics(@Param("customerName") String customerName, @Param("areaCodeList") String areaCodeList) {
            String select = "SELECT customer_id,customer_name,SUM(estimate_freight) AS beforeTaxFreightSum,SUM(after_tax_freight) AS afterTaxFreightSum ";
            String form = "FROM `waybill` ";
            String group = "GROUP BY `customer_id` ";
//            String order = " ORDER BY  `customer_id` DESC ";
            String limit = "LIMIT #{offSet},#{size}";
            StringBuffer where = new StringBuffer("WHERE `status_view` = #{statusView} AND reconciliation_status = #{reconciliationStatus} AND tenant_id = #{tenantId} AND (reconciliation_no IS NULL OR reconciliation_no='') AND is_delete = 0 AND customer_id IS NOT NULL ");
            if (areaCodeList != null && areaCodeList.length() > 0) {
                where.append(" AND ( ");
                String [] areaCodes = areaCodeList.split(",");
                boolean isStart = true;
                for( String areaCode : areaCodes ) {
                    if(StringUtils.isNotBlank( areaCode ) && StringUtils.isNotBlank( areaCode.trim())) {
                        String c = areaCode;
                        if ( isStart ) {
                            where.append("area_code LIKE CONCAT('" + c + "','%' ) ");
                            isStart = false;
                        } else {
                            where.append("OR area_code LIKE CONCAT('" + c + "','%' ) ");
                        }
                    }
                }
                where.append(" ) ");
                // 这段代码 恶心到我了，注解支持太弱
            }
            if (customerName != null && customerName.length() > 0) {
                where.append("AND customer_name =#{customerName} ");
            }
            return new StringBuffer().append(select).append(form).append(where).append(group).append(limit).toString();
        }

        public String statisticsProject(@Param("projectName") String projectName) {
            String select = "SELECT customer_id,`project_id`,`project_name`,customer_name,SUM(estimate_freight) AS beforeTaxFreightSum,SUM(after_tax_freight) AS afterTaxFreightSum ";
            String form = "FROM `waybill` ";
            String group = "GROUP BY `project_id` ";
            StringBuffer where = new StringBuffer("WHERE `status_view` = #{statusView} AND reconciliation_status = #{reconciliationStatus} AND `customer_id`=#{customerId} AND tenant_id = #{tenantId} AND (reconciliation_no IS NULL OR reconciliation_no='') AND is_delete = 0 ");
            if (projectName != null && projectName.length() > 0) {
                where.append("AND project_name = #{projectName}");
            }
            return new StringBuffer().append(select).append(form).append(where).append(group).toString();
        }
    }


}
