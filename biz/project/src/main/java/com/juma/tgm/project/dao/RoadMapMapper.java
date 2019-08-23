package com.juma.tgm.project.dao;

import com.juma.tgm.project.domain.RoadMap;
import com.juma.tgm.project.domain.RoadMapExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface RoadMapMapper {
    int countByExample(RoadMapExample example);

    int deleteByExample(RoadMapExample example);

    @Delete({
        "delete from road_map",
        "where road_map_id = #{roadMapId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer roadMapId);

    @Insert({
        "insert into road_map (project_id, name, ",
        "who_write_work, create_user_id, ",
        "create_time, is_delete, ",
        "last_update_user_id, last_update_time)",
        "values (#{projectId,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{whoWriteWork,jdbcType=TINYINT}, #{createUserId,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{isDelete,jdbcType=TINYINT}, ",
        "#{lastUpdateUserId,jdbcType=INTEGER}, #{lastUpdateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="roadMapId", before=false, resultType=Integer.class)
    int insert(RoadMap record);

    int insertSelective(RoadMap record);

    List<RoadMap> selectByExample(RoadMapExample example);

    @Select({
        "select",
        "road_map_id, project_id, name, who_write_work, create_user_id, create_time, ",
        "is_delete, last_update_user_id, last_update_time",
        "from road_map",
        "where road_map_id = #{roadMapId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    RoadMap selectByPrimaryKey(Integer roadMapId);

    int updateByExampleSelective(@Param("record") RoadMap record, @Param("example") RoadMapExample example);

    int updateByExample(@Param("record") RoadMap record, @Param("example") RoadMapExample example);

    int updateByPrimaryKeySelective(RoadMap record);

    @Update({
        "update road_map",
        "set project_id = #{projectId,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "who_write_work = #{whoWriteWork,jdbcType=TINYINT},",
          "create_user_id = #{createUserId,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "is_delete = #{isDelete,jdbcType=TINYINT},",
          "last_update_user_id = #{lastUpdateUserId,jdbcType=INTEGER},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP}",
        "where road_map_id = #{roadMapId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RoadMap record);
}