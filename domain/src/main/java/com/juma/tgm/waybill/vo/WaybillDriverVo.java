package com.juma.tgm.waybill.vo;

import com.juma.vms.driver.domain.Driver;
import java.io.Serializable;

/**
 * @ClassName WaybillDriverVo
 * @Description TODO
 * @Author weilibin
 * @Date 2019-06-18 09:10
 * @Version 1.0.0
 */

public class WaybillDriverVo implements Serializable {

    private Driver driver;
    private Integer waybillCount;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Integer getWaybillCount() {
        return waybillCount;
    }

    public void setWaybillCount(Integer waybillCount) {
        this.waybillCount = waybillCount;
    }
}
