package com.juma.tgm.region.service;

import com.juma.conf.domain.Region;
import com.juma.tgm.base.domain.RegionBo;
import com.juma.tgm.base.domain.RegionBo.SimpleRegionKey;
import com.juma.tgm.gaode.domain.AddressComponent;
import com.juma.tgm.waybill.domain.CityAdressData;

import java.util.List;

/**
 * 
 * @Description: 地区的处理
 * @author Administrator
 * @date 2016年5月24日 上午10:29:48
 * @version V1.0
 */

public interface RegionTgmService {

    /**
     * 
     * @Title: getRegionName @Description: 根据地区编码获取地区全名 @param
     * RegionCode @return String @throws
     */
    String findRegionNameByCode(String RegionCode);

    /**
     * 
     * @Title: getRegionCode @Description: 获取地区编码 @param regionId @return
     * String @throws
     */
    String findRegionCodeById(Integer regionId);

    /**
     * 
     * @Title: getRegionIdList @Description: 根据地区编码获取地区集合 @param
     * RegionCode @return List<RegionBo> @throws
     */
    List<RegionBo> listRegionBoByCode(String RegionCode);

    /**
     * 
     * @Description 根据地区编码获取对应的地区名称
     * @author Libin.Wei
     * @Date 2017年3月31日 下午1:09:17
     * @param regionCode
     *            地区编码
     * @param simpleRegionKey
     *            要保留的地区名称:省、省市、市区、市、区，RegionBo.SimpleRegionKey
     * @return
     */
    String findSimpleRegionName(String regionCode, SimpleRegionKey simpleRegionKey);

    /**
     * 
     * 根据坐标获取地区编码
     * 
     * @author Libin.Wei
     * @Date 2017年5月3日 上午10:49:11
     * @param coordinate
     *            坐标
     * @return regionCode
     */
    String findRegionCodeByCoordinate(String coordinate);

    /**
     * 
     * 根据省或省市或省市区的中文名获取获取地区编码
     * 
     * @author Libin.Wei
     * @Date 2017年5月3日 上午10:49:49
     * @param addressComponent
     * @return
     */
    String findRegionCodeBy(AddressComponent addressComponent);

    /**
     * 
     * @Title: buildSpecialCity
     * @Description: 处理直辖市等特殊城市的名字
     * @param region
     *            城市信息
     * @return String
     */
    String buildSpecialCity(Region region);

    /**
     * 
     * @Title: getRegionBo
     * @Description: 根据地区编码获取地区集合
     * @param regionCode
     * @return RegionBo
     */
    RegionBo getRegionBo(String regionCode);

    /**
     * 
     * @Description 请写注释...
     * @author Libin.Wei
     * @Date 2017年2月16日 下午2:14:10
     * @param regionCode
     *            地区编码
     * @param detailAddress
     *            详细地址
     * @return
     */
    String buildDetailAddress(String regionCode, String detailAddress);

    /**
     * 
     * @Description 根据地址信息获取城市编码
     * @author Libin.Wei
     * @Date 2017年3月7日 下午2:34:43
     * @param cityAdress
     *            地址信息
     * @return
     */
    String findRegionCodeByAddress(CityAdressData cityAdress);

    /**
     * 
     * 根据省份城市名称查询
     * 
     * @author Libin.Wei
     * @Date 2017年11月21日 下午5:49:44
     * @param province
     * @param city
     * @return
     */
    Region findByProvinceAndCityName(String province, String city);


    /**
     * 通过code获取区域信息
     * @param cityAdress
     * @return
     */
    Region findRegionByAddress(CityAdressData cityAdress);
}
