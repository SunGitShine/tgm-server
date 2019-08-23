package com.juma.tgm.id.service;

import com.juma.tgm.common.id.IdGeneratorTable;

public interface IdGeneratorService {

    String genId(IdGeneratorTable.IdType idType);
    
    
    String genProjectNo(IdGeneratorTable.IdType idType);


    String genAdjustFormMasterNo(IdGeneratorTable.IdType idType);

    String genTaskScheduledNo(IdGeneratorTable.IdType idType);

    /**
     * 
     * <p>Title: genProjectDailyId</p>  
     * <p>Description: 生成日报单id</p>  
     * @param idType RB
     * @return
     */
    String genProjectDailyId(IdGeneratorTable.IdType idType);
}
