package com.juma.tgm.truck.dao;

import com.juma.tgm.truck.domain.TruckFleetTruck;
import com.juma.tgm.truck.domain.TruckFleetTruckExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.generator.my.mapper.Mapper;

public interface TruckFleetTruckMapper extends Mapper {
    long countByExample(TruckFleetTruckExample example);

    int deleteByExample(TruckFleetTruckExample example);

    int deleteByPrimaryKey(Integer truckFleetTruckId);

    int insert(TruckFleetTruck record);

    int insertSelective(TruckFleetTruck record);

    List<TruckFleetTruck> selectByExample(TruckFleetTruckExample example);

    TruckFleetTruck selectByPrimaryKey(Integer truckFleetTruckId);

    int updateByExampleSelective(@Param("record") TruckFleetTruck record, @Param("example") TruckFleetTruckExample example);

    int updateByExample(@Param("record") TruckFleetTruck record, @Param("example") TruckFleetTruckExample example);

    int updateByPrimaryKeySelective(TruckFleetTruck record);

    int updateByPrimaryKey(TruckFleetTruck record);
}