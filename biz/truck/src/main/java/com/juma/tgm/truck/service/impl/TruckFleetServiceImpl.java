package com.juma.tgm.truck.service.impl;

import com.alibaba.fastjson.JSON;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.giants.common.tools.PageQueryCondition;
import com.juma.auth.employee.domain.Employee;
import com.juma.auth.employee.domain.EmployeeFilter;
import com.juma.auth.employee.domain.EmployeeInfo;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.tgm.capacity.domian.vo.CapacityFilter;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.tools.service.BusinessAreaCommonService;
import com.juma.tgm.tools.service.CommonService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.truck.dao.TruckFleetDao;
import com.juma.tgm.truck.domain.TruckFleet;
import com.juma.tgm.truck.domain.vo.TruckDriverVo;
import com.juma.tgm.truck.domain.vo.TruckFleetDriverRelationVo;
import com.juma.tgm.truck.domain.vo.TruckFleetQueryVo;
import com.juma.tgm.truck.service.TruckFleetService;
import com.juma.tgm.truck.service.TruckFleetTruckService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.truck.vo.TruckFleetTruckVo;
import com.juma.vms.transport.external.CapacityPoolExternalQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 
 * @Description: 车队信息实现类
 * @author weilibin
 * @date 2016年5月12日 下午2:00:45
 * @version V1.0
 */

@Service
public class TruckFleetServiceImpl implements TruckFleetService {

    private final Logger log = LoggerFactory.getLogger(TruckFleetServiceImpl.class);
    @Resource
    private TruckFleetDao truckFleetDao;
    @Resource
    private TruckFleetTruckService truckFleetTruckService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private CommonService commonService;
    @Resource
    private BusinessAreaCommonService businessAreaCommonService;
    @Resource
    private VmsCommonService vmsCommonService;
    @Resource
    private TruckTypeService truckTypeService;

    @Override
    public Page<TruckFleet> search(PageCondition pageCondition, LoginUser loginUser) {
        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());
        structPageCondition(pageCondition, loginUser);
        int count = truckFleetDao.searchCount(pageCondition);
        List<TruckFleet> result = truckFleetDao.search(pageCondition);
        for (TruckFleet truckFleet : result) {
            // 业务范围
            truckFleet.setAreaName(
                    businessAreaCommonService.loadLogicAndSelfAreaName(truckFleet.getAreaCode(), loginUser));

            // 客户经理信息
            User user = employeeService.loadUserByEmployeeId(truckFleet.getEmployeeId(), loginUser);
            if (null != user) {
                truckFleet.setAccountManager(user.getName());
                truckFleet.setAccountManagerPhone(user.getMobileNumber());
            }

            // 绑定的车辆信息
            StringBuffer plateNumberSf = new StringBuffer("");
            List<TruckFleetTruckVo> truckList = truckFleetTruckService.listByTruckFleetId(truckFleet.getTruckFleetId());
            for (TruckFleetTruckVo vo : truckList) {
                truckFleet.getListTruckFleetTruck().add(vo);
                plateNumberSf.append(vo.getPlateNumber()).append(",");
            }

            String plateNumbers = plateNumberSf.toString();
            if (plateNumbers.endsWith(",")) {
                plateNumbers = plateNumbers.substring(0, plateNumbers.length() - 1);
            }
            truckFleet.setPlateNumbers(plateNumbers);
        }
        return new Page<TruckFleet>(pageCondition.getPageNo(), pageCondition.getPageSize(), count, result);
    }

    @Override
    public TruckFleet getTruckFleet(Integer truckFleetId) {
        if (null == truckFleetId) {
            return null;
        }
        return truckFleetDao.get(truckFleetId);
    }

    @Override
    public List<TruckFleet> listTruckFleetByUserId(LoginUser loginUser) {
        TruckFleet example = new TruckFleet();
        example.setTenantId(loginUser.getTenantId());
        example.setUserId(loginUser.getUserId());
        return truckFleetDao.findByExample(example);
    }

    private TruckFleet findTruckFleetByTruckFleetName(String truckFleetName, LoginUser loginUser) {
        if (StringUtils.isBlank(truckFleetName)) {
            return null;
        }

        TruckFleet example = new TruckFleet();
        example.setTenantId(loginUser.getTenantId());
        example.setTruckFleetName(truckFleetName);
        List<TruckFleet> list = truckFleetDao.findByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<TruckFleet> listTruckFleetByListTruckFleetId(List<Integer> truckFleetIdList) {
        if (CollectionUtils.isEmpty(truckFleetIdList)) {
            return new ArrayList<TruckFleet>();
        }
        return truckFleetDao.listTruckFleetByListTruckFleetId(truckFleetIdList);
    }

    // 构造查询条件
    private void structPageCondition(PageCondition pageCondition, LoginUser loginUser) {
        Map<String, Object> filters = pageCondition.getFilters();
        if (null == filters) {
            return;
        }

        // 根据客户经理姓名查询
        Object obj = filters.get("customerManagerName");
        filters.remove("customerManagerName");
        if (null != obj) {
            // 根据客户经理(企业客户的客户经理)姓名获取客户经理的userId
            try {
                EmployeeFilter employeeFilter = new EmployeeFilter();
                employeeFilter.setName(obj.toString().trim());
                Page<EmployeeInfo> page = employeeService.searchEmployees(new PageQueryCondition<EmployeeFilter>(employeeFilter), Constants.AUTH_KEY_TGM_MANAGE,
                        Constants.CUSTOMER_MANAGER_PERMISSION_KEY, commonService.buildTopLoginEmployee(loginUser));
                log.info("根据客户经理(企业客户的客户经理)姓名获取客户经理员工ID：{}", page == null ? null : JSON.toJSONString(page));
                if (null != page && CollectionUtils.isNotEmpty(page.getResults())) {
                    List<Integer> employeeIdList = new ArrayList<Integer>();
                    for (EmployeeInfo employeeInfo : page.getResults()) {
                        employeeIdList.add(employeeInfo.getEmployeeId());
                    }
                    filters.put("employeeIdList", employeeIdList);
                } else {
                    // 查询不到是的策略
                    filters.put("employeeId", -1);
                }
            } catch (Exception e) {
            }
        }
        pageCondition.setFilters(filters);
    }

    @Override
    public void insert(TruckFleet truckFleet, LoginUser loginUser) {
        // 添加车队信息
        check(truckFleet, loginUser);
        Employee employee = employeeService.findEmployee(truckFleet.getEmployeeId(), loginUser);
        truckFleet.setUserId(employee.getUserId());
        truckFleet.setStatus(0);
        truckFleet.setCreateUserId(loginUser.getUserId());
        truckFleet.setTenantId(loginUser.getTenantId());
        truckFleet.setTenantCode(loginUser.getTenantCode());
        truckFleetDao.insert(truckFleet);
    }

    @Override
    public void update(TruckFleet truckFleet, LoginUser loginUser) {
        // 修改车队信息
        check(truckFleet, loginUser);
        Employee employee = employeeService.findEmployee(truckFleet.getEmployeeId(), loginUser);
        truckFleet.setUserId(employee.getUserId());
        truckFleet.setLastUpdateUserId(loginUser.getUserId());
        truckFleetDao.update(truckFleet);
    }

    /**
     * 验证车队名称唯一
     */
    private void check(TruckFleet truckFleet, LoginUser loginUser) {
        String truckFleetName = truckFleet.getTruckFleetName();
        Integer truckFleetId = truckFleet.getTruckFleetId();
        Integer employeeId = truckFleet.getEmployeeId();
        if (StringUtils.isBlank(truckFleetName)) {
            throw new BusinessException("truckFleetNameNotNull", "truckFleet.error.truckFleetNameNotNull");
        }

        TruckFleet tf = findTruckFleetByTruckFleetName(truckFleetName, loginUser);
        if (null != tf) {
            if (null == truckFleetId) {
                throw new BusinessException("truckFleetNameExist", "truckFleet.error.truckFleetNameExist",
                        truckFleetName);
            }
            if (!truckFleetId.equals(tf.getTruckFleetId())) {
                throw new BusinessException("truckFleetNameExist", "truckFleet.error.truckFleetNameExist",
                        truckFleetName);
            }
        }

        if (null == employeeId) {
            throw new BusinessException("accountManagerNotNull", "truckFleet.error.accountManagerNotNull");
        }
    }

    @Override
    public void updateToEnable(Integer truckFleetId, LoginUser loginUser) {
        TruckFleet truckFleet = new TruckFleet();
        truckFleet.setTruckFleetId(truckFleetId);
        truckFleet.setDelete(false);
        truckFleet.setLastUpdateUserId(loginUser.getUserId());
        truckFleet.setLastUpdateTime(new Date());
        truckFleetDao.update(truckFleet);
    }

    @Override
    public void updateToDisable(Integer truckFleetId, LoginUser loginUser) {
        TruckFleet truckFleet = new TruckFleet();
        truckFleet.setTruckFleetId(truckFleetId);
        truckFleet.setDelete(true);
        truckFleet.setLastUpdateUserId(loginUser.getUserId());
        truckFleet.setLastUpdateTime(new Date());
        truckFleetDao.update(truckFleet);

        // 删除车队关联表
        truckFleetTruckService.deleteByTruckFleetId(truckFleetId);
    }

    @Override
    public void updateToUnbundlingFleetAndUser(Integer userId, LoginUser loginUser) {
        TruckFleet truckFleet = new TruckFleet();
        truckFleet.setUserId(userId);
        truckFleet.setLastUpdateUserId(loginUser.getUserId());
        truckFleet.setLastUpdateTime(new Date());
        truckFleetDao.updateToUnbundlingFleetAndUser(truckFleet);
    }

    @Override
    public int countTruckFleet(LoginEmployee loginEmployee) {
        PageCondition pageCondition = new PageCondition();
        Map<String, Object> params = new HashMap<>();
        params.put("tenantId", loginEmployee.getTenantId());
        params.put("userId", loginEmployee.getUserId());
        params.put("isDelete", 0);
        pageCondition.setFilters(params);

        return truckFleetDao.searchCount(pageCondition);
    }

    @Override
    public TruckFleet findTruckFleetByEmployeeId(Integer employeeId, Integer tenantId) {
        if(null == employeeId || null == tenantId) {
            return null;
        }
        TruckFleet example = new TruckFleet();
        example.setTenantId(tenantId);
        example.setEmployeeId(employeeId);
        example.setIsDelete(false);
        List<TruckFleet> list = truckFleetDao.findByExample(example);
        return list.isEmpty()? null : list.get(0);
    }


    @Override
    public Page<TruckFleetDriverRelationVo> searchTruckFleet(PageQueryCondition<TruckFleetQueryVo> queryCondition, LoginEmployee loginEmployee) throws BusinessException {
        if (queryCondition == null) throw new BusinessException("pageQueryConditionNullError", "errors.paramCanNotNullWithName", "查询参数");
        if(loginEmployee == null) throw new BusinessException("loginEmployeeNullError", "errors.paramCanNotNullWithName", "登录人");

        TruckFleetQueryVo filters = queryCondition.getFilters();
        filters.setEmployeeId(loginEmployee.getEmployeeId());

        Page<TruckFleetDriverRelationVo> relationPage = new Page<>(queryCondition.getPageNo(), queryCondition.getPageSize(), 0);
        //判断数量是否大于0
        int count = truckFleetDao.searchTruckFleetCount(queryCondition);

        if(count == 0) return relationPage;

        relationPage.setTotal(count);

        List<TruckFleetDriverRelationVo> truckFleetDriverRelationVoList = truckFleetDao.searchTruckFleet(queryCondition);
        relationPage.setResults(truckFleetDriverRelationVoList);

        return relationPage;
    }

    @Override
    public Page<TruckDriverVo> availableTruckSearch(QueryCond<CapacityFilter> queryCond, LoginUser loginUser) {
        List<TruckDriverVo> result = new ArrayList<>();

        if (null == loginUser || null == loginUser.getTenantId()) {
            return new Page<>(queryCond.getPageNo(), queryCond.getPageSize(), 0, result);
        }

        CapacityFilter filters = queryCond.getFilters();
        if (null == filters) {
            filters = new CapacityFilter();
        }
        filters.setCapacityStatus(true);
        queryCond.setFilters(filters);

        Page<CapacityPoolExternalQuery> page = vmsCommonService.seachCapacity(queryCond, loginUser);
        for (CapacityPoolExternalQuery c : page.getResults()) {
            TruckDriverVo truckDriverVo = new TruckDriverVo();
            truckDriverVo.setTruckId(c.getTruckId());
            truckDriverVo.setTruckTypeName(
                    truckTypeService.findTruckTypeNameBy(c.getVehicleBoxType(), c.getVehicleBoxLength()));


            truckDriverVo.setDriverName(c.getDriverName());
            truckDriverVo.setDriverPhone(c.getDriverPhone());
            truckDriverVo.setPlateNumber(c.getPlateNumber());

            result.add(truckDriverVo);
        }

        return new Page<>(queryCond.getPageNo(), queryCond.getPageSize(), page.getTotal(), result);
    }
}
