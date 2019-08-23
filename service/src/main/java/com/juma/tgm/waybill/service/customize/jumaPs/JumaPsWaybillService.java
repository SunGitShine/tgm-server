package com.juma.tgm.waybill.service.customize.jumaPs;

import com.giants.common.exception.BusinessException;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.waybill.domain.Waybill;

/**
 * 驹马配送定制需求
 * @ClassName: JumaPsWaybillService
 * @Description:
 * @author: liang
 * @date: 2018-03-30 11:52
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public interface JumaPsWaybillService {

    /**
     * 客户经理修改运费价格
     * 1.派车前的运单，可随意修改价格
     * 2.派车后的运单，若修改的价格比之前的价格低，则修改成功后，提示价格审核中，且此时不能再修改价格
     * 3.审核通过，直接显示修改后的价格；审核拒绝，提示审核不通过，并可重新修改价格
     * 4.若是修改的价格比之前的高，则直接修改成功；且可以再次修改
     *
     * @param waybill
     * @param loginEmployee
     */
    Waybill customerManagerModifyFreight(Waybill waybill, LoginEmployee loginEmployee) throws BusinessException;
}
