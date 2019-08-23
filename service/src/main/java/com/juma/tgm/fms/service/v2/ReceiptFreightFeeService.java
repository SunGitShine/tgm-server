package com.juma.tgm.fms.service.v2;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageQueryCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.fms.core.domain.BillDO;
import com.juma.fms.core.domain.BillFilter;
import com.juma.fms.core.domain.ReceiptBillInfoBo;

/**
 * 经纪人端-收款管理-fms
 * @ClassName: ReceiptFreightFeeSerivce
 * @Description:
 * @author: liang
 * @date: 2018-08-08 15:09
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public interface ReceiptFreightFeeService {

    /**
     * 收款单查询
     * @param billFilterPageQueryCondition 条件：客户名称，收款状态；排序：生成时间
     * @param loginEmployee 当前登录人
     * @return 收款单列表
     * @throws BusinessException 业务异常
     */
    Page<BillDO> searchBillForReceipt(PageQueryCondition<BillFilter> billFilterPageQueryCondition, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 获取收款单详情
     * @param billDO billId, billNo
     * @return 收款单详情
     * @throws BusinessException 业务异常
     */
    ReceiptBillInfoBo findReceiptBillInfoBo(BillDO billDO) throws BusinessException;

}
