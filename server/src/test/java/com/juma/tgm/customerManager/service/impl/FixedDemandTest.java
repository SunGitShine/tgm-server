package com.juma.tgm.customerManager.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.giants.common.exception.BusinessException;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.customerManager.domain.FixedDemand;
import com.juma.tgm.customerManager.domain.FixedDemandDeliveryPoint;
import com.juma.tgm.customerManager.service.FixedDemandService;
import com.juma.tgm.customerManager.service.vo.FixedDemandVo;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import org.springframework.util.Assert;
import org.testng.annotations.Test;
import testng.BaseTestNGTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: FixedDemandTest
 * @Description:
 * @author: liang
 * @date: 2017-07-25 09:40
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class FixedDemandTest extends BaseTestNGTest {

    @Resource
    private FixedDemandService fixedDemandService;


    @Test
    public void getFixedDemandList() throws BusinessException {


//        return null;
    }

    @Test
    public void add() throws BusinessException {
        FixedDemandVo fixedDemandVo = new FixedDemandVo();
        FixedDemand demand = new FixedDemand();
        demand.setCustomerId(141);
        demand.setCustomerManagerId(87);
        demand.setEstimateFreight(new BigDecimal("520"));
        demand.setReceiptType(Waybill.ReceiptType.DRIVER_CHEQUES.getCode());
        demand.setRemark("这是一个固定需求");
        demand.setDeliveryTimePoint("8:00");
        demand.setVehicleCount(3);
        demand.setNeedDeliveryPointNote(1);

        TruckRequire require = new TruckRequire();
        require.setAdditionalFunctionIds("10,11");
        require.setGoodsVolume("1");
        require.setGoodsWeight("2");
        require.setTruckTypeId(5);

        demand.setRequireJson(JSONObject.toJSONString(require));

        List<FixedDemandDeliveryPoint> pointList = new ArrayList<>();
        //取货地
        FixedDemandDeliveryPoint begin = new FixedDemandDeliveryPoint();
        begin.setAddressDetail("详细的地址");
        begin.setAddressName("地址");
        begin.setCoordinates("104.171907,30.616085");
        begin.setStartPoint(1);
        begin.setRegionCode("22000");
        begin.setContactName("取货联系人");
        begin.setContactPhone("15882104186");

        pointList.add(begin);

        //送货地
        FixedDemandDeliveryPoint end = new FixedDemandDeliveryPoint();
        end.setAddressDetail("配送地详细的地址");
        end.setAddressName("配送地地址");
        end.setCoordinates("104.171907,30.616085");
        end.setRegionCode("22000");
        end.setContactName("收货联系人");
        end.setContactPhone("15882104186");
        end.setStartPoint(FixedDemandDeliveryPoint.PointType.delivery_address.getCode());

        pointList.add(end);

        fixedDemandVo.setFixedDemand(demand);

        List<FixedDemandDeliveryPoint> receive = new ArrayList<>();
        receive.add(begin);
        fixedDemandVo.setReceiveAddresses(receive);

        List<FixedDemandDeliveryPoint> deliveryPoints = new ArrayList<>();
        deliveryPoints.add(end);
        fixedDemandVo.setDeliveryAddresses(deliveryPoints);

        System.out.println(JSONObject.toJSONString(fixedDemandVo));


        //登录对象
        LoginEmployee loginEmployee = getLoginEmployee();
        fixedDemandService.add(fixedDemandVo, loginEmployee);

    }

    private LoginEmployee getLoginEmployee() {
        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setUserId(3230);
        return loginEmployee;
    }

    @Test
    public void del() throws BusinessException {

    }

    @Test
    public void update() throws BusinessException {
        FixedDemandVo vo = fixedDemandService.get(12);
        vo.getFixedDemand().setVehicleCount(3);
        fixedDemandService.update(vo, this.getLoginEmployee());
    }

    @Test
    public void get() throws BusinessException {

        FixedDemandVo vo = fixedDemandService.get(1);

        Assert.notNull(vo,"不能为空");
    }
}
