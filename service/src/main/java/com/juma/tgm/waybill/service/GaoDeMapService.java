package com.juma.tgm.waybill.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.giants.common.exception.BusinessException;
import com.juma.tgm.gaode.domain.AmapGeoResponse;
import com.juma.tgm.gaode.domain.AmapRegeoResponse;
import com.juma.tgm.gaode.domain.DriverLocation;
import com.juma.tgm.gaode.domain.GaodeDistanceResponse;
import com.juma.tgm.gaode.domain.GaodeDistrictResponse;
import com.juma.tgm.gaode.domain.GaodeInputtipsResponse;
import com.juma.tgm.gaode.domain.GaodeKeywordsResponse;
import com.juma.tgm.gaode.domain.IpAddressComponent;
import com.juma.tgm.waybill.domain.CityAdressData;
import com.juma.tgm.waybill.domain.DistanceAndPriceData;

/**
 * @author rx
 * @version V1.0
 * @Description: 高德地图service
 * @date 2016/05/16 11:02
 */
public interface GaoDeMapService {

    /**
     * 根据名称得到经纬度
     * 
     * @param address
     * @param city
     *            城市名
     * @return String
     */
    String getLatLng(String address, String city);

    /**
     * 根据经纬度坐标查询线路信息
     * 
     * @param startCoordinates
     *            起点经纬度
     * @param endCoordinates
     *            终点经纬度
     * @return JSONObject
     */
    JSONObject getRouteInfoByLatLng(String startCoordinates, String endCoordinates);

    /**
     * 处理根据经纬度坐标查询线路距离
     * 
     * @param routeInfo
     *            根据经纬度坐标查询线路距离等信息
     * @return Map<String, Integer>
     */
    DistanceAndPriceData getRouteDistanceByLatLng(JSONObject routeInfo);

    /**
     * 根据名称得到经纬度
     * 地理编码适用：详细的结构化地址转换为高德经纬度坐标，支持名胜景区、标志性建筑物名称解析为高德经纬度坐标
     * 
     * @param address
     *            结构化地址信息(规则： 省+市+区+街道+门牌号)
     * @param city
     *            城市名
     * @return
     */
    AmapGeoResponse geocode(String address, String city);

    /**
     * 根据经纬度得到结构化地址信息
     * 逆地理编码适用：将经纬度转换为详细结构化的地址
     * 
     * @param startCoordinates
     *            经纬度坐标
     * @return AmapRegeoResponse
     */
    AmapRegeoResponse regeocode(String startCoordinates);

    /**
     * 关键字搜索
     * 适用：通过用户输入查询POI的关键字进行条件搜索
     * 
     * @param keywords
     *            查询关键字(规则： 多个关键字用“|”分割)
     * @param city
     *            城市名
     * @return
     */
    GaodeKeywordsResponse gaodeTips(String city, String keywords) throws BusinessException;

    /**
     * 输入提示
     * 适用：配合搜索服务-ID查询API使用（Place API）
     * 
     * @param keywords
     *            查询关键字
     * @param location
     *            坐标
     * @return
     */
    GaodeInputtipsResponse gaodeInputtips(String location, String keywords) throws BusinessException;

    /**
     * 行政区域查询
     * 适用：用户希望通过得到行政区域信息，进行开发工作
     * 
     * @param keywords
     *            查询关键字(规则：只支持单个关键词语搜索关键词支持：行政区名称、citycode、adcode)
     * @param level
     *            行政区划级别(country:国家
     *                      province:省份（直辖市会在province和city显示）
     *                      city:市（直辖市会在province和city显示）
     *                      district:区县
     *                      biz_area:商圈（强烈建议利用showbiz参数跳过）
     *                      street:街道)
     * @param subdistrict
     *            子级行政区(0：不返回下级行政区；
     *                     1：返回下一级行政区；
     *                     2：返回下两级行政区；
     *                     3：返回下三级行政区；)
     * @return GaodeDistrictResponse
     * @throws BusinessException
     */
    GaodeDistrictResponse gaodeDistrict(String keywords, String level, int subdistrict) throws BusinessException;

    /**
     * @Title: getDistance
     * 
     * 根据经纬度坐标查询距离
     * 
     * @param startCoordinates
     *            起始经纬度
     * @param endCoordinates
     *            终止经纬度
     * @return DistanceAndPriceData
     */
    DistanceAndPriceData getDistance(String startCoordinates, String endCoordinates);

    /**
     * @Title: getDistanceSimple
     * 
     * 根据经纬度坐标查询直线距离(只含有距离与预估时间)
     * 
     * @param startCoordinates
     *            起始经纬度
     * @param endCoordinates
     *            终止经纬度
     * @return DistanceAndPriceData
     */
    DistanceAndPriceData getDistanceSimple(String startCoordinates, String endCoordinates);

    /**
     * 
     * @Title: reportLocation
     * @Description: 上报位置
     * @param data json格式 
     * @return void
     * @throws BusinessException
     */
    @Deprecated
    void reportLocation(DriverLocation data) throws BusinessException;

    List<DriverLocation> searchLocation(String city, String location);

    /**
     * 
     * @Title: getDistanceInfo
     * @Description: 获取路程、坐标等信息
     * @param formAddress
     *            出发地
     * @param toAddresss
     *            目的地
     * @param city
     *            所在城市
     * @return DistanceAndPriceData
     */
    DistanceAndPriceData getDistanceInfo(CityAdressData formAddress, List<CityAdressData> toAddresss);

    /**
     * 
     * @Description 坐标转换
     * @author Libin.Wei
     * @Date 2017年2月23日 下午5:53:27
     * @param coordinate 原始坐标
     * @param coordsys 原始坐标的坐标系类别Constants.Coordsys
     * @return
     */
    String convertCoordinate(String coordinate, String coordsys);
    
    /**
     * 
     * @Title:       lineDistance   
     * @Description: 直线距离
     * @return:      GaodeDistanceResponse      
     * @throws
     */
    GaodeDistanceResponse lineDistance(String origins,String destination);

    /**
     * 
     * 根据IP获取城市信息
     * @author Libin.Wei
     * @Date 2017年11月21日 下午5:19:10
     * @param ipAddress
     * @return
     */
    IpAddressComponent findRegionCodeByIpAddress(String ipAddress);
}
