package com.juma.tgm.configure.dao;

import com.juma.tgm.configure.domain.TenantFreightFactor;
import com.juma.tgm.configure.domain.TenantFreightFactorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TenantFreightFactorMapper {
    long countByExample(TenantFreightFactorExample example);

    int deleteByExample(TenantFreightFactorExample example);

    int deleteByPrimaryKey(Integer tenantFreightFactorId);

    int insert(TenantFreightFactor record);

    int insertSelective(TenantFreightFactor record);

    List<TenantFreightFactor> selectByExample(TenantFreightFactorExample example);

    TenantFreightFactor selectByPrimaryKey(Integer tenantFreightFactorId);

    int updateByExampleSelective(@Param("record") TenantFreightFactor record, @Param("example") TenantFreightFactorExample example);

    int updateByExample(@Param("record") TenantFreightFactor record, @Param("example") TenantFreightFactorExample example);

    int updateByPrimaryKeySelective(TenantFreightFactor record);

    int updateByPrimaryKey(TenantFreightFactor record);
}