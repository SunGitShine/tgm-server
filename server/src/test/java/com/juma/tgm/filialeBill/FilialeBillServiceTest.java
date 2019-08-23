package com.juma.tgm.filialeBill;

import java.util.List;

import javax.annotation.Resource;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.filiale.service.FilialeBillService;
import com.juma.tgm.project.vo.ProjectBillVo;
import com.juma.tgm.waybill.domain.Waybill;
import testng.BaseTestNGTest;

/**
 * @ClassName: FilialeBillServiceTest
 * @Description:
 * @author: liang
 * @date: 2017-09-30 16:29
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class FilialeBillServiceTest extends BaseTestNGTest {


    @Resource
    private FilialeBillService filialeBillService;

    @Test
    public void getProjectBillDetail () {
        filialeBillService.getProjectBillDetail(999824, new LoginUser(2, 1));
    }
    
    
    @Test
    public void createProjectBill(){

        ProjectBillVo vo = new ProjectBillVo();

        String json = "{\n" +
            "\t\"waybill\": {\n" +
            "\t\t\"truckCustomerId\": 2364,\n" +
            "\t\t\"customerId\": 85755,\n" +
            "\t\t\"planDeliveryTime\": \"2018-10-23 22:27:00\",\n" +
            "\t\t\"receiptType\": 3,\n" +
            "\t\t\"receiveWay\": 6,\n" +
            "\t\t\"regionCode\": \"22000\",\n" +
            "\t\t\"businessBranch\": 3,\n" +
            "\t\t\"cmEstimateFinishTime\": \"2018-10-24 04:27:00\",\n" +
            "\t\t\"waybillRemark\": \"\",\n" +
            "\t\t\"rebateRate\": 0.05,\n" +
            "\t\t\"needDeliveryPointNote\": 1,\n" +
            "\t\t\"onlyLoadCargo\": 1,\n" +
            "\t\t\"estimateFreight\": 900\n" +
            "\t},\n" +
            "\t\"fixedTruckIds\": [],\n" +
            "\t\"project\": {\n" +
            "\t\t\"projectId\": 173,\n" +
            "\t\t\"projectTruck\": []\n" +
            "\t},\n" +
            "\t\"truckRequire\": {\n" +
            "\t\t\"goodsType\": \"酒水饮料\",\n" +
            "\t\t\"goodsVolume\": 5,\n" +
            "\t\t\"goodsWeight\": 5,\n" +
            "\t\t\"additionalFunctionIds\": \"9,7,6,11,10\",\n" +
            "\t\t\"taxRateId\": 7,\n" +
            "\t\t\"taxRateValue\": 0.05,\n" +
            "\t\t\"truckTypeId\": 9\n" +
            "\t},\n" +
            "\t\"deliveryAddress\": [\n" +
            "\t\t{\n" +
            "\t\t\t\"regionCode\": \"22000\",\n" +
            "\t\t\t\"addressName\": \"天府宝座(西北门)\",\n" +
            "\t\t\t\"addressDetail\": \"四川省成都市锦江区金石路166号\",\n" +
            "\t\t\t\"contactName\": \"根据\",\n" +
            "\t\t\t\"contactPhone\": \"13717936245\",\n" +
            "\t\t\t\"coordinates\": \"104.088981,30.588987\"\n" +
            "\t\t}\n" +
            "\t],\n" +
            "\t\"receiveAddress\": [\n" +
            "\t\t{\n" +
            "\t\t\t\"regionCode\": \"22000007\",\n" +
            "\t\t\t\"addressName\": \"W\",\n" +
            "\t\t\t\"addressDetail\": \"小南街与银杏街交叉口东50米\",\n" +
            "\t\t\t\"contactName\": \"\",\n" +
            "\t\t\t\"contactPhone\": \"\",\n" +
            "\t\t\t\"coordinates\": \"104.015426,30.872023\"\n" +
            "\t\t}\n" +
            "\t],\n" +
            "\t\"truckValidator\": [],\n" +
            "\t\"waybillParam\": {\n" +
            "\t\t\"agencyTakeFreight\": null,\n" +
            "\t\t\"projectFreightRuleJson\": \"{\\\"truckTypeId\\\":9,\\\"roadMapId\\\":380,\\\"valuationWay\\\":2,\\\"factorJson\\\":\\\"{\\\\\\\"estimateFreight\\\\\\\":900}\\\"}\"\n" +
            "\t},\n" +
            "\t\"createBatchAmount\": 1,\n" +
            "\t\"waybillCarrierVo\": {\n" +
            "\t\t\"vendorId\": 123,\n" +
            "\t\t\"customerTenantId\": 9,\n" +
            "\t\t\"customerId\": 85895,\n" +
            "\t\t\"projectId\": 98,\n" +
            "\t\t\"billTaxRate\": 0.07,\n" +
            "\t\t\"rebateRate\": 0.1,\n" +
            "\t\t\"settlementType\": 2,\n" +
            "\t\t\"vendorFeeRate\": 0.1\n" +
            "\t}\n" +
            "}";


        ProjectBillVo projectBillVo = JSONObject.parseObject(json, ProjectBillVo.class);

        projectBillVo.getWaybill().setWaybillSource(Waybill.WaybillSource.JUMA_CLIENT.getCode());

        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setUserId(13168);
        loginEmployee.setEmployeeId(2260);

        loginEmployee.setTenantCode("000000000");
        loginEmployee.setTenantId(2);


        List<Integer> ids =  filialeBillService.createProjectBill(projectBillVo, loginEmployee);

        Assert.assertNotNull(ids,"id不能为空");


    }
}
