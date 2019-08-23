package com.juma.tgm.fms.dao.v3.ext;

import com.giants.common.tools.PageCondition;
import com.juma.tgm.fms.domain.v3.ReconciliationExtraForPayable;
import com.juma.tgm.fms.domain.v3.ReconcilicationForPayableItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

public interface ReconcilicationForPayableItemExtraMapper {

    /**
     * 统计总数
     */
    int itemCount(PageCondition pageCondition);

    /**
     * 分页获取
     */
    List<ReconcilicationForPayableItem> itemSearch(PageCondition pageCondition);

    /**
     *
     * 承运商含税总额
     *@return
     */
    @Select({"SELECT SUM(settle_freight) \n" +
            "FROM reconcilication_for_payable_item \n" +
            "WHERE reconcilication_id = #{reconciliationId}"})
    BigDecimal sumSettleFreight(@Param("reconciliationId") Integer reconciliationId);

    /**
     *
     * 承运商可抵扣进项税费总额
     *@return
     */
    @Select({"SELECT extra_id extraId,SUM(deduction_tax_fee) deductionTaxFee,SUM(management_fee) managementFee\n" +
            "FROM reconciliation_extra_for_payable \n" +
            "WHERE reconciliation_id = #{reconciliationId}"})
    ReconciliationExtraForPayable sumDeductionTaxFee(@Param("reconciliationId") Integer reconciliationId);

}