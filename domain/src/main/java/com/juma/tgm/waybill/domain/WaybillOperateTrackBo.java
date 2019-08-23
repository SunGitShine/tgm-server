package com.juma.tgm.waybill.domain;

import com.juma.auth.user.domain.User;
import com.juma.tgm.common.Constants;
import org.apache.commons.lang.time.DateFormatUtils;

import java.io.Serializable;

/**
 * 运单操作轨迹BO
 * 
 * @author weilibin
 *
 */

public class WaybillOperateTrackBo implements Serializable {

    private static final long serialVersionUID = 7408247523026436523L;
    private WaybillOperateTrack waybillOperateTrack;
    private User user;
    private String distanceStr;

    public WaybillOperateTrack getWaybillOperateTrack() {
        return waybillOperateTrack;
    }

    public void setWaybillOperateTrack(WaybillOperateTrack waybillOperateTrack) {
        this.waybillOperateTrack = waybillOperateTrack;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDistanceStr() {
        return distanceStr;
    }

    public void setDistanceStr(String distanceStr) {
        this.distanceStr = distanceStr;
    }

    public String getCreateDate() {
        return waybillOperateTrack == null ? null
                : (waybillOperateTrack.getCreateTime() == null ? null
                        : DateFormatUtils.format(waybillOperateTrack.getCreateTime(), Constants.YYYYMMDDHHMM.toPattern()));
    }

}