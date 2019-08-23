package com.juma.tgm.cms.dao;

import com.juma.tgm.export.domain.CustomizedExport;
import com.juma.tgm.export.domain.CustomizedExportExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface CustomizedExportMapper {
    int countByExample(CustomizedExportExample example);

    int deleteByExample(CustomizedExportExample example);

    @Delete({
        "delete from customized_export",
        "where customized_export_id = #{customizedExportId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer customizedExportId);

    @Insert({
        "insert into customized_export (tenant_id, tenant_code, ",
        "user_id, customized_export_json, ",
        "create_user_id, create_time, ",
        "is_delete, last_update_user_id, ",
        "last_update_time)",
        "values (#{tenantId,jdbcType=INTEGER}, #{tenantCode,jdbcType=VARCHAR}, ",
        "#{userId,jdbcType=INTEGER}, #{customizedExportJson,jdbcType=VARCHAR}, ",
        "#{createUserId,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{isDelete,jdbcType=BIT}, #{lastUpdateUserId,jdbcType=INTEGER}, ",
        "#{lastUpdateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="customizedExportId", before=false, resultType=Integer.class)
    int insert(CustomizedExport record);

    int insertSelective(CustomizedExport record);

    List<CustomizedExport> selectByExample(CustomizedExportExample example);

    @Select({
        "select",
        "customized_export_id, tenant_id, tenant_code, user_id, customized_export_json, ",
        "create_user_id, create_time, is_delete, last_update_user_id, last_update_time",
        "from customized_export",
        "where customized_export_id = #{customizedExportId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    CustomizedExport selectByPrimaryKey(Integer customizedExportId);

    int updateByExampleSelective(@Param("record") CustomizedExport record, @Param("example") CustomizedExportExample example);

    int updateByExample(@Param("record") CustomizedExport record, @Param("example") CustomizedExportExample example);

    int updateByPrimaryKeySelective(CustomizedExport record);

    @Update({
        "update customized_export",
        "set tenant_id = #{tenantId,jdbcType=INTEGER},",
          "tenant_code = #{tenantCode,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "customized_export_json = #{customizedExportJson,jdbcType=VARCHAR},",
          "create_user_id = #{createUserId,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "is_delete = #{isDelete,jdbcType=BIT},",
          "last_update_user_id = #{lastUpdateUserId,jdbcType=INTEGER},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP}",
        "where customized_export_id = #{customizedExportId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CustomizedExport record);
}