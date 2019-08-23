package com.juma.tgm.tools.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.exception.NotLoggedInException;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.employee.domain.Department;
import com.juma.auth.employee.domain.DepartmentCompany;
import com.juma.auth.employee.domain.ECompany;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.EcoUser;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.conf.domain.ConfParamOption;

import java.util.List;

public interface AuthCommonService {

    /**
     * 根据用户ID返回帐号基本信息
     */
    User loadUser(Integer userId);

    /**
     * 根据员工ID获取用户信息
     *
     * @param employeeId
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    User loadUserByEmployeeId(Integer employeeId, LoginUser loginUser) throws BusinessException;

    /**
     * 根据UserID获取用户信息
     *
     * @param userId
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    EcoUser loadEcoUserByUserId(Integer userId, LoginUser loginUser) throws BusinessException;

    /**
     * 查询所有数据字典项
     */
    List<ConfParamOption> listOption(String optionKey);

    /**
     * 查询数据字典项名称
     */
    String getOptionName(String optionKey, String optionValue);

    /**
     * 获取车辆入城证中文名
     *
     * @param entryLicense
     * @return
     * @author Libin.Wei
     * @Date 2018年1月24日 下午3:52:36
     */
    String getEntryLicenseCnName(Integer entryLicense);

    /**
     * 检查当前登录人有没有指定的数据权限,返回boolean值
     *
     * @param permissionKey
     * @param loginEmployee
     * @return
     * @throws NotLoggedInException
     */
    boolean isPermission(String permissionKey, LoginEmployee loginEmployee) throws NotLoggedInException;

    /**
     * 获取参数配置的value
     *
     * @param optionKey  必填，参数配置key
     * @param optionName 没有特殊要求传null,为空返回第一条数据
     * @param defaultValue 没有特殊要求传null
     *
     * @return
     */
    String getOptionValue(String optionKey, String optionName, String defaultValue) throws BusinessException;

    /**
     * 根据业务编码查询业务区域
     * @param areaCode 业务区域编码
     * @param loginUser  登陆用户
     * @return
     */
    BusinessArea loadBusinessArea(String areaCode, LoginUser loginUser);

    /**
     * 根据部门ID,查询部门
     * @param departmentId
     * @return
     * @throws BusinessException
     */
    Department loadDepartment(Integer departmentId) throws BusinessException;

    /**
     * 根据部门ID获取公司信息
     *
     * @param departmentId
     * @return
     */
    DepartmentCompany findDepartmentCompanyByDepartmentId(Integer departmentId);

    /**
     * 根据社会统一信用代码获取公司信息
     *
     * @param uniformSocialCreditCode
     * @return
     */
    ECompany loadCreditCode(String uniformSocialCreditCode);
}
