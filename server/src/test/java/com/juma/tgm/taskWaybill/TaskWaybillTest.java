package com.juma.tgm.taskWaybill;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.giants.common.tools.PageQueryCondition;
import com.juma.auth.employee.domain.EmployeeBo;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.Constants;
import com.juma.tgm.cron.bussinessModules.Task4WaybillModule;
import com.juma.tgm.cron.service.CronjobService;
import com.juma.tgm.customerManager.domain.Task4Waybill;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplate;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateSrcAddress;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillQueryVo;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.TaskWaybillTemplateCreateVo;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.TaskWaybillTemplateEnum;
import com.juma.tgm.customerManager.service.Task4WaybillService;
import com.juma.tgm.customerManager.service.TaskWaybillTemplateService;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import testng.BaseTestNGTest;

/**
 * @ClassName: taskWaybil
 * @Description:
 * @author: liang
 * @date: 2018-10-09 16:27
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class TaskWaybillTest extends BaseTestNGTest {

    @Resource
    private TaskWaybillTemplateService taskWaybillTemplateService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private CronjobService cronjobService;

    @Resource
    private Task4WaybillModule task4WaybillModule;

    @Resource
    private Task4WaybillService task4WaybillService;


    @Test
    public void addTaskWaybillTest() {
        TaskWaybillTemplateCreateVo taskWaybillTemplateCreateVo = new TaskWaybillTemplateCreateVo();

        //任务模板
        TaskWaybillTemplate template = new TaskWaybillTemplate();
        template.setAgencyTakeFreight(new BigDecimal("200"));
        template.setDeliveryTimePoint("20:00");
        template.setBusinessBranch(Byte.valueOf("3"));
        template.setCustomerId(85289);
        template.setCustomerManagerId(2260);
        template.setTruckCustomerId(1850);
        template.setProjectId(30);
        template.setFinishTimePoint(3600);
        template.setProjectFreightRuleJson("{\"projectFreightRuleId\":827,\"truckTypeId\":5,\"projectId\":201,\"factorJson\":\"{\\\"initiateRate\\\":18}\"}");
        template.setReceiptType(Integer.valueOf(Waybill.ReceiptType.PROJECTPAY.getCode() + "").byteValue());

        taskWaybillTemplateCreateVo.setTaskWaybillTemplate(template);

        TruckRequire truckRequire = new TruckRequire();
        truckRequire.setTruckTypeId(33);
        truckRequire.setAdditionalFunctionIds("12");
        truckRequire.setTaxRateValue(new BigDecimal("0.2"));
        truckRequire.setGoodsVolume("2");
        truckRequire.setGoodsWeight("3");
        truckRequire.setGoodsType("书");

        taskWaybillTemplateCreateVo.setTruckRequire(truckRequire);

        //任务信息
        Task4Waybill task4Waybill = new Task4Waybill();
        task4Waybill.setEmployeeId(2260);
        Date today = Calendar.getInstance().getTime();
        task4Waybill.setTaskEndDate(DateUtils.addDays(today, 10));
        task4Waybill.setTaskStartDate(DateUtils.addDays(today, -1));

        task4Waybill.setIsClose(TaskWaybillTemplateEnum.AutoCreateBillEnum.AUTO_CREATE.getCode());
        task4Waybill.setTaskWeekDays("thu,mon,tue,wen,fri");

        taskWaybillTemplateCreateVo.setTask4Waybill(task4Waybill);

        //线路信息
        //---- 取货地
        List<TaskWaybillTemplateSrcAddress> srcAddresses = new ArrayList<>();
        TaskWaybillTemplateSrcAddress srcAddress = new TaskWaybillTemplateSrcAddress();
        srcAddress.setAddressDetail("详细地址信息");
        srcAddress.setAddressName("地址名称");
        srcAddress.setContactName("联系人");
        srcAddress.setContactPhone("15882104100");
        srcAddress.setCoordinates("104.159199,30.587940");
        srcAddress.setRegionCode("22000");

        srcAddresses.add(srcAddress);
        taskWaybillTemplateCreateVo.setSrcAddresses(srcAddresses);
        //---- 配送地
//            List< TaskWaybillTemplateDestAddress > destAddresses =

        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setEmployeeId(2260);
        loginEmployee.setUserId(13168);

        int templateId = taskWaybillTemplateService.addTaskWaybill(taskWaybillTemplateCreateVo, loginEmployee);

        Assert.assertNotNull(templateId, "返回值为空");
    }


    @Test
    public void modify() {
//        TaskWaybillTemplateCreateVo taskWaybillTemplateCreateVo = new TaskWaybillTemplateCreateVo();

        EmployeeBo employeeBo = employeeService.loadEmployeeBo(2260, Constants.SYS_LOGIN_USER);

        System.out.println(JSON.toJSONString(employeeBo));

    }


    @Test
    public void taskWaybillTest() {
        cronjobService.task4Waybill();
    }


    @Test
    public void pointTask() {
        List<Integer> taskIds = new ArrayList<>();
        taskIds.add(47);

        task4WaybillModule.createBillByTaskIds(taskIds);
    }


    @Test
    public void transformFixedDemandData() {
        cronjobService.transformFixedDemandData(true);
    }

    @Test
    public void searchTask(){
        Task4WaybillQueryVo vo = new Task4WaybillQueryVo();
        vo.setTodayTask(false);
        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setTenantId(19);
        loginEmployee.setUserId(8020);
        PageQueryCondition<Task4WaybillQueryVo> queryCondition = new PageQueryCondition<>(vo);
        task4WaybillService.searchTask(queryCondition,loginEmployee);
    }

    @Test
    public void taskCount(){
        PageQueryCondition<Task4WaybillQueryVo> queryCondition = new PageQueryCondition<>(new Task4WaybillQueryVo());
        queryCondition.getFilters().setUserId(29961);
        queryCondition.getFilters().setEmployeeId(9907);
        queryCondition.setPageNo(1);
        queryCondition.setPageSize(10);
//        task4WaybillService.taskCount(queryCondition);
    }

    @Test
    public void getDetail(){
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(29961);
        loginUser.setTenantId(4);
        taskWaybillTemplateService.getDetail(5205, loginUser);
    }

}
