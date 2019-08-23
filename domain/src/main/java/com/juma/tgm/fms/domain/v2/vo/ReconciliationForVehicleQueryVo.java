package com.juma.tgm.fms.domain.v2.vo;

import com.juma.tgm.fms.domain.v2.ReconciliationItemNew;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: ReconciliationForVehicleQueryVo
 * @Description:
 * @author: liang
 * @date: 2018-06-06 11:54
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class ReconciliationForVehicleQueryVo extends ReconciliationItemNew implements Serializable {
    /**
     * 开始配送时间
     */
    private Date timeStart;

    /**
     * 结束时间
     */
    private Date timeEnd;



    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

}
