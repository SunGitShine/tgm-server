package com.juma.tgm.fms.dao.v3;

import com.juma.tgm.fms.domain.v3.ReconciliationUpdateVendorMq;
import com.juma.tgm.fms.domain.v3.ReconciliationUpdateVendorMqExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReconciliationUpdateVendorMqMapper {
    long countByExample(ReconciliationUpdateVendorMqExample example);

    int deleteByExample(ReconciliationUpdateVendorMqExample example);

    int deleteByPrimaryKey(Integer mqId);

    int insert(ReconciliationUpdateVendorMq record);

    int insertSelective(ReconciliationUpdateVendorMq record);

    List<ReconciliationUpdateVendorMq> selectByExampleWithBLOBs(ReconciliationUpdateVendorMqExample example);

    List<ReconciliationUpdateVendorMq> selectByExample(ReconciliationUpdateVendorMqExample example);

    ReconciliationUpdateVendorMq selectByPrimaryKey(Integer mqId);

    int updateByExampleSelective(@Param("record") ReconciliationUpdateVendorMq record, @Param("example") ReconciliationUpdateVendorMqExample example);

    int updateByExampleWithBLOBs(@Param("record") ReconciliationUpdateVendorMq record, @Param("example") ReconciliationUpdateVendorMqExample example);

    int updateByExample(@Param("record") ReconciliationUpdateVendorMq record, @Param("example") ReconciliationUpdateVendorMqExample example);

    int updateByPrimaryKeySelective(ReconciliationUpdateVendorMq record);

    int updateByPrimaryKeyWithBLOBs(ReconciliationUpdateVendorMq record);

    int updateByPrimaryKey(ReconciliationUpdateVendorMq record);

    int insertBatch(List<ReconciliationUpdateVendorMq> list);

    int updateBatchByPrimaryKeySelective(List<ReconciliationUpdateVendorMq> list);

    int updateBatch(List<ReconciliationUpdateVendorMq> list);
}