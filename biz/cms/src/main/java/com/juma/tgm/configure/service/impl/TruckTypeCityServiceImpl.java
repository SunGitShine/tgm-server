package com.juma.tgm.configure.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.conf.domain.Region;
import com.juma.conf.service.RegionService;
import com.juma.tgm.configure.dao.TruckTypeCityDao;
import com.juma.tgm.configure.domain.TruckTypeCity;
import com.juma.tgm.configure.service.TruckTypeCityService;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.truck.service.TruckTypeService;

@Service
public class TruckTypeCityServiceImpl implements TruckTypeCityService {

    @Resource
    private TruckTypeCityDao truckTypeCityDao;
    @Resource
    private RegionService regionService;
    @Resource
    private TruckTypeService truckTypeService;

    @Override
    public Page<TruckTypeCity> search(PageCondition pageCondition, LoginUser loginUser) {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new Page<TruckTypeCity>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                    new ArrayList<TruckTypeCity>());
        }

        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());
        int total = truckTypeCityDao.searchCount(pageCondition);
        List<TruckTypeCity> rows = truckTypeCityDao.search(pageCondition);
        for (TruckTypeCity truckTypeCity : rows) {
            Region region = regionService.findByRegionCode(truckTypeCity.getRegionCode());
            if (null != region) {
                truckTypeCity.setRegionName(region.getRegionName());
            }
            TruckType truckType = truckTypeService.getTruckType(truckTypeCity.getTruckTypeId());
            if (null != truckType) {
                truckType.setTruckTypeName(truckTypeService.findTruckTypeNameByTypeId(truckTypeCity.getTruckTypeId()));
            }
            truckTypeCity.setTruckType(truckType);
        }
        return new Page<TruckTypeCity>(pageCondition.getPageNo(), pageCondition.getPageSize(), total, rows);
    }

    @Override
    public TruckTypeCity getTruckTypeCity(Integer truckTypeCityId) {
        return truckTypeCityDao.get(truckTypeCityId);
    }

    @Override
    public List<TruckTypeCity> listByRegionCode(String regionCode, boolean isEnable, LoginUser loginUser) {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new ArrayList<TruckTypeCity>();
        }

        TruckTypeCity example = new TruckTypeCity();
        example.setTenantId(loginUser.getTenantId());
        example.setRegionCode(regionCode);
        example.setIsEnable(isEnable);
        return truckTypeCityDao.findByExample(example);
    }

    @Override
    public List<TruckTypeCity> listByTruckTypeId(Integer truckTypeId, LoginUser loginUser) {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new ArrayList<TruckTypeCity>();
        }

        TruckTypeCity example = new TruckTypeCity();
        example.setTenantId(loginUser.getTenantId());
        example.setTruckTypeId(truckTypeId);
        return truckTypeCityDao.findByExample(example);
    }

    @Override
    public void insert(TruckTypeCity truckTypeCity, LoginUser loginUser) throws BusinessException {
        truckTypeCity.setTenantId(loginUser.getTenantId());
        truckTypeCity.setTenantCode(loginUser.getTenantCode());
        check(truckTypeCity);
        truckTypeCity.setOrderNo(findMaxOrderNo(truckTypeCity.getRegionCode(), loginUser) + 1);
        truckTypeCity.setCreateUserId(loginUser.getUserId());
        truckTypeCity.setIsEnable(true);
        truckTypeCityDao.insert(truckTypeCity);
    }

    @Override
    public void update(TruckTypeCity truckTypeCity, LoginUser loginUser) throws BusinessException {
        truckTypeCity.setTenantId(loginUser.getTenantId());
        truckTypeCity.setTenantCode(loginUser.getTenantCode());
        check(truckTypeCity);
        truckTypeCity.setLastUpdateUserId(loginUser.getUserId());
        truckTypeCityDao.update(truckTypeCity);
    }

    private void check(TruckTypeCity truckTypeCity) {
        // 租户信息不能为空
        if (null == truckTypeCity.getTenantId()) {
            throw new BusinessException("userTenantCodeNotExist", "errors.userTenantCodeNotExist");
        }

        // 城市信息不能为空
        if (StringUtils.isBlank(truckTypeCity.getRegionCode())) {
            throw new BusinessException("regionCodeRequire", "truckTypeCity.error.regionCodeRequire");
        }

        // 需要选择具体城市
        if (truckTypeCity.getRegionCode().length() < 5) {
            throw new BusinessException("regionCodeRequire", "truckTypeCity.error.regionCodeRequire");
        }

        // 车型信息不能为空
        if (null == truckTypeCity.getTruckTypeId()) {
            throw new BusinessException("truckTypeIdRequire", "truckTypeCity.error.truckTypeIdRequire");
        }

        TruckTypeCity example = new TruckTypeCity();
        example.setTenantId(truckTypeCity.getTenantId());
        example.setRegionCode(truckTypeCity.getRegionCode());
        example.setTruckTypeId(truckTypeCity.getTruckTypeId());
        List<TruckTypeCity> list = truckTypeCityDao.findByExample(example);

        if (list.isEmpty()) {
            return;
        }

        if (null == truckTypeCity.getTruckTypeCityId()
                || !truckTypeCity.getTruckTypeCityId().equals(list.get(0).getTruckTypeCityId())) {
            throw new BusinessException("regionCodeAndTruckTypeIdExist",
                    "truckTypeCity.error.regionCodeAndTruckTypeIdExist");
        }
    }

    @Override
    public void updateToEnable(Integer truckTypeCityId, LoginUser loginUser) throws BusinessException {
        TruckTypeCity truckTypeCity = new TruckTypeCity();
        truckTypeCity.setTruckTypeCityId(truckTypeCityId);
        truckTypeCity.setIsEnable(true);
        truckTypeCity.setLastUpdateUserId(loginUser.getUserId());
        truckTypeCityDao.update(truckTypeCity);
    }

    @Override
    public void updateToDisable(Integer truckTypeCityId, LoginUser loginUser) throws BusinessException {
        TruckTypeCity truckTypeCity = new TruckTypeCity();
        truckTypeCity.setTruckTypeCityId(truckTypeCityId);
        truckTypeCity.setIsEnable(false);
        truckTypeCity.setLastUpdateUserId(loginUser.getUserId());
        truckTypeCityDao.update(truckTypeCity);
    }

    // 获取到最大的order_no
    private int findMaxOrderNo(String regionCode, LoginUser loginUser) {
        PageCondition pageCondition = new PageCondition();
        pageCondition.getFilters().put("regionCode", regionCode);
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(1);
        pageCondition.setOrderBy(" order_no desc ");
        Page<TruckTypeCity> page = search(pageCondition, loginUser);
        if (page.getResults().isEmpty()) {
            return 1;
        }

        return ((List<TruckTypeCity>) page.getResults()).get(0).getOrderNo();
    }

    @Override
    public void updateToMoveUp(Integer truckTypeCityId, LoginUser loginUser) throws BusinessException {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return;
        }

        TruckTypeCity truckTypeCity = getTruckTypeCity(truckTypeCityId);
        if (null == truckTypeCity) {
            return;
        }

        // 获取上一个数据
        TruckTypeCity lastTruckTypeCity = truckTypeCityDao.findLastOrderNo(truckTypeCity);
        if (null == lastTruckTypeCity) {
            throw new BusinessException("orderNumTop", "errors.orderNumTop");
        }

        Integer orderNo = truckTypeCity.getOrderNo();
        // 交换
        truckTypeCity.setOrderNo(lastTruckTypeCity.getOrderNo());
        lastTruckTypeCity.setOrderNo(orderNo);
        updateDataBase(truckTypeCity, loginUser);
        updateDataBase(lastTruckTypeCity, loginUser);
    }

    @Override
    public void updateToMoveDown(Integer truckTypeCityId, LoginUser loginUser) throws BusinessException {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return;
        }

        TruckTypeCity truckTypeCity = getTruckTypeCity(truckTypeCityId);
        if (null == truckTypeCity) {
            return;
        }

        // 获取下一个数据
        TruckTypeCity nextTruckTypeCity = truckTypeCityDao.findNextOrderNo(truckTypeCity);
        if (null == nextTruckTypeCity) {
            throw new BusinessException("orderNumButtom", "errors.orderNumButtom");
        }

        Integer orderNo = truckTypeCity.getOrderNo();
        // 交换
        truckTypeCity.setOrderNo(nextTruckTypeCity.getOrderNo());
        nextTruckTypeCity.setOrderNo(orderNo);
        updateDataBase(truckTypeCity, loginUser);
        updateDataBase(nextTruckTypeCity, loginUser);
    }

    // 修改数据库
    private void updateDataBase(TruckTypeCity truckTypeCity, LoginUser loginUser) {
        truckTypeCity.setLastUpdateTime(new Date());
        truckTypeCity.setLastUpdateUserId(loginUser.getUserId());
        truckTypeCityDao.update(truckTypeCity);
    }
}
