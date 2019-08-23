package com.juma.tgm.customerManager.dao;

import com.juma.tgm.customerManager.domain.FixedDemandTruck;
import com.juma.tgm.customerManager.domain.FixedDemandTruckExample;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Deprecated
public interface FixedDemandTruckMapper {
    int countByExample(FixedDemandTruckExample example);

    int deleteByExample(FixedDemandTruckExample example);

    @Delete({
        "delete from fixed_demand_truck",
        "where fixed_demand_truck_id = #{fixedDemandTruckId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer fixedDemandTruckId);

    @Insert({
        "insert into fixed_demand_truck (fixed_demand_id, truck_id)",
        "values (#{fixedDemandId,jdbcType=INTEGER}, #{truckId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="fixedDemandTruckId", before=false, resultType=Integer.class)
    int insert(FixedDemandTruck record);

    int insertSelective(FixedDemandTruck record);

    List<FixedDemandTruck> selectByExample(FixedDemandTruckExample example);

    @Select({
        "select",
        "fixed_demand_truck_id, fixed_demand_id, truck_id",
        "from fixed_demand_truck",
        "where fixed_demand_truck_id = #{fixedDemandTruckId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    FixedDemandTruck selectByPrimaryKey(Integer fixedDemandTruckId);

    int updateByExampleSelective(@Param("record") FixedDemandTruck record, @Param("example") FixedDemandTruckExample example);

    int updateByExample(@Param("record") FixedDemandTruck record, @Param("example") FixedDemandTruckExample example);

    int updateByPrimaryKeySelective(FixedDemandTruck record);

    @Update({
        "update fixed_demand_truck",
        "set fixed_demand_id = #{fixedDemandId,jdbcType=INTEGER},",
          "truck_id = #{truckId,jdbcType=INTEGER}",
        "where fixed_demand_truck_id = #{fixedDemandTruckId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FixedDemandTruck record);
}