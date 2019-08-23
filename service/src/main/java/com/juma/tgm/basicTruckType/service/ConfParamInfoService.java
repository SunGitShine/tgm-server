package com.juma.tgm.basicTruckType.service;

import java.util.Map;

import com.giants.common.exception.BusinessException;
import com.juma.conf.domain.ConfParamOption;
import com.juma.tgm.waybill.domain.ConfParamInfo;

/**
 * 数据字典数据
 * 
 * @author weilibin
 *
 */

public interface ConfParamInfoService {

    /**
     * 
     * @Description 获取货物类型列表
     * @author Libin.Wei
     * @Date 2017年3月31日 下午12:54:15
     * @return
     * @throws BusinessException
     */
    ConfParamInfo goodsTypeList() throws BusinessException;

    /**
     * 
     * @Description 根据参数KEY返回参数选项集合，map的key=optionValue，val=ConfParamOption
     * @author Libin.Wei
     * @Date 2017年3月31日 下午12:56:25
     * @param paramKey 参数KEY
     * @return
     */
    Map<String, ConfParamOption> findConfParamInfoByParamKey(String paramKey);
}
