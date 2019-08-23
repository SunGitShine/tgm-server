package com.juma.tgm.fms.dao.v3;

import com.juma.tgm.fms.domain.v3.ReconcilicationForPayable;
import com.juma.tgm.fms.domain.v3.ReconcilicationForPayableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.generator.my.mapper.Mapper;

public interface ReconcilicationForPayableMapper extends Mapper {
    long countByExample(ReconcilicationForPayableExample example);

    int deleteByExample(ReconcilicationForPayableExample example);

    int deleteByPrimaryKey(Integer reconcilicationId);

    int insert(ReconcilicationForPayable record);

    int insertSelective(ReconcilicationForPayable record);

    List<ReconcilicationForPayable> selectByExample(ReconcilicationForPayableExample example);

    ReconcilicationForPayable selectByPrimaryKey(Integer reconcilicationId);

    int updateByExampleSelective(@Param("record") ReconcilicationForPayable record, @Param("example") ReconcilicationForPayableExample example);

    int updateByExample(@Param("record") ReconcilicationForPayable record, @Param("example") ReconcilicationForPayableExample example);

    int updateByPrimaryKeySelective(ReconcilicationForPayable record);

    int updateByPrimaryKey(ReconcilicationForPayable record);
}