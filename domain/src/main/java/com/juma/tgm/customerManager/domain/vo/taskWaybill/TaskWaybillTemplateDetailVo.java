package com.juma.tgm.customerManager.domain.vo.taskWaybill;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.customer.domain.TruckCustomer;
import com.juma.tgm.customerManager.domain.Task4Waybill;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplate;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateDestAddress;
import com.juma.tgm.customerManager.domain.TaskWaybillTemplateSrcAddress;
import com.juma.tgm.project.domain.RoadMap;
import com.juma.tgm.project.vo.v2.ProjectVoApp;
import com.juma.tgm.truck.domain.bo.DriverTruckInfoBo;
import com.juma.tgm.waybill.domain.TruckRequire;

/**
 * @ClassName: TaskWaybillTemplateDetailVo
 * @Description:
 * @author: liang
 * @date: 2018-09-30 10:35
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class TaskWaybillTemplateDetailVo implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(TaskWaybillTemplateDetailVo.class);

    /**
     * 运单模板
     */
    private TaskWaybillTemplate taskWaybillTemplate;

    /**
     * 所属项目
     */
    private ProjectVoApp project;

    /**
     * 所属任务
     */
    @JSONField(serialize = false)
    private Task4Waybill task4WaybillRaw;

    /**
     * 用车人
     */
    @JSONField(serialize = false)
    private TruckCustomer truckCustomerRaw;

    /**
     * 客户经理
     */
    private String managerName;

    /**
     * 客户经理
     */
    private String projectManagerName;

    /**
     * 固定运力
     */
    private List<DriverTruckInfoBo> driverTruckInfoBos;

    /**
     * 线路（自定义时为空）
     */
    @JSONField(serialize = false)
    private RoadMap roadMapRaw;

    /**
     * 用车要求
     */
    private TruckRequire truckRequire;


    @JSONField(serialize = false)
    private CustomerInfo customerInfoRaw;


    /**
     * 取货地
     */
    private List<TaskWaybillTemplateSrcAddress> srcAddresses;

    /**
     * 配送地
     */
    private List<TaskWaybillTemplateDestAddress> destAddresses;

    //最近维护人
    private String lastUpdateName;


    public TaskWaybillTemplate getTaskWaybillTemplate() {
        return taskWaybillTemplate;
    }

    public void setTaskWaybillTemplate(TaskWaybillTemplate taskWaybillTemplate) {
        this.taskWaybillTemplate = taskWaybillTemplate;
    }

    public ProjectVoApp getProject() {
        return project;
    }

    public void setProject(ProjectVoApp project) {
        this.project = project;
    }

    public Task4Waybill getTask4Waybill() {
        return task4WaybillRaw;
    }

    public void setTask4WaybillRaw(Task4Waybill task4WaybillRaw) {
        this.task4WaybillRaw = task4WaybillRaw;
    }

    public TruckCustomer getTruckCustomer() {
        return truckCustomerRaw;
    }

    public void setTruckCustomerRaw(TruckCustomer truckCustomer) {
        this.truckCustomerRaw = truckCustomer;
    }

    public List<DriverTruckInfoBo> getDriverTruckInfoBos() {
        return driverTruckInfoBos;
    }

    public void setDriverTruckInfoBos(List<DriverTruckInfoBo> driverTruckInfoBos) {
        this.driverTruckInfoBos = driverTruckInfoBos;
    }

    public List<TaskWaybillTemplateSrcAddress> getSrcAddresses() {
        return srcAddresses;
    }

    public void setSrcAddresses(List<TaskWaybillTemplateSrcAddress> srcAddresses) {
        this.srcAddresses = srcAddresses;
    }

    public List<TaskWaybillTemplateDestAddress> getDestAddresses() {
        return destAddresses;
    }

    public void setDestAddresses(List<TaskWaybillTemplateDestAddress> destAddresses) {
        this.destAddresses = destAddresses;
    }

    public void setRoadMapRaw(RoadMap roadMapRaw) {
        this.roadMapRaw = roadMapRaw;
    }

    public RoadMap getRoadMap() {
        return roadMapRaw;
    }


    public TruckRequire buildTruckRequire(){
        if(StringUtils.isEmpty(this.taskWaybillTemplate.getRequireJson())) return null;

        TruckRequire require = null;
        try {
            require = JSONObject.parseObject(this.taskWaybillTemplate.getRequireJson(), TruckRequire.class);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }

        return require;
    }

    public TruckRequire getTruckRequire() {
        return truckRequire;
    }

    public void setTruckRequire(TruckRequire truckRequire) {
        this.truckRequire = truckRequire;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }


    public void setCustomerInfoRaw(CustomerInfo customerInfoRaw) {
        this.customerInfoRaw = customerInfoRaw;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfoRaw;
    }

    public String getProjectManagerName() {
        return projectManagerName;
    }

    public void setProjectManagerName(String projectManagerName) {
        this.projectManagerName = projectManagerName;
    }

    public String getLastUpdateName() {
        return lastUpdateName;
    }

    public void setLastUpdateName(String lastUpdateName) {
        this.lastUpdateName = lastUpdateName;
    }
}
