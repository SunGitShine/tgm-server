package com.juma.tgm.fms.dao.v3.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.juma.tgm.fms.domain.v3.vo.ReconcilicationForReceivableItemVo;

public interface ReconcilicationForReceivableItemExtraMapper {

    void batchInsert(@Param("items") List<ReconcilicationForReceivableItemVo> items);
}