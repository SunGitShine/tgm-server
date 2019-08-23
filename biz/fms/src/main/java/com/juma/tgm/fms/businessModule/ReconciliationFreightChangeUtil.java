package com.juma.tgm.fms.businessModule;

import com.giants.common.exception.BusinessException;
import com.juma.tgm.fms.domain.v2.ReconciliationItemNew;
import com.juma.tgm.fms.domain.v2.ReconciliationNew;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * 对账单改价计算
 *
 * @ClassName: ReconciliationFreightChangeUtil
 * @Description:
 * @author: liang
 * @date: 2018-06-06 20:57
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
@Component
public class ReconciliationFreightChangeUtil {

    public static class ReconciliationFreightChangeVo {
        /**
         * 调整额度（税前）
         */
        private BigDecimal adjustAmount;
        /**
         * 税后受影响的额度
         */
        private BigDecimal afterTaxChangeAmount;

        /**
         * 现税前价格(含税价)
         */
        private BigDecimal presentBeforeTaxPrice;
        /**
         * 现税后价格(不含税价)
         */
        private BigDecimal presentAfterTaxPrice;

        public BigDecimal getAdjustAmount() {
            return adjustAmount;
        }

        public void setAdjustAmount(BigDecimal adjustAmount) {
            this.adjustAmount = adjustAmount;
        }

        public BigDecimal getAfterTaxChangeAmount() {
            return afterTaxChangeAmount;
        }

        public void setAfterTaxChangeAmount(BigDecimal afterTaxChangeAmount) {
            this.afterTaxChangeAmount = afterTaxChangeAmount;
        }

        public BigDecimal getPresentBeforeTaxPrice() {
            return presentBeforeTaxPrice;
        }

        public void setPresentBeforeTaxPrice(BigDecimal presentBeforeTaxPrice) {
            this.presentBeforeTaxPrice = presentBeforeTaxPrice;
        }

        public BigDecimal getPresentAfterTaxPrice() {
            return presentAfterTaxPrice;
        }

        public void setPresentAfterTaxPrice(BigDecimal presentAfterTaxPrice) {
            this.presentAfterTaxPrice = presentAfterTaxPrice;
        }
    }

    /**
     * 修改车辆对账
     *
     * @param reconciliationNew 对账表
     * @param itemNewList       对账明细
     */
    public void modifyVehicleReconciliation(ReconciliationNew reconciliationNew, List<ReconciliationItemNew> itemNewList) {
        if (CollectionUtils.isEmpty(itemNewList)) return;
        if (reconciliationNew == null) return;
        //计算司机税前最终价
        BigDecimal beforeTaxPrice = BigDecimal.ZERO;
        //计算司机税后最终价
        BigDecimal afterTaxPrice = BigDecimal.ZERO;
        for (ReconciliationItemNew itemNew : itemNewList) {
            beforeTaxPrice = beforeTaxPrice.add(itemNew.getDriverFinalBeforeTax());
            afterTaxPrice = afterTaxPrice.add(itemNew.getDriverFinalAfterTax());
        }

        reconciliationNew.setDriverFinalAfterTax(afterTaxPrice);
        reconciliationNew.setDriverFinalBeforeTax(beforeTaxPrice);

    }


    /**
     * 修改客户对账
     *
     * @param reconciliationNew 被更新的对账单
     * @param adjustAmount      调整额度
     * @param changeVo          改变后的税前税后费用
     */
    public void modifyCustomerReconciliation(ReconciliationNew reconciliationNew, BigDecimal adjustAmount, ReconciliationFreightChangeVo changeVo) {
        if (reconciliationNew == null)
            throw new BusinessException("reconciliationNewNull", "errors.paramCanNotNullWithName", "对账单");
        if (adjustAmount == null)
            throw new BusinessException("reconciliationNewNull", "errors.paramCanNotNullWithName", "客户对账调整量");
        if (reconciliationNew.getCustomerFinalBeforeTax() == null)
            throw new BusinessException("CustomerFinalBeforeTaxNull", "errors.paramCanNotNullWithName", "客户税前费用");
        if (reconciliationNew.getTaxRateValue() == null)
            throw new BusinessException("TaxRateValueNull", "errors.paramCanNotNullWithName", "客户税率");
        if (changeVo == null)
            throw new BusinessException("ReconciliationFreightChangeVoNull", "errors.paramCanNotNullWithName", "记录参数");
        //修改后的税前费用
        BigDecimal presentBeforeTaxPrice = reconciliationNew.getCustomerFinalBeforeTax().add(adjustAmount).setScale(2, BigDecimal.ROUND_HALF_UP);

        changeVo.setPresentBeforeTaxPrice(presentBeforeTaxPrice);
        BigDecimal taxRate = reconciliationNew.getTaxRateValue() == null ? BigDecimal.ZERO : reconciliationNew.getTaxRateValue();
        //修改后的税后费用
        BigDecimal presentAfterTaxPrice = presentBeforeTaxPrice.divide(BigDecimal.ONE.add(taxRate), 2, BigDecimal.ROUND_HALF_UP);

        changeVo.setPresentAfterTaxPrice(presentAfterTaxPrice);

        //税后差额 = 原税后 - 现在的税后
//        BigDecimal afterTaxChangeAmount = reconciliationNew.getCustomerFinalAfterTax().subtract(presentAfterTaxPrice);
        BigDecimal afterTaxChangeAmount = presentAfterTaxPrice.subtract(reconciliationNew.getCustomerFinalAfterTax());
        changeVo.setAfterTaxChangeAmount(afterTaxChangeAmount);

        reconciliationNew.setCustomerFinalAfterTax(presentAfterTaxPrice);
        reconciliationNew.setCustomerFinalBeforeTax(presentBeforeTaxPrice);

    }

    /**
     * 计算改价后的差价
     *
     * @param itemNew
     * @param adjustAmount
     * @return
     * @throws BusinessException
     */
    public ReconciliationFreightChangeVo calculateVehiclePriceSpread(ReconciliationItemNew itemNew, BigDecimal adjustAmount) throws BusinessException {
        if (itemNew == null)
            throw new BusinessException("ReconciliationItemNewNull", "errors.paramCanNotNullWithName", "对账明细");
        if (itemNew.getDriverFinalAfterTax() == null)
            throw new BusinessException("DriverFinalAfterTaxNull", "errors.paramCanNotNullWithName", "车辆对账明细税后价格");
        if (itemNew.getDriverFinalBeforeTax() == null)
            throw new BusinessException("DriverFinalBeforeTaxNull", "errors.paramCanNotNullWithName", "车辆对账明细税前价格");
        if (adjustAmount == null)
            throw new BusinessException("adjustAmountNull", "errors.paramCanNotNullWithName", "车辆对账明细调整量");

        ReconciliationFreightChangeVo changeVo = new ReconciliationFreightChangeVo();
        changeVo.setAdjustAmount(adjustAmount);
        //现在的税前价格 = 原税前 + 调整额
        BigDecimal presentBeforeTaxPrice = itemNew.getDriverFinalBeforeTax().add(adjustAmount);
        //现在的税后 = 现在的税前 / （1 + 税率）
        BigDecimal taxRate = itemNew.getTaxRateValue() == null ? BigDecimal.ZERO : itemNew.getTaxRateValue();
        BigDecimal presentAfterTaxPrice = presentBeforeTaxPrice.divide(BigDecimal.ONE.add(taxRate), 2, BigDecimal.ROUND_HALF_UP);
        //税后差价 = 原来税后 - 现在税后
        BigDecimal afterTaxChangeAmount = presentAfterTaxPrice.subtract(itemNew.getDriverFinalAfterTax()).setScale(2, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
        changeVo.setPresentAfterTaxPrice(presentAfterTaxPrice);
        changeVo.setPresentBeforeTaxPrice(presentBeforeTaxPrice);
        changeVo.setAfterTaxChangeAmount(afterTaxChangeAmount);

        itemNew.setDriverFinalAfterTax(presentAfterTaxPrice);
        itemNew.setDriverFinalBeforeTax(presentBeforeTaxPrice);

        return changeVo;

    }

}
