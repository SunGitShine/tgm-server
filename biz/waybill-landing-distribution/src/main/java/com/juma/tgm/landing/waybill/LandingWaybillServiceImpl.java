package com.juma.tgm.landing.waybill;

import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.conf.service.BusinessAreaService;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.base.domain.RegionBo;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.landingWaybill.domain.DeliveryAddress;
import com.juma.tgm.landingWaybill.domain.LandingWaybill;
import com.juma.tgm.landingWaybill.service.LandingWaybillService;
import com.juma.tgm.region.service.RegionTgmService;
import com.juma.tgm.tools.service.BusinessAreaCommonService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName LandingWaybillServiceImpl.java
 * @Description 落地配运单
 * @author Libin.Wei
 * @Date 2017年11月22日 下午2:33:46
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Service
public class LandingWaybillServiceImpl implements LandingWaybillService {

    @Resource
    private WaybillCommonService waybillCommonService;
    @Resource
    private TruckRequireService truckRequireService;
    @Resource
    private WaybillDeliveryAddressService waybillDeliveryAddressService;
    @Resource
    private WaybillReceiveAddressService waybillReceiveAddressService;
    @Resource
    private RegionTgmService regionTgmService;
    @Resource
    private WaybillService waybillService;
    @Resource
    private BusinessAreaService businessAreaService;
    @Resource
    private BusinessAreaCommonService businessAreaCommonService;
    @Autowired
    private TruckTypeService truckTypeService;

    @Override
    public List<LandingWaybill> listLandingWatingReceiveWaybill(PageCondition pageCondition, LoginUser loginUser) {
        List<LandingWaybill> result = new ArrayList<LandingWaybill>();
        if (null == loginUser || null == loginUser.getTenantId()) {
            return result;
        }

        pageCondition.getFilters().put("status", Waybill.Status.NO_DRIVER_ANSWER.getCode());
        pageCondition.getFilters().put("statusView", Waybill.StatusView.WATING_RECEIVE.getCode());
        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());
        List<Waybill> list = waybillCommonService.search(pageCondition);
        for (Waybill waybill : list) {
            LandingWaybill lw = new LandingWaybill();
            lw.setWaybillId(waybill.getWaybillId());
            // 取货地
            buildReceiveAddress(lw, waybill.getWaybillId());
            if (null != waybill.getEntryLicense()) {
                lw.setEnterCity(waybill.getEntryLicense() == 0 ? false : true);
            }

            TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), loginUser);
            if (null != truckRequire) {
                lw.setColdChainCar(truckRequire.isColdChain());
            }

            result.add(lw);
        }

        return result;
    }

    @Override
    public LandingWaybill getLandingWaybill(int waybillId, LoginUser loginUser) {
        Waybill waybill = waybillCommonService.getWaybillById(waybillId);
        if (null == waybill) {
            return null;
        }

        LandingWaybill lw = new LandingWaybill();
        lw.setWaybillId(waybillId);
        lw.setWaybillNo(waybill.getWaybillNo());
        lw.setCustomerName(waybill.getCustomerName());
        lw.setBusinessBranch(waybill.getBusinessBranch());
        lw.setVehicleBoxType(waybill.getVehicleBoxType());
        lw.setPlanDeliveryDate(DateUtil.format(waybill.getPlanDeliveryTime(), DateUtil.YYYYMMDDHHMM));
        // 业务区域
        lw.setAreaName(businessAreaCommonService.loadPreAndSelfAreaName(waybill.getAreaCode(), loginUser));
        lw.setPlanDeliveryTime(waybill.getPlanDeliveryTime());
        lw.setCmEstimateFinishTime(waybill.getCmEstimateFinishTime());

        TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybillId, loginUser);
        if (null != truckRequire) {
            lw.setGoodsInfo(truckRequireService.getGoodsFullName(truckRequire, waybillId));
            lw.setTruckTypeId(truckRequire.getTruckTypeId());
            lw.setGoodsWeight(truckRequire.getGoodsWeight());
            lw.setTruckTypeName(truckTypeService.findTruckTypeNameByTypeId(truckRequire.getTruckTypeId()));
        }

        // 取货地
        buildReceiveAddress(lw, waybillId);
        // 配送地
        buildDeliveryAddress(lw, waybillId);

        if (null != waybill.getEntryLicense()) {
            lw.setEnterCity(waybill.getEntryLicense() == 0 ? false : true);
        }

        // 逻辑业务区域
        BusinessArea businessArea = businessAreaService.loadLogicBusinessArea(waybill.getAreaCode(), loginUser);
        if (null != businessArea) {
            lw.setLogicAreaCode(businessArea.getAreaCode());
            lw.setLogicAreaCodeName(businessArea.getAreaName());
        }

        // 是否预约单
        lw.setAppointmentWaybill(DateUtil.compare(new Date(), waybill.getPlanDeliveryTime(), DateUtil.YYYYMMDD) == -1);

        return lw;
    }

    // 取货地的处理
    private void buildReceiveAddress(LandingWaybill lw, int waybillId) {
        WaybillDeliveryAddress deliveryAddress = waybillDeliveryAddressService.findByWaybillId(waybillId);
        if (null == deliveryAddress) {
            return;
        }
        lw.setReceiveAddressCoordinate(deliveryAddress.getCoordinates());
        if (StringUtils.isNotBlank(deliveryAddress.getRegionCode())) {
            lw.setRegionCode(deliveryAddress.getRegionCode().length() > 5
                    ? deliveryAddress.getRegionCode().substring(0, 5) : deliveryAddress.getRegionCode());
        }

        String addressName = deliveryAddress.getAddressName();
        String name = "";
        if (StringUtils.isNotBlank(deliveryAddress.getRegionCode())) {
            name = regionTgmService.findSimpleRegionName(deliveryAddress.getRegionCode(),
                    RegionBo.SimpleRegionKey.PROVINCE_CITY);
        }

        if (StringUtils.isBlank(deliveryAddress.getAddressDetail())) {
            lw.setReceiveDetailAddress(addressName);
            return;
        }

        if (!deliveryAddress.getAddressDetail().startsWith(name)) {
            lw.setReceiveDetailAddress(addressName + "(" + deliveryAddress.getAddressDetail() + ")");
            return;
        }
        lw.setReceiveDetailAddress(
                addressName + "(" + deliveryAddress.getAddressDetail().substring(name.length()) + ")");
    }

    // 配送地的处理
    private void buildDeliveryAddress(LandingWaybill lw, int waybillId) {
        List<WaybillReceiveAddress> list = waybillReceiveAddressService.findAllByWaybillId(waybillId);
        if (list.isEmpty()) {
            return;
        }

        StringBuffer sf = new StringBuffer("");

        for (WaybillReceiveAddress r : list) {
            DeliveryAddress d = new DeliveryAddress();

            if (StringUtils.isNotBlank(r.getCoordinates())) {
                d.setCoordinates(r.getCoordinates());
                d.setAddressDetail(r.getAddressDetail());
                lw.getDeliveryAddressCoordinateList().add(d);
            }

            sf.append(r.getAddressName());
            String name = "";
            if (StringUtils.isNotBlank(r.getRegionCode())) {
                name = regionTgmService.findSimpleRegionName(r.getRegionCode(), RegionBo.SimpleRegionKey.PROVINCE_CITY);
            }

            if (StringUtils.isNotBlank(r.getAddressDetail()) && r.getAddressDetail().startsWith(name)) {
                sf.append("(").append(r.getAddressDetail().substring(name.length())).append(")");
            }
            sf.append("；").append("<br/>");
        }

        String str = sf.toString();
        if (StringUtils.isBlank(str)) {
            return;
        }

        if (str.endsWith("<br/>")) {
            lw.setDeliveryDetailAddress(str.substring(0, str.length() - 5));
        }
    }

    @Override
    public Page<LandingWaybill> listScatteredWaybill(PageCondition pageCondition, LoginEmployee loginEmployee) {
        if (null == pageCondition.getFilters().get("waybillIds")) {
            return new Page<LandingWaybill>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                    new ArrayList<LandingWaybill>());
        }

        String waybillids = pageCondition.getFilters().get("waybillIds").toString();
        String[] split = waybillids.split(",");

        List<Integer> listWaybillId = new ArrayList<Integer>();
        for (String id : split) {
            listWaybillId.add(Integer.parseInt(id));
        }

        pageCondition.getFilters().put("waybillIds", listWaybillId);

        Page<Waybill> page = waybillService.search(loginEmployee, pageCondition);

        List<LandingWaybill> result = new ArrayList<LandingWaybill>();

        for (Waybill w : page.getResults()) {
            LandingWaybill l = new LandingWaybill();
            l.setWaybillId(w.getWaybillId());
            l.setWaybillNo(w.getWaybillNo());
            l.setPlanDeliveryDate(DateUtil.format(w.getPlanDeliveryTime()));
            if (null != w.getEntryLicense()) {
                l.setEnterCity(w.getEntryLicense() == 0 ? false : true);
            }
            // 取货地
            buildReceiveAddress(l, w.getWaybillId());
            // 配送地
            buildDeliveryAddress(l, w.getWaybillId());
            result.add(l);
        }

        return new Page<LandingWaybill>(page.getPageNo(), page.getPageSize(), page.getTotal(), result);
    }

    @Override
    public void confirmReceipt(Integer waybillId, LoginUser loginUser) {
        Waybill waybill = waybillCommonService.getWaybillById(waybillId);
        if (null == waybill) {
            return;
        }

        if (null != waybill.getCustomerId() || null != waybill.getCustomerManagerId()) {
            return;
        }

        if (Waybill.StatusView.WATING_PAY.getCode() != waybill.getStatusView()) {
            return;
        }

        waybill.setStatus(Waybill.Status.PAIED.getCode());
        waybill.setStatusView(Waybill.StatusView.FINISH.getCode());

        waybillCommonService.update(waybill, loginUser);
    }

}
