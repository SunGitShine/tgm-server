package com.juma.tgm.truck.service.impl;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.scm.product.bo.PropertyBO;
import com.juma.scm.product.domain.PropertyValue;
import com.juma.scm.product.service.PropertyService;
import com.juma.tgm.base.domain.BaseEnumDomian;
import com.juma.tgm.common.Constants;
import com.juma.tgm.configure.domain.TruckTypeCity;
import com.juma.tgm.configure.service.TruckTypeCityService;
import com.juma.tgm.truck.dao.TruckTypeDao;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.truck.service.TruckTypeService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TruckTypeServiceImpl implements TruckTypeService {

    private static Logger log = LoggerFactory.getLogger(TruckTypeServiceImpl.class);

    @Resource
    private TruckTypeDao truckTypeDao;
    @Resource
    private TruckTypeCityService truckTypeCityService;
    @Resource
    private PropertyService propertyService;

    @Override
    public Page<TruckType> search(PageCondition pageCondition, LoginUser loginUser) {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new Page<TruckType>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                    new ArrayList<TruckType>());
        }

        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());
        pageCondition.getFilters().put("isDelete", false);
        int total = truckTypeDao.searchCount(pageCondition);
        List<TruckType> rows = truckTypeDao.search(pageCondition);
        return new Page<TruckType>(pageCondition.getPageNo(), pageCondition.getPageSize(), total, rows);
    }

    @Override
    public TruckType getTruckType(Integer truckTypeId) {
        return truckTypeDao.get(truckTypeId);
    }

    @Override
    public List<TruckType> listAllTruckTypeSimpleByOrderNoAsc(Integer tenantId, Boolean isDelete) {
        if (null == tenantId) {
            return new ArrayList<TruckType>();
        }

        TruckType example = new TruckType();
        example.setTenantId(tenantId);
        example.setIsDelete(isDelete);
        return truckTypeDao.findByExample(example);
    }

    @Override
    public List<TruckType> listAllTruckTypeByOrderNoAsc(Integer tenantId, Boolean isDelete) {
        List<TruckType> list = listAllTruckTypeSimpleByOrderNoAsc(tenantId, isDelete);
        if (list.isEmpty()) {
            return list;
        }

        // 获取厢型基础数据
        Map<Integer, String> mapBoxType = this.mapPropertyValue(Constants.SCM_BOX_TYPE);

        // 获取厢长基础数据
        Map<Integer, String> mapBoxlength = this.mapPropertyValue(Constants.SCM_BOX_LEVEL);
        for (TruckType truckType : list) {
            String boxTypeName = mapBoxType.get(truckType.getVehicleBoxType());
            String boxLengthName = mapBoxlength.get(truckType.getTruckLengthId());
            truckType.setTruckTypeName((StringUtils.isBlank(boxTypeName) ? "" : boxTypeName)
                    + (StringUtils.isBlank(boxLengthName) ? "" : boxLengthName));
        }
        return list;
    }

    // 获取厢型或厢长基础数据，并组装为map
    private Map<Integer, String> mapPropertyValue(String key) {
        Map<Integer, String> mapProperty = new HashMap<Integer, String>();
        List<BaseEnumDomian> listProperty = this.handlePropertyValue(key);
        for (BaseEnumDomian d : listProperty) {
            mapProperty.put(d.getCode(), d.getDesc());
        }

        return mapProperty;
    }

    @Override
    public List<TruckType> listByRegionCode(String regionCode, boolean isEnable, LoginUser loginUser) {
        List<TruckType> result = new ArrayList<TruckType>();
        if (null == loginUser || null == loginUser.getTenantId() || StringUtils.isBlank(regionCode)) {
            return result;
        }

        List<TruckTypeCity> list = truckTypeCityService.listByRegionCode(regionCode, isEnable, loginUser);
        if (list.isEmpty()) {
            return result;
        }

        // 获取厢型基础数据
        Map<Integer, String> mapBoxType = this.mapPropertyValue(Constants.SCM_BOX_TYPE);

        // 获取厢长基础数据
        Map<Integer, String> mapBoxlength = this.mapPropertyValue(Constants.SCM_BOX_LEVEL);
        for (TruckTypeCity c : list) {
            TruckType truckType = getTruckType(c.getTruckTypeId());
            if (null == truckType) {
                continue;
            }

            String boxTypeName = mapBoxType.get(truckType.getVehicleBoxType());
            String boxLengthName = mapBoxlength.get(truckType.getTruckLengthId());
            truckType.setTruckTypeName((StringUtils.isBlank(boxTypeName) ? "" : boxTypeName)
                    + (StringUtils.isBlank(boxLengthName) ? "" : boxLengthName));

            result.add(truckType);
        }

        return result;
    }

    @Override
    public String findTruckTypeNameByTypeId(Integer truckTypeId) {
        TruckType truckType = getTruckType(truckTypeId);
        if (null == truckType) {
            return null;
        }

        return findTruckTypeNameBy(truckType.getVehicleBoxType(), truckType.getTruckLengthId());
    }

    @Override
    public String findVehicleBoxTypeName(Integer vehicleBoxType) {
        if (null == vehicleBoxType) {
            return null;
        }

        List<BaseEnumDomian> listVehicleBoxType = listVehicleBoxType();

        for (BaseEnumDomian domian : listVehicleBoxType) {
            if (NumberUtils.compare(domian.getCode(), vehicleBoxType) == 0) {
                return domian.getDesc();
            }
        }

        return null;
    }

    @Override
    public List<BaseEnumDomian> listVehicleBoxType() {
        return this.handlePropertyValue(Constants.SCM_BOX_TYPE);
    }

    @Override
    public List<BaseEnumDomian> listVehicleBoxlength() {
        return this.handlePropertyValue(Constants.SCM_BOX_LEVEL);
    }

    // 处理SCM获取的PropertyValue数据
    private List<BaseEnumDomian> handlePropertyValue(String key) {
        List<BaseEnumDomian> result = new ArrayList<BaseEnumDomian>();
        List<String> keys = new ArrayList<String>();
        keys.add(key);

        Map<String, List<PropertyBO>> map = null;
        try {
            map = propertyService.listByKeys(keys);
        } catch (BusinessException e) {
            log.warn("从SCM获取数据错误", e);
        }

        if (null == map || map.isEmpty()) {
            return result;
        }

        List<PropertyBO> list = map.get(key);
        if (CollectionUtils.isEmpty(list)) {
            return result;
        }

        List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();
        for (PropertyBO propertyBO : list) {
            if (StringUtils.isNotBlank(propertyBO.getKey()) && propertyBO.getKey().equals(key)) {
                propertyValues = propertyBO.getPropertyValues();
            }
        }

        if (CollectionUtils.isEmpty(propertyValues)) {
            return result;
        }

        for (PropertyValue propertyValue : propertyValues) {
            BaseEnumDomian domain = new BaseEnumDomian();
            domain.setCode(propertyValue.getId());
            domain.setDesc(propertyValue.getName());
            result.add(domain);
        }

        return result;
    }

    @Override
    public String findVehicleBoxLengthName(Integer boxLength) {
        if (null == boxLength) {
            return null;
        }

        List<BaseEnumDomian> list = this.listVehicleBoxlength();
        if (list.isEmpty()) {
            return null;
        }

        for (BaseEnumDomian domian : list) {
            if (NumberUtils.compare(domian.getCode(), boxLength) == 0) {
                return domian.getDesc();
            }
        }

        return null;
    }

    @Override
    public void insert(TruckType truckType, LoginUser loginUser) {
        truckType.setTenantCode(loginUser.getTenantCode());
        truckType.setTenantId(loginUser.getTenantId());
        check(truckType);
        truckType.setCreateUserId(loginUser.getUserId());
        truckType.setOrderNum(truckTypeDao.findMaxOrderNo(loginUser.getTenantId()) + 1);
        truckTypeDao.insert(truckType);
    }

    @Override
    public void update(TruckType truckType, LoginUser loginUser) {
        truckType.setTenantCode(loginUser.getTenantCode());
        truckType.setTenantId(loginUser.getTenantId());
        check(truckType);
        truckType.setLastUpdateUserId(loginUser.getUserId());
        truckTypeDao.update(truckType);
    }

    @Override
    public void updateToEnable(Integer truckTypeId, LoginUser loginUser) throws BusinessException {
        TruckType truckType = getTruckType(truckTypeId);
        if (null == truckType) {
            return;
        }

        // 判断厢型是不是存在,不存在则不能启用
        if (StringUtils.isBlank(findVehicleBoxTypeName(truckType.getVehicleBoxType()))) {
            throw new BusinessException("truckClassError", "truckType.error.truckClassError");
        }

        // 判断车长是不是存在,不存在则不能启用
        if (StringUtils.isBlank(findVehicleBoxLengthName(truckType.getTruckLengthId()))) {
            throw new BusinessException("truckLengthError", "truckType.error.truckLengthError");
        }

        // 启用
        truckType.setIsDelete(false);
        truckType.setLastUpdateUserId(loginUser.getUserId());
        truckTypeDao.update(truckType);
    }

    @Override
    public void updateToDisable(Integer truckTypeId, LoginUser loginUser) {
        TruckType truckType = new TruckType();
        truckType.setTruckTypeId(truckTypeId);
        truckType.setIsDelete(true);
        truckType.setLastUpdateUserId(loginUser.getUserId());
        truckTypeDao.update(truckType);
    }

    // 验证
    private void check(TruckType truckType) {
        if (null == truckType) {
            throw new BusinessException("paramsError", "errors.paramsError");
        }

        // 租户信息不能为空
        if (null == truckType.getTenantId()) {
            throw new BusinessException("userTenantCodeNotExist", "errors.userTenantCodeNotExist");
        }

        // 厢型信息不能为空
        if (null == truckType.getVehicleBoxType()) {
            throw new BusinessException("truckClassNotSelected", "truckType.error.truckClassNotSelected");
        }

        // 车型的车厢长度可以为空
        if (null == truckType.getTruckLengthId()) {
            return;
        }

        // 同一租户下，车型信息不能重复
        TruckType oldTruckType = findByBoxAndLength(truckType.getVehicleBoxType(), truckType.getTruckLengthId(),
                truckType.getTenantId());
        if (null != oldTruckType && null == truckType.getTruckTypeId()) {
            // 添加是不能重复
            throw new BusinessException("truckTypeHasExsit", "truckType.error.truckTypeHasExsit");
        } else if (null != oldTruckType && !oldTruckType.getTruckTypeId().equals(truckType.getTruckTypeId())) {
            // 修改时不能重复
            throw new BusinessException("truckTypeHasExsit", "truckType.error.truckTypeHasExsit");
        }
    }

    @Override
    public String findTruckTypeNameBy(Integer vehicleBoxType, Integer vehicleBoxLength) {
        String truckTypeName = "";
        if (null == vehicleBoxType && null == vehicleBoxLength) {
            return truckTypeName;
        }

        // 箱型
        truckTypeName = findVehicleBoxTypeName(vehicleBoxType);

        // 车长
        String lengthName = findVehicleBoxLengthName(vehicleBoxLength);
        truckTypeName = (StringUtils.isBlank(truckTypeName) ? "" : truckTypeName)
                + (StringUtils.isBlank(lengthName) ? "" : lengthName);

        return truckTypeName;
    }

    @Override
    public List<TruckType> findTruckTypeByLengthId(Integer truckLengthId) {
        TruckType example = new TruckType();
        example.setTruckLengthId(truckLengthId);
        example.setIsDelete(false);
        return truckTypeDao.findByExample(example);
    }

    @Override
    public void updateToUp(Integer truckTypeId, LoginUser loginUser) {
        TruckType truckType = getTruckType(truckTypeId);
        if (null == truckType || null == truckType.getTenantId()) {
            return;
        }

        truckType.setLastUpdateUserId(loginUser.getUserId());
        Integer orderNum = truckType.getOrderNum();
        if (orderNum.equals(1)) {
            throw new BusinessException("orderNumTop", "errors.orderNumTop");
        }
        updateNowOrderNum(truckType.getTenantId(), orderNum - 1, "UP", loginUser);
        truckType.setOrderNum(orderNum - 1);
        truckTypeDao.update(truckType);
    }

    @Override
    public void updateToDown(Integer truckTypeId, LoginUser loginUser) {
        TruckType truckType = getTruckType(truckTypeId);
        if (null == truckType || null == truckType.getTenantId()) {
            return;
        }

        truckType.setLastUpdateUserId(loginUser.getUserId());
        Integer orderNum = truckType.getOrderNum();
        int total = truckTypeDao.findMaxOrderNo(loginUser.getTenantId());
        if (orderNum.equals(total)) {
            throw new BusinessException("orderNumButtom", "errors.orderNumButtom");
        }
        updateNowOrderNum(truckType.getTenantId(), orderNum + 1, "DOWN", loginUser);
        truckType.setOrderNum(orderNum + 1);
        truckTypeDao.update(truckType);
    }

    // 移动
    private void updateNowOrderNum(Integer tenantId, Integer orderNum, String action, LoginUser loginUser) {
        TruckType example = new TruckType();
        example.setTenantId(tenantId);
        example.setOrderNum(orderNum);
        List<TruckType> list = truckTypeDao.findByExample(example);
        if (list.isEmpty()) {
            return;
        }

        TruckType truckType = list.get(0);
        if (null == truckType) {
            throw new BusinessException("orderNumErr", "truckType.error.orderNumErr");
        }
        truckType.setLastUpdateUserId(loginUser.getUserId());
        if (StringUtils.isNotBlank(action) && action.equals("UP")) {
            truckType.setOrderNum(orderNum + 1);
        } else if (StringUtils.isNotBlank(action) && action.equals("DOWN")) {
            truckType.setOrderNum(orderNum - 1);
        }
        truckTypeDao.update(truckType);
    }

    @Override
    public TruckType findByBoxAndLength(Integer vehicleBoxType, Integer vehicleBoxlength, Integer tenantId) {
        if (null == vehicleBoxType || null == vehicleBoxlength || null == tenantId) {
            return null;
        }
        TruckType example = new TruckType();
        example.setVehicleBoxType(vehicleBoxType);
        example.setTruckLengthId(vehicleBoxlength);
        example.setTenantId(tenantId);
        List<TruckType> list = truckTypeDao.findByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<TruckType> listVehicleBoxTypeByTenant(LoginUser loginUser) {
        List<TruckType> result = new ArrayList<>();
        if (null == loginUser || null == loginUser.getTenantId()) {
            return result;
        }

        TruckType example = new TruckType();
        example.setTenantId(loginUser.getTenantId());
        example.setIsDelete(false);
        return truckTypeDao.findByExampleGroupByBoxType(example);
    }

    @Override
    public List<TruckType> listVehicleBoxLengthBytypeAndTenant(Integer vehicleBoxType, LoginUser loginUser) {
        List<TruckType> result = new ArrayList<>();
        if (null == vehicleBoxType || null == loginUser || null == loginUser.getTenantId()) {
            return result;
        }

        TruckType example = new TruckType();
        example.setTenantId(loginUser.getTenantId());
        example.setVehicleBoxType(vehicleBoxType);
        example.setIsDelete(false);
        return truckTypeDao.findByExample(example);
    }
}
