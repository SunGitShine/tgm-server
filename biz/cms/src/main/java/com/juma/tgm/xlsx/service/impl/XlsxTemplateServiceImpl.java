package com.juma.tgm.xlsx.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.juma.tgm.xlsx.dao.XlsxTemplateDao;
import com.juma.tgm.xlsx.dao.XlsxTitleFieldMappingDao;
import com.juma.tgm.xlsx.domain.XlsxTemplate;
import com.juma.tgm.xlsx.domain.XlsxTitleFieldMapping;
import com.juma.tgm.xlsx.service.XlsxTemplateService;

@Service
public class XlsxTemplateServiceImpl implements XlsxTemplateService {

    @Resource
    private XlsxTemplateDao xlsxTemplateDao;

    @Resource
    private XlsxTitleFieldMappingDao xlsxTitleFieldMappingDao;

    @Override
    public List<XlsxTemplate> findAllTemplate() {
        return xlsxTemplateDao.loadAll();
    }

    @Override
    public List<XlsxTitleFieldMapping> findTitleFieldMapping(Integer templateId) {
        if (templateId == null) return new ArrayList<XlsxTitleFieldMapping>();
        XlsxTitleFieldMapping example = new XlsxTitleFieldMapping();
        example.setTemplateId(templateId);
        return xlsxTitleFieldMappingDao.findByExample(example);
    }

    @Override
    public Map<String, String> transferToMapping(Integer templateId) throws BusinessException {
        List<XlsxTitleFieldMapping> rows = findTitleFieldMapping(templateId);
        if (rows.isEmpty()) {
            throw new BusinessException("excel.template.mapping.error.notExist", "excel.template.mapping.error.notExist");
        }
        Map<String, String> mapping = new HashMap<String, String>();
        for (XlsxTitleFieldMapping titleFieldMapping : rows) {
            mapping.put(titleFieldMapping.getTitle(), titleFieldMapping.getField());
        }
        return mapping;
    }

    @Override
    public void deleteMapping(Integer mappingId) {
        if (mappingId == null) return;
        XlsxTitleFieldMapping mapping = new XlsxTitleFieldMapping();
        mapping.setMappingId(mappingId);
        xlsxTitleFieldMappingDao.delete(mapping);
    }

    @Override
    public void saveTemplate(XlsxTemplate template) throws BusinessException {
        if(template == null) return;
        if(template.getTemplateId() == null) {
            xlsxTemplateDao.insert(template);
        } else {
            xlsxTemplateDao.update(template);
        }
    }

    @Override
    public void saveMapping(XlsxTitleFieldMapping mapping) throws BusinessException {
        if(mapping == null) return;
        if(mapping.getTemplateId() == null) {
            throw new BusinessException("errors.validation.failure", "errors.validation.failure");
        }
        if(mapping.getMappingId() == null) {
            xlsxTitleFieldMappingDao.insert(mapping);
        } else {
            xlsxTitleFieldMappingDao.update(mapping);
        }
    }

    @Override
    public void deleteTemplate(Integer templateId) {
        if(templateId == null) return;
        XlsxTitleFieldMapping example = new XlsxTitleFieldMapping();
        example.setTemplateId(templateId);
        xlsxTitleFieldMappingDao.delete(example);
        
        XlsxTemplate template = new XlsxTemplate();
        template.setTemplateId(templateId);
        xlsxTemplateDao.delete(template);
    }

    @Override
    public void batchSaveMapping(List<XlsxTitleFieldMapping> mappings) throws BusinessException {
        if(mappings == null || mappings.isEmpty()) return;
        XlsxTitleFieldMapping example = new XlsxTitleFieldMapping();
        example.setTemplateId(mappings.get(0).getTemplateId());
        xlsxTitleFieldMappingDao.delete(example);
        
        for(XlsxTitleFieldMapping mapping : mappings) {
            xlsxTitleFieldMappingDao.insert(mapping);
        }
    }

    @Override
    public XlsxTemplate getTemplate(Integer templateId) {
        return xlsxTemplateDao.get(templateId);
    }
}
