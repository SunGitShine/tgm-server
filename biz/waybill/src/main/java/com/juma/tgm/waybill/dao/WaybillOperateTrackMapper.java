package com.juma.tgm.waybill.dao;

import com.juma.tgm.waybill.domain.WaybillOperateTrack;
import com.juma.tgm.waybill.domain.WaybillOperateTrackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.generator.my.mapper.Mapper;

public interface WaybillOperateTrackMapper extends Mapper {
    long countByExample(WaybillOperateTrackExample example);

    int deleteByExample(WaybillOperateTrackExample example);

    int deleteByPrimaryKey(Integer trackId);

    int insert(WaybillOperateTrack record);

    int insertSelective(WaybillOperateTrack record);

    List<WaybillOperateTrack> selectByExample(WaybillOperateTrackExample example);

    WaybillOperateTrack selectByPrimaryKey(Integer trackId);

    int updateByExampleSelective(@Param("record") WaybillOperateTrack record, @Param("example") WaybillOperateTrackExample example);

    int updateByExample(@Param("record") WaybillOperateTrack record, @Param("example") WaybillOperateTrackExample example);

    int updateByPrimaryKeySelective(WaybillOperateTrack record);

    int updateByPrimaryKey(WaybillOperateTrack record);
}