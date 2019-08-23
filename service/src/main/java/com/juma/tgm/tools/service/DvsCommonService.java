package com.juma.tgm.tools.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.giants.common.exception.BusinessException;
import com.juma.tgm.common.dvsSystem.DvsDataVO;
import com.juma.tgm.daily.vo.ProjectDailyVo;
import com.juma.tgm.tool.domain.DvsParam;

import java.util.List;

public interface DvsCommonService <T> {
    /**
     * 此方式实现从dvs系统获取数据，不实现和具体业务相关的转换等其他工作
     * @param sqlId
     * @param param
     * @return
     * @throws BusinessException
     */
    DvsDataVO getDataFromDvsSystem(Integer sqlId, DvsParam param)throws BusinessException;

    /**
     * 列表查询接口返回的数据才能使用这个方法转换成
     * @param listData
     * @return
     */
    List<JSONObject> convertToJSONObject(DvsDataVO listData);

    List<T> convertToList(DvsDataVO listData, Class<T> clazz);
}
