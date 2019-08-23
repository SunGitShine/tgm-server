package com.juma.tgm.customerManager.domain.vo.taskWaybill;

import java.io.Serializable;

/**
 * @ClassName: Task4WaybillOverview
 * @Description:
 * @author: liang
 * @date: 2018-09-30 10:28
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class Task4WaybillOverview implements Serializable {


    /**
     * 任务总量
     */
    private Integer all;
    /**
     * 今日任务
     */
    private Integer today;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    public Integer getToday() {
        return today;
    }

    public void setToday(Integer today) {
        this.today = today;
    }
}
