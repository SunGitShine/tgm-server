package com.juma.tgm.external.service.impl;

import com.alibaba.fastjson.JSON;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.external.domain.ReconcilicationForPayableExternal;
import com.juma.tgm.external.domain.ReconcilicationForPayableExternalFilter;
import com.juma.tgm.external.service.TmsService;
import com.juma.tgm.external.service.WaybillExternalService;
import com.juma.tgm.truck.enumeration.QueryTypeEnum;
import com.juma.tgm.truck.external.TruckTypeExternalFilter;
import com.juma.tgm.truck.external.TruckTypeExternalVo;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.external.WaybillExternalVo;
import com.juma.tgm.waybill.external.WaybillOperateTrackExternalVo;
import java.math.BigDecimal;
import org.testng.annotations.Test;
import testng.BaseTestNGTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class TmsServiceImplTest extends BaseTestNGTest {

    @Resource
    private TmsService tmsService;
    @Resource
    private WaybillExternalService waybillExternalService;

    @Test
    public void listReconcilicationForPayableByNo() {
        ReconcilicationForPayableExternalFilter filter = new ReconcilicationForPayableExternalFilter();
        List<String> listReconcilicationNo = new ArrayList<>();
        listReconcilicationNo.add("AP20190219160832397001397");
        listReconcilicationNo.add("AP20190212193035331001396");

        filter.setListReconcilicationNo(listReconcilicationNo);
        filter.setNeedCrmCustomerId(true);
        List<ReconcilicationForPayableExternal> list = tmsService.listReconcilicationForPayableByNo(filter);

        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void loadVendorUnReconciliationByPlateNumber() {
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(1);
        loginUser.setTenantId(2);
        loginUser.setTenantCode("0000000000000");
        BigDecimal bigDecimal = waybillExternalService.loadVendorUnReconciliationByPlateNumber("陕A56QJ5", loginUser);
    }

    @Test
    public void listVehicleBoxTypeOrLengthByFilter() {
        TruckTypeExternalFilter filter = new TruckTypeExternalFilter();
        filter.setVehicleBoxType(31);

        List<TruckTypeExternalVo> truckTypeExternalVos = tmsService
            .listVehicleBoxTypeOrLengthByFilter(filter, QueryTypeEnum.VEHICLE_BOX_LENGTH, new LoginUser(19, 1));
        System.out.println(JSON.toJSONString(truckTypeExternalVos));
    }

    @Test
    public void loadByWaybillId() {
        WaybillExternalVo vo = waybillExternalService.loadWaybillByWaybillId(1757974);
    }

    @Test
    public void loadTrackByWaybillId() {
        List<WaybillOperateTrackExternalVo> list = waybillExternalService
            .listWaybillOperateTrackBy(1757974, null, null);
    }
}
