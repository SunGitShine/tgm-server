package com.juma.tgm.waybill.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author rx
 * @version V1.0
 * @Description: 地图service
 * @date 2016/05/16  11:02
 */
public interface BaiDuMapService {

    /**
     * 根据名称得到经纬度
     * @param address
     * @param city 城市名
     * @return
     */
    Map<String,String> getLatLng(String address,String city);

    /**
     * 根据经纬度坐标查询线路信息
     * @param srcLat
     * @param srcLng
     * @param toLat
     * @param toLng
     * @param originRegion 起点城市
     * @param destinationRegion 终点城市
     * @return
     */
    JSONObject getRouteInfoByLatLng(String srcLat,String srcLng,String toLat,String toLng,String originRegion,String destinationRegion);

    /**
     * 根据经纬度坐标查询线路距离
     * @param srcLat
     * @param srcLng
     * @param toLat
     * @param toLng
     * @param originRegion 起点城市
     * @param destinationRegion 终点城市
     * @return
     */
    Integer getRouteDistanceByLatLng(String srcLat,String srcLng,String toLat,String toLng,String originRegion,String destinationRegion);

    /**
     * 根据名称得到线路信息
     * @param srcAddress 源地址
     * @param toAddress  目的地址
     * @param originRegion 起点城市
     * @param destinationRegion 终点城市
     * @return
     */
    JSONObject getRouteInfoByAddress(String srcAddress,String toAddress,String originRegion,String destinationRegion);
}
