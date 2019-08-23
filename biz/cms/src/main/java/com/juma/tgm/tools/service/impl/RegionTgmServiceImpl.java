package com.juma.tgm.tools.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.giants.common.beanutils.BeanUtils;
import com.juma.conf.domain.Region;
import com.juma.conf.service.RegionService;
import com.juma.tgm.base.domain.RegionBo;
import com.juma.tgm.base.domain.RegionBo.SimpleRegionKey;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.Constants;
import com.juma.tgm.gaode.domain.AddressComponent;
import com.juma.tgm.gaode.domain.AmapRegeoResponse;
import com.juma.tgm.region.service.RegionTgmService;
import com.juma.tgm.waybill.domain.CityAdressData;
import com.juma.tgm.waybill.service.GaoDeMapService;

@Service
public class RegionTgmServiceImpl implements RegionTgmService {

    private static final Logger log = LoggerFactory.getLogger(RegionTgmServiceImpl.class);

    @Resource
    private RegionService regionService;
    @Resource
    private GaoDeMapService gaoDeMapService;

    @Override
    public String findRegionNameByCode(String RegionCode) {
        String regionName = "";
        List<Region> regionList = getRegionList(RegionCode);
        if (CollectionUtils.isEmpty(regionList)) {
            return regionName;
        }

        for (Region region : regionList) {
            regionName = regionName + region.getRegionName();
        }

        return regionName;
    }

    @Override
    public String findRegionCodeById(Integer regionId) {
        if (null == regionId) {
            return null;
        }

        Region region = regionService.findByRegionId(regionId);
        if (null == region) {
            return null;
        }

        return region.getRegionCode();
    }

    @Override
    public List<RegionBo> listRegionBoByCode(String RegionCode) {
        List<RegionBo> list = new ArrayList<RegionBo>();
        try {
            List<Region> allLevelsRegion = getRegionList(RegionCode);
            if (CollectionUtils.isEmpty(allLevelsRegion)) {
                return list;
            }

            for (Region region : allLevelsRegion) {
                if (null == region.getParentRegionId()) {
                    region.setParentRegionId(-1);
                }
                RegionBo regionBo = new RegionBo();
                BeanUtils.copyProperties(regionBo, region);
                list.add(regionBo);
            }
            return list;
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public String findSimpleRegionName(String regionCode, SimpleRegionKey simpleRegionKey) {
        String regionName = "";

        if (null == simpleRegionKey) {
            return regionName;
        }

        List<Region> regionList = getRegionList(regionCode);
        if (CollectionUtils.isEmpty(regionList)) {
            return regionName;
        }

        for (Region region : regionList) {
            if (RegionBo.SimpleRegionKey.PROVINCE.equals(simpleRegionKey)) {
                if (null == region.getParentRegionId() && !region.isLeaf()) {
                    regionName = region.getRegionName();
                }
            } else if (RegionBo.SimpleRegionKey.PROVINCE_CITY.equals(simpleRegionKey)) {
                if (!region.isLeaf()) {
                    regionName = regionName + region.getRegionName();
                }
            } else if (RegionBo.SimpleRegionKey.CITY.equals(simpleRegionKey)) {
                if (null != region.getParentRegionId() && !region.isLeaf()) {
                    regionName = region.getRegionName();
                }
            } else if (RegionBo.SimpleRegionKey.CITY_TOWN.equals(simpleRegionKey)) {
                if (null != region.getParentRegionId()) {
                    regionName = regionName + region.getRegionName();
                }
            } else if (RegionBo.SimpleRegionKey.TOWN.equals(simpleRegionKey)) {
                if (region.isLeaf()) {
                    regionName = region.getRegionName();
                }
            }
        }
        return regionName;
    }

    // 获取地区列表
    private List<Region> getRegionList(String regionCode) {
        try {
            if(StringUtils.isBlank(regionCode)){
                return new ArrayList<Region>();
            }
            return regionService.findAllLevelsRegion(regionCode);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return new ArrayList<Region>();
    }

    @Override
    public String findRegionCodeByCoordinate(String coordinate) {
        if (StringUtils.isBlank(coordinate)) {
            return Constants.DEFAULT_REGION_CODE;
        }

        AmapRegeoResponse regeocode = gaoDeMapService.regeocode(coordinate);
        if (null == regeocode) {
            return Constants.DEFAULT_REGION_CODE;
        }

        return findRegionCodeBy(regeocode.getRegeocode().getAddressComponent());
    }

    @Override
    public String findRegionCodeBy(AddressComponent addressComponent) {
        if (null == addressComponent) {
            return Constants.DEFAULT_REGION_CODE;
        }
        String province = addressComponent.getProvince();
        String city = addressComponent.getCity();
        String district = addressComponent.getDistrict();
        String specialCity = BaseUtil.buildSpecialCity(province);
        if (StringUtils.isNotBlank(specialCity)) {
            return specialCity;
        }

        Region provinceRegion = regionService.findByRegionName(null, addressComponent.getProvince());
        try {
            if (StringUtils.isBlank(city) || "[]".equals(city)) {
                List<Region> childRegion = regionService.findChildRegion(provinceRegion.getRegionId());
                if (CollectionUtils.isNotEmpty(childRegion)) {
                    Region districtRegion = regionService.findByRegionName(childRegion.get(0).getRegionId(), district);
                    return districtRegion == null ? Constants.DEFAULT_REGION_CODE : districtRegion.getRegionCode();
                }
            } else {
                Region cityRegion = regionService.findByRegionName(provinceRegion.getRegionId(), city);
                if(StringUtils.isNotBlank(district)){
                    Region districtRegion = regionService.findByRegionName(cityRegion.getRegionId(), district);
                    return districtRegion.getRegionCode();
                }
                return cityRegion.getRegionCode();
            }
        } catch (Exception e) {
            return Constants.DEFAULT_REGION_CODE;
        }
        return Constants.DEFAULT_REGION_CODE;
    }

    @Override
    public String buildSpecialCity(Region region) {
        String cityName = "";
        String regionCode = region.getRegionCode();
        if (regionCode.equals("00000") || regionCode.equals("01000") || regionCode.equals("08000")
                || regionCode.equals("21000") || regionCode.equals("32") || regionCode.equals("33")) {
            cityName = region.getRegionName();
        }
        return cityName;
    }

    @Override
    public RegionBo getRegionBo(String regionCode) {
        RegionBo bo = new RegionBo();
        List<RegionBo> list = listRegionBoByCode(regionCode);
        if (CollectionUtils.isNotEmpty(list)) {
            bo.setRegionId(list.get(list.size() - 1).getRegionId());
        }
        bo.setRegionIdList(list);
        return bo;
    }

    @Override
    public String buildDetailAddress(String regionCode, String detailAddress) {
        if (StringUtils.isNotBlank(regionCode) && StringUtils.isNotBlank(detailAddress)) {
            String regionName = findSimpleRegionName(regionCode, RegionBo.SimpleRegionKey.PROVINCE);
            if (StringUtils.isNotBlank(regionName)) {
                detailAddress = detailAddress.replaceAll(regionName, "");
            }
        }
        return detailAddress;
    }

    @Override
    public String findRegionCodeByAddress(CityAdressData cityAdress) {
        if (null == cityAdress) {
            return null;
        }
        String coordinate = cityAdress.getCoordinate();
        if (StringUtils.isNotBlank(coordinate)) {
            return findRegionCodeByCoordinate(coordinate);
        }
        String address = cityAdress.getAddress();
        String city = cityAdress.getCity();
        if (StringUtils.isNotBlank(address) && StringUtils.isNotBlank(city)) {
            return findRegionCodeByCoordinate(gaoDeMapService.getLatLng(address, city));
        }
        return null;
    }

    @Override
    public Region findByProvinceAndCityName(String province, String city) {
        if (StringUtils.isBlank(province) && StringUtils.isBlank(city)) {
            return null;
        }
        String specialCity = BaseUtil.buildSpecialCity(province);
        if (StringUtils.isNotBlank(specialCity)) {
            Region region = new Region();
            region.setRegionCode(specialCity);
            region.setRegionName(province);
            return region;
        }

        Region provinceRegion = regionService.findByRegionName(null, province);
        try {
            List<Region> childRegion = regionService.findChildRegion(provinceRegion.getRegionId());
            for (Region region : childRegion) {
                if (region.getRegionName().startsWith(city)) {
                    return region;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return provinceRegion;
    }

    @Override
    public Region findRegionByAddress(CityAdressData cityAdress){
        if (null == cityAdress) {
            return null;
        }
        String coordinate = cityAdress.getCoordinate();
        if (StringUtils.isNotBlank(coordinate)) {
            return findRegionByCoordinate(coordinate);
        }
        String address = cityAdress.getAddress();
        String city = cityAdress.getCity();
        if (StringUtils.isNotBlank(address) && StringUtils.isNotBlank(city)) {
            return findRegionByCoordinate(gaoDeMapService.getLatLng(address, city));
        }
        return null;
    }

    private Region findRegionByCoordinate(String coordinate) {
        if (StringUtils.isBlank(coordinate)) {
            return null;
        }

        AmapRegeoResponse regeocode = gaoDeMapService.regeocode(coordinate);
        if (null == regeocode) {
            return null;
        }

        return findRegionBy(regeocode.getRegeocode().getAddressComponent());
    }

    private Region findRegionBy(AddressComponent addressComponent) {
        if (null == addressComponent) {
            return null;
        }
        String province = addressComponent.getProvince();
        String city = addressComponent.getCity();
        String district = addressComponent.getDistrict();
        String specialCity = BaseUtil.buildSpecialCity(province);
        if (StringUtils.isNotBlank(specialCity)) {
            return null;
        }

        Region provinceRegion = regionService.findByRegionName(null, addressComponent.getProvince());
        try {
            if (StringUtils.isBlank(city) || "[]".equals(city)) {
                List<Region> childRegion = regionService.findChildRegion(provinceRegion.getRegionId());
                if (CollectionUtils.isNotEmpty(childRegion)) {
                    return regionService.findByRegionName(childRegion.get(0).getRegionId(), district);
                }
            } else {
                Region cityRegion = regionService.findByRegionName(provinceRegion.getRegionId(), city);
                Region districtRegion = regionService.findByRegionName(cityRegion.getRegionId(), district);

                return districtRegion;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
