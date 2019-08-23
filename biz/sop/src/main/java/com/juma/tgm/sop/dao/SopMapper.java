package com.juma.tgm.sop.dao;

import com.juma.tgm.sop.domain.Sop;
import com.juma.tgm.sop.domain.SopExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SopMapper {
    long countByExample(SopExample example);

    int deleteByExample(SopExample example);

    int deleteByPrimaryKey(Integer sopId);

    int insert(Sop record);

    int insertSelective(Sop record);

    List<Sop> selectByExampleWithBLOBs(SopExample example);

    List<Sop> selectByExample(SopExample example);

    Sop selectByPrimaryKey(Integer sopId);

    int updateByExampleSelective(@Param("record") Sop record, @Param("example") SopExample example);

    int updateByExampleWithBLOBs(@Param("record") Sop record, @Param("example") SopExample example);

    int updateByExample(@Param("record") Sop record, @Param("example") SopExample example);

    int updateByPrimaryKeySelective(Sop record);

    int updateByPrimaryKeyWithBLOBs(Sop record);

    int updateByPrimaryKey(Sop record);
}