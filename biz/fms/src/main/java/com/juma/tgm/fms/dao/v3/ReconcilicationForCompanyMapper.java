package com.juma.tgm.fms.dao.v3;

import com.juma.tgm.fms.domain.v3.ReconcilicationForCompany;
import com.juma.tgm.fms.domain.v3.ReconcilicationForCompanyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.generator.my.mapper.Mapper;

public interface ReconcilicationForCompanyMapper extends Mapper {
    long countByExample(ReconcilicationForCompanyExample example);

    int deleteByExample(ReconcilicationForCompanyExample example);

    int deleteByPrimaryKey(Integer reconcilicationCompanyId);

    int insert(ReconcilicationForCompany record);

    int insertSelective(ReconcilicationForCompany record);

    List<ReconcilicationForCompany> selectByExample(ReconcilicationForCompanyExample example);

    ReconcilicationForCompany selectByPrimaryKey(Integer reconcilicationCompanyId);

    int updateByExampleSelective(@Param("record") ReconcilicationForCompany record, @Param("example") ReconcilicationForCompanyExample example);

    int updateByExample(@Param("record") ReconcilicationForCompany record, @Param("example") ReconcilicationForCompanyExample example);

    int updateByPrimaryKeySelective(ReconcilicationForCompany record);

    int updateByPrimaryKey(ReconcilicationForCompany record);
}