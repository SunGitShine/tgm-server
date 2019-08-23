package com.juma.tgm.fms.dao.v3;

import com.juma.tgm.waybill.domain.Waybill;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReconciliationMonthlyBillMapper {

	/**
	 *
	 * 应付暂估月账单主体信息
	 *@return
	 */
	@Select({"SELECT\n" +
			"waybill_id waybillId,\n" +
			"waybill_no waybillNo,\n" +
			"customer_id customerId,\n" +
			"customer_name customerName,\n" +
			"plan_delivery_time planDeliveryTime,\n" +
			"finish_time finishTime,\n" +
			"project_id projectId,\n" +
			"project_name projectName,\n" +
			"tenant_id tenantId,\n" +
			"receive_way receiveWay,\n" +
			"show4_driver_freight show4DriverFreight,\n" +
			"truck_id truckId,\n" +
			"area_code areaCode,\n" +
			"vehicle_to_vendor vehicleToVendor,\n" +
			"reconciliation_no reconciliationNo,\n" +
			"vendor_id vendorId,\n" +
			"department_id departmentId,\n" +
			"pay_to_company payToCompany,\n" +
			"contract_to_company_credit_code contractToCompanyCreditCode,\n" +
			"pay_to_company_credit_code payToCompanyCreditCode\n" +
			"FROM waybill\n" +
			"WHERE is_delete = 0 AND finish_time >= #{firstDay}\n" +
			"AND finish_time <= #{lastDay}\n" +
			"AND reconciliation_status = 1\n" +
			"AND driver_type != 1"})
	@ResultType(Waybill.class)
	List<Waybill> findPayableMonthlyBillMain(@Param("firstDay") String firstDay, @Param("lastDay") String lastDay);

	/**
	 *
	 * 应收暂估月账单主体信息
	 *@return
	 */
	@Select({"SELECT\n" +
			"waybill_id waybillId,\n" +
			"waybill_no waybillNo,\n" +
			"customer_id customerId,\n" +
			"customer_name customerName,\n" +
			"plan_delivery_time planDeliveryTime,\n" +
			"finish_time finishTime,\n" +
			"project_id projectId,\n" +
			"project_name projectName,\n" +
			"tenant_id tenantId,\n" +
			"receive_way receiveWay,\n" +
			"show4_driver_freight show4DriverFreight,\n" +
			"truck_id truckId,\n" +
			"area_code areaCode,\n" +
			"receivable_reconcilication_no receivableReconcilicationNo,\n" +
			"estimate_freight estimateFreight,\n" +
			"after_tax_freight afterTaxFreight,\n" +
			"vendor_id vendorId,\n" +
			"vehicle_to_vendor vehicleToVendor,\n" +
			"receivable_reconcilication_status receivableReconcilicationStatus,\n" +
			"department_id departmentId,\n" +
			"pay_to_company payToCompany,\n" +
			"contract_to_company_credit_code contractToCompanyCreditCode,\n" +
			"pay_to_company_credit_code payToCompanyCreditCode\n" +
			"FROM waybill\n" +
			"WHERE is_delete = 0 AND finish_time >= #{firstDay}\n" +
			"AND finish_time <= #{lastDay}\n" +
			"AND receivable_reconcilication_status = 1"})
	@ResultType(Waybill.class)
	List<Waybill> findReceivableMonthlyBillMain(@Param("firstDay") String firstDay, @Param("lastDay") String lastDay);

}
