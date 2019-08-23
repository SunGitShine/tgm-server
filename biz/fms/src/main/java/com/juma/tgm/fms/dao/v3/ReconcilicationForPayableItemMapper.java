package com.juma.tgm.fms.dao.v3;

import com.juma.tgm.fms.domain.v3.ReconcilicationForPayableItem;
import com.juma.tgm.fms.domain.v3.ReconcilicationForPayableItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface ReconcilicationForPayableItemMapper {
    int countByExample(ReconcilicationForPayableItemExample example);

    int deleteByExample(ReconcilicationForPayableItemExample example);

    @Delete({
        "delete from reconcilication_for_payable_item",
        "where reconcilication_item_id = #{reconcilicationItemId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer reconcilicationItemId);

    @Insert({
        "insert into reconcilication_for_payable_item (reconcilication_id, settle_type, ",
        "settle_account_id, settle_account_name, ",
        "waybill_id, waybill_no, ",
        "plate_number, vehicle_frame_no, ",
        "settle_freight, tax_rate, ",
        "rebate_fee, driver_transport_fee, ",
        "temporary_transport_fee, settle_status, receivable_with_tax)",
        "values (#{reconcilicationId,jdbcType=INTEGER}, #{settleType,jdbcType=INTEGER}, ",
        "#{settleAccountId,jdbcType=INTEGER}, #{settleAccountName,jdbcType=VARCHAR}, ",
        "#{waybillId,jdbcType=INTEGER}, #{waybillNo,jdbcType=VARCHAR}, ",
        "#{plateNumber,jdbcType=VARCHAR}, #{vehicleFrameNo,jdbcType=VARCHAR}, ",
        "#{settleFreight,jdbcType=DECIMAL}, #{taxRate,jdbcType=DECIMAL}, ",
        "#{rebateFee,jdbcType=DECIMAL}, #{driverTransportFee,jdbcType=DECIMAL}, ",
        "#{temporaryTransportFee,jdbcType=DECIMAL}, #{settleStatus,jdbcType=INTEGER},#{receivableWithTax,jdbcType=DECIMAL})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="reconcilicationItemId", before=false, resultType=Integer.class)
    int insert(ReconcilicationForPayableItem record);

    int insertSelective(ReconcilicationForPayableItem record);

    List<ReconcilicationForPayableItem> selectByExample(ReconcilicationForPayableItemExample example);

    @Select({
        "select",
        "reconcilication_item_id, reconcilication_id, settle_type, settle_account_id, ",
        "settle_account_name, waybill_id, waybill_no, plate_number, vehicle_frame_no, ",
        "settle_freight, tax_rate, rebate_fee, driver_transport_fee, temporary_transport_fee, ",
        "settle_status",
        "from reconcilication_for_payable_item",
        "where reconcilication_item_id = #{reconcilicationItemId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    ReconcilicationForPayableItem selectByPrimaryKey(Integer reconcilicationItemId);

    int updateByExampleSelective(@Param("record") ReconcilicationForPayableItem record, @Param("example") ReconcilicationForPayableItemExample example);

    int updateByExample(@Param("record") ReconcilicationForPayableItem record, @Param("example") ReconcilicationForPayableItemExample example);

    int updateByPrimaryKeySelective(ReconcilicationForPayableItem record);

    @Update({
        "update reconcilication_for_payable_item",
        "set reconcilication_id = #{reconcilicationId,jdbcType=INTEGER},",
          "settle_type = #{settleType,jdbcType=INTEGER},",
          "settle_account_id = #{settleAccountId,jdbcType=INTEGER},",
          "settle_account_name = #{settleAccountName,jdbcType=VARCHAR},",
          "waybill_id = #{waybillId,jdbcType=INTEGER},",
          "waybill_no = #{waybillNo,jdbcType=VARCHAR},",
          "plate_number = #{plateNumber,jdbcType=VARCHAR},",
          "vehicle_frame_no = #{vehicleFrameNo,jdbcType=VARCHAR},",
          "settle_freight = #{settleFreight,jdbcType=DECIMAL},",
          "tax_rate = #{taxRate,jdbcType=DECIMAL},",
          "rebate_fee = #{rebateFee,jdbcType=DECIMAL},",
          "driver_transport_fee = #{driverTransportFee,jdbcType=DECIMAL},",
          "temporary_transport_fee = #{temporaryTransportFee,jdbcType=DECIMAL},",
          "settle_status = #{settleStatus,jdbcType=INTEGER}",
        "where reconcilication_item_id = #{reconcilicationItemId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ReconcilicationForPayableItem record);
}