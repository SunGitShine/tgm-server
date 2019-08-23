package com.juma.tgm.truck.domain.vo;

import java.io.Serializable;

/**
 * 用于车队搜索返回值
 * 注意：只返回主键信息，具体数据由业务组装
 * @ClassName: TruckFleetDriverRelationVo
 * @Description:
 * @author: liang
 * @date: 2018-09-02 15:19
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class TruckFleetDriverRelationVo implements Serializable {

    /**
     * 车队
     *
     */
    private Integer truckFleetId;
    /**
     * 车队中的车
     *
     */
    private Integer truckId;


    public Integer getTruckFleetId() {
        return truckFleetId;
    }

    public void setTruckFleetId(Integer truckFleetId) {
        this.truckFleetId = truckFleetId;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }
}
