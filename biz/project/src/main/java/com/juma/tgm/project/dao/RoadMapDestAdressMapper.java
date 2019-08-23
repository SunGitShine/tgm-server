package com.juma.tgm.project.dao;

import com.juma.tgm.project.domain.RoadMapDestAdress;
import com.juma.tgm.project.domain.RoadMapDestAdressExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface RoadMapDestAdressMapper {
    int countByExample(RoadMapDestAdressExample example);

    int deleteByExample(RoadMapDestAdressExample example);

    @Delete({
        "delete from road_map_dest_adress",
        "where road_map_dest_adress_id = #{roadMapDestAdressId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer roadMapDestAdressId);

    @Insert({
        "insert into road_map_dest_adress (road_map_id, region_code, ",
        "address_name, address_detail, ",
        "coordinates, contact_name, ",
        "contact_phone, create_time, ",
        "create_user_id)",
        "values (#{roadMapId,jdbcType=INTEGER}, #{regionCode,jdbcType=VARCHAR}, ",
        "#{addressName,jdbcType=VARCHAR}, #{addressDetail,jdbcType=VARCHAR}, ",
        "#{coordinates,jdbcType=VARCHAR}, #{contactName,jdbcType=VARCHAR}, ",
        "#{contactPhone,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{createUserId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="roadMapDestAdressId", before=false, resultType=Integer.class)
    int insert(RoadMapDestAdress record);

    int insertSelective(RoadMapDestAdress record);

    List<RoadMapDestAdress> selectByExample(RoadMapDestAdressExample example);

    @Select({
        "select",
        "road_map_dest_adress_id, road_map_id, region_code, address_name, address_detail, ",
        "coordinates, contact_name, contact_phone, create_time, create_user_id",
        "from road_map_dest_adress",
        "where road_map_dest_adress_id = #{roadMapDestAdressId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    RoadMapDestAdress selectByPrimaryKey(Integer roadMapDestAdressId);

    int updateByExampleSelective(@Param("record") RoadMapDestAdress record, @Param("example") RoadMapDestAdressExample example);

    int updateByExample(@Param("record") RoadMapDestAdress record, @Param("example") RoadMapDestAdressExample example);

    int updateByPrimaryKeySelective(RoadMapDestAdress record);

    @Update({
        "update road_map_dest_adress",
        "set road_map_id = #{roadMapId,jdbcType=INTEGER},",
          "region_code = #{regionCode,jdbcType=VARCHAR},",
          "address_name = #{addressName,jdbcType=VARCHAR},",
          "address_detail = #{addressDetail,jdbcType=VARCHAR},",
          "coordinates = #{coordinates,jdbcType=VARCHAR},",
          "contact_name = #{contactName,jdbcType=VARCHAR},",
          "contact_phone = #{contactPhone,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "create_user_id = #{createUserId,jdbcType=INTEGER}",
        "where road_map_dest_adress_id = #{roadMapDestAdressId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RoadMapDestAdress record);
}