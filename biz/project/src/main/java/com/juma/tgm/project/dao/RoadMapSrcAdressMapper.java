package com.juma.tgm.project.dao;

import com.juma.tgm.project.domain.RoadMapSrcAdress;
import com.juma.tgm.project.domain.RoadMapSrcAdressExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface RoadMapSrcAdressMapper {
    int countByExample(RoadMapSrcAdressExample example);

    int deleteByExample(RoadMapSrcAdressExample example);

    @Delete({
        "delete from road_map_src_adress",
        "where road_map_src_adress_id = #{roadMapSrcAdressId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer roadMapSrcAdressId);

    @Insert({
        "insert into road_map_src_adress (road_map_id, region_code, ",
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
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="roadMapSrcAdressId", before=false, resultType=Integer.class)
    int insert(RoadMapSrcAdress record);

    int insertSelective(RoadMapSrcAdress record);

    List<RoadMapSrcAdress> selectByExample(RoadMapSrcAdressExample example);

    @Select({
        "select",
        "road_map_src_adress_id, road_map_id, region_code, address_name, address_detail, ",
        "coordinates, contact_name, contact_phone, create_time, create_user_id",
        "from road_map_src_adress",
        "where road_map_src_adress_id = #{roadMapSrcAdressId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    RoadMapSrcAdress selectByPrimaryKey(Integer roadMapSrcAdressId);

    int updateByExampleSelective(@Param("record") RoadMapSrcAdress record, @Param("example") RoadMapSrcAdressExample example);

    int updateByExample(@Param("record") RoadMapSrcAdress record, @Param("example") RoadMapSrcAdressExample example);

    int updateByPrimaryKeySelective(RoadMapSrcAdress record);

    @Update({
        "update road_map_src_adress",
        "set road_map_id = #{roadMapId,jdbcType=INTEGER},",
          "region_code = #{regionCode,jdbcType=VARCHAR},",
          "address_name = #{addressName,jdbcType=VARCHAR},",
          "address_detail = #{addressDetail,jdbcType=VARCHAR},",
          "coordinates = #{coordinates,jdbcType=VARCHAR},",
          "contact_name = #{contactName,jdbcType=VARCHAR},",
          "contact_phone = #{contactPhone,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "create_user_id = #{createUserId,jdbcType=INTEGER}",
        "where road_map_src_adress_id = #{roadMapSrcAdressId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RoadMapSrcAdress record);
}