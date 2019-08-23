package com.juma.tgm.vendor.dao;

import com.juma.tgm.vendor.domain.VendorMapping;
import com.juma.tgm.vendor.domain.VendorMappingExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface VendorMappingMapper {
    int countByExample(VendorMappingExample example);

    int deleteByExample(VendorMappingExample example);

    @Delete({
        "delete from vendor_mapping",
        "where vendor_mapping_id = #{vendorMappingId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer vendorMappingId);

    @Insert({
        "insert into vendor_mapping (vendor_id, vendor_tenant_id, ",
        "vendor_name, vendor_customer_id, ",
        "customer_tenant_id, vendor_customer_name, ",
        "create_time, create_user_id, ",
        "department_code, is_delete, ",
        "last_update_user_id, last_update_user_name)",
        "values (#{vendorId,jdbcType=INTEGER}, #{vendorTenantId,jdbcType=INTEGER}, ",
        "#{vendorName,jdbcType=VARCHAR}, #{vendorCustomerId,jdbcType=INTEGER}, ",
        "#{customerTenantId,jdbcType=INTEGER}, #{vendorCustomerName,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{createUserId,jdbcType=INTEGER}, ",
        "#{departmentCode,jdbcType=VARCHAR}, #{isDelete,jdbcType=BIT}, ",
        "#{lastUpdateUserId,jdbcType=INTEGER}, #{lastUpdateUserName,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="vendorMappingId", before=false, resultType=Integer.class)
    int insert(VendorMapping record);

    int insertSelective(VendorMapping record);

    List<VendorMapping> selectByExample(VendorMappingExample example);

    @Select({
        "select",
        "vendor_mapping_id, vendor_id, vendor_tenant_id, vendor_name, vendor_customer_id, ",
        "customer_tenant_id, vendor_customer_name, create_time, create_user_id, department_code, ",
        "is_delete, last_update_user_id, last_update_user_name",
        "from vendor_mapping",
        "where vendor_mapping_id = #{vendorMappingId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    VendorMapping selectByPrimaryKey(Integer vendorMappingId);

    int updateByExampleSelective(@Param("record") VendorMapping record, @Param("example") VendorMappingExample example);

    int updateByExample(@Param("record") VendorMapping record, @Param("example") VendorMappingExample example);

    int updateByPrimaryKeySelective(VendorMapping record);

    @Update({
        "update vendor_mapping",
        "set vendor_id = #{vendorId,jdbcType=INTEGER},",
          "vendor_tenant_id = #{vendorTenantId,jdbcType=INTEGER},",
          "vendor_name = #{vendorName,jdbcType=VARCHAR},",
          "vendor_customer_id = #{vendorCustomerId,jdbcType=INTEGER},",
          "customer_tenant_id = #{customerTenantId,jdbcType=INTEGER},",
          "vendor_customer_name = #{vendorCustomerName,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "create_user_id = #{createUserId,jdbcType=INTEGER},",
          "department_code = #{departmentCode,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=BIT},",
          "last_update_user_id = #{lastUpdateUserId,jdbcType=INTEGER},",
          "last_update_user_name = #{lastUpdateUserName,jdbcType=TIMESTAMP}",
        "where vendor_mapping_id = #{vendorMappingId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(VendorMapping record);
}