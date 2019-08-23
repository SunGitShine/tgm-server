package com.juma.tgm.configure.dao;

import com.juma.tgm.configure.domain.FreightFormula;
import com.juma.tgm.configure.domain.FreightFormulaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FreightFormulaMapper {
    long countByExample(FreightFormulaExample example);

    int deleteByExample(FreightFormulaExample example);

    int deleteByPrimaryKey(Integer freightFormulaId);

    int insert(FreightFormula record);

    int insertSelective(FreightFormula record);

    List<FreightFormula> selectByExample(FreightFormulaExample example);

    FreightFormula selectByPrimaryKey(Integer freightFormulaId);

    int updateByExampleSelective(@Param("record") FreightFormula record, @Param("example") FreightFormulaExample example);

    int updateByExample(@Param("record") FreightFormula record, @Param("example") FreightFormulaExample example);

    int updateByPrimaryKeySelective(FreightFormula record);

    int updateByPrimaryKey(FreightFormula record);
}