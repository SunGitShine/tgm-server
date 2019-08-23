package com.juma.tgm.customerManager.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.giants.common.exception.BusinessException;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.service.ConfParamService;
import com.juma.crm.customer.domain.Customer4TmsBo;
import com.juma.crm.support.service.Crm4TmsService;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.configure.service.ServiceConfService;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.customer.service.CustomerManagerService;
import com.juma.tgm.customerManager.dao.Task4WaybillMapper;
import com.juma.tgm.customerManager.dao.TaskWaybillTemplateDestAddressMapper;
import com.juma.tgm.customerManager.dao.TaskWaybillTemplateMapper;
import com.juma.tgm.customerManager.dao.TaskWaybillTemplateSrcAddressMapper;
import com.juma.tgm.customerManager.dao.TaskWaybillTemplateTruckBindingMapper;
import com.juma.tgm.customerManager.domain.Task4Waybill;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplate;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateDestAddress;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateDestAddressExample;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateExample;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateSrcAddress;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateSrcAddressExample;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateTruckBinding;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateTruckBindingExample;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.TaskWaybillTemplateCreateVo;
import com.juma.tgm.customerManager.domain.vo.taskWaybill.TaskWaybillTemplateDetailVo;
import com.juma.tgm.customerManager.service.Task4WaybillReportService;
import com.juma.tgm.customerManager.service.Task4WaybillService;
import com.juma.tgm.customerManager.service.TaskWaybillTemplateService;
import com.juma.tgm.project.domain.RoadMap;
import com.juma.tgm.project.domain.ValuationWay;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.project.service.RoadMapService;
import com.juma.tgm.project.vo.ProjectFreightRuleVo;
import com.juma.tgm.project.vo.v2.ProjectVoApp;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.truck.domain.bo.DriverTruckInfoBo;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillDeliveryAddress;
import com.juma.tgm.waybill.domain.WaybillDetailInfo;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.WaybillReceiveAddress;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.vms.driver.domain.Driver;
import com.juma.vms.transport.domain.CapacityPool;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * 定时下单基础信息维护
 *
 * @ClassName: TaskWaybillTemplateServiceImpl
 * @Description:
 * @author: liang
 * @date: 2018-09-28 13:59
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
@Service
public class TaskWaybillTemplateServiceImpl implements TaskWaybillTemplateService {


    private static final Logger log = LoggerFactory.getLogger(TaskWaybillTemplateServiceImpl.class);

    @Autowired
    private ConfParamService confParamService;

    @Resource
    private Task4WaybillMapper task4WaybillMapper;

    @Resource
    private TaskWaybillTemplateDestAddressMapper taskWaybillTemplateDestAddressMapper;

    @Resource
    private TaskWaybillTemplateSrcAddressMapper taskWaybillTemplateSrcAddressMapper;

    @Resource
    private TaskWaybillTemplateMapper taskWaybillTemplateMapper;

    @Resource
    private TaskWaybillTemplateTruckBindingMapper taskWaybillTemplateTruckBindingMapper;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private Task4WaybillService task4WaybillService;

    @Resource
    private CustomerManagerService customerManagerService;

    @Resource
    private RoadMapService roadMapService;

    @Resource
    private ProjectService projectService;
    @Resource
    private ApplicationEventPublisher publisher;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private TruckRequireService truckRequireService;

    @Autowired
    private TruckTypeService truckTypeService;

    @Autowired
    private VmsCommonService vmsCommonService;

    @Autowired
    private ServiceConfService serviceConfService;

    @Resource
    private Task4WaybillReportService task4WaybillReportService;

    @Resource
    private Crm4TmsService crm4TmsService;

    @Resource
    private UserService userService;


    @Override
    public int addTaskWaybill(TaskWaybillTemplateCreateVo taskWaybillTemplateCreateVo, LoginEmployee loginEmployee) throws BusinessException {

        this.checkTaskTemplateObjectCommon(taskWaybillTemplateCreateVo);

        //任务模板参数检查
        TaskWaybillTemplate taskWaybillTemplate = taskWaybillTemplateCreateVo.getTaskWaybillTemplate();
        if (taskWaybillTemplate.getProjectId() == null)
            throw new BusinessException("projectIdNullError", "errors.paramCanNotNullWithName", "项目");
        if (taskWaybillTemplate.getCustomerId() == null)
            throw new BusinessException("customerIdNullError", "errors.paramCanNotNullWithName", "客户");
        if (taskWaybillTemplate.getReceiveWay() == null)
            throw new BusinessException("receiveWayIdNullError", "errors.paramCanNotNullWithName", "派单方式");
        if (taskWaybillTemplate.getFinishTimePoint() == null)
            throw new BusinessException("finishTimePointNullError", "errors.paramCanNotNullWithName", "预估完成时间");
        if (taskWaybillTemplate.getDeliveryTimePoint() == null)
            throw new BusinessException("deliveryTimePointNullError", "errors.paramCanNotNullWithName", "用车时间");
        if (taskWaybillTemplateCreateVo.getTruckRequire() == null)
            throw new BusinessException("truckRequireNullError", "errors.paramCanNotNullWithName", "用车要求");
        if (taskWaybillTemplate.getValuationWay() == null)
            throw new BusinessException("valuationWayNullError", "errors.paramCanNotNullWithName", "计价方式");
//        if(StringUtils.isBlank(taskWaybillTemplate.getDepartmentCode())){
//            throw new BusinessException("departmentCodeNotNull", "waybill.error.departmentCodeNotNull");
//        }
        //指派车队时检验是否已有重复模板
        if(taskWaybillTemplate.getReceiveWay() == 1 && checkIsSameTemplate(taskWaybillTemplateCreateVo)){
            throw new BusinessException("templateIsExistError", "errors.common.prompt", "当前指派的运力已被占用，请更换运力或调整用车时间");
        }

        Task4Waybill task4Waybill = taskWaybillTemplateCreateVo.getTask4Waybill();
        List<TaskWaybillTemplateSrcAddress> srcAddresses = taskWaybillTemplateCreateVo.getSrcAddresses();
        List<TaskWaybillTemplateDestAddress> destAddresses = taskWaybillTemplateCreateVo.getDestAddresses();

        //保存任务模板
        this.buildTemplate(taskWaybillTemplateCreateVo, loginEmployee);
        this.insertTaskWaybillTemplate(taskWaybillTemplate, loginEmployee);

        //保存任务信息
        this.buildTask4Waybill(task4Waybill, taskWaybillTemplate, loginEmployee);
        task4WaybillMapper.insert(task4Waybill);

        //保存线路信息
        this.saveSrcAddr(srcAddresses, taskWaybillTemplate, loginEmployee);
        this.saveDestAddr(destAddresses, taskWaybillTemplate, loginEmployee);

        //固定车辆
        this.saveTaskWaybillTemplateTruckBinding(taskWaybillTemplateCreateVo.getTaskWaybillTemplateTruckBindings(), taskWaybillTemplate);

        //将任务放入redis
        publisher.publishEvent(task4Waybill);

        return taskWaybillTemplate.getTaskWaybillTemplateId();
    }

    /**
     * 通用任务模板参数对象检查
     *
     * @param taskWaybillTemplateCreateVo
     */
    private void checkTaskTemplateObjectCommon(TaskWaybillTemplateCreateVo taskWaybillTemplateCreateVo) {
        if (taskWaybillTemplateCreateVo == null)
            throw new BusinessException("addTaskWaybillParamNullError", "errors.paramCanNotNullWithName", "运单任务模板");

        if (taskWaybillTemplateCreateVo.getTaskWaybillTemplate() == null)
            throw new BusinessException("addTaskWaybillTemplateNullError", "errors.paramCanNotNullWithName", "运单任务模板");

        if (taskWaybillTemplateCreateVo.getTask4Waybill() == null)
            throw new BusinessException("addTaskWaybillTaskNullError", "errors.paramCanNotNullWithName", "运单任务信息");

        if (CollectionUtils.isEmpty(taskWaybillTemplateCreateVo.getSrcAddresses()))
            throw new BusinessException("addTaskWaybillSrcAddrNullError", "errors.paramCanNotNullWithName", "运单任务取货地");

//        if (CollectionUtils.isEmpty(taskWaybillTemplateCreateVo.getDestAddresses()))
//            throw new BusinessException("addTaskWaybillDestAddrNullError", "errors.paramCanNotNullWithName", "运单任务配送地");
    }

    private boolean checkIsSameTemplate(TaskWaybillTemplateCreateVo taskWaybillTemplateCreateVo) throws BusinessException {

        TaskWaybillTemplate taskWaybillTemplate = taskWaybillTemplateCreateVo.getTaskWaybillTemplate();

        boolean isExist = false;
        TaskWaybillTemplateExample example = new TaskWaybillTemplateExample();
        TaskWaybillTemplateExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(taskWaybillTemplate.getProjectId());
        criteria.andDeliveryTimePointEqualTo(taskWaybillTemplate.getDeliveryTimePoint());
        if(taskWaybillTemplate.getTaskWaybillTemplateId() != null){
            criteria.andTaskWaybillTemplateIdNotEqualTo(taskWaybillTemplate.getTaskWaybillTemplateId());
        }
        //帅选项目id和用车时间相同的任务模板
        List<TaskWaybillTemplate> taskWaybillTemplates = taskWaybillTemplateMapper.selectByExample(example);
        if(!taskWaybillTemplates.isEmpty()){
            for(TaskWaybillTemplate template : taskWaybillTemplates){
                Task4Waybill task4Waybill = task4WaybillService.getByTemplateId(template.getTaskWaybillTemplateId());
                String weekDays = task4Waybill.getTaskWeekDays();
                //配送周期相同
                if(weekDays.equals(taskWaybillTemplateCreateVo.getTask4Waybill().getTaskWeekDays())){
                    List<TaskWaybillTemplateTruckBinding> bindings = this.getTruckBindingByTemplateId(template.getTaskWaybillTemplateId());
                    List<TaskWaybillTemplateTruckBinding> paramBindings = taskWaybillTemplateCreateVo.getTaskWaybillTemplateTruckBindings();
                    //运力相同
                    if(!bindings.isEmpty() && !paramBindings.isEmpty()){
                        List<Integer> bindingList = new ArrayList<>();
                        for(TaskWaybillTemplateTruckBinding binding : bindings){
                            bindingList.add(binding.getTruckId());
                        }
                        List<Integer> paramBindingList = new ArrayList<>();
                        for(TaskWaybillTemplateTruckBinding binding : paramBindings){
                            paramBindingList.add(binding.getTruckId());
                        }
                        if(bindingList.containsAll(paramBindingList) && paramBindingList.containsAll(bindingList)){
                            isExist = true;
                            break;
                        }
                    }
                }
            }
        }
        return isExist;
    }

    /**
     * 组装运单任务模板
     *
     * @param taskWaybillTemplateCreateVo
     * @param loginEmployee
     */
    private void buildTemplate(TaskWaybillTemplateCreateVo taskWaybillTemplateCreateVo, LoginEmployee loginEmployee) {
        //用车要求
        TruckRequire truckRequire = taskWaybillTemplateCreateVo.getTruckRequire();

        //吨方件检查
        if (truckRequire.getTaxRateValue() == null && truckRequire.getTaxRateId() == null)
            throw new BusinessException("TaxRateValueNullError", "errors.paramCanNotNullWithName", "税率");
        if (truckRequire.getTruckTypeId() == null)
            throw new BusinessException("TruckTypeNullError", "errors.paramCanNotNullWithName", "车型");
        if (truckRequire.getGoodsType() == null)
            throw new BusinessException("goodsTypeNullError", "errors.paramCanNotNullWithName", "货物类型");
        if (truckRequire.getGoodsWeight() == null)
            throw new BusinessException("goodsWeightNullError", "errors.paramCanNotNullWithName", "货物重量");
        if (truckRequire.getGoodsVolume() == null)
            throw new BusinessException("goodsVolumeNullError", "errors.paramCanNotNullWithName", "货物方量");

        TaskWaybillTemplate template = taskWaybillTemplateCreateVo.getTaskWaybillTemplate();
        template.setReceiptType(Byte.valueOf(Waybill.ReceiptType.PROJECTPAY.getCode() + ""));
        template.setCustomerManagerId(loginEmployee.getEmployeeId());
        if (truckRequire != null) {
            String requireJson = JSON.toJSONString(truckRequire);
            template.setRequireJson(requireJson);
        }

    }

    /**
     * 组装运单任务信息
     *
     * @param task4Waybill
     * @param taskWaybillTemplate
     * @param loginEmployee
     */
    private void buildTask4Waybill(Task4Waybill task4Waybill, TaskWaybillTemplate taskWaybillTemplate, LoginEmployee loginEmployee) {
        task4Waybill.setCreateTime(new Date());
        task4Waybill.setCreateUserId(loginEmployee.getUserId());
        task4Waybill.setLastUpdateTime(new Date());
        task4Waybill.setLastUpdateUserId(loginEmployee.getUserId());
        task4Waybill.setUserId(loginEmployee.getUserId());
        task4Waybill.setEmployeeId(loginEmployee.getEmployeeId());
        task4Waybill.setTaskWaybillTemplateId(taskWaybillTemplate.getTaskWaybillTemplateId());
    }

    private void insertTaskWaybillTemplate(TaskWaybillTemplate taskWaybillTemplate, LoginUser loginUser) {
        taskWaybillTemplate.setCreateUserId(loginUser.getUserId());
        taskWaybillTemplate.setCreateTime(new Date());

        taskWaybillTemplateMapper.insert(taskWaybillTemplate);
    }

    /**
     * 保存取货地信息
     *
     * @param srcAddresses
     * @param taskWaybillTemplate
     * @param loginUser
     */
    private void saveSrcAddr(List<TaskWaybillTemplateSrcAddress> srcAddresses, TaskWaybillTemplate taskWaybillTemplate, LoginUser loginUser) {
        //删除原有地址信息
        TaskWaybillTemplateSrcAddressExample example = new TaskWaybillTemplateSrcAddressExample();
        TaskWaybillTemplateSrcAddressExample.Criteria criteria = example.createCriteria();
        criteria.andTaskWaybillTemplateIdEqualTo(taskWaybillTemplate.getTaskWaybillTemplateId());
        taskWaybillTemplateSrcAddressMapper.deleteByExample(example);

        Date createTime = new Date();
        for (TaskWaybillTemplateSrcAddress srcAddress : srcAddresses) {
            srcAddress.setTaskWaybillTemplateId(taskWaybillTemplate.getTaskWaybillTemplateId());
            srcAddress.setCreateTime(createTime);
            srcAddress.setCreateUserId(loginUser.getUserId());

            taskWaybillTemplateSrcAddressMapper.insert(srcAddress);
        }

    }

    public void updateTaskWaybillTemplate(TaskWaybillTemplate taskWaybillTemplate, LoginUser loginUser, Boolean isUpdateCustomerManagerId ) {
//        if(StringUtils.isBlank(taskWaybillTemplate.getDepartmentCode())){
//            throw new BusinessException("departmentCodeNotNull", "waybill.error.departmentCodeNotNull");
//        }
        taskWaybillTemplate.setTruckCustomerId(null);
        if( isUpdateCustomerManagerId == null || !isUpdateCustomerManagerId ) {
            taskWaybillTemplate.setCustomerManagerId(null);
        }
        taskWaybillTemplate.setCustomerId(null);
        taskWaybillTemplate.setProjectId(null);

        taskWaybillTemplate.setLastUpdateTime(new Date());
        taskWaybillTemplate.setLastUpdateUserId(loginUser.getUserId());

        taskWaybillTemplateMapper.updateByPrimaryKeySelective(taskWaybillTemplate);

    }

    /**
     * 保存收货地信息
     *
     * @param destAddresses
     * @param taskWaybillTemplate
     * @param loginUser
     */
    private void saveDestAddr(List<TaskWaybillTemplateDestAddress> destAddresses, TaskWaybillTemplate taskWaybillTemplate, LoginUser loginUser) {
        //删除原有地址信息
        TaskWaybillTemplateDestAddressExample example = new TaskWaybillTemplateDestAddressExample();
        TaskWaybillTemplateDestAddressExample.Criteria criteria = example.createCriteria();
        criteria.andTaskWaybillTemplateIdEqualTo(taskWaybillTemplate.getTaskWaybillTemplateId());
        taskWaybillTemplateDestAddressMapper.deleteByExample(example);

        if (CollectionUtils.isEmpty(destAddresses)) return;

        Date createTime = new Date();

        for (TaskWaybillTemplateDestAddress destAddress : destAddresses) {
            destAddress.setTaskWaybillTemplateId(taskWaybillTemplate.getTaskWaybillTemplateId());
            destAddress.setCreateTime(createTime);
            destAddress.setCreateUserId(loginUser.getUserId());

            taskWaybillTemplateDestAddressMapper.insert(destAddress);
        }
    }


    /**
     * 保存定时下单固定车辆信息
     *
     * @param truckBindings
     */
    private void saveTaskWaybillTemplateTruckBinding(List<TaskWaybillTemplateTruckBinding> truckBindings, TaskWaybillTemplate taskWaybillTemplate) {
        //删除原有固定车辆信息
        this.delTruckBinding(taskWaybillTemplate.getTaskWaybillTemplateId());

        if (CollectionUtils.isEmpty(truckBindings)) return;

        for (TaskWaybillTemplateTruckBinding binding : truckBindings) {
            binding.setTaskWaybillTemplateId(taskWaybillTemplate.getTaskWaybillTemplateId());

            taskWaybillTemplateTruckBindingMapper.insert(binding);
        }

    }


    @Override
    public void updateTaskWaybill(TaskWaybillTemplateCreateVo taskWaybillTemplateCreateVo, LoginEmployee loginEmployee) throws BusinessException {
        this.checkTaskTemplateObjectCommon(taskWaybillTemplateCreateVo);
        if (taskWaybillTemplateCreateVo.getTaskWaybillTemplate().getTaskWaybillTemplateId() == null)
            throw new BusinessException("updateTaskWaybillTemplateIdNullError", "errors.paramCanNotNullWithName", "定时下单模板id");
        Task4Waybill task4Waybill = taskWaybillTemplateCreateVo.getTask4Waybill();
        if (task4Waybill.getTaskId() == null)
            throw new BusinessException("taskIdNullError", "errors.paramCanNotNullWithName", "任务id");
        task4Waybill.setTaskWaybillTemplateId(taskWaybillTemplateCreateVo.getTaskWaybillTemplate().getTaskWaybillTemplateId());

        //指派车队时检验是否已有重复模板
        if(taskWaybillTemplateCreateVo.getTaskWaybillTemplate().getReceiveWay() == 1 && checkIsSameTemplate(taskWaybillTemplateCreateVo)){
            throw new BusinessException("templateIsExistError", "errors.common.prompt", "当前指派的运力已被占用，请更换运力或调整用车时间");
        }

        this.buildTemplate(taskWaybillTemplateCreateVo, loginEmployee);
        //更新模板基础信息
        this.updateTaskWaybillTemplate(taskWaybillTemplateCreateVo.getTaskWaybillTemplate(), loginEmployee, false);
        //地址信息
        this.saveDestAddr(taskWaybillTemplateCreateVo.getDestAddresses(), taskWaybillTemplateCreateVo.getTaskWaybillTemplate(), loginEmployee);
        this.saveSrcAddr(taskWaybillTemplateCreateVo.getSrcAddresses(), taskWaybillTemplateCreateVo.getTaskWaybillTemplate(), loginEmployee);
        //固定车辆
        this.saveTaskWaybillTemplateTruckBinding(taskWaybillTemplateCreateVo.getTaskWaybillTemplateTruckBindings(), taskWaybillTemplateCreateVo.getTaskWaybillTemplate());
        task4WaybillService.updateTask4Waybill(task4Waybill, loginEmployee);
        //定时任务
        publisher.publishEvent(task4WaybillService.get(task4Waybill.getTaskId()));

    }

    @Override
    public void delTaskWaybillTemplate(Integer taskWaybillTemplateId) throws BusinessException {
        if (taskWaybillTemplateId == null)
            throw new BusinessException("delTaskWaybillTemplateIdNullError", "errors.paramCanNotNullWithName", "定时下单模板id");
        //删除模板相关数据
        //----基础信息
        taskWaybillTemplateMapper.deleteByPrimaryKey(taskWaybillTemplateId);
        //----地址信息
        TaskWaybillTemplateSrcAddressExample srcExample = new TaskWaybillTemplateSrcAddressExample();
        TaskWaybillTemplateSrcAddressExample.Criteria srcCriteria = srcExample.createCriteria();
        srcCriteria.andTaskWaybillTemplateIdEqualTo(taskWaybillTemplateId);
        taskWaybillTemplateSrcAddressMapper.deleteByExample(srcExample);

        TaskWaybillTemplateDestAddressExample destExample = new TaskWaybillTemplateDestAddressExample();
        TaskWaybillTemplateDestAddressExample.Criteria destCriteria = destExample.createCriteria();
        destCriteria.andTaskWaybillTemplateIdEqualTo(taskWaybillTemplateId);
        taskWaybillTemplateDestAddressMapper.deleteByExample(destExample);

        //----固定车辆
        //删除固定车辆
        this.delTruckBinding(taskWaybillTemplateId);
        Task4Waybill task4Waybill = task4WaybillService.getByTemplateId(taskWaybillTemplateId);
        if (task4Waybill != null) {
            //删除任务
            task4WaybillService.delTask4Waybill(task4Waybill.getTaskId());
            //删除任务执行报告
            task4WaybillReportService.delTaskReport(task4Waybill.getTaskId());
        }

        //----redis
        publisher.publishEvent(taskWaybillTemplateId);

    }


    //删除固定车辆
    private void delTruckBinding(Integer taskWaybillTemplateId) {
        TaskWaybillTemplateTruckBindingExample truckBindingExample = new TaskWaybillTemplateTruckBindingExample();
        TaskWaybillTemplateTruckBindingExample.Criteria criteria = truckBindingExample.createCriteria();
        criteria.andTaskWaybillTemplateIdEqualTo(taskWaybillTemplateId);
        taskWaybillTemplateTruckBindingMapper.deleteByExample(truckBindingExample);
    }

    private List<TaskWaybillTemplateTruckBinding> getTruckBinding(Integer taskWaybillTemplateId) {
        TaskWaybillTemplateTruckBindingExample truckBindingExample = new TaskWaybillTemplateTruckBindingExample();
        TaskWaybillTemplateTruckBindingExample.Criteria criteria = truckBindingExample.createCriteria();
        criteria.andTaskWaybillTemplateIdEqualTo(taskWaybillTemplateId);
        List<TaskWaybillTemplateTruckBinding> bindingList = taskWaybillTemplateTruckBindingMapper.selectByExample(truckBindingExample);

        return bindingList;
    }

    @Override
    public List<TaskWaybillTemplateTruckBinding> getTruckBindingByTemplateId(Integer taskWaybillTemplateId) throws BusinessException {
        if (taskWaybillTemplateId == null) return null;

        return this.getTruckBinding(taskWaybillTemplateId);
    }

    private List<DriverTruckInfoBo> buildBindingTruckInfo(Integer taskWaybillTemplateId, LoginUser loginUser) {
        List<TaskWaybillTemplateTruckBinding> bindingList = this.getTruckBinding(taskWaybillTemplateId);

        if (CollectionUtils.isEmpty(bindingList)) return null;

        List<DriverTruckInfoBo> driverTruckInfoBos = new ArrayList<>();
        for (TaskWaybillTemplateTruckBinding binding : bindingList) {
            DriverTruckInfoBo driverTruckInfoBo = this.doBuildDriverTruckInfo(binding.getTruckId(), loginUser);
            if (driverTruckInfoBo == null) continue;

            driverTruckInfoBos.add(driverTruckInfoBo);
        }

        return driverTruckInfoBos;
    }

    private DriverTruckInfoBo doBuildDriverTruckInfo(Integer truckId, LoginUser loginUser) {
        if (truckId == null) return null;

        //获取车辆信息
        com.juma.vms.truck.domain.Truck truck = vmsCommonService.loadTruckByTruckId(truckId);
        if (null == truck) {
            return null;
        }

        CapacityPool capacityPool = vmsCommonService.loadCapacityByTruckId(truckId, loginUser);
        Driver driver = null;
        if (null != capacityPool && null != capacityPool.getStatus() && capacityPool.getStatus()) {
            driver = vmsCommonService.loadDriverByDriverId(capacityPool.getDriverId());
        }

        DriverTruckInfoBo driverTruckInfoBo = customerManagerService.buildDriverTruckInfo(truck, driver, capacityPool, loginUser);

        return driverTruckInfoBo;
    }


    @Override
    public TaskWaybillTemplate get(Integer taskWaybillTemplateId) throws BusinessException {
        if (taskWaybillTemplateId == null)
            throw new BusinessException("taskWaybillTemplateIdNullError", "errors.paramCanNotNullWithName", "任务模板id");
        TaskWaybillTemplate template = taskWaybillTemplateMapper.selectByPrimaryKey(taskWaybillTemplateId);

        return template;
    }


    @Override
    public TaskWaybillTemplateDetailVo getDetail(Integer taskWaybillTemplateId, LoginUser loginUser) throws BusinessException {
        if (taskWaybillTemplateId == null)
            throw new BusinessException("taskWaybillTemplateIdNullError", "errors.paramCanNotNullWithName", "任务模板id");

        TaskWaybillTemplateDetailVo detailVo = new TaskWaybillTemplateDetailVo();
        //模板
        TaskWaybillTemplate template = taskWaybillTemplateMapper.selectByPrimaryKey(taskWaybillTemplateId);
        if (template == null) throw new BusinessException("TaskWaybillTemplateNull", "errors.notExistWithName", "任务模板");
        detailVo.setTaskWaybillTemplate(template);
        //项目
        com.juma.tgm.project.domain.v2.Project project = projectService.getProjectV2(template.getProjectId());
        ProjectVoApp projectVoApp = new ProjectVoApp();
        BeanUtils.copyProperties(project, projectVoApp);
        if (project == null)
            throw new BusinessException("TaskWaybillTemplateProjectNull", "errors.notExistWithName", "任务所属项目");
        //项目物流产品标签
        Customer4TmsBo customer4TmsBo = crm4TmsService.findProductAndDepId(null, loginUser);
        if(customer4TmsBo != null){
            Map<String, Object> logisticsMap = customer4TmsBo.getLogisticsProducts();
            Object logistticsOb = logisticsMap.get(project.getLogisticsLabel());
            projectVoApp.setLogisticsName(logistticsOb == null ? "" : (String)logistticsOb);
        }
        detailVo.setProject(projectVoApp);
        //任务
        Task4Waybill task4Waybill = task4WaybillService.getByTemplateId(template.getTaskWaybillTemplateId());
        if (task4Waybill == null) throw new BusinessException("task4WaybillNull", "errors.notExistWithName", "任务");

        detailVo.setTask4WaybillRaw(task4Waybill);
        //客户信息
        CustomerInfo customerInfo = customerInfoService.findCusInfoById(template.getCustomerId());
        detailVo.setCustomerInfoRaw(customerInfo);
        //客户经理
        User employeeUser = employeeService.loadUserByEmployeeId(task4Waybill.getEmployeeId(), loginUser);
        detailVo.setManagerName(employeeUser == null ? "" : employeeUser.getName());
        //固定运力
        detailVo.setDriverTruckInfoBos(this.buildBindingTruckInfo(taskWaybillTemplateId, loginUser));
        //线路名称
        if (template.getRoadMapId() != null) {
            RoadMap roadMap = roadMapService.getRoadMap(template.getRoadMapId());
            detailVo.setRoadMapRaw(roadMap);
        }
        TruckRequire truckRequire = detailVo.buildTruckRequire();
        truckRequire.setTruckRequireStr(truckRequireService.getTruckRequire(truckRequire, null));
        detailVo.setTruckRequire(truckRequire);

        //线路信息
        detailVo.setSrcAddresses(this.getTemplateSrcAddressByTemplateId(taskWaybillTemplateId));
        detailVo.setDestAddresses(this.getTemplateDestAddressByTemplateId(taskWaybillTemplateId));

        //项目经理
        User projectManagerUser = userService.loadUser(project.getProjectManagerUserId());
        if(projectManagerUser != null){
            detailVo.setProjectManagerName(projectManagerUser.getName());
        }
        //最近维护人
        User lastUpdateUser = userService.loadUser(task4Waybill.getLastUpdateUserId());
        if(lastUpdateUser != null){
            detailVo.setLastUpdateName(lastUpdateUser.getName());
        }
        return detailVo;
    }


    /**
     * 获取取货地
     *
     * @param taskWaybillTemplateId
     * @return
     */
    public List<TaskWaybillTemplateSrcAddress> getTemplateSrcAddressByTemplateId(Integer taskWaybillTemplateId) {
        if (taskWaybillTemplateId == null) return null;

        TaskWaybillTemplateSrcAddressExample srcExample = new TaskWaybillTemplateSrcAddressExample();
        TaskWaybillTemplateSrcAddressExample.Criteria srcCriteria = srcExample.createCriteria();
        srcCriteria.andTaskWaybillTemplateIdEqualTo(taskWaybillTemplateId);
        List<TaskWaybillTemplateSrcAddress> srcAddressList = taskWaybillTemplateSrcAddressMapper.selectByExample(srcExample);

        return srcAddressList;
    }


    /**
     * 获取配送地
     *
     * @param taskWaybillTemplateId
     * @return
     */
    public List<TaskWaybillTemplateDestAddress> getTemplateDestAddressByTemplateId(Integer taskWaybillTemplateId) {
        if (taskWaybillTemplateId == null) return null;

        TaskWaybillTemplateDestAddressExample srcExample = new TaskWaybillTemplateDestAddressExample();
        TaskWaybillTemplateDestAddressExample.Criteria srcCriteria = srcExample.createCriteria();
        srcCriteria.andTaskWaybillTemplateIdEqualTo(taskWaybillTemplateId);
        List<TaskWaybillTemplateDestAddress> destAddressList = taskWaybillTemplateDestAddressMapper.selectByExample(srcExample);

        return destAddressList;
    }

    @Override
    public void doTask() throws BusinessException {

    }

    @Override
    public WaybillDetailInfo getWaybillDetailInfo(Integer taskWaybillTemplateId, LoginUser loginUser) throws BusinessException {
        WaybillDetailInfo detailInfo = new WaybillDetailInfo();
        TaskWaybillTemplate taskWaybillTemplate = this.get(taskWaybillTemplateId);
        if (taskWaybillTemplate == null) {
            throw new BusinessException("taskWaybillTemplateId", "未知的 taskWaybillTemplateId ：" + taskWaybillTemplateId);
        }

        // 运单
        Waybill waybill = this.getWaybill(taskWaybillTemplate);
        detailInfo.setWaybill(waybill);
        // 线路信息
        List<WaybillDeliveryAddress> waybillDeliveryAddresses = this.getWaybillDeliveryAddress(taskWaybillTemplate);
        List<WaybillReceiveAddress> waybillReceiveAddressList = this.getWaybillReceiveAddress(taskWaybillTemplate);
        detailInfo.setWaybillDeliveryAddresses(waybillDeliveryAddresses);
        detailInfo.setWaybillReceiveAddresses(waybillReceiveAddressList);
        // 客户
        CustomerInfo customerInfo = customerInfoService.findCusInfoById(taskWaybillTemplate.getCustomerId());
        detailInfo.setCustomerInfo(customerInfo);
        detailInfo.setCanUseCustomerInfo(customerInfoService.customerBelongToManager(taskWaybillTemplate.getCustomerId(), taskWaybillTemplate.getCustomerManagerId()));
        // 用车要求
        detailInfo.setTruckRequire(JSONObject.parseObject(taskWaybillTemplate.getRequireJson(), TruckRequire.class));
        detailInfo.setTruckRequireStr(truckRequireService.getTruckRequire(detailInfo.getTruckRequire(), null));
        //抢单超时倒计时
        detailInfo.setExpireTimeLength(this.getExpireTimeLength());
        // 计价规则
        WaybillParam waybillParam = new WaybillParam();
        waybillParam.setProjectFreightRuleJson(taskWaybillTemplate.getProjectFreightRuleJson());
        detailInfo.setWaybillParam(waybillParam);
        // 实际使用情况
        //detailInfo.setValuationConst(waybillParam.getValuationConstJson());
        ProjectFreightRuleVo rule = null;
        if (StringUtils.isNotBlank(waybillParam.getProjectFreightRuleJson())) {

            List<ValuationWay> valuationWays = new ArrayList<ValuationWay>();
            detailInfo.setValuationWays(valuationWays);

            rule = JSON.parseObject(waybillParam.getProjectFreightRuleJson(), ProjectFreightRuleVo.class);
            rule.setFactorJson(rule.getFactorJson());
            detailInfo.setProjectFreightRule(rule);
            rule.setTruckTypeId(detailInfo.getTruckRequire().getTruckTypeId());
            // 获取计价方式
            if (rule != null) {
                // 车型名称
                String truckTypeName = truckTypeService.findTruckTypeNameByTypeId(rule.getTruckTypeId());
                rule.setTruckTypeName(truckTypeName);
            }
        }

//        //计价方式
//        List<FreightFactor> factors = freightFactorService.findByFreightWay(FreightEnum.PROJECT.getCode(), loginUser);
//        detailInfo.setAllFactors(factors);
        //Constants
        //客服电话
        detailInfo.setCustomerServiceTel(serviceConfService.findCustomerServiceTel(waybill.getRegionCode(), loginUser));
        return detailInfo;
    }

    @Override
    public List<TaskWaybillTemplate> findByCustomerId(Integer customerId) throws BusinessException {
        TaskWaybillTemplateExample taskWaybillTemplateExample = new TaskWaybillTemplateExample();
        TaskWaybillTemplateExample.Criteria criteria  = taskWaybillTemplateExample.createCriteria();
        criteria.andCustomerIdEqualTo( customerId );
        return this.taskWaybillTemplateMapper.selectByExample( taskWaybillTemplateExample );
    }

    /***
     * 根据 模板 拼装 运单创建相关信息
     *
     * @param taskWaybillTemplate 模板相关信息
     * */
    private Waybill getWaybill(TaskWaybillTemplate taskWaybillTemplate) throws BusinessException {
        Waybill waybill = new Waybill();
        // 相同的先复制
        BeanUtils.copyProperties(taskWaybillTemplate, waybill);
        waybill.setCreateUserId(null);
        waybill.setCreateTime(null);
        waybill.setWaybillIds(null);
        if (taskWaybillTemplate.getBusinessBranch() != null) {
            waybill.setBusinessBranch(Integer.valueOf(taskWaybillTemplate.getBusinessBranch()));
        }
        if (taskWaybillTemplate.getReceiptType() != null) {
            waybill.setReceiptType(Integer.valueOf(taskWaybillTemplate.getReceiptType()));
        }
        if (taskWaybillTemplate.getReceiveWay() != null) {
            waybill.setReceiveWay(Integer.valueOf(taskWaybillTemplate.getReceiveWay()));
        }
        if (taskWaybillTemplate.getDeliveryTimePoint() != null) {
            // 计划用车时间点 只有小时分钟
            String f = DateUtil.format(new Date(), DateUtil.YYYYMMDD);//年月日
            Date date = null;
            try {
                date = DateUtil.parse(f + " " + taskWaybillTemplate.getDeliveryTimePoint(), DateUtil.YYYYMMDDHHMM);
            } catch (Exception e) {
                log.warn("时间转换错误", e);
            }
            waybill.setPlanDeliveryTime(date);
        }
        if (taskWaybillTemplate.getDeliveryTimePoint() != null && taskWaybillTemplate.getFinishTimePoint() != null) {
            // 单位分钟 预估耗时
            // 计划用车时间点 只有小时分钟
            String f = DateUtil.format(new Date(), DateUtil.YYYYMMDD);//年月日
            Date date = null;
            try {
                date = DateUtil.parse(f + " " + taskWaybillTemplate.getDeliveryTimePoint(), DateUtil.YYYYMMDDHHMM);
            } catch (Exception e) {
                log.warn("时间转换错误", e);
            }
            Long endTime = date.getTime() + taskWaybillTemplate.getFinishTimePoint() * 60 * 1000;// 毫秒数
            waybill.setCmEstimateFinishTime(new Date(endTime));
        }
        if (taskWaybillTemplate.getNeedDeliveryPointNote() != null) {
            waybill.setNeedDeliveryPointNote(Integer.valueOf(taskWaybillTemplate.getNeedDeliveryPointNote()));
        }
        return waybill;
    }

    /**
     * 根据模板获取路线信息(  发货地 )
     */
    private List<WaybillDeliveryAddress> getWaybillDeliveryAddress(TaskWaybillTemplate taskWaybillTemplate) throws BusinessException {
        List<TaskWaybillTemplateSrcAddress> taskWaybillTemplateSrcAddresses = this.getTemplateSrcAddressByTemplateId(taskWaybillTemplate.getTaskWaybillTemplateId());
        List<WaybillDeliveryAddress> waybillDeliveryAddresses = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(taskWaybillTemplateSrcAddresses)) {
            for (TaskWaybillTemplateSrcAddress taskWaybillTemplateSrcAddress : taskWaybillTemplateSrcAddresses) {
                WaybillDeliveryAddress waybillDeliveryAddress = new WaybillDeliveryAddress();
                BeanUtils.copyProperties(taskWaybillTemplateSrcAddress, waybillDeliveryAddress);
                waybillDeliveryAddresses.add(waybillDeliveryAddress);
            }
        }
        return waybillDeliveryAddresses;
    }

    /**
     * 根据模板获取 路线信息( 运单收货地址)
     */
    private List<WaybillReceiveAddress> getWaybillReceiveAddress(TaskWaybillTemplate taskWaybillTemplate) throws BusinessException {
        List<TaskWaybillTemplateDestAddress> taskWaybillTemplateDestAddresses = this.getTemplateDestAddressByTemplateId(taskWaybillTemplate.getTaskWaybillTemplateId());
        List<WaybillReceiveAddress> waybillReceiveAddressList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(taskWaybillTemplateDestAddresses)) {
            for (TaskWaybillTemplateDestAddress taskWaybillTemplateDestAddress : taskWaybillTemplateDestAddresses) {
                WaybillReceiveAddress waybillReceiveAddress = new WaybillReceiveAddress();
                BeanUtils.copyProperties(taskWaybillTemplateDestAddress, waybillReceiveAddress);
                waybillReceiveAddressList.add(waybillReceiveAddress);
            }
        }
        return waybillReceiveAddressList;
    }

    /**
     * 获取超时时长（毫秒）
     *
     * @return
     */
    public long getExpireTimeLength() {
        long expireTimeLength = 10 * 60 * 1000;

        List<ConfParamOption> optionList = confParamService.findParamOptions(Constants.COMPETE_BILL_KEY_EXPIRE_TIME);

        if (CollectionUtils.isNotEmpty(optionList)) {
            ConfParamOption option = optionList.get(0);
            if (StringUtils.isNumeric(option.getOptionValue())) {
                expireTimeLength = Long.parseLong(option.getOptionValue()) * 60 * 1000;
            }
        } else {
            log.info("没有配置经纪人运单超时时间，使用默认值10分钟");
        }

        return expireTimeLength;
    }

}
