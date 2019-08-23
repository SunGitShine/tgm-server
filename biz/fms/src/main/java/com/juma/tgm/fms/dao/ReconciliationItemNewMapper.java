package com.juma.tgm.fms.dao;

import com.juma.tgm.fms.domain.v2.ReconciliationItemNew;
import com.juma.tgm.fms.domain.v2.ReconciliationItemNewExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface ReconciliationItemNewMapper {
    int countByExample(ReconciliationItemNewExample example);

    int deleteByExample(ReconciliationItemNewExample example);

    @Delete({
        "delete from reconciliation_item_new",
        "where reconciliation_item_id = #{reconciliationItemId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer reconciliationItemId);

    @Insert({
        "insert into reconciliation_item_new (reconciliation_id, area_code, ",
        "customer_id, customer_name, ",
        "project_id, project_name, ",
        "truck_id, plate_number, ",
        "tax_rate_value, rebate_fee, ",
        "driver_handling_fee, laborer_handling_fee, ",
        "has_pay_freight, pay_status, ",
        "lessee_name, lessee_id, ",
        "driver_initial_before_tax, driver_initial_after_tax, ",
        "driver_final_before_tax, driver_final_after_tax, ",
        "create_time, create_user_id, ",
        "last_update_time, last_update_user_id, ",
        "vendor_id, vendor_name)",
        "values (#{reconciliationId,jdbcType=INTEGER}, #{areaCode,jdbcType=VARCHAR}, ",
        "#{customerId,jdbcType=INTEGER}, #{customerName,jdbcType=VARCHAR}, ",
        "#{projectId,jdbcType=INTEGER}, #{projectName,jdbcType=VARCHAR}, ",
        "#{truckId,jdbcType=INTEGER}, #{plateNumber,jdbcType=VARCHAR}, ",
        "#{taxRateValue,jdbcType=DECIMAL}, #{rebateFee,jdbcType=DECIMAL}, ",
        "#{driverHandlingFee,jdbcType=DECIMAL}, #{laborerHandlingFee,jdbcType=DECIMAL}, ",
        "#{hasPayFreight,jdbcType=DECIMAL}, #{payStatus,jdbcType=TINYINT}, ",
        "#{lesseeName,jdbcType=VARCHAR}, #{lesseeId,jdbcType=INTEGER}, ",
        "#{driverInitialBeforeTax,jdbcType=DECIMAL}, #{driverInitialAfterTax,jdbcType=DECIMAL}, ",
        "#{driverFinalBeforeTax,jdbcType=DECIMAL}, #{driverFinalAfterTax,jdbcType=DECIMAL}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{createUserId,jdbcType=INTEGER}, ",
        "#{lastUpdateTime,jdbcType=TIMESTAMP}, #{lastUpdateUserId,jdbcType=INTEGER}, ",
        "#{vendorId,jdbcType=INTEGER}, #{vendorName,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="reconciliationItemId", before=false, resultType=Integer.class)
    int insert(ReconciliationItemNew record);

    int insertSelective(ReconciliationItemNew record);

    List<ReconciliationItemNew> selectByExample(ReconciliationItemNewExample example);

    @Select({
        "select",
        "reconciliation_item_id, reconciliation_id, area_code, customer_id, customer_name, ",
        "project_id, project_name, truck_id, plate_number, tax_rate_value, rebate_fee, ",
        "driver_handling_fee, laborer_handling_fee, has_pay_freight, pay_status, lessee_name, ",
        "lessee_id, driver_initial_before_tax, driver_initial_after_tax, driver_final_before_tax, ",
        "driver_final_after_tax, create_time, create_user_id, last_update_time, last_update_user_id, ",
        "vendor_id, vendor_name",
        "from reconciliation_item_new",
        "where reconciliation_item_id = #{reconciliationItemId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    ReconciliationItemNew selectByPrimaryKey(Integer reconciliationItemId);

    int updateByExampleSelective(@Param("record") ReconciliationItemNew record, @Param("example") ReconciliationItemNewExample example);

    int updateByExample(@Param("record") ReconciliationItemNew record, @Param("example") ReconciliationItemNewExample example);

    int updateByPrimaryKeySelective(ReconciliationItemNew record);

    @Update({
        "update reconciliation_item_new",
        "set reconciliation_id = #{reconciliationId,jdbcType=INTEGER},",
          "area_code = #{areaCode,jdbcType=VARCHAR},",
          "customer_id = #{customerId,jdbcType=INTEGER},",
          "customer_name = #{customerName,jdbcType=VARCHAR},",
          "project_id = #{projectId,jdbcType=INTEGER},",
          "project_name = #{projectName,jdbcType=VARCHAR},",
          "truck_id = #{truckId,jdbcType=INTEGER},",
          "plate_number = #{plateNumber,jdbcType=VARCHAR},",
          "tax_rate_value = #{taxRateValue,jdbcType=DECIMAL},",
          "rebate_fee = #{rebateFee,jdbcType=DECIMAL},",
          "driver_handling_fee = #{driverHandlingFee,jdbcType=DECIMAL},",
          "laborer_handling_fee = #{laborerHandlingFee,jdbcType=DECIMAL},",
          "has_pay_freight = #{hasPayFreight,jdbcType=DECIMAL},",
          "pay_status = #{payStatus,jdbcType=TINYINT},",
          "lessee_name = #{lesseeName,jdbcType=VARCHAR},",
          "lessee_id = #{lesseeId,jdbcType=INTEGER},",
          "driver_initial_before_tax = #{driverInitialBeforeTax,jdbcType=DECIMAL},",
          "driver_initial_after_tax = #{driverInitialAfterTax,jdbcType=DECIMAL},",
          "driver_final_before_tax = #{driverFinalBeforeTax,jdbcType=DECIMAL},",
          "driver_final_after_tax = #{driverFinalAfterTax,jdbcType=DECIMAL},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "create_user_id = #{createUserId,jdbcType=INTEGER},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "last_update_user_id = #{lastUpdateUserId,jdbcType=INTEGER},",
          "vendor_id = #{vendorId,jdbcType=INTEGER},",
          "vendor_name = #{vendorName,jdbcType=VARCHAR}",
        "where reconciliation_item_id = #{reconciliationItemId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ReconciliationItemNew record);
}