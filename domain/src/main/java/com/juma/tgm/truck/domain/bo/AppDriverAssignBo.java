package com.juma.tgm.truck.domain.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: AppDriverAssignBo
 * @Description:
 * @author: liang
 * @date: 2017-04-14 17:21
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class AppDriverAssignBo implements Serializable{
    /**
     * 司机和车辆信息列表（有序）
     */
    private List<DriverTruckInfoBo> truck;

    private Integer total;


    public List<DriverTruckInfoBo> getTruck() {
        return truck;
    }

    public void setTruck(List<DriverTruckInfoBo> truck) {
        this.truck = truck;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
