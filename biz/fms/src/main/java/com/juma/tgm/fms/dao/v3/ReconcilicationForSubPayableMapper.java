package com.juma.tgm.fms.dao.v3;

import com.juma.tgm.fms.domain.v3.ReconcilicationForSubPayable;
import com.juma.tgm.fms.domain.v3.ReconcilicationForSubPayableExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface ReconcilicationForSubPayableMapper {
    int countByExample(ReconcilicationForSubPayableExample example);

    int deleteByExample(ReconcilicationForSubPayableExample example);

    @Delete({
        "delete from reconcilication_for_sub_payable",
        "where sub_reconcilication_id = #{subReconcilicationId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer subReconcilicationId);

    @Insert({
        "insert into reconcilication_for_sub_payable (sub_reconcilication_no, reconcilication_id, ",
        "vendor_id, settle_status, ",
        "create_user_id, create_time)",
        "values (#{subReconcilicationNo,jdbcType=VARCHAR}, #{reconcilicationId,jdbcType=INTEGER}, ",
        "#{vendorId,jdbcType=INTEGER}, #{settleStatus,jdbcType=TINYINT}, ",
        "#{createUserId,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="subReconcilicationId", before=false, resultType=Integer.class)
    int insert(ReconcilicationForSubPayable record);

    int insertSelective(ReconcilicationForSubPayable record);

    List<ReconcilicationForSubPayable> selectByExample(ReconcilicationForSubPayableExample example);

    @Select({
        "select",
        "sub_reconcilication_id, sub_reconcilication_no, reconcilication_id, vendor_id, ",
        "settle_status, create_user_id, create_time",
        "from reconcilication_for_sub_payable",
        "where sub_reconcilication_id = #{subReconcilicationId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    ReconcilicationForSubPayable selectByPrimaryKey(Integer subReconcilicationId);

    int updateByExampleSelective(@Param("record") ReconcilicationForSubPayable record, @Param("example") ReconcilicationForSubPayableExample example);

    int updateByExample(@Param("record") ReconcilicationForSubPayable record, @Param("example") ReconcilicationForSubPayableExample example);

    int updateByPrimaryKeySelective(ReconcilicationForSubPayable record);

    @Update({
        "update reconcilication_for_sub_payable",
        "set sub_reconcilication_no = #{subReconcilicationNo,jdbcType=VARCHAR},",
          "reconcilication_id = #{reconcilicationId,jdbcType=INTEGER},",
          "vendor_id = #{vendorId,jdbcType=INTEGER},",
          "settle_status = #{settleStatus,jdbcType=TINYINT},",
          "create_user_id = #{createUserId,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where sub_reconcilication_id = #{subReconcilicationId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ReconcilicationForSubPayable record);
}