package com.juma.tgm.customerManager.domain.vo.taskWaybill;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Task4WaybillReportCountVo
 * @Description:
 * @author: liang
 * @date: 2018-10-08 15:01
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class Task4WaybillReportCountVo implements Serializable {

    /**
     * 总数
     */
    private Integer total;

    /**
     * 成功数量
     */
    private Integer successCount;

    /**
     * 失败数量
     */
    private Integer failCount;

    /**
     * 执行时间
     */
    private Date executeDate;

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }


    public Date getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(Date executeDate) {
        this.executeDate = executeDate;
    }
}
