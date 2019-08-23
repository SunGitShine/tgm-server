package com.juma.tgm.id.dao;

public interface IdGeneratorMapper {

    Integer genId(String tableName);
    
    Integer excuteSQL(String sql);
    
}
