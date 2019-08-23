package com.juma.tgm.truck.service;

import java.util.List;

import com.juma.tgm.truck.domain.AdditionalFunction;
import com.juma.tgm.truck.domain.AdditionalFunctionBo;

/**
 * @author rx
 * @version V1.0
 * @Description: 符加功能servcie
 * @date 2016/05/13 13:55
 */
public interface AdditionalFunctionService {

    /**
     * 根据ID返回符加功能信息
     * 
     * @param functionId
     *            附加功能ID
     * @return AdditionalFunction
     */
    AdditionalFunction getAdditionFunction(Integer functionId);

    /**
     * 根据基础运费ID得到该车型的符加功能
     * 
     * @param freightId
     *            基础运费ID
     * @return List<AdditionalFunction>
     */
    @Deprecated
    List<AdditionalFunction> getAdditionalFunctionByTruckTypeId(Integer freightId);

    /**
     * 获取所有附加功能
     * 
     * @return List<AdditionalFunction>
     */
    List<AdditionalFunction> getAllAdditionalFunction();

    /**
     * 获取所有附加功能与车型基本信息
     * 
     * @return getFunctionList
     * @return List<AdditionalFunctionBo>
     */
    List<AdditionalFunctionBo> getFunctionList();

    /**
     * 根据KEY返回符加功能信息
     * 
     * @param functionKey
     *            附加功能key
     * @return AdditionalFunction
     */
    AdditionalFunction findAdditionFunctionByKey(String functionKey);

    /**
     * 根据KEY返回符加功能信息
     * 
     * @param functionIds
     *            附加功能ID字符串
     * @return List<AdditionalFunction>
     */
    List<AdditionalFunction> getAdditionFunctionByIds(String functionIds);
}
