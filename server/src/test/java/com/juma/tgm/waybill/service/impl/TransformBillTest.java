package com.juma.tgm.waybill.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.authority.service.TgmUserCenterService;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.customer.domain.TruckCustomer;
import com.juma.tgm.filiale.service.FilialeBillService;
import com.juma.tgm.project.vo.ProjectBillVo;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillBo;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.vo.WaybillCarrierVo;
import com.juma.tgm.waybill.service.WaybillService;

import testng.BaseTestNGTest;

/**
 * @ClassName: TransformBillTest
 * @Description:
 * @author: liang
 * @date: 2018-09-01 16:39
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class TransformBillTest extends BaseTestNGTest {
    @Resource
    private WaybillService waybillService;

    @Resource
    private FilialeBillService filialeBillService;

    @Resource
    private TgmUserCenterService tgmUserCenterService;

    @Resource
    private CustomerInfoService customerInfoService;

    //专车转单
    public void zhuanche2xidiTest() {
        WaybillBo waybillBo = new WaybillBo();
        Waybill waybill = new Waybill();
        TruckRequire truckRequire = new TruckRequire();
        WaybillParam waybillParam = new WaybillParam();

    }

    //配送转单
    @Test
    public void project2Scattered() {
        String projectBillStr = "{\"waybill\":{\"truckCustomerId\":2411,\"customerId\":85899,\"planDeliveryTime\":\"2018-09-02 23:30:00\",\"receiptType\":3,\"receiveWay\":3," +
            "\"regionCode\":\"22000\",\"accountType\":1,\"businessBranch\":3,\"cmEstimateFinishTime\":\"2018-09-02 23:51:00\",\"waybillRemark\":\"转运单测试\",\"rebateRate\":null," +
            "\"needDeliveryPointNote\":0,\"onlyLoadCargo\":0},\"project\":{\"projectId\":197,\"projectTruck\":[]},\"truckRequire\":{\"goodsType\":\"粮油\",\"goodsVolume\":5," +
            "\"goodsWeight\":5,\"additionalFunctionIds\":\"12\",\"taxRateId\":40,\"truckTypeId\":36,\"vehicleBoxType\":null},\"deliveryAddress\":[{\"regionCode\":\"22000\"," +
            "\"addressName\":\"金都花园(蜀汉路)\",\"addressDetail\":\"四川省成都市金牛区金牛蜀汉中路526号\",\"coordinates\":\"104.012894,30.69187\"}],\"receiveAddress\":[{\"regionCode\":" +
            "\"22000\",\"addressName\":\"1+1超市(六十八分店)\",\"addressDetail\":\"万柳路飞扬发艺旁\",\"contactName\":\"\",\"contactPhone\":\"\",\"coordinates\":\"103.970505,30.659175\"}]," +
            "\"truckValidator\":[],\"waybillParam\":{\"agencyTakeFreight\":null,\"projectFreightRuleJson\":{\"projectFreightRuleId\":812,\"projectId\":197,\"truckTypeId\":36,\"factorJson\":" +
            "\"{\\\"initiateRate\\\":100,\\\"byDay\\\":50,\\\"byTime\\\":100}\"}},\"createBatchAmount\":2}";
        ProjectBillVo projectBillVo = JSONObject.parseObject(projectBillStr, ProjectBillVo.class);
        WaybillCarrierVo waybillCarrierVo = new WaybillCarrierVo();
        waybillCarrierVo.setBillTaxRate(new BigDecimal("0.1"));
        waybillCarrierVo.setSettlementType(WaybillCarrierVo.TransformCarrierSettlementType.SETTLEMENT_TYPE_FEE.getCode());
        waybillCarrierVo.setVendorFees(new BigDecimal("500"));
        waybillCarrierVo.setVendorId(3032);
        waybillCarrierVo.setCustomerId(61725);

        projectBillVo.getWaybill().setWaybillSource(Waybill.WaybillSource.JUMA_CLIENT.getCode());
        projectBillVo.getWaybill().setReceiveWay(Waybill.ReceiveWay.TRANSFORM_BILL.getCode());

        projectBillVo.setWaybillCarrierVo(waybillCarrierVo);
        LoginUser loginUser = new LoginUser(9, 13168);
        filialeBillService.createProjectBill(projectBillVo, loginUser);

    }
    //希地转单


    /**
     * 添加客户用车人关系
     */
    @Test
    public void addTruckCustomer() {
        TruckCustomer truckCustomer = new TruckCustomer();
        truckCustomer.setContactPhone("18502799427");
        truckCustomer.setNickname("吴建");
        CustomerInfo customerInfo = customerInfoService.findCusInfoById(61725);

        LoginUser loginUser = new LoginUser(3, 1);
        loginUser.setTenantCode("000000001");
        tgmUserCenterService.createCargoOwnerBelongEnterprise(truckCustomer, customerInfo, loginUser);
    }


    @Test
    public void projectBill2NoProjectBillTest() {
        String billString = "{\"createBatchAmount\":1,\"deliveryAddress\":[{\"addressDetail\":\"四川省成都市成华区青衣江路成都东站2F层\",\"addressName\":\"成都东站\",\"contactName\":\"何\",\"contactPhone\":\"18628271310\",\"coordinates\":\"104.141085,30.628884\",\"createTime\":1536143456000,\"delete\":false,\"lastUpdateTime\":1536143456000,\"regionCode\":\"22000\"}],\"project\":{\"isEnable\":true,\"name\":\"项目测试\",\"projectDestAdress\":[],\"projectFreightRule\":[],\"projectId\":1,\"projectTruck\":[],\"valuationWays\":[]},\"receiveAddress\":[{\"addressDetail\":\"四川省成都市武侯区天府大道北段\",\"addressName\":\"成都南站\",\"contactName\":\"何\",\"contactPhone\":\"18628271310\",\"coordinates\":\"104.068431,30.606147\",\"createTime\":1536143456000,\"delete\":false,\"lastUpdateTime\":1536143456000,\"regionCode\":\"22000\"}],\"truckRequire\":{\"additionalFunctionIds\":\",10,11\",\"coldChain\":false,\"collectPayment\":false,\"createTime\":1536143563108,\"delete\":false,\"entryLicense\":0,\"goodsAmount\":\"1\",\"goodsType\":\"快递\",\"goodsVolume\":\"1\",\"goodsWeight\":\"1\",\"lastUpdateTime\":1536143563108,\"needBackStorage\":false,\"receipt\":false,\"taxRateValue\":0.05,\"truckTypeId\":1},\"waybill\":{\"allowCancel\":false,\"allowChangeCar\":false,\"allowSendCar\":true,\"areaCode\":\"000106\",\"businessBranch\":3,\"calculatedFreight\":0.00,\"cmEstimateFinishTime\":1536433200000,\"customerId\":9878,\"customerManagerId\":499,\"customerManagerName\":\"测试-何平\",\"customerName\":\"何\",\"delete\":false,\"driverHasRead\":false,\"isSubmitMatch\":0,\"lastUpdateTime\":1536143563079,\"miniFreight\":0.00,\"ownerAreaCanOperate\":true,\"ownerEmployeeId\":499,\"planDeliveryTime\":1536364800000,\"priceExceptionFlag\":false,\"projectId\":1,\"projectName\":\"项目测试\",\"rebateRate\":0,\"receiptType\":3,\"receiptTypeText\":\"项目结算\",\"receiveWay\":6,\"referenceFreight\":0.00,\"selfWaybill\":true,\"shareAreaCanOperate\":true,\"statusViewText\":\"\",\"tenantCode\":\"000000000\",\"test\":true,\"truckCustomerId\":654,\"valuationWays\":[],\"vendorName\":\"朱照林\",\"waybillIds\":[],\"waybillNo\":\"\",\"waybillSource\":4,\"waybillSourceText\":\"后台下单\"},\"waybillCarrierVo\":{\"billTaxRate\":0.05,\"customerId\":85895,\"customerTenantId\":9,\"projectId\":98,\"settlementType\":1,\"vendorFees\":500,\"vendorId\":123},\"waybillParam\":{\"createTime\":1536143563114,\"delete\":false,\"lastUpdateTime\":1536143563114,\"projectFreightRuleJson\":\"{\\\"projectFreightRuleId\\\":1,\\\"truckTypeId\\\":1,\\\"projectId\\\":1,\\\"factorJson\\\":\\\"{\\\\\\\"initiateRate\\\\\\\":\\\\\\\"1000\\\\\\\"}\\\"}\",\"valuationWays\":[]}}";
        String loginString = "{\"authDepartments\":[{\"departmentId\":201,\"departmentName\":\"驹马集团\",\"loginDepartment\":false}],\"employeeId\":1,\"loginDepartment\":{\"businessAreas\":[{\"areaCode\":\"00\",\"areaName\":\"全国\"}],\"departmentCode\":\"00\",\"departmentId\":201,\"departmentName\":\"驹马集团\"},\"loginName\":\"admin\",\"maxInactiveInterval\":3600,\"sessionId\":\"8BDD13C0278E44309151AB5585236803\",\"sysUser\":true,\"tenantCode\":\"000000000\",\"tenantId\":2,\"test\":false,\"userId\":1,\"userName\":\"超级管理员\"}";

        ProjectBillVo projectBillVo = JSONObject.parseObject(billString, ProjectBillVo.class);

//        System.out.println(JSON.toJSONString(projectBillVo, SerializerFeature.WriteDateUseDateFormat));

        LoginUser loginEmployee = JSONObject.parseObject(loginString, LoginUser.class);


       List<Integer> ids = filialeBillService.createProjectBill(projectBillVo, loginEmployee);

        Assert.assertFalse(CollectionUtils.isEmpty(ids),"建单失败了");
    }


    @Test
    public void noneProject2NoneProject(){
        String billString = "{\n" +
            "    \"waybill\": {\n" +
            "        \"startCoordinates\": \"104.085602,30.682140\",\n" +
            "        \"endCoordinates\": \"103.923588,30.574488\",\n" +
            "        \"estimateDistance\": 26,\n" +
            "        \"estimateTimeConsumption\": 62,\n" +
            "        \"estimateFreight\": 178.67,\n" +
            "        \"freight\": 178.67,\n" +
            "        \"calculatedFreight\": 148.89,\n" +
            "        \"planDeliveryTime\": \"2018-09-06 16:36:00\",\n" +
            "        \"waybillRemark\": \"\",\n" +
            "        \"regionCode\": \"22000002\",\n" +
            "        \"receiptType\": 5,\n" +
            "        \"receiveWay\":6,\n" +
            "        \"customerId\": 85989,\n" +
            "        \"areaCode\": \"0001000006\",\n" +
            "        \"truckCustomerId\": 2491,\n" +
            "        \"cmEstimateFinishTime\": \"2018-09-06 21:37:00\",\n" +
            "        \"show4DriverFreight\": 178.67,\n" +
            "        \"businessBranch\": 3,\n" +
            "        \"deviceNo\": null,\n" +
            "        \"deviceType\": null,\n" +
            "        \"location\": \"116.42792,39.902896\"\n" +
            "    },\n" +
            "    \"deliveryAddress\": [{\n" +
            "        \"regionCode\": \"22000\",\n" +
            "        \"addressName\": \"北门车站\",\n" +
            "        \"addressDetail\": \"四川省成都市金牛区一环路北四段197号\",\n" +
            "        \"contactName\": \"tt\",\n" +
            "        \"contactPhone\": \"18428385729\",\n" +
            "        \"spareContactPhone\": \"\",\n" +
            "        \"coordinates\": \"104.085602,30.682140\"\n" +
            "    }],\n" +
            "    \"receiveAddress\": [{\n" +
            "        \"regionCode\": \"22000\",\n" +
            "        \"addressName\": \"李宗\",\n" +
            "        \"addressDetail\": \"四川省成都市双流区双流区\",\n" +
            "        \"contactName\": \"\",\n" +
            "        \"contactPhone\": \"\",\n" +
            "        \"coordinates\": \"103.923588,30.574488\"\n" +
            "    }],\n" +
            "    \"truckRequire\": {\n" +
            "        \"goodsType\": \"粮油\",\n" +
            "        \"goodsVolume\": 5,\n" +
            "        \"goodsWeight\": 5,\n" +
            "        \"goodsAmount\": null,\n" +
            "        \"isTailBoard\": 0,\n" +
            "        \"remark\": \"\",\n" +
            "        \"taxRateId\": 13,\n" +
            "        \"taxRateValue\": 0,\n" +
            "        \"truckTypeId\": 9,\n" +
            "        \"vehicleBoxType\": null\n" +
            "    },\n" +
            "    \"createBatchAmount\": 1,\n" +
            "    \"waybillParam\": {\n" +
            "        \"agencyTakeFreight\": null\n" +
            "    },\n" +
            "    \"waybillCarrierVo\": {\n" +
            "        \"vendorId\": 123,\n" +
            "        \"customerTenantId\": 9,\n" +
            "        \"customerId\": 85895,\n" +
            "        \"settlementType\": 2,\n" +
            "        \"vendorFeeRate\": 10\n" +
            "    }\n" +
            "}";



        LoginUser loginUser = new LoginUser(2,3141);

        WaybillBo waybillBo = JSONObject.parseObject(billString, WaybillBo.class);

        Integer id = waybillService.createWaybill(waybillBo,Waybill.WaybillSource.JUMA_CLIENT,loginUser);

        Assert.assertNotNull(id, "建单失败");

    }
}


