package com.juma.tgm.customerManager.domain.vo.taskWaybill;

import java.io.Serializable;

/**
 * @ClassName: Task4WaybillListCountVo
 * @Description:
 * @author: liang
 * @date: 2018-10-13 14:35
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class Task4WaybillListCountVo implements Serializable {

    /**
     * 总计
     */
    private Integer total;

    /**
     * 今日任务数量
     */
    private Integer todayCount;

    /**
     * 未读消息
     */
    private Boolean unreadReport;


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTodayCount() {
        return todayCount;
    }

    public void setTodayCount(Integer todayCount) {
        this.todayCount = todayCount;
    }

    public Boolean getUnreadReport() {
        return unreadReport;
    }

    public void setUnreadReport(Boolean unreadReport) {
        this.unreadReport = unreadReport;
    }
}
