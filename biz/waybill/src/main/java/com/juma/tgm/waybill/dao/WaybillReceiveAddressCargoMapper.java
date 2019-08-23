package com.juma.tgm.waybill.dao;

import com.juma.tgm.waybill.domain.WaybillReceiveAddressCargo;
import com.juma.tgm.waybill.domain.WaybillReceiveAddressCargoExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface WaybillReceiveAddressCargoMapper {
    int countByExample(WaybillReceiveAddressCargoExample example);

    int deleteByExample(WaybillReceiveAddressCargoExample example);

    @Delete({
        "delete from waybill_receive_address_cargo",
        "where cargo_id = #{cargoId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer cargoId);

    @Insert({
        "insert into waybill_receive_address_cargo (address_id, cargo_name, ",
        "cargo_type, cargo_volume, ",
        "cargo_packages, cargo_weight)",
        "values (#{addressId,jdbcType=INTEGER}, #{cargoName,jdbcType=VARCHAR}, ",
        "#{cargoType,jdbcType=VARCHAR}, #{cargoVolume,jdbcType=REAL}, ",
        "#{cargoPackages,jdbcType=INTEGER}, #{cargoWeight,jdbcType=REAL})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="cargoId", before=false, resultType=Integer.class)
    int insert(WaybillReceiveAddressCargo record);

    int insertSelective(WaybillReceiveAddressCargo record);

    List<WaybillReceiveAddressCargo> selectByExample(WaybillReceiveAddressCargoExample example);

    @Select({
        "select",
        "cargo_id, address_id, cargo_name, cargo_type, cargo_volume, cargo_packages, ",
        "cargo_weight",
        "from waybill_receive_address_cargo",
        "where cargo_id = #{cargoId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    WaybillReceiveAddressCargo selectByPrimaryKey(Integer cargoId);

    int updateByExampleSelective(@Param("record") WaybillReceiveAddressCargo record, @Param("example") WaybillReceiveAddressCargoExample example);

    int updateByExample(@Param("record") WaybillReceiveAddressCargo record, @Param("example") WaybillReceiveAddressCargoExample example);

    int updateByPrimaryKeySelective(WaybillReceiveAddressCargo record);

    @Update({
        "update waybill_receive_address_cargo",
        "set address_id = #{addressId,jdbcType=INTEGER},",
          "cargo_name = #{cargoName,jdbcType=VARCHAR},",
          "cargo_type = #{cargoType,jdbcType=VARCHAR},",
          "cargo_volume = #{cargoVolume,jdbcType=REAL},",
          "cargo_packages = #{cargoPackages,jdbcType=INTEGER},",
          "cargo_weight = #{cargoWeight,jdbcType=REAL}",
        "where cargo_id = #{cargoId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WaybillReceiveAddressCargo record);
}