package com.juma.tgm.flight.domain.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RunningWaybill.java
 * @Description 进行中的运单
 * @author Libin.Wei
 * @Date 2017年5月17日 下午6:27:10
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class RunningWaybill implements Serializable {

    private static final long serialVersionUID = 3794891411676800911L;
    // 运单ID
    private Integer waybillId;
    // 描述(日期、离仓时间、路线离仓、配送点，没有离仓时间取用车时间)
    private String desc;

    // 运单ID集合
    private List<Integer> listWaybillId = new ArrayList<Integer>();

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Integer> getListWaybillId() {
        return listWaybillId;
    }

    public void setListWaybillId(List<Integer> listWaybillId) {
        this.listWaybillId = listWaybillId;
    }

    @Override
    public String toString() {
        return "RunningWaybill [waybillId=" + waybillId + ", desc=" + desc + "]";
    }

}
