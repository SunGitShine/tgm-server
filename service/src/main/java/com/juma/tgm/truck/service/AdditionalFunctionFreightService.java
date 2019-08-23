package com.juma.tgm.truck.service;


import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.truck.domain.AdditionalFunctionFreight;

import java.util.List;

/**
 * Created by Administrator on 2016/5/11 0011.
 * 符加功能运费service
 */
public interface AdditionalFunctionFreightService{

    /**
     * 根据条件返回运费
     * @param freightId 基础运费ID
     * @param additionalFunctionId 附加功能ID
     * @return
     */
    AdditionalFunctionFreight getAddFuncFreightByTypeAF(Integer freightId,Integer additionalFunctionId);

    /**
     * 添加组记录
     * @param List<AdditionalFunctionFreight>
     * @param loginEmployee
     * @return
     */
    void insert(List<AdditionalFunctionFreight> additionalFunctionFreight, LoginEmployee loginEmployee);

    /**
     * 修改，先删除组记录，后插入组记录
     * @param List<AdditionalFunctionFreight>
     * @param loginEmployee
     * @return
     */
    void update(List<AdditionalFunctionFreight> additionalFunctionFreight, Integer freightId, LoginEmployee loginEmployee);
    
    /**
     * 禁用或启用
     * @param freight
     */
    void update(AdditionalFunctionFreight freight);

    /**
     * 删除
     * @param freightId 基础运费ID
     * @return
     */
    void delete(Integer freightId);

    /**
     * 
     * 根据条件获取
     * 
     * @author Libin.Wei
     * @Date 2017年12月7日 下午6:14:57
     * @param freightId
     * @return
     */
    List<AdditionalFunctionFreight> findAddFuncFreightByFreightId(int freightId);
}
