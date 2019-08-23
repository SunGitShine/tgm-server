package com.juma.tgm.configure.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.Employee;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.tgm.configure.dao.ConfigParamOptionDao;
import com.juma.tgm.configure.domain.ConfigParam;
import com.juma.tgm.configure.domain.ConfigParam.ParamKey;
import com.juma.tgm.configure.domain.ConfigParamOption;
import com.juma.tgm.configure.service.ConfigParamOptionService;
import com.juma.tgm.configure.service.ConfigParamService;

@Service
public class ConfigParamOptionServiceImpl implements ConfigParamOptionService {

    private final Logger log = LoggerFactory.getLogger(ConfigParamOptionServiceImpl.class);
    @Resource
    private ConfigParamOptionDao configParamOptionDao;
    @Resource
    private UserService userService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private ConfigParamService configParamService;

    @Override
    public Page<ConfigParamOption> search(PageCondition pageCondition, LoginUser loginUser) {
        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());
        int count = configParamOptionDao.searchCount(pageCondition);
        List<ConfigParamOption> result = configParamOptionDao.search(pageCondition);
        for (ConfigParamOption configParamOption : result) {
            try {
                if (null == configParamOption.getUserId()) {
                    continue;
                }
                User user = userService.loadUser(configParamOption.getUserId());
                configParamOption.setUserName(user.getName());
                configParamOption.setLoginName(user.getLoginName());
            } catch (Exception e) {
            }
        }
        return new Page<ConfigParamOption>(pageCondition.getPageNo(), pageCondition.getPageSize(), count, result);
    }

    @Override
    public List<ConfigParamOption> loadAll() {
        return configParamOptionDao.loadAll();
    }

    @Override
    public ConfigParamOption getConfigParamOption(Integer optionId) {
        return configParamOptionDao.get(optionId);
    }

    @Override
    public List<ConfigParamOption> findByParamId(Integer paramId, LoginUser loginUser) {
        if (null == paramId) {
            return new ArrayList<ConfigParamOption>();
        }

        ConfigParamOption example = new ConfigParamOption();
        example.setTenantId(loginUser.getTenantId());
        example.setParamId(paramId);
        return configParamOptionDao.findByParamId(example);
    }

    @Override
    public void insert(ConfigParamOption confParamOption, LoginEmployee loginEmployee) throws BusinessException {
        Employee employee = employeeService.findEmployee(confParamOption.getEmployeeId(), loginEmployee);
        confParamOption.setUserId(employee.getUserId());

        confParamOption.setTenantId(loginEmployee.getTenantId());
        confParamOption.setTenantCode(loginEmployee.getTenantCode());
        check(confParamOption);
        confParamOption.setTenantCode(loginEmployee.getTenantCode());
        confParamOption.setCreateUserId(loginEmployee.getUserId());
        configParamOptionDao.insert(confParamOption);
    }

    @Override
    public void update(ConfigParamOption confParamOption, LoginEmployee loginEmployee) throws BusinessException {
        Employee employee = employeeService.findEmployee(confParamOption.getEmployeeId(), loginEmployee);
        confParamOption.setUserId(employee.getUserId());
        check(confParamOption);
        confParamOption.setTenantCode(loginEmployee.getTenantCode());
        confParamOption.setLastUpdateUserId(loginEmployee.getUserId());
        configParamOptionDao.update(confParamOption);
    }

    @Override
    public void delete(ConfigParamOption confParamOption, LoginEmployee loginEmployee) throws BusinessException {
        configParamOptionDao.delete(confParamOption);
    }

    @Override
    public void deleteByParamId(Integer paramId, LoginEmployee loginEmployee) throws BusinessException {
        configParamOptionDao.deleteByParamId(paramId);
    }

    @Override
    public void enable(Integer optionId, LoginEmployee loginEmployee) throws BusinessException {
        ConfigParamOption configParamOption = configParamOptionDao.get(optionId);
        if (null != configParamOption) {
            configParamOption.setIsDelete(false);
            configParamOptionDao.updateByUserId(configParamOption);
        }
    }

    @Override
    public void disable(Integer optionId, LoginEmployee loginEmployee) throws BusinessException {
        ConfigParamOption configParamOption = configParamOptionDao.get(optionId);
        if (null != configParamOption) {
            configParamOption.setIsDelete(true);
            configParamOptionDao.updateByUserId(configParamOption);
        }
    }

    @Override
    public List<ConfigParamOption> listPushParamOptionBy(String areaCode, ParamKey paramKey, LoginUser loginUser) {
        if (StringUtils.isBlank(areaCode)) {
            log.info("areaCodeList is null...");
            return new ArrayList<ConfigParamOption>();
        }
        if (null == loginUser || null == loginUser.getTenantId()) {
            log.info("tenantId is null...");
            return new ArrayList<ConfigParamOption>();
        }
        if (null == paramKey) {
            log.info("paramKey is null...");
            return new ArrayList<ConfigParamOption>();
        }
        
        ConfigParam configParam = configParamService.findByParamKey(paramKey.toString(), loginUser);
        if (null == configParam) {
            log.info("configParam is null...");
            return new ArrayList<ConfigParamOption>();
        }

        ConfigParamOption example = new ConfigParamOption();
        example.setAreaCode(areaCode);
        example.setTenantId(loginUser.getTenantId());
        example.setParamId(configParam.getParamId());
        example.setIsDelete(false);
        return configParamOptionDao.findByExample(example);
    }

    // 验证
    private void check(ConfigParamOption confParamOption) {
        ConfigParamOption option = new ConfigParamOption();
        Integer optionId = confParamOption.getOptionId();
        option.setEmployeeId(confParamOption.getEmployeeId());
        option.setParamId(confParamOption.getParamId());
        option.setTenantId(confParamOption.getTenantId());
        List<ConfigParamOption> list = configParamOptionDao.findByExample(option);
        if (CollectionUtils.isNotEmpty(list)) {
            User loadUser = userService.loadUser(confParamOption.getUserId());
            if (null == optionId) {
                throw new BusinessException("exsitErr", "errors.exsitErr", ("用户[" + loadUser.getName() + "]"));
            } else {
                for (ConfigParamOption configParamOption : list) {
                    if (!optionId.equals(configParamOption.getOptionId())) {
                        throw new BusinessException("exsitErr", "errors.exsitErr", ("用户[" + loadUser.getName() + "]"));
                    }
                }
            }
        }

        if (StringUtils.isBlank(confParamOption.getAreaCode())) {
            throw new BusinessException("areaCodeMustWrite", "confParamOption.errors.areaCodeMustWrite");
        }
    }
}
