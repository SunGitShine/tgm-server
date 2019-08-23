package com.juma.tgm.xlsx.service;

import java.util.List;
import java.util.Map;

import com.giants.common.exception.BusinessException;
import com.juma.tgm.xlsx.domain.XlsxTemplate;
import com.juma.tgm.xlsx.domain.XlsxTitleFieldMapping;

public interface XlsxTemplateService {

    List<XlsxTemplate> findAllTemplate();
    
    XlsxTemplate getTemplate(Integer templateId);
    
    List<XlsxTitleFieldMapping> findTitleFieldMapping(Integer templateId);
    
    Map<String,String> transferToMapping(Integer templateId) throws BusinessException;
    
    void saveTemplate(XlsxTemplate template) throws BusinessException;
    
    void saveMapping(XlsxTitleFieldMapping mapping) throws BusinessException;
    
    void batchSaveMapping(List<XlsxTitleFieldMapping> mappings) throws BusinessException;
    
    void deleteMapping(Integer mappingId);
    
    void deleteTemplate(Integer templateId);
    
}
