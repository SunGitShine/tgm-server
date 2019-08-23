package com.juma.tgm.crm.domain;

import com.juma.crm.customer.domain.ConsignorVisitRecord;

/**
 * @ClassName: ConsignorVisitRecordVo
 * @Description:
 * @author: liang
 * @date: 2017-03-15 17:14
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class ConsignorVisitRecordTgmVo extends ConsignorVisitRecord {


    /**
     * 车辆需求程度
     */
    private String vehicleDemandLevelStr;

    /**
     * 车辆需求类型
     */
    private String vehicleDemandTypeStr;

    /**
     * 拜访方式
     */
    private String visitWayStr;

    /**
     * 评估结果
     */
    private String evaluationResultsStr;

    public String getVehicleDemandLevelStr() {
        return vehicleDemandLevelStr;
    }

    public void setVehicleDemandLevelStr(String vehicleDemandLevelStr) {
        this.vehicleDemandLevelStr = vehicleDemandLevelStr;
    }

    public String getVehicleDemandTypeStr() {
        return vehicleDemandTypeStr;
    }

    public void setVehicleDemandTypeStr(String vehicleDemandTypeStr) {
        this.vehicleDemandTypeStr = vehicleDemandTypeStr;
    }

    public String getVisitWayStr() {
        return visitWayStr;
    }

    public void setVisitWayStr(String visitWayStr) {
        this.visitWayStr = visitWayStr;
    }

    public String getEvaluationResultsStr() {
        return evaluationResultsStr;
    }

    public void setEvaluationResultsStr(String evaluationResultsStr) {
        this.evaluationResultsStr = evaluationResultsStr;
    }
}
