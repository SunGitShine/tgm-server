package com.juma.tgm.vendor.service.impl;

import com.giants.common.beanutils.BeanUtils;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.Department;
import com.juma.auth.employee.service.DepartmentService;
import com.juma.auth.tenant.domain.Tenant;
import com.juma.auth.tenant.service.TenantService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.authority.service.TgmUserCenterService;
import com.juma.tgm.base.domain.Paging;
import com.juma.tgm.common.Constants;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.customer.domain.TruckCustomer;
import com.juma.tgm.project.domain.Project;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.vendor.dao.VendorMappingMapper;
import com.juma.tgm.vendor.dao.VendorProjectMappingMapper;
import com.juma.tgm.vendor.domain.*;
import com.juma.tgm.vendor.domain.VendorMappingExample.Criteria;
import com.juma.tgm.vendor.service.VendorMappingService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
public class VendorMappingServiceImpl implements VendorMappingService {

    private final Logger log = LoggerFactory.getLogger(VendorMappingServiceImpl.class);

    @Resource
    private VendorMappingMapper vendorMappingMapper;
    @Resource
    private VendorProjectMappingMapper vendorProjectMappingMapper;
    @Resource
    private TenantService tenantService;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private TgmUserCenterService tgmUserCenterService;
    @Resource
    private ProjectService projectService;
    @Resource
    private DepartmentService departmentService;

    @Override
    public List<VendorMapping> listVendorMapping(String vendorName, Integer backMaxPageSize, LoginUser loginUser) {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new ArrayList<VendorMapping>();
        }

        VendorMappingExample example = new VendorMappingExample();
        Criteria criteria = example.createCriteria();
        criteria.andVendorTenantIdEqualTo(loginUser.getTenantId());

        if (StringUtils.isNotBlank(vendorName)) {
            criteria.andVendorNameLike(vendorName + "%");
        }
        criteria.andIsDeleteEqualTo(false);

        if (null != backMaxPageSize && backMaxPageSize.compareTo(0) == 1) {
            example.setSize(backMaxPageSize);
            example.setStartOffSet(0);
        }
        example.setOrderByClause(" create_time desc ");

        return vendorMappingMapper.selectByExample(example);
    }

    @Override
    public VendorProjectMapping findVendorProjectMappingBy(Integer vendorId, Integer vendorCustomerId,
            Integer projectId, LoginUser loginUser) throws BusinessException {
        if (null == vendorId || null == vendorCustomerId || null == loginUser || null == loginUser.getTenantId()) {
            return null;
        }

        VendorMappingExample example = new VendorMappingExample();
        Criteria criteria = example.createCriteria();
        criteria.andVendorTenantIdEqualTo(loginUser.getTenantId());
        criteria.andVendorIdEqualTo(vendorId);
        criteria.andVendorCustomerIdEqualTo(vendorCustomerId);
        criteria.andIsDeleteEqualTo(false);
        List<VendorMapping> list = vendorMappingMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }

        return this.findProjectMappingByVendorMappingIdAndProjectId(list.get(0).getVendorMappingId(), projectId);
    }

    @Override
    public VendorProjectMapping findProjectMappingByVendorMappingIdAndProjectId(Integer vendorMappingId,
            Integer projectId) throws BusinessException {
        if (null == vendorMappingId) {
            return null;
        }

        // 无项目数据库projectId默认为-1
        if (null == projectId) {
            projectId = -1;
        }

        VendorProjectMappingExample example = new VendorProjectMappingExample();
        example.createCriteria().andVendorMappingIdEqualTo(vendorMappingId).andProjectIdEqualTo(projectId);
        List<VendorProjectMapping> list = vendorProjectMappingMapper.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public VendorMapping getVendorMapping(Integer vendorId, LoginUser loginUser) {
        if (null == vendorId || null == loginUser || null == loginUser.getTenantId()) {
            return null;
        }

        VendorMappingExample example = new VendorMappingExample();
        example.createCriteria().andVendorIdEqualTo(vendorId).andVendorTenantIdEqualTo(loginUser.getTenantId());

        List<VendorMapping> list = vendorMappingMapper.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void updateVendorName(Integer vendorId, String vendorName) throws BusinessException {
        if (null == vendorId || StringUtils.isBlank(vendorName)) {
            return;
        }

        VendorMappingExample example = new VendorMappingExample();
        Criteria criteria = example.createCriteria();
        criteria.andVendorIdEqualTo(vendorId);
        List<VendorMapping> list = vendorMappingMapper.selectByExample(example);
        for (VendorMapping vendorMapping : list) {
            vendorMapping.setVendorName(vendorName);
            vendorMappingMapper.updateByExample(vendorMapping, example);
        }
    }

    @Override
    public void updateVendorCustomerName(Integer vendorCustomerId, String vendorCustomerName) throws BusinessException {
        if (null == vendorCustomerId || StringUtils.isBlank(vendorCustomerName)) {
            return;
        }

        VendorMappingExample example = new VendorMappingExample();
        Criteria criteria = example.createCriteria();
        criteria.andVendorCustomerIdEqualTo(vendorCustomerId);
        List<VendorMapping> list = vendorMappingMapper.selectByExample(example);
        for (VendorMapping vendorMapping : list) {
            vendorMapping.setVendorCustomerName(vendorCustomerName);
            vendorMappingMapper.updateByExample(vendorMapping, example);
        }
    }

    @Override
    public Page<VendorMappingBo> search(PageCondition pageCondition) {
        VendorMappingExample example = new VendorMappingExample();
        Paging paging = new Paging(pageCondition, "create_time", null);
        example.setStartOffSet(paging.getStartOffSet());
        example.setSize(paging.getPageSize());
        example.setOrderByClause(paging.getOrder());

        Criteria criteria = example.createCriteria();
        if (null != pageCondition.getFilters() && null != pageCondition.getFilters().get("vendorName")) {
            criteria.andVendorNameLike(pageCondition.getFilters().get("vendorName").toString() + "%");
        }
        if (null != pageCondition.getFilters() && null != pageCondition.getFilters().get("vendorCustomerName")) {
            criteria.andVendorCustomerNameLike(pageCondition.getFilters().get("vendorCustomerName").toString() + "%");
        }
        long total = vendorMappingMapper.countByExample(example);
        List<VendorMapping> list = vendorMappingMapper.selectByExample(example);
        List<VendorMappingBo> boList = new ArrayList<VendorMappingBo>();
        Map map = getAllTenant();
        for (VendorMapping vendorMapping : list) {
            VendorMappingBo vendorMappingBo = new VendorMappingBo();
            try {
                BeanUtils.copyProperties(vendorMappingBo, vendorMapping);
                vendorMappingBo.setVendorTenantName(String.valueOf(map.get(vendorMapping.getVendorTenantId())));
                vendorMappingBo.setCustomerTenantName(String.valueOf(map.get(vendorMapping.getCustomerTenantId())));
                boList.add(vendorMappingBo);
            } catch (IllegalAccessException e) {
                log.warn(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                log.warn(e.getMessage(), e);
            }
        }
        return new Page<VendorMappingBo>(pageCondition.getPageNo(), pageCondition.getPageSize(), (int) total, boList);
    }

    private Map getAllTenant() {
        Map map = new HashMap();
        List<Tenant> allTenant = tenantService.findAllTenant();
        for (Tenant t : allTenant) {
            map.put(t.getTenantId(), t.getTenantName());
        }
        return map;
    }

    @Override
    public Page<VendorProjectMappingBo> listByVendorMappingId(PageCondition pageCondition) {
        if (null == pageCondition.getFilters().get("vendorMappingId")) {
            return new Page<VendorProjectMappingBo>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                    new ArrayList<VendorProjectMappingBo>());
        }
        VendorProjectMappingExample example = new VendorProjectMappingExample();
        example.setStartOffSet(pageCondition.getStartOffSet());
        example.setSize(pageCondition.getPageSize());
        VendorProjectMappingExample.Criteria criteria = example.createCriteria();

        if (null != pageCondition.getFilters() && null != pageCondition.getFilters().get("vendorMappingId")) {
            Integer vendorMappingId = Integer.parseInt(pageCondition.getFilters().get("vendorMappingId").toString());
            criteria.andVendorMappingIdEqualTo(vendorMappingId);
        }
        long total = vendorProjectMappingMapper.countByExample(example);
        List<VendorProjectMapping> list = vendorProjectMappingMapper.selectByExample(example);
        List<VendorProjectMappingBo> vendorProjectMappingBos = buildVendorProjectMappingBo(list);
        return new Page<VendorProjectMappingBo>(pageCondition.getPageNo(), pageCondition.getPageSize(), (int) total,
                vendorProjectMappingBos);
    }

    @Override
    public Integer save(VendorMappingBo vendorMappingBo, LoginUser loginUser) throws BusinessException {
        validateParm(vendorMappingBo);
        vendorMappingBo.setCreateTime(new Date());
        vendorMappingBo.setCreateUserId(loginUser.getUserId());
        vendorMappingBo.setIsDelete(false);
        vendorMappingMapper.insertSelective(vendorMappingBo);
        if (null != vendorMappingBo.getVendorProjectMappings()) {
            for (VendorProjectMapping vpm : vendorMappingBo.getVendorProjectMappings()) {
                vpm.setVendorMappingId(vendorMappingBo.getVendorMappingId());
                vendorProjectMappingMapper.insertSelective(vpm);
            }
        }

        this.modifyCargoOwnerBelongToEnterprise(vendorMappingBo.getVendorCustomerId(),
                vendorMappingBo.getCustomerTenantId());

        return vendorMappingBo.getVendorMappingId();
    }

    @Override
    public Integer update(VendorMappingBo vendorMappingBo, LoginUser loginUser) {
        validateParm(vendorMappingBo);
        vendorMappingBo.setLastUpdateUserId(loginUser.getUserId());
        vendorMappingBo.setLastUpdateUserName(new Date());
        vendorMappingMapper.updateByPrimaryKeySelective(vendorMappingBo);
        if (null != vendorMappingBo.getVendorProjectMappings()) {
            for (VendorProjectMapping vpm : vendorMappingBo.getVendorProjectMappings()) {
                vendorProjectMappingMapper.updateByPrimaryKeySelective(vpm);
            }
        }

        this.modifyCargoOwnerBelongToEnterprise(vendorMappingBo.getVendorCustomerId(),
                vendorMappingBo.getCustomerTenantId());

        return vendorMappingBo.getVendorMappingId();
    }

    @Override
    public void deleteVendor(Integer vendorMappingId) throws BusinessException {
        VendorProjectMappingExample example = new VendorProjectMappingExample();
        VendorProjectMappingExample.Criteria criteria = example.createCriteria();
        criteria.andVendorMappingIdEqualTo(vendorMappingId);
        List<VendorProjectMapping> vendorProjectMappings = vendorProjectMappingMapper.selectByExample(example);
        if (!vendorProjectMappings.isEmpty()) {
            throw new BusinessException("ExistVendorProjectMapping", "vendor.error.existVendorProjectMapping");
        }
        vendorMappingMapper.deleteByPrimaryKey(vendorMappingId);
    }

    private void validateParm(VendorMappingBo vendorMappingBo) {
        if (null == vendorMappingBo.getVendorTenantId()) {
            throw new BusinessException("VendorTenantIdNull", "vendor.error.vendorTenantIdNull");
        }
        if (null == vendorMappingBo.getVendorId() || StringUtils.isBlank(vendorMappingBo.getVendorName())) {
            throw new BusinessException("VendorIdNull", "vendor.error.vendorIdNull");
        }
        if (null == vendorMappingBo.getCustomerTenantId()) {
            throw new BusinessException("CustomerTenantIdNull", "vendor.error.customerTenantIdNull");
        }
        if (null == vendorMappingBo.getVendorCustomerId()
                || StringUtils.isBlank(vendorMappingBo.getVendorCustomerName())) {
            throw new BusinessException("VendorCustomerIdNull", "vendor.error.vendorCustomerIdNull");
        }

        VendorMappingExample example = new VendorMappingExample();
        Criteria criteria = example.createCriteria();
        criteria.andVendorIdEqualTo(vendorMappingBo.getVendorId());
        criteria.andVendorTenantIdEqualTo(vendorMappingBo.getVendorTenantId());
        List<VendorMapping> list = vendorMappingMapper.selectByExample(example);
        if (!list.isEmpty()) {
            if (null == vendorMappingBo.getVendorMappingId()) {
                throw new BusinessException("ExistVendorRelation", "vendor.error.existVendorRelation");
            } else {
                if (!list.get(0).getVendorMappingId().equals(vendorMappingBo.getVendorMappingId())) {
                    throw new BusinessException("ExistVendorRelation", "vendor.error.existVendorRelation");
                }
            }
        }

        VendorMappingExample e = new VendorMappingExample();
        Criteria c = e.createCriteria();
        c.andVendorCustomerIdEqualTo(vendorMappingBo.getVendorCustomerId());
        c.andCustomerTenantIdEqualTo(vendorMappingBo.getCustomerTenantId());
        List<VendorMapping> l = vendorMappingMapper.selectByExample(e);
        if (!l.isEmpty()) {
            if (null == vendorMappingBo.getVendorMappingId()) {
                throw new BusinessException("ExistVendorCustomerRelation", "vendor.error.existVendorCustomerRelation");
            } else {
                if (!l.get(0).getVendorMappingId().equals(vendorMappingBo.getVendorMappingId())) {
                    throw new BusinessException("ExistVendorCustomerRelation",
                            "vendor.error.existVendorCustomerRelation");
                }
            }
        }
    }

    @Override
    public void saveOrUpdateVendorProjectMapping(VendorProjectMappingBo vendorProjectMappingBo)
            throws BusinessException {
        if (vendorProjectMappingBo.getProjectId().equals(vendorProjectMappingBo.getVendorProjectId())) {
            throw new BusinessException("ProjectIdNotTheSame", "vendor.error.projectIdNotTheSame");
        }

        if (null != vendorProjectMappingBo.getVendorMappingId() && null != vendorProjectMappingBo.getCustomerId()
                && null != vendorProjectMappingBo.getProjectId()) {
            VendorProjectMappingExample example = new VendorProjectMappingExample();
            VendorProjectMappingExample.Criteria criteria = example.createCriteria();
            criteria.andVendorMappingIdEqualTo(vendorProjectMappingBo.getVendorMappingId());
            criteria.andCustomerIdEqualTo(vendorProjectMappingBo.getCustomerId());
            criteria.andProjectIdEqualTo(vendorProjectMappingBo.getProjectId());
            List<VendorProjectMapping> vendorProjectMappings = vendorProjectMappingMapper.selectByExample(example);
            if (!vendorProjectMappings.isEmpty()) {
                if (null == vendorProjectMappingBo.getVendorProjectMappingId()) {
                    throw new BusinessException("ExistProjectVendorRelation",
                            "vendor.error.existProjectVendorRelation");
                } else {
                    if (!vendorProjectMappings.get(0).getVendorProjectMappingId()
                            .equals(vendorProjectMappingBo.getVendorProjectMappingId())) {
                        throw new BusinessException("ExistProjectVendorRelation",
                                "vendor.error.existProjectVendorRelation");
                    }
                }
            }
        }

        if (null == vendorProjectMappingBo.getProjectId()) {
            vendorProjectMappingBo.setProjectId(-1);
        }

        if (null == vendorProjectMappingBo.getVendorProjectMappingId()) {
            vendorProjectMappingMapper.insert(vendorProjectMappingBo);
        } else {
            vendorProjectMappingMapper.updateByPrimaryKey(vendorProjectMappingBo);
        }
    }

    @Override
    public void delete(Integer vendorProjectMappingId) throws BusinessException {
        vendorProjectMappingMapper.deleteByPrimaryKey(vendorProjectMappingId);
    }

    @Override
    public VendorMappingBo edit(Integer vendorMappingId) {
        VendorMapping vendorMapping = vendorMappingMapper.selectByPrimaryKey(vendorMappingId);
        VendorMappingBo vendorMappingBo = new VendorMappingBo();
        if (null != vendorMapping.getVendorTenantId()) {
            try {
                BeanUtils.copyProperties(vendorMappingBo, vendorMapping);
            } catch (IllegalAccessException e) {
                log.warn(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                log.warn(e.getMessage(), e);
            }
            Tenant vendorTenant = tenantService.loadTenant(vendorMapping.getVendorTenantId());
            if (null != vendorTenant) {
                vendorMappingBo.setVendorTenantName(vendorTenant.getTenantName());
            }
            Tenant customerTenant = tenantService.loadTenant(vendorMapping.getCustomerTenantId());
            if (null != customerTenant) {
                vendorMappingBo.setCustomerTenantName(customerTenant.getTenantName());
            }
            if (StringUtils.isNotBlank(vendorMapping.getDepartmentCode())) {
                LoginUser loginUser = new LoginUser(vendorMapping.getCustomerTenantId(), 1);
                Department department = departmentService.loadDepartment(vendorMapping.getDepartmentCode(),loginUser);
                // 子公司名称
                if (null != department) {
                    vendorMappingBo.setBusinessLicenceName(department.getBusinessLicenceName());
                }
            }
            //customerManagerUserId
            CustomerInfo customerInfo = customerInfoService.findCusInfoById(vendorMapping.getVendorCustomerId());
            if(null != customerInfo){
                vendorMappingBo.setCustomerManagerUserId(customerInfo.getCustomerManagerUserId());
            }
        }
        VendorProjectMappingExample example = new VendorProjectMappingExample();
        VendorProjectMappingExample.Criteria criteria = example.createCriteria();
        criteria.andVendorMappingIdEqualTo(vendorMappingId);
        List<VendorProjectMapping> vendorProjectMappings = vendorProjectMappingMapper.selectByExample(example);
        List<VendorProjectMappingBo> vendorProjectMappingBos = buildVendorProjectMappingBo(vendorProjectMappings);
        vendorMappingBo.setVendorProjectMappings(vendorProjectMappingBos);
        return vendorMappingBo;
    }

    private List<VendorProjectMappingBo> buildVendorProjectMappingBo(List<VendorProjectMapping> vendorProjectMappings) {
        List<VendorProjectMappingBo> vendorProjectMappingBos = new ArrayList<VendorProjectMappingBo>();
        for (VendorProjectMapping vpm : vendorProjectMappings) {
            VendorProjectMappingBo bo = new VendorProjectMappingBo();
            try {
                BeanUtils.copyProperties(bo, vpm);
                if (null != vpm.getCustomerId()) {
                    CustomerInfo customerInfo = customerInfoService.findCusInfoById(vpm.getCustomerId());
                    if (null != customerInfo) {
                        bo.setCustomerName(customerInfo.getCustomerName());
                    }
                }
                if (null != vpm.getProjectId()) {
                    Project project = projectService.getProject(vpm.getProjectId());
                    if (null != project) {
                        bo.setProjectName(project.getName());
                    }
                }
                if (null != vpm.getVendorProjectId()) {
                    Project project = projectService.getProject(vpm.getVendorProjectId());
                    if (null != project) {
                        bo.setVendorProjectName(project.getName());
                    }
                }
                vendorProjectMappingBos.add(bo);
            } catch (IllegalAccessException e) {
                log.warn(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                log.warn(e.getMessage(), e);
            }
        }
        return vendorProjectMappingBos;
    }

    @Override
    public List<VendorMapping> findVendorName(VendorMapping vendorMapping) {
        if (null == vendorMapping.getVendorTenantId()) {
            return null;
        }
        VendorMappingExample example = new VendorMappingExample();
        Criteria criteria = example.createCriteria();
        example.setSize(Integer.MAX_VALUE);
        criteria.andVendorTenantIdEqualTo(vendorMapping.getVendorTenantId());
        if (StringUtils.isNotBlank(vendorMapping.getVendorName())) {
            criteria.andVendorNameLike(vendorMapping.getVendorName() + "%");
        }
        return vendorMappingMapper.selectByExample(example);
    }

    // 将客户的联系人转为用车人并与客户创建关联关系
    private void modifyCargoOwnerBelongToEnterprise(Integer vendorCustomerId, Integer customerTenantId) {
        if (null == vendorCustomerId || null == customerTenantId) {
            return;
        }

        CustomerInfo customerInfo = customerInfoService.findCusInfoById(vendorCustomerId);
        if (null == customerInfo) {
            return;
        }

        Tenant tenant = tenantService.loadTenant(customerTenantId);
        if (null == tenant) {
            return;
        }

        LoginUser loginUser = Constants.SYS_LOGIN_USER;
        loginUser.setTenantId(customerTenantId);
        loginUser.setTenantCode(tenant.getTenantCode());

        TruckCustomer truckCustomer = new TruckCustomer();
        truckCustomer.setNickname(customerInfo.getLinkMan());
        truckCustomer.setContactPhone(customerInfo.getLinkTel());
        tgmUserCenterService.createCargoOwnerBelongEnterprise(truckCustomer, customerInfo, loginUser);
    }

    @Override
    public Project getByMappingIdAndSourceProjectId(Integer vendorMappingId, Integer sourceProjectId)
            throws BusinessException {
        if (vendorMappingId == null)
            throw new BusinessException("vendorMappingIdNullError", "errors.paramErrorWithName", "承运商映射id");

        VendorProjectMappingExample example = new VendorProjectMappingExample();
        VendorProjectMappingExample.Criteria criteria = example.createCriteria();
        criteria.andVendorMappingIdEqualTo(vendorMappingId);
        if (sourceProjectId == null) {
            criteria.andProjectIdEqualTo(-1);
        } else {
            criteria.andProjectIdEqualTo(sourceProjectId);
        }
        List<VendorProjectMapping> vendorProjectMappingList = vendorProjectMappingMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(vendorProjectMappingList))
            return null;

        // 获取项目信息
        return projectService.getProject(vendorProjectMappingList.get(0).getVendorProjectId());
    }

    @Override
    public void deleteByVendorId(Integer vendorId, LoginUser loginUser) throws BusinessException {
        if (null == vendorId) {
            return;
        }

        VendorMappingExample example = new VendorMappingExample();
        Criteria criteria = example.createCriteria();
        criteria.andVendorIdEqualTo(vendorId);
        List<VendorMapping> list = vendorMappingMapper.selectByExample(example);
        for (VendorMapping v : list) {
            vendorMappingMapper.deleteByPrimaryKey(v.getVendorMappingId());

            VendorProjectMappingExample projectExample = new VendorProjectMappingExample();
            projectExample.createCriteria().andVendorMappingIdEqualTo(v.getVendorMappingId());
            vendorProjectMappingMapper.deleteByExample(projectExample);
        }
    }
}
