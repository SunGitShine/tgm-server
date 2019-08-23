package com.juma.tgm.vendor.dao;

import com.juma.tgm.vendor.domain.VendorProjectMapping;
import com.juma.tgm.vendor.domain.VendorProjectMappingExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface VendorProjectMappingMapper {
    int countByExample(VendorProjectMappingExample example);

    int deleteByExample(VendorProjectMappingExample example);

    @Delete({
        "delete from vendor_project_mapping",
        "where vendor_project_mapping_id = #{vendorProjectMappingId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer vendorProjectMappingId);

    @Insert({
        "insert into vendor_project_mapping (vendor_mapping_id, customer_id, ",
        "project_id, vendor_project_id)",
        "values (#{vendorMappingId,jdbcType=INTEGER}, #{customerId,jdbcType=INTEGER}, ",
        "#{projectId,jdbcType=INTEGER}, #{vendorProjectId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="vendorProjectMappingId", before=false, resultType=Integer.class)
    int insert(VendorProjectMapping record);

    int insertSelective(VendorProjectMapping record);

    List<VendorProjectMapping> selectByExample(VendorProjectMappingExample example);

    @Select({
        "select",
        "vendor_project_mapping_id, vendor_mapping_id, customer_id, project_id, vendor_project_id",
        "from vendor_project_mapping",
        "where vendor_project_mapping_id = #{vendorProjectMappingId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    VendorProjectMapping selectByPrimaryKey(Integer vendorProjectMappingId);

    int updateByExampleSelective(@Param("record") VendorProjectMapping record, @Param("example") VendorProjectMappingExample example);

    int updateByExample(@Param("record") VendorProjectMapping record, @Param("example") VendorProjectMappingExample example);

    int updateByPrimaryKeySelective(VendorProjectMapping record);

    @Update({
        "update vendor_project_mapping",
        "set vendor_mapping_id = #{vendorMappingId,jdbcType=INTEGER},",
          "customer_id = #{customerId,jdbcType=INTEGER},",
          "project_id = #{projectId,jdbcType=INTEGER},",
          "vendor_project_id = #{vendorProjectId,jdbcType=INTEGER}",
        "where vendor_project_mapping_id = #{vendorProjectMappingId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(VendorProjectMapping record);
}