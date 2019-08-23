package com.juma.tgm.tools.service.impl;

import com.giants.common.exception.BusinessException;
import com.giants.common.exception.NotLoggedInException;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.conf.service.BusinessAreaService;
import com.juma.auth.employee.domain.Department;
import com.juma.auth.employee.domain.DepartmentCompany;
import com.juma.auth.employee.domain.ECompany;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.DepartmentService;
import com.juma.auth.employee.service.ECompanyService;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.EcoUser;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.EcoUserService;
import com.juma.auth.user.service.UserService;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.service.ConfParamService;
import com.juma.tgm.common.Constants;
import com.juma.tgm.tools.service.AuthCommonService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthCommonServiceImpl implements AuthCommonService {

    private final static Logger logger = LoggerFactory.getLogger(AuthCommonServiceImpl.class);

    @Autowired
    private ConfParamService confParamService;
    @Autowired
    private UserService userService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private EcoUserService ecoUserService;
    @Resource
    private BusinessAreaService businessAreaService;
    @Resource
    private ECompanyService eCompanyService;

    @Override
    public User loadUser(Integer userId) {
        if (null == userId) {
            return null;
        }

        User user = null;
        try {
            user = userService.loadUser(userId);
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                throw e;
            }
            throw new BusinessException("callAuthSystemException", "errors.callAuthSystemException");
        }
        return user;
    }

    @Override
    public User loadUserByEmployeeId(Integer employeeId, LoginUser loginUser) throws BusinessException {
        User user = null;
        try {
            user = employeeService.loadUserByEmployeeId(employeeId, loginUser);
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                throw e;
            }
            throw new BusinessException("callAuthSystemException", "errors.callAuthSystemException");
        }
        return user;
    }

    @Override
    public EcoUser loadEcoUserByUserId(Integer userId, LoginUser loginUser) throws BusinessException {
        if (null == userId) {
            return null;
        }

        return ecoUserService.loadEcoUserByUserId(userId, loginUser);
    }

    @Override
    public List<ConfParamOption> listOption(String optionKey) {
        if(optionKey == null) {
            return new ArrayList<>();
        }
        List<ConfParamOption> paramOptions = null;
        try {
            paramOptions = confParamService.findParamOptions(optionKey);
        } catch (Exception e) {
            logger.warn("查询数据字典错误，optionKey = " + optionKey);
            if (e instanceof BusinessException) {
                throw e;
            }
            throw new BusinessException("callAuthSystemException", "errors.callAuthSystemException");
        }
        return paramOptions == null ? new ArrayList<ConfParamOption>() : paramOptions;
    }

    @Override
    public String getOptionName(String optionKey, String optionValue) {
        if(optionKey == null || optionValue == null) {
            return null;
        }
        ConfParamOption option = null;
        try {
            option = confParamService.findParamOption(optionKey, optionValue);
        } catch (Exception e) {
            logger.warn("查询数据字典错误，optionKey = " + optionKey + ", optionValue = " + optionValue);
            if (e instanceof BusinessException) {
                throw e;
            }
            throw new BusinessException("callAuthSystemException", "errors.callAuthSystemException");
        }
        return option == null ? null : option.getOptionName();
    }

    @Override
    public String getEntryLicenseCnName(Integer entryLicense) {
        if (null == entryLicense) {
            return "无";
        }

        ConfParamOption option = confParamService.findParamOption(Constants.ENTRY_CITY_LICENSE_TYPE,
                entryLicense.toString());
        return (option == null ? "无" : option.getOptionName());
    }

    @Override
    public boolean isPermission(String permissionKey, LoginEmployee loginEmployee) throws NotLoggedInException {
        if (StringUtils.isBlank(permissionKey) || null == loginEmployee) {
            return false;
        }

        return employeeService.isPermission(Constants.AUTH_KEY_TGM_MANAGE, permissionKey, loginEmployee);
    }

    @Override
    public String getOptionValue(String optionKey, String optionName, String defaultValue) {
        if (StringUtils.isBlank(optionKey)) {
            return null;
        }

        List<ConfParamOption> options = this.listOption(optionKey);
        if (options.isEmpty()) {
            return null;
        }

        if (StringUtils.isBlank(optionName)) {
            return options.get(0).getOptionValue() == null ? defaultValue : options.get(0).getOptionValue();
        }

        // 取值指定optionName的value
        for (ConfParamOption option : options) {
            if (StringUtils.isBlank(option.getOptionValue())) {
                continue;
            }

            if (optionName.equals(option.getOptionName())) {
                return option.getOptionValue() == null ? defaultValue : option.getOptionValue();
            }
        }

        return null;

    }

    @Override
    public BusinessArea loadBusinessArea(String areaCode, LoginUser loginUser) throws BusinessException {
        return businessAreaService.loadBusinessArea(areaCode,loginUser);
    }


    @Override
    public Department loadDepartment(Integer departmentId) throws BusinessException {
        return departmentService.loadDepartment(departmentId);
    }

    @Override
    public DepartmentCompany findDepartmentCompanyByDepartmentId(Integer departmentId) {
        if (null == departmentId) {
            return null;
        }

        return eCompanyService.findOperationDCompanyByDepartment(departmentId);
    }

    @Override
    public ECompany loadCreditCode(String uniformSocialCreditCode) {
        if (StringUtils.isBlank(uniformSocialCreditCode)) {
            return null;
        }

        return eCompanyService.getByUniformSocialCreditCode(uniformSocialCreditCode);
    }
}
