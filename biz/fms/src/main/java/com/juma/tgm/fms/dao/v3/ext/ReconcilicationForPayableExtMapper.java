package com.juma.tgm.fms.dao.v3.ext;

import com.giants.common.tools.PageCondition;
import com.juma.tgm.fms.domain.v3.ReconcilicationForPayable;
import com.juma.tgm.fms.domain.v3.bo.ReconcilicationForPayApply;
import com.juma.tgm.waybill.domain.Waybill;

import java.util.List;

public interface ReconcilicationForPayableExtMapper {

    /**
     * 统计总数
     */
    int searchCount(PageCondition pageCondition);

    /**
     * 分页获取
     */
    List<ReconcilicationForPayApply> search(PageCondition pageCondition);

    /**
     * 分页获取
     */
    List<Waybill> selectByFilter(PageCondition pageCondition);

    /**
     * 承运商对账单统计总数
     */
    int vendorCount(PageCondition pageCondition);

    /**
     * 承运商对账单分页获取
     */
    List<ReconcilicationForPayable> vendorSearch(PageCondition pageCondition);

}
