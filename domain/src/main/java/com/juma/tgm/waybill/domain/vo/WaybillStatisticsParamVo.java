package com.juma.tgm.waybill.domain.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于运单统计
 * @ClassName: WaybillStatisticsParamVo
 * @Description:
 * @author: liang
 * @date: 2017-11-17 15:05
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class WaybillStatisticsParamVo implements Serializable {


    private Map<String, Object> filters;


    public Map<String, Object> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, Object> filters) {
        this.filters = filters;
    }

    public void put(String key, Object param){
        if(this.filters == null){
            this.filters = new HashMap<>();
        }
        filters.put(key, param);
    }
}
