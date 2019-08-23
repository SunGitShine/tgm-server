package com.juma.tgm.waybill.dao;

import com.juma.tgm.waybill.domain.TaxRate;
import com.juma.tgm.waybill.domain.TaxRateExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface TaxRateMapper {
    int countByExample(TaxRateExample example);

    int deleteByExample(TaxRateExample example);

    @Delete({
        "delete from tax_rate",
        "where tax_rate_id = #{taxRateId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer taxRateId);

    @Insert({
        "insert into tax_rate (tax_rate_name, tax_rate_value, ",
        "tax_rate_value_text, tenant_id, ",
        "tenant_code, create_time, ",
        "create_user_id, is_delete, ",
        "last_update_time, last_update_user_id)",
        "values (#{taxRateName,jdbcType=VARCHAR}, #{taxRateValue,jdbcType=DECIMAL}, ",
        "#{taxRateValueText,jdbcType=VARCHAR}, #{tenantId,jdbcType=INTEGER}, ",
        "#{tenantCode,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{createUserId,jdbcType=INTEGER}, #{isDelete,jdbcType=BIT}, ",
        "#{lastUpdateTime,jdbcType=TIMESTAMP}, #{lastUpdateUserId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="taxRateId", before=false, resultType=Integer.class)
    int insert(TaxRate record);

    int insertSelective(TaxRate record);

    List<TaxRate> selectByExample(TaxRateExample example);

    @Select({
        "select",
        "tax_rate_id, tax_rate_name, tax_rate_value, tax_rate_value_text, tenant_id, ",
        "tenant_code, create_time, create_user_id, is_delete, last_update_time, last_update_user_id",
        "from tax_rate",
        "where tax_rate_id = #{taxRateId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    TaxRate selectByPrimaryKey(Integer taxRateId);

    int updateByExampleSelective(@Param("record") TaxRate record, @Param("example") TaxRateExample example);

    int updateByExample(@Param("record") TaxRate record, @Param("example") TaxRateExample example);

    int updateByPrimaryKeySelective(TaxRate record);

    @Update({
        "update tax_rate",
        "set tax_rate_name = #{taxRateName,jdbcType=VARCHAR},",
          "tax_rate_value = #{taxRateValue,jdbcType=DECIMAL},",
          "tax_rate_value_text = #{taxRateValueText,jdbcType=VARCHAR},",
          "tenant_id = #{tenantId,jdbcType=INTEGER},",
          "tenant_code = #{tenantCode,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "create_user_id = #{createUserId,jdbcType=INTEGER},",
          "is_delete = #{isDelete,jdbcType=BIT},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "last_update_user_id = #{lastUpdateUserId,jdbcType=INTEGER}",
        "where tax_rate_id = #{taxRateId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TaxRate record);
}