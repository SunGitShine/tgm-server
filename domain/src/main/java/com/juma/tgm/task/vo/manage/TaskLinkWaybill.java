package com.juma.tgm.task.vo.manage;

import com.juma.tgm.task.vo.gateway.Depot;
import com.juma.tgm.task.vo.gateway.FixedDeliveryInfo;
import com.juma.tgm.task.vo.gateway.NotFixedDeliveryInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "任务关联运单")
public class TaskLinkWaybill implements Serializable {

    @ApiModelProperty(value = "任务id")
    private Integer taskId;

    @ApiModelProperty(value = "任务编号")
    private String taskNo;

    @ApiModelProperty(value = "预估里程")
    private String estimateMileageRange;

    @ApiModelProperty(value = "预估用时")
    private String estimateTimeCostRange;

    @ApiModelProperty(value = "仓库信息")
    private Depot depot;

    @ApiModelProperty(value = "是否固定点")
    private Boolean isFixedDelivery;

    @ApiModelProperty(value = "固定点")
    private List<FixedDeliveryInfo> fixedDeliveries = new ArrayList<>();

    @ApiModelProperty(value = "非固定点")
    private NotFixedDeliveryInfo notFixedDeliveryInfo;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public String getEstimateMileageRange() {
        return estimateMileageRange;
    }

    public void setEstimateMileageRange(String estimateMileageRange) {
        this.estimateMileageRange = estimateMileageRange;
    }

    public String getEstimateTimeCostRange() {
        return estimateTimeCostRange;
    }

    public void setEstimateTimeCostRange(String estimateTimeCostRange) {
        this.estimateTimeCostRange = estimateTimeCostRange;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public Boolean getIsFixedDelivery() {
        return isFixedDelivery;
    }

    public void setIsFixedDelivery(Boolean isFixedDelivery) {
        this.isFixedDelivery = isFixedDelivery;
    }

    public List<FixedDeliveryInfo> getFixedDeliveries() {
        return fixedDeliveries;
    }

    public void setFixedDeliveries(List<FixedDeliveryInfo> fixedDeliveries) {
        this.fixedDeliveries = fixedDeliveries;
    }

    public NotFixedDeliveryInfo getNotFixedDeliveryInfo() {
        return notFixedDeliveryInfo;
    }

    public void setNotFixedDeliveryInfo(NotFixedDeliveryInfo notFixedDeliveryInfo) {
        this.notFixedDeliveryInfo = notFixedDeliveryInfo;
    }
}
