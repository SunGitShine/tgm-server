package com.juma.tgm.customerManager.domain.vo.taskWaybill;

import com.juma.tgm.customerManager.domain.Task4WaybillReport;

import java.io.Serializable;
import java.util.List;

/**
 * 任务执行报告查询参数vo
 * @ClassName: Task4WaybillReportQueryVo
 * @Description:
 * @author: liang
 * @date: 2018-10-08 11:24
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class Task4WaybillReportQueryVo extends Task4WaybillReport implements Serializable {

    private List<Byte> executeStatus;


    public List<Byte> getExecuteStatus() {
        return executeStatus;
    }

    public void setExecuteStatus(List<Byte> executeStatus) {
        this.executeStatus = executeStatus;
    }
}
