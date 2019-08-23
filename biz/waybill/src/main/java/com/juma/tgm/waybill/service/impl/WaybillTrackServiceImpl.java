package com.juma.tgm.waybill.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.juma.auth.user.domain.LoginUser;
import com.juma.monitor.service.DeviceFilterService;
import com.juma.monitor.service.VehicleService;
import com.juma.monitor.truck.domain.vo.VehicleInfoVo;
import com.juma.tgm.common.Constants;
import com.juma.tgm.cron.vo.WaybillMileageBo;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillTrackService;
import com.juma.tgm.waybill.vo.TruckDeviceVo;
import com.juma.tgm.waybillLbsSource.domain.WaybillLbsSource;
import com.juma.tgm.waybillLbsSource.service.WaybillLbsSourceService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 *
 * 运单轨迹工具类
 * 
 * @ClassName: WaybillTrackUtils
 * @Description:
 * @author: liang
 * @date: 2017-07-18 14:35
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
@Service
public class WaybillTrackServiceImpl implements WaybillTrackService {

    private final static Logger log = LoggerFactory.getLogger(WaybillTrackServiceImpl.class);
    @Resource
    private DeviceFilterService deviceFilterService;

    @Resource
    private WaybillLbsSourceService waybillLbsSourceService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private TruckService truckService;

    @Resource
    private WaybillCommonService waybillCommonService;

    @Override
    public void changeActualMileage(WaybillMileageBo waybillMileageBo, LoginUser loginUser) {
        if (null == waybillMileageBo || null == waybillMileageBo.getWaybillId()) {
            return;
        }

        // 记录数据源
        insertLbsSource(waybillMileageBo);

        if (null == waybillMileageBo.getActualMileage()) {
            return;
        }

        Waybill waybill = new Waybill();
        waybill.setWaybillId(waybillMileageBo.getWaybillId());
        waybill.setActualMileage(waybillMileageBo.getActualMileage());
        waybillCommonService.update(waybill, loginUser);
    }

    // 记录计算实际里程数据源
    private void insertLbsSource(WaybillMileageBo waybillMileageBo) {
        if (null == waybillMileageBo || null == waybillMileageBo.getWaybillId()) {
            return;
        }

        if (StringUtils.isBlank(waybillMileageBo.getDeviceNo()) && null == waybillMileageBo.getDeviceType()) {
            return;
        }


        if (updateLbsSource(waybillMileageBo)) {
            return;
        }

        WaybillLbsSource waybillLbsSource = new WaybillLbsSource();
        waybillLbsSource.setWaybillId(waybillMileageBo.getWaybillId());
        waybillLbsSource.setSign(WaybillLbsSource.Sign.ACTUAL_MILEAGE.getCode());
        waybillLbsSource.setDeviceNo(waybillMileageBo.getDeviceNo());
        waybillLbsSource.setDeviceType(waybillMileageBo.getDeviceType());
        waybillLbsSourceService.insert(waybillLbsSource);
    }

    // 历史实际里程数据
    private boolean updateLbsSource(WaybillMileageBo waybillMileageBo) {
        WaybillLbsSource source = waybillLbsSourceService.findSourceByWaybillIdAndSign(waybillMileageBo.getWaybillId(),
                WaybillLbsSource.Sign.ACTUAL_MILEAGE.getCode());
        if (null == source) {
            return false;
        }

        source.setDeviceNo(waybillMileageBo.getDeviceNo());
        source.setDeviceType(waybillMileageBo.getDeviceType());

        waybillLbsSourceService.update(source);
        return true;
    }

    private TruckDeviceVo getLastDeviceTrack(Integer truckId) {
        // 通过订单号获取到仓设备
        if (null == truckId) {
            return null;
        }

        // 通过车辆id获取车牌号
        Truck truck = truckService.getTruck(truckId);
        if (truck == null) {
            return null;
        }

        // 通过车牌号获取设备号
        VehicleInfoVo vehicle = deviceFilterService.getVechicleByPlateNumber(truck.getPlateNumber());
        if (null == vehicle || StringUtils.isBlank(vehicle.getDeviceNo()) || null == vehicle.getDevice()
                || null == vehicle.getDevice().getType()) {
            return null;
        }

        TruckDeviceVo vo = new TruckDeviceVo();
        vo.setDeviceNo(vehicle.getDeviceNo());
        vo.setDeviceType(vehicle.getDevice().getType() == null ? null : (vehicle.getDevice().getType().intValue()));
        return vo;
    }

    /**
     * 获取运单运输距离
     */
    public Map<String, String> getActualMile(TruckDeviceVo truckDeviceVo, long startTime, long endTime) {
        if (null == truckDeviceVo) {
            return null;
        }

        // 拼接查询参数
        String device = truckDeviceVo.getDeviceNo();
        StringBuffer sb = new StringBuffer("/location/totalMileage?");
        sb.append("device=");
        try {
            sb.append(URLEncoder.encode(device, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.warn("获取总里程url编码错误.", e);
            return null;
        }
        sb.append("&");
        sb.append("startTime=");
        sb.append(startTime);
        sb.append("&");
        sb.append("endTime=");
        sb.append(endTime);
        URI uri = null;
        String rst = "";
        try {
            uri = new URI(Constants.MAP_VIEW_LOCATION + sb.toString());
            log.info("lbs-url:" + uri.toString());
            rst = Request.Get(uri).socketTimeout(5000).connectTimeout(4000).execute().returnContent().asString();
            log.info("response:" + rst);
        } catch (URISyntaxException ue) {
            log.warn("waybillActualMileageHttpConfigError", "errors.config", ue);
        } catch (IOException ie) {
            log.warn("waybillActualMileageHttpCallError", "errors.out.interface.call", ie);
        }

        Map<String, String> mapRst = null;
        try {
            mapRst = JSON.parseObject(rst, new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        if (MapUtils.isEmpty(mapRst)) {
            return null;
        }

        if (!NumberUtils.isNumber(mapRst.get("totalMileage"))) {
            return null;
        }
        return mapRst;
    }

    @Override
    public void getWaybillTraceInfo(Waybill waybill) {
        if (null == waybill) {
            return;
        }

        if (NumberUtils.compare(Waybill.Status.DELIVERYING.getCode(), waybill.getStatus()) != 0)
            return;

        // 获取已完成运单的设备信息
        TruckDeviceVo deviceVo = this.getLastDeviceTrack(waybill.getTruckId());

        // 若订单正在运输中，则使用当前时间
        waybill.setFinishTime(new Date());

        if (null == deviceVo) {
            waybill.setActualMileageErrorStr("设备信息为空");
            return;
        }

        // 如果订单表中到仓时间为空，则使用操作轨迹中的到仓时间
        if (null == waybill.getArriveDepotTime()) {
            waybill.setActualMileageErrorStr("到仓时间为空");
            return;
        }

        // 运输中的订单单独获取里程数
        Map<String, String> rstMap = this.getActualMile(deviceVo,
                waybill.getArriveDepotTime().getTime(), waybill.getFinishTime().getTime());
        if (MapUtils.isNotEmpty(rstMap)) {
            BigDecimal kilometers = NumberUtils.createBigDecimal(rstMap.get("totalMileage"));// 返回的是公里
            if (kilometers != null) {
                waybill.setActualMileage(kilometers.multiply(BigDecimal.valueOf(1000))
                        .setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
            }
        }

        waybill.setDeviceNo(deviceVo.getDeviceNo());
        waybill.setDeviceType(deviceVo.getDeviceType());
    }

    @Override
    public WaybillMileageBo getMileageInfo(Integer waybillId, LoginUser loginUser) {
        if (null == waybillId) {
            return null;
        }

        Waybill waybill = waybillCommonService.getWaybillById(waybillId);
        if (null == waybill) {
            return null;
        }

        TruckDeviceVo deviceVo = this.getLastDeviceTrack(waybill.getTruckId());
        // 若果参数为空则返回
        if (null == deviceVo) {
            return null;
        }
        WaybillMileageBo bo = new WaybillMileageBo();
        bo.setWaybillId(waybillId);
        bo.setDeviceNo(deviceVo.getDeviceNo());
        bo.setDeviceType(deviceVo.getDeviceType());

        Truck truck = truckService.getTruck(waybill.getTruckId());
        if (null == truck) {
            return bo;
        }


        // 到仓时间为空或1970年，使用用车时间为开始时间
        Date startTime = waybill.getArriveDepotTime();
        if (null == startTime) {
            startTime = waybill.getPlanDeliveryTime();
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startTime);
            int year = calendar.get(Calendar.YEAR);
            if (year < 2016) {
                startTime = waybill.getPlanDeliveryTime();
            }
        }

        log.info("请求wt获取实际里程的参数运单ID：" + waybill.getWaybillId() + ",PlateNumber:" + truck.getPlateNumber()
            + ",startTime:" + startTime + ",finishTime:" + waybill.getFinishTime());
        Double kilometers = vehicleService.queryOrbitsOdoByParam(truck.getPlateNumber(), startTime.getTime(),
            waybill.getFinishTime().getTime());
        log.info("请求wt获取的实际里程：{}", kilometers);
        if (null == kilometers) {
            return bo;
        }

        bo.setActualMileage(new BigDecimal(kilometers.toString()).intValue());
        return bo;
    }

    @Override
    public void syncChangeActualMileage(Integer waybillId, LoginUser loginUser) {
        WaybillMileageBo waybillMileageBo = this.getMileageInfo(waybillId, loginUser);
        this.changeActualMileage(waybillMileageBo, loginUser);
    }
}
