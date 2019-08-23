package com.juma.tgm.fms.dao.v3;

import com.juma.tgm.fms.domain.v3.ReconcilicationForReceivableItem;
import com.juma.tgm.fms.domain.v3.vo.ReconcilicationForReceivableItemVo;
import com.juma.tgm.fms.domain.v3.ReconcilicationForReceivableItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface ReconcilicationForReceivableItemMapper {
    int countByExample(ReconcilicationForReceivableItemExample example);

    int deleteByExample(ReconcilicationForReceivableItemExample example);

    @Delete({
        "delete from reconcilication_for_receivable_item",
        "where reconcilication_item_id = #{reconcilicationItemId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer reconcilicationItemId);

    @Insert({
        "insert into reconcilication_for_receivable_item (reconcilication_id, waybill_id, ",
        "waybill_no, plan_delivery_time, ",
        "receivable_with_tax, receivable_without_tax, ",
        "tax_rate, rebate_fee, ",
        "settle_status, payable_reconcilication_status)",
        "values (#{reconcilicationId,jdbcType=INTEGER}, #{waybillId,jdbcType=INTEGER}, ",
        "#{waybillNo,jdbcType=VARCHAR}, #{planDeliveryTime,jdbcType=TIMESTAMP}, ",
        "#{receivableWithTax,jdbcType=DECIMAL}, #{receivableWithoutTax,jdbcType=DECIMAL}, ",
        "#{taxRate,jdbcType=DECIMAL}, #{rebateFee,jdbcType=DECIMAL}, ",
        "#{settleStatus,jdbcType=INTEGER}, #{payableReconcilicationStatus,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="reconcilicationItemId", before=false, resultType=Integer.class)
    int insert(ReconcilicationForReceivableItemVo record);

    int insertSelective(ReconcilicationForReceivableItemVo record);

    List<ReconcilicationForReceivableItem> selectByExample(ReconcilicationForReceivableItemExample example);

    @Select({
        "select",
        "reconcilication_item_id, reconcilication_id, waybill_id, waybill_no, plan_delivery_time, ",
        "receivable_with_tax, receivable_without_tax, tax_rate, rebate_fee, settle_status, ",
        "payable_reconcilication_status",
        "from reconcilication_for_receivable_item",
        "where reconcilication_item_id = #{reconcilicationItemId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
	ReconcilicationForReceivableItemVo selectByPrimaryKey(Integer reconcilicationItemId);

    int updateByExampleSelective(@Param("record") ReconcilicationForReceivableItemVo record, @Param("example") ReconcilicationForReceivableItemExample example);

    int updateByExample(@Param("record") ReconcilicationForReceivableItemVo record, @Param("example") ReconcilicationForReceivableItemExample example);

    int updateByPrimaryKeySelective(ReconcilicationForReceivableItemVo record);

    @Update({
        "update reconcilication_for_receivable_item",
        "set reconcilication_id = #{reconcilicationId,jdbcType=INTEGER},",
          "waybill_id = #{waybillId,jdbcType=INTEGER},",
          "waybill_no = #{waybillNo,jdbcType=VARCHAR},",
          "plan_delivery_time = #{planDeliveryTime,jdbcType=TIMESTAMP},",
          "receivable_with_tax = #{receivableWithTax,jdbcType=DECIMAL},",
          "receivable_without_tax = #{receivableWithoutTax,jdbcType=DECIMAL},",
          "tax_rate = #{taxRate,jdbcType=DECIMAL},",
          "rebate_fee = #{rebateFee,jdbcType=DECIMAL},",
          "settle_status = #{settleStatus,jdbcType=INTEGER},",
          "payable_reconcilication_status = #{payableReconcilicationStatus,jdbcType=INTEGER}",
        "where reconcilication_item_id = #{reconcilicationItemId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ReconcilicationForReceivableItemVo record);
}