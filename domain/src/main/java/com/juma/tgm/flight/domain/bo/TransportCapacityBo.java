package com.juma.tgm.flight.domain.bo;

import com.juma.tgm.flight.domain.bo.vo.FreeVehicleOverViewVo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * 空闲运力
 * @ClassName: TransportCapacityBo
 * @Description:
 * @author: liang
 * @date: 2017-07-10 14:01
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class TransportCapacityBo implements Serializable {


    /**
     * 统计开始时间
     */
    private Date countStartTime;

    /**
     * 统计结束时间
     */
    private Date countEndTime;

    /**
     * 空闲统计时长
     */
    private Integer validTimeLength;

    /**
     * 时刻概要列表
     */
    private List<FreeVehicleOverViewVo> overViewList = new ArrayList<>();


    public void addOverView(FreeVehicleOverViewVo overViewVo){
        if(this.overViewList == null){
            this.overViewList = new ArrayList<>();
        }
        this.overViewList.add(overViewVo);
    }

    public Date getCountStartTime() {
        return countStartTime;
    }

    public void setCountStartTime(Date countStartTime) {
        this.countStartTime = countStartTime;
    }

    public Date getCountEndTime() {
        return countEndTime;
    }

    public void setCountEndTime(Date countEndTime) {
        this.countEndTime = countEndTime;
    }

    public Integer getValidTimeLength() {
        return validTimeLength;
    }

    public void setValidTimeLength(Integer validTimeLength) {
        this.validTimeLength = validTimeLength;
    }


    public List<FreeVehicleOverViewVo> getOverViewList() {
        return overViewList;
    }

    public void setOverViewList(List<FreeVehicleOverViewVo> overViewList) {
        this.overViewList = overViewList;
    }
}
