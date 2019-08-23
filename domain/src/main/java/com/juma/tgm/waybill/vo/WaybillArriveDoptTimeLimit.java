package com.juma.tgm.waybill.vo;

import java.io.Serializable;

/**
 * @ClassName WaybillArriveDoptTimeLimit
 * @Description TODO
 * @Author weilibin
 * @Date 2019-06-13 11:18
 * @Version 1.0.0
 */

public class WaybillArriveDoptTimeLimit implements Serializable {

    // 用车时间之前N分钟
    private Integer forward;
    // 用车时间之后N分钟
    private Integer backward;

    public Integer getForward() {
        return forward;
    }

    public void setForward(Integer forward) {
        this.forward = forward;
    }

    public Integer getBackward() {
        return backward;
    }

    public void setBackward(Integer backward) {
        this.backward = backward;
    }
}
