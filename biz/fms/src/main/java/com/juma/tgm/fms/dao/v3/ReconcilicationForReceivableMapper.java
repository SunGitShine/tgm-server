package com.juma.tgm.fms.dao.v3;

import com.juma.tgm.fms.domain.v3.ReconcilicationForReceivable;
import com.juma.tgm.fms.domain.v3.ReconcilicationForReceivableExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface ReconcilicationForReceivableMapper {
    int countByExample(ReconcilicationForReceivableExample example);

    int deleteByExample(ReconcilicationForReceivableExample example);

    @Delete({
        "delete from reconcilication_for_receivable",
        "where reconcilication_id = #{reconcilicationId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer reconcilicationId);

    @Insert({
        "insert into reconcilication_for_receivable (tenant_id, tenant_code, ",
        "area_code, reconcilication_no, ",
        "customer_id, customer_name, ",
        "project_id, project_name, ",
        "receivable_with_tax, receivable_without_tax, ",
        "process_instance_id, submit_time, ",
        "submit_user_id, approval_status, ",
        "receive_status, invoice_status, ",
        "create_time, create_user_id)",
        "values (#{tenantId,jdbcType=INTEGER}, #{tenantCode,jdbcType=VARCHAR}, ",
        "#{areaCode,jdbcType=VARCHAR}, #{reconcilicationNo,jdbcType=VARCHAR}, ",
        "#{customerId,jdbcType=INTEGER}, #{customerName,jdbcType=VARCHAR}, ",
        "#{projectId,jdbcType=INTEGER}, #{projectName,jdbcType=VARCHAR}, ",
        "#{receivableWithTax,jdbcType=DECIMAL}, #{receivableWithoutTax,jdbcType=DECIMAL}, ",
        "#{processInstanceId,jdbcType=VARCHAR}, #{submitTime,jdbcType=TIMESTAMP}, ",
        "#{submitUserId,jdbcType=INTEGER}, #{approvalStatus,jdbcType=INTEGER}, ",
        "#{receiveStatus,jdbcType=INTEGER}, #{invoiceStatus,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{createUserId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="reconcilicationId", before=false, resultType=Integer.class)
    int insert(ReconcilicationForReceivable record);

    int insertSelective(ReconcilicationForReceivable record);

    List<ReconcilicationForReceivable> selectByExample(ReconcilicationForReceivableExample example);

    @Select({
        "select",
        "reconcilication_id, tenant_id, tenant_code, area_code, reconcilication_no, customer_id, ",
        "customer_name, project_id, project_name, receivable_with_tax, receivable_without_tax, ",
        "process_instance_id, submit_time, submit_user_id, approval_status, receive_status, ",
        "invoice_status, create_time, create_user_id",
        "from reconcilication_for_receivable",
        "where reconcilication_id = #{reconcilicationId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    ReconcilicationForReceivable selectByPrimaryKey(Integer reconcilicationId);

    int updateByExampleSelective(@Param("record") ReconcilicationForReceivable record, @Param("example") ReconcilicationForReceivableExample example);

    int updateByExample(@Param("record") ReconcilicationForReceivable record, @Param("example") ReconcilicationForReceivableExample example);

    int updateByPrimaryKeySelective(ReconcilicationForReceivable record);

    @Update({
        "update reconcilication_for_receivable",
        "set tenant_id = #{tenantId,jdbcType=INTEGER},",
          "tenant_code = #{tenantCode,jdbcType=VARCHAR},",
          "area_code = #{areaCode,jdbcType=VARCHAR},",
          "reconcilication_no = #{reconcilicationNo,jdbcType=VARCHAR},",
          "customer_id = #{customerId,jdbcType=INTEGER},",
          "customer_name = #{customerName,jdbcType=VARCHAR},",
          "project_id = #{projectId,jdbcType=INTEGER},",
          "project_name = #{projectName,jdbcType=VARCHAR},",
          "receivable_with_tax = #{receivableWithTax,jdbcType=DECIMAL},",
          "receivable_without_tax = #{receivableWithoutTax,jdbcType=DECIMAL},",
          "process_instance_id = #{processInstanceId,jdbcType=VARCHAR},",
          "submit_time = #{submitTime,jdbcType=TIMESTAMP},",
          "submit_user_id = #{submitUserId,jdbcType=INTEGER},",
          "approval_status = #{approvalStatus,jdbcType=INTEGER},",
          "receive_status = #{receiveStatus,jdbcType=INTEGER},",
          "invoice_status = #{invoiceStatus,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "create_user_id = #{createUserId,jdbcType=INTEGER}",
        "where reconcilication_id = #{reconcilicationId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ReconcilicationForReceivable record);
}