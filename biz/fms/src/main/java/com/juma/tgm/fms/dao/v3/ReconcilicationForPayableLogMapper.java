package com.juma.tgm.fms.dao.v3;

import com.juma.tgm.fms.domain.v3.ReconcilicationForPayableLog;
import com.juma.tgm.fms.domain.v3.ReconcilicationForPayableLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReconcilicationForPayableLogMapper {
    long countByExample(ReconcilicationForPayableLogExample example);

    int deleteByExample(ReconcilicationForPayableLogExample example);

    int deleteByPrimaryKey(Integer logId);

    int insert(ReconcilicationForPayableLog record);

    int insertSelective(ReconcilicationForPayableLog record);

    List<ReconcilicationForPayableLog> selectByExampleWithBLOBs(ReconcilicationForPayableLogExample example);

    List<ReconcilicationForPayableLog> selectByExample(ReconcilicationForPayableLogExample example);

    ReconcilicationForPayableLog selectByPrimaryKey(Integer logId);

    int updateByExampleSelective(@Param("record") ReconcilicationForPayableLog record, @Param("example") ReconcilicationForPayableLogExample example);

    int updateByExampleWithBLOBs(@Param("record") ReconcilicationForPayableLog record, @Param("example") ReconcilicationForPayableLogExample example);

    int updateByExample(@Param("record") ReconcilicationForPayableLog record, @Param("example") ReconcilicationForPayableLogExample example);

    int updateByPrimaryKeySelective(ReconcilicationForPayableLog record);

    int updateByPrimaryKeyWithBLOBs(ReconcilicationForPayableLog record);

    int updateByPrimaryKey(ReconcilicationForPayableLog record);

    int insertBatch(List<ReconcilicationForPayableLog> list);

    int updateBatchSelective(List<ReconcilicationForPayableLog> list);
}