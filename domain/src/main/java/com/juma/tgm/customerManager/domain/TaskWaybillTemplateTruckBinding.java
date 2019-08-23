package com.juma.tgm.customerManager.domain;

import java.io.Serializable;

public class TaskWaybillTemplateTruckBinding implements Serializable {
    private Integer taskWaybillTemplateTruckBindingId;

    private Integer taskWaybillTemplateId;

    private Integer truckId;

    private static final long serialVersionUID = 1L;

    public Integer getTaskWaybillTemplateTruckBindingId() {
        return taskWaybillTemplateTruckBindingId;
    }

    public void setTaskWaybillTemplateTruckBindingId(Integer taskWaybillTemplateTruckBindingId) {
        this.taskWaybillTemplateTruckBindingId = taskWaybillTemplateTruckBindingId;
    }

    public Integer getTaskWaybillTemplateId() {
        return taskWaybillTemplateId;
    }

    public void setTaskWaybillTemplateId(Integer taskWaybillTemplateId) {
        this.taskWaybillTemplateId = taskWaybillTemplateId;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TaskWaybillTemplateTruckBinding other = (TaskWaybillTemplateTruckBinding) that;
        return (this.getTaskWaybillTemplateTruckBindingId() == null ? other.getTaskWaybillTemplateTruckBindingId() == null : this.getTaskWaybillTemplateTruckBindingId().equals(other.getTaskWaybillTemplateTruckBindingId()))
            && (this.getTaskWaybillTemplateId() == null ? other.getTaskWaybillTemplateId() == null : this.getTaskWaybillTemplateId().equals(other.getTaskWaybillTemplateId()))
            && (this.getTruckId() == null ? other.getTruckId() == null : this.getTruckId().equals(other.getTruckId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTaskWaybillTemplateTruckBindingId() == null) ? 0 : getTaskWaybillTemplateTruckBindingId().hashCode());
        result = prime * result + ((getTaskWaybillTemplateId() == null) ? 0 : getTaskWaybillTemplateId().hashCode());
        result = prime * result + ((getTruckId() == null) ? 0 : getTruckId().hashCode());
        return result;
    }
}