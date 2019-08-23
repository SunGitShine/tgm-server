package com.juma.tgm.waybill.dao;

import com.juma.tgm.fms.domain.v2.vo.CustomerStatisticsVo;
import com.juma.tgm.waybill.domain.Waybill;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

/**
 * @ClassName: WaybillExtDao
 * @Description:
 * @author: liang
 * @date: 2018-07-04 11:33
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public interface WaybillExtDao {

    /**
     * 通过对账单号更新运单收款状态
     *
     * @param reconciliationNo
     * @param waybillAboutReconciliation
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = "buildUpdateStatusSqlAboutReconciliation")
    CustomerStatisticsVo modifyStatusAboutReconciliationByReconciliationNo(@Param(value = "reconciliationNo") String reconciliationNo, @Param(value = "plateNumber") String plateNumber,@Param("vendorId") Integer vendorId , @Param(value = "waybill") Waybill waybillAboutReconciliation);

    class SqlBuilder {

        // If use @Param, you can define only arguments to be used
        public static String buildUpdateStatusSqlAboutReconciliation(@Param(value = "reconciliationNo") String reconciliationNo, @Param(value = "plateNumber") String plateNumber,@Param("vendorId") Integer vendorId , @Param(value = "waybill") Waybill waybillAboutReconciliation) {
            //UPDATE waybill SET receipt_status = '' WHERE reconciliation_no = '' ;

//            UPDATE("waybill");
//            if (waybillAboutReconciliation.getSettlementStatus() != null) {
//                SET("settlement_status = #{waybill.settlementStatus}");
//            }
//            if (waybillAboutReconciliation.getReceiptStatus() != null) {
//                SET("receipt_status = #{waybill.receiptStatus}");
//            }

            SQL sql = new SQL();
            sql.UPDATE("waybill");

            if (waybillAboutReconciliation.getSettlementStatus() != null) {
                sql.SET("settlement_status = #{waybill.settlementStatus}");
            }
            if (waybillAboutReconciliation.getReceiptStatus() != null) {
                sql.SET("receipt_status = #{waybill.receiptStatus}");
            }
            if (waybillAboutReconciliation.getReconciliationStatus() != null) {
                sql.SET("reconciliation_status = #{waybill.reconciliationStatus}");
            }
            if (waybillAboutReconciliation.getLastUpdateUserId() != null) {
                sql.SET("last_update_user_id = #{waybill.lastUpdateUserId}");
            }
            if (waybillAboutReconciliation.getLastUpdateTime() != null) {
                sql.SET("last_update_time = #{waybill.lastUpdateTime}");
            }

            if (StringUtils.isNotBlank(plateNumber)) {
                sql.WHERE("plate_number = #{plateNumber}");
            }
            /*if( vendorId != null ) {
                sql.WHERE("vendor_id = #{vendorId}");
            }*/
            sql.WHERE("reconciliation_no = #{reconciliationNo}");


            return sql.toString();

        }
    }
}
