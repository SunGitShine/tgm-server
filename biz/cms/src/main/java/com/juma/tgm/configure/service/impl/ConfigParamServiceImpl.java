package com.juma.tgm.configure.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.configure.dao.ConfigParamDao;
import com.juma.tgm.configure.domain.ConfigParam;
import com.juma.tgm.configure.service.ConfigParamOptionService;
import com.juma.tgm.configure.service.ConfigParamService;
import com.juma.tgm.id.dao.IdGeneratorMapper;

@Service
public class ConfigParamServiceImpl implements ConfigParamService {

    @Resource
    private ConfigParamDao configParamDao;
    @Resource
    private ConfigParamOptionService ConfParamOptionService;
    
    @Resource
    private IdGeneratorMapper idGeneratorMapper;

    @Override
    public Page<ConfigParam> search(PageCondition pageCondition) {
        int count = searchCount(pageCondition);
        List<ConfigParam> list = configParamDao.search(pageCondition);
        return new Page<ConfigParam>(pageCondition.getPageNo(), pageCondition.getPageSize(), count, list);
    }

    @Override
    public int searchCount(PageCondition pageCondition) {
        return configParamDao.searchCount(pageCondition);
    }

    @Override
    public List<ConfigParam> loadAll() {
        return configParamDao.loadAll();
    }

    @Override
    public ConfigParam get(Integer paramId) {
        return configParamDao.get(paramId);
    }

    @Override
    public void insert(ConfigParam confParam) throws BusinessException {
        configParamDao.insert(confParam);
    }

    @Override
    public void update(ConfigParam confParam) throws BusinessException {
        configParamDao.update(confParam);
    }

    @Override
    public void delete(ConfigParam confParam) throws BusinessException {
        configParamDao.delete(confParam);
        ConfParamOptionService.deleteByParamId(confParam.getParamId(), null);
    }

    @Override
    public ConfigParam findByParamKey(String paramKey, LoginUser loginUser) {
        if (StringUtils.isBlank(paramKey) || null == loginUser || null == loginUser.getTenantId()) {
            return null;
        }

        ConfigParam example = new ConfigParam();
        example.setParamKey(paramKey);
        example.setTenantId(loginUser.getTenantId());
        List<ConfigParam> list = configParamDao.findByExample(example);

        if (list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    @Override
    public List<ConfigParam> listByTenantId(int tenantId) {
        ConfigParam example = new ConfigParam();
        example.setTenantId(tenantId);
        return configParamDao.findByExample(example);
    }
    
    @Override
    public Integer excuteSQL(String sql) {
        return idGeneratorMapper.excuteSQL(sql);
    }
}
