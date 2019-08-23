package com.juma.tgm.waybill.service.impl;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.PageCondition;
import com.juma.tgm.waybill.dao.WaybillViewDao;
import com.juma.tgm.waybill.domain.view.WaybillViewVo;
import com.juma.tgm.waybill.service.WaybillViewService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: WaybillViewServiceImpl
 * @Description:
 * @author: liang
 * @date: 2018-01-30 14:57
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
@Service
public class WaybillViewServiceImpl implements WaybillViewService {

    @Resource
    private WaybillViewDao waybillViewDao;

    @Override
    public List<WaybillViewVo> searchForApp(PageCondition pageCondition) throws BusinessException {
//        pageCondition = this.buildProjectConditions(pageCondition);
        return waybillViewDao.searchForApp(pageCondition);
    }

    @Override
    public int searchForAppCount(PageCondition pageCondition) throws BusinessException {
//        pageCondition = this.buildProjectConditions(pageCondition);
        return waybillViewDao.searchCount(pageCondition);
    }


//    @Override
//    public PageCondition buildProjectConditions(PageCondition pageCondition) {
//        if (pageCondition == null) return pageCondition;
//        if (pageCondition.getFilters() == null) return pageCondition;
//        Map<String, Object> filters = pageCondition.getFilters();
//        Object obj = filters.get("statusView");
//        if (obj == null) return pageCondition;
//
//        Integer statusView = null;
//        try {
//            statusView = NumberUtils.createInteger(obj.toString());
//        } catch (Exception e) {
//            throw new BusinessException("statusViewCodeError", "errors.paramErrorWithName", "运单状态");
//        }
//
//        //配送完成+未对账=未对账
//        //Waybill.StatusView.UNCHECK
//        if (NumberUtils.compare(statusView, Waybill.StatusView.UNCHECK.getCode()) == 0) {
//            filters.put("reconciliationStatus", Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());
//            filters.put("status", Waybill.Status.PAIED.getCode());
//            filters.remove("statusView");
//            return pageCondition;
//        }
//
//        //配送完成+已对账=待支付(sql)
//        //Waybill.StatusView.WATING_PAY
//
//        //配送完成+已结算=已结算
//        //Waybill.StatusView.CLOSED
//        if (NumberUtils.compare(statusView, Waybill.StatusView.CLOSED.getCode()) == 0) {
//            filters.put("receiptStatus", Waybill.ReceiptStatus.HAS_COLLECTION.getCode());
//            filters.put("status", Waybill.Status.PAIED.getCode());
//            filters.remove("statusView");
//            return pageCondition;
//        }
//
//        return pageCondition;
//    }

}
