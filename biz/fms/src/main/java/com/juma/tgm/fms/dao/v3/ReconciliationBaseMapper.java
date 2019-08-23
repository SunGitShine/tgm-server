package com.juma.tgm.fms.dao.v3;

import java.util.List;

import com.giants.common.tools.PageCondition;
import com.juma.tgm.fms.domain.v3.vo.ReceivableApplyVo;
import com.juma.tgm.waybill.domain.Waybill;

public interface ReconciliationBaseMapper {

	/**
	 * 分页查询应收未对账申请列表
	 * @param pageCondition
	 * @return
	 */
	List<ReceivableApplyVo> findReceivableApplyPage(PageCondition pageCondition);

	/**条件查询运单列表**/
	List<Waybill> findWaybillByFilter(PageCondition pageCondition);

	/**
	 * 应收未对账申请列表总数
	 * @param pageCondition
	 * @return
	 */
	Integer findReceivableApplyCount(PageCondition pageCondition);
}
