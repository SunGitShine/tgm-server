package com.juma.tgm.tools.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.giants.common.collections.CollectionUtils;
import com.giants.common.tools.Page;
import com.juma.auth.conf.domain.BusinessAreaNode;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.service.ConfParamService;
import com.juma.server.vm.domain.Driver;
import com.juma.server.vm.domain.dto.DriverQueryConditionDTO;
import com.juma.tgm.base.domain.MapViewDomain;
import com.juma.tgm.base.domain.RegionBo;
import com.juma.tgm.base.domain.ViewInfo;
import com.juma.tgm.basicTruckType.service.LocationService;
import com.juma.tgm.capacity.domian.vo.CapacityFilter;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.region.service.RegionTgmService;
import com.juma.tgm.tools.service.AmsCommonService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.user.domain.CurrentUser;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillMap;
import com.juma.tgm.waybill.domain.WaybillNav;
import com.juma.tgm.waybill.domain.map.BusinessInfo;
import com.juma.tgm.waybill.domain.map.MapBusinessInfo;
import com.juma.tgm.waybill.service.WaybillQueryService;
import com.juma.vms.transport.domain.CapacityPool;
import com.juma.vms.transport.external.CapacityPoolExternalQuery;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class LocationServiceImpl implements LocationService {

    private static final Logger log = LoggerFactory.getLogger(LocationServiceImpl.class);
    @Resource
    private WaybillQueryService waybillQueryService;
    @Resource
    private TruckService truckService;
    @Resource
    private RegionTgmService regionTgmService;
    @Resource
    private ConfParamService confParamService;
    @Resource
    private VmsCommonService vmsCommonService;
    @Resource
    private AmsCommonService amsCommonService;

    // 调用在途监控接口
    private MapViewDomain get(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }

        try {
            String ret = Request.Get(url).socketTimeout(5000).connectTimeout(4000).execute().returnContent().asString();
            log.info("调用在途监控接口返回数据：{}", ret);
            if (StringUtils.isBlank(ret)) {
                return null;
            }

            MapViewDomain view = JSONObject.parseObject(ret, MapViewDomain.class);
            if (view.getCode() == 0) {
                return view;
            }
        } catch (Exception e) {
            log.warn("调用在途监控接口失败", e);
        }
        return null;
    }

    @Override
    public MapBusinessInfo view(CurrentUser currentUser, LoginEmployee loginEmployee) {
        MapBusinessInfo info = new MapBusinessInfo();

        Set<String> areaCodeSet = new HashSet<String>();
        if (areaCodeSet.isEmpty()) {
            return info;
        }

        for (BusinessAreaNode areaNode : currentUser.getBusinessAreas()) {
            areaCodeSet.add(areaNode.getAreaCode());
        }

        QueryCond<CapacityFilter> queryCond = new QueryCond<>();
        CapacityFilter filter = new CapacityFilter();
        filter.setAreaCodeList(new ArrayList<String>(areaCodeSet));
        queryCond.setFilters(filter);
        queryCond.setPageNo(1);
        queryCond.setPageSize(Integer.MAX_VALUE);

        Page<CapacityPoolExternalQuery> page = vmsCommonService.seachCapacity(queryCond, loginEmployee);


        if (null == page || CollectionUtils.isEmpty(page.getResults())) {
            return info;
        }

        StringBuffer sf = new StringBuffer("");
        for (CapacityPoolExternalQuery c : page.getResults()) {
            sf.append(c.getPlateNumber()).append(",");
        }

        String plateNumbers = sf.toString();
        info.setPlateNumbers(
                StringUtils.isBlank(plateNumbers) ? null : plateNumbers.substring(0, plateNumbers.length() - 1));
        return info;
    }

    @Override
    public Map<String, Object> callback(String plateNumber, LoginUser loginUser) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            BusinessInfo info = new BusinessInfo();
            Truck truck = truckService.findTruckByPlateNumber(plateNumber);
            if (null == truck) {
                return result;
            }

            info.setPlateNumber(truck.getPlateNumber());
            CapacityPool capacityPool = vmsCommonService.loadCapacityByTruckId(truck.getTruckId(), loginUser);
            if (null == capacityPool) {
                return result;
            }

            com.juma.vms.driver.domain.Driver driver = vmsCommonService.loadDriverByDriverId(capacityPool.getDriverId());
            if (null != driver) {
                info.setDriverName(driver.getName());
                info.setDriverPhone(driver.getPhone());
            }

            if (null != driver.getAmsDriverId()) {
                DriverQueryConditionDTO dto = new DriverQueryConditionDTO();
                dto.setDriverId(driver.getAmsDriverId());
                dto.setTenantId(loginUser.getTenantId());
                dto.setTenantCode(loginUser.getTenantCode());
                Driver amsDriver = amsCommonService.findDriver(driver.getAmsDriverId(), loginUser);

                info.setParkingLocation(regionTgmService.findSimpleRegionName(amsDriver.getParkRegionCode(),
                        RegionBo.SimpleRegionKey.TOWN));
            }
            if (null != truck.getEntryLicense()) {
                ConfParamOption option = confParamService.findParamOption(Constants.ENTRY_CITY_LICENSE_TYPE,
                        truck.getEntryLicense().toString());
                info.setEntryLicense(option == null ? "无" : option.getOptionName());
            }

            String html = "<label>车牌号:</label><label>"
                    + (StringUtils.isBlank(info.getPlateNumber()) ? "" : info.getPlateNumber()) + "</label>";
            html += "<label style='margin-left: 25%;'>入城证:</label><label>"
                    + (StringUtils.isBlank(info.getEntryLicense()) ? "" : info.getEntryLicense()) + "</label><br/>";
            html += "<label>司机姓名:</label><label>"
                    + (StringUtils.isBlank(info.getDriverName()) ? "" : info.getDriverName()) + "</label><br/>";
            html += "<label>任务状态:</label><label>"
                    + (StringUtils.isBlank(info.getTaskStatus()) ? "" : info.getTaskStatus()) + "</label><br/>";
            html += "<label>停放区域:</label><label>"
                    + (StringUtils.isBlank(info.getParkingLocation()) ? "" : info.getParkingLocation()) + "</label>";
            try {
                WaybillNav waybillNav = waybillQueryService.findLastRunningWaybillByTruck(truck.getPlateNumber());
                if (waybillNav != null && waybillNav.getWaybillId() != null && waybillNav.getWaybillNo() != null) {
                    result.put("haveOrder", "<a href=\"/forward/pages/order_orbit/orderOrbit.html?waybillId="
                            + waybillNav.getWaybillId() + "&plateNumber=" + truck.getPlateNumber()
                            + "\" target=\"_blank\" type='button' class='btn btn-primary' style=\"margin-left: 140px;margin-right: 10px;display: inline-block;\">订单跟踪</a>");
                }
            } catch (Exception e) {

            }

            result.put("html", html);
            result.put("footer", buildFooter(plateNumber));
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return result;
    }

    // 组装footer
    private String buildFooter(String plateNumber) {
        List<WaybillMap> listWaybill = new ArrayList<WaybillMap>();
        listWaybill = waybillQueryService.findRunningWaybillByTruck(plateNumber);

        String footer = "<div class=\"btn-group btn-group-justified\" role=\"group\">";

        if (!listWaybill.isEmpty()) {
            WaybillMap wbm = listWaybill.get(0);
            if (wbm.getStatusView() == Waybill.StatusView.DELIVERYING.getCode()) { // 当为配送中时，增加订单跟踪按钮
                footer += "  <div class=\"btn-group\" role=\"group\">"
                        + "    <a href=\"/orderOrbit/init.html?waybillId=" + wbm.getWaybillId()
                        + "\" target=\"_blank\" class=\"btn btn-primary\">订单跟踪</a>" + "  </div>";
            }
        }
        String orbitURL = "<a href=\"/map/orbit.html?plateNumber=" + plateNumber
                + "\" target=\"_blank\" class=\"btn btn-primary\">轨迹回放</a>";
        footer += "  <div class=\"btn-group\" role=\"group\">" + orbitURL + "  </div></div>";
        return footer;
    }

    @Override
    public String findCoordinate(String deviceNo) {
        try {
            String url = Constants.MAP_VIEW_LOCATION;
            if (StringUtils.isBlank(deviceNo)) {
                return null;
            }

            url = url + "/location/lastStatus?deviceIds=" + deviceNo;
            MapViewDomain mapViewDomain = get(url);
            if (null == mapViewDomain) {
                return null;
            }

            List<ViewInfo> list = mapViewDomain.getData();
            if (CollectionUtils.isNotEmpty(list)) {
                ViewInfo info = list.get(0);
                if (null != info) {
                    return info.getLongitude() + "," + info.getLatitude();
                }
            }
        } catch (Exception e) {
            log.warn("单条调用在途监控接口失败", e);
        }
        return null;
    }
}
