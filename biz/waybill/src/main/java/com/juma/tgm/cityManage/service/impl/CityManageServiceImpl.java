package com.juma.tgm.cityManage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.giants.common.collections.CollectionUtils;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.conf.domain.Region;
import com.juma.conf.service.RegionService;
import com.juma.tgm.cityManage.dao.CityManageDao;
import com.juma.tgm.cityManage.domain.CityManage;
import com.juma.tgm.cityManage.domain.CityManageBo;
import com.juma.tgm.cityManage.domain.CityManageInfo;
import com.juma.tgm.cityManage.service.CityManageService;
import com.juma.tgm.region.service.RegionTgmService;

@Service
public class CityManageServiceImpl implements CityManageService {

    @Resource
    private RegionService regionService; // 根据市id取得传过来的市 名及其父id 再取得省份名
    @Resource
    private CityManageDao cityManageDao;
    @Resource
    private RegionTgmService regionTgmService;

    @Override
    public Page<CityManage> searchDetails(PageCondition pageCondition, LoginEmployee loginEmployee) {
        Integer pageNo = pageCondition.getPageNo();
        Integer pageSize = pageCondition.getPageSize();
        int temp = 0;
        int total = cityManageDao.searchCount(pageCondition);
        List<CityManage> rows = cityManageDao.search(pageCondition);
        if (CollectionUtils.isNotEmpty(rows)) {
            for (CityManage cityManage : rows) {
                temp++;
                cityManage.setOrderNo(
                        (pageNo - 1) < 0 ? (pageNo * pageSize + temp) : ((pageNo - 1) * pageSize + temp));
            }
        }
        return new Page<CityManage>(pageNo, pageSize, total, rows);
    }

    @Override
    public void insert(CityManageBo cityManageBo, LoginEmployee loginEmployee) throws BusinessException {
        if (null != cityManageBo) {
            CityManage cityManage = cityManageBo.getCityManage();
            Integer citySign = cityManage.getCitySign();
            if (null != citySign && (citySign.equals(CityManage.Sign.START_FROM.getCode())
                    || citySign.equals(CityManage.Sign.END_FORM.getCode()))) {
                cityManage.setCitySign(citySign);
            }
            Integer regionId = cityManageBo.getRegionId();
            Region region = regionService.findByRegionId(regionId);
            if (null != region) {
                cityManage.setCityCode(region.getRegionCode());
                cityManage.setCityName(region.getRegionName());
                Integer parentRegionId = region.getParentRegionId();
                if (null != parentRegionId) {
                    Region parentRegion = regionService.findByRegionId(parentRegionId);
                    if (null != parentRegion) {
                        cityManage.setProvinceName(parentRegion.getRegionName());
                        cityManage.setProvinceCode(parentRegion.getRegionCode());
                        String cityName = regionTgmService.buildSpecialCity(region);
                        if (StringUtils.isNotBlank(cityName)) {
                            cityManage.setCityName(cityName);
                        }
                    }
                } else {
                    // 未选择市区
                    throw new BusinessException("cityIdNull", "cityManage.error.cityIdNull");
                }
            }
            cityManage.setOrderNo(findMaxOrderNo(citySign) + 1);
            check(cityManage);
            cityManage.setCreateUserId(loginEmployee.getUserId());
            cityManageDao.insert(cityManage);
        }
    }

    @Override
    public void insertArea(CityManageBo cityManageBo, LoginEmployee loginEmployee) throws BusinessException {
        if (null != cityManageBo) {
            CityManage cityManage = cityManageBo.getCityManage();
            Integer parentCityManageId = cityManage.getParentCityManageId();
            if (null != parentCityManageId && null != cityManageBo.getRegionId()) {
                Integer citySign = cityManage.getCitySign();
                if (null != citySign && (citySign.equals(CityManage.Sign.AREA_MANAGE.getCode()))) {
                    cityManage.setCitySign(citySign);
                }
                Integer regionId = cityManageBo.getRegionId();
                Region region = regionService.findByRegionId(regionId);
                if (null != region) {
                    cityManage.setCityCode(region.getRegionCode());
                    cityManage.setCityName(region.getRegionName());
                    Integer parentRegionId = region.getParentRegionId();
                    if (null != parentRegionId) {
                        Region parentRegion = regionService.findByRegionId(parentRegionId);
                        if (null != parentRegion) {
                            cityManage.setProvinceName(parentRegion.getRegionName());
                            cityManage.setProvinceCode(parentRegion.getRegionCode());
                            String cityName = regionTgmService.buildSpecialCity(region);
                            if (StringUtils.isNotBlank(cityName)) {
                                cityManage.setCityName(cityName);
                            }
                        }
                    } else {
                        // 未选择市区
                        throw new BusinessException("cityIdNull", "cityManage.error.cityIdNull");
                    }
                }
                cityManage.setOrderNo(findMaxOrderNo(citySign) + 1);
            }
            checkArea(cityManage);
            cityManage.setCreateUserId(loginEmployee.getUserId());
            cityManageDao.insert(cityManage);
        }

    }

    // 启用
    @Override
    public void updateToEnable(Integer cityManageId, LoginEmployee loginEmployee) throws BusinessException {
        enableOrDisable(cityManageId, false, loginEmployee.getUserId());
    }

    // 禁用
    @Override
    public void updateToDisable(Integer cityManageId, LoginEmployee loginEmployee) throws BusinessException {
        enableOrDisable(cityManageId, true, loginEmployee.getUserId());

    }

    // 启用或停用
    private void enableOrDisable(Integer cityManageId, boolean flag, Integer userId) {
        CityManage cityManage = new CityManage();
        cityManage.setCityManageId(cityManageId);
        cityManage.setDelete(flag);
        cityManage.setLastUpdateUserId(userId);
        cityManageDao.update(cityManage);
    }

    // 判断保存同sign记录是否重复
    private void check(CityManage cityManage) {
        CityManage city = new CityManage();
        city.setCityCode(cityManage.getCityCode());
        city.setCitySign(cityManage.getCitySign());
        List<CityManage> manage = cityManageDao.findByExample(city);
        if (CollectionUtils.isNotEmpty(manage)) {
            throw new BusinessException("cityIdMultiple", "cityManage.error.cityIdMultiple");
        }
        city.setCitySign(CityManage.Sign.AREA_MANAGE.getCode());
        List<CityManage> regionManage = cityManageDao.findByExample(city);
        if (CollectionUtils.isEmpty(regionManage)) {
            throw new BusinessException("cityNotExist", "cityManage.error.cityNotExist");
        }
    }

    // 判断保存同sign记录是否重复
    private void checkArea(CityManage cityManage) {
        Integer parentCityManageId = cityManage.getParentCityManageId();
        if (null != parentCityManageId) {
            if (parentCityManageId > 0) {
                CityManage city = new CityManage();
                city.setCityCode(cityManage.getCityCode());
                city.setCitySign(cityManage.getCitySign());
                List<CityManage> manage = cityManageDao.findByExample(city);
                if (CollectionUtils.isNotEmpty(manage)) {
                    throw new BusinessException("cityIdMultiple", "cityManage.error.cityIdMultiple");
                }
            } else {
                String cityName = cityManage.getCityName();
                if (StringUtils.isBlank(cityName)) {
                    throw new BusinessException("largeAreaNameNotNull", "cityManage.error.largeAreaNameNotNull");
                }
                cityManage.setCityName(cityName.trim());
                List<CityManage> list = cityManageDao.findByExample(cityManage);
                if (CollectionUtils.isNotEmpty(list)) {
                    throw new BusinessException("largeAreaNameExist", "cityManage.error.largeAreaNameExist");
                }
            }
        }
    }

    @Override
    public CityManageInfo getCityList(Integer citySign) {
        CityManageInfo info = new CityManageInfo();
        CityManage cityManage = new CityManage();
        cityManage.setCitySign(citySign);
        cityManage.setDelete(false);
        info.setCityManageList(cityManageDao.findByExample(cityManage));
        return info;
    }

    private Integer findMaxOrderNo(Integer citySign) {
        Integer oederNo = cityManageDao.findMaxOederNo(citySign);
        if (null != oederNo) {
            return oederNo;
        }
        return 0;
    }

    @Override
    public void updateToUp(Integer cityManageId, LoginEmployee loginEmployee) throws BusinessException {
        CityManage manage = findOne(cityManageId);
        if (null != manage) {
            Integer orderNo = manage.getOrderNo();
            if (null != orderNo && orderNo.equals(1)) {
                throw new BusinessException("orderNumTop", "errors.orderNumTop");
            }
            CityManage cityManage = findByParam(manage.getCitySign(), (orderNo - 1));
            if (null != cityManage) {
                transforManage(manage, cityManage.getOrderNo(), loginEmployee);
                transforManage(cityManage, orderNo, loginEmployee);
            }
        }
    }

    @Override
    public void updateToDown(Integer cityManageId, LoginEmployee loginEmployee) throws BusinessException {
        CityManage manage = findOne(cityManageId);
        if (null != manage) {
            Integer orderNo = manage.getOrderNo();
            if (null != orderNo && orderNo.equals(findMaxOrderNo(manage.getCitySign()))) {
                throw new BusinessException("orderNumButtom", "errors.orderNumButtom");
            }
            CityManage cityManage = findByParam(manage.getCitySign(), (orderNo + 1));
            if (null != cityManage) {
                transforManage(manage, cityManage.getOrderNo(), loginEmployee);
                transforManage(cityManage, orderNo, loginEmployee);
            }
        }
    }

    @Override
    public CityManage findOne(Integer cityManageId) {
        if (null != cityManageId) {
            return cityManageDao.get(cityManageId);
        }
        return null;
    }

    private CityManage findByParam(Integer citySign, Integer orderNo) {
        CityManage cityManage = new CityManage();
        cityManage.setCitySign(citySign);
        cityManage.setOrderNo(orderNo);
        List<CityManage> list = cityManageDao.findByExample(cityManage);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    // 移动位置
    private void transforManage(CityManage manage, Integer orderNo, LoginEmployee loginEmployee) {
        manage.setOrderNo(orderNo);
        update(manage, loginEmployee);
    }

    @Override
    public void update(CityManage cityManage, LoginEmployee loginEmployee) {
        cityManage.setLastUpdateUserId(loginEmployee.getUserId());
        cityManageDao.update(cityManage);
    }

    @Override
    public List<CityManage> getParaentManageList() {
        CityManage cityManage = new CityManage();
        cityManage.setParentCityManageId(0);
        cityManage.setCitySign(CityManage.Sign.AREA_MANAGE.getCode());
        return cityManageDao.findByExample(cityManage);
    }

    @Override
    public List<String> findCityCodeByIds(List<Integer> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            return cityManageDao.findCityCodeByIds(list);
        }
        return null;
    }

    @Override
    public CityManageInfo getProvinceList(Integer citySign, LoginEmployee loginEmployee) {
        CityManageInfo info = new CityManageInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("provinceListFlag", true);
        map.put("citySign", citySign);
        info.setCityManageList(cityManageDao.getCityManageListByMap(map));
        return info;
    }

    @Override
    public List<CityManage> findCityBy(Integer citySign) {
        return cityManageDao.findBySign(citySign);
    }

    @Override
    public CityManageInfo getCityList(CityManage cityManage) {
        CityManageInfo info = new CityManageInfo();
        if (StringUtils.isNotBlank(cityManage.getProvinceCode())) {
            info.setCityManageList(cityManageDao.findByExample(cityManage)); 
        }
        return info;
    }

    @Override
    public CityManage findCityManageByCityCode(Integer citySign, String cityCode) {
        CityManage cityManage = new CityManage();
        cityManage.setCitySign(citySign);
        cityManage.setCityCode(cityCode);
        List<CityManage> list = cityManageDao.findByExample(cityManage);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

}
