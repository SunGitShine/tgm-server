package com.juma.tgm.fms.dao;

import com.juma.tgm.fms.domain.v2.ReconciliationChangeLog;
import com.juma.tgm.fms.domain.v2.ReconciliationChangeLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface ReconciliationChangeLogMapper {
    int countByExample(ReconciliationChangeLogExample example);

    int deleteByExample(ReconciliationChangeLogExample example);

    @Delete({
        "delete from reconciliation_change_log",
        "where reconciliation_change_log_id = #{reconciliationChangeLogId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer reconciliationChangeLogId);

    @Insert({
        "insert into reconciliation_change_log (reconciliation_id, type, ",
        "driver_name, plate_number, ",
        "before_tax_freight, tax_rate, ",
        "after_tax_freight, user_name, ",
        "customer_id, customer_name, ",
        "note, vehicle_use_time, ",
        "create_user_id, create_time)",
        "values (#{reconciliationId,jdbcType=INTEGER}, #{type,jdbcType=INTEGER}, ",
        "#{driverName,jdbcType=VARCHAR}, #{plateNumber,jdbcType=VARCHAR}, ",
        "#{beforeTaxFreight,jdbcType=DECIMAL}, #{taxRate,jdbcType=DECIMAL}, ",
        "#{afterTaxFreight,jdbcType=DECIMAL}, #{userName,jdbcType=VARCHAR}, ",
        "#{customerId,jdbcType=INTEGER}, #{customerName,jdbcType=VARCHAR}, ",
        "#{note,jdbcType=VARCHAR}, #{vehicleUseTime,jdbcType=TIMESTAMP}, ",
        "#{createUserId,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="reconciliationChangeLogId", before=false, resultType=Integer.class)
    int insert(ReconciliationChangeLog record);

    int insertSelective(ReconciliationChangeLog record);

    List<ReconciliationChangeLog> selectByExample(ReconciliationChangeLogExample example);

    @Select({
        "select",
        "reconciliation_change_log_id, reconciliation_id, type, driver_name, plate_number, ",
        "before_tax_freight, tax_rate, after_tax_freight, user_name, customer_id, customer_name, ",
        "note, vehicle_use_time, create_user_id, create_time",
        "from reconciliation_change_log",
        "where reconciliation_change_log_id = #{reconciliationChangeLogId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    ReconciliationChangeLog selectByPrimaryKey(Integer reconciliationChangeLogId);

    int updateByExampleSelective(@Param("record") ReconciliationChangeLog record, @Param("example") ReconciliationChangeLogExample example);

    int updateByExample(@Param("record") ReconciliationChangeLog record, @Param("example") ReconciliationChangeLogExample example);

    int updateByPrimaryKeySelective(ReconciliationChangeLog record);

    @Update({
        "update reconciliation_change_log",
        "set reconciliation_id = #{reconciliationId,jdbcType=INTEGER},",
          "type = #{type,jdbcType=INTEGER},",
          "driver_name = #{driverName,jdbcType=VARCHAR},",
          "plate_number = #{plateNumber,jdbcType=VARCHAR},",
          "before_tax_freight = #{beforeTaxFreight,jdbcType=DECIMAL},",
          "tax_rate = #{taxRate,jdbcType=DECIMAL},",
          "after_tax_freight = #{afterTaxFreight,jdbcType=DECIMAL},",
          "user_name = #{userName,jdbcType=VARCHAR},",
          "customer_id = #{customerId,jdbcType=INTEGER},",
          "customer_name = #{customerName,jdbcType=VARCHAR},",
          "note = #{note,jdbcType=VARCHAR},",
          "vehicle_use_time = #{vehicleUseTime,jdbcType=TIMESTAMP},",
          "create_user_id = #{createUserId,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where reconciliation_change_log_id = #{reconciliationChangeLogId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ReconciliationChangeLog record);
}