package com.juma.tgm.tools.service.impl;

import com.alibaba.fastjson.JSON;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageQueryCondition;
import com.juma.auth.employee.domain.DepartmentCompany;
import com.juma.auth.user.domain.LoginUser;
import com.juma.crm.contract.domain.ConsignorVo;
import com.juma.crm.contract.domain.Contract;
import com.juma.crm.contract.domain.ContractFilters;
import com.juma.crm.contract.domain.ContractStatusEnum;
import com.juma.crm.contract.domain.ContractTypeEnum;
import com.juma.crm.customer.domain.Customer4TmsBo;
import com.juma.crm.customer.domain.DepartmentCompanyInfo;
import com.juma.crm.customer.domain.ProductsLableInfo;
import com.juma.crm.support.domain.CustomerInfoBo;
import com.juma.crm.support.domain.CustomerInfoFilters;
import com.juma.crm.support.domain.DepartmentCompanyFilter;
import com.juma.crm.support.service.Crm4TmsService;
import com.juma.crm.support.service.CrmService;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.project.common.CustomerForProductAndDept;
import com.juma.tgm.project.common.LogisticsProduct;
import com.juma.tgm.project.vo.ContractFilter;
import com.juma.tgm.project.vo.ContractVo;
import com.juma.tgm.tools.service.AuthCommonService;
import com.juma.tgm.tools.service.CrmCommonService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;

@Service
public class CrmCommonServiceImpl implements CrmCommonService {

    @Resource
    private CrmService crmService;
    @Resource
    private Crm4TmsService crm4TmsService;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private AuthCommonService authCommonService;

    @Override
    public boolean isShowLogisticsProduct(LoginUser loginUser) throws BusinessException {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return false;
        }
        return crm4TmsService.isShowConfigure(ProductsLableInfo.configureEnum.LOGISTICS_PRODUCTS.getCode(), loginUser);
    }

    @Override
    public List<LogisticsProduct> listLogisticsProduct(LoginUser loginUser) throws BusinessException {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new ArrayList<>();
        }

        Customer4TmsBo customer4TmsBo = null;
        try {
            customer4TmsBo = crm4TmsService.findProductAndDepId(null, loginUser);
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                throw e;
            }

            throw new BusinessException("callCrmSystemException", "errors.callCrmSystemException");
        }

        if (null == customer4TmsBo) {
            return new ArrayList<>();
        }

        List<LogisticsProduct> listLogisticsProduct = new ArrayList<>();
        if (null == customer4TmsBo.getLogisticsProducts() || customer4TmsBo.getLogisticsProducts().isEmpty()) {
            return listLogisticsProduct;
        }

        Map<String, Object> map = customer4TmsBo.getLogisticsProducts();
        for (String key : map.keySet()) {
            LogisticsProduct p = new LogisticsProduct();
            p.setCode(key);
            p.setName(map.get(key) + "");
            listLogisticsProduct.add(p);
        }

        LogisticsProduct p = new LogisticsProduct();
        p.setCode(LogisticsProduct.NO_LOGISTICS_LABEL);
        p.setName(LogisticsProduct.NO_LOGISTICS_LABEL_NAME);
        listLogisticsProduct.add(0, p);

        return listLogisticsProduct;
    }

    @Override
    public List<LogisticsProduct> listLogisticsProductByCustomerId(Integer customerId, LoginUser loginUser) throws BusinessException {
        CustomerForProductAndDept productAndDept = this.loadCustomerForProductAndDeptByCustomerId(customerId, loginUser);
        if (null == productAndDept) {
            return new ArrayList<>();
        }

        return productAndDept.getListLogisticsProduct();
    }

    @Override
    public CustomerForProductAndDept loadCustomerForProductAndDeptByCustomerId(Integer customerId, LoginUser loginUser) throws BusinessException {
        if (null == customerId || null == loginUser || null == loginUser.getTenantId()) {
            return null;
        }

        CustomerInfo customerInfo = customerInfoService.findCusInfoById(customerId);
        if (null == customerInfo) {
            return null;
        }

        return this.loadCustomerForProductAndDeptByCrmCustomerId(customerInfo.getCrmCustomerId(), loginUser);
    }

    @Override
    public CustomerForProductAndDept loadCustomerForProductAndDeptByCrmCustomerId(Integer crmCustomerId, LoginUser loginUser) throws BusinessException {
        if (null == crmCustomerId || null == loginUser || null == loginUser.getTenantId()) {
            return null;
        }

        Customer4TmsBo customer4TmsBo = null;

        try {
            customer4TmsBo = crm4TmsService.findProductAndDepId(crmCustomerId, loginUser);
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                throw e;
            }

            throw new BusinessException("callCrmSystemException", "errors.callCrmSystemException");
        }

        if (null == customer4TmsBo) {
            return null;
        }

        CustomerForProductAndDept cf = new CustomerForProductAndDept();
        cf.setDeparmentId(customer4TmsBo.getDepartmentId());
        if (null != customer4TmsBo.getDepartmentId()) {
            DepartmentCompany departmentCompany =
                authCommonService.findDepartmentCompanyByDepartmentId(customer4TmsBo.getDepartmentId());
            cf.setCompanyCreditCode(departmentCompany == null ? "" : departmentCompany.getUniformSocialCreditCode());
        }

        if (null == customer4TmsBo.getLogisticsProducts() || customer4TmsBo.getLogisticsProducts().isEmpty()) {
            return cf;
        }

        Map<String, Object> map = customer4TmsBo.getLogisticsProducts();
        for (String key : map.keySet()) {
            LogisticsProduct p = new LogisticsProduct();
            p.setCode(key);
            p.setName(map.get(key) + "");
            cf.getListLogisticsProduct().add(p);
        }

        return cf;
    }

    @Override
    public Map<String, String> mapLogisticsProduct(LoginUser loginUser) throws BusinessException {
        Map<String, String> map = new HashMap<>();
        List<LogisticsProduct> logisticsProducts = this.listLogisticsProduct(loginUser);
        for (LogisticsProduct p : logisticsProducts) {
            map.put(p.getCode(), p.getName());
        }
        return map;
    }

    @Override
    public List<CustomerForProductAndDept> listSubCompanyLikeName(String subCompanyName, Integer callbackPageSize,
        LoginUser loginUser) throws BusinessException {
        List<CustomerForProductAndDept> result = new ArrayList<>();
        if (null == loginUser || null == loginUser.getTenantId()) {
            return result;
        }
        callbackPageSize = callbackPageSize == null ? 15 : (NumberUtils.compare(callbackPageSize, 200) == 1 ? 200 :
            callbackPageSize);

        DepartmentCompanyFilter filter = new DepartmentCompanyFilter();
        filter.setTenantId(loginUser.getTenantId());
        filter.setSubCompanyName(subCompanyName);
        PageQueryCondition<DepartmentCompanyFilter> cond = new PageQueryCondition<>(filter);
        cond.setPageNo(1);
        cond.setPageSize(callbackPageSize);
        Page<DepartmentCompanyInfo> page = null;

        try {
            page = crm4TmsService.search(cond);
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                throw e;
            }

            throw new BusinessException("callCrmSystemException", "errors.callCrmSystemException");
        }
        if (null == page || CollectionUtils.isEmpty(page.getResults())) {
            return result;
        }

        for (DepartmentCompanyInfo info : page.getResults()) {
            CustomerForProductAndDept dept = new CustomerForProductAndDept();
            dept.setDeparmentId(info.getDepartmentId());
            dept.setBusinessLicenceName(info.getSubCompanyName());
            result.add(dept);
        }

        return result;
    }

    @Override
    public List<ContractVo> listContractByContractFilter(ContractFilter contractFilter, LoginUser loginUser) throws BusinessException {
        List<ContractVo> result = new ArrayList<>();
        if (null == contractFilter || null == contractFilter.getCustomerId()) {
            return result;
        }

        CustomerInfo customerInfo = customerInfoService.findCusInfoById(contractFilter.getCustomerId());

        if (null == customerInfo || null == customerInfo.getCrmCustomerId()) {
            return result;
        }

        ContractFilters filter = new ContractFilters();
        filter.setCustomerId(customerInfo.getCrmCustomerId());
        filter.setInvalidTime(new Date());
        filter.setContractType(ContractTypeEnum.CONSIGNOR.getCode());
        filter.setContractStatusList(Arrays.asList(new Byte[]{ContractStatusEnum.SIGNED.getCode()}));
        PageQueryCondition<ContractFilters> condition = new PageQueryCondition<>(filter);
        condition.setPageNo(1);
        condition.setPageSize(Integer.MAX_VALUE);
        Page<Contract> contracts = getContract(condition);

        if (null == contracts) {
            return result;
        }

        List<Contract> list = (List<Contract>) contracts.getResults();

        for (Contract detail : list) {
            //组装结果集
            ContractVo vo = buildContractVo(detail);
            result.add(vo);
        }
        return result;
    }

    private ContractVo buildContractVo(Contract contract) {
        if (null == contract) {
            return null;
        }

        ContractVo vo = new ContractVo();
        vo.setContractId(contract.getContractId());
        vo.setContractNo(contract.getContractNumber());
        vo.setContractName(contract.getContractName());
        vo.setContractStartDate(contract.getEffectiveTime());
        vo.setContractEndDate(contract.getInvalidTime());
        //已归档附件
        vo.setContractEnclosureUrl(contract.getArchiveContent());
        ConsignorVo consignorVo = JSON.parseObject(contract.getContent(), ConsignorVo.class);
        if (null == consignorVo) {
            return vo;
        }

        // 签约主体
        vo.setContractToCompany(consignorVo.getSignPartDepartmentId());
        vo.setContractToCompanyCreditCode(
            null == consignorVo.getSignPartCreditCode() ? "" : consignorVo.getSignPartCreditCode());
        if (null != vo.getContractToCompany()) {
            DepartmentCompany department = authCommonService
                .findDepartmentCompanyByDepartmentId(vo.getContractToCompany());
            vo.setContractToCompanyName(department == null ? null : department.getCompanyName());
        }

        // 运作主体信用代码：如果运作方为空，赋值为签约方
        String operatePartCreditCode = consignorVo.getOperatePartCreditCode();
        if (StringUtils.isBlank(operatePartCreditCode)) {
            vo.setPayToCompany(vo.getContractToCompany());
            vo.setPayToCompanyCreditCode(vo.getContractToCompanyCreditCode());
            vo.setPayToCompanyName(vo.getContractToCompanyName());
            return vo;
        }

        vo.setPayToCompany(consignorVo.getOperatePartDepartmentId());
        vo.setPayToCompanyCreditCode(operatePartCreditCode == null ? "" : operatePartCreditCode);
        DepartmentCompany department = authCommonService
            .findDepartmentCompanyByDepartmentId(vo.getPayToCompany());
        vo.setPayToCompanyName(department == null ? null : department.getCompanyName());
        return vo;
    }

    @Override
    public Contract getByContractNo(String contractNo, LoginUser loginUser) throws BusinessException {
        ContractFilters filter = new ContractFilters();
        filter.setContractNumber(contractNo);
        PageQueryCondition<ContractFilters> condition = new PageQueryCondition<>(filter);
        Page<Contract> contracts = getContract(condition);

        if (null == contracts) {
            return null;
        }

        List<Contract> list = (List<Contract>) contracts.getResults();

        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    @Override
    public ContractVo loadByContractNo(String contractNo, LoginUser loginUser) throws BusinessException {
        if (null == contractNo || null == loginUser) {
            return null;
        }

        return buildContractVo(this.getByContractNo(contractNo, loginUser));
    }

    private Page<Contract> getContract(PageQueryCondition<ContractFilters> condition) {
        // 获取合同信息
        Page<Contract> contracts = null;
        try {
            contracts = crm4TmsService.findContractByFilters(condition);
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                throw e;
            }
            throw new BusinessException("callCrmSystemException", "errors.callCrmSystemException");
        }
        return contracts;
    }

    @Override
    public CustomerInfoBo findByCrmCustomerId(Integer crmCustomerId){
        if( null == crmCustomerId ){ return null; }
        CustomerInfoFilters filters = new CustomerInfoFilters();
        filters.setCustomerId(String.valueOf(crmCustomerId));
        PageQueryCondition<CustomerInfoFilters> customerParams = new PageQueryCondition<>(filters,1,10);
        Page<CustomerInfoBo> customerInfos = crmService.findCustomerInfoBo(customerParams);

        if( null == customerInfos ){
            return null;
        }

        if( CollectionUtils.isEmpty(customerInfos.getResults()) ){
            return null;
        }

        return customerInfos.getResults().iterator().next();
    }

}
