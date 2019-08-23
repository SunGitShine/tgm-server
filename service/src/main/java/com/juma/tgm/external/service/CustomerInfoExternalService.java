package com.juma.tgm.external.service;

import com.juma.tgm.crm.external.CustomerInfoExternal;

/**
 * @ClassName CustomerInfoExternalService
 * @Description TODO
 * @Author weilibin
 * @Date 2019-07-30 14:35
 * @Version 1.0.0
 */

public interface CustomerInfoExternalService {

    /**
     * @apiDefine ReconcilicationForPayableExternalSuccess
     * @apiSuccess (成功结果参数) {String} reconcilicationNo 应付对账单编号
     * @apiSuccess (成功结果参数) {Integer} reconcilicationId 应付对账单id
     * @apiSuccess (成功结果参数) {Integer} customerId tms系统的客户ID
     * @apiSuccess (成功结果参数) {Integer} crmCustomerId crm系统的客户ID
     * @apiSuccess (成功结果参数) {String} customerName 客户名称
     */

    /**
     * @api com.juma.tgm.external.service.CustomerInfoExternalService.loadCustomerInfoByTmsCustomerId 根据TMS的客户ID获取客户信息
     * @apiDescription 根据TMS的客户ID获取客户信息
     * @apiVersion 1.0.0
     * @apiName loadCustomerInfoByTmsCustomerId
     * @apiGroup 客户查询
     * @apiParam (接口参数) {Integer} customerId 客户id(必填)
     * @apiUse ReconcilicationForPayableExternalSuccess
     */

    /**
     * 根据TMS的客户ID获取
     *
     * @param customerId
     *
     * @return
     */
    CustomerInfoExternal loadCustomerInfoByTmsCustomerId(Integer customerId);
}
