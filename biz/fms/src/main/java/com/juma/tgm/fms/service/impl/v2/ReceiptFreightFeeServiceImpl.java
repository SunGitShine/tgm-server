package com.juma.tgm.fms.service.impl.v2;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageQueryCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.fms.core.domain.BillDO;
import com.juma.fms.core.domain.BillFilter;
import com.juma.fms.core.domain.ReceiptBillInfoBo;
import com.juma.fms.core.service.BillService;
import com.juma.fms.core.service.ReceiptBillService;
import com.juma.tgm.fms.service.v2.ReceiptFreightFeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @ClassName: ReceiptFreightFeeServiceImpl
 * @Description:
 * @author: liang
 * @date: 2018-08-09 10:26
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
@Service
public class ReceiptFreightFeeServiceImpl implements ReceiptFreightFeeService {

    @Resource
    private BillService billService;

    @Resource
    private ReceiptBillService receiptBillService;

    @Override
    public Page<BillDO> searchBillForReceipt(PageQueryCondition<BillFilter> billFilterPageQueryCondition, LoginEmployee loginEmployee) throws BusinessException {
        Page<BillDO> data = billService.searchBillForReceipt(billFilterPageQueryCondition, loginEmployee);

        return data;
    }

    @Override
    public ReceiptBillInfoBo findReceiptBillInfoBo(BillDO billDO) throws BusinessException {
        ReceiptBillInfoBo receiptBillInfoBo = receiptBillService.findReceiptBillInfoBo(billDO);

        return receiptBillInfoBo;
    }

}
