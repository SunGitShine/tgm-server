package com.juma.tgm.vendor.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.project.domain.Project;
import com.juma.tgm.vendor.domain.VendorMapping;
import com.juma.tgm.vendor.domain.VendorMappingBo;
import com.juma.tgm.vendor.domain.VendorProjectMapping;
import com.juma.tgm.vendor.domain.VendorProjectMappingBo;

import java.util.List;

/**
 * @ClassName VendorMappingService.java
 * @Description 承运商客户关系
 * @author Libin.Wei
 * @Date 2018年8月29日 上午9:58:20
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface VendorMappingService {

    /**
     * 
     * 获取对应租户下所有的承运商客户关系
     * 
     * @author Libin.Wei
     * @Date 2018年8月29日 上午10:00:55
     * @param vendorName
     * @param backMaxPageSize
     * @param loginUser
     * @return
     */
    List<VendorMapping> listVendorMapping(String vendorName, Integer backMaxPageSize, LoginUser loginUser);

    /**
     * 
     * 获取承运商的项目关系
     * 
     * @author Libin.Wei
     * @Date 2018年8月29日 上午10:04:06
     * @param vendorId
     * @param vendorCustomerId
     * @param projectId
     * @param loginUser
     * @return
     */
    VendorProjectMapping findVendorProjectMappingBy(Integer vendorId, Integer vendorCustomerId, Integer projectId,
            LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 获取承运商的项目关系
     * 
     * @author Libin.Wei
     * @Date 2018年8月29日 上午10:07:05
     * @param vendorMappingId
     * @param projectId
     * @return
     * @throws BusinessException
     */
    VendorProjectMapping findProjectMappingByVendorMappingIdAndProjectId(Integer vendorMappingId, Integer projectId)
            throws BusinessException;

    /**
     * 
     * 根据主键获取
     * 
     * @author Libin.Wei
     * @Date 2018年9月10日 下午3:58:31
     * @param vendorMappingId
     * @return
     */
    VendorMapping getVendorMapping(Integer vendorId, LoginUser loginUser);

    /**
     * 
     * 修改承运商名称
     * 
     * @author Libin.Wei
     * @Date 2018年8月29日 下午8:03:56
     * @param vendorId
     * @param vendorName
     * @throws BusinessException
     */
    void updateVendorName(Integer vendorId, String vendorName) throws BusinessException;

    /**
     * 
     * 修改承运商客户的名称
     * 
     * @author Libin.Wei
     * @Date 2018年8月29日 下午8:04:36
     * @param vendorCustomerId
     * @param vendorCustomerName
     * @throws BusinessException
     */
    void updateVendorCustomerName(Integer vendorCustomerId, String vendorCustomerName) throws BusinessException;

    /**
     * 运单共享关系配置列表
     */
    Page<VendorMappingBo> search(PageCondition pageCondition);

    /**
     * 运单共享关系配置新增
     */
    Integer save(VendorMappingBo vendorMappingBo, LoginUser loginUser) throws BusinessException;

    /**
     * 运单共享关系配置保存
     */
    Integer update(VendorMappingBo vendorMappingBo, LoginUser loginUser) throws BusinessException;

    /**
     * 项目关系配置删除
     */
    void deleteVendor(Integer vendorMappingId) throws BusinessException;

    /**
     * 运单共享关系配置列表
     */
    Page<VendorProjectMappingBo> listByVendorMappingId(PageCondition pageCondition);

    /**
     * 项目关系配置新增或保存
     */
    void saveOrUpdateVendorProjectMapping(VendorProjectMappingBo vendorProjectMappingBo) throws BusinessException;

    /**
     * 项目关系配置删除
     */
    void delete(Integer vendorProjectMappingId) throws BusinessException;

    /**
     * 运单共享关系配置编辑
     */
    VendorMappingBo edit(Integer vendorMappingId);

    /**
     * 获取承运商名称
     */
    List<VendorMapping> findVendorName(VendorMapping vendorMapping);

    /**
     * 通过承运商映射id和货源方项目id获取转运项目id
     * 
     * @param vendorMappingId
     * @param sourceProjectId
     * @return
     * @throws BusinessException
     */
    Project getByMappingIdAndSourceProjectId(Integer vendorMappingId, Integer sourceProjectId) throws BusinessException;

    /**
     * 
     * 根据承运商ID删除
     * 
     * @author Libin.Wei
     * @Date 2018年11月7日 下午7:29:11
     * @param vendorId
     * @param loginUser
     * @throws BusinessException
     */
    void deleteByVendorId(Integer vendorId, LoginUser loginUser) throws BusinessException;
}
