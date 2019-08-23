package com.juma.tgm.fms.dao.v3;

import com.juma.tgm.fms.domain.v3.AdjustForPayable;
import com.juma.tgm.fms.domain.v3.AdjustForPayableExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface AdjustForPayableMapper {
    int countByExample(AdjustForPayableExample example);

    int deleteByExample(AdjustForPayableExample example);

    @Delete({
        "delete from adjust_for_payable",
        "where adjust_id = #{adjustId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer adjustId);

    @Insert({
        "insert into adjust_for_payable (waybill_id, waybill_no, ",
        "payable_with_tax, payable_with_tax_adjust, ",
        "driver_transport_fee, driver_transport_fee_adjust, ",
        "temporary_transport_fee, temporary_transport_fee_adjust, ",
        "adjust_remark, adjust_user_id, ",
        "adjust_time)",
        "values (#{waybillId,jdbcType=INTEGER}, #{waybillNo,jdbcType=VARCHAR}, ",
        "#{payableWithTax,jdbcType=DECIMAL}, #{payableWithTaxAdjust,jdbcType=DECIMAL}, ",
        "#{driverTransportFee,jdbcType=DECIMAL}, #{driverTransportFeeAdjust,jdbcType=DECIMAL}, ",
        "#{temporaryTransportFee,jdbcType=DECIMAL}, #{temporaryTransportFeeAdjust,jdbcType=DECIMAL}, ",
        "#{adjustRemark,jdbcType=VARCHAR}, #{adjustUserId,jdbcType=INTEGER}, ",
        "#{adjustTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="adjustId", before=false, resultType=Integer.class)
    int insert(AdjustForPayable record);

    int insertSelective(AdjustForPayable record);

    List<AdjustForPayable> selectByExample(AdjustForPayableExample example);

    @Select({
        "select",
        "adjust_id, waybill_id, waybill_no, payable_with_tax, payable_with_tax_adjust, ",
        "driver_transport_fee, driver_transport_fee_adjust, temporary_transport_fee, ",
        "temporary_transport_fee_adjust, adjust_remark, adjust_user_id, adjust_time",
        "from adjust_for_payable",
        "where adjust_id = #{adjustId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    AdjustForPayable selectByPrimaryKey(Integer adjustId);

    int updateByExampleSelective(@Param("record") AdjustForPayable record, @Param("example") AdjustForPayableExample example);

    int updateByExample(@Param("record") AdjustForPayable record, @Param("example") AdjustForPayableExample example);

    int updateByPrimaryKeySelective(AdjustForPayable record);

    @Update({
        "update adjust_for_payable",
        "set waybill_id = #{waybillId,jdbcType=INTEGER},",
          "waybill_no = #{waybillNo,jdbcType=VARCHAR},",
          "payable_with_tax = #{payableWithTax,jdbcType=DECIMAL},",
          "payable_with_tax_adjust = #{payableWithTaxAdjust,jdbcType=DECIMAL},",
          "driver_transport_fee = #{driverTransportFee,jdbcType=DECIMAL},",
          "driver_transport_fee_adjust = #{driverTransportFeeAdjust,jdbcType=DECIMAL},",
          "temporary_transport_fee = #{temporaryTransportFee,jdbcType=DECIMAL},",
          "temporary_transport_fee_adjust = #{temporaryTransportFeeAdjust,jdbcType=DECIMAL},",
          "adjust_remark = #{adjustRemark,jdbcType=VARCHAR},",
          "adjust_user_id = #{adjustUserId,jdbcType=INTEGER},",
          "adjust_time = #{adjustTime,jdbcType=TIMESTAMP}",
        "where adjust_id = #{adjustId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AdjustForPayable record);
}