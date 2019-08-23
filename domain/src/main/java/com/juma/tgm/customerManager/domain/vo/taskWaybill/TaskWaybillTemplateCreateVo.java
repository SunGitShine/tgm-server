package com.juma.tgm.customerManager.domain.vo.taskWaybill;

import com.juma.tgm.customerManager.domain.*;
import com.juma.tgm.waybill.domain.TruckRequire;

import java.io.Serializable;
import java.util.List;

/**
 * 新建定时下单模板vo
 *
 * @ClassName: TaskWaybillTemplateCreateVo
 * @Description:
 * @author: liang
 * @date: 2018-09-28 11:44
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class TaskWaybillTemplateCreateVo implements Serializable {


    /**
     * 定时发单基础信息
     */
    private TaskWaybillTemplate taskWaybillTemplate;

    /**
     * 发货地
     */
    private List<TaskWaybillTemplateSrcAddress> srcAddresses;

    /**
     * 目的地
     */
    private List<TaskWaybillTemplateDestAddress> destAddresses;


    /**
     * 任务信息
     */
    private Task4Waybill task4Waybill;


    /**
     * 固定车辆
     */
    private List<TaskWaybillTemplateTruckBinding> taskWaybillTemplateTruckBindings;

    /**
     * 用车要求
     */
    private TruckRequire truckRequire;



    public TaskWaybillTemplate getTaskWaybillTemplate() {
        return taskWaybillTemplate;
    }

    public void setTaskWaybillTemplate(TaskWaybillTemplate taskWaybillTemplate) {
        this.taskWaybillTemplate = taskWaybillTemplate;
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

    public Task4Waybill getTask4Waybill() {
        return task4Waybill;
    }

    public void setTask4Waybill(Task4Waybill task4Waybill) {
        this.task4Waybill = task4Waybill;
    }

    public List<TaskWaybillTemplateTruckBinding> getTaskWaybillTemplateTruckBindings() {
        return taskWaybillTemplateTruckBindings;
    }

    public void setTaskWaybillTemplateTruckBindings(List<TaskWaybillTemplateTruckBinding> taskWaybillTemplateTruckBindings) {
        this.taskWaybillTemplateTruckBindings = taskWaybillTemplateTruckBindings;
    }


    public TruckRequire getTruckRequire() {
        return truckRequire;
    }

    public void setTruckRequire(TruckRequire truckRequire) {
        this.truckRequire = truckRequire;
    }
}
