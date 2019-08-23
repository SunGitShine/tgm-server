package com.juma.tgm.fms.dao;

import com.juma.tgm.fms.domain.v2.ReconciliationNew;
import com.juma.tgm.fms.domain.v2.ReconciliationNewExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface ReconciliationNewMapper {
    int countByExample(ReconciliationNewExample example);

    int deleteByExample(ReconciliationNewExample example);

    @Delete({
        "delete from reconciliation_new",
        "where reconciliation_id = #{reconciliationId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer reconciliationId);

    @Insert({
        "insert into reconciliation_new (reconciliation_no, reconciliation_type, ",
        "reconciliation_status, submit_time, ",
        "submitter, process_instance_id, ",
        "tenant_id, tenant_code, ",
        "tax_rate_value, has_receive_freight, ",
        "receive_status, invoice_no, ",
        "project_id, project_name, ",
        "customer_id, customer_name, ",
        "customer_initial_before_tax, customer_initial_after_tax, ",
        "customer_final_before_tax, customer_final_after_tax, ",
        "driver_initial_before_tax, driver_initial_after_tax, ",
        "driver_final_before_tax, driver_final_after_tax, ",
        "driver_handling_fee, laborer_handling_fee, ",
        "area_code, create_user_id, ",
        "create_time, last_update_user_id, ",
        "last_update_time)",
        "values (#{reconciliationNo,jdbcType=VARCHAR}, #{reconciliationType,jdbcType=INTEGER}, ",
        "#{reconciliationStatus,jdbcType=TINYINT}, #{submitTime,jdbcType=TIMESTAMP}, ",
        "#{submitter,jdbcType=INTEGER}, #{processInstanceId,jdbcType=VARCHAR}, ",
        "#{tenantId,jdbcType=INTEGER}, #{tenantCode,jdbcType=VARCHAR}, ",
        "#{taxRateValue,jdbcType=DECIMAL}, #{hasReceiveFreight,jdbcType=DECIMAL}, ",
        "#{receiveStatus,jdbcType=TINYINT}, #{invoiceNo,jdbcType=VARCHAR}, ",
        "#{projectId,jdbcType=INTEGER}, #{projectName,jdbcType=VARCHAR}, ",
        "#{customerId,jdbcType=INTEGER}, #{customerName,jdbcType=VARCHAR}, ",
        "#{customerInitialBeforeTax,jdbcType=DECIMAL}, #{customerInitialAfterTax,jdbcType=DECIMAL}, ",
        "#{customerFinalBeforeTax,jdbcType=DECIMAL}, #{customerFinalAfterTax,jdbcType=DECIMAL}, ",
        "#{driverInitialBeforeTax,jdbcType=DECIMAL}, #{driverInitialAfterTax,jdbcType=DECIMAL}, ",
        "#{driverFinalBeforeTax,jdbcType=DECIMAL}, #{driverFinalAfterTax,jdbcType=DECIMAL}, ",
        "#{driverHandlingFee,jdbcType=DECIMAL}, #{laborerHandlingFee,jdbcType=DECIMAL}, ",
        "#{areaCode,jdbcType=VARCHAR}, #{createUserId,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{lastUpdateUserId,jdbcType=INTEGER}, ",
        "#{lastUpdateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="reconciliationId", before=false, resultType=Integer.class)
    int insert(ReconciliationNew record);

    int insertSelective(ReconciliationNew record);

    List<ReconciliationNew> selectByExample(ReconciliationNewExample example);

    @Select({
        "select",
        "reconciliation_id, reconciliation_no, reconciliation_type, reconciliation_status, ",
        "submit_time, submitter, process_instance_id, tenant_id, tenant_code, tax_rate_value, ",
        "has_receive_freight, receive_status, invoice_no, project_id, project_name, customer_id, ",
        "customer_name, customer_initial_before_tax, customer_initial_after_tax, customer_final_before_tax, ",
        "customer_final_after_tax, driver_initial_before_tax, driver_initial_after_tax, ",
        "driver_final_before_tax, driver_final_after_tax, driver_handling_fee, laborer_handling_fee, ",
        "area_code, create_user_id, create_time, last_update_user_id, last_update_time",
        "from reconciliation_new",
        "where reconciliation_id = #{reconciliationId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    ReconciliationNew selectByPrimaryKey(Integer reconciliationId);

    int updateByExampleSelective(@Param("record") ReconciliationNew record, @Param("example") ReconciliationNewExample example);

    int updateByExample(@Param("record") ReconciliationNew record, @Param("example") ReconciliationNewExample example);

    int updateByPrimaryKeySelective(ReconciliationNew record);

    @Update({
        "update reconciliation_new",
        "set reconciliation_no = #{reconciliationNo,jdbcType=VARCHAR},",
          "reconciliation_type = #{reconciliationType,jdbcType=INTEGER},",
          "reconciliation_status = #{reconciliationStatus,jdbcType=TINYINT},",
          "submit_time = #{submitTime,jdbcType=TIMESTAMP},",
          "submitter = #{submitter,jdbcType=INTEGER},",
          "process_instance_id = #{processInstanceId,jdbcType=VARCHAR},",
          "tenant_id = #{tenantId,jdbcType=INTEGER},",
          "tenant_code = #{tenantCode,jdbcType=VARCHAR},",
          "tax_rate_value = #{taxRateValue,jdbcType=DECIMAL},",
          "has_receive_freight = #{hasReceiveFreight,jdbcType=DECIMAL},",
          "receive_status = #{receiveStatus,jdbcType=TINYINT},",
          "invoice_no = #{invoiceNo,jdbcType=VARCHAR},",
          "project_id = #{projectId,jdbcType=INTEGER},",
          "project_name = #{projectName,jdbcType=VARCHAR},",
          "customer_id = #{customerId,jdbcType=INTEGER},",
          "customer_name = #{customerName,jdbcType=VARCHAR},",
          "customer_initial_before_tax = #{customerInitialBeforeTax,jdbcType=DECIMAL},",
          "customer_initial_after_tax = #{customerInitialAfterTax,jdbcType=DECIMAL},",
          "customer_final_before_tax = #{customerFinalBeforeTax,jdbcType=DECIMAL},",
          "customer_final_after_tax = #{customerFinalAfterTax,jdbcType=DECIMAL},",
          "driver_initial_before_tax = #{driverInitialBeforeTax,jdbcType=DECIMAL},",
          "driver_initial_after_tax = #{driverInitialAfterTax,jdbcType=DECIMAL},",
          "driver_final_before_tax = #{driverFinalBeforeTax,jdbcType=DECIMAL},",
          "driver_final_after_tax = #{driverFinalAfterTax,jdbcType=DECIMAL},",
          "driver_handling_fee = #{driverHandlingFee,jdbcType=DECIMAL},",
          "laborer_handling_fee = #{laborerHandlingFee,jdbcType=DECIMAL},",
          "area_code = #{areaCode,jdbcType=VARCHAR},",
          "create_user_id = #{createUserId,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "last_update_user_id = #{lastUpdateUserId,jdbcType=INTEGER},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP}",
        "where reconciliation_id = #{reconciliationId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ReconciliationNew record);
}