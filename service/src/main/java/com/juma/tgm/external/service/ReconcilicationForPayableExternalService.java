package com.juma.tgm.external.service;

import com.juma.tgm.external.domain.ReconcilicationForPayableExternal;
import com.juma.tgm.external.domain.ReconcilicationForPayableExternalFilter;

import java.util.List;

public interface ReconcilicationForPayableExternalService {

    /**
     * @apiDefine ReconcilicationForPayableExternalSuccess
     * @apiSuccess (成功结果参数) {String} reconcilicationNo 应付对账单编号
     * @apiSuccess (成功结果参数) {Integer} reconcilicationId 应付对账单id
     * @apiSuccess (成功结果参数) {Integer} customerId tms系统的客户ID
     * @apiSuccess (成功结果参数) {Integer} crmCustomerId crm系统的客户ID
     * @apiSuccess (成功结果参数) {String} customerName 客户名称
     */

    /**
     * @api com.juma.tgm.external.service.TmsService.listReconcilicationForPayableByNo 通过承运商对账单号获取简单的承运商对账单信息
     * @apiDescription 通过承运商对账单号获取简单的承运商对账单信息
     * @apiVersion 1.0.0
     * @apiName listReconcilicationForPayableByNo
     * @apiGroup 承运商对账单查询
     * @apiParam (接口参数) {Object} ReconcilicationForPayableExternalFilter
     * @apiParam (接口参数) {String[]} listReconcilicationNo 对账单号集合(必填)
     * @apiParam (接口参数) {boolean} isNeedCrmCustomerId 是否需要crmCustomerId(非必填)
     * @apiUse ReconcilicationForPayableExternalSuccess
     */
    List<ReconcilicationForPayableExternal> listReconcilicationForPayableByNo(ReconcilicationForPayableExternalFilter filter);
}
