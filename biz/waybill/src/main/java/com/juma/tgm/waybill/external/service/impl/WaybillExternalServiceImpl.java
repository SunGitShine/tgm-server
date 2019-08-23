package com.juma.tgm.waybill.external.service.impl;

import com.alibaba.fastjson.JSON;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.PageCondition;
import com.google.common.collect.Lists;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.external.domain.WaybillExternalExample;
import com.juma.tgm.external.service.WaybillExternalService;
import com.juma.tgm.imageUploadManage.domain.ImageUploadManage;
import com.juma.tgm.imageUploadManage.service.ImageUploadManageService;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.waybill.domain.CityAdressData;
import com.juma.tgm.waybill.domain.DistanceAndPriceData;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillOperateTrack;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateApplication;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateType;
import com.juma.tgm.waybill.external.WaybillExternalVo;
import com.juma.tgm.waybill.external.WaybillOperateTrackExternalVo;
import com.juma.tgm.waybill.service.GaoDeMapService;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillDeliveryAddressService;
import com.juma.tgm.waybill.service.WaybillOperateTrackService;
import com.juma.tgm.waybill.service.WaybillReceiveAddressService;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.tgm.waybill.vo.WaybillDeliveryAddressVo;
import com.juma.tgm.waybill.vo.WaybillToOmsOrder;
import com.juma.vms.driver.domain.Driver;
import com.juma.vms.vendor.domain.Vendor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @ClassName WaybillExternalServiceImpl.java
 * @Description 运单对外接口
 * @author Libin.Wei
 * @Date 2018年7月20日 上午11:30:48
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Service
public class WaybillExternalServiceImpl implements WaybillExternalService {

    private final Logger log = LoggerFactory.getLogger(WaybillExternalServiceImpl.class);

    @Value("${tms.base.img.url}")
    private String baseImgUrl;
    @Resource
    private GaoDeMapService gaoDeMapService;
    @Resource
    private WaybillService waybillService;
    @Resource
    private WaybillReceiveAddressService waybillReceiveAddressService;
    @Resource
    private WaybillDeliveryAddressService waybillDeliveryAddressService;
    @Resource
    private WaybillCommonService waybillCommonService;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private WaybillOperateTrackService waybillOperateTrackService;
    @Resource
    private VmsCommonService vmsCommonService;
    @Resource
    private ProjectService projectService;
    @Resource
    private ImageUploadManageService imageUploadManageService;
    @Resource
    private TruckRequireService truckRequireService;

    @Override
    public void updateWaybillDeliveryAddress(Integer waybillId, List<WaybillDeliveryAddressVo> waybillDeliveryAddressList, LoginUser loginUser) {
        if (waybillId == null || waybillDeliveryAddressList == null || waybillDeliveryAddressList.isEmpty()) {
            throw new BusinessException("argumentError","参数错误");
        }
        //删除老的配送地
        waybillReceiveAddressService.deleteByWaybillId(waybillId);

        CityAdressData formAddress = new CityAdressData();

        WaybillDeliveryAddress waybillDeliveryAddress = waybillDeliveryAddressService.findByWaybillId(waybillId);
        if (waybillDeliveryAddress == null) return;
        formAddress.setCoordinate(waybillDeliveryAddress.getCoordinates());

        List<CityAdressData> toAddressList = new ArrayList<>();
        List<WaybillReceiveAddress> receiveAddressList = new ArrayList<>();
        for ( WaybillDeliveryAddressVo waybillDeliveryAddressVo : waybillDeliveryAddressList ) {
            WaybillReceiveAddress waybillReceiveAddress = new WaybillReceiveAddress();
            waybillReceiveAddress.setCoordinates(waybillDeliveryAddressVo.getCoordinates());
            waybillReceiveAddress.setContactPhone(waybillDeliveryAddressVo.getContactPhone());
            waybillReceiveAddress.setContactName(waybillDeliveryAddressVo.getContactName());
            waybillReceiveAddress.setAddressDetail(waybillDeliveryAddressVo.getAddressDetail());
            waybillReceiveAddress.setAddressName(waybillDeliveryAddressVo.getAddressName());
            receiveAddressList.add(waybillReceiveAddress);

            CityAdressData cityAdressData = new CityAdressData();
            cityAdressData.setCoordinate(waybillDeliveryAddressVo.getCoordinates());
            toAddressList.add(cityAdressData);
        }
        if (receiveAddressList.isEmpty()) return;
        waybillReceiveAddressService.batchInsert(waybillId,receiveAddressList,loginUser);
        //计算距离

        //更新预估公里与预估时间
        DistanceAndPriceData distanceInfo = gaoDeMapService.getDistanceInfo(formAddress, toAddressList);
        if (distanceInfo == null) return;
        Waybill waybill = new Waybill();
        waybill.setWaybillId(waybillId);
        waybill.setEstimateDistance(distanceInfo.getDistance());
        waybill.setEstimateTimeConsumption(BaseUtil.strToNum(distanceInfo.getDuration()));
        waybillCommonService.update(waybill,loginUser);

    }

    @Override
    public List<String> receiptImgUrls(Integer waybillId) {
        if (waybillId == null) return Lists.newArrayList();
        List<ImageUploadManage> imageUploadManageList = imageUploadManageService.listByRelationIdAndSign(waybillId, ImageUploadManage.ImageUploadManageSign.NEED_RECEIPT.getCode());
        if (imageUploadManageList.isEmpty()) return Lists.newArrayList();
        List<String> imgUrls = new ArrayList<>();
        for ( ImageUploadManage imageUploadManage : imageUploadManageList ) {
            imgUrls.add(baseImgUrl+"/" + imageUploadManage.getImageUploadUrl());
        }
        return imgUrls;
    }

    @Override
    public WaybillToOmsOrder loadWaybillForOms(String waybillNo) {
        Waybill waybill = waybillService.findWaybillByWaybillNo(waybillNo, Constants.SYS_LOGIN_USER);
        if (waybill == null) return null;
        WaybillToOmsOrder waybillToOmsOrder = new WaybillToOmsOrder();
        waybillToOmsOrder.setWaybillId(waybill.getWaybillId());
        waybillToOmsOrder.setWaybillNo(waybill.getWaybillNo());
        waybillToOmsOrder.setTruckId(waybill.getTruckId());
        waybillToOmsOrder.setDriverId(waybill.getDriverId());
        waybillToOmsOrder.setProjectId(waybill.getProjectId());
        Project projectV2 = projectService.getProjectV2(waybill.getProjectId());
        if (projectV2 != null) {
            waybillToOmsOrder.setProjectNo(projectV2.getProjectNo());
        }
        waybillToOmsOrder.setPlanDeliveryTime(waybill.getPlanDeliveryTime());
        waybillToOmsOrder.setStatusView(waybill.getStatusView());
        return waybillToOmsOrder;
    }

    @Override
    public WaybillExternalVo loadWaybillByWaybillId(Integer waybillId) {
        if (null == waybillId) {
            return null;
        }

        Waybill waybill = waybillCommonService.getWaybillById(waybillId);
        if (null == waybill) {
            return null;
        }

        return new WaybillExternalVo(waybill);
    }

    @Override
    public int countWaybillByCustomer(WaybillExternalExample example) {
        log.info("WaybillExternalServiceImpl->countWaybill->example:{}", example.toString());
        if (null == example || null == example.getCrmCustomerId()) {
            return 0;
        }

        // 获取TMS对应的客户信息
        CustomerInfo customerInfo = customerInfoService.findByCrmId(example.getCrmCustomerId());
        if (null == customerInfo) {
            throw new BusinessException("customerInfoInTmsUndefound", "errors.customerInfoInTmsUndefind",
                    example.getCrmCustomerId());
        }

        // 组装条件
        PageCondition cond = new PageCondition();
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("customerId", customerInfo.getCustomerId());
        if (null != example.getTenantId()) {
            filters.put("tenantId", example.getTenantId());
        }

        if (null != example.getStartTime()) {
            filters.put("startTime", DateUtil.format(example.getStartTime()));
        }

        if (null != example.getEndTime()) {
            filters.put("endTime", DateUtil.format(example.getEndTime()));
        }

        if (CollectionUtils.isNotEmpty(example.getListStatusView())) {
            filters.put("statusViewList", example.getListStatusView());
        }
        cond.setFilters(filters);

        return waybillCommonService.searchCount(cond);
    }

    @Override
    public List<Waybill> listWaybillBy(PageCondition pageCondition, LoginUser loginUser) throws BusinessException {
        log.info("WaybillExternalServiceImpl->listWaybillBy->pageCondition:{}", JSON.toJSONString(pageCondition));
        List<Waybill> result = new ArrayList<Waybill>();
        if (null == pageCondition || null == loginUser || null == loginUser.getTenantId()) {
            return result;
        }

        Map<String, Object> filters = pageCondition.getFilters();
        if (null != filters) {
            if (null != filters.get("amsDriverId")) {
                com.juma.vms.driver.domain.Driver driver = vmsCommonService
                    .loadDriverByAmsDriverId(Integer.parseInt(filters.get("amsDriverId").toString()));
                if (null == driver) {
                    throw new BusinessException("driverInTmsUndefound", "errors.driverInTmsUndefound",
                            "ID【" + filters.get("amsDriverId") + "】");
                }
                filters.put("driverId", driver.getDriverId());
                filters.remove("amsDriverId");
            }

            if (null != filters.get("driverMobileNumber")) {
                Driver driver = vmsCommonService
                    .loadDriverByPhone(filters.get("driverMobileNumber").toString());
                if (null == driver) {
                    throw new BusinessException("driverInTmsUndefound", "errors.driverInTmsUndefound",
                            "手机号【" + filters.get("driverMobileNumber") + "】");
                }
                filters.put("driverId", driver.getDriverId());
                filters.remove("amsDriverId");
            }

            if (null != filters.get("crmCustomerId")) {
                CustomerInfo customerInfo = customerInfoService
                        .findByCrmId(Integer.parseInt(filters.get("crmCustomerId").toString()));
                if (null == customerInfo) {
                    throw new BusinessException("customerInfoInTmsUndefound", "errors.customerInfoInTmsUndefind",
                            filters.get("crmCustomerId").toString());
                }
                filters.put("customerId", customerInfo.getCustomerId());
                filters.remove("crmCustomerId");
            }
        }

        pageCondition.setPageNo(pageCondition.getPageNo() == null ? 1 : pageCondition.getPageNo());
        Integer pageSize = pageCondition.getPageSize() == null ? 15 : pageCondition.getPageSize();
        pageCondition.setPageSize(NumberUtils.compare(pageSize, 1000) == 1 ? 1000 : pageSize);
        pageCondition.setOrderBy(
                pageCondition.getOrderBy() == null ? " plan_delivery_time desc " : pageCondition.getOrderBy());
        log.info("WaybillExternalServiceImpl->listWaybillBy->has build pageCondition:{}",
                JSON.toJSONString(pageCondition));
        return waybillCommonService.search(pageCondition);
    }

    @Override
    public BigDecimal loadVendorUnReconciliationByPlateNumber(String plateNumber, LoginUser loginUser) {
        if (StringUtils.isBlank(plateNumber) || null == loginUser || null == loginUser.getTenantId()) {
            return null;
        }

        Vendor vendor = vmsCommonService.loadVendorByPlateNumber(plateNumber, loginUser);
        if (null == vendor) {
            return null;
        }

        return waybillCommonService.findUnReconciliationByVendorId(vendor.getVendorId());
    }

    @Override
    public List<WaybillOperateTrackExternalVo> listWaybillOperateTrackBy(Integer waybillId,
        OperateApplication operateApplication, OperateType operateType) {
        List<WaybillOperateTrackExternalVo> result = new ArrayList<>();
        if (null == waybillId) {
            return result;
        }

        List<WaybillOperateTrack> list = waybillOperateTrackService
            .listOperateTrackBy(waybillId, operateApplication, operateType);
        for (WaybillOperateTrack track : list) {
            result.add(new WaybillOperateTrackExternalVo(track));
        }

        return result;
    }

    @Override
    public List<WaybillDeliveryAddress> listWaybillDeliveryAddressByWaybillId(Integer waybillId) {
        if (null == waybillId) {
            return null;
        }

        WaybillDeliveryAddress address = new WaybillDeliveryAddress();
        address.setWaybillId(waybillId);
        return waybillDeliveryAddressService.selectEntryListByCondition(address);
    }

    @Override
    public List<WaybillReceiveAddress> listWaybillReceiveAddressByWaybillId(Integer waybillId) {
        if (null == waybillId) {
            return null;
        }

        return waybillReceiveAddressService.listSimpleReceiveAddressByWaybillId(waybillId);
    }

    @Override
    public TruckRequire loadTruckRequireByWaybillId(Integer waybillId) {
        if (null == waybillId) {
            return null;
        }
        
        return truckRequireService.loadReuckRequireByWaybillId(waybillId);
    }
}
