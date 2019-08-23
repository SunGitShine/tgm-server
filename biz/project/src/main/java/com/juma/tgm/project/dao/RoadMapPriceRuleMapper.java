package com.juma.tgm.project.dao;

import com.juma.tgm.project.domain.RoadMapPriceRule;
import com.juma.tgm.project.domain.RoadMapPriceRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface RoadMapPriceRuleMapper {
    int countByExample(RoadMapPriceRuleExample example);

    int deleteByExample(RoadMapPriceRuleExample example);

    @Delete({
        "delete from road_map_price_rule",
        "where road_map_price_rule_id = #{roadMapPriceRuleId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer roadMapPriceRuleId);

    @Insert({
        "insert into road_map_price_rule (road_map_id, valuation_way, ",
        "truck_type_id, valuation_model_json, ",
        "create_user_id, create_time)",
        "values (#{roadMapId,jdbcType=INTEGER}, #{valuationWay,jdbcType=TINYINT}, ",
        "#{truckTypeId,jdbcType=INTEGER}, #{valuationModelJson,jdbcType=VARCHAR}, ",
        "#{createUserId,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="roadMapPriceRuleId", before=false, resultType=Integer.class)
    int insert(RoadMapPriceRule record);

    int insertSelective(RoadMapPriceRule record);

    List<RoadMapPriceRule> selectByExample(RoadMapPriceRuleExample example);

    @Select({
        "select",
        "road_map_price_rule_id, road_map_id, valuation_way, truck_type_id, valuation_model_json, ",
        "create_user_id, create_time",
        "from road_map_price_rule",
        "where road_map_price_rule_id = #{roadMapPriceRuleId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    RoadMapPriceRule selectByPrimaryKey(Integer roadMapPriceRuleId);

    int updateByExampleSelective(@Param("record") RoadMapPriceRule record, @Param("example") RoadMapPriceRuleExample example);

    int updateByExample(@Param("record") RoadMapPriceRule record, @Param("example") RoadMapPriceRuleExample example);

    int updateByPrimaryKeySelective(RoadMapPriceRule record);

    @Update({
        "update road_map_price_rule",
        "set road_map_id = #{roadMapId,jdbcType=INTEGER},",
          "valuation_way = #{valuationWay,jdbcType=TINYINT},",
          "truck_type_id = #{truckTypeId,jdbcType=INTEGER},",
          "valuation_model_json = #{valuationModelJson,jdbcType=VARCHAR},",
          "create_user_id = #{createUserId,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where road_map_price_rule_id = #{roadMapPriceRuleId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RoadMapPriceRule record);
}