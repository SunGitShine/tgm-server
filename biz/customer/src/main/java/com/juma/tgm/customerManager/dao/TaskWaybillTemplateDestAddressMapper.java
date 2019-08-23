package com.juma.tgm.customerManager.dao;

import com.juma.tgm.customerManager.domain.TaskWaybillTemplateDestAddress;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateDestAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface TaskWaybillTemplateDestAddressMapper {
    int countByExample(TaskWaybillTemplateDestAddressExample example);

    int deleteByExample(TaskWaybillTemplateDestAddressExample example);

    @Delete({
        "delete from task_waybill_template_dest_address",
        "where address_id = #{addressId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer addressId);

    @Insert({
        "insert into task_waybill_template_dest_address (task_waybill_template_id, region_code, ",
        "address_name, address_detail, ",
        "contact_name, contact_phone, ",
        "coordinates, create_time, ",
        "create_user_id, last_update_time, ",
        "last_update_user_id)",
        "values (#{taskWaybillTemplateId,jdbcType=INTEGER}, #{regionCode,jdbcType=VARCHAR}, ",
        "#{addressName,jdbcType=VARCHAR}, #{addressDetail,jdbcType=VARCHAR}, ",
        "#{contactName,jdbcType=VARCHAR}, #{contactPhone,jdbcType=VARCHAR}, ",
        "#{coordinates,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{createUserId,jdbcType=INTEGER}, #{lastUpdateTime,jdbcType=TIMESTAMP}, ",
        "#{lastUpdateUserId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="addressId", before=false, resultType=Integer.class)
    int insert(TaskWaybillTemplateDestAddress record);

    int insertSelective(TaskWaybillTemplateDestAddress record);

    List<TaskWaybillTemplateDestAddress> selectByExample(TaskWaybillTemplateDestAddressExample example);

    @Select({
        "select",
        "address_id, task_waybill_template_id, region_code, address_name, address_detail, ",
        "contact_name, contact_phone, coordinates, create_time, create_user_id, last_update_time, ",
        "last_update_user_id",
        "from task_waybill_template_dest_address",
        "where address_id = #{addressId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    TaskWaybillTemplateDestAddress selectByPrimaryKey(Integer addressId);

    int updateByExampleSelective(@Param("record") TaskWaybillTemplateDestAddress record, @Param("example") TaskWaybillTemplateDestAddressExample example);

    int updateByExample(@Param("record") TaskWaybillTemplateDestAddress record, @Param("example") TaskWaybillTemplateDestAddressExample example);

    int updateByPrimaryKeySelective(TaskWaybillTemplateDestAddress record);

    @Update({
        "update task_waybill_template_dest_address",
        "set task_waybill_template_id = #{taskWaybillTemplateId,jdbcType=INTEGER},",
          "region_code = #{regionCode,jdbcType=VARCHAR},",
          "address_name = #{addressName,jdbcType=VARCHAR},",
          "address_detail = #{addressDetail,jdbcType=VARCHAR},",
          "contact_name = #{contactName,jdbcType=VARCHAR},",
          "contact_phone = #{contactPhone,jdbcType=VARCHAR},",
          "coordinates = #{coordinates,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "create_user_id = #{createUserId,jdbcType=INTEGER},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "last_update_user_id = #{lastUpdateUserId,jdbcType=INTEGER}",
        "where address_id = #{addressId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TaskWaybillTemplateDestAddress record);
}