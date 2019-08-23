package com.juma.tgm.tools.service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.crm.contract.domain.Contract;
import com.juma.crm.support.domain.CustomerInfoBo;
import com.juma.tgm.project.common.CustomerForProductAndDept;
import com.juma.tgm.project.common.LogisticsProduct;
import com.juma.tgm.project.vo.ContractFilter;
import com.juma.tgm.project.vo.ContractVo;

import java.util.List;
import java.util.Map;

public interface CrmCommonService {

    /**
     * 判断当前租户是否要展示物流标签
     *
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    @Deprecated
    boolean isShowLogisticsProduct(LoginUser loginUser) throws BusinessException;

    /**
     * 获取租户下所有的物流标签
     *
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    @Deprecated
    List<LogisticsProduct> listLogisticsProduct(LoginUser loginUser) throws BusinessException;

    /**
     * 获取当前客户下的物流标签
     *
     * @param customerId tgm的客户ID
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    @Deprecated
    List<LogisticsProduct> listLogisticsProductByCustomerId(Integer customerId, LoginUser loginUser) throws BusinessException;

    /**
     * 获取客户下的子公司与物流标签信息
     *
     * @param customerId tgm的客户ID
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    @Deprecated
    CustomerForProductAndDept loadCustomerForProductAndDeptByCustomerId(Integer customerId, LoginUser loginUser) throws BusinessException;

    /**
     * 获取客户下的子公司与物流标签信息
     *
     * @param crmCustomerId crm的客户ID
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    CustomerForProductAndDept loadCustomerForProductAndDeptByCrmCustomerId(Integer crmCustomerId,
        LoginUser loginUser) throws BusinessException;

    /**
     * 取租户下所有的物流标签,MAP返回
     *
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    @Deprecated
    Map<String, String> mapLogisticsProduct(LoginUser loginUser) throws BusinessException;

    /**
     * 根据子公司名称获取子公司信息
     * @param subCompanyName 子公司名称
     * @param callbackPageSize 返回条数，默认15，最大200
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    @Deprecated
    List<CustomerForProductAndDept> listSubCompanyLikeName(String subCompanyName, Integer callbackPageSize,
        LoginUser loginUser) throws BusinessException;

    /**
     * 根据TMS的客户ID获取合同信息列表
     *
     * @param contractFilter
     *         查询条件
     * @param loginUser
     *         当前登录人信息
     * @return
     * @throws BusinessException
     */
    List<ContractVo> listContractByContractFilter(ContractFilter contractFilter, LoginUser loginUser) throws BusinessException;

    /**
     * 根据合同no获取crm合同信息
     *
     * @return
     * @throws BusinessException
     */
    Contract getByContractNo(String contractNo, LoginUser loginUser) throws BusinessException;

    /**
     * 根据合同no获取crm合同信息,并转为tms的数据结构
     *
     * @param contractNo
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    ContractVo loadByContractNo(String contractNo, LoginUser loginUser) throws BusinessException;

    /**
     * 根据crm客户ID和租户,获取客户信息
     */
    CustomerInfoBo findByCrmCustomerId(Integer crmCustomerId) throws BusinessException;
}
