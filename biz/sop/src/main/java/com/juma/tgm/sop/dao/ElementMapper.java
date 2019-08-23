package com.juma.tgm.sop.dao;

import com.juma.tgm.sop.domain.Element;
import com.juma.tgm.sop.domain.ElementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ElementMapper {
    long countByExample(ElementExample example);

    int deleteByExample(ElementExample example);

    int deleteByPrimaryKey(Integer elementId);

    int insert(Element record);

    int insertSelective(Element record);

    List<Element> selectByExample(ElementExample example);

    Element selectByPrimaryKey(Integer elementId);

    int updateByExampleSelective(@Param("record") Element record, @Param("example") ElementExample example);

    int updateByExample(@Param("record") Element record, @Param("example") ElementExample example);

    int updateByPrimaryKeySelective(Element record);

    int updateByPrimaryKey(Element record);
}