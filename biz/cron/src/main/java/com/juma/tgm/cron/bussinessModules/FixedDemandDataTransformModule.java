package com.juma.tgm.cron.bussinessModules;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.PageQueryCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.common.Constants;
import com.juma.tgm.customerManager.domain.FixedDemand;
import com.juma.tgm.customerManager.domain.FixedDemandDeliveryPoint;
import com.juma.tgm.customerManager.domain.FixedDemandTruck;
import com.juma.tgm.customerManager.domain.Task4Waybill;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplate;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateDestAddress;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateSrcAddress;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateTruckBinding;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillListCountVo;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.Task4WaybillQueryVo;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.TaskWaybillTemplateCreateVo;
import com.juma.tgm.customerManager.service.FixedDemandService;
import com.juma.tgm.customerManager.service.Task4WaybillService;
import com.juma.tgm.customerManager.service.TaskWaybillTemplateService;
import com.juma.tgm.customerManager.service.vo.FixedDemandVo;
import com.juma.tgm.project.enumeration.ValuationWayEnum;
import com.juma.tgm.waybill.domain.TruckRequire;

/**
 * @ClassName: FixedDemandDataTransformModule
 * @Description:
 * @author: liang
 * @date: 2018-10-15 13:53
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
@Deprecated
@Component
public class FixedDemandDataTransformModule {

    private static final Logger log = LoggerFactory.getLogger(FixedDemandDataTransformModule.class);

    @Resource
    private FixedDemandService fixedDemandService;

    @Resource
    private TaskWaybillTemplateService taskWaybillTemplateService;

    @Resource
    private Task4WaybillService task4WaybillService;

    //固定需求迁移到定时下单
    @Deprecated
    public void transformTaskData(Boolean newFlag) {
        log.info("开始迁移固定需求到定时下单");
        Task4WaybillQueryVo queryVo = new Task4WaybillQueryVo();
        queryVo.setUserId(0);
        PageQueryCondition<Task4WaybillQueryVo> queryCondition = new PageQueryCondition<>(queryVo);

        if (newFlag == null || newFlag == true) {
            Task4WaybillListCountVo countVo = task4WaybillService.taskCount(queryCondition, null);

            if (countVo.getTotal() != null && NumberUtils.compare(countVo.getTotal(), 0) > 0) {
                throw new BusinessException("DataError", "errors.common.prompt", "请先删除Task4Waybill表中任务数据");
            }
        }

        List<FixedDemand> allData = fixedDemandService.loadAll();

        this.doTransform(allData);

        log.info("数据迁移完成");
    }

    /**
     * 指定固定需求迁移
     *
     * @param fixedDemandIdList
     */
    public void pointTransformTaskData(List<Integer> fixedDemandIdList) {
        if (CollectionUtils.isEmpty(fixedDemandIdList)) {
            log.info("没有指定需要迁移的参数， 退出指定");
            return;
        }

        List<FixedDemand> allData = new ArrayList<>();
        for (Integer id : fixedDemandIdList) {
            FixedDemandVo fixedDemand = fixedDemandService.get(id);
            allData.add(fixedDemand.getFixedDemand());
        }

        this.doTransform(allData);
    }

    //读取固定需求
    private void doTransform(List<FixedDemand> allData) {

        if (CollectionUtils.isEmpty(allData)) {
            log.info("没有需要执行转换的固定需求");
            return;
        }

        TaskWaybillTemplateCreateVo createVo = null;

        LoginEmployee loginEmployee = new LoginEmployee();

        for (FixedDemand fixedDemand : allData) {
            try {
                createVo = new TaskWaybillTemplateCreateVo();
                //任务（原任务时间处理）
                Task4Waybill task4Waybill = this.buildTask4Waybill(fixedDemand);
                //模板(原计价规则处理)
                TaskWaybillTemplate template = this.buildTemplate(fixedDemand);
                //用车要求
                this.buildTruckRequire(fixedDemand, createVo);
                //线路,固定车辆
                this.buildDeliveryPointAndTruck(fixedDemand, createVo);

                createVo.setTaskWaybillTemplate(template);
                createVo.setTask4Waybill(task4Waybill);

                loginEmployee.setEmployeeId(template.getCustomerManagerId());
                loginEmployee.setUserId(template.getCreateUserId());
                taskWaybillTemplateService.addTaskWaybill(createVo, loginEmployee);
            } catch (Exception e) {
                log.warn("固定需求转换失败,fixedDemandId:{}", fixedDemand.getFixedDemandId(), e);
            }
        }

    }

    //任务转换
    private Task4Waybill buildTask4Waybill(FixedDemand fixedDemand) {
        Task4Waybill task4Waybill = new Task4Waybill();

        if (StringUtils.isNotBlank(fixedDemand.getAutoCreateBillStrategy())) {
            FixedDemand.BillStrategy strategy = JSONObject.parseObject(fixedDemand.getAutoCreateBillStrategy(), FixedDemand.BillStrategy.class);
            Set<String> days = strategy.getDays();
            if (CollectionUtils.isNotEmpty(days)) {
                String daysStr = days.toString();
                daysStr = StringUtils.replace(daysStr, "[", "");
                daysStr = StringUtils.replace(daysStr, "]", "");

                task4Waybill.setTaskWeekDays(daysStr);
            }

            task4Waybill.setIsClose(fixedDemand.getIsAutoCreateBill().byteValue());
            Date startDate = null;
            Date endDate = null;
            try {
                startDate = Constants.YYYYMMDD.parse(strategy.getStartDate());
                endDate = Constants.YYYYMMDD.parse(strategy.getEndDate());
            } catch (ParseException e) {
                log.warn(e.getMessage(), e);
            }

            task4Waybill.setTaskStartDate(startDate);
            task4Waybill.setTaskEndDate(endDate);
        }
        task4Waybill.setEmployeeId(fixedDemand.getCustomerManagerId());
        task4Waybill.setUserId(fixedDemand.getCreateUserId());

        return task4Waybill;
    }

    /**
     * 转换为任务模板
     *
     * @param fixedDemand
     * @return
     */
    private TaskWaybillTemplate buildTemplate(FixedDemand fixedDemand) {
        TaskWaybillTemplate template = new TaskWaybillTemplate();
        Byte receiptType = fixedDemand.getReceiptType() == null ? null : fixedDemand.getReceiptType().byteValue();
        template.setReceiptType(receiptType);
        template.setCustomerManagerId(fixedDemand.getCustomerManagerId());
        template.setRequireJson(fixedDemand.getRequireJson());
        template.setProjectId(fixedDemand.getProjectId());
        template.setCustomerId(fixedDemand.getCustomerId());
        template.setTruckCustomerId(fixedDemand.getTruckCustomerId());
        template.setValuationWay(Byte.valueOf(ValuationWayEnum.WORKLOAD.getCode() + ""));
        template.setProjectFreightRuleJson(fixedDemand.getProjectFreightRuleJson());
        template.setDeliveryTimePoint(fixedDemand.getDeliveryTimePoint());
        template.setFinishTimePoint(fixedDemand.getFinishTimePoint());
        Byte businessBranch = fixedDemand.getBusinessBranch() == null ? null : fixedDemand.getBusinessBranch().byteValue();
        template.setBusinessBranch(businessBranch);
        template.setAgencyTakeFreight(fixedDemand.getAgencyTakeFreight());
        template.setLastUpdateUserId(fixedDemand.getLastUpdateUserId());
        template.setLastUpdateTime(fixedDemand.getLastUpdateTime());
        template.setCreateUserId(fixedDemand.getCreateUserId());
        template.setCreateTime(fixedDemand.getCreateTime());
        template.setRemark(fixedDemand.getRemark());
        template.setRequiredMaxTemperature(fixedDemand.getRequiredMaxTemperature());
        template.setRequiredMinTemperature(fixedDemand.getRequiredMinTemperature());
        template.setVehicleCount(fixedDemand.getVehicleCount());
        Byte receiceWay = fixedDemand.getReceiveWay() == null ? null : fixedDemand.getReceiveWay().byteValue();
        template.setReceiveWay(receiceWay);
        Byte onlyLoadCargo = fixedDemand.getOnlyLoadCargo() == null ? null : fixedDemand.getOnlyLoadCargo().byteValue();
        template.setOnlyLoadCargo(onlyLoadCargo);
        Byte needDeliverPointNote = fixedDemand.getNeedDeliveryPointNote() == null ? null : fixedDemand.getNeedDeliveryPointNote().byteValue();
        template.setNeedDeliveryPointNote(needDeliverPointNote);

        return template;
    }

    private void buildTruckRequire(FixedDemand fixedDemand, TaskWaybillTemplateCreateVo templateCreateVo) {
        if (StringUtils.isBlank(fixedDemand.getRequireJson())) return;

        TruckRequire truckRequire = JSONObject.parseObject(fixedDemand.getRequireJson(), TruckRequire.class);

        templateCreateVo.setTruckRequire(truckRequire);
    }

    //线路数据,固定车辆
    private void buildDeliveryPointAndTruck(FixedDemand fixedDemand, TaskWaybillTemplateCreateVo createVo) {
        FixedDemandVo fixedDemandVo = fixedDemandService.get(fixedDemand.getFixedDemandId());
        //获取线路
        List<FixedDemandDeliveryPoint> fixedDemandDeliveryPoints = fixedDemandVo.getDeliveryAddresses();
        TaskWaybillTemplateSrcAddress templateSrcAddress = new TaskWaybillTemplateSrcAddress();
        BeanUtils.copyProperties(fixedDemandDeliveryPoints.get(0), templateSrcAddress);
        List<TaskWaybillTemplateSrcAddress> templateSrcAddressList = new ArrayList<>();
        templateSrcAddressList.add(templateSrcAddress);
        createVo.setSrcAddresses(templateSrcAddressList);

        List<FixedDemandDeliveryPoint> fixedDemandReceivePoints = fixedDemandVo.getReceiveAddresses();

        if (CollectionUtils.isNotEmpty(fixedDemandReceivePoints)) {
            List<TaskWaybillTemplateDestAddress> destAddresses = new ArrayList<>();
            TaskWaybillTemplateDestAddress destAddress = null;
            for (FixedDemandDeliveryPoint deliveryPoint : fixedDemandReceivePoints) {
                destAddress = new TaskWaybillTemplateDestAddress();
                BeanUtils.copyProperties(deliveryPoint, destAddress);

                destAddresses.add(destAddress);
            }
            createVo.setDestAddresses(destAddresses);
        }

        //固定车辆
        List<FixedDemandTruck> fixedDemandTrucks = fixedDemandVo.getFixedDemandTruck();
        if (CollectionUtils.isNotEmpty(fixedDemandTrucks)) {
            List<TaskWaybillTemplateTruckBinding> truckBindingList = new ArrayList<>();
            TaskWaybillTemplateTruckBinding truckBinding = null;

            for (FixedDemandTruck truck : fixedDemandTrucks) {
                truckBinding = new TaskWaybillTemplateTruckBinding();
                truckBinding.setTruckId(truck.getTruckId());

                truckBindingList.add(truckBinding);
            }

            createVo.setTaskWaybillTemplateTruckBindings(truckBindingList);
        }

    }
}
