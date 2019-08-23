package com.juma.tgm.operateLog.dao;

import com.juma.tgm.operateLog.domain.OperateLog;
import com.juma.tgm.operateLog.domain.OperateLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface OperateLogMapper {
    int countByExample(OperateLogExample example);

    int deleteByExample(OperateLogExample example);

    @Delete({
        "delete from operate_log",
        "where operate_log_id = #{operateLogId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer operateLogId);

    @Insert({
        "insert into operate_log (log_sign, relation_table_id, ",
        "operate_type, operate_applicatoin, ",
        "create_user_id, create_time, ",
        "remark)",
        "values (#{logSign,jdbcType=TINYINT}, #{relationTableId,jdbcType=INTEGER}, ",
        "#{operateType,jdbcType=TINYINT}, #{operateApplicatoin,jdbcType=TINYINT}, ",
        "#{createUserId,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{remark,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="operateLogId", before=false, resultType=Integer.class)
    int insert(OperateLog record);

    int insertSelective(OperateLog record);

    List<OperateLog> selectByExample(OperateLogExample example);

    @Select({
        "select",
        "operate_log_id, log_sign, relation_table_id, operate_type, operate_applicatoin, ",
        "create_user_id, create_time, remark",
        "from operate_log",
        "where operate_log_id = #{operateLogId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    OperateLog selectByPrimaryKey(Integer operateLogId);

    int updateByExampleSelective(@Param("record") OperateLog record, @Param("example") OperateLogExample example);

    int updateByExample(@Param("record") OperateLog record, @Param("example") OperateLogExample example);

    int updateByPrimaryKeySelective(OperateLog record);

    @Update({
        "update operate_log",
        "set log_sign = #{logSign,jdbcType=TINYINT},",
          "relation_table_id = #{relationTableId,jdbcType=INTEGER},",
          "operate_type = #{operateType,jdbcType=TINYINT},",
          "operate_applicatoin = #{operateApplicatoin,jdbcType=TINYINT},",
          "create_user_id = #{createUserId,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where operate_log_id = #{operateLogId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(OperateLog record);
}