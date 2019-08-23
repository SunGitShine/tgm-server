package com.juma.tgm.waybill.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.juma.tgm.waybill.domain.WaybillReceiveAddressCargo;

/**
 * @ClassName WaybillReceiveAddressCargoService.java
 * @Description 配送地对应货物信息
 * @author Libin.Wei
 * @Date 2018年4月23日 下午4:04:52
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface WaybillReceiveAddressCargoService {

    /**
     * 
     * 根据主键获取
     * 
     * @author Libin.Wei
     * @Date 2018年4月23日 下午4:09:00
     * @param cargoId
     * @return
     */
    WaybillReceiveAddressCargo getWaybillReceiveAddressCargo(Integer cargoId);

    /**
     * 
     * 根据地址ID获取
     * 
     * @author Libin.Wei
     * @Date 2018年4月23日 下午4:09:13
     * @param addressId
     * @return
     */
    List<WaybillReceiveAddressCargo> listByAddressId(Integer addressId);

    /**
     * 
     * 批量添加
     * 
     * @author Libin.Wei
     * @Date 2018年4月23日 下午4:38:12
     * @param listCargo
     * @throws BusinessException
     */
    void batchInsert(List<WaybillReceiveAddressCargo> listCargo) throws BusinessException;

    /**
     * 
     * 添加
     * 
     * @author Libin.Wei
     * @Date 2018年4月23日 下午4:09:27
     * @param cargo
     * @throws BusinessException
     */
    void insert(WaybillReceiveAddressCargo cargo) throws BusinessException;

    /**
     * 
     * 删除，物理删
     * 
     * @author Libin.Wei
     * @Date 2018年4月23日 下午4:09:41
     * @param cargoId
     * @throws BusinessException
     */
    void delete(Integer cargoId) throws BusinessException;

    /**
     * 根据地址ID删除
     * 
     * @author Libin.Wei
     * @Date 2018年4月23日 下午4:39:30
     * @param addressId
     * @throws BusinessException
     */
    void deleteByAddressId(Integer addressId) throws BusinessException;
}
