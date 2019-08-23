package com.juma.tgm.exterl.service.impl;

import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.base.domain.BaseEnumDomian;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.external.CustomerInfoExternal;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.external.domain.ReconcilicationForPayableExternal;
import com.juma.tgm.external.domain.ReconcilicationForPayableExternalFilter;
import com.juma.tgm.external.service.TmsService;
import com.juma.tgm.fms.domain.v3.ReconcilicationForPayable;
import com.juma.tgm.fms.service.v3.ReconcilicationForPayableService;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.truck.enumeration.QueryTypeEnum;
import com.juma.tgm.truck.external.TruckTypeExternalFilter;
import com.juma.tgm.truck.external.TruckTypeExternalVo;
import com.juma.tgm.truck.service.TruckTypeService;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TmsServiceImpl implements TmsService {


    private final Logger log = LoggerFactory.getLogger(TmsServiceImpl.class);
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private ReconcilicationForPayableService reconcilicationForPayableService;
    @Resource
    private TruckTypeService truckTypeService;

    @Override
    public List<ReconcilicationForPayableExternal> listReconcilicationForPayableByNo(ReconcilicationForPayableExternalFilter filter) {
        List<ReconcilicationForPayableExternal> result = new ArrayList<>();
        if (null == filter || CollectionUtils.isEmpty(filter.getListReconcilicationNo())) {
            return result;
        }

        for (String reconcilicationNo : filter.getListReconcilicationNo()) {
            if (StringUtils.isBlank(reconcilicationNo)) {
                continue;
            }

            ReconcilicationForPayable reconciliation = reconcilicationForPayableService.findByReconciliationNo(reconcilicationNo);
            if (null == reconciliation) {
                continue;
            }

            ReconcilicationForPayableExternal external = new ReconcilicationForPayableExternal();
            BeanUtils.copyProperties(reconciliation, external);

            if (!filter.isNeedCrmCustomerId()) {
                result.add(external);
                continue;
            }

            CustomerInfo customerInfo = customerInfoService.findCusInfoById(reconciliation.getCustomerId());
            if (null == customerInfo) {
                result.add(external);
                continue;
            }

            external.setCrmCustomerId(customerInfo.getCrmCustomerId());
            result.add(external);
        }

        return result;
    }

    @Override
    public List<TruckTypeExternalVo> listVehicleBoxTypeOrLengthByFilter(TruckTypeExternalFilter truckTypeExternalFilter,
        QueryTypeEnum queryTypeEnum, LoginUser loginUser) {
        List<TruckTypeExternalVo> result = new ArrayList<>();
        if (null == queryTypeEnum || null == loginUser || null == loginUser.getTenantId()) {
            return result;
        }

        List<TruckType> list = null;
        if (queryTypeEnum.equals(QueryTypeEnum.TRUCK_TYPE)) {
            list = truckTypeService.listAllTruckTypeSimpleByOrderNoAsc(loginUser.getTenantId(), false);
        } else if (queryTypeEnum.equals(QueryTypeEnum.VEHICLE_BOX_TYPE)) {
            list = truckTypeService.listVehicleBoxTypeByTenant(loginUser);
        } else if (queryTypeEnum.equals(QueryTypeEnum.VEHICLE_BOX_LENGTH)) {
            if (null == truckTypeExternalFilter || null == truckTypeExternalFilter.getVehicleBoxType()) {
                return result;
            }

            list = truckTypeService.listVehicleBoxLengthBytypeAndTenant(truckTypeExternalFilter.getVehicleBoxType()
                , loginUser);
        } else {
            return result;
        }

        // 厢型
        Map<Integer, String> mapBoxType = this.mapPropertyValue(truckTypeService.listVehicleBoxType());
        // 厢长
        Map<Integer, String> mapBoxLength = this.mapPropertyValue(truckTypeService.listVehicleBoxlength());

        for (TruckType t : list) {
            TruckTypeExternalVo vo = new TruckTypeExternalVo();
            vo.setTruckTypeId(t.getTruckTypeId());
            vo.setVehicleBoxType(t.getVehicleBoxType());
            vo.setVehicleBoxLenght(t.getTruckLengthId());
            vo.setVehicleBoxTypeName(mapBoxType.get(vo.getVehicleBoxType()));
            vo.setVehicleBoxLenghtName(mapBoxLength.get(vo.getVehicleBoxLenght()));
            result.add(vo);
        }

        return result;
    }


    // 获取厢型或厢长基础数据，并组装为map
    private Map<Integer, String> mapPropertyValue(List<BaseEnumDomian> list) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (BaseEnumDomian d : list) {
            map.put(d.getCode(), d.getDesc());
        }

        return map;
    }

    public CustomerInfoExternal loadCustomerInfoByTmsCustomerId(Integer customerId) {
        if (null == customerId) {
            return  null;
        }

        CustomerInfo customerInfo = customerInfoService.findCusInfoById(customerId);
        if (null == customerInfo) {
            return null;
        }

        CustomerInfoExternal info = new CustomerInfoExternal();
        BeanUtils.copyProperties(customerInfo, info);
        return info;
    }
}
