package com.juma.tgm.tools.service;

import java.util.List;

/**
 * Title: BigDataCommonService
 * Description:大数据中心公共接口
 * Created by gzq on 2019/7/23.
 */
public interface BigDataCommonService {

    /**
     * 从OPS获取数据
     * @param opsId 数据类型id
     * @param param 参数
     * @param clazz 返回类型
     * @return
     */
    <T> List<T> getOpsData(String opsId, String param, Class<T> clazz);

}
